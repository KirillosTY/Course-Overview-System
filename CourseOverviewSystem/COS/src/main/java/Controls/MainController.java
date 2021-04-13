package Controls;

import InformationCollector.InformationCatcher;
import courseoverviewsystem.cos.UiMainStart;

import static javafx.application.Application.launch;

public class MainController {

    public static void main(String[] args) throws Exception {

        InformationCatcher courseHSaver = new InformationCatcher();

        courseHSaver.Loader();

        CourseHandler startCH = courseHSaver.getCH();




        launch(UiMainStart.class);





    }
}
