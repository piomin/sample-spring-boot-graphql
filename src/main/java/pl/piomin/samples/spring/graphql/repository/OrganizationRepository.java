package pl.piomin.samples.spring.graphql.repository;

import pl.piomin.samples.spring.graphql.domain.Organization;

import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
}
