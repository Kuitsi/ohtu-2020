package ohtu.verkkokauppa;

public class PankkiImpl implements Pankki {

    private static PankkiImpl instanssi;

    public static PankkiImpl getInstance() {
        if (instanssi == null) {
            instanssi = new PankkiImpl();
        }

        return instanssi;
    }
    private KirjanpitoImpl kirjanpito;

    public PankkiImpl() {
        kirjanpito = KirjanpitoImpl.getInstance();
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
