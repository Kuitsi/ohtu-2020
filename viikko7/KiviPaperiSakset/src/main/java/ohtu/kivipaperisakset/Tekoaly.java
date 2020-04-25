package ohtu.kivipaperisakset;

public class Tekoaly implements AI {

    int siirto;

    public Tekoaly() {
        siirto = 0;
    }

    @Override
    public String annaSiirto() {
        siirto++;
        siirto = siirto % 3;

        if (siirto == 0) {
            return "k";
        } else if (siirto == 1) {
            return "p";
        } else {
            return "s";
        }
    }

    @Override
    public void asetaSiirto(String pelaajan1AiempiSiirto) {
        // ei tehdä mitään, noudatetaan samaa logiikkaa koko ajan
    }
}
