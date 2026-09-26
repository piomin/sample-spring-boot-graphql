package pl.piomin.samples.spring.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.test.tester.ExecutionGraphQlServiceTester;
import org.springframework.graphql.test.tester.GraphQlTester;
import pl.piomin.samples.spring.graphql.domain.Employee;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EmployeeMutationTests {

    @Autowired
    private ExecutionGraphQlService graphQlService;

    private GraphQlTester tester() {
        return ExecutionGraphQlServiceTester.create(graphQlService);
    }

    @Test
    void addEmployee() {
        String query = "mutation { newEmployee(employee: { firstName: \"John\" lastName: \"Wick\" position: \"developer\" salary: 10000 age: 20 departmentId: 1 organizationId: 1}) { id } }";
        Employee employee = tester().document(query)
                .execute()
                .path("data.newEmployee")
                .entity(Employee.class)
                .get();
        Assertions.assertNotNull(employee);
        Assertions.assertNotNull(employee.getId());
    }
}
