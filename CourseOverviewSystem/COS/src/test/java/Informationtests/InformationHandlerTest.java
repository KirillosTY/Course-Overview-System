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
    private Properties testProp;
    private static TemporaryFolder removeF = new TemporaryFolder();

    @Rule
    public  TemporaryFolder testF = new TemporaryFolder();

    @Before
    public  void initialize(){


        tester = new InformationHandler();

        loadingTestProp();
        setURL();
        createSettings();
        createCourse();
    }

    @Test
    public void loadingTestProp(){


        if(!tester.loadProp()){
            Assert.fail();
        }



    }

    @Test
    public  void setURL(){

        tester.getProperties().setProperty("setTest","settTest.bin");

        tester.getProperties().setProperty("chTest","chTest.bin");

        tester.setURLS("chTest","setTest");

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

        try {
            Files.deleteIfExists(Paths.get(tester.getProperties().getProperty("setTest")));

        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            Files.deleteIfExists(Paths.get(tester.getProperties().getProperty("chTest")));

        } catch (Exception e){
            e.printStackTrace();

        }

    }









}
