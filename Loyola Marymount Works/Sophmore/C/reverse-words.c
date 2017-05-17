#include <stdio.h>
#include <string.h>

void reverse(char *begin, char *end);
 
int clamp(int value, int minimum, int maximum) {
  return (value < minimum) ? minimum : ((value > maximum) ? maximum : value);
}

void reverse_range_in_place(char* string, int start_index, int end_index) {
  int string_length = strlen(string);
  start_index = clamp(start_index, 0, string_length - 1);
  end_index = clamp(end_index, 0, string_length - 1);

  if (end_index < start_index) {
    return;
  }

  int opposite_index = end_index;
  for (int i = start_index; i <= (start_index + end_index) / 2; i++) {
    char swap = string[i];
    string[i] = string[opposite_index];
    string[opposite_index] = swap;
    opposite_index--;
  }
}

void reverseWords(char *s) {
  char *word_begin = NULL;
  char *temp = s;
 
  while(*temp) {
    if (( word_begin == NULL ) && (*temp != ' ') ) {
      word_begin=temp;
    }
    if(word_begin && ((*(temp+1) == ' ') || (*(temp+1) == '\0'))) {
      reverse(word_begin, temp);
      word_begin = NULL;
    }
    temp++;
  }

  reverse(s, temp-1);
}
 
void reverse(char *begin, char *end) {
  char temp;
  while (begin < end) {
    temp = *begin;
    *begin++ = *end;
    *end-- = temp;
  }
}
