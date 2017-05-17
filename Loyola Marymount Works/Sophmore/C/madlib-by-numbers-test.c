#include <assert.h>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "madlib_by_numbers.c"

char* madlib_by_numbers(char* temp, int word_count, char* words[]);

int main() {
  char* temp1 = "The 1 0 likes to 2 in the moonlight.";
  char* words[] = {"git", "brilliant", "swim"};
  char* result = "The brilliant git likes to swim in the moonlight.";
  int stringLength = strlen(result);

  char* test;
  test = madlib_by_numbers(temp1, 3, words);
  assert(strncmp(test, result, stringLength) == 0);
  free(test);

  return 0;
}