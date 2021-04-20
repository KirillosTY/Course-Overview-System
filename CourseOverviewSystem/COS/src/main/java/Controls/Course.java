package Controls;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Course extends Task implements Serializable {


    private int value;

    private ArrayList<Task> taskList;


    public Course(boolean state, WorkHourCounter wHS, String name, String description, String notes, int priority, int value) {

        super(state, wHS, name, description, notes, priority);

        this.value = value;

        this.taskList = new ArrayList<>();
    }

    public void addTask(boolean state, WorkHourCounter wHS, String name, String des, String notes, Integer prio) {

        taskList.add(new Task(state, wHS, name, des, notes, prio));

    }

    public void addTaskStartDate(boolean state, LocalDateTime eD, LocalDateTime sD,
                                 WorkHourCounter wHS, String name, String des, String notes, Integer prio) {

        taskList.add(new Task(state, wHS, name, des, notes, prio));
    }


    public ArrayList<Task> getTaskList() {


        System.out.println(taskList);
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

    @Override
    public String toString() {

        return super.getName() + " - " + super.getDescription();
    }
}
