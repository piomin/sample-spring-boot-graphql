package pl.piomin.samples.spring.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.test.tester.ExecutionGraphQlServiceTester;
import org.springframework.graphql.test.tester.GraphQlTester;
import pl.piomin.samples.spring.graphql.domain.Department;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class DepartmentMutationTests {

    @Autowired
    private ExecutionGraphQlService graphQlService;

    private GraphQlTester tester() {
        return ExecutionGraphQlServiceTester.create(graphQlService);
    }

    @Test
    void addDepartment() {
        String query = "mutation { newDepartment(department: { name: \"Test10\" organizationId: 1}) { id } }";
        Department department = tester().document(query)
                .execute()
                .path("data.newDepartment")
                .entity(Department.class)
                .get();
        Assertions.assertNotNull(department);
        Assertions.assertNotNull(department.getId());
    }
}
