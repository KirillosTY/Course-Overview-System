package cos.controls;

import cos.informationprocessing.InformationHandler;
import cos.ui.UiMainStart;

import static javafx.application.Application.launch;

public class MainController {

    private static InformationHandler informationHandler;

    private static CourseHandler courseHandler;

    private static Settings settings;

    private static String[] popupText;

    public static void main(String[] args) {


        informationHandler = new InformationHandler();


        if (informationHandler.loadSettings() == null) {

            if (informationHandler.createCourseList()) {


                if (informationHandler.createSettings()) {
                    courseHandler = informationHandler.courseLoader();
                    settings = informationHandler.loadSettings();

                    courseHandler.courseDateUpdater();

                    launch(UiMainStart.class);

                }
            }


        } else {

            courseHandler = informationHandler.courseLoader();
            settings = informationHandler.loadSettings();

            courseHandler.courseDateUpdater();

            launch(UiMainStart.class);
        }


    }

    public static InformationHandler getInformationHandler() {
        return informationHandler;
    }


    public static CourseHandler getCourseHandler() {
        return courseHandler;
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
