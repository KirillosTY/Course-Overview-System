package Controls;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseHandler implements Serializable {


    private ArrayList<Course> courseList;

    private Course current;

    private String notesOverall;

    private Integer courseID;

    public CourseHandler (ArrayList<Course> courseL, String notesO, Integer cID) {

        this.courseList = courseL;

        this.notesOverall = notesO;

        this.courseID = cID;

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
