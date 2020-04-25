package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPSPeli {

    private Tekoaly tekoaly;

    public KPSTekoaly(IO io) {
        super(io);
        this.tekoaly = new Tekoaly();
    }

    @Override
    protected String pelaajan2siirto() {
        String siirto = tekoaly.annaSiirto();
        io.tulosta("Tietokone valitsi: " + siirto);
        return siirto;
    }
}