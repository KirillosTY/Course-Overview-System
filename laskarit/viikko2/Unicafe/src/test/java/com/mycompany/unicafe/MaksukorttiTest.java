package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(15);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void alkuSaldo() {

        assertTrue(kortti.saldo() == 15);
    }

    @Test
    public void lataaminen() {
        kortti.lataaRahaa(10);
        assertTrue(kortti.saldo() == 25);
    }

    @Test
    public void ottaminen() {

        kortti.otaRahaa(3);
        assertTrue(kortti.saldo()==12);

    }

    @Test
    public void ottaminenNegaatiivinen() {

        kortti.otaRahaa(30);
        assertTrue(kortti.saldo()==15);

    }

    @Test
    public void booleanPalautus() {

        assertTrue(kortti.otaRahaa(10));
    }

    @Test
    public void booleanPalautusNega() {

        assertFalse(kortti.otaRahaa(100));
    }

    @Test
    public void toStringTest() {
        assertEquals("saldo: "+0+"."+15, kortti.toString());

    }
}