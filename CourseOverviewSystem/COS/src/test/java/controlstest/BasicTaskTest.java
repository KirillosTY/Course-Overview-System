package controlstest;

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

        assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.LONG,FormatStyle.MEDIUM))+"\nAdding more\n\n", tester.buildNotes());

    }

    @Test
    public void stringReturn(){


        assertEquals("Why - 168 Hours left",tester.toString());

    }

    @Test
    public void nameIsLong(){

        tester.setName("OHJELMISTOT");

        assertEquals("OHJELMISTO... - 168 Hours left",tester.toString());
    }

    @Test
    public void isCompletedString(){

        tester.getWorkHoursSpent().setEndDate(LocalDateTime.now().minusDays(2));
        tester.setName("Ohje");

        assertEquals("Ohje - Deadline ended: " + LocalDateTime.now().minusDays(2).toLocalDate(),tester.toString());
    }


    @Test
    public void isCompletedStringLong(){

        tester.getWorkHoursSpent().setEndDate(LocalDateTime.now().minusDays(2));
        tester.setName("Ohjelmistote");

        assertEquals("Ohjelmisto... - Deadline ended: " + LocalDateTime.now().minusDays(2).toLocalDate(),tester.toString());
    }
}
