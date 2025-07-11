package pl.piomin.samples.spring.graphql.domain;

import java.util.Objects;

public class OrganizationInput {
    private String name;

    public OrganizationInput() {
    }

    public OrganizationInput(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationInput that = (OrganizationInput) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "OrganizationInput{" +
               "name='" + name + '\'' +
               '}';
    }
}
