package Controls;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class Course extends BasicTask {


    private int value;

    private ArrayList<Task> taskList;




    public Course(boolean state, WorkHourCounter wHS, String name, String description, String notes, int priority, int value) {

        super(state, wHS, name, description, notes, priority);

        this.value = value;

        this.taskList = new ArrayList<>();
    }

    public void addTask(boolean state, WorkHourCounter wHS, String name, String des, String notes, Integer prio) {
        Task newTask = new Task(state, wHS, name, des, notes, prio);
        taskList.add(newTask);

        if(MainController.getCourseHandler().getCurrentTask()== null){
            MainController.getCourseHandler().setCurrentTask(newTask);
        }

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


    public void saveNotesWithStamp(String notes, String nameOfNote){

        String time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.LONG,FormatStyle.MEDIUM));

        StringBuilder  build = new StringBuilder(time+" "+nameOfNote+":\n\n"
                +notes.split("\n\n\n")[0]);
        build.append("\n\n\n");
        build.append(this.getNotes());
        this.setNotes(build.toString());

    }


    public boolean removeTask(Task task){

        if(taskList.remove(task)) {
            taskList.remove(task);

            return true;
        } else return false;

    }


    @Override
    public String toString() {

        return super.getName() + " - " + super.getDescription();
    }
}
