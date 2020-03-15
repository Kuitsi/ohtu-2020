package ohtu.verkkokauppa;

public class ViitegeneraattoriImpl implements Viitegeneraattori {

    private static ViitegeneraattoriImpl instanssi;

    public static ViitegeneraattoriImpl getInstance() {
        if (instanssi == null) {
            instanssi = new ViitegeneraattoriImpl();
        }

        return instanssi;
    }

    private int seuraava;

    private ViitegeneraattoriImpl(){
        seuraava = 1;
    }

    @Override
    public int uusi(){
        return seuraava++;
    }
}
