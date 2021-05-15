package cos.controls;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class handles all the courses of this application.
 */

public class CourseHandler implements Serializable {


    private ArrayList<Course> courseList;

    private ArrayList<Course> pastCourse;

    private ArrayList<Course> upcomingCourse;

    private Course current;


    private Task currentTask;

    public CourseHandler(ArrayList<Course> courseL) {

        this.courseList = courseL;



        pastCourse = new ArrayList<>();

        upcomingCourse = new ArrayList<>();
    }

    /**
     * Creates a course on the given parameters and then passes the course to method courselistDecider.
     *
     * @param state       Marks the course as done or undone.
     * @param wHS         WorkHourCounter object
     * @param name        name of the object
     * @param description Description of the object
     * @param notes       Notes of the object
     * @param priority    Value of priority.
     * @param value       Value of the course.
     */

    public void createCourse(boolean state, WorkHourCounter wHS, String name, String description
            , String notes, int priority, int value) {

        current = new Course(state, wHS, name, description, notes, priority, value);

        courseListDecider(current);


    }

    /**
     * Updates Upcoming and current courselist by start and end dates using the courseListDecider method.
     */

    public void courseDateUpdater() {

        upcomingCourse.removeIf(c -> courseListDecider(c) != 1);
        courseList.removeIf(c -> courseListDecider(c) != 2);
    }


    /**
     * Assigns the given parameter to the right list based on start and end date.
     *
     * @param c Course to be evaluated.
     * @return number represents list that the course got moved to.
     */

    public int courseListDecider(Course c) {


        if (c.getWorkHoursSpent().getStartDate().isAfter(LocalDateTime.now()) && c.getWorkHoursSpent().getEndDate().isAfter(LocalDateTime.now())) {

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

    /**
     * Adds thee given parameter to the courseList, then passes it to courseListDecider().
     *
     * @param c  Course to be added and evaluated.
     *
     */



    public void createCourse(Course c) {

        courseList.add(c);
        if (current == null) {
            current = c;
        }
        courseListDecider(c);

    }

    /**
     * Checks on which list the course is currently on.
     *
     * @param c Course to be evaluated.
     * @return Courselist where course is found. if not found then null.
     */

    public ArrayList checkList(Course c) {

        if (upcomingCourse.contains(c)) {
            return upcomingCourse;
        }

        if (courseList.contains(c)) {
            return courseList;
        }

        if (pastCourse.contains(c)) {
            return pastCourse;
        }


        return null;

    }

    /**
     * Checks on which list the course is the marks the course as done and moves it the pastCourse list
     * Or if parameter delete is true, then it just removes the course from the given list.
     *
     * @param c course to be checked and, marked or deleted.
     * @param delete defines if course will be deleted.
     * @return returns true or false based on finding the course.
     */


    public boolean markCourseAsDone(Course c, boolean delete) {
        ArrayList<Course> checkedList = checkList(c);
        if (checkedList == null) {
            return false;
        }
        if (delete) {
            checkedList.remove(c);
        } else if (!checkedList.equals(pastCourse)) {
            pastCourse.add(c);
            checkedList.remove(c);
        }
        return true;
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
