package pl.piomin.samples.spring.graphql.context;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilder;
import org.springframework.stereotype.Component;
import pl.piomin.samples.spring.graphql.domain.Employee;

import java.util.List;

@Component
public class EmployeeContextBuilder implements DgsCustomContextBuilder<EmployeeContext> {

    private List<Employee> employees;

    public EmployeeContextBuilder withEmployees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }

    @Override
    public EmployeeContext build() {
        return new EmployeeContext(employees);
    }
}
