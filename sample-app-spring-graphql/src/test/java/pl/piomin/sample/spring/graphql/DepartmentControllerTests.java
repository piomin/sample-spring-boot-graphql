package pl.piomin.sample.spring.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import pl.piomin.sample.spring.graphql.domain.Department;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class DepartmentControllerTests {

    @Autowired
    private GraphQlTester tester;

    @Test
    void addDepartment() {
        String query = "mutation { newDepartment(department: { name: \"Test10\" organizationId: 1}) { id } }";
        Department department = tester.document(query)
                .execute()
                .path("data.newDepartment")
                .entity(Department.class)
                .get();
        Assertions.assertNotNull(department);
        Assertions.assertNotNull(department.getId());
    }

    @Test
    void findAll() {
        String query = "{ departments { id name } }";
        List<Department> departments = tester.document(query)
                .execute()
                .path("data.departments[*]")
                .entityList(Department.class)
                .get();
        Assertions.assertTrue(departments.size() > 0);
        Assertions.assertNotNull(departments.get(0).getId());
        Assertions.assertNotNull(departments.get(0).getName());
    }

    @Test
    void findById() {
        String query = "{ department(id: 1) { id name organization { id } } }";
        Department department = tester.document(query)
                .execute()
                .path("data.department")
                .entity(Department.class)
                .get();
        Assertions.assertNotNull(department);
        Assertions.assertNotNull(department.getId());
        Assertions.assertNotNull(department.getOrganization());
        Assertions.assertNotNull(department.getOrganization().getId());
    }
    
}
