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
        properties = new Properties();

    }


    public void loadProperties() {


        try {
            properties = (Properties) fileReaderInput("src/main/resources/config.properties");
            courseHandlerURL = properties.getProperty("CourseHandler");
            settingsURL = properties.getProperty("Settings");

        } catch (ClassNotFoundException | IOException e) {

        }

    }


    public void createProperties() {

        try (OutputStream outprop = new FileOutputStream("src/main/resources/config.properties")) {
            properties = new Properties();
            properties.setProperty("CourseHandler", "src/main/resources/courselist.bin");
            properties.setProperty("Settings", "src/main/resources/settings.bin");
            courseHandlerURL = "src/main/resources/courselist.bin";
            settingsURL = "src/main/resources/settings.bin";
            properties.store(outprop, null);

        } catch (Exception e) {

        }

    }


    public boolean checkForProp() {

        try (FileInputStream propL = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(propL);

            settingsURL = properties.getProperty("Settings");
            courseHandlerURL = properties.getProperty("CourseHandler");

            return true;
        } catch (IOException e) {
            return false;
        }


    }

    public boolean fileReaderOutput(String fileUrl, Object obj) {

        try (BufferedOutputStream cW = new BufferedOutputStream(new FileOutputStream(fileUrl))) {

            ObjectOutputStream oS = new ObjectOutputStream(cW);

            oS.writeObject(obj);
            oS.close();
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public Object fileReaderInput(String fileUrl) throws IOException, ClassNotFoundException {

        BufferedInputStream cR = new BufferedInputStream(new FileInputStream(fileUrl));

        return new ObjectInputStream(cR).readObject();


    }

    public boolean createCourseList() {


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

        Settings settings = new Settings();

        return fileReaderOutput(settingsURL, settings);


    }

    public boolean saveSettings(Settings saveSetting) {

        return fileReaderOutput(settingsURL, saveSetting);


    }

    public Settings loadSettings() {

        try {
            if (fileReaderInput(settingsURL) != null) {
                Settings settings = (Settings) fileReaderInput(settingsURL);
                return settings;
            }


        } catch (ClassNotFoundException | IOException e) {

            //this line will need to handle the file not found exception!
            return null;

        }

        return null;

    }

    public Properties getProperties() {
        return properties;
    }


}
