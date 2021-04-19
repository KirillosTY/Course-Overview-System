package Controls;

import java.io.Serializable;
import java.time.LocalDateTime;

public class  Task implements Comparable, Serializable {

    private boolean done = false;

    private LocalDateTime endDate;

    private LocalDateTime startDate;

    private WorkHourCounter workHoursSpent;

    private String name;

    private String notes;

    private String description;

    private Integer priority;


    public Task(boolean state, WorkHourCounter wHS,String name,String des, String notes, Integer prio){

        this.done = state;

        this.workHoursSpent = wHS;

        this.name = name;

        this.notes = notes;

        this.priority = prio;

        this.description = des;


    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Object o) {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WorkHourCounter getWorkHoursSpent() {
        return workHoursSpent;
    }

    public void setWorkHoursSpent(WorkHourCounter workHoursSpent) {
        this.workHoursSpent = workHoursSpent;
    }

    @Override
    public String toString(){

        return this.name;
    }
}
