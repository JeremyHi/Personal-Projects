#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

char* rep_str(char* template_sentence, char* old_word, char* new_word){
    char* replaced_code;
    int i, count = 0;
    int newlen = strlen(new_word);
    int oldlen = strlen(old_word);
 
    for (i = 0; i < (sizeof(template_sentence) / sizeof(char*)); i++){
        if (strstr(&template_sentence[i], old_word) == &template_sentence[i]){
            count++;
            i += oldlen - 1;
        }
    }
    replaced_code = (char*)malloc(i + count * (newlen - oldlen));

    if (replaced_code == NULL){
        exit(EXIT_FAILURE);
    }

    i = 0;
    while (*template_sentence){
        if (strstr(template_sentence, old_word) == template_sentence){
            strcpy(&replaced_code[i], new_word);
            i += newlen;
            template_sentence += oldlen;
        } else {
            replaced_code[i++] = *template_sentence++;
        }
    }
    replaced_code[i] = '\0';
    return replaced_code;
}

char* madlib_by_numbers(char* temp, int word_count, char* words[]){
    char* numbers[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    int tempSize = strlen(temp);

    temp = strdup(temp);
    if (temp == NULL) {
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < tempSize; i++) {
        if (isdigit(temp[i])) {
            for (int i = word_count, j = 0; j < i; j++) {
                char* replace_string = rep_str(temp, numbers[j], words[j]);
                temp = replace_string;
                free(temp);
            }
        }
    }
    return temp;
}
