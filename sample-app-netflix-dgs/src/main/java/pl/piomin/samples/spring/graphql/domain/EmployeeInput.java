package pl.piomin.samples.spring.graphql.domain;

import java.util.Objects;

public class EmployeeInput {
    private String firstName;
    private String lastName;
    private String position;
    private Integer salary;
    private Integer age;
    private Integer departmentId;
    private Integer organizationId;

    public EmployeeInput() {
    }

    public EmployeeInput(String firstName, String lastName, String position, Integer salary, Integer age, 
                        Integer departmentId, Integer organizationId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.age = age;
        this.departmentId = departmentId;
        this.organizationId = organizationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInput that = (EmployeeInput) o;
        return Objects.equals(salary, that.salary) &&
               Objects.equals(age, that.age) &&
               Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(position, that.position) &&
               Objects.equals(departmentId, that.departmentId) &&
               Objects.equals(organizationId, that.organizationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, position, salary, age, departmentId, organizationId);
    }

    @Override
    public String toString() {
        return "EmployeeInput{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", position='" + position + '\'' +
               ", salary=" + salary +
               ", age=" + age +
               ", departmentId=" + departmentId +
               ", organizationId=" + organizationId +
               '}';
    }
}
