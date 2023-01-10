package pl.piomin.samples.spring.graphql;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Employee;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeQueryResolverTests {

    @Autowired
    GraphQLTestTemplate template;

    @Test
    void employees() throws IOException {
        Employee[] employees = template.postForResource("employees.graphql")
                .get("$.data.employees", Employee[].class);
        Assertions.assertTrue(employees.length > 0);
    }

    @Test
    void employeeById() throws IOException {
        Employee employee = template.postForResource("employeeById.graphql")
                .get("$.data.employee", Employee.class);
        Assertions.assertNotNull(employee);
        Assertions.assertNotNull(employee.getId());
    }

    @Test
    void employeesWithFilter() throws IOException {
        Employee[] employees = template.postForResource("employeesWithFilter.graphql")
                .get("$.data.employeesWithFilter", Employee[].class);
        Assertions.assertEquals(4, employees.length);
    }
}
