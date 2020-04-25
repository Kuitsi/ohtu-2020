package ohtu.kivipaperisakset;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly {

    private final IO io;

    public KPSParempiTekoaly(IO io) {
        this.io = io;
    }

    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        TekoalyParannettu tekoaly = new TekoalyParannettu(20);

        io.tulosta("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = io.seuraava();
        String tokanSiirto;


        tokanSiirto = tekoaly.annaSiirto();
        io.tulosta("Tietokone valitsi: " + tokanSiirto);


        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.tulosta(tuomari);
            io.tulosta("");

            io.tulosta("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = io.seuraava();

            tokanSiirto = tekoaly.annaSiirto();
            io.tulosta("Tietokone valitsi: " + tokanSiirto);
            tekoaly.asetaSiirto(ekanSiirto);

        }

        io.tulosta("");
        io.tulosta("Kiitos!");
        io.tulosta(tuomari);
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
