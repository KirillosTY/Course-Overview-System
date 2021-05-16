package cos.controls;

import cos.informationprocessing.InformationHandler;
import cos.ui.UiMainStart;

import static javafx.application.Application.launch;

/**
 * This class starts the application by loading necessary file using the InformationHandler class.
 */

public class MainController {

    private static InformationHandler informationHandler;

    private static CourseHandler courseHandler;

    private static Settings settings;

    private static String[] popupText;

    public static void main(String[] args) {
        informationHandler = new InformationHandler();
        propertiesSetup();
        courSettCreateIfNull();
        settings = informationHandler.loadSettings();
        courseHandler = informationHandler.courseLoader();
        try {
            if (!(courseHandler.getCourseList().isEmpty() && courseHandler.getUpcomingCourse().isEmpty())) {
                courseHandler.courseDateUpdater();
            }

            launch(UiMainStart.class);
        } catch (Exception e) {
            launch(UiMainStart.class);

        }
    }

    private static void courSettCreateIfNull() {
        if (informationHandler.loadSettings() == null) {
            informationHandler.createSettings();
        }

        if (informationHandler.courseLoader() == null) {
            informationHandler.createCourseList();

        }
    }

    private static void propertiesSetup() {
        if (informationHandler.loadProp() == 0) {
            informationHandler.createDefaultProperties();
            informationHandler.loadProp();
        }

        informationHandler.setURLS("CourseHandler", "Settings");
    }

    public static InformationHandler getInformationHandler() {
        return informationHandler;
    }


    public static CourseHandler getCourseHandler() {
        return courseHandler;
    }

    public static void setCourseHandler(CourseHandler ch) {
        courseHandler = ch;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static String[] getPopupText() {
        return popupText;

    }

    public static void setPopupText(String[] pops) {

        popupText = pops;

    }
}
