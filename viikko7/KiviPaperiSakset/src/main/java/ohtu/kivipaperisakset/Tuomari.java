package ohtu.kivipaperisakset;

/**
 * Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.
 */
public class Tuomari {

    private int ekanPisteet;
    private int tokanPisteet;
    private int tasapelit;

    public Tuomari() {
        this.ekanPisteet = 0;
        this.tokanPisteet = 0;
        this.tasapelit = 0;
    }

    /**
     * Kirjaa seuraavan siirron
     * @param ekanSiirto pelaajan 1 siirto
     * @param tokanSiirto pelaajan 2 siirto
     * @return true jos siirrot olivat kelvolliset ja ne kirjattiin, muuten false
     */
    public boolean kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        if (!(onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto))) {
            return false;
        }

        if (tasapeli(ekanSiirto, tokanSiirto)) {
            tasapelit++;
        } else if (ekaVoittaa(ekanSiirto, tokanSiirto)) {
            ekanPisteet++;
        } else {
            tokanPisteet++;
        }
        return true;
    }

    private boolean tasapeli(String ekanSiirto, String tokanSiirto) {
        return ekanSiirto.equals(tokanSiirto);
    }

    private boolean ekaVoittaa(String ekanSiirto, String tokanSiirto) {
        if ("k".equals(ekanSiirto) && "s".equals(tokanSiirto)) {
            return true;
        } else if ("s".equals(ekanSiirto) && "p".equals(tokanSiirto)) {
            return true;
        } else if ("p".equals(ekanSiirto) && "k".equals(tokanSiirto)) {
            return true;
        }
        return false;
    }

    private boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

    @Override
    public String toString() {
        return "Pelitilanne: " + ekanPisteet + " - " + tokanPisteet + "\n"
                + "Tasapelit: " + tasapelit;
    }
}