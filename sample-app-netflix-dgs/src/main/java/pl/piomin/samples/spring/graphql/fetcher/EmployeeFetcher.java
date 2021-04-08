package pl.piomin.samples.spring.graphql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import graphql.execution.DataFetcherResult;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.jpa.domain.Specification;
import pl.piomin.samples.spring.graphql.context.EmployeeContext;
import pl.piomin.samples.spring.graphql.context.EmployeeContextBuilder;
import pl.piomin.samples.spring.graphql.domain.Employee;
import pl.piomin.samples.spring.graphql.filter.EmployeeFilter;
import pl.piomin.samples.spring.graphql.filter.FilterField;
import pl.piomin.samples.spring.graphql.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@DgsComponent
public class EmployeeFetcher {

    private EmployeeRepository repository;
    private EmployeeContextBuilder contextBuilder;

    public EmployeeFetcher(EmployeeRepository repository, EmployeeContextBuilder contextBuilder) {
        this.repository = repository;
        this.contextBuilder = contextBuilder;
    }

    @DgsData(parentType = "QueryResolver", field = "employees")
    public List<Employee> findAll(DataFetchingEnvironment dfe) {
        List<Employee> employees = (List<Employee>) repository.findAll();
        contextBuilder.withEmployees(employees).build();
        return employees;
    }

    @DgsData(parentType = "QueryResolver", field = "employee")
    public Employee findById(@InputArgument("id") Integer id, DataFetchingEnvironment dfe) {
        EmployeeContext employeeContext = DgsContext.getCustomContext(dfe);
        List<Employee> employees = employeeContext.getEmployees();
        Optional<Employee> employeeOpt = employees.stream().filter(employee -> employee.getId().equals(id))
                .findFirst();
        return employeeOpt.orElseGet(() -> repository.findById(id).orElseThrow(DgsEntityNotFoundException::new));
    }

    @DgsData(parentType = "QueryResolver", field = "employeesWithFilter")
    public Iterable<Employee> findWithFilter(@InputArgument("filter") EmployeeFilter filter) {
        Specification<Employee> spec = null;
        if (filter.getSalary() != null)
            spec = bySalary(filter.getSalary());
        if (filter.getAge() != null)
            spec = (spec == null ? byAge(filter.getAge()) : spec.and(byAge(filter.getAge())));
        if (filter.getPosition() != null)
            spec = (spec == null ? byPosition(filter.getPosition()) :
                    spec.and(byPosition(filter.getPosition())));
        if (spec != null)
            return repository.findAll(spec);
        else
            return repository.findAll();
    }

    private Specification<Employee> bySalary(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("salary"));
    }

    private Specification<Employee> byAge(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("age"));
    }

    private Specification<Employee> byPosition(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("position"));
    }
}
