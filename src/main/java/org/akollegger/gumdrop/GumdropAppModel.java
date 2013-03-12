package org.akollegger.gumdrop;

public class GumdropAppModel {
    private String currentQuery;
    private String cypherResult;
    private String cypherQuery;

    public GumdropAppModel() {
    }

    public String getCypherResult() {
        return cypherResult;
    }

    public void setCypherResult(final String cypherResult) {
        this.cypherResult = cypherResult;
    }

    public String getCypherQuery() {
        return cypherQuery;
    }

    public void setCypherQuery(final String cypherQuery) {
        this.cypherQuery = cypherQuery;
    }
}