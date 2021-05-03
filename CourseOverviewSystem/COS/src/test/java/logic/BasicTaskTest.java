package logic;

import cos.controls.BasicTask;
import cos.controls.WorkHourCounter;
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
        testCounter.setStartDate(LocalDateTime.now());
        testCounter.setEndDate(LocalDateTime.now().plusDays(7).plusMinutes(10));

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


        assertEquals("Why - Hours left: 168",tester.toString());

    }

    @Test
    public void nameIsLong(){

        tester.setName("OHJELMISTOT");

        assertEquals("OHJELMISTO... - Hours left: 168",tester.toString());
    }

}
