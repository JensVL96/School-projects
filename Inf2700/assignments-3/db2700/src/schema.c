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


#define MAX_SCHEMA_NAME_LENGTH 30

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

/**
 * Merge records from left and right schemas into a destination schema.
 * 
 * Args:
 *   left: The left schema to merge.
 *   right: The right schema to merge.
 *   dest: The destination schema to store the merged records.
 *   left_rec: The left record to merge.
 *   right_rec: The right record to merge.
 *   dest_rec: The destination record to store the merged result.
 */
void merging_records(schema_p left, schema_p right, schema_p dest, record left_rec, record right_rec, record dest_rec)
{
  field_desc_p tmp_l, tmp_r, tmp_d;

  //  Create temporary schemas to avoid pointer issues
  schema_p tmp_left = copy_schema(left, "tmp_left");
  schema_p tmp_right = copy_schema(right, "tmp_right");
  schema_p tmp_dest = copy_schema(dest, "tmp_dest");

  //  Define the first field pointer in both schemas
  tmp_l = tmp_left->first;
  tmp_r = tmp_right->first;

  // Initializing the counters
  int index = 0;
  int i = 0;
  int j = 0;

  // Runs through the joined schema and finds the corresponding field in either left or right
  for(tmp_d = tmp_dest->first; tmp_d != NULL; tmp_r = tmp_r->next, tmp_l = tmp_l->next, i++, j++) {
    // Checks if field is in left schema, adds it to the result record if so
    if(!strcmp(tmp_d->name, tmp_l->name)) {
      memcpy(dest_rec[index], left_rec[i], tmp_l->len);
      index++;

      // Resets the counter/ position and moves to the next result field
      tmp_l = tmp_left->first;
      tmp_d = tmp_d->next;
      i = 0;
    }
    // Checks if field is in right schema, adds it to the result record if so
    if(!strcmp(tmp_d->name, tmp_r->name)) {
      memcpy(dest_rec[index], right_rec[j], tmp_r->len);
      index++;

      // Resets the counter/ position and moves to the next result field
      tmp_r = tmp_right->first;
      tmp_d = tmp_d->next;
      j = 0;
    }
  }

  // removes temporary shcemas after use
  remove_schema(tmp_left);
  remove_schema(tmp_right);
  remove_schema(tmp_dest);
}

/**
 * Find records in schemas with block-based search and store the result in a new schema.
 * 
 * Args:
 *   left: The left schema to search.
 *   right: The right schema to search.
 *   l_offset: The offset in the left schema.
 *   r_offset: The offset in the right schema.
 *   result: The schema to store the search results.
 * 
 * Returns:
 *   The schema containing the found records.
 */
schema_p finding_records_with_block(schema_p left, schema_p right, int l_offset, int r_offset, schema_p result) 
{
  page_p left_pg, right_pg;

  printf("block test 1");

  // Create the left record and page given the record
  record left_rec = new_record(left); 
  set_tbl_position(left->tbl, TBL_BEG);
  left_pg = get_page_for_next_record(left);

  // Create the right record and page given the record
  record right_rec = new_record(right);
  set_tbl_position(right->tbl, TBL_BEG);
  right_pg = get_page_for_next_record(right);

  //  Initial integer definitions
  int left_pos, left_rec_val, right_pos, right_rec_val;
  
  //  Loops through the blocks on the left side, retrieves the page given the block number
  for(int i = 0; i < file_num_blocks(left->name); i++) {
    left_pg = get_page(left->name, i);
    // printf("left block nr: %d\t", page_block_nr(left_pg));

    //  Loops through the blocks on the right side, retrieves the page given the block number
    for(int j = 0; j < file_num_blocks(right->name); j++) {
      right_pg = get_page(right->name, j);
      // printf("right block nr: %d\n", page_block_nr(right_pg));

      // Sets initial left position
      left_pos = page_current_pos(left_pg);

      //  Adds record lenght onto left position until end of page is reached
      for ( ; !eop(left_pg); left_pos += left->len) {
        // printf("left pos: %d\t", left_pos);

        //  Retrieves value at current left position
        left_rec_val = page_get_int_at(left_pg, left_pos);

        //  Sets the position and retrieves current record for merging
        page_set_current_pos(left_pg, left_pos);
        get_page_record(left_pg, left_rec, left);

        // Sets initial left position
        right_pos = page_current_pos (right_pg);

        //  Adds record lenght onto right position until end of page is reached
        for ( ; !eop(right_pg); right_pos += right->len) {
          // printf("right pos: %d\n",  right_pos);

          //  Retrieves value at current right position
          right_rec_val = page_get_int_at(right_pg, right_pos);
          page_set_current_pos(right_pg, right_pos);

          // printf("left value: %d\tright value: %d\n", left_rec_val, right_rec_val);
          //  compares right retreived value with left retrieved value
          if (right_rec_val == left_rec_val) {
            // creates a record for right schema, retrieves the data, merges with left and appends to result
            record temp_rec = new_record(result);
            // printf("\n\n\nadding record to result\n\n\n");
            get_page_record(right_pg, right_rec, right);
            merging_records(left, right, result, left_rec, right_rec, temp_rec);
            append_record(temp_rec, result);

            break;
          } else {
            // Updates the position if the value isn't the same
            page_set_current_pos(right_pg, right_pos + right->len);
          }
        }
      }
      //  Unpins the right page from the block
      unpin(right_pg);
      // printf("at line: %d\n", __LINE__);
    }
    //  Unpins the left page from the block
    unpin(left_pg);
    // printf("at line: %d\n", __LINE__);
  }
  return result;
}

/**
 * Find records in schemas with tuple-based search and store the result in a new schema.
 * 
 * Args:
 *   left: The left schema to search.
 *   right: The right schema to search.
 *   l_offset: The offset in the left schema.
 *   r_offset: The offset in the right schema.
 *   result: The schema to store the search results.
 * 
 * Returns:
 *   The schema containing the found records.
 */
schema_p finding_records_with_tuple(schema_p left, schema_p right, int l_offset, int r_offset, schema_p result) 
{
  page_p left_pg, right_pg;

  // Create the left record and page given the record
  record left_rec = new_record(left); 
  set_tbl_position(left->tbl, TBL_BEG);
  left_pg = get_page_for_next_record(left);

  // Create the right record and page given the record
  record right_rec = new_record(right);
  set_tbl_position(right->tbl, TBL_BEG);
  right_pg = get_page_for_next_record(right);

  // Initial integer definitions
  int left_pos, left_rec_val, right_pos, right_rec_val;

  // Loops through the pages on the left until the page is empty
  for (; left_pg != NULL; left_pg = get_page_for_next_record(left)) {
    if (eop(left_pg)) continue;
    // Retrieves the position and position value given the page
    left_pos = page_current_pos(left_pg);
    left_rec_val = page_get_int_at(left_pg, left_pos + l_offset);

    // Sets the position and retrieves the current record for merging
    page_set_current_pos(left_pg, left_pos);
    get_page_record(left_pg, left_rec, left);

    // Loops through the pages on the left until the page is empty
    for (; right_pg != NULL; right_pg = get_page_for_next_record(right)) {
      if (eop(right_pg)) continue;
      
      // Retrieves the position and position value given the page
      right_pos = page_current_pos(right_pg);
      right_rec_val = page_get_int_at(right_pg, right_pos + r_offset);
      page_set_current_pos(right_pg, right_pos);

      // Compares the right retrieved value with the left retrieved value
      if (right_rec_val == left_rec_val) {
        // Creates a record for the right schema, retrieves the data, merges with the left, and appends to the result
        record temp_rec = new_record(result);
        get_page_record(right_pg, right_rec, right);
        merging_records(left, right, result, left_rec, right_rec, temp_rec);
        append_record(temp_rec, result);
        break;
      } else {
        // Updates the position if the value isn't the same
        page_set_current_pos(right_pg, right_pos + right->len);
      }
    }
  }
  // Return the result schema
  return result;
}

typedef struct {
  field_desc_p field;
} CommonField;

/**
 * Find a common field between two schemas and add it to a joined schema.
 * 
 * Args:
 *   left_schema: The schema on the left.
 *   right_schema: The schema on the right.
 *   joined: The joined schema to add the field.
 * 
 * Returns:
 *   A structure containing the common field from both schemas.
 */
CommonField find_common_field(schema_p left_schema, schema_p right_schema, schema_p joined) {
  CommonField result = { NULL, NULL };
  for (field_desc_p left_field = left_schema->first; left_field != NULL; left_field = left_field->next) {
    for (field_desc_p right_field = right_schema->first; right_field != NULL; right_field = right_field->next) {
      if (strcmp(left_field->name, right_field->name) == 0) {
        result.field = dup_field(left_field);

        add_field(joined, dup_field(right_field));
        return result;
      }
    }
  }
  return result; // No common field found
}

/**
 * Add fields from left and right schemas to a joined schema while excluding the common field.
 * 
 * Args:
 *   left: The left schema to add fields from.
 *   right: The right schema to add fields from.
 *   joined: The joined schema to store the added fields.
 *   common: The common field to exclude.
 */
void add_fields(schema_p left, schema_p right, schema_p joined, field_desc_p common) {
  for (field_desc_p left_field = left->first; left_field != NULL; left_field = left_field->next) {
    if (strcmp(left_field->name,common->name) != 0) {
      add_field(joined, dup_field(left_field));
      }
  }

  for (field_desc_p right_field = right->first; right_field != NULL; right_field = right_field->next) {
    if (strcmp(right_field->name,common->name) != 0) {
      add_field(joined, dup_field(right_field));
      }
  }
}

/**
 * Perform a natural join between two tables based on a common field.
 * 
 * Args:
 *   left: The left table to join.
 *   right: The right table to join.
 *   key: The key to determine the join method (0 for tuple-based, 1 for block-based).
 * 
 * Returns:
 *   The result of the natural join as a new table schema.
 */
tbl_p table_natural_join (tbl_p left, tbl_p right, int key)
{
  //  Creates a new schema and gives it a name
  schema_p join_sch = new_schema("natural_join_result");

  // Find the common field and add all unique fields to the new schema
  CommonField common = find_common_field(left->sch, right->sch, join_sch);
  add_fields(left->sch, right->sch, join_sch, common.field);

  // Handle the case of no common field
  if (common.field == NULL) {
      printf("No common field found for natural join.\n");
      return NULL;
  }

  if (is_int_field(common.field)) {
      // Determine the search method based on the 'key'.
      if (key == 0) {
          join_sch = finding_records_with_tuple(left->sch, right->sch, common.field->offset, common.field->offset, join_sch);
      } else {
          join_sch = finding_records_with_block(left->sch, right->sch, common.field->offset, common.field->offset, join_sch);
      }
  }
  return join_sch->tbl;  
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
