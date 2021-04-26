package Controls;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class BasicTask implements  Serializable {


    private boolean done = false;

    private WorkHourCounter workHoursSpent;

    private String name;

    private String notes;

    private String description;

    private Integer priority;


    public BasicTask(boolean state, WorkHourCounter wHS, String name, String des, String notes, Integer prio) {

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

    public void saveNotesWithStamp(String notes){

        String time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.LONG,FormatStyle.MEDIUM));
        StringBuilder  build = new StringBuilder();
        build.append(time+":\n\n");

        build.append(notes);

        this.setNotes(build.toString());

    }

    @Override
    public String toString() {

        return this.name + " - "+this.description;
    }
}
