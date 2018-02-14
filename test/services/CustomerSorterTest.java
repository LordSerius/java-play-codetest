package services;

import model.Customer;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CustomerSorterTest {
    private final Customer CUSTOMER_1 = new Customer(1, "batman",
            new DateTime(2012, 1, 12, 16, 0, 0, DateTimeZone.forID("America/Vancouver")),
            new DateTime(2006, 1, 1, 0, 0, 0, DateTimeZone.forID("America/Vancouver")));
    private final Customer CUSTOMER_2 = new Customer(1, "superman",
            new DateTime(2012, 1, 12, 8, 0, 0, DateTimeZone.forID("America/Vancouver")),
            new DateTime(2006, 1, 1, 0, 0, 0, DateTimeZone.forID("America/Vancouver")));
    private final Customer CUSTOMER_3 = new Customer(1, "ironman",
            new DateTime(2012, 1, 12, 20, 0, 0, DateTimeZone.forID("UTC")),
            new DateTime(2006, 1, 1, 0, 0, 0, DateTimeZone.forID("UTC")));

    @Test
    public void testWithEmptyList() {
        List<Customer> EMPTY_LIST = Lists.emptyList();

        List<Customer> sortedList = CustomerSorter.sortByDueTime(EMPTY_LIST);

        assertThat(sortedList).isEqualTo(EMPTY_LIST);
    }

    @Test
    public void testWithOneElementList() {
        List<Customer> ONE_ELEMENT_LIST = Lists.newArrayList(CUSTOMER_1);

        List<Customer> sortedList = CustomerSorter.sortByDueTime(ONE_ELEMENT_LIST);

        assertThat(sortedList).isEqualTo(ONE_ELEMENT_LIST);
    }

    @Test
    public void testWithSortedTwoElementList() {
        List<Customer> TWO_ELEMENT_LIST = Lists.newArrayList(CUSTOMER_2, CUSTOMER_1);

        List<Customer> sortedList = CustomerSorter.sortByDueTime(TWO_ELEMENT_LIST);

        assertThat(sortedList).isEqualTo(TWO_ELEMENT_LIST);
    }

    @Test
    public void testWithUnsortedTwoElementList() {
        List<Customer> TWO_ELEMENT_LIST = Lists.newArrayList(CUSTOMER_1, CUSTOMER_2);
        List<Customer> SORTED_TWO_ELEMENT_LIST = Lists.newArrayList(CUSTOMER_2, CUSTOMER_1);

        List<Customer> sortedList = CustomerSorter.sortByDueTime(TWO_ELEMENT_LIST);

        assertThat(sortedList).isEqualTo(SORTED_TWO_ELEMENT_LIST);
    }

    @Test
    public void testSortTimeZonesCorrectly() {
        List<Customer> TWO_ELEMENT_LIST = Lists.newArrayList(CUSTOMER_1, CUSTOMER_2, CUSTOMER_3);
        List<Customer> SORTED_TWO_ELEMENT_LIST = Lists.newArrayList(CUSTOMER_2, CUSTOMER_3, CUSTOMER_1);

        List<Customer> sortedList = CustomerSorter.sortByDueTime(TWO_ELEMENT_LIST);

        assertThat(sortedList).isEqualTo(SORTED_TWO_ELEMENT_LIST);
    }
}