package Controls;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorkHourCounter implements Serializable {

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

    public WorkHourCounter(Long cc){

        currentCount = cc;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;

        if(seconds < 0){
            this.seconds = 59;

            setMinutes(getMinutes()-1);
        }

    }

    public long getMinutes() {
        return minutes;

    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;

        if(minutes < 0){
            this.minutes = 59;
            setHours(getHours()-1);
        }


    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;

        if(hours < 0){
            this.hours = 23;
            setDays(getDays()-1);
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

    public long getFullSeconds(){

        return this.seconds+this.minutes*60+this.hours*3600;
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

    public void counter(long countD){

        seconds=countD%60;
        countD-=countD%60;
        countD/=60;


        minutes=countD%60;
        countD-=countD%60;
        countD/=60;

        hours=countD%60;
        countD-=countD%60;
        countD/=60;

        days= countD%24;



    }

    public String timeToString(){

        String time;

        if (seconds < 10){
            time= "0"+seconds;

        } else {
            time = seconds+"";
        }

        if(minutes < 10){

            time+=":0"+minutes;
        } else {

            time+=minutes;
        }

        if(hours < 10){
            time+=":0"+hours;
        } else {
            time+=hours;
        }

        return time;
    }



}
