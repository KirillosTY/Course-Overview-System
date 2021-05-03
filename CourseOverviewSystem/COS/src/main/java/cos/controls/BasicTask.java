package cos.controls;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

/**
 * The basis of courses and tasks. This class offers all the basic implementation of courses and tasks.
 *
 *
 */


public class BasicTask implements Serializable {


    private boolean done = false;

    private WorkHourCounter workHoursSpent;

    private String name;

    private String notes;

    private String description;

    private Integer priority;

    /**
     * Creates a task object.
     * @param state Marks the tasks as done or undone.
     * @param wHS WorkHourCounter object
     * @param name name of the object
     * @param des Description of the object
     * @param notes
     * @param prio
     */


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

    /**
     * adds the string as note to this objects current notes.
     *
     *
     * @param notes
     */

    public void saveNotesWithStamp(String notes) {

        String time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.LONG, FormatStyle.MEDIUM));
        StringBuilder build = new StringBuilder();
        build.append(time + ":\n\n");

        build.append(notes);

        this.setNotes(build.toString());

    }

    /**
     * Returns a string representation of the objects name and timeleft to deadline.
     * Shortens any number longer than characters.
     * If task is done it display date completed instead of time left.
     *
     *
     * @return name and current state of time on the object.
     */


    @Override
    public String toString() {
        String tempN = this.name;
        if (name.length() > 10) {
            tempN = this.name.substring(0, 10) + "...";
        }
        if (getWorkHoursSpent().getEndDate().isBefore(LocalDateTime.now())) {

            return tempN + "... - Completed: " + getWorkHoursSpent().getEndDate().toLocalDate();

        }
        return tempN + " - Hours left: " + Duration.between(LocalDateTime.now(), this.getWorkHoursSpent().getEndDate()).toHours();


    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasicTask)) {
            return false;
        }
        BasicTask basicTask = (BasicTask) o;
        return done == basicTask.done &&
                workHoursSpent.equals(basicTask.workHoursSpent) &&
                name.equals(basicTask.name) &&
                description.equals(basicTask.description) &&
                Objects.equals(priority, basicTask.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(done, workHoursSpent, name, description, priority);
    }
}
