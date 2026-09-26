package pl.piomin.samples.spring.graphql.resolver;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import pl.piomin.samples.spring.graphql.domain.Employee;
import pl.piomin.samples.spring.graphql.filter.EmployeeFilter;
import pl.piomin.samples.spring.graphql.filter.FilterField;
import pl.piomin.samples.spring.graphql.repository.EmployeeRepository;

@Controller
public class EmployeeQueryResolver {

	private EmployeeRepository repository;

	EmployeeQueryResolver(EmployeeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public Iterable<Employee> employees() {
		return repository.findAll();
	}

	@QueryMapping
	public Employee employee(@Argument Integer id) {
		return repository.findById(id).get();
	}

	@QueryMapping
	public Iterable<Employee> employeesWithFilter(@Argument EmployeeFilter filter) {
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
