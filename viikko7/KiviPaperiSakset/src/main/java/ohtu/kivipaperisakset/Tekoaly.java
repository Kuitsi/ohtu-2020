package ohtu.kivipaperisakset;

public class Tekoaly implements AI {

    int siirto;

    public Tekoaly() {
        siirto = 0;
    }

    @Override
    public String seuraavaSiirto() {
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
    public void havainnoiSiirto(String vastustajanAiempiSiirto) {
        // ei tehdä mitään, noudatetaan samaa logiikkaa koko ajan
    }
}
