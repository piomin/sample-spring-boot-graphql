package pl.piomin.samples.spring.graphql.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.piomin.samples.spring.graphql.domain.Department;
import pl.piomin.samples.spring.graphql.domain.Employee;
import pl.piomin.samples.spring.graphql.domain.Organization;
import pl.piomin.samples.spring.graphql.repository.DepartmentRepository;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class DepartmentQueryResolver implements GraphQLQueryResolver {

	private DepartmentRepository repository;

	DepartmentQueryResolver(DepartmentRepository repository) {
		this.repository = repository;
	}

	public Iterable<Department> departments(DataFetchingEnvironment environment) {
		DataFetchingFieldSelectionSet s = environment.getSelectionSet();
		List<Specification<Department>> specifications = new ArrayList<>();
		if (s.contains("employees") && !s.contains("organization"))
			return repository.findAll(fetchEmployees());
		else if (!s.contains("employees") && s.contains("organization"))
			return repository.findAll(fetchOrganization());
		else if (s.contains("employees") && s.contains("organization"))
			return repository.findAll(fetchEmployees().and(fetchOrganization()));
		else
			return repository.findAll();
	}

	public Department department(Integer id, DataFetchingEnvironment environment) {
		Specification<Department> spec = byId(id);
		DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();
		if (selectionSet.contains("employees"))
			spec = spec.and(fetchEmployees());
		if (selectionSet.contains("organization"))
			spec = spec.and(fetchOrganization());
		return repository.findOne(spec).orElseThrow(NoSuchElementException::new);
	}

	private Specification<Department> fetchOrganization() {
		return (Specification<Department>) (root, query, builder) -> {
			Fetch<Department, Organization> f = root.fetch("organization", JoinType.LEFT);
			Join<Department, Organization> join = (Join<Department, Organization>) f;
			return join.getOn();
		};
	}

	private Specification<Department> fetchEmployees() {
		return (Specification<Department>) (root, query, builder) -> {
			Fetch<Department, Employee> f = root.fetch("employees", JoinType.LEFT);
			Join<Department, Employee> join = (Join<Department, Employee>) f;
			return join.getOn();
		};
	}

	private Specification<Department> byId(Integer id) {
		return (Specification<Department>) (root, query, builder) -> builder.equal(root.get("id"), id);
	}
}
