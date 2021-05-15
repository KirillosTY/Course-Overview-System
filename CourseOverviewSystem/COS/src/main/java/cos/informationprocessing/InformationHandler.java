package cos.informationprocessing;

import cos.controls.CourseHandler;
import cos.controls.Settings;

import java.io.*;
import java.util.Properties;

/**
 *
 * this class is responsible for handling information loading and saving.
 */
public class InformationHandler implements Serializable {

    private final Properties properties;

    private String courseHandlerURL;

    private String settingsURL;

    public InformationHandler() {
        this(new Properties());

    }

    public InformationHandler(Properties propset) {
        properties = propset;

    }

    /**
     * Creates default properties to the location the application is run from.
     *
     */


    public void createDefaultProperties() {

        try (OutputStream outprop = new FileOutputStream("config.properties")) {

            properties.setProperty("CourseHandler", "courselist.bin");
            properties.setProperty("Settings", "settings.bin");
            properties.setProperty("setTest","settTest.bin");
            properties.setProperty("chTest","chTest.bin");
            courseHandlerURL = "courselist.bin";
            settingsURL = "settings.bin";
            properties.store(outprop, null);

        } catch (Exception e) {

        }

    }

    /**
     * saves current state of properties
     *
     * @throws IOException Exception is thrown if writing has been unsuccessful
     */

    public void saveProperties() throws IOException {

        try (OutputStream outprop = new FileOutputStream("config.properties")) {

            properties.store(outprop, null);

        }
    }

    /**
     * Loads the properties first from the file location, secondly loads the default properties set at the application resources
     *
     * @return returns true if loading was successful, else false.
     */


    public int loadProp() {

        try (InputStream propLocation = new FileInputStream("config.properties")) {

            properties.load(propLocation);
            setURLS("CourseHandler", "Settings");
            return 1;
        } catch (Exception e) {

            try (InputStream propL = InformationHandler.class.getClassLoader().getResourceAsStream("config.properties")) {

                properties.load(propL);
                setURLS("CourseHandler", "Settings");
                return 2;
            } catch (Exception s) {
                return 0;
            }

        }

    }

    /**
     * sets the location for the given parameters
     *
     * @param courseH Location of courselist.bin
     * @param settings Location of settings.bin
     */

    public void setURLS(String courseH, String settings) {
        settingsURL = properties.getProperty(settings);
        courseHandlerURL = properties.getProperty(courseH);


    }


    /**
     * Writes the given on object to disk with the name of the given parameter.
     * @param fileUrl Location and name of the object.
     * @param obj  CourseHandler or Settings object.
     * @return returns true or false based on successful implementation
     */


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

    /**
     * Loads an object based on the given url.
     * @param fileUrl Location of file and name of the file.
     * @return returns an object.
     * @throws IOException if  loading is successful it will throw this exception.
     * @throws ClassNotFoundException if the loaded file is not an object it will throw this exception
     */

    public Object fileReaderInput(String fileUrl) throws IOException, ClassNotFoundException {

        BufferedInputStream cR = new BufferedInputStream(new FileInputStream(fileUrl));
        ObjectInputStream closeMe = new ObjectInputStream(cR);
        Object sendAway = closeMe.readObject();
        closeMe.close();


        return sendAway;


    }

    /***
     * Creates a new CourseHandler objects and writes to disk as file.
     * @return returns true or false based on success
     */

    public boolean createCourseList() {


        CourseHandler cH = new CourseHandler();

        return fileReaderOutput(courseHandlerURL, cH);

    }
    /***
     * saves given CourseHandler object and writes to disk as file.
     *
     * @param courseHandler CourseHandler object
     * @return returns true or false based on success
     *
     */

    public boolean saveCourseHandler(CourseHandler courseHandler) {

        return fileReaderOutput(courseHandlerURL, courseHandler);


    }
    /***
     * Loads a CourseHandler object from the location set through properties
     *
     * @see InformationHandler#setURLS(String, String)
     * @see InformationHandler#courseHandlerURL
     *
     * @return returns true or false based on success
     */

    public CourseHandler courseLoader() {

        try {

            return (CourseHandler) fileReaderInput(courseHandlerURL);


        } catch (ClassNotFoundException | IOException e) {


        }

        return null;
    }

    /***
     * Creates a new settings objects and writes to disk as file.
     * @return returns true or false based on success
     */


    public boolean createSettings() {

        Settings settings = new Settings();

        return this.saveSettings(settings);


    }

    /***
     * saves given Settings object and writes to disk as file.
     *
     * @param saveSetting Settings object
     * @return returns true or false based on success
     *
     */

    public boolean saveSettings(Settings saveSetting) {

        return fileReaderOutput(settingsURL, saveSetting);


    }

    /***
     * Loads a Settings object from the location set through properties
     *
     * @see InformationHandler#setURLS(String, String)
     * @see InformationHandler#settingsURL
     *
     * @return returns true or false based on success
     */

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
