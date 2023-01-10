package pl.piomin.samples.spring.graphql;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Department;
import pl.piomin.samples.spring.graphql.domain.Employee;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartmentQueryResolverTests {

    @Autowired
    GraphQLTestTemplate template;

    @Test
    void departments() throws IOException {
        Department[] departments = template.postForResource("departments.graphql")
                .get("$.data.departments", Department[].class);
        Assertions.assertTrue(departments.length > 0);
    }

    @Test
    void departmentById() throws IOException {
        Department department = template.postForResource("departmentById.graphql")
                .get("$.data.department", Department.class);
        Assertions.assertNotNull(department);
        Assertions.assertNotNull(department.getId());
    }

}
