package Informationtests;


import cos.controls.CourseHandler;
import cos.controls.Settings;
import cos.informationprocessing.InformationHandler;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import static org.junit.Assert.*;

public class InformationHandlerTest {

    private static InformationHandler tester = new InformationHandler();
    private static Properties configBefore;
    private static int configLoc;


    @Rule
    public  TemporaryFolder testF = new TemporaryFolder();

    @BeforeClass
    public static void start(){
        configLoc = tester.loadProp();
        configBefore = tester.getProperties();
        tester.getProperties().setProperty("setTest","settTest.bin");

        tester.getProperties().setProperty("chTest","chTest.bin");

        tester.setURLS("chTest","setTest");
        tester.createSettings();
        tester.createCourseList();

    }





    @Test
    public void saveProp(){

        try {
            tester.saveProperties();
        } catch (IOException e){
            fail();
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
    public void creatingFileFailure(){

        assertFalse(tester.fileReaderOutput("../../../../../../..---", new Object()));

    }

    @Test
    public void loadFileFailure(){

      try {
        tester.fileReaderInput("../../../../../../..----");
        Assert.fail();
      }catch ( IOException | ClassNotFoundException e){

      }

    }

    @Test
    public void loadSetFile() {

        try {

            Settings s = (Settings) tester.fileReaderInput(tester.getProperties().getProperty("setTest"));
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
    public void settingsFailLoad(){


        try {
            tester.setURLS("dwdwa", "dwadd");
            Settings set = tester.loadSettings();
            fail();
        } catch (Exception e){
           assertTrue(true);
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
    public void saveCourseHandler(){

        assertTrue(tester.saveCourseHandler(new CourseHandler(new ArrayList<>(),"")));
    }

    @Test
    public void saveSettings(){

        assertTrue(tester.saveSettings(new Settings()));

    }


    @AfterClass
    public static void removeFiles(){
        System.out.println(configLoc);
        try {
            Files.deleteIfExists(Paths.get(tester.getProperties().getProperty("setTest")));

        } catch (Exception e){

        }
        try {
            Files.deleteIfExists(Paths.get(tester.getProperties().getProperty("chTest")));

        } catch (Exception e){


        }

        if(configLoc == 2){
            try {
                Files.deleteIfExists(Paths.get("config.properties"));
            } catch (Exception e){

            }
        }


    }









}
