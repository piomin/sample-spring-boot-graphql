package pl.piomin.sample.spring.graphql.resolver;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import pl.piomin.sample.spring.graphql.domain.Department;
import pl.piomin.sample.spring.graphql.domain.Employee;
import pl.piomin.sample.spring.graphql.domain.Organization;
import pl.piomin.sample.spring.graphql.domain.OrganizationInput;
import pl.piomin.sample.spring.graphql.repository.OrganizationRepository;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Controller
public class OrganizationController {

    OrganizationRepository repository;

    OrganizationController(OrganizationRepository repository) {
        this.repository = repository;
    }

    @MutationMapping
    public Organization newOrganization(@Argument OrganizationInput organization) {
        return repository.save(new Organization(null, organization.getName(), null, null));
    }

    @QueryMapping
    public Iterable<Organization> organizations() {
        return repository.findAll();
    }

    @QueryMapping
    public Organization organization(@Argument Integer id, DataFetchingEnvironment environment) {
        Specification<Organization> spec = byId(id);
        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();
        if (selectionSet.contains("employees"))
            spec = spec.and(fetchEmployees());
        if (selectionSet.contains("departments"))
            spec = spec.and(fetchDepartments());
        return repository.findOne(spec).orElseThrow();
    }

    private Specification<Organization> fetchDepartments() {
        return (root, query, builder) -> {
            Fetch<Organization, Department> f = root.fetch("departments", JoinType.LEFT);
            Join<Organization, Department> join = (Join<Organization, Department>) f;
            return join.getOn();
        };
    }

    private Specification<Organization> fetchEmployees() {
        return (root, query, builder) -> {
            Fetch<Organization, Employee> f = root.fetch("employees", JoinType.LEFT);
            Join<Organization, Employee> join = (Join<Organization, Employee>) f;
            return join.getOn();
        };
    }

    private Specification<Organization> byId(Integer id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }
}
