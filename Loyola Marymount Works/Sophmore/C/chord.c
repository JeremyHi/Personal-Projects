#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        puts("Exactly one command line argument of type char* needed.");
        return 1;
    } else {
        char *scale_array[] = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
        for (int i = 0; i < (sizeof(scale_array) / sizeof(scale_array[0])); i++) {
            if (strcmp(argv[1], scale_array[i]) == 0) {
                //Major Chord
                printf("%s: %s %s %s\n", argv[1], argv[1], scale_array[(i+4)%12], scale_array[(i+7)%12]);

                //minor Chord
                printf("%sm: %s %s %s\n", argv[1], argv[1], scale_array[(i+3)%12], scale_array[(i+7)%12]);

                //Dominant 7th Chord
                printf("%s7: %s %s %s %s\n", argv[1], argv[1], scale_array[(i+4)%12], scale_array[(i+7)%12], scale_array[(i+10)%12]);

                //diminished 7th Chord
                printf("%sdim7: %s %s %s %s\n", argv[1], argv[1], scale_array[(i+3)%12], scale_array[(i+6)%12], scale_array[(i+9)%12]);
                break;
            }
        }
        return 0;
    }
}