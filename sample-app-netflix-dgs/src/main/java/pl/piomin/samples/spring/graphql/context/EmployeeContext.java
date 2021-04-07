package pl.piomin.samples.spring.graphql.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.piomin.samples.spring.graphql.domain.Employee;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeContext {
    private List<Employee> employees = new ArrayList<>();
}
