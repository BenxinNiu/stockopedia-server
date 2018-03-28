package MUN.Factory;
public class QueryForm{
    private String query;

    private String type;

    public QueryForm(String query, String type) {
        this.query = query;
        this.type = type;
    }

    public QueryForm(){}

    public String getType() { return type; }

    public String getQuery() { return query; }
}
