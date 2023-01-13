package pl.piomin.sample.spring.graphql.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import pl.piomin.sample.spring.graphql.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>,
		JpaSpecificationExecutor<Employee> {
}
