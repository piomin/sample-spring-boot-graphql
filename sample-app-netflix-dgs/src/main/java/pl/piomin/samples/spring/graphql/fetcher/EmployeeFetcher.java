package pl.piomin.samples.spring.graphql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.data.jpa.domain.Specification;
import pl.piomin.samples.spring.graphql.domain.Employee;
import pl.piomin.samples.spring.graphql.filter.EmployeeFilter;
import pl.piomin.samples.spring.graphql.filter.FilterField;
import pl.piomin.samples.spring.graphql.repository.EmployeeRepository;

@DgsComponent
public class EmployeeFetcher {

    private EmployeeRepository repository;

    public EmployeeFetcher(EmployeeRepository repository) {
        this.repository = repository;
    }

    @DgsData(parentType = "EmployeeResolver", field = "employees")
    public Iterable<Employee> findAll() {
        return repository.findAll();
    }

    @DgsData(parentType = "EmployeeResolver", field = "employee")
    public Employee findById(@InputArgument("id") Integer id) {
        return repository.findById(id).orElseThrow();
    }

    @DgsData(parentType = "EmployeeResolver", field = "employeesWithFilter")
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
