package pl.piomin.samples.spring.graphql;

import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Employee;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeMutableResolverTests {

    @Autowired
    GraphQLTestTemplate template;

    @Test
    void newEmployee() throws IOException {
        Employee employee = template.postForResource("newEmployee.graphql")
                .get("$.data.newEmployee", Employee.class);
        Assertions.assertNotNull(employee);
        Assertions.assertNotNull(employee.getId());
    }
}
