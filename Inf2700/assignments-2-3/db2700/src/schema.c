/***********************************************************
 * Schema for assignments in the Databases course INF-2700 *
 * UIT - The Arctic University of Norway                   *
 * Author: Weihai Yu                                       *
 ************************************************************/

#include "schema.h"
#include "pmsg.h"
#include <string.h>
#include <stdbool.h>

/** @brief Field descriptor */
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
  char *name;           /**< schema (table) name */
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


/** @brief Database tables*/
tbl_p db_tables; /**< a linked list of table descriptors */

void put_field_info(pmsg_level level, field_desc_p f) {
  if (!f) {
    put_msg(level,  "  empty field\n");
    return;
  }
  put_msg(level, "  \"%s\", ", f->name);
  if (is_int_field(f))
    append_msg(level,  "int ");
  else
    append_msg(level,  "str ");
  append_msg(level, "field, len: %d, offset: %d, ", f->len, f->offset);
  if (f->next)
    append_msg(level,  ", next field: %s\n", f->next->name);
  else
    append_msg(level,  "\n");
}

void put_schema_info(pmsg_level level, schema_p s) {
  if (!s) {
    put_msg(level,  "--empty schema\n");
    return;
  }
  field_desc_p f;
  put_msg(level, "--schema %s: %d field(s), totally %d bytes\n",
          s->name, s->num_fields, s->len);
  for (f = s->first; f; f = f->next)
    put_field_info(level, f);
  put_msg(level, "--\n");
}

void put_tbl_info(pmsg_level level, tbl_p t) {
  if (!t) {
    put_msg(level,  "--empty tbl desc\n");
    return;
  }
  put_schema_info(level, t->sch);
  put_file_info(level, t->sch->name);
  put_msg(level, " %d blocks, %d records\n",
          file_num_blocks(t->sch->name), t->num_records);
  put_msg(level, "----\n");
}

void put_record_info(pmsg_level level, record r, schema_p s) {
  field_desc_p f;
  size_t i = 0;
  put_msg(level, "Record: ");
  for (f = s->first; f; f = f->next, i++) {
    if (is_int_field(f))
      append_msg(level,  "%d", *(int *)r[i]);
    else
      append_msg(level,  "%s", (char *)r[i]);

    if (f->next)
      append_msg(level,  " | ");
  }
  append_msg(level,  "\n");
}

void put_db_info(pmsg_level level) {
  char *db_dir = system_dir();
  if (!db_dir) return;
  put_msg(level, "======Database at %s:\n", db_dir);
  for (tbl_p tbl = db_tables; tbl; tbl = tbl->next)
    put_tbl_info(level, tbl);
  put_msg(level, "======\n");
}

field_desc_p new_int_field(char const* name) {
  field_desc_p res = malloc(sizeof (field_desc_struct));
  res->name = strdup(name);
  res->type = INT_TYPE;
  res->len = INT_SIZE;
  res->offset = 0;
  res->next = 0;
  return res;
}

field_desc_p new_str_field(char const* name, int len) {
  field_desc_p res = malloc(sizeof (field_desc_struct));
  res->name = strdup(name);
  res->type = STR_TYPE;
  res->len = len;
  res->offset = 0;
  res->next = 0;
  return res;
}

static void release_field_desc(field_desc_p f) {
  if (f) {
    free(f->name);
    free(f);
    f = 0;
  }
}

int is_int_field(field_desc_p f) {
  return f ? (f->type == INT_TYPE) : 0;
}

field_desc_p field_desc_next(field_desc_p f) {
  if (f)
    return f->next;
  else {
    put_msg(ERROR, "field_desc_next: NULL field_desc_next.\n");
    return 0;
  }
}

static schema_p make_schema(char const* name) {
  schema_p res = malloc(sizeof (schema_struct));
  res->name = strdup(name);
  res->first = 0;
  res->last = 0;
  res->num_fields = 0;
  res->len = 0;
  return res;
}

/** Release the memory allocated for the schema and its field descriptors.*/
static void release_schema(schema_p sch) {
  if (!sch) return;

  field_desc_p f, nextf;
  f = sch->first;
  while (f) {
    nextf = f->next;
    release_field_desc(f);
    f = nextf;
  }
  free(sch->name);
  free(sch);
}

char const* const schema_name(schema_p sch) {
  if (sch)
    return sch->name;
  else {
    put_msg(ERROR, "schema_name: NULL schema.\n");
    return 0;
  }
}

field_desc_p schema_first_fld_desc(schema_p sch) {
  if (sch)
    return sch->first;
  else {
    put_msg(ERROR, "schema_first_fld_desc: NULL schema.\n");
    return 0;
  }
}

field_desc_p schema_last_fld_desc(schema_p sch) {
  if (sch)
    return sch->last;
  else {
    put_msg(ERROR, "schema_last_fld_desc: NULL schema.\n");
    return 0;
  }
}

int schema_num_flds(schema_p sch) {
  if (sch)
    return sch->num_fields;
  else {
    put_msg(ERROR, "schema_num_flds: NULL schema.\n");
    return -1;
  }
}

int schema_len(schema_p sch) {
  if (sch)
    return sch->len;
  else {
    put_msg(ERROR, "schema_len: NULL schema.\n");
    return -1;
  }
}

const char tables_desc_file[] = "db.db"; /***< File holding table descriptors */

static char* concat_names(char const* name1, char const* sep, char const* name2) {
  char *res = malloc((strlen(name1)) + (strlen(sep)) + (strlen(name2)) + 1);
  strcpy(res, name1);
  strcat(res, sep);
  strcat(res, name2);
  return res;
}

static void save_tbl_desc(FILE *fp, tbl_p tbl) {
  schema_p sch = tbl->sch;
  fprintf(fp, "%s %d\n", sch->name, sch->num_fields);
  field_desc_p fld = schema_first_fld_desc(sch);
  while (fld) {
    fprintf(fp, "%s %d %d %d\n",
            fld->name, fld->type, fld->len, fld->offset);
    fld = fld->next;
  }
  fprintf(fp, "%d\n", tbl->num_records);
}

static void save_tbl_descs() {
  /* backup the descriptors first in case we need some manual investigation */
  char *tbl_desc_backup = concat_names("__backup", "_", tables_desc_file);
  rename(tables_desc_file, tbl_desc_backup);
  free(tbl_desc_backup);

  FILE *dbfile = fopen(tables_desc_file, "w");
  tbl_p tbl = db_tables, next_tbl = 0;
  while (tbl) {
    save_tbl_desc(dbfile, tbl);
    release_schema(tbl->sch);
    next_tbl = tbl->next;
    free(tbl);
    tbl = next_tbl;
  }
  fclose(dbfile);
}

static void read_tbl_descs() {
  FILE *fp = fopen(tables_desc_file, "r");
  if (!fp) return;
  char name[30] = "";
  schema_p sch = NULL;
  field_desc_p fld = NULL;
  int num_flds = 0, fld_type, fld_len;
  while (!feof(fp)) {
    if (fscanf(fp, "%s %d\n", name, &num_flds) < 2) {
      fclose(fp);
      return;
    }
    sch = new_schema(name);
    for (size_t i = 0; i < num_flds; i++) {
      fscanf(fp, "%s %d %d", name, &(fld_type), &(fld_len));
      switch (fld_type) {
      case INT_TYPE:
        fld = new_int_field(name);
        break;
      case STR_TYPE:
        fld = new_str_field(name, fld_len);
        break;
      }
      fscanf(fp, "%d\n", &(fld->offset));
      add_field(sch, fld);
    }
    fscanf(fp, "%d\n", &(sch->tbl->num_records));
  }
  db_tables = sch->tbl;
  fclose(fp);
}

int open_db(void) {
  pager_terminate(); /* first clean up for a fresh start */
  pager_init();
  read_tbl_descs();
  return 1;
}

void close_db(void) {
  save_tbl_descs();
  db_tables = 0;
  pager_terminate();
}

schema_p new_schema(char const* name) {
  tbl_p tbl = malloc(sizeof (tbl_desc_struct));
  tbl->sch = make_schema(name);
  tbl->sch->tbl = tbl;
  tbl->num_records = 0;
  tbl->current_pg = 0;
  tbl->next = db_tables;
  db_tables = tbl;
  return tbl->sch;
}

tbl_p get_table(char const* name) {
  for (tbl_p tbl = db_tables; tbl; tbl = tbl->next)
    if (strcmp(name, tbl->sch->name) == 0)
      return tbl;
  return 0;
}

schema_p get_schema(char const* name) {
  tbl_p tbl = get_table(name);
  if (tbl) return tbl->sch;
  else return 0;
}

void remove_table(tbl_p t) {
  if (!t) return;

  for (tbl_p tbl = db_tables, prev = 0;
       tbl;
       prev = tbl, tbl = tbl->next)
    if (tbl == t) {
      if (t == db_tables)
        db_tables = t->next;
      else
        prev->next = t->next;

      close_file(t->sch->name);
      char *tbl_backup = concat_names("_", "_", t->sch->name);
      rename(t->sch->name, tbl_backup);
      free(tbl_backup);
      release_schema(t->sch);
      free(t);
      return;
    }
}

void remove_schema(schema_p s) {
  if (s) remove_table(s->tbl);
}

static field_desc_p dup_field(field_desc_p f) {
  field_desc_p res = malloc(sizeof (field_desc_struct));
  res->name = strdup(f->name);
  res->type = f->type;
  res->len = f->len;
  res->offset = 0;
  res->next = 0;
  return res;
}

static schema_p copy_schema(schema_p s, char const* dest_name) {
  if (!s) return 0;
  schema_p dest = new_schema(dest_name);
  for (field_desc_p f = s->first; f; f = f->next)
    add_field(dest, dup_field(f));
  return dest;
}

static field_desc_p get_field(schema_p s, char const* name) {
  for (field_desc_p f = s->first; f; f = f->next)
    if (strcmp(f->name, name) == 0) return f;
  return 0;
}

static char* tmp_schema_name(char const* op_name, char const* name) {
  char *res = malloc((strlen(op_name)) + (strlen(name)) + 10);
  int i = 0;
  do
    sprintf(res, "%s__%s_%d", op_name, name, i++);
  while (get_schema(res));

  return res;
}

static schema_p make_sub_schema(schema_p s, int num_fields, char *fields[]) {
  if (!s) return 0;

  char *sub_sch_name = tmp_schema_name("project", s->name);
  schema_p res = new_schema(sub_sch_name);
  free(sub_sch_name);
  
  field_desc_p f = 0;
  for (size_t i= 0; i < num_fields; i++) {
    f = get_field(s, fields[i]);
    if (f)
      add_field(res, dup_field(f));
    else {
      put_msg(ERROR, "\"%s\" has no \"%s\" field\n",
              s->name, fields[i]);
      remove_schema(res);
      return 0;
    }
  }
  return res;
}

int add_field(schema_p s, field_desc_p f) {
  if (!s) return 0;
  if (s->len + f->len > BLOCK_SIZE - PAGE_HEADER_SIZE) {
    put_msg(ERROR,
            "schema already has %d bytes, adding %d will exceed limited %d bytes.\n",
            s->len, f->len, BLOCK_SIZE - PAGE_HEADER_SIZE);
    return 0;
  }
  if (s->num_fields == 0) {
    s->first = f;
    f->offset = 0;
  }
  else {
    s->last->next = f;
    f->offset = s->len;
  }
  s->last = f;
  s->num_fields++;
  s->len += f->len;
  return s->num_fields;
}

record new_record(schema_p s) {
  if (!s) {
    put_msg(ERROR,  "new_record: NULL schema!\n");
    exit(EXIT_FAILURE);
  }
  record res = malloc((sizeof (void *)) * s->num_fields);

  /* allocate memory for the fields */
  field_desc_p f;
  size_t i = 0;
  for (f = s->first; f; f = f->next, i++) {
    res[i] =  malloc(f->len);
  }
  return res;
}

void release_record(record r, schema_p s) {
  if (!(r && s)) {
    put_msg(ERROR,  "release_record: NULL record or schema!\n");
    return;
  }
  for (size_t i = 0; i < s->num_fields; i++)
    free(r[i]);
  free(r);
  r = 0;
}

void assign_int_field(void const* field_p, int int_val) {
  *(int *)field_p = int_val;
}

void assign_str_field(void* field_p, char const* str_val) {
  strcpy(field_p, str_val);
}

int fill_record(record r, schema_p s, ...) {
  if (!(r && s)) {
    put_msg(ERROR,  "fill_record: NULL record or schema!\n");
    return 0;
  }
  va_list vals;
  va_start(vals, s);
  field_desc_p f;
  size_t i = 0;
  for (f = s->first; f; f = f->next, i++) {
    if (is_int_field(f))
      assign_int_field(r[i], va_arg(vals, int));
    else
      assign_str_field(r[i], va_arg(vals, char*));
  }
  return 1;
}

static void fill_sub_record(record dest_r, schema_p dest_s,
                            record src_r, schema_p src_s) {
  field_desc_p src_f, dest_f;
  size_t i = 0, j = 0;
  for (dest_f = dest_s->first; dest_f; dest_f = dest_f->next, i++) {
    for (j = 0, src_f = src_s->first;
         strcmp(src_f->name, dest_f->name) != 0;
         j++, src_f = src_f->next)
      ;
    if (is_int_field(dest_f))
      assign_int_field(dest_r[i], *(int *)src_r[j]);
    else
      assign_str_field(dest_r[i], (char *)src_r[j]);
  }
}

int equal_record(record r1, record r2, schema_p s) {
  if (!(r1 && r2 && s)) {
    put_msg(ERROR,  "equal_record: NULL record or schema!\n");
    return 0;
  }

  field_desc_p fd;
  size_t i = 0;;
  for (fd = s->first; fd; fd = fd->next, i++) {
    if (is_int_field(fd)) {
      if (*(int *)r1[i] != *(int *)r2[i])
        return 0;
    }
    else {
      if (strcmp((char *)r1[i], (char *)r2[i]) != 0)
        return 0;
    }
  }
  return 1;
}

void set_tbl_position(tbl_p t, tbl_position pos) {
  switch (pos) {
  case TBL_BEG:
    {
      t->current_pg = get_page(t->sch->name, 0);
      page_set_pos_begin(t-> current_pg);
    }
    break;
  case TBL_END:
    t->current_pg = get_page_for_append(t->sch->name);
  }
}

int eot(tbl_p t) {
  return (peof(t->current_pg));
}

/** check if the the current position is valid */
static int page_valid_pos_for_get_with_schema(page_p p, schema_p s) {
  return (page_valid_pos_for_get(p, page_current_pos(p))
          && (page_current_pos(p) - PAGE_HEADER_SIZE) % s->len == 0);
}

/** check if the the current position is valid */
static int page_valid_pos_for_put_with_schema(page_p p, schema_p s) {
  return (page_valid_pos_for_put(p, page_current_pos(p), s->len)
          && (page_current_pos(p) - PAGE_HEADER_SIZE) % s->len == 0);
}

static page_p get_page_for_next_record(schema_p s) {
  page_p pg = s->tbl->current_pg;
  if (peof(pg)) return 0;
  if (eop(pg)) {
    unpin(pg);
    pg = get_next_page(pg);
    if (!pg) {
      put_msg(FATAL, "get_page_for_next_record failed at block %d\n",
              page_block_nr(pg) + 1);
      exit(EXIT_FAILURE);
    }
    page_set_pos_begin(pg);
    s->tbl->current_pg = pg;
  }
  return pg;
}

static int get_page_record(page_p p, record r, schema_p s) {
  if (!p) return 0;
  if (!page_valid_pos_for_get_with_schema(p, s)) {
    put_msg(FATAL, "try to get record at invalid position.\n");
    exit(EXIT_FAILURE);
  }
  field_desc_p fld_desc;
  size_t i = 0;
  for (fld_desc = s->first; fld_desc;
       fld_desc = fld_desc->next, i++)
    if (is_int_field(fld_desc))
      assign_int_field(r[i], page_get_int(p));
    else
      page_get_str(p, r[i], fld_desc->len);
  return 1;
}

int get_record(record r, schema_p s) {
  page_p pg = get_page_for_next_record(s);
  return pg ? get_page_record(pg, r, s) : 0;
}

static int int_equal(int x, int y) {
  return x == y;
}

static int int_smaller(int x, int y) {
  return x > y;
}

static int int_smallerThanEqual(int x, int y) {
  return x >= y;
}

static int int_greater(int x, int y) {
  return x < y;
}

static int int_greaterThanEqual(int x, int y) {
  return x < y;
}

static int int_not_equal(int x, int y) {
  return x != y;
}


static int find_record_int_val(record r, schema_p s, int offset,
                               int (*op) (int, int), int val) {
  page_p pg = get_page_for_next_record(s);
  if (!pg) return 0;
  int pos, rec_val;
  for (; pg; pg = get_page_for_next_record(s)) {
    pos = page_current_pos(pg);
    rec_val = page_get_int_at (pg, pos + offset);
    if ((*op) (val, rec_val)) {
      page_set_current_pos(pg, pos);
      get_page_record(pg, r, s);
      return 1;
    }
    else
      page_set_current_pos(pg, pos + s->len);
  }
  return 0;
}

static int put_page_record(page_p p, record r, schema_p s) {
  if (!page_valid_pos_for_put_with_schema(p, s))
    return 0;

  field_desc_p fld_desc;
  size_t i = 0;
  for (fld_desc = s->first; fld_desc;
       fld_desc = fld_desc->next, i++)
    if (is_int_field(fld_desc))
      page_put_int(p, *(int *)r[i]);
    else
      page_put_str(p, (char *)r[i], fld_desc->len);
  return 1;
}

int put_record(record r, schema_p s) {
  page_p p = s->tbl->current_pg;

  if (!page_valid_pos_for_put_with_schema(p, s))
    return 0;

  field_desc_p fld_desc;
  size_t i = 0;
  for (fld_desc = s->first; fld_desc;
       fld_desc = fld_desc->next, i++)
    if (is_int_field(fld_desc))
      page_put_int(p, *(int *)r[i]);
    else
      page_put_str(p, (char *)r[i], fld_desc->len);
  return 1;
}

void append_record(record r, schema_p s) {
  tbl_p tbl = s->tbl;
  page_p pg = get_page_for_append(s->name);
  if (!pg) {
    put_msg(FATAL, "Failed to get page for appending to \"%s\".\n",
            s->name);
    exit(EXIT_FAILURE);
  }
  if (!put_page_record(pg, r, s)) {
    /* not enough space in the current page */
    unpin(pg);
    pg = get_next_page(pg);
    if (!pg) {
      put_msg(FATAL, "Failed to get page for \"%s\" block %d.\n",
              s->name, page_block_nr(pg) + 1);
      exit(EXIT_FAILURE);
    }
    if (!put_page_record(pg, r, s)) {
      put_msg(FATAL, "Failed to put record to page for \"%s\" block %d.\n",
              s->name, page_block_nr(pg) + 1);
      exit(EXIT_FAILURE);
    }
  }
  tbl->current_pg = pg;
  tbl->num_records++;
}

static void display_tbl_header(tbl_p t) {
  if (!t) {
    put_msg(INFO,  "Trying to display non-existant table.\n");
    return;
  }
  schema_p s = t->sch;
  for (field_desc_p f = s->first; f; f = f->next)
    put_msg(FORCE, "%20s", f->name);
  put_msg(FORCE, "\n");
  for (field_desc_p f = s->first; f; f = f->next) {
    for (size_t i = 0; i < 20 - strlen(f->name); i++)
      put_msg(FORCE, " ");
    for (size_t i = 0; i < strlen(f->name); i++)
      put_msg(FORCE, "-");
  }
  put_msg(FORCE, "\n");
}

static void display_record(record r, schema_p s) {
  field_desc_p f = s->first;
  for (size_t i = 0; f; f = f->next, i++) {
    if (is_int_field(f))
      put_msg(FORCE, "%20d", *(int *)r[i]);
    else
      put_msg(FORCE, "%20s", (char *)r[i]);
  }
  put_msg(FORCE, "\n");
}

void table_display(tbl_p t) {
  if (!t) return;
  display_tbl_header(t);

  schema_p s = t->sch;
  record rec = new_record(s);
  set_tbl_position(t, TBL_BEG);
  while (get_record(rec, s)) {
    display_record(rec, s);
  }
  put_msg(FORCE, "\n");

  release_record(rec, s);
}

/* Perform a search operation on a table.
 * 
 * Args:
 *   t: The table to search.
 *   attr: The name of the attribute to search.
 *   op: The comparison operator
 *   val: The value to search for.
 *   type : The search type (0 for binary search, 1 for linear search).
 * 
 * Returns:
 *   tbl_p: A new table containing records that match the search criteria, or NULL if no records match or on error.
 */
tbl_p table_search(tbl_p t, char const* attr, char const* op, int val, int type) {
  if (!t) return 0;

  int (*cmp_op)() = 0;

  if (strcmp(op, "=") == 0)
    cmp_op = int_equal;
  else if (strcmp(op, "<") == 0)
    cmp_op = int_smaller;
  else if (strcmp(op, "<=") == 0)
    cmp_op = int_smallerThanEqual;
  else if (strcmp(op, ">") == 0)
    cmp_op = int_greater;
  else if (strcmp(op, ">=") == 0)
    cmp_op = int_greaterThanEqual;
  else if (strcmp(op, "!=") == 0)
    cmp_op = int_not_equal;
  else {
    put_msg(ERROR, "unknown comparison operator \"%s\".\n", op);
    return 0;
  }

  schema_p s = t->sch;
  field_desc_p f;
  size_t i = 0;
  for (f = s->first; f; f = f->next, i++)
    if (strcmp(f->name, attr) == 0) {
      if (f->type != INT_TYPE) {
        put_msg(ERROR, "\"%s\" is not an integer field.\n", attr);
        return 0;
      }
      break;
    }
  if (!f) return 0;

  // Dynamic Allocation
  char *tmp_name = tmp_schema_name("select", s->name);
  schema_p res_sch = copy_schema(s, tmp_name);
  free(tmp_name);

  // Fixed-sized array
  // char tmp_name[30] = "tmp_tbl__";
  // strcat (tmp_name, s->name);
  // schema_p res_sch = copy_schema ( s, tmp_name );

  record rec = new_record ( s );
  set_tbl_position ( t, TBL_BEG );

  // Runs the binary search or linear search depending on the function argument
  if (type == 0) {
    binary_search (rec, s, f->offset, cmp_op, val);
    put_record_info (DEBUG, rec, s);
    append_record ( rec, res_sch );
  } else {
    while (find_record_int_val(rec, s, f->offset, cmp_op, val)) {
      put_record_info(DEBUG, rec, s);
      append_record(rec, res_sch);
    }
  }

  release_record(rec, s);
  return res_sch->tbl;
}

tbl_p table_project(tbl_p t, int num_fields, char* fields[]) {
  schema_p s = t->sch;
  schema_p dest = make_sub_schema(s, num_fields, fields);
  if (!dest) return 0;

  record rec = new_record(s), rec_dest = new_record(dest);

  set_tbl_position(t, TBL_BEG);
  while (get_record(rec, s)) {
    fill_sub_record(rec_dest, dest, rec, s);
    put_record_info(DEBUG, rec_dest, dest);
    append_record(rec_dest, dest);
  }

  release_record(rec, s);
  release_record(rec_dest, dest);

  return dest->tbl;
}

/* Copy fields from input records into the destination record, based on schema comparisons.

Args:
  left: The schema of the left table.
  right: The schema of the right table.
  dest: The schema of the destination (result) table.
  left_rec: The record containing data from the left table.
  right_rec: The record containing data from the right table.
  dest_rec: The record for the destination schema.
  offset: The field offset for the left table's field to join.

Returns:
  This function doesn't return a value; it updates the 'dest_rec' directly.
*/
void placing_records(schema_p left, schema_p right, schema_p dest, record left_rec, record right_rec, record dest_rec, int offset)
{
  field_desc_p tmp_l, tmp_r, tmp_d;

  schema_p tmp_left = copy_schema(left, "tmp_left");
  schema_p tmp_right = copy_schema(right, "tmp_right");
  schema_p tmp_dest = copy_schema(dest, "tmp_dest");

  // printf("\nplacing record in result\n");
  
  put_record_info(0, left_rec, tmp_left);
  put_record_info(0, right_rec, tmp_right);

  tmp_l = tmp_left->first;
  tmp_r = tmp_right->first;


  int index = 0;
  int i = 0;
  int j = 0;

  // put_record_info(0, left_rec, tmp_left);
  for(tmp_d = tmp_dest->first; tmp_d != NULL; tmp_r = tmp_r->next, tmp_l = tmp_l->next, i++, j++) {
    
    if(!strcmp(tmp_d->name, tmp_l->name)) {
      memcpy(dest_rec[index], left_rec[i], tmp_l->len);
      index++;
      tmp_l = tmp_left->first;
      tmp_d = tmp_d->next;
      i = 0;
    }
    if(!strcmp(tmp_d->name, tmp_r->name)) {
      memcpy(dest_rec[index], right_rec[j], tmp_r->len);
      index++;
      tmp_r = tmp_right->first;
      tmp_d = tmp_d->next;
      j = 0;
    }
  }
  // printf("\nresult record\n");
  // put_record_info(0, dest_rec, tmp_dest);

  // Free the temporary schemas.
  remove_schema(tmp_left);
  remove_schema(tmp_right);
  remove_schema(tmp_dest);
  return;
}


// /* Perform an integer field join operation between two tables and update a result schema.

// Args:
//   left: The schema of the left table.
//   right: The schema of the right table.
//   l_off: The field offset for the left table's field to join.
//   r_off: The field offset for the right table's field to join.
//   result: The schema representing the result of the join operation.

// Returns:
//   The updated 'result' schema containing the records.
// */
// tbl_p joining_int_field(schema_p left, schema_p right, field_desc_p l_off, field_desc_p r_off, schema_p result) 
// {
//   // Pointers for a left, right, and a temporary page.
//   page_p left_pg, right_pg, temp_pg;

//   // Create a new record for the left table and set the table position.
//   record left_rec = new_record(left); 
//   set_tbl_position(left->tbl, TBL_BEG);
//   left_pg = get_page_for_next_record(left);

//   // Create a new record for the right table and set the table position.
//   record right_rec = new_record(right);
//   set_tbl_position(right->tbl, TBL_BEG);
//   right_pg = get_page_for_next_record(right);

//   // put_schema_info(0, result);
//   // put_tbl_info(0, result->tbl);

//   int left_pos, left_rec_val, right_pos, right_rec_val;

//   // Iterate through records in the left table.
//   for ( ; left_pg != NULL; left_pg = get_page_for_next_record(left)) {
//     left_pos = page_current_pos(left_pg);
//     left_rec_val = page_get_int_at(left_pg, left_pos + l_off->offset /*Pid*/);
//     page_set_current_pos(left_pg, left_pos);
//     // printf("left pos %d,\tleft value %d\n", left_pos, left_rec_val);
//     get_page_record(left_pg, left_rec, left);
//     page_set_current_pos(left_pg, left_pos + left->len);
//     // put_record_info(0, left_rec, left);

//     // Iterate through records in the right table.
//     for ( ; right_pg != NULL; right_pg = get_page_for_next_record(right)) {
//         right_pos = page_current_pos (right_pg);
//         right_rec_val = page_get_int_at (right_pg, right_pos + r_off->offset);
//         // printf("right pos %d,\tright value %d\n", right_pos, right_rec_val);
//         if (right_rec_val == left_rec_val) {
//           page_set_current_pos(right_pg, right_pos);
//           get_page_record(right_pg, right_rec, right);

//           // Create a new record for the 'result' schema.
//           record temp_rec = new_record(result);
//           // printf("at line %d\n", __LINE__);
//           // put_record_info(0, left_rec, left);
//           // put_record_info(0, right_rec, right);

//           // Place records from the left and right tables into the 'result' schema.
//           placing_records(left, right, result, left_rec, right_rec, temp_rec, l_off->offset);
//           // put_record_info(0, temp_rec, result);

//           // printf("at line %d\n", __LINE__);
//           // Append the temp record to the 'result' schema.
//           append_record(temp_rec, result);
//           // release_record(temp_rec, result);
//           break;
//         } else
//           page_set_current_pos(right_pg, right_pos + right->len);
//     }
//     set_tbl_position(right->tbl, TBL_BEG);
//   }

//   return result;
// }

// /* Combine fields from two schemas and create a new schema.

// Args:
//   left: The schema of the left table to be joined.
//   right: The schema of the right table to be joined.
//   join_sch: The schema for the joined table where common fields will be combined.

// Returns:
//   An array of field names common to both input schemas, followed by NULL.
//   The 'join_sch' schema is updated with the fields from both schemas.
// */
// char **combine_fields(schema_p left, schema_p right, schema_p join_sch)
// {
//   // Calculate the maximum number of fields when both schemas are combined.
//   int max_fields = left->num_fields + right->num_fields;
//   // Allocate memory for an array of field names.
//   char **name = malloc(sizeof(char) * max_fields);

//   int counter = 0;
//   int i = 0;

//   // Temporary schemas for left and right tables.
//   schema_p tmp_left = copy_schema(left, "tmp_left");
//   schema_p tmp_right = copy_schema(right, "tmp_right");
//   field_desc_p tmp_l, tmp_r;

//   // Iterate through the fields of both schemas to find common field names.
//   for (tmp_l = tmp_left->first; tmp_l != NULL; tmp_l = tmp_l->next, i++) {
//     for (tmp_r = tmp_right->first; tmp_r != NULL; tmp_r = tmp_r->next) {
//       if (strcmp(tmp_l->name, tmp_r->name) == 0) {
//         strcpy(name[i], tmp_l->name);
//         counter++;
//       }
//     }
//   }
//   tmp_l = tmp_left->first;
//   tmp_r = tmp_right->first;

//   // Iterate through both schemas to add fields to the 'join_sch' schema.
//   for ( ; tmp_l != NULL && tmp_r != NULL ; ) {
//     if (strcmp(tmp_l->name, name[0])) {
//       // printf("\nadding field: %s\n", tmp_l->name);
//       add_field(join_sch, tmp_l);
//       tmp_l = tmp_l->next;
//     } else {
//       tmp_l = tmp_l->next;
//     }
//     if (strcmp(tmp_r->name, name[0])) {
//       // printf("\nadding field: %s\n", tmp_r->name);
//       add_field(join_sch, tmp_r);
//       tmp_r = tmp_r->next;
//     } else {
//       // printf("\nadding field: %s\n", tmp_r->name);
//       add_field(join_sch, tmp_r);
//       tmp_r = tmp_r->next;
//     }
//   }
//   // remove_schema(tmp_left);
//   // remove_schema(tmp_right);
//   // put_schema_info(0, join_sch);

//   // Set the last element of the 'name' array to NULL to terminate it properly.
//   name[counter] = NULL;
//   return name;
// }

// /* Perform a natural join operation on two tables.

// Args:
//   left: The left table to join.
//   right: The right table to join.

// Returns:
//   A table in side of the new schema representing 
//   the result of the natural join operation.
//   The schema defines the structure of the joined table.
// */
// tbl_p table_natural_join (tbl_p left, tbl_p right)
// {
//   // Pointers to the first field descriptions in the schemas.
//   field_desc_p curr_left = left->sch->first;
//   field_desc_p curr_right = right->sch->first;
//   // Pointers for tracking the current fields to join from the schemas.
//   field_desc_p save_left, save_right;

//   // Create a new schema for the joined table and set its name.
//   schema_p join_sch;
//   char join_name[30] = "natural_join";
//   join_sch = new_schema(join_name);

//   // Combine the fields from the left and right schemas 
//   // into a single array of field names.
//   char **save = combine_fields(left->sch, right->sch, join_sch);

//   put_tbl_info(0, left);
//   put_tbl_info(0, right);

//   // printf("\n\nleft:\t num_fields: %d, num_records: %d, rec length: %d\n", left->sch->num_fields, left->num_records, left->sch->len);
//   // printf("right:\t num_fields: %d, num_records: %d, rec length: %d\n", right->sch->num_fields, right->num_records, right->sch->len);

//   // Iterate through all field names.
//   for (int j = 0; save[j] != NULL; ) {
//     // printf("\nIs current left: %s and right: %s same as duplicate: %s nr %d\n", curr_left->name, curr_right->name, save[j], j);
//     // Check if the current field names from both schemas match.
//     if (strcmp(curr_right->name, save[j]) == 0 && strcmp(curr_right->name, save[j]) == 0) {
//       // printf("\nThey are the same!\n");
//       // The field is assumed an integer, perform integer field joining.
//       //if (is_int_field (curr_right)) {
//         // printf("\nit's an integer field\n");
//       joining_int_field(left->sch, right->sch, curr_left, curr_right, join_sch);
//       //}
//       j++;
//     } else if (strcmp(curr_left->name, save[j]) != 0) {
//       // If the current left field name doesn't match, advance to the next left field.
//       curr_left = curr_left->next;
//     } else if (strcmp(curr_right->name, save[j]) != 0) {
//       // If the current right field name doesn't match, advance to the next right field.
//       curr_right = curr_right->next;
//     }
//   }

//   return join_sch->tbl;  
// }

// Define the read_block function
tbl_p read_block(tbl_p table, int block_index, int block_size, int num_blocks) {
    // Check if the block_index is within a valid range.
    if (block_index < 0 || block_index >= num_blocks) {
        return NULL; // Invalid block index.
    }

    // Calculate the starting record index for the block.
    int start_record_index = block_index * block_size;
    // Calculate the number of records to read in this block.
    int records_to_read = (start_record_index + block_size <= table->num_records) ? block_size : (table->num_records - start_record_index);

    // Create a new table for the block.
    tbl_p block_table = create_empty_table(table->sch);

    // Iterate through the records in the block and append them to the block table.
    for (int i = 0; i < records_to_read; i++) {
        record source_record = get_record(table, start_record_index + i);
        append_record(source_record, block_table);
    }

    return block_table;
}

// Define the common_attribute_matches function for comparing integer attributes
bool common_attribute_matches(schema_p left, schema_p right, record left_record, record right_record) {
    for (field_desc_p left_field = left->first; left_field != NULL; left_field = left_field->next) {
        for (field_desc_p right_field = right->first; right_field != NULL; right_field = right_field->next) {
            // Check if there's a common attribute
            if (strcmp(left_field->name, right_field->name) == 0) {
                // Extract the field values from both records.
                int left_value = *((int*)get_field_value(left_record, left_field));
                int right_value = *((int*)get_field_value(right_record, right_field));

                // Compare the integer values.
                if (left_value == right_value) {
                    // Values match for this common attribute.
                    return true;
                }
            }
        }
    }

    // If no common attributes matched, return false.
    return false;
}


tbl_p nested_loop_join(tbl_p left, tbl_p right) {
    // Create a result schema for the joined table.
    schema_p result_schema;
    char join_name[30] = "nested_loop_join";
    result_schema = new_schema(join_name);
    
    // Create an empty result table using the result schema.
    tbl_p result_table = create_empty_table(result_schema);
    
    // Iterate through records in the left table.
    for (int i = 0; i < left->num_records; i++) {
        record left_record = get_record(left, i);
        
        // Iterate through records in the right table.
        for (int j = 0; j < right->num_records; j++) {
            record right_record = get_record(right, j);
            
            // Check if the common attribute values match.
            if (common_attribute_matches(left->sch, right->sch, left_record, right_record)) {
                // Create a new record for the result schema.
                record combined_record = new_record(result_schema);
                
                // Place records from the left and right tables into the combined record.
                placing_records(left->sch, right->sch, result_schema, left_record, right_record, combined_record, 0);
                
                // Append the combined record to the result table.
                append_record(combined_record, result_table);
                
                // Release the memory for the combined record.
                release_record(combined_record, result_schema);
            }
        }
    }
    
    return result_table;
}

tbl_p block_nested_loop_join(tbl_p left, tbl_p right) {
    // Create a result schema for the joined table and set its name.
    schema_p result_schema;
    char join_name[30] = "block_nested_loop_join";
    result_schema = new_schema(join_name);
    
    // Create an empty result table using the result schema.
    tbl_p result_table = create_empty_table(result_schema);
    
    // Define the block size for each table (adjust as needed).
    int block_size = 100;

    int num_blocks_left = (left->num_records * left->sch->len + block_size - 1) / block_size;
    int num_blocks_right = (right->num_records * right->sch->len + block_size - 1) / block_size;

    // Iterate through blocks in the left table.
    for (int left_block = 0; left_block < num_blocks_left; left_block++) {
        // Read a block of records from the left table.
        tbl_p left_block_table = read_block(left, left_block, block_size, num_blocks_left);
        
        // Iterate through blocks in the right table.
        for (int right_block = 0; right_block < num_blocks_right; right_block++) {
            // Read a block of records from the right table.
            tbl_p right_block_table = read_block(right, right_block, block_size, num_blocks_right);
            
            // Iterate through records in the left block.
            for (int i = 0; i < left_block_table->num_records; i++) {
                record left_record = get_record(left_block_table, i);
                
                // Iterate through records in the right block.
                for (int j = 0; j < right_block_table->num_records; j++) {
                    record right_record = get_record(right_block_table, j);
                    
                    // Check if the common attribute values match (you need to define this function).
                    if (common_attribute_matches(left_record, right_record)) {
                        // Create a new record for the result schema.
                        record combined_record = new_record(result_schema);
                        
                        // Use the 'placing_records' function to combine fields from left and right records.
                        placing_records(left->sch, right->sch, result_schema, left_record, right_record, combined_record, 0);
                        
                        // Append the combined record to the result table.
                        append_record(combined_record, result_table);
                        
                        // Release the combined record to free the memory.
                        release_record(combined_record, result_schema);
                    }
                }
            }
            
            // Don't forget to release the temporary block tables after use.
            release_table(left_block_table);
            release_table(right_block_table);
        }
    }
    
    return result_table;
}


/* Perform a search operation on a table using binary search.

Args:
  r: The record to search within the schema.
  s: The schema to search within.
  offset: The starting offset within the schema.
  op: The comparison operator (a function pointer) used to compare values.
  val: The value to search for.
  
Returns:
  The index of the record that matches the search criteria, or -1 if no records match or on error.
*/
int binary_search(record r , schema_p s, int offset, int (*op) (int, int), int val)
{ 
  page_p page;

  int rec_len = s->len;
  char *sch_name = schema_name(s);

  int max = s->tbl->num_records - 1;
  int rec_per_block = (BLOCK_SIZE - PAGE_HEADER_SIZE) / rec_len;

  int rec_val, index, blk_num, blk_idx, location, min = 0;

  // will run until the given value is found or everything is searched
  while (min <= max) {
    // The current index in the records
    index = (max + min) / 2;

    // block meta data
    blk_num = index / rec_per_block;
    blk_idx = index - rec_per_block * blk_num;

    location = rec_len * blk_idx + PAGE_HEADER_SIZE + offset;

    // Retrieves the page from the given block number in the given file and checks if empty content
    page = get_page (sch_name, blk_num);
    if ( page == NULL ) {
      return 0;  
    }

    // Finds the current value at the given page
    rec_val = page_get_int_at (page, location);
    
    // Changes the limits for the search if not equal, and returns if it is
    if (rec_val == val) {
      page_set_current_pos (page, location);
      get_page_record (page, r, s);
      return 1;
    } else if (rec_val < val) {
      min = index + 1;
    } else {
      max = index - 1;
    }
  }
  // returns 0 if not found
  return 0;
}
