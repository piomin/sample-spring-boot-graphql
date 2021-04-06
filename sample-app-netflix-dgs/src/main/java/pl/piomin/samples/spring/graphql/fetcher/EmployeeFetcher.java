package pl.piomin.samples.spring.graphql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import pl.piomin.samples.spring.graphql.domain.Employee;
import pl.piomin.samples.spring.graphql.repository.EmployeeRepository;

@DgsComponent
public class EmployeeFetcher {

    private EmployeeRepository repository;

    public EmployeeFetcher(EmployeeRepository repository) {
        this.repository = repository;
    }

    @DgsData(parentType = "QueryResolver", field = "employee")
    public Employee findById(@InputArgument("id") Integer id) {
        return repository.findById(id).orElseThrow();
    }

}
