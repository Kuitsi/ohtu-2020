package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KPSPeli {

    public KPSPelaajaVsPelaaja(IO io) {
        super(io);
    }

    @Override
    protected String pelaajan2siirto(String vastustajanAiempiSiirto) {
        io.tulosta("Toisen pelaajan siirto: ");
        return io.seuraava();
    }
}