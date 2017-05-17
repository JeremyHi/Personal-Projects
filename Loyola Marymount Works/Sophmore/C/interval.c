#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
    if (argc != 3) {
        puts("Exactly two command line arguments of type char* needed.");
        return 1;
    } else {
        char *scale_array[] = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
        char *interval_array[] = {"minor second", "major second", "minor third", "major third", "perfect fourth", "tritone", "perfect fifth", "minor sixth", "major sixth", "minor seventh", "major seventh", "perfect octave"};
        int smaller_key = 0;
        int larger_key = 0;
        int j = 1;
        for (int i = 0; i < (sizeof(scale_array) / sizeof(scale_array[0])); i++) {
            if (strcmp(argv[j], scale_array[i]) == 0) {
                if (j == 2)
                {
                    smaller_key = i;
                    break;
                }
                larger_key = i;

                j++;    
                i = -1;
            }
            if(j == argc) {
                break;
            }
        }
        int interval_number = (sizeof(interval_array) / sizeof(char*) - 1) - (larger_key - smaller_key);
        printf("%s to %s is a %s\n", argv[1], argv[2], interval_array[interval_number]);

        return 0;
    }
}