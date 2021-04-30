package cos.controls;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CourseHandler implements Serializable {


    private ArrayList<Course> courseList;

    private ArrayList<Course> pastCourse;

    private ArrayList<Course> upcomingCourse;

    private Course current;

    private String notesOverall;

    private Task currentTask;

    public CourseHandler(ArrayList<Course> courseL, String notesO) {

        this.courseList = courseL;

        this.notesOverall = notesO;

        pastCourse = new ArrayList<>();

        upcomingCourse = new ArrayList<>();
    }

    public void createCourse(boolean state, WorkHourCounter wHS, String name, String description
            , String notes, int priority, int value) {

        current = new Course(state, wHS, name, description, notes, priority, value);

        courseListDecider(current);
        MainController.getInformationHandler().saveCourseHandler(MainController.getCourseHandler());



    }

    public void courseDateUpdater() {

        upcomingCourse.removeIf(c -> courseListDecider(c) != 1);
        courseList.removeIf(c -> courseListDecider(c) != 2);
    }

    public int courseListDecider(Course c) {

        if (c.getWorkHoursSpent().getStartDate().isAfter(LocalDateTime.now())) {

            if (!upcomingCourse.contains(c)) {
                upcomingCourse.add(c);
            }

            return 1;
        } else if (c.getWorkHoursSpent().getStartDate().isBefore(LocalDateTime.now())
                && c.getWorkHoursSpent().getEndDate().isAfter(LocalDateTime.now())) {

            if (!courseList.contains(c)) {
                courseList.add(c);
            }
            return 2;
        } else {

            pastCourse.add(c);
            return 3;
        }

    }

    public void createCourse(Course c) {

        courseList.add(c);
        if (current == null) {
            current = c;
        }

    }

    public int checkList(Course c){

        if(courseList.contains(c)){
            return 1;
        }

        if(pastCourse.contains(c)){
            return  2;
        }

        if(upcomingCourse.contains(c)){
            return 3;
        }
        return -1;

    }


    public void markCourseAsDone(Course c, boolean delete) {

        ArrayList<Course> cList = new ArrayList<>();

        int list = checkList(c);
        if(list == -1){
            return;
        }

        if(list == 1) cList = courseList;

        if(list == 2) cList = pastCourse;

        if(list == 3) cList = upcomingCourse;

        if (delete) {
            cList.remove(c);
        } else if(list != 2) {

            pastCourse.add(c);
            cList.remove(c);

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

    public ArrayList<Course> getPastCourse() {
        return pastCourse;
    }

    public void setPastCourse(ArrayList<Course> pastCourse) {
        this.pastCourse = pastCourse;
    }

    public ArrayList<Course> getUpcomingCourse() {
        return upcomingCourse;
    }

    public void setUpcomingCourse(ArrayList<Course> upcomingCourse) {
        this.upcomingCourse = upcomingCourse;
    }


}
