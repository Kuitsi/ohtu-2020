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

    /**
     * Aloittaa pelin
     */
    public void pelaa() {
        io.tulosta("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
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

    /**
     * Kysyy pelattavan pelin tyypin ja valmistelelee sen aloitettavaksi {@link #pelaa} metodilla
     * @return uusi KPSPeli tai null mikäli valittu muuta kuin annetut vaihtoehdot
     */
    public static KPSPeli luoUusiPeli() {
        IO io = new KonsoliIO();

        io.tulosta("\nValitse pelataanko"
                + "\n (a) ihmistä vastaan"
                + "\n (b) tekoälyä vastaan"
                + "\n (c) parannettua tekoälyä vastaan"
                + "\nmuilla valinnoilla lopetetaan");

        String vastaus = io.seuraava();
        if (vastaus.endsWith("a")) {
            return new KPSPelaajaVsPelaaja(io);
        } else if (vastaus.endsWith("b")) {
            return new KPSTekoaly(io);
        } else if (vastaus.endsWith("c")) {;
            return new KPSParempiTekoaly(io);
        }
        return null;
    }
}
