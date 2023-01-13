package pl.piomin.sample.spring.graphql.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import pl.piomin.sample.spring.graphql.domain.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>,
		JpaSpecificationExecutor<Department> {

}
