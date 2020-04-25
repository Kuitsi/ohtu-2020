package ohtu.kivipaperisakset;

public class Paaohjelma {

    public static void main(String[] args) {
        while (true) {
            KPSPeli peli = KPSPeli.luoUusiPeli();
            if (peli == null) break;
            peli.pelaa();
        }

    }
}
