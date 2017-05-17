#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>
#include <stdint.h>

int64_t gcd(int64_t x, int64_t y);

int main(int argc, char** argv) {
	if (argc != 3)
	{
		printf("%s\n", "Only 2 arguments needed.");
		exit(0);
	}
   int firstArgument = atoi(argv[1]);
   int secondArgument = atoi(argv[2]);
   printf("%ld\n", gcd(firstArgument, secondArgument));
}