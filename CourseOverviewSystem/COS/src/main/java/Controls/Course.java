package Controls;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Course {


    private boolean done;

    private WorkHourCounter workHoursSpent;

    private String name;

    private String notes;

    private int value;

    private ArrayList<Task> taskList;


    public Course(boolean state, WorkHourCounter wHS, String name, String notes,  int value) {

        this.done = state;

        this.workHoursSpent = wHS;

        this.name = name;

        this.notes = notes;

        this.value = value;

        this.taskList = new ArrayList<>();
    }

    public void addTask(boolean state, WorkHourCounter wHS,String name, String des, String notes, Integer prio){

        taskList.add(new Task(state, wHS, name, des, notes, prio));

    }

    public void addTaskStartDate(boolean state, LocalDateTime eD, LocalDateTime sD,
                                 WorkHourCounter wHS,String name,String des, String notes, Integer prio){

        taskList.add(new Task(state, wHS, name, des, notes, prio));
    }





    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    public WorkHourCounter getWorkHoursSpent() {
        return workHoursSpent;
    }

    public void setWorkHoursSpent(WorkHourCounter workHoursSpent) {
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
}
