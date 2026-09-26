package pl.piomin.samples.spring.graphql.resolver;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import pl.piomin.samples.spring.graphql.domain.Organization;
import pl.piomin.samples.spring.graphql.repository.OrganizationRepository;

@Controller
public class OrganizationQueryResolver {

	private OrganizationRepository repository;

	OrganizationQueryResolver(OrganizationRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public Iterable<Organization> organizations() {
		return repository.findAll();
	}

	@QueryMapping
	public Organization organization(@Argument Integer id) {
		return repository.findById(id).orElseThrow();
	}
}
