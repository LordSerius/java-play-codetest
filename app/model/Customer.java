package model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;
import serializers.JodaDateTimeSerializer;

import java.util.Objects;

public class Customer {
    private long id;
    private String name;
    @JsonSerialize(using = JodaDateTimeSerializer.class)
    private DateTime duetime;
    @JsonSerialize(using = JodaDateTimeSerializer.class)
    private DateTime jointime;

    public Customer() {}

    public Customer(long id, String name, DateTime dueTime, DateTime joinTime) {
        this.id = id;
        this.name = name;
        this.duetime = dueTime;
        this.jointime = joinTime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateTime getDuetime() {
        return duetime;
    }

    public DateTime getJointime() {
        return jointime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(duetime, customer.duetime) &&
                Objects.equals(jointime, customer.jointime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duetime, jointime);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duetime=" + duetime +
                ", jointime=" + jointime +
                '}';
    }
}
