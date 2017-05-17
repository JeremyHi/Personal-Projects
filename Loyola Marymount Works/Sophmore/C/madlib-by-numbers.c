#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

char* rep_str(const char* s, const char* old, const char* new1){
    char* ret;
    int i, count = 0;
    int newlen = strlen(new1);
    int oldlen = strlen(old);
 
    for (i = 0; i < (sizeof(s) / sizeof(char*)); i++){
        if (strstr(&s[i], old) == &s[i]){
            count++;
            i += oldlen - 1;
        }
    }
    ret = (char*)malloc(i + count * (newlen - oldlen));

    if (ret == NULL){
        exit(EXIT_FAILURE);
    }

    i = 0;
    while (*s){
        if (strstr(s, old) == s){
            strcpy(&ret[i], new1);
            i += newlen;
            s += oldlen;
        } else {
            ret[i++] = *s++;
        }
    }
    ret[i] = '\0';

    return ret;
}

char* madlib_by_numbers(char* temp, int word_count, char* words[]){
    char* numbers[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    int tempSize = strlen(temp);

    for (int i = 0; i < tempSize; i++){
        if (isdigit(temp[i])){
            for (int j = 0; j < word_count; j++){
                // temp[i] - '0';
                temp = rep_str(temp, numbers[j], words[j]);
            }
        }
    }
    return temp;
}
