package pl.piomin.samples.spring.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Department;

@SpringBootTest
public class DepartmentFetcherTests {

    @Autowired
    DgsQueryExecutor executor;

    @Test
    void findAll() {
        String query = "{ departments { id name } }";
        Department[] departments = executor
                .executeAndExtractJsonPathAsObject(query, "data.departments[*]", Department[].class);
        Assertions.assertTrue(departments.length > 0);
        Assertions.assertNotNull(departments[0].getId());
        Assertions.assertNotNull(departments[0].getName());
    }

    @Test
    void findById() {
        String query = "{ department(id: 1) { id name organization { id } } }";
        Department department = executor
                .executeAndExtractJsonPathAsObject(query, "data.department", Department.class);
        Assertions.assertNotNull(department);
        Assertions.assertNotNull(department.getId());
        Assertions.assertNotNull(department.getOrganization());
        Assertions.assertNotNull(department.getOrganization().getId());
    }
    
}
