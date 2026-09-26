package pl.piomin.samples.spring.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.test.tester.ExecutionGraphQlServiceTester;
import org.springframework.graphql.test.tester.GraphQlTester;
import pl.piomin.samples.spring.graphql.domain.Department;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DepartmentQueryResolverTests {

    @Autowired
    private ExecutionGraphQlService graphQlService;

    private GraphQlTester tester() {
        return ExecutionGraphQlServiceTester.create(graphQlService);
    }

    @Test
    void departments() {
        String query = "{ departments { id name } }";
        List<Department> departments = tester().document(query)
                .execute()
                .path("data.departments[*]")
                .entityList(Department.class)
                .get();
        Assertions.assertNotNull(departments);
        Assertions.assertTrue(departments.size() > 0);
    }

    @Test
    void departmentById() {
        String query = "{ department(id: 1) { id name } }";
        Department department = tester().document(query)
                .execute()
                .path("data.department")
                .entity(Department.class)
                .get();
        Assertions.assertNotNull(department);
        Assertions.assertNotNull(department.getId());
    }

}
