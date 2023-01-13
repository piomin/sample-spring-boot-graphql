package pl.piomin.sample.spring.graphql.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import pl.piomin.sample.spring.graphql.domain.Department;
import pl.piomin.sample.spring.graphql.domain.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Integer>,
        JpaSpecificationExecutor<Organization> {
}
