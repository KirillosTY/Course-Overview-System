package Controls;

import java.time.LocalDateTime;

public class  Task  {

    private boolean done = false;

    private LocalDateTime endDate;

    private LocalDateTime startDate;

    private Long workHoursSpent;

    private String name;

    private String notes;

    private Integer priority;



    public Task(boolean state, LocalDateTime eD, Long wHS,String name, String notes, Integer prio) {

        this(state, eD, LocalDateTime.now(), wHS, name, notes, prio);

    }

    public Task(boolean state, LocalDateTime eD, LocalDateTime sD,
                Long wHS,String name, String notes, Integer prio){

        this.startDate = sD;

        this.done = state;

        this.endDate = eD;

        this.workHoursSpent = wHS;

        this.name = name;

        this.notes = notes;

        this.priority = prio;


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
