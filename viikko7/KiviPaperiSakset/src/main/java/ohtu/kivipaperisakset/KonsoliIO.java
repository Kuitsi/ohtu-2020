package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KonsoliIO implements IO {

    private final Scanner lukija;

    public KonsoliIO() {
        lukija = new Scanner(System.in);
    }

    @Override
    public String seuraava() {
        return lukija.nextLine();
    }

    @Override
    public void tulosta(Object o) {
        System.out.println(o);
    }

}
