#include <stdio.h>
#include "reverse-words.c"

int main() {
  char s[] = "i like this program very much";
  char *temp = s;
  char *temp2 = s;
  printf("You want to reverse this: %s\n", temp);
  reverseWords(temp);
  printf("Reversed word order: %s\n", temp);
  reverse_range_in_place(temp2, 0, 28);
  printf("Reversed words in place: %s\n", temp2);
  return 0;
}
