package sew.ai.api.pojos.billing.applepay;

public class AppleTokenGraphQLQuery {
    private String query;
    private Object variables;

    public AppleTokenGraphQLQuery() {}

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Object getVariables() {
        return variables;
    }

    public void setVariables(Object variables) {
        this.variables = variables;
    }
}
