package Informationtests;


import InformationCollector.InformationCatcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InformationCatcherTest {

    private InformationCatcher tester;

    @Before
    public void initialize(){
        tester = new InformationCatcher();
    }


    @Test
    public void creatingFile() throws Exception{

        assertTrue(tester.createCourseList());

    }



}
