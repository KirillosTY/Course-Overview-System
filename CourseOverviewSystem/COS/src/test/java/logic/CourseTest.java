package logic;

import cos.controls.Course;
import cos.controls.Task;
import cos.controls.WorkHourCounter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class CourseTest {

    private Task  testTask;
    private Course testCourse;
    private WorkHourCounter testWHC;

    @Before
    public void initialize(){
        testWHC = new WorkHourCounter();

        testCourse = new Course(false,testWHC,
                "test","no need","notes",0,0);



        for (int i = 0; i <10; i++) {

            testWHC = new WorkHourCounter();
            testWHC.setEndDate(LocalDateTime.now().plusDays(30-i));
            testWHC.setStartDate(LocalDateTime.now().plusDays(i));
            testTask = new Task(false, testWHC,"task"+i,""+i,"",0);
            testCourse.addTask(testTask);
        }

    }

    @Test
    public void taskIsFirst(){
        testWHC = new WorkHourCounter();
        testWHC.setStartDate(LocalDateTime.now());
        testWHC.setEndDate(LocalDateTime.now().plusDays(1));
        testTask = new Task(false,testWHC, "should be first","first","",0);
        testCourse.addTask(testTask);
        System.out.println(testCourse.getTaskList());
        assertEquals(testTask,testCourse.getTaskList().get(0));

    }
}
