package Controls;

public class WorkHourCounter {

    private long seconds = 0;

    private long minutes = 0;

    private long hours = 0;

    private long days = 0;


    public WorkHourCounter() {



    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public long getDays() {
        return days;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public void counter(long countD){

        seconds=countD%60;
        countD-=countD%60;
        countD/=60;


        minutes=countD%60*100;
        countD-=countD%60;
        countD/=60;

        hours=countD%60*10000;
        countD-=countD%60;
        countD/=60;

        days= countD%60*1000000;



    }

    public long counter(Long countD){


        seconds=countD%60;
        countD-=countD%60;
        countD/=60;


        minutes=countD%60*100;
        countD-=countD%60;
        countD/=60;

        hours=countD%60*10000;
        countD-=countD%60;
        countD/=60;

        days= countD%60*1000000;


        return seconds+minutes+hours;
    }



}
