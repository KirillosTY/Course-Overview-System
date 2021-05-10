package cos.controls;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Comparator;


/**
 * The base class for all courses.
 */

public class Course extends BasicTask {

    private final ArrayList<Task> doneTasks;
    private int value;
    private ArrayList<Task> taskList;

    /**
     * Creates a course object.
     *
     * @param state       Marks the course as done or undone.
     * @param wHS         WorkHourCounter object
     * @param name        name of the object
     * @param description Description of the object
     * @param notes       Notes of the object
     * @param priority    Value of priority.
     * @param value       Value of the course.
     */


    public Course(boolean state, WorkHourCounter wHS, String name, String description, String notes, int priority, int value) {


        super(state, wHS, name, description, notes, priority);

        saveNotesWithStamp(notes);
        this.value = value;

        this.taskList = new ArrayList<>();

        this.doneTasks = new ArrayList<>();
    }

    /**
     * Creates a task object. Updates the dates on all the tasks as this is done and sets a current task to the CourseHandler if null.
     *
     * @param state Marks the tasks as done or undone.
     * @param wHS   WorkHourCounter object
     * @param name  name of the object
     * @param des   Description of the object
     * @param notes Notes of the object
     * @param prio  Value of priority.
     */

    public void addTask(boolean state, WorkHourCounter wHS, String name, String des, String notes, Integer prio) {
        Task newTask = new Task(state, wHS, name, des, notes, prio);
        taskList.add(newTask);
        taskDateUpdater();
        saveNotesWithStamp(notes, name);
        if (MainController.getCourseHandler().getCurrentTask() == null) {
            MainController.getCourseHandler().setCurrentTask(newTask);
        }

    }

    /**
     * adds a task object to the tasklist then updates through taskDateUpdater() method.
     * and saves notes to this course through saveNotesWithSTamp method.
     *
     * @see Course#saveNotesWithStamp(String, String)
     * @see Course#taskDateUpdater()
     *
     * @param task task object
     */

    public void addTask(Task task) {

        taskList.add(task);
        taskDateUpdater();
        saveNotesWithStamp(task.getNotes(), task.getName());

    }


    public ArrayList<Task> getTaskList() {

        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * adds the notes as a note to this objects current notes with the nameOfNote as a stamp.
     *
     * @param notes notes written in the task
     * @param nameOfNote name of the task object
     */

    public void saveNotesWithStamp(String notes, String nameOfNote) {

        String time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.LONG, FormatStyle.MEDIUM));
        if (notes.split("\n\n\n").length > 0) {

            StringBuilder build = new StringBuilder(time + " " + nameOfNote + ":\n\n"
                    + notes.split("\n\n\n")[0]);
            build.append("\n\n\n");
            build.append(this.getNotes());
            this.setNotes(build.toString());
        }

    }

    /**
     * Updates tasks based on the end on the WorkHourCounter.
     * if end date is past current time it is  moved to the doneTasks list.
     * afterwards it will call taskListSort() to sort them.
     *
     * @see WorkHourCounter#getEndDate()
     *
     *
     */

    public void taskDateUpdater() {

        for (Task t : taskList) {

            if (t.getWorkHoursSpent().getEndDate().isBefore(LocalDateTime.now())) {
                doneTasks.add(t);
            }
        }

        taskList.removeIf(t -> t.getWorkHoursSpent().getEndDate().isBefore(LocalDateTime.now()));
        taskListSort();
    }

    /**
     * Sorts tasks first by end date and secondly by starting date.
     *
     */

    public void taskListSort() {

        taskList.sort(Comparator.comparing((t1) -> t1.getWorkHoursSpent().getStartDate()));
        taskList.sort(Comparator.comparing((t1) -> t1.getWorkHoursSpent().getEndDate()));


    }

    /**
     * If given parameter is found on this objects tasklist it is moved to the doneTasks list and its end date set
     * for current time.
     *
     * @param task task object
     * @return returns true or false based on successful action.
     */

    public boolean markTaskDone(Task task) {

        if (this.getTaskList().contains(task)) {
            task.getWorkHoursSpent().setEndDate(LocalDateTime.now());
            doneTasks.add(task);
            return taskList.remove(task);

        } else {
            return false;
        }
    }

    /**
     * removes given parameter from the tasklist.
     * @param task Task object
     * @return returns true or false based on successful action.
     */

    public boolean removeTask(Task task) {

        return taskList.remove(task);

    }

    public ArrayList<Task> getDoneTasks() {
        return doneTasks;
    }

    /**
     * Returns a string representation of the objects name and  days left to deadline.
     *
     *
     * @return name and current state of time on the object.
     */

    @Override
    public String toString() {

        return this.getName() + " - Days left: " + Duration.between(LocalDateTime.now(), this.getWorkHoursSpent().getEndDate()).toDays();
    }
}
