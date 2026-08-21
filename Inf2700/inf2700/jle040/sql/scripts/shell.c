#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sqlite3.h>       /*  IMPORTANT!!! READ library how to*/

#define MAX_CHAR 255

//  Name Of The Folder Holding Database Files
SQLITE_EXTERN char *sqlite3_data_directory;

//  Number Of Columns In A Result Set
/* A SELECT statement will always have a positive sqlite3_column_count() but depending on the WHERE clause constraints and the table content, it might return no rows. */
int sqlite3_column_count(sqlite3_stmt *pStmt);

//  Compiling An SQL Statement
int sqlite3_prepare(
  sqlite3 *db,            /* Database handle */
  const char *zSql,       /* SQL statement, UTF-8 encoded */
  int nByte,              /* Maximum length of zSql in bytes. */
  sqlite3_stmt **ppStmt,  /* OUT: Statement handle */
  const char **pzTail     /* OUT: Pointer to unused portion of zSql */
);

//  Evaluate An SQL Statement
int sqlite3_step(sqlite3_stmt*);

//  Result Codes

    #define SQLITE_OK           0   /* Successful result */
    /* beginning-of-error-codes */
    #define SQLITE_ERROR        1   /* Generic error */
    #define SQLITE_INTERNAL     2   /* Internal logic error in SQLite */
    #define SQLITE_PERM         3   /* Access permission denied */
    #define SQLITE_ABORT        4   /* Callback routine requested an abort */
    #define SQLITE_BUSY         5   /* The database file is locked */
    #define SQLITE_LOCKED       6   /* A table in the database is locked */
    #define SQLITE_NOMEM        7   /* A malloc() failed */
    #define SQLITE_READONLY     8   /* Attempt to write a readonly database */
    #define SQLITE_INTERRUPT    9   /* Operation terminated by sqlite3_interrupt()*/
    #define SQLITE_IOERR       10   /* Some kind of disk I/O error occurred */
    #define SQLITE_CORRUPT     11   /* The database disk image is malformed */
    #define SQLITE_NOTFOUND    12   /* Unknown opcode in sqlite3_file_control() */
    #define SQLITE_FULL        13   /* Insertion failed because database is full */
    #define SQLITE_CANTOPEN    14   /* Unable to open the database file */
    #define SQLITE_PROTOCOL    15   /* Database lock protocol error */
    #define SQLITE_EMPTY       16   /* Internal use only */
    #define SQLITE_SCHEMA      17   /* The database schema changed */
    #define SQLITE_TOOBIG      18   /* String or BLOB exceeds size limit */
    #define SQLITE_CONSTRAINT  19   /* Abort due to constraint violation */
    #define SQLITE_MISMATCH    20   /* Data type mismatch */
    #define SQLITE_MISUSE      21   /* Library used incorrectly */
    #define SQLITE_NOLFS       22   /* Uses OS features not supported on host */
    #define SQLITE_AUTH        23   /* Authorization denied */
    #define SQLITE_FORMAT      24   /* Not used */
    #define SQLITE_RANGE       25   /* 2nd parameter to sqlite3_bind out of range */
    #define SQLITE_NOTADB      26   /* File opened that is not a database file */
    #define SQLITE_NOTICE      27   /* Notifications from sqlite3_log() */
    #define SQLITE_WARNING     28   /* Warnings from sqlite3_log() */
    #define SQLITE_ROW         100  /* sqlite3_step() has another row ready */
    #define SQLITE_DONE        101  /* sqlite3_step() has finished executing */
    /* end-of-error-codes */


//  Source Of Data In A Query Result
const char *sqlite3_column_database_name(sqlite3_stmt*,int);
const char *sqlite3_column_table_name(sqlite3_stmt*,int);
const char *sqlite3_column_origin_name(sqlite3_stmt*,int);

//  Column Names In A Result Set
const char *sqlite3_column_name(sqlite3_stmt*, int N);

//  Closing A Database Connection
int sqlite3_close(sqlite3*);

//  Error Codes And Messages
int sqlite3_errcode(sqlite3 *db);
const char *sqlite3_errmsg(sqlite3*);
const char *sqlite3_errstr(int);

//  Retrieving Statement SQL
const char *sqlite3_sql(sqlite3_stmt *pStmt);

//  Opening A New Database Connection
int sqlite3_open(
  const char *filename,   /* Database filename (UTF-8) */
  sqlite3 **ppDb          /* OUT: SQLite db handle */
);

typedef struct input_buffer {
  char *updt;
} input_buf;

char *getInput()
{
  struct input_buffer *input = malloc(sizeof(input));
  printf(">> ");

  //char input[MAX_CHAR];// = malloc(sizeof(char) * MAX_CHAR);
  //char nl_update[MAX_CHAR];// = malloc(sizeof(char) * MAX_CHAR);

  while (fgets(input->updt, MAX_CHAR, stdin) != ';') {

    //memmove(buffer->updt, input, MAX_CHAR);
    //int len = strlen(input);

    // printf("\n\narray: %s\n\n", input);
    // printf("\n\narray copy: %s\n\n", buffer->updt);

    // if(input[len-1] == '\n') {
    //   input[len-1] = 0;
    // }

    /* if (strchr(input->updt, ';')) {
      return input->updt;
    } */
  }
  return input->updt;
}

int parse_schema(char *str)
{
    
}



