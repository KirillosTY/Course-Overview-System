package Controls;

import java.util.HashMap;

public class CourseHandler {


    private HashMap<Integer ,Course> courseList;

    private Course current;

    private String notesOverall;

    public CourseHandler(){

        this.courseList = new HashMap<>();
        this.notesOverall = "";

    }

    public CourseHandler (HashMap<Integer, Course> courseL, String notesO, Integer cID) {



    }

}
