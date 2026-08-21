/** @file memory.c
 *  @brief Implements starting point for a memory hierarchy with caching and RAM.
 *  @see memory.h
 */

#include "memory.h"

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <stdbool.h>
#include <math.h>

#include "config.h"

#define _NUMBLOCKS(cs)	(cs*1024)/BLOCK_SIZE
#define _NUMLINES(cs, ca) _NUMBLOCKS(cs)/ca

#define _INDEX(addr, cs, ca) ((addr >> (int)log(BLOCK_SIZE)) & (_NUMLINES(cs, ca)-1))
#define _TAG(addr, cs, ca)	(addr >> (int)(log(BLOCK_SIZE) + log(_NUMLINES(cs, ca))))

/**
 * for block size = 64
 * 
 * uassosiativ: NUMLINES == NUMBLOCKS
 * 0: block,
 * 1: block,
 * 2: block,
 * .
 * .
 * .
 * 63: block
 * 
 * offset = log(block_size)
 * 
 * 0b ...t tttt iiii iioo oooo
 * rshift 0b0110
 *     0b... 0100 0000
 * and 0b... 0011 1111 <- numblocks-1
 * 
 * assosiativ: NUMLINES == NUMBLOCKS/assosiativitet
 * 0: block, block, block, block,
 * 1: block, block, block, block,
 * 2: block, block, block, block,
 * ...
 * 15: block, block, block, block
 * 
 * rshift 0b0110
 *     0b... 0001 0000
 * and 0b... 0000 1111 <- numlines-1 == (numblocks/associativity)-1
 * 0b ...t tttt ttii iioo oooo
 * 
 * 
 */

/** block
 * 
 * 	Type of ´block´ in the cache.
 */
typedef struct block {
	bool valid;	// block is valid data
	bool dirty;	// block has been written to
	int tag;	// tag portion of physical address
} block_t;

typedef block_t * queue_t;

/**	cache
 * 	Type of all caches.
 */
typedef struct cache {
	int shots;	// attempted hit/miss
	int hits;	// total number of hits
	int misses;	// total number of misses

	// number of attempts of types read and write
	int reads;
	int writes;
	queue_t *queues; // each line is a queue
} cache_t;

struct memory {
	int fetches;	// total number of block fetches from lower memory

	int writes;		// total number of writes to lower memory
};

struct memory memory;

static
cache_t *IC;
static
cache_t *DC;
static
cache_t *L2;

static
int hits = 0;
static
int misses = 0;

static
unsigned long instr_count;

/**	cache_size
 * 
 * 	Returns size of cache in unit MiB based
 * 	on pointer comparison with static caches.
 * 
 * 	@param	cache target cache struct
 * 
 * 	@return	size of cache
 */
static
int size (cache_t *cache)
{
	if (cache == IC)
		return CACHE_SIZE_IC;
	if (cache == DC)
		return CACHE_SIZE_DC;
	if (cache == L2)
		return CACHE_SIZE_L2;

	fprintf(stderr, "Invalid cache");
	exit(-1);
}

/**	associativity
 * 
 * 	Returns the associativity for 
 * 	the	static caches
 * 
 * 	@param	cache target cache struct
 * 
 * 	@return	cache associativity
 */
static
int associativity (cache_t *cache)
{
	if (cache == IC)
		return ASSOCIATIVITY_IC;
	if (cache == DC)
		return ASSOCIATIVITY_DC;
	if (cache == L2)
		return ASSOCIATIVITY_L2;

	fprintf(stderr, "Invalid cache");
	exit(-1);
}

#define NUMBLOCKS(cache) _NUMBLOCKS(size(cache)) // for non-associative cache use only
#define NUMLINES(cache) _NUMLINES(size(cache),associativity(cache)) // for associativity
#define INDEX(addr, cache) _INDEX(address, size(cache), associativity(cache)) // Compute 'index' field of address
#define TAG(address, cache) _TAG(address, size(cache), associativity(cache)) // Compute 'tag' field of address

void memory_init(void)
{
	if (WRITE_POLICY != 0 && WRITE_POLICY != 1) {
		fprintf(stderr, "Write policy must be either \"Write Through\" or \"Write Back\".\n");
		exit(1);
	}

	// Initialize Data Cache
	DC = calloc(1, sizeof(cache_t));
	assert(DC != NULL);
	DC->queues = malloc(NUMLINES(DC) * sizeof(queue_t));
	assert(DC->queues != NULL);
	for (int i = 0; i < NUMLINES(DC); i++){
		DC->queues[i] = calloc(associativity(DC), sizeof(block_t));
		assert(DC->queues[i] != NULL);
	}

	// Initialize Instruction Cache
	IC = calloc(1, sizeof(cache_t));
	assert(IC != NULL);
	IC->queues = malloc(NUMLINES(IC) * sizeof(queue_t));
	assert(IC->queues != NULL);
	for (int i = 0; i < NUMLINES(IC); i++){
		IC->queues[i] = calloc(associativity(IC), sizeof(block_t));
		assert(IC->queues[i] != NULL);
	}

	// Initialize L2 unified cache
	L2 = calloc(1, sizeof(cache_t));
	assert(L2 != NULL);
	L2->queues = malloc(NUMLINES(L2) * sizeof(queue_t));
	assert(L2->queues != NULL);
	for (int i = 0; i < NUMLINES(L2); i++){
		L2->queues[i] = calloc(associativity(L2), sizeof(block_t));
		assert(L2->queues[i] != NULL);
	}

	memory = (struct memory){
		.fetches = 0,
		.writes = 0
	};

	instr_count = 0;
}

/** find_block_index
 * 
 * 	Function that retreives the queue in the cache and compares the tag to find the queue index.
 * 
 * 	@param 		cache		target cache struct
 * 	@param		address		physical address from CPU
 * 
 * 	@return		i			index given the tag in the cache queue
 * 	@return		-1			Failed to find the block index
 */
static
int find_block_index (cache_t *cache, unsigned int address)
{
	queue_t queue = cache->queues[INDEX(address, cache)];

	for (int i = 0; i < associativity(cache); i++) {
		if (queue[i].valid && (queue[i].tag == TAG(address, cache)))
			return i;
	}

	return -1;
}

/** block_dirty
 * 
 * 	Function that checks whether the block at the index position is dirty.
 * 
 * 	@param 		cache		target cache struct
 * 	@param		address		physical address from CPU
 * 
 * 	@return		1			block is dirty
 * 	@return		0			block not dirty
 */
static
int block_dirty (cache_t *cache, unsigned int address)
{
	return cache->queues[INDEX(address, cache)][associativity(cache)-1].dirty;
}

/** block_dirty
 * 
 * 	Function that finds the least recently used block at the appropriate index.
 * 
 * 	@param 		cache		target cache struct
 * 	@param		address		physical address from CPU
 * 
 * 	@return		least recently used block			
 */
static
block_t find_LRU_block (cache_t *cache, unsigned int address)
{
	return cache->queues[INDEX(address, cache)][associativity(cache)-1];
}

// binary concatenation
#define CONCAT(x,y) x*(int)log(y)+y

/**	Used for communication between DC and L2 in memory write (finding L2 block to set to dirty)
 * 
 * 	pseudo address used to compute equivalent block in lower cache=
 * 	(tag ## index) << offset length
 * 				   \______________/
 * 						  |
 * 						  ^
 * 	(with a binary block size this equates to multiplication with the block size)
 */
#define PSEUDO_ADDRESS(cache, address, LRU) (CONCAT(LRU.tag, INDEX(address, cache)) * BLOCK_SIZE)

/** set_block_dirty
 * 
 * 	Function that sets the block at the index position to dirty.
 * 
 * 	@param 		cache		target cache struct
 * 	@param		address		physical address from CPU
 */
static
void set_block_dirty (cache_t *cache, unsigned int address)
{
	queue_t queue = cache->queues[INDEX(address, cache)];

	for (int i = 0; i < associativity(cache); i++) {
		if (queue[i].valid && (queue[i].tag == TAG(address, cache)))
			queue[i].dirty = true;
	}

}

/** move_2_front
 * 
 * 	Function that overwrites the elements of the queue array towards the queue index, storing the block at the 
 *  index position and placing it in the front
 * 
 * 	@param 		index		integer position of the block in the queue
 * 	@param		queue		target queue struct
 */
static
void move_2_front (int index, queue_t queue)
{
	block_t temp = queue[index];

	for (int i = index; i > 0; i--) {
		queue[i] = queue[i - 1];
	}

	queue[0] = temp;
}

/** overwrite
 * 
 * Function that overwrites on the given queue and creates a new block for the front of the queue
 * 
 * 	@param 		cache		target cache struct
 * 	@param		address		physical address from CPU		
 */
static
void overwrite(cache_t *cache, unsigned int address)
{
	//find relevant queue in cache
	queue_t queue = cache->queues[INDEX(address, cache)];

	//shift the array towards the rear
	for (int i = associativity(cache); i > 0; i--) {
		queue[i] = queue[i - 1];
	}

	// place the new input in the front
	queue[0] = (block_t){
	.valid = true,
	.dirty = false,
	.tag = TAG(address, cache)
	};
}

/** memory_get
 * 
 * 	Unified static function called by memory fetch, read and write
 * 	performs the hit/miss check operation
 * 
 * 	@param	cache		cache to be accessed
 * 	@param	address		physical address from CPU
 * 
 * 	@return	
 */
static
void memory_get(cache_t *cache, unsigned int address)
{
	cache->shots++;
	queue_t queue = cache->queues[INDEX(address, cache)];
	int index;

	if ((index = find_block_index(cache, address)) >= 0) {

		cache->hits++;

		move_2_front(index, queue);

	} else {

		cache->misses++;

		/**	On cache miss:
		 * If write policy is write through, block->dirty is never true,
		 * and no special action is needed.
		 * 
		 * If write policy is write back, when block->dirty is true,
		 * L2 must be updated before a new block is fetched.
		 */
		if (block_dirty(cache, address)) {

			if (cache != L2) {
				L2->writes++;

			} else {
				memory.writes++;

			}
		}

		/**	Block fetch policy,
		 * 	On miss, try again at lower level
		 */
		if (cache != L2)
			memory_get(L2, address);
		else
			memory.fetches++;

		// block must be updated
		overwrite(cache, address);

	}
}

void memory_fetch(unsigned int address, data_t *data)
{
	IC->reads++;
	memory_get(IC, address);

	instr_count++;
}

void memory_read(unsigned int address, data_t *data)
{
	DC->reads++;
	memory_get(DC, address);

	instr_count++;
}

void memory_write(unsigned int address, data_t *data)
{
	cache_t *cache = DC;
	queue_t queue = cache->queues[INDEX(address, cache)];
	block_t LRU;
	int index;

	cache->shots++;
	cache->writes++;

	/** The processor cache is checked whether block containing physical address is present.
	 * 
	 * 	Moves the block found by the index to the front since replacement policy is LRU.
	 */ 
	if ((index = find_block_index(cache, address)) >= 0) {
		cache->hits++; // hit!
		move_2_front(index, queue);

	} else {
		cache->misses++; // miss!

#if WRITE_POLICY == WRITE_THROUGH

		memory_get(L2, address);
		overwrite(cache, address);
	}

	L2->writes++;
	memory.writes++;

#else //Write Back

		/**
		 * 0b tttt tttt tttt tttt ttti iiii iioo oooo
		 * 0b tttt tttt tttt tttt iiii iiii iioo oooo
		 */

		LRU = find_LRU_block(cache, address);

		if(LRU.dirty){
			L2->writes++;



			set_block_dirty(L2, PSEUDO_ADDRESS(cache, address, LRU));
		}

		memory_get(L2, address);
		overwrite(cache, address);
	}

	set_block_dirty(cache, address);

#endif


	instr_count++;
}

static
void print_output(void)
{
	int DC_attempts = DC->reads + DC->writes;
	int IC_attempts = IC->reads + IC->writes;
	int L2_attempts = L2->reads + L2->writes;

	float L1_hits = IC->hits + DC->hits;

	float DC_Hit_ratio = DC->hits / DC->shots;
	float IC_Hit_ratio = IC->hits / IC->shots;
	float L2_Hit_ratio = L2->hits / L2->shots;

	printf("Cache:\t\tHits:\t\tMisses:\t\tShots:\t\tReads:\t\tWrites:\n");
	printf("DC\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", DC->hits, DC->misses, DC->shots, DC->reads, DC->writes);
	printf("IC\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", IC->hits, IC->misses, IC->shots, IC->reads, IC->writes);
	printf("L2\t\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", L2->hits, L2->misses, L2->shots, L2->reads, L2->writes);
	printf("L1 average hit percentage: %f%%.\n", (L1_hits / instr_count)*100);
}


void memory_finish(void)
{
	fprintf(stdout, "Executed %lu instructions.\n\n", instr_count);
	
	print_output();

	for (int i = 0; i < NUMLINES(DC); i++) {
		free(DC->queues[i]);
	}
	free(DC->queues);
	free(DC);
	
	for (int i = 0; i < NUMLINES(IC); i++) {
		free(IC->queues[i]);
	}
	free(IC->queues);
	free(IC);
	
	for (int i = 0; i < NUMLINES(L2); i++) {
		free(L2->queues[i]);
	}
	free(L2->queues);
	free(L2);
}
