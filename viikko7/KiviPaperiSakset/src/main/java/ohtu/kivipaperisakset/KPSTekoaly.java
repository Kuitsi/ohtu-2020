package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPSPeli {

    private AI tekoaly;

    public KPSTekoaly(IO io, AI logiikka) {
        super(io);
        this.tekoaly = logiikka;
    }

    @Override
    protected String pelaajan2siirto(String vastustajanAiempiSiirto) {
        tekoaly.havainnoiSiirto(vastustajanAiempiSiirto);
        String siirto = tekoaly.seuraavaSiirto();
        io.tulosta("Tietokone valitsi: " + siirto);
        return siirto;
    }
}