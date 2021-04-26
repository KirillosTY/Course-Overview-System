package Informationtests;


import Controls.CourseHandler;
import Controls.Settings;
import InformationCollector.InformationHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class InformationHandlerTest {

    private InformationHandler tester;

    @Before
    public void initialize(){

        tester = new InformationHandler();

    }



    @Test
    public void creatingFile() {

        try {
            tester.fileReaderOutput("CourseInfo/test.bin", new Settings());

            assert true;
        } catch (FileNotFoundException e){
            assert false;
        }


    }

    @Test
    public void loadFile() {

        try {

            Settings s = (Settings) tester.fileReaderInput("CourseInfo/test.bin");
            assertNotNull(s);
        } catch (Exception e){

            assert false;

        }


    }

    @Test
    public void courseLoader(){

        try {

            CourseHandler chTest = tester.courseLoader();
            assertNotNull(chTest);
        } catch (Exception e){
            assert false;
        }

    }

    @Test
    public void settingsLoader(){

        try {

            Settings setTest = tester.loadSettings();

            assertNotNull(setTest);

        } catch (Exception e){
            assert false;
        }

    }

    @Test
    public void createSettings(){

        assertTrue(tester.createSettings());

    }

    @Test
    public void createCourse(){

        assertTrue(tester.createCourseList());

    }

    @Test
    public void saveCourseHandler(){

        assertTrue(tester.saveCourseHandler(new CourseHandler(new ArrayList<>(),"")));
    }

    @Test
    public void saveSettings(){

        assertTrue(tester.saveSettings(new Settings()));

    }








}
