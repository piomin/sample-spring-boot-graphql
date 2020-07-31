package pl.piomin.samples.spring.graphql.repository;

import pl.piomin.samples.spring.graphql.domain.Department;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer>,
		JpaSpecificationExecutor<Department> {

}
