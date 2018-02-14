package controllers.v1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guice.GuiceModule;
import model.Customer;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.CustomerSorter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CustomersEndPoint extends Controller {

    @Inject
    public CustomersEndPoint(@Named(GuiceModule.JODA_OBJECT_MAPPER) ObjectMapper objectMapper) {
        Json.setObjectMapper(objectMapper);
    }

    public Result sortByDueTime() {
        JsonNode customerJsonArray = request().body().asJson();
        List<Customer> customerList = new ArrayList<>();
        customerJsonArray.forEach(customer -> customerList.add(Json.fromJson(customer, Customer.class)));

        List<Customer> sortedCustomers = CustomerSorter.sortByDueTime(customerList);

        return ok(Json.prettyPrint(Json.toJson(sortedCustomers)));
    }
}
