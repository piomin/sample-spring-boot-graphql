package pl.piomin.samples.spring.graphql.resolver;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import pl.piomin.samples.spring.graphql.domain.Organization;
import pl.piomin.samples.spring.graphql.domain.OrganizationInput;
import pl.piomin.samples.spring.graphql.repository.OrganizationRepository;

@Controller
public class OrganizationMutableResolver {

	OrganizationRepository repository;

	OrganizationMutableResolver(OrganizationRepository repository) {
		this.repository = repository;
	}

	@MutationMapping
	public Organization newOrganization(@Argument OrganizationInput organization) {
		return repository.save(new Organization(null, organization.getName(), null, null));
	}

}
