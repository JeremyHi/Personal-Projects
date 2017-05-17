#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>
#include <stdint.h>

int64_t add(int64_t a, int64_t b);

int main(int argc, char** argv) {
   int firstArgument = atoi(argv[1]);
   int secondArgument = atoi(argv[2]);
   printf("%ld\n", add(firstArgument, secondArgument));
}