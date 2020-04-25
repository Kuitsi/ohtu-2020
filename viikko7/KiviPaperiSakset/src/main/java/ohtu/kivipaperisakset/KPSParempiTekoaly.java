package ohtu.kivipaperisakset;

public class KPSParempiTekoaly extends KPSPeli {

    private TekoalyParannettu tekoaly;

    public KPSParempiTekoaly(IO io) {
        super(io);
        this.tekoaly = new TekoalyParannettu(20);
    }

    @Override
    protected String pelaajan2siirto() {
        String siirto = tekoaly.annaSiirto();
        io.tulosta("Tietokone valitsi: " + siirto);
        return siirto;
    }
}
