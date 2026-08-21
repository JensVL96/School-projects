#include "test_data_gen.h"
#include "testschema.h"
#include "pmsg.h"
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

void handle_test_options(int argc, char* argv[]) {
  int c;
  char new_sys_dir[512];

  new_sys_dir[0] = '\0';
  msglevel = INFO;

  while ((c = getopt(argc, argv, "hm:d:")) != -1)
    switch (c) {
    case 'h':
      printf("Usage: runtest [switches]\n");
      printf("\t-h           help, print this message\n");
      printf("\t-m [fewid]   msg level [fatal,error,warn,info,debug]\n");
      printf("\t-d db_dir  default to ./tests/testdb\n");
      exit(0);
    case 'm':
      switch (optarg[0]) {
      case 'f': msglevel = FATAL; break;
      case 'e': msglevel = ERROR; break;
      case 'w': msglevel = WARN; break;
      case 'i': msglevel = INFO; break;
      case 'd': msglevel = DEBUG; break;
      }
      break;
    case 'd':
      strcpy(new_sys_dir, optarg);
      break;
    case '?':
      if (optopt == 'm' || optopt == 'd' || optopt == 'c')
        printf("Option -%c requires an argument.\n", optopt);
      else if (isprint(optopt))
        printf("Unknown option `-%c'.\n", optopt);
      else
        printf("Unknown option character `\\x%x'.\n", optopt);
      abort();
    default:
      abort();
    }

  if (new_sys_dir[0] == '\0')
    strcpy(new_sys_dir, "./tests/testdb");

  if (!set_system_dir(new_sys_dir)) {
    put_msg(ERROR, "cannot set system dir at %s\n", new_sys_dir);
    exit(EXIT_FAILURE);
  }
}

/*  Retrieves the given table and performs both search methods to find the target
*/
int profiling (char *table_name, char *column, int target_num, int search_type) {
  open_db();
  tbl_p table = get_table(table_name);
  table_search(table, column, "=", target_num, search_type);

  // Prints the profiler info to the terminal
  if (search_type == 0) {
    put_msg(INFO, "\nbinary search for %d:", target_num);
  } else {
    put_msg(INFO, "\nlinear search for %d:", target_num);
  }
  put_pager_profiler_info(INFO);
  close_db();
  return 0;
}

/*  Fills the table with records
*/
int fill_table(char *table_name, int n) {
  record rec;
  schema_p sch = get_schema(table_name);

  for (int i = 0; i < n; i++) {
    rec = new_record(sch);
    fill_record(rec, sch, i, i + n);
    append_record(rec, sch);
  }
  return 0;
}


int main(int argc, char* argv[]) {
  handle_test_options(argc, argv);
  prepare_test_data_gen();
  /*
  test_page_write("testpage");
  test_page_read("testpage");

  test_page_write_with_offset("testpage_w_offset");
  test_page_read_with_offset("testpage_w_offset");
  */

 // schema meta data
  char *id = "id";
  char *value = "value";
  char *columns[] = {id, value};
  int attr_types[] = {INT_TYPE, INT_TYPE};

  int record_num = 10000;

  // creates and fills a table
  open_db();
  create_test_schema("test_first", 2, columns, attr_types);
  fill_table("test_first", record_num);

  create_test_schema("test_middle", 2, columns, attr_types);
  fill_table("test_middle", record_num);

  create_test_schema("test_miss", 2, columns, attr_types);
  fill_table("test_miss", record_num);

  create_test_schema("test_rand", 2, columns, attr_types);
  fill_table("test_rand", record_num);
  close_db();

  // Sends profiling info to the terminal for both search methods
  profiling("test_first", id, 1, 0);   // binary search first
  profiling("test_first", id, 1, 1);   // linear search first     

  profiling("test_middle", id, record_num / 2, 0);   // binary search middle
  profiling("test_middle", id, record_num / 2, 1);   // linear search middle

  profiling("test_miss", id, record_num + 21, 0);   // binary search missing
  profiling("test_miss", id, record_num + 21, 1);   // linear search missing

  profiling("test_rand", id, record_num / 100 * 67, 0);   // binary search random
  profiling("test_rand", id, record_num / 100 * 67, 1);   // linear search random


  // char my_tbl[] = "Me";
  // test_tbl_write(my_tbl);
  // test_tbl_read(my_tbl);

  // test_tbl_natural_join(my_tbl, "You");

  return (0);
}
