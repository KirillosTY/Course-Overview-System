package Controls;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Course {


    private boolean done = false;

    private LocalDateTime endDate;

    private LocalDateTime startDate;

    private Long workHoursSpent;

    private String name;

    private String notes;

    private int value;

    private HashMap<Integer, Controls.Task> taskList;


    public Course(boolean state, LocalDateTime eD,

                  Long wHS,String name, String notes, int value) {

        this(state, eD, LocalDateTime.now(), wHS, name, notes,value);

    }

    public Course(boolean state, LocalDateTime eD,LocalDateTime sD, Long wHS,

                  String name, String notes,  int value) {

        this.startDate = sD;

        this.done = state;

        this.endDate = eD;

        this.workHoursSpent = wHS;

        this.name = name;

        this.notes = notes;

        this.value = value;

        this.taskList = new HashMap<>();
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

    public Long getWorkHoursSpent() {
        return workHoursSpent;
    }

    public void setWorkHoursSpent(Long workHoursSpent) {
        this.workHoursSpent = workHoursSpent;
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
}
