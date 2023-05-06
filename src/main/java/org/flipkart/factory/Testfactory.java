package org.flipkart.factory;

import org.flipkart.dao.Store;
import org.flipkart.domain.RestApiTest;
import org.testng.annotations.DataProvider;

import java.util.List;

public class Testfactory {
    @DataProvider(name="RestApiData")
    public Object[] fetchData(){
        Store store = Store.getInstance();
        List<RestApiTest> allData = store.findAllStudentsWithJpql();
        int size = allData.size();
        Object[] data = allData.toArray(new Object[size]);
        return data;
    }
}
