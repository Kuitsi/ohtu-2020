package ohtu.kivipaperisakset;

public class Paaohjelma {

    public static void main(String[] args) {
        IO io = new KonsoliIO();

        while (true) {
            io.tulosta("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = io.seuraava();
            if (vastaus.endsWith("a")) {
                io.tulosta("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KPSPelaajaVsPelaaja kaksinpeli = new KPSPelaajaVsPelaaja(io);
                kaksinpeli.pelaa();
            } else if (vastaus.endsWith("b")) {
                io.tulosta("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KPSPeli yksinpeli = new KPSTekoaly(io);
                yksinpeli.pelaa();
            } else if (vastaus.endsWith("c")) {
                io.tulosta("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                KPSParempiTekoaly pahaYksinpeli = new KPSParempiTekoaly(io);
                pahaYksinpeli.pelaa();
            } else {
                break;
            }

        }

    }
}
