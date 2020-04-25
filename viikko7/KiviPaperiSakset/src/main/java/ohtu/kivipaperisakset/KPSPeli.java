package ohtu.kivipaperisakset;

public abstract class KPSPeli {

    protected IO io;
    private Tuomari tuomari;
    private String ekanSiirto;
    private String tokanSiirto;

    public KPSPeli(IO io) {
        this.io = io;
        this.tuomari = new Tuomari();
    }

    public void pelaa() {
        teeSiirrot();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.tulosta(tuomari);
            io.tulosta("");

            teeSiirrot();
        }

        io.tulosta("");
        io.tulosta("Kiitos!");
        io.tulosta(tuomari);
    }

    private void teeSiirrot() {
        io.tulosta("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = io.seuraava();

        tokanSiirto = pelaajan2siirto();
    }

    private boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    protected abstract String pelaajan2siirto();
}
