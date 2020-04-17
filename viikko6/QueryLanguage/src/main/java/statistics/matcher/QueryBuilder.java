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

    public Matcher build() {
        return query;
    }

}
