#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>
#include <stdint.h>

int64_t add(int64_t a, int64_t b);
int64_t subtraction(int64_t a, int64_t b);
int64_t gcd(int64_t a, int64_t b);


int main(int argc, char** argv) {
   int firstArgument = atoi(argv[1]);
   int secondArgument = atoi(argv[2]);
   printf("%s %ld\n", "Adding your numbers yields:", add(firstArgument, secondArgument));
   printf("%s %ld\n", "subtracting your numbers yields:", subtraction(firstArgument, secondArgument));
   printf("%s %ld\n", "GCD of your numbers yields:", gcd(firstArgument, secondArgument));
}