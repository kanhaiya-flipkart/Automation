package org.flipkart.domain;

public class RestApiTest {
    private String base_url
            ;
    private String headers;

    private String query_params;

    private String output;

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getQuery_params() {
        return query_params;
    }

    public void setQuery_params(String query_params) {
        this.query_params = query_params;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "RestApiTest{" +
                "base_url='" + base_url + '\'' +
                ", headers='" + headers + '\'' +
                ", query_params='" + query_params + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
