#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

#define MAX_COUNT 100

int main(void) {
	pid_t pid;
	pid = fork();

    if (pid == 0) {
    	int i;

	    for (i = 1; i <= MAX_COUNT; i = i + 10) {
	    	sleep(1);
	    	printf("	CHILD: pid is %d and my parent's id is %d. VALUE is %d\n", getpid(), getppid(), i);
	    }
	    printf("	*** Child process is done ***\n");
	} else {
    	int i;

    	// sleep(1);	
    	for (i = 1; i <= MAX_COUNT; i = i + 10) {
    		//sleep(1);
        	printf("PARENT: My pid is %d and my parent's id is %d. VALUE is %d\n", getpid(), pid, i);
    	}
    	printf("*** Parent is done ***\n");
    }
}