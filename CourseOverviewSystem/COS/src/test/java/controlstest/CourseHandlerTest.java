package controlstest;

import cos.controls.Course;
import cos.controls.CourseHandler;
import cos.controls.Task;
import cos.controls.WorkHourCounter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CourseHandlerTest {


    private CourseHandler handler;
    private Task testTask;
    private Course testCourse;
    private WorkHourCounter testWHC;

    @Before
    public void initialize() {

        handler = new CourseHandler(new ArrayList<Course>());
        testWHC = new WorkHourCounter();
        testWHC.setStartDate(LocalDateTime.now());
        testWHC.setEndDate(LocalDateTime.now().plusDays(55).plusMinutes(10));
        testCourse = new Course(false, testWHC,
                "test", "no need", "notes", 0, 0);


        for (int k = 0; k < 9; k++) {
            handler.createCourse(new Course(false, testWHC,
                    "test " + k, "no need " + k, "notes", k, k));

            for (int i = 0; i < 10; i++) {

                testWHC = new WorkHourCounter();
                testWHC.setEndDate(LocalDateTime.now().plusDays(30 - i));
                testWHC.setStartDate(LocalDateTime.now().plusDays(i));
                testTask = new Task(false, testWHC, "task" + i * k, "" + i * k, "", 0);
                handler.getCurrent().addTask(testTask);
            }

        }
    }

    @Test
    public void courseToCurrentList(){

        testWHC.setStartDate(LocalDateTime.now().minusMinutes(10));
        testWHC.setEndDate(LocalDateTime.now().plusDays(55).plusMinutes(10));
        testCourse = new Course(false, testWHC,
                "current", "no need", "notes", 0, 0);
        handler.createCourse(false, testWHC,
                "current", "no need", "notes", 0, 0);

        assertTrue(handler.getCourseList().contains(testCourse));

    }

    @Test
    public void courseToUpcomingList(){


        testWHC.setStartDate(LocalDateTime.now().plusDays(20));
        testWHC.setEndDate(LocalDateTime.now().plusDays(55).plusMinutes(10));

        testCourse = new Course(false, testWHC,
                "up", "no need", "notes", 0, 0);

        handler.createCourse(false, testWHC,
                "up", "no need", "notes", 0, 0);

        assertTrue(handler.getUpcomingCourse().contains(testCourse));

    }

    @Test
    public void courseToPastList(){
        testWHC.setStartDate(LocalDateTime.now());
        testWHC.setEndDate(LocalDateTime.now().minusDays(55).plusMinutes(10));

        testCourse = new Course(false, testWHC,
                "past", "no need", "notes", 0, 0);
        handler.createCourse(false, testWHC,
                "past", "no need", "notes", 0, 0);

        assertTrue(handler.getPastCourse().contains(testCourse));

    }

    @Test
    public void listDeciderCurrent(){

        testWHC.setStartDate(LocalDateTime.now().minusMinutes(10));
        testWHC.setEndDate(LocalDateTime.now().plusDays(55).plusMinutes(10));
        testCourse = new Course(false, testWHC,
                "past", "no need", "notes", 0, 0);

        assertEquals(handler.courseListDecider(testCourse),2);


    }

    @Test
    public void listDeciderUpc(){

        testWHC.setStartDate(LocalDateTime.now().plusDays(2));
        testWHC.setEndDate(LocalDateTime.now().plusDays(55).plusMinutes(10));
        testCourse = new Course(false, testWHC,
                "past", "no need", "notes", 0, 0);

        assertEquals(handler.courseListDecider(testCourse),1);


    }

    @Test
    public void listDeciderPast(){

        testWHC.setStartDate(LocalDateTime.now().plusDays(2));
        testWHC.setEndDate(LocalDateTime.now().minusDays(55).plusMinutes(10));
        testCourse = new Course(false, testWHC,
                "past", "no need", "notes", 0, 0);

        assertEquals(handler.courseListDecider(testCourse),3);


    }

    @Test
    public void markCourseAsDone(){

        testWHC.setStartDate(LocalDateTime.now().minusMinutes(10));
        testWHC.setEndDate(LocalDateTime.now().plusDays(55).plusMinutes(10));
        testCourse = new Course(false, testWHC,
                "past", "no need", "notes", 0, 0);
        handler.createCourse(testCourse);

        handler.markCourseAsDone(testCourse, false);

        assertTrue(handler.getPastCourse().contains(testCourse));

    }

    @Test
    public void markCourseDelete(){

        testWHC.setStartDate(LocalDateTime.now().minusMinutes(10));
        testWHC.setEndDate(LocalDateTime.now().plusDays(55).plusMinutes(10));
        testCourse = new Course(false, testWHC,
                "past", "no need", "notes", 0, 0);
        handler.createCourse(testCourse);

        handler.markCourseAsDone(testCourse, true);

        assertFalse(handler.getPastCourse().contains(testCourse));

    }

    @Test
    public void markCDoneNotFound(){



        assertFalse(handler.markCourseAsDone(new Course(false,testWHC,"found","po","intless",0,0), false));

    }




}
