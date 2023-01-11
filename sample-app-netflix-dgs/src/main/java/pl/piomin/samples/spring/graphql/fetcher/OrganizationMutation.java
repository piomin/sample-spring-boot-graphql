package pl.piomin.samples.spring.graphql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import pl.piomin.samples.spring.graphql.domain.Organization;
import pl.piomin.samples.spring.graphql.domain.OrganizationInput;
import pl.piomin.samples.spring.graphql.repository.OrganizationRepository;

@DgsComponent
public class OrganizationMutation {

    OrganizationRepository repository;

    OrganizationMutation(OrganizationRepository repository) {
        this.repository = repository;
    }

    @DgsData(parentType = "MutationResolver", field = "newOrganization")
    public Organization newOrganization(@InputArgument("organization") OrganizationInput organizationInput) {
        return repository.save(new Organization(null, organizationInput.getName(), null, null));
    }

}
