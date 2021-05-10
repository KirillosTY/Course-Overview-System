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
        propertiesSetup();

        if(informationHandler.loadSettings() == null){
            informationHandler.createSettings();
           settings = informationHandler.loadSettings();
        }

        if(informationHandler.courseLoader() == null){
            informationHandler.createCourseList();

        }
        settings = informationHandler.loadSettings();
        courseHandler = informationHandler.courseLoader();
        try {
            if (!(courseHandler.getCourseList().isEmpty() && courseHandler.getUpcomingCourse().isEmpty())) {
                courseHandler.courseDateUpdater();
            }

            launch(UiMainStart.class);
        } catch (Exception e){
            launch(UiMainStart.class);

        }



    }

    private static void propertiesSetup(){
        if(!informationHandler.loadProp()){
            informationHandler.createDefaultProperties();
            informationHandler.loadProp();
        }

        informationHandler.setURLS("CourseHandler","Settings");
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

    public static void setCourseHandler(CourseHandler ch){
        courseHandler = ch;
    }
}
