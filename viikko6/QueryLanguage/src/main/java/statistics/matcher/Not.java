package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {

    private Matcher other;

    public Not(Matcher other) {
        this.other = other;
    }

    @Override
    public boolean matches(Player p) {
        return !other.matches(p);
    }

}
