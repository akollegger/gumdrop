package org.akollegger.gumdrop;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GumdropPost {

    public GumdropPost() {

    }

    public Content postQuery(String query) throws IOException {
        return Request.Post("http://localhost:7474/db/data/cypher")
                .bodyString("{ \"query\" : \"" + query + "\" }",
                        ContentType.APPLICATION_JSON)
                .addHeader("Accept", ContentType.TEXT_PLAIN.toString())
                .execute().returnContent();
    }

    public List<String> postQueries(InputStream is) throws IOException {
        return postQueries(new InputStreamReader(is));
    }

    public List<String> postQueries(Reader queryReader) throws IOException {
        BufferedReader bufferedQueries = new BufferedReader(queryReader);
        String line;
        List<String> results = new ArrayList<String>();
        StringBuilder currentQuery = new StringBuilder();
        while ((line=bufferedQueries.readLine())!=null) {
            if (!isComment(line)) {
                currentQuery.append(line);
                if (line.contains(";")) {
                    try {
                        results.add(postQuery(prepareString(currentQuery.toString())).asString());
                    } catch (IOException e) {
                        results.add("Failed to post \"" + currentQuery + "\", because " + e.getLocalizedMessage());
                    }
                    currentQuery = new StringBuilder();
                }
            }
        }
        return results;
    }

    private boolean isComment(String line) {
        return line.startsWith("//") || line.matches("\\s*");
    }

    private String prepareString(String query) {
        return query.replace(";", "").replaceAll("\"", "\\\\\"").trim();
    }
}