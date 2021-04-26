package logic;

import Controls.BasicTask;
import Controls.WorkHourCounter;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static org.junit.Assert.assertEquals;

public class BasicTaskTest {

    private BasicTask tester;

    @Before
    public void initialize(){

        WorkHourCounter testCounter = new WorkHourCounter();


        tester = new BasicTask(false, testCounter,"Why","not","",0);
    }

    @Test
    public void savingNotes(){

        tester.saveNotesWithStamp("Adding more");

        String time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.LONG,FormatStyle.MEDIUM));

        assertEquals(tester.getNotes(),time+":\n\n"+"Adding more");


    }

    @Test
    public void stringReturn(){


        assertEquals("Why - not",tester.toString());

    }


}
