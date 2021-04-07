package pl.piomin.samples.spring.graphql.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import pl.piomin.samples.spring.graphql.domain.Organization;
import pl.piomin.samples.spring.graphql.repository.OrganizationRepository;

@Component
public class OrganizationQueryResolver implements GraphQLQueryResolver {

	private OrganizationRepository repository;

	OrganizationQueryResolver(OrganizationRepository repository) {
		this.repository = repository;
	}

	public Iterable<Organization> organizations() {
		return repository.findAll();
	}

	public Organization organization(Integer id) {
		return repository.findById(id).orElseThrow();
	}
}
