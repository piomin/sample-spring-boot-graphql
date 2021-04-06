package pl.piomin.samples.spring.graphql.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import pl.piomin.samples.spring.graphql.domain.Organization;
import pl.piomin.samples.spring.graphql.domain.OrganizationInput;
import pl.piomin.samples.spring.graphql.repository.OrganizationRepository;

@Component
public class OrganizationMutableResolver implements GraphQLMutationResolver {

	OrganizationRepository repository;

	OrganizationMutableResolver(OrganizationRepository repository) {
		this.repository = repository;
	}

	public Organization newOrganization(OrganizationInput organizationInput) {
		return repository.save(new Organization(null, organizationInput.getName(), null, null));
	}

}
