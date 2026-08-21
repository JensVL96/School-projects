/** @file config.h
 * Defines 
 */

#define CACHE_SIZE_IC 32  // MiB
#define CACHE_SIZE_DC 32  // MiB
#define CACHE_SIZE_L2 256 // MiB
#define BLOCK_SIZE 64 //B


// write policies

// do not touch
#define WRITE_THROUGH 0
#define WRITE_BACK 1

// touch if you want to
#define WRITE_POLICY WRITE_BACK


#define ASSOCIATIVITY_IC 4
#define ASSOCIATIVITY_DC 8
#define ASSOCIATIVITY_L2 8