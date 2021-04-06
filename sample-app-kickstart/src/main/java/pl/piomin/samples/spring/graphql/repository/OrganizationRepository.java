package pl.piomin.samples.spring.graphql.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.samples.spring.graphql.domain.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
}
