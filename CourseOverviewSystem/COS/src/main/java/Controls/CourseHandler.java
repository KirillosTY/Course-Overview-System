package Controls;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseHandler implements Serializable  {


    private ArrayList<Course> courseList;

    private Course current;

    private String notesOverall;


    public CourseHandler (ArrayList<Course> courseL, String notesO) {

        this.courseList = courseL;

        this.notesOverall = notesO;



    }

    public void createCourse(boolean state, WorkHourCounter wHS,String name, String description
            , String notes,int priority, int value){

        Course course = new Course(state, wHS, name, description, notes, priority, value);
        System.out.println("luotu");
        courseList.add(course);
        if(current==null){
            current = course;
        }


    }

    public void createCourse(Course c){


        System.out.println("luotu");
        courseList.add(c);
        if(current==null){
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


}
