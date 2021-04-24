package Controls;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseHandler implements Serializable {


    private ArrayList<Course> courseList;

    private Course current;

    private String notesOverall;

    private Task currentTask;

    public CourseHandler(ArrayList<Course> courseL, String notesO) {

        this.courseList = courseL;

        this.notesOverall = notesO;

    }

    public void createCourse(boolean state, WorkHourCounter wHS, String name, String description
            , String notes, int priority, int value) {

        current = new Course(state, wHS, name, description, notes, priority, value);
        courseList.add(current);

    }

    public void createCourse(Course c) {

        courseList.add(c);
        if (current == null) {
            current = c;
        }

    }


    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public Course getCurrent() {
        return current;
    }

    public void setCurrent(Course current) {

        this.current = current;
    }

    public String getNotesOverall() {
        return notesOverall;
    }

    public void setNotesOverall(String notesOverall) {
        this.notesOverall = notesOverall;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }
}
