package cos.informationprocessing;

import cos.controls.Course;
import cos.controls.CourseHandler;
import cos.controls.Settings;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class InformationHandler implements Serializable {

    private Properties properties;

    private String courseHandlerURL;

    private String settingsURL;

    public InformationHandler() {
        this(new Properties());

    }

    public InformationHandler(Properties propset) {
        properties = propset;

    }



    public void createDefaultProperties() {

        try (OutputStream outprop = new FileOutputStream("config.properties")) {
            properties.setProperty("Propset","true");
            properties.setProperty("CourseHandler", "courselist.bin");
            properties.setProperty("Settings", "settings.bin");
            courseHandlerURL = "courselist.bin";
            settingsURL = "settings.bin";
            properties.store(outprop, null);

        } catch (Exception e) {

        }

    }

    public void saveProperties() throws IOException{

        try (OutputStream outprop = new FileOutputStream("config.properties")) {

            properties.store(outprop,null);

        }
    }


    public boolean loadProp() {

        try (InputStream propLocation = new FileInputStream("config.properties")) {

            properties.load(propLocation);

        } catch (Exception e){

            try (InputStream propL = InformationHandler.class.getClassLoader().getResourceAsStream("config.properties")) {

                properties.load(propL);
            }catch (Exception s ){
            return false;
            }

        }
        return true;


    }

    public void setURLS(String courseH, String settings){
        settingsURL = properties.getProperty(settings);
        courseHandlerURL = properties.getProperty(courseH);


    }


    public boolean fileReaderOutput(String fileUrl, Object obj) {

        try (BufferedOutputStream cW = new BufferedOutputStream(new FileOutputStream(fileUrl))) {

            ObjectOutputStream oS = new ObjectOutputStream(cW);

            oS.writeObject(obj);
            oS.close();
        } catch (IOException e) {


            return false;
        }

        return true;
    }

    public Object fileReaderInput(String fileUrl) throws IOException, ClassNotFoundException {

        BufferedInputStream cR = new BufferedInputStream(new FileInputStream(fileUrl));
        ObjectInputStream closeMe =new ObjectInputStream(cR);
        Object sendAway = closeMe.readObject();
        closeMe.close();


        return sendAway;


    }

    public boolean createCourseList() {

        properties.setProperty("CHCreated","true");
        CourseHandler cH = new CourseHandler(new ArrayList<Course>(), "Write something");

        return fileReaderOutput(courseHandlerURL, cH);

    }

    public boolean saveCourseHandler(CourseHandler courseHandler) {

        return fileReaderOutput(courseHandlerURL, courseHandler);


    }

    public CourseHandler courseLoader() {

        try {

            return (CourseHandler) fileReaderInput(courseHandlerURL);


        } catch (ClassNotFoundException | IOException e) {


        }

        return null;
    }


    public boolean createSettings() {

        properties.setProperty("settingsCreated","true");
        Settings settings = new Settings();

        return this.saveSettings(settings);


    }

    public boolean saveSettings(Settings saveSetting) {

        return fileReaderOutput(settingsURL, saveSetting);


    }

    public Settings loadSettings() {

        try {

                Settings settings = (Settings) fileReaderInput(settingsURL);
                return settings;


        } catch (ClassNotFoundException | IOException e) {

            //this line will need to handle the file not found exception!
            return null;

        }



    }

    public Properties getProperties() {
        return properties;
    }





}
