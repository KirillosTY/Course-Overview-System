package logic;

import cos.controls.WorkHourCounter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorkHourCounterTest {

    private WorkHourCounter whc;



    @Before
    public void initialize(){

        whc = new WorkHourCounter();

        whc.setCurrentCount(3660L*100);
    }


    @Test
    public void whcSetupParameters(){

        whc = new WorkHourCounter(10000L);

        assertEquals(10000L,(long) whc.getCurrentCount());
    }

    @Test
    public void counterCheck(){

        whc.setCurrentCount(1000L);

        assertEquals(1000, (long) whc.getCurrentCount());

    }

    @Test
    public void counterString(){

        whc.setCurrentCount(4827L);

        assertEquals("01:20:27",whc.timeToString());

    }

    @Test
    public void setAppliesCount(){

        whc.setCurrentCount(4827L);
        assertEquals("01:20:27",whc.timeToString());

    }






}
