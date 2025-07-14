package pl.piomin.samples.spring.graphql.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import java.util.Objects;

public class FilterField {
    private String operator;
    private String value;

    public FilterField() {
    }

    public FilterField(String operator, String value) {
        this.operator = operator;
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Predicate generateCriteria(CriteriaBuilder builder, Path field) {
        try {
            int v = Integer.parseInt(value);
            switch (operator) {
                case "lt": return builder.lt(field, v);
                case "le": return builder.le(field, v);
                case "gt": return builder.gt(field, v);
                case "ge": return builder.ge(field, v);
                case "eq": return builder.equal(field, v);
            }
        } catch (NumberFormatException e) {
            switch (operator) {
                case "endsWith": return builder.like(field, "%" + value);
                case "startsWith": return builder.like(field, value + "%");
                case "contains": return builder.like(field, "%" + value + "%");
                case "eq": return builder.equal(field, value);
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterField that = (FilterField) o;
        return Objects.equals(operator, that.operator) &&
               Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operator, value);
    }

    @Override
    public String toString() {
        return "FilterField{" +
               "operator='" + operator + '\'' +
               ", value='" + value + '\'' +
               '}';
    }
}
