package ohtu.intjoukkosovellus;

import org.junit.Before;

public class IntJoukkoYksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    @Override
    public void setUp() {
        joukko = new IntJoukko(3);
        joukko.lisaa(10);
        joukko.lisaa(3);
    }
    
    // perii kaikki testit luokasta IntJoukkoTest
}
