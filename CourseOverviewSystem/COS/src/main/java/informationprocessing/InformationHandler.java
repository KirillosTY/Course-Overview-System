package informationprocessing;

import controls.Course;
import controls.CourseHandler;
import controls.Settings;

import java.io.*;
import java.util.ArrayList;

public class InformationHandler implements Serializable {

    public InformationHandler() {


    }

    public boolean fileReaderOutput(String fileUrl, Object obj) throws FileNotFoundException {

        try (FileOutputStream cW = new FileOutputStream(fileUrl)) {

            ObjectOutputStream oS = new ObjectOutputStream(cW);

            oS.writeObject(obj);


        } catch (IOException e) {
            System.out.println("tämä");
            return false;
        }

        return true;
    }

    public Object fileReaderInput(String fileUrl) throws IOException {

        try (FileInputStream cR = new FileInputStream(fileUrl)) {

            return new ObjectInputStream(cR).readObject();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

            //This line will need to handle other exceptions

        }


        return null;
    }

    public boolean createCourseList() {

        try {
            CourseHandler cH = new CourseHandler(new ArrayList<Course>(), "Write something");

            fileReaderOutput("CourseInfo/courselist.bin", cH);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
            // this line will need to handle exceptions.
        }

        return true;
    }

    public boolean saveCourseHandler(CourseHandler courseHandler) {

        try {

            return fileReaderOutput("CourseInfo/courselist.bin", courseHandler);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            // this line will need to handle settings.
        }
        return false;

    }

    public CourseHandler courseLoader() {

        try {

            if (fileReaderInput("CourseInfo/courselist.bin") != null) {

                return (CourseHandler) fileReaderInput("CourseInfo/courselist.bin");
            }


        } catch (FileNotFoundException e) {


            //this line will need to handle the file not found exception!
        } catch (IOException e) {


        }

        return null;
    }


    public boolean createSettings() {

        try {

            Settings settings = new Settings();

            fileReaderOutput("CourseInfo/settings.bin", settings);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            // this line will need to handle exceptions.
        }
        return false;

    }

    public boolean saveSettings(Settings saveSetting) {

        try {

            return fileReaderOutput("CourseInfo/settings.bin", saveSetting);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            // this line will need to handle settings.
        }
        return false;

    }

    public Settings loadSettings() {


        try {
            if (fileReaderInput("CourseInfo/Settings.bin") != null) {
                Settings settings = (Settings) fileReaderInput("CourseInfo/Settings.bin");
                return settings;
            }


        } catch (FileNotFoundException e) {
            System.out.println("Ei löytynyt luodaan");
            //this line will need to handle the file not found exception!
            return null;

        } catch (IOException e) {


        }

        return null;

    }


}
