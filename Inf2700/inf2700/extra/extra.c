/** @brief Data record

    A record consists of an array of pointers to field values.
    Because in general, the types of the fields of a record are
    unknown at compile time, the memory of these values has to be
    allocated at run time with @ref new_record.  When accessing these
    values, the generic (void *) pointers must be casted to the
    correct C types (int *) and (char *).  See the source code of @ref
    put_record_info and @ref fill_record as examples of how to access
    field values of a record.  */

// typedef struct tbl_desc_struct {
//   schema_p sch;      /**< schema of this table. */
//   int num_records;   /**< number of records this table has. */
//   page_p current_pg; /**< current page being accessed. */
//   tbl_p next;        /**< next tbl_desc in the database. */
// } tbl_desc_struct;

// typedef struct field_desc_struct {
//   char *name;        /**< field name */
//   field_type type;   /**< field type */
//   int len;           /**< field length (number of bytes) */
//   int offset;        /**< offset from the beginning of the record */
//   field_desc_p next; /**< next field_desc of the table, NULL if no more */
// } field_desc_struct;

// typedef struct select_desc {
//   tbl_p from_tbl, right_tbl;
//   char where_attr[11], where_op[3];
//   int where_val;
//   int num_attrs;
//   char *attrs[10];
// } select_desc;

// typedef struct schema_struct {
//   char *name;           /**< table name */
//   field_desc_p first;   /**< first field_desc */
//   field_desc_p last;    /**< last field_desc */
//   int num_fields;       /**< number of fields in the table */
//   int len;              /**< record length */
//   tbl_p tbl;            /**< table descriptor */
// } schema_struct;

int main()
{
    int array[11] = {2,3,4,5,6,7,8,9,22,33,45};
    int low       = 0;
    int high      = 10;
    int flag      = 0;
    int s         = 4;

    while(low <= high){
        int index = low+(high-low)/2;
        if(array[index] == s){
            flag = 1;
            printf("Founded: %d \n",index);
            break;
        }
        else if(array[index] < s){
            low   = index+1;
        }
        else{
            high = index-1;
        }
    }
    if(flag == 0){
        printf("Not Found!\n");
    }

    return 0;
}

/* int find_record_int_val ( record r, schema_p s, int offset,
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
} */

/* int page_get_int_at ( page_p p, int offset )
{
  if ( !page_valid_pos_for_get (p, offset) )
    {
      put_msg (FATAL, "page_get_int_at\n");
      exit (EXIT_FAILURE);
    }
  int res = (int) *((int *)((p->content) + offset));
  p->current_pos += INT_SIZE;
  return res;
} */