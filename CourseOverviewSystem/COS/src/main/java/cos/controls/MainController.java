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

        if (!informationHandler.checkForProp()) {
            System.out.println("käydään");
            informationHandler.createProperties();
            System.out.println(informationHandler.getProperties().get("CourseHandler"));
            System.out.println(informationHandler.createCourseList());
            System.out.println(informationHandler.createSettings());

        } else {
            informationHandler.loadProperties();
        }
        courseHandler = informationHandler.courseLoader();
        settings = informationHandler.loadSettings();

        if (!(courseHandler.getCourseList().isEmpty() && courseHandler.getUpcomingCourse().isEmpty())) {
            courseHandler.courseDateUpdater();
        }

        launch(UiMainStart.class);


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
