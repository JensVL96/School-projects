#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sqlite3.h>
//  ../inf2700_orders.sqlite3
//  SELECT orderNumber, orderDate FROM Orders WHERE status = 'Cancelled';

#define MAX_CHAR 4096
int global_1st_inst = 0;

typedef struct database_t {
  char *buffer;
  char *path;
} database;

int parse_input();
int parse_cmd();

static int callback(void *NotUsed, int argc, char **argv, char **azColName)
{
    NotUsed = 0;
    if (global_1st_inst == 0) {
        global_1st_inst = 1;
    } else {
        printf(",\n");
    }
    for (int i = 0; i < argc; i++) {
        if (i % argc == 0) {
            printf("{\"%s\": %s, ", azColName[i], argv[i] ? argv[i] : "NULL");
        } else {
            printf("\"%s\": %s}", azColName[i], argv[i] ? argv[i] : "NULL");
        }
    }
    return 0;
}

int open_db(char *input) 
{
    sqlite3 *db;
    if (sqlite3_open(input, &db) != SQLITE_OK) {
        fprintf(stderr, "Can't' open database: %s\n", sqlite3_errmsg(db));
        sqlite3_close(db);
        return SQLITE_CANTOPEN;
    }
    return 1;
}

int cmd_end(char *string, database *db)
{
    if (strchr(string, ';')) {
        return 1;
    }
    return 0;
}

int is_cmd(char *string)
{
    if (string[0] == '.') {
        return 1;
    }
    return 0;
}

int main()
{
    char sql[MAX_CHAR];
    struct database_t *database = malloc(sizeof(database));
    database->buffer = malloc(sizeof(char) * MAX_CHAR);

    printf("\nWelcome to sql-json\n");
    printf("Enter \".help\" for instructions\n");
    printf("Enter SQL statements terminated with a \";\"\n");
    printf("Enter \".quit\" to exit\n");

    while(1) {
        CMD_LOOP: printf(">> ");
        fgets(sql, sizeof(sql), stdin);
        
        if (is_cmd(sql)) {
            char *command = sql;
            command[strlen(command) - 1] = 0;

            parse_cmd(sql, database);
        }
        else if (cmd_end(sql, database)) {
            strcat(database->buffer, sql);
            parse_cmd(sql, database);
        } else {
            strcat(database->buffer, sql);
        }
        goto CMD_LOOP;
    }
    return 0;
}

int parse_input(database *db_buf) {
    sqlite3 *db;
    char *err_msg = 0;

    int result = sqlite3_open(db_buf->path, &db);

    printf("[");
    result = sqlite3_exec(db, db_buf->buffer, callback, 0, &err_msg);
    printf("]\n");

    if( result != SQLITE_OK ){
        fprintf(stderr, "Failed to select data\n");
        fprintf(stderr, "SQL error: %s\n", err_msg);
        sqlite3_free(err_msg);
        sqlite3_close(db);
        return 1;
    }
    return 0;
}

int parse_cmd(char input[], database *db) {
    char *path = malloc(sizeof(char) * MAX_CHAR);

    if (strcmp(input, ".help") == 0) {
        printf("\n----\tCOMMANDS\t----\n");
        printf("\n.quit\tQuits the program\n");
        printf(".exit\tExits the program\n");
        printf(".help\tOpens the list of commands\n");
        printf(".open\tOpens the database at the user specified path\n\n");
    } 
    else if (strcmp(input, ".quit") == 0) {
        exit(EXIT_SUCCESS);
    }
    else if (strcmp(input, ".exit") == 0) {
        exit(EXIT_SUCCESS);
    }
    else if (strcmp(input, ".open") == 0) {
        printf("Path name here :\t");
        fgets(path, MAX_CHAR, stdin);

        char *cut = path;
        cut[strlen(cut) - 1] = 0;
        db->path = path;
    } else {
        parse_input(db);
    }
    return 0;
}
