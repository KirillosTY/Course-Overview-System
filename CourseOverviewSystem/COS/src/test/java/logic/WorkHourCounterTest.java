package logic;

import Controls.WorkHourCounter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorkHourCounterTest {

    private WorkHourCounter whc;



    @Before
    public void initialize(){

        whc = new WorkHourCounter();
    }


    @Test
    public void whcSetupParameters(){

        whc = new WorkHourCounter(10000L);

        assertEquals(10000, whc.getFullSeconds());
    }

    @Test
    public void counterCheck(){

        whc.counter(1000L);

        assertEquals(1000, whc.getFullSeconds());

    }

    @Test
    public void counterString(){

        whc.counter(4827L);

        assertEquals("27:20:01",whc.timeToString());

    }

    @Test
    public void setAppliesCount(){

        whc.setCurrentCount(4827L);
        assertEquals("27:20:01",whc.timeToString());

    }


}
