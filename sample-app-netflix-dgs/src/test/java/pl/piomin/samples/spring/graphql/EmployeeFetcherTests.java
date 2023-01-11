package pl.piomin.samples.spring.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Employee;

@SpringBootTest
public class EmployeeFetcherTests {

    @Autowired
    DgsQueryExecutor executor;

    @Test
    void findAll() {
        String query = "{ employees { id firstName lastName salary } }";
        Employee[] employees = executor
                .executeAndExtractJsonPathAsObject(query, "data.employees[*]", Employee[].class);
        Assertions.assertTrue(employees.length > 0);
        Assertions.assertNotNull(employees[0].getId());
        Assertions.assertNotNull(employees[0].getFirstName());
    }

    @Test
    void findById() {
        String query = "{ employee(id: 1) { id firstName lastName salary } }";
        Employee employee = executor
                .executeAndExtractJsonPathAsObject(query, "data.employee", Employee.class);
        Assertions.assertNotNull(employee);
        Assertions.assertNotNull(employee.getId());
        Assertions.assertNotNull(employee.getFirstName());
    }

    @Test
    void findWithFilter() {
        String query = "{ employeesWithFilter(filter: { salary: { operator: \"gt\" value: \"12000\" } }) { id firstName lastName salary } }";
        Employee[] employees = executor
                .executeAndExtractJsonPathAsObject(query, "data.employeesWithFilter[*]", Employee[].class);
        Assertions.assertTrue(employees.length > 0);
        Assertions.assertNotNull(employees[0].getId());
        Assertions.assertNotNull(employees[0].getFirstName());
    }
}
