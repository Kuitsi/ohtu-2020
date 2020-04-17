package statistics.matcher;

public class QueryBuilder {
    private Matcher query;

    public QueryBuilder() {
        query = new All();
    }

    public Matcher build() {
        return query;
    }

}
