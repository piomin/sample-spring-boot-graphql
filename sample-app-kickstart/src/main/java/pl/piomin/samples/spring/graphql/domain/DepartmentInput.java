package pl.piomin.samples.spring.graphql.domain;

import java.util.Objects;

public class DepartmentInput {
    private String name;
    private Integer organizationId;

    public DepartmentInput() {
    }

    public DepartmentInput(String name, Integer organizationId) {
        this.name = name;
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        DepartmentInput that = (DepartmentInput) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(organizationId, that.organizationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, organizationId);
    }

    @Override
    public String toString() {
        return "DepartmentInput{" +
               "name='" + name + '\'' +
               ", organizationId=" + organizationId +
               '}';
    }
}
