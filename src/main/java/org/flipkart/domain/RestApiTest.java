package org.flipkart.domain;


import jakarta.persistence.*;
import org.codehaus.plexus.classworlds.strategy.Strategy;

@Entity
@Table(name = "testing1",uniqueConstraints = {
        @UniqueConstraint(columnNames = "testId")
})

public class RestApiTest  {

    private int testId;
    private String base_url;
    private String headers;

    private String query_params;

    private String output;

    private String service;

    private String test_case_name;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @Column(name = "service",unique = true,nullable = true,length = 2000)
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Column(name = "test_name",unique = false,nullable = true,length = 2000)
    public String getTest_case_name() {
        return test_case_name;
    }

    public void setTest_case_name(String test_case_name) {
        this.test_case_name = test_case_name;
    }

    @Override
    public String toString() {
        return "RestApiTest{" +
                "testId=" + testId +
                ", base_url='" + base_url + '\'' +
                ", headers='" + headers + '\'' +
                ", query_params='" + query_params + '\'' +
                ", output='" + output + '\'' +
                ", service='" + service + '\'' +
                ", test_case_name='" + test_case_name + '\'' +
                '}';
    }
}
