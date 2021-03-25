package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(10000);
    }

    @Test
    public void alkuSetti() {

        assertTrue(kassa.kassassaRahaa()==100000);

    }

    @Test
    public void alkuRaha() {

        assertTrue(this.kassa.kassassaRahaa()== 100000);
    }

    @Test
    public void lounasNolla() {

        assertTrue(kassa.maukkaitaLounaitaMyyty()+kassa.edullisiaLounaitaMyyty()==0);
    }

    @Test
    public void kateisOstoEduPos() {

        kassa.syoEdullisesti(240);
        assertTrue(kassa.kassassaRahaa()==100240);
    }

    @Test
    public void kateisOstoEduNeg() {

        kassa.syoEdullisesti(239);
        assertTrue(kassa.kassassaRahaa()==100000);
    }

    @Test
    public void kateisOstoMauPos() {

        kassa.syoMaukkaasti(400);
        assertTrue(kassa.kassassaRahaa()== 100400);
    }

    @Test
    public void kateisOstoMauNeg() {

        kassa.syoMaukkaasti(399);
        assertTrue(kassa.kassassaRahaa()==100000);
    }

    @Test
    public void kateisOstoEduPosMaara() {

        kassa.syoEdullisesti(240);
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
    }

    @Test
    public void kateisOstoEduNegMaara() {

        kassa.syoEdullisesti(239);
        assertTrue(kassa.edullisiaLounaitaMyyty()==0);
    }

    @Test
    public void kateisOstoMauPosMaara() {

        kassa.syoMaukkaasti(400);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
    }

    @Test
    public void kateisOstoMauNegMaara() {

        kassa.syoMaukkaasti(399);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==0);
    }


    @Test
    public void kateisOstoEduPosPalautus() {


        assertTrue(kassa.syoEdullisesti(241)==1);
    }

    @Test
    public void kateisOstoEduNegPalautus() {


        assertTrue( kassa.syoEdullisesti(239) == 239);
    }

    @Test
    public void kateisOstoMauPosPalautus() {


        assertTrue(kassa.syoMaukkaasti(403) == 3);
    }

    @Test
    public void kateisOstoMauNegPalautus() {

        assertTrue(kassa.syoMaukkaasti(399) == 399);
    }


    @Test
    public void korttiOstEduPos() {

        assertTrue(kassa.syoEdullisesti(kortti));


    }

    @Test
    public void korttiOstEduNeg() {

        assertFalse(kassa.syoEdullisesti(new Maksukortti(100)));

    }

    @Test
    public void korttiOstMauPos() {

        assertTrue(kassa.syoMaukkaasti(kortti));

    }

    @Test
    public void korttiOstMauNeg() {

        assertFalse(kassa.syoMaukkaasti(new Maksukortti(100)));
    }

    @Test
    public void korttiOsteduPosMaara() {

        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);

    }

    @Test
    public void korttiOstEduNegMaara() {

        kassa.syoEdullisesti(new Maksukortti(10));
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);

    }

    @Test
    public void korttiOstMauPosMaara() {

        kassa.syoMaukkaasti(kortti);

        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }

    @Test
    public void korttiOstMauNegMaara() {

        kassa.syoMaukkaasti(new Maksukortti(10));

        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);

    }

    @Test
    public void korttiOsteduPosKassa() {

        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.kassassaRahaa() == 100000);

    }

    @Test
    public void korttiOstEduNegKassa() {

        kassa.syoEdullisesti(new Maksukortti(10));
        assertTrue(kassa.kassassaRahaa() == 100000);

    }

    @Test
    public void korttiOstMauPosKassa() {

        kassa.syoMaukkaasti(kortti);

        assertTrue(kassa.kassassaRahaa() == 100000);
    }

    @Test
    public void korttiOstMauNegKassa() {

        kassa.syoMaukkaasti(new Maksukortti(10));

        assertTrue(kassa.kassassaRahaa() == 100000);

    }

    @Test
    public void lataaRahaaKortille() {

        kassa.lataaRahaaKortille(kortti, 100);
        assertTrue(kortti.saldo()==10100);
    }

    @Test
    public void lataaRahaaKortilleKassa() {

        kassa.lataaRahaaKortille(kortti, 200);
        assertTrue(kassa.kassassaRahaa() ==  100200);
    }


    @Test
    public void lataaRahaaKortilleNeg() {

        kassa.lataaRahaaKortille(kortti, -100);
        assertTrue(kortti.saldo()==10000);
    }

    @Test
    public void lataaRahaaKortilleKassaNeg() {

        kassa.lataaRahaaKortille(kortti, -200);
        assertTrue(kassa.kassassaRahaa() ==  100000);
    }

}
