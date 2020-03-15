
package ohtu.verkkokauppa;

import java.util.ArrayList;

public class KirjanpitoImpl implements Kirjanpito {
    private static KirjanpitoImpl instance;

    public static KirjanpitoImpl getInstance() {
        if ( instance==null) {
            instance = new KirjanpitoImpl();
        }

        return instance;
    }

    private ArrayList<String> tapahtumat;

    private KirjanpitoImpl() {
        tapahtumat = new ArrayList<String>();
    }

    @Override
    public void lisaaTapahtuma(String tapahtuma) {
        tapahtumat.add(tapahtuma);
    }

    @Override
    public ArrayList<String> getTapahtumat() {
        return tapahtumat;
    }
}
