#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* madlib(char* template, char* adjective, char* noun, char* verb) {
    if (strstr(template, "%s") != NULL) {
        char* str = (char*)malloc(sizeof(char) * (strlen(template) + strlen(adjective) + strlen(noun) + strlen(verb)));
        sprintf(str, template, adjective, noun, verb);
        puts(str);
        return str;
    }
    return "";
}