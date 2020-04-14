package ohtu.intjoukkosovellus;

public class IntJoukko {
    /** aloitustaulukon koko */
    public final static int OLETUSKAPASITEETTI = 5;
    /** luotava uusi taulukko on näin paljon isompi kuin vanha */
    public final static int OLETUSKASVATUS = 5;
    /** Koko, jolla alkuioiden taulukkoa kasvatetaan tarvittaessa */
    private int kasvatuskoko;
    /** Taulukko joukon alkioista. Joukon luvut säilytetään taulukon alkupäässä. */
    private int[] joukonAlkiot;
    /** Montako alkiota {@code joukonAlkiot} oikeasti sisältää. Tyhjässä joukossa 0. */
    private int alkioidenLkm;

    public IntJoukko() {
        this(OLETUSKAPASITEETTI);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetin täytyy olla positiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoon täytyy olla positiivinen");
        }
        joukonAlkiot = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            joukonAlkiot[0] = luku;
            alkioidenLkm++;
            return true;
        }

        if (!kuuluu(luku)) {
            joukonAlkiot[alkioidenLkm] = luku; // lisätään uusi luku taulukon perään
            alkioidenLkm++;
            if (alkioidenLkm % joukonAlkiot.length == 0) {
                // ei mahtuisi enää seuraavaa lisäystä, joten kasvatetaan taulukkoa valmiiksi
                int[] uusiTaulukko = new int[alkioidenLkm + kasvatuskoko];
                System.arraycopy(joukonAlkiot, 0, uusiTaulukko, 0, alkioidenLkm);
                joukonAlkiot = uusiTaulukko;
            }
            return true;
        }
        return false;
    }

    /**
     * Selvittää, kuuluuko luku joukkoon.
     * @param luku
     * @return true jos kuuluu
     */
    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonAlkiot[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Poistaa joukosta luvun, jos se siellä sattui olemaan
     * @param luku
     * @return true, jos luku todellakin poistettiin, muuten false
     */
    public boolean poista(int luku) {
        int indeksi = -1;
        // nollataan löytynyt alkio
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonAlkiot[i]) {
                indeksi = i;
                joukonAlkiot[indeksi] = 0;
                break;
            }
        }
        // siirretään poiston jälkeen lopputaulukkoa pykälällä alkuun päin
        if (indeksi != -1) {
            for (int j = indeksi; j < alkioidenLkm - 1; j++) {
                int apu = joukonAlkiot[j];
                joukonAlkiot[j] = joukonAlkiot[j + 1];
                joukonAlkiot[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    public int mahtavuus() { // mielenkiintoinen nimivalinta, mutta on dokumentoitu kuuluvaksi julkiseen APIin
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < alkioidenLkm; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(joukonAlkiot[i]);
        }
        sb.append('}');
        return sb.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(joukonAlkiot, 0, taulu, 0, taulu.length);
        return taulu;
    }

    /**
     * Joukkojen yhdiste
     * @param a
     * @param b
     * @return joukko, joka sisältää kaikki a:n ja b:n alkiot
     */
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        for (int arvo : a.toIntArray()) {
            yhdiste.lisaa(arvo);
        }
        for (int arvo : b.toIntArray()) {
            yhdiste.lisaa(arvo);
        }
        return yhdiste;
    }

    /**
     * Joukkojen leikkaus
     * @param a
     * @param b
     * @return joukko, jonka kaikki alkiot kuuluvat sekä joukkoon a että joukkoon b
     */
    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        for (int aArvo : a.toIntArray()) {
            for (int bArvo : b.toIntArray()) {
                if (aArvo == bArvo) {
                    leikkaus.lisaa(bArvo);
                }
            }
        }
        return leikkaus;
    }

    /**
     * Joukkojen erotus
     * @param a
     * @param b
     * @return joukko, joka sisältää kaikki a:n alkiot, jotka eivät kuulu joukkoon b
     */
    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        for (int aArvo : a.toIntArray()) {
            erotus.lisaa(aArvo);
        }
        for (int bArvo : b.toIntArray()) {
            erotus.poista(bArvo);
        }
        return erotus;
    }

}
