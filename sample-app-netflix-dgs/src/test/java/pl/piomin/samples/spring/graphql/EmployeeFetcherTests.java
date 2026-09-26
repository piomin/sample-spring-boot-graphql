package pl.piomin.samples.spring.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.test.tester.ExecutionGraphQlServiceTester;
import org.springframework.graphql.test.tester.GraphQlTester;
import pl.piomin.samples.spring.graphql.domain.Employee;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EmployeeFetcherTests {

    @Autowired
    private ExecutionGraphQlService graphQlService;

    private GraphQlTester tester() {
        return ExecutionGraphQlServiceTester.create(graphQlService);
    }

    @Test
    void findAll() {
        String query = "{ employees { id firstName lastName salary } }";
        List<Employee> employees = tester().document(query)
                .execute()
                .path("data.employees[*]")
                .entityList(Employee.class)
                .get();
        Assertions.assertNotNull(employees);
        Assertions.assertTrue(employees.size() > 0);
        Assertions.assertNotNull(employees.get(0).getId());
        Assertions.assertNotNull(employees.get(0).getFirstName());
    }

    @Test
    void findById() {
        String query = "{ employee(id: 1) { id firstName lastName salary } }";
        Employee employee = tester().document(query)
                .execute()
                .path("data.employee")
                .entity(Employee.class)
                .get();
        Assertions.assertNotNull(employee);
        Assertions.assertNotNull(employee.getId());
        Assertions.assertNotNull(employee.getFirstName());
    }

    @Test
    void findWithFilter() {
        String query = "{ employeesWithFilter(filter: { salary: { operator: \"gt\" value: \"12000\" } }) { id firstName lastName salary } }";
        List<Employee> employees = tester().document(query)
                .execute()
                .path("data.employeesWithFilter[*]")
                .entityList(Employee.class)
                .get();
        Assertions.assertNotNull(employees);
        Assertions.assertTrue(employees.size() > 0);
        Assertions.assertNotNull(employees.get(0).getId());
        Assertions.assertNotNull(employees.get(0).getFirstName());
    }
}
