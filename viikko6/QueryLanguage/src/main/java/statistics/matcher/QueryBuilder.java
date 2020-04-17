package statistics.matcher;

public class QueryBuilder {
    private Matcher query;

    public QueryBuilder() {
        query = new All();
    }

    public QueryBuilder playsIn(String team) {
        this.query = new And(query,  new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        this.query = new And(query,  new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        this.query = new And(query,  new HasFewerThan(value, category));
        return this;
    }

    public Matcher build() {
        return query;
    }

}
