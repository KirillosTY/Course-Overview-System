package Controls;

import InformationCollector.InformationHandler;
import courseoverviewsystem.cos.UiMainStart;

import static javafx.application.Application.launch;

public class MainController {

    private static InformationHandler informationHandler;

    private static CourseHandler courseHandler;

    private static Settings settings;

    public static void main(String[] args)  {


        informationHandler = new InformationHandler();



        if(informationHandler.loadSettings()==null){

            informationHandler.createCourseList();
            informationHandler.createSettings();

        }

        courseHandler = informationHandler.courseLoader();
        settings = informationHandler.loadSettings();

        launch(UiMainStart.class);





    }

    public static InformationHandler getInformationHandler(){
        return  informationHandler;
    }


    public static CourseHandler getCourseHandler() {
        return courseHandler;
    }

    public static Settings getSettings() {
        return settings;
    }
}
