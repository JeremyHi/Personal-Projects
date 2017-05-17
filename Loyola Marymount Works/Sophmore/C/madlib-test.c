#include <string.h>
#include <assert.h>
#include "madlib.c"

int main() {
    
    char* real = "The large dog flies at midnight.";
    char* sentence = "The %s %s %s at midnight.\n";
    char* adjective = "large";
    char* noun = "dog";
    char* verb = "flies";
    char* madLib = madlib(sentence, adjective, noun, verb);
    assert(strcmp(madLib, real) == 0);

    return(0);     
}














