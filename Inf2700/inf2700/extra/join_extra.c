typedef struct field_desc_struct {
  char *name;        /**< field name */
  field_type type;   /**< field type */
  int len;           /**< field length (number of bytes) */
  int offset;        /**< offset from the beginning of the record */
  field_desc_p next; /**< next field_desc of the table, NULL if no more */
} field_desc_struct;

/** @brief Table/record schema */
/** A schema is a linked list of @ref field_desc_struct "field descriptors".
    All records of a table are of the same length.
*/
typedef struct schema_struct {
  char *name;           /**< table name */
  field_desc_p first;   /**< first field_desc */
  field_desc_p last;    /**< last field_desc */
  int num_fields;       /**< number of fields in the table */
  int len;              /**< record length */
  tbl_p tbl;            /**< table descriptor */
} schema_struct;

/** @brief Table descriptor */
/** A table descriptor allows us to find the schema and
    run-time infomation about the table.
 */
typedef struct tbl_desc_struct {
  schema_p sch;      /**< schema of this table. */
  int num_records;   /**< number of records this table has. */
  page_p current_pg; /**< current page being accessed. */
  tbl_p next;        /**< next tbl_desc in the database. */
} tbl_desc_struct;

typedef struct page_struct {
  char *content;   /**< BLOCK_SIZE of bytes */
  int page_nr;
  block_p block;   /**< the correspoding file block */
  pq_elm_p qelm;   /**< the corresponding elm in pfifo */ 
  int pinned;      /**< non-zoro if the block is pinned to the page */
  int dirty;       /**< non-zero if the content has been changed (dirty) */
  int free_pos;    /**< beginning of free space */
  int current_pos; /**< current position for next access */
} page_struct;

int find_record_int_val ( record r, schema_p s, int offset,
			  int (*op) (int, int), int val)
{
  page_p pg = get_page_for_next_record (s);
  if ( pg == NULL ) return 0;
  int pos, rec_val;
  for ( ; pg != NULL; pg = get_page_for_next_record (s) )
    {
      pos = page_current_pos (pg);
      rec_val = page_get_int_at (pg, pos + offset);
      if ( (*op) (rec_val, val) )
	{
	  page_set_current_pos (pg, pos);
	  get_page_record ( pg, r, s );
	  return 1;
	}
      else
	page_set_current_pos (pg, pos + s->len);
    }
  return 0;
}

int add_field ( schema_p s, field_desc_p f )
{
  if ( s == NULL )
    return -1;
  if ( s->len + f->len > BLOCK_SIZE - PAGE_HEADER_SIZE )
    {
      put_msg (ERROR,
	       "schema already has %d bytes, adding %d will exceed limited %d bytes.\n",
	       s->len, f->len, BLOCK_SIZE - PAGE_HEADER_SIZE);
      return -1;
    }
  if ( s->num_fields == 0)
    {
      s->first = f;
      f->offset = 0;
    }
  else
    {
      s->last->next = f;
      f->offset = s->len;
    }
  s->last = f;
  s->num_fields++;
  s->len += f->len;
  return s->num_fields;
}

int append_record ( record r, schema_p s )
{
  tbl_p tbl = s->tbl;
  page_p pg = get_page_for_append ( s->name );
  if (pg == NULL)
    {
      put_msg (FATAL, "Failed to get page for appending to \"%s\".\n",
	       s->name);
      exit (EXIT_FAILURE);
    }
  if ( put_page_record (pg, r, s) == -1 )
    {
      /* not enough space in the current page */
      unpin (pg);
      pg = get_next_page ( pg );
      if (pg == NULL)
	{
	  put_msg (FATAL, "Failed to get page for \"%s\" block %d.\n",
		   s->name, page_block_nr(pg) + 1);
	  exit (EXIT_FAILURE);
	}
      if ( put_page_record (pg, r, s) == -1 )
	{
	  put_msg (FATAL, "Failed to put record to page for \"%s\" block %d.\n",
		   s->name, page_block_nr(pg) + 1);
	  exit (EXIT_FAILURE);
	}
    }
  tbl->current_pg = pg;
  tbl->num_records++;
  return 0;
}

static page_p get_page_for_next_record ( schema_p s )
{
  page_p pg = s->tbl->current_pg;
  if (peof(pg)) return NULL;
  if (eop(pg))
    {
      unpin (pg);
      pg = get_next_page (pg);
      if ( pg == NULL)
	{
	  put_msg (FATAL, "get_page_for_next_record failed at block %d\n",
		   page_block_nr(pg) + 1);
	  exit (EXIT_FAILURE);
	}
      page_set_pos_begin (pg);
      s->tbl->current_pg = pg;
    }
  return pg;
}

record new_record ( schema_p s )
{
  if ( s == NULL )
    {
      put_msg (ERROR,  "new_record: NULL schema!\n");
      exit (EXIT_FAILURE);
    }
  record res = malloc (sizeof(void *) * s->num_fields);
	
  /* allocate memory for the fields */
  field_desc_p f;
  size_t i = 0;
  for ( f = s->first; f != NULL; f = f->next, i++)
    {
	res[i] =  malloc (f->len);
    }
  return res;
}

static field_desc_p copy_field ( field_desc_p f )
{
  field_desc_p res = (field_desc_p) malloc (sizeof (field_desc_struct));
  res->name = (char *) malloc (strlen (f->name) + 1);
  strcpy (res->name, f->name);
  res->type = f->type;
  res->len = f->len;
  res->offset = 0;
  res->next = NULL;
  return res;  
}

static int get_page_record ( page_p p, record r, schema_p s )
{
  if ( p == NULL ) return 0;
  if (!page_valid_pos_for_get_with_schema (p, s))
    {
      put_msg (FATAL, "try to get record at invalid position.\n");
      exit (EXIT_FAILURE);
    }
  field_desc_p fld_desc;
  size_t i = 0;
  for ( fld_desc = s->first; fld_desc != NULL;
	fld_desc = fld_desc->next, i++)
    if ( is_int_field (fld_desc) )
      assign_int_field (r[i], page_get_int(p));
    else
      page_get_str(p, r[i], fld_desc->len);
  return 1;
}