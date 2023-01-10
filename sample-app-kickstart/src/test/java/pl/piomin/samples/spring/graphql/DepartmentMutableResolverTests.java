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
public class DepartmentMutableResolverTests {

    @Autowired
    GraphQLTestTemplate template;

    @Test
    void newDepartment() throws IOException {
        Department department = template.postForResource("newDepartment.graphql")
                .get("$.data.newDepartment", Department.class);
        Assertions.assertNotNull(department);
        Assertions.assertNotNull(department.getId());
    }
}
