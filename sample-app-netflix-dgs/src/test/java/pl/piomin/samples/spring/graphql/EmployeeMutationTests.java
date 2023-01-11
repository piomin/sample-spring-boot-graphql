package pl.piomin.samples.spring.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Employee;

@SpringBootTest
public class EmployeeMutationTests {

    @Autowired
    DgsQueryExecutor executor;

    @Test
    void addEmployee() {
        String query = "mutation { newEmployee(employee: { firstName: \"John\" lastName: \"Wick\" position: \"developer\" salary: 10000 age: 20 departmentId: 1 organizationId: 1}) { id } }";
        Employee employee = executor
                .executeAndExtractJsonPathAsObject(query, "data.newEmployee", Employee.class);
        Assertions.assertNotNull(employee);
        Assertions.assertNotNull(employee.getId());
    }
}
