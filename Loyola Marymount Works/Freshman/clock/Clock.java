package clock;

/**
 * Represents a standard twelve-hour analog clock, keeping track of the hours,
 * minutes, and seconds of the hands. Clock also has a grain. The grain is the
 * number of seconds the clock moves each time the tick() method is called.
 */
public class Clock {

    private double grain;
    private int hours;
    private int minutes;
    private double seconds;
    private double timePassed;

    public Clock() {
        this(12);
    }

    public Clock(int hours) {
        this(hours,0);
    }

    public Clock(int hours, int minutes) {
        this(hours,minutes,0.0);
    
    }

    public Clock(int hours, int minutes, double seconds) {
        this(hours,minutes,seconds,1.0);
    }

    public Clock(int hours, int minutes, double seconds, double grain) {   
        if ((hours > 12 || hours < 1) || (minutes > 60 || minutes < 0) || (seconds > 60 || seconds < 0)) {
            throw new IllegalArgumentException();
        }
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.grain = grain;       
    }

    // TODO: Add missing methods and constructors.

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getSeconds() {
        return seconds;
    }

    public double getGrain() {
        return grain;
    }

    public double getSecondsPassed() {
        return timePassed;
    }

    public void tick() {
        this.seconds += grain;
        this.timePassed += this.grain;
        if (seconds >= 60.0) {
            minutes += seconds/60;
            seconds = seconds%60;
        } 
        if (minutes >= 60) {
            hours += minutes/60;
            minutes = minutes%60;
        }
        if (hours > 12) {
            hours = hours%12;
        }

    }

    @Override
    public String toString() {
        String lessHours = "" + hours;
        String lessMinutes = "" + minutes;
        String lessSeconds = "" + seconds;
        if (hours < 10) {
            lessHours = "0" + hours;
        }
        if (minutes < 10) { 
            lessMinutes = "0" + minutes;
        } 
        if (seconds < 10) {
            lessSeconds = "0" + seconds;
        }
        return lessHours + ":" + lessMinutes + ":" + lessSeconds;
    }
}