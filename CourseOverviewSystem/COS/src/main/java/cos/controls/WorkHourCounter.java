package cos.controls;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class holds the start and dates of basic task in @LOCALDATETIME format.
 * It also implements various of ways of getting @Long values to time based strings.
 *
 */

public class WorkHourCounter implements Serializable {

    private long serialVersionUID = 1L;

    private long seconds = 0;

    private long minutes = 0;

    private long hours = 0;

    private long days = 0;

    private Long currentCount;

    private int cycle = 0;

    private LocalDateTime endDate;

    private LocalDateTime startDate;


    public WorkHourCounter() {

        this(0L);

    }

    /**
     * Creates a new WorkHourCounter with values that turn into days, hours, minutes and seconds.
     *
     * @param cc Number is treated as seconds.
     */

    public WorkHourCounter(Long cc) {

        currentCount = cc;
        setCurrentCount(cc);
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;

        if (seconds < 0) {
            this.seconds = 59;

            setMinutes(getMinutes() - 1);
        }

    }

    public long getMinutes() {
        return minutes;

    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;

        if (minutes < 0) {
            this.minutes = 59;
            setHours(getHours() - 1);
        }


    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;

        if (hours < 0) {
            this.hours = 23;
            setDays(getDays() - 1);
        }
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    /**
     * Retuns the current count of this object as seconds.
     * @return overall seconds passed.
     */


    public Long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Long currentCount) {
        this.currentCount = currentCount;

        seconds = currentCount % 60;
        currentCount -= currentCount % 60;
        currentCount /= 60;


        minutes = currentCount % 60;
        currentCount -= currentCount % 60;
        currentCount /= 60;

        hours = currentCount % 60;
        currentCount -= currentCount % 60;
        currentCount /= 60;

        days = currentCount % 24;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }


    /**
     * Turns seconds into days, hours, minutes and seconds.
     * @param countD this Long value represents seconds.
     */



    /**
     * Returns the current time of the object in a string format of "HH:MM:SS".
     * @return time as string
     */

    public String timeToString() {

        String time;
        if (hours < 10) {
            time = "0" + hours;
        } else {
            time = hours + "";
        }

        if (minutes < 10) {

            time += ":0" + minutes;
        } else {

            time += ":" + minutes;
        }
        if (seconds < 10) {
            time += ":0" + seconds;

        } else {
            time += ":" + seconds;
        }

        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkHourCounter)) {
            return false;
        }
        WorkHourCounter that = (WorkHourCounter) o;
        return currentCount.equals(that.currentCount) &&
                endDate.equals(that.endDate) &&
                startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentCount, endDate, startDate);
    }
}
