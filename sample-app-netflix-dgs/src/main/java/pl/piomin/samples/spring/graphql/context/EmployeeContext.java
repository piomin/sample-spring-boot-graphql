package pl.piomin.samples.spring.graphql.context;

import pl.piomin.samples.spring.graphql.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeContext {
    private List<Employee> employees;

    public EmployeeContext() {
        this.employees = new ArrayList<>();
    }

    public EmployeeContext(List<Employee> employees) {
        this.employees = employees != null ? new ArrayList<>(employees) : new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees != null ? new ArrayList<>(employees) : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeContext that = (EmployeeContext) o;
        return Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees);
    }

    @Override
    public String toString() {
        return "EmployeeContext{" +
               "employees=" + employees +
               '}';
    }
}
