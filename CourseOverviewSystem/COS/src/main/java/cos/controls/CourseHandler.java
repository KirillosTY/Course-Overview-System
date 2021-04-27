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

        if (current.getWorkHoursSpent().getStartDate().isAfter(LocalDateTime.now())) {

            if (!upcomingCourse.contains(c)) {
                upcomingCourse.add(current);
            }

            return 1;
        } else if (current.getWorkHoursSpent().getStartDate().isBefore(LocalDateTime.now())
                && current.getWorkHoursSpent().getEndDate().isAfter(LocalDateTime.now())) {

            if (!courseList.contains(c)) {
                courseList.add(current);
            }
            return 2;
        } else {

            pastCourse.add(current);
            return 3;
        }

    }

    public void createCourse(Course c) {

        courseList.add(c);
        if (current == null) {
            current = c;
        }

    }

    public void markCourseAsDone(Course c, boolean delete) {

        if (courseList.contains(c)) {

            if (delete) {

                courseList.remove(c);
            } else {
                courseList.remove(c);
                pastCourse.add(c);
            }
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
