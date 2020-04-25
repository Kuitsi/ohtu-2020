package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja {

    private final IO io;

    public KPSPelaajaVsPelaaja(IO io) {
        this.io = io;
    }

    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        io.tulosta("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = io.seuraava();
        io.tulosta("Toisen pelaajan siirto: ");
        String tokanSiirto = io.seuraava();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.tulosta(tuomari);
            io.tulosta("");

            io.tulosta("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = io.seuraava();
            
            io.tulosta("Toisen pelaajan siirto: ");
            tokanSiirto = io.seuraava();
        }

        io.tulosta("");
        io.tulosta("Kiitos!");
        io.tulosta(tuomari);
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}