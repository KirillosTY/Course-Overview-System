package InformationCollector;

import Controls.Course;
import Controls.CourseHandler;
import Controls.Settings;

import java.io.*;
import java.util.ArrayList;

public class InformationHandler implements Serializable {

    public InformationHandler() {




    }

    public boolean fileReaderOutput(String fileUrl, Object obj) throws FileNotFoundException {

        try (FileOutputStream CW = new FileOutputStream(fileUrl)) {

            ObjectOutputStream OS = new ObjectOutputStream(CW);

            OS.writeObject(obj);

            return true;


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    public Object fileReaderInput(String fileUrl) throws IOException {

        try (FileInputStream CR = new FileInputStream(fileUrl)) {

            return new ObjectInputStream(CR).readObject();

        } catch (ClassNotFoundException e){

            e.printStackTrace();

            //This line will need to handle other exceptions

        }


        return null;
    }

    public boolean createCourseList()  {

        try {
            CourseHandler CH = new CourseHandler(new ArrayList<Course>(), "Write something");


            fileReaderOutput("CourseInfo/courselist.bin",CH);

            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            // this line will need to handle exceptions.
        }
        return false;

    }

    public boolean saveCourseHandler(CourseHandler courseHandler){

        try {

            return  fileReaderOutput("CourseInfo/courselist.bin", courseHandler);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            // this line will need to handle settings.
        }
        return false;

    }

    public CourseHandler courseLoader() {

        try {

            if(fileReaderInput("CourseInfo/courselist.bin")!=null)

                return  (CourseHandler) fileReaderInput("CourseInfo/courselist.bin");


        } catch (FileNotFoundException e){



            //this line will need to handle the file not found exception!
        }
        catch ( IOException e){


        }

        return null;
    }



    public boolean createSettings(){

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

    public boolean saveSettings(Settings saveSetting){

        try {

            return  fileReaderOutput("CourseInfo/settings.bin", saveSetting);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            // this line will need to handle settings.
        }
        return false;

    }

    public Settings loadSettings(){




        try {
            if (fileReaderInput("CourseInfo/Settings.bin") != null) {
                Settings settings = (Settings) fileReaderInput("CourseInfo/Settings.bin");
                return settings;
            }


        } catch (FileNotFoundException e){
            System.out.println("Ei löytynyt luodaan");
            //this line will need to handle the file not found exception!
            return null;

        } catch ( IOException e){


        }

        return null;

    }




}
