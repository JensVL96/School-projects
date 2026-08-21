#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sqlite3.h>

#define MAX_CHAR 4096
int global_1st_inst = 0;

typedef struct database_t {
  char *buffer;
  char *path;
} database;

int parse_input();
int parse_cmd();

/*  
 * Prints the database data into a JSON format 
 */
static int callback(void *NotUsed, int argc, char **argv, char **azColName)
{
    NotUsed = 0;
    if (global_1st_inst == 0) {
        global_1st_inst = 1;
    } else {
        printf(",\n");
    }

    //  Prints all of the arguments in the SQL query
    for (int i = 0; i < argc; i++) {
        if (i % argc == 0) {
            printf("{\"%s\": %s, ", azColName[i], argv[i] ? argv[i] : "NULL");
        } else if (i == argc - 1) {
            printf("\"%s\": %s}", azColName[i], argv[i] ? argv[i] : "NULL");
        } else {
            printf("\"%s\": %s, ", azColName[i], argv[i] ? argv[i] : "NULL");
        }
    }
    return 0;
}

/*
 * Checks if the input query is over 
 */
int cmd_end(char *string, database *db)
{
    if (strchr(string, ';')) {
        return 1;
    }
    return 0;
}

/*
 * Checks if the input query is a command 
 */
int is_cmd(char *string)
{
    if (string[0] == '.') {
        return 1;
    }
    return 0;
}

int main()
{
    //  memory allocation and creating arrays
    char sql[MAX_CHAR];
    struct database_t *database = malloc(sizeof(database));
    database->buffer = malloc(sizeof(char) * MAX_CHAR);

    //  The welcome message when opening the program
    printf("\nWelcome to sql-json\n");
    printf("Enter \".help\" for instructions\n");
    printf("Enter SQL statements terminated with a \";\"\n");
    printf("Enter \".quit\" to exit\n");

    //  The input loop interpretting the input from the terminal
    while(1) {
        CMD_LOOP: printf(">> ");
        fgets(sql, sizeof(sql), stdin);
        
        // Seperates commands and queries handling them differently
        if (is_cmd(sql)) {
            char *command = sql;
            command[strlen(command) - 1] = 0;

            parse_cmd(sql, database);
        }
        // Continues to add the input to a structure query until all of the input has been inserted
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

/*
 * Parses the input knowing it's a query, so opens and executes the database given the path and query
 */
int parse_input(database *db_buf) {
    sqlite3 *db;
    char *err_msg = 0;

    int result = sqlite3_open(db_buf->path, &db);

    //  Prints the results in a predetermined JSON format
    printf("[");
    result = sqlite3_exec(db, db_buf->buffer, callback, 0, &err_msg);
    printf("]\n");

    //  Checks if an error occured when executing the database
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

    // Checks though a list of commands, defaults to parse the input when it's a query
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
        //  Gives the user the chance to specify which database to use
        printf("Path name here :\t");
        fgets(path, MAX_CHAR, stdin);

        //  removes the newline character at the end of the input
        char *cut = path;
        cut[strlen(cut) - 1] = 0;
        db->path = path;
    } else {
        parse_input(db);
    }
    return 0;
}
