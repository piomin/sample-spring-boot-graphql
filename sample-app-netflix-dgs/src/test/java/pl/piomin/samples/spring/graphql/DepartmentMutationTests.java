package pl.piomin.samples.spring.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Department;
import pl.piomin.samples.spring.graphql.domain.Employee;

@SpringBootTest
public class DepartmentMutationTests {

    @Autowired
    DgsQueryExecutor executor;

    @Test
    void addDepartment() {
        String query = "mutation { newDepartment(department: { name: \"Test10\" organizationId: 1}) { id } }";
        Department department = executor
                .executeAndExtractJsonPathAsObject(query, "data.newDepartment", Department.class);
        Assertions.assertNotNull(department);
        Assertions.assertNotNull(department.getId());
    }
}
