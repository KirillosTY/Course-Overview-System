package InformationCollector;

import Controls.Course;
import Controls.CourseHandler;

import java.io.*;
import java.util.ArrayList;

public class InformationCatcher {

    private CourseHandler CH;

    private ArrayList<Course> courselist;

    public InformationCatcher() {


    }

    public void Loader() throws Exception{
        try (FileInputStream OI = new FileInputStream("CourseInfo/courselist.bin")) {

            ObjectInputStream OIS = new ObjectInputStream(OI);

            CH = (CourseHandler) OIS.readObject();



        }catch (FileNotFoundException e){
            createCourseList();
        }
    }



    public boolean createCourseList() throws Exception {

        try (FileOutputStream CW = new FileOutputStream("CourseInfo/courselist.bin")) {

            ObjectOutputStream OS = new ObjectOutputStream(CW);

            CH = new CourseHandler(courselist,"Write something",0);

            OS.writeObject(CH);

        } catch (Exception e){

            return false;
        }
        return true;
    }

    public CourseHandler getCH() {
        return CH;
    }

    public void setCH(CourseHandler CH) {
        this.CH = CH;
    }

    public ArrayList<Course> getCourselist() {
        return courselist;
    }

    public void setCourselist(ArrayList<Course> courselist) {
        this.courselist = courselist;
    }



}
