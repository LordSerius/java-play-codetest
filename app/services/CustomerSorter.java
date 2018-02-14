package services;

import model.Customer;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerSorter {
    public static List<Customer> sortByDueTime(List<Customer> customerList) {
        return customerList.stream()
                .sorted(Comparator.comparingLong(customer -> customer.getDuetime().getMillis()))
                .collect(Collectors.toList());
    }
}
