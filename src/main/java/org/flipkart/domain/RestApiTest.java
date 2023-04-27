package org.flipkart.domain;


import jakarta.persistence.*;
import org.codehaus.plexus.classworlds.strategy.Strategy;

@Entity
@Table(name = "testing",uniqueConstraints = {
        @UniqueConstraint(columnNames = "testId")
})

public class RestApiTest  {

    private int testId;
    private String base_url;
    private String headers;

    private String query_params;

    private String output;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="testID",unique = true,nullable = false)
    public int getTestId(){
        return testId;
    }

    public void setTestId(int testId){
        this.testId = testId;
    }

    @Column(name = "baseUrl",unique = false,nullable = false)
    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    @Column(name = "header",unique = false,nullable = true,length = 500)
    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    @Column(name = "queryParam",unique = false,nullable = true,length = 500)
    public String getQuery_params() {
        return query_params;
    }

    public void setQuery_params(String query_params) {
        this.query_params = query_params;
    }

    @Column(name = "output",unique = false,nullable = true,length = 2000)
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
