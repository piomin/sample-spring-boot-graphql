package pl.piomin.samples.spring.graphql.filter;

import java.util.Objects;

public class EmployeeFilter {
    private FilterField salary;
    private FilterField age;
    private FilterField position;

    public FilterField getSalary() {
        return salary;
    }

    public void setSalary(FilterField salary) {
        this.salary = salary;
    }

    public FilterField getAge() {
        return age;
    }

    public void setAge(FilterField age) {
        this.age = age;
    }

    public FilterField getPosition() {
        return position;
    }

    public void setPosition(FilterField position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeFilter that = (EmployeeFilter) o;
        return Objects.equals(salary, that.salary) &&
               Objects.equals(age, that.age) &&
               Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, age, position);
    }

    @Override
    public String toString() {
        return "EmployeeFilter{" +
               "salary=" + salary +
               ", age=" + age +
               ", position=" + position +
               '}';
    }
}
