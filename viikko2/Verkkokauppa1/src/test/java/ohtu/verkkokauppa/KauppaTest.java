package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void kahdenEriTuotteenOstossaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        // tuote numero 2 on leipä jonka hinta on 6 ja saldo 10
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 6));

        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2); // leipä
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(11));
    }

    @Test
    public void kahdenSamanTuotteenOstossaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));
    }

    @Test
    public void kahdenEriTuotteenJoistaToinenOnLoppuOstossaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "jäätelö", 10));

        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(3); // jäätelö, joka on loppu varastosta
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikeilla arvoilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void metodinAloitaAsiointiAloittaaUudenSession() {
        when(varasto.saldo(4)).thenReturn(1);
        when(varasto.haeTuote(4)).thenReturn(new Tuote(4, "paisti", 15));

        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));

        // uusi tyhjä sessio edellisen asiakkaan jälkeen
        k.aloitaAsiointi();
        k.lisaaKoriin(4);
        k.tilimaksu("pertti", "13579");

        verify(pankki).tilisiirto(eq("pertti"), anyInt(), eq("13579"), anyString(), eq(15));
    }

    @Test
    public void kaytetaanPerakkaistenViitekutsujenArvoja() {
        // määritellään että metodi palauttaa ensimmäisellä kutsukerralla 1, toisella 2
        // ja kolmannella 3. Tämän pitäisi jyrätä setUp():ssa määritetyn arvon
        when(viite.uusi()).
                thenReturn(1).
                thenReturn(2).
                thenReturn(3);

        Kauppa k = new Kauppa(varasto, pankki, viite);

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("a", "111-111");
        // varmistetaan, että nyt käytössä ensimmäisenä pyydetty viite
        verify(pankki).tilisiirto(anyString(), eq(1), anyString(), anyString(), anyInt());

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("b", "222-222");
        // ... toisena pyydetty viite
        verify(pankki).tilisiirto(anyString(), eq(2), anyString(), anyString(), anyInt());

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("c", "333-333");
        // ... ja kolmantena pyydetty viite
        verify(pankki).tilisiirto(anyString(), eq(3), anyString(), anyString(), anyInt());

        // tarkistetaan vielä, että viitegeneraattorin metodia uusi() kutsuttu kolme kertaa
        verify(viite, times(3)).uusi();
    }

    @Test
    public void poistaKoristaPalauttaaVarastoon() {
        Kauppa k = new Kauppa(varasto, pankki, viite);
        int tuoteId = 1;
        k.aloitaAsiointi();
        k.lisaaKoriin(tuoteId);

        k.poistaKorista(tuoteId);

        Tuote t = varasto.haeTuote(tuoteId);
        verify(varasto, times(1)).palautaVarastoon(t);

        //TODO: olisi hyvä testata myös häviääkö tuote ostoskorista sekä kasvaako varaston saldo, mutta niille pitäisi keksiä sopiva testaustapa mockien kautta/ohi
    }
}
