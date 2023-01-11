package pl.piomin.samples.spring.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Department;
import pl.piomin.samples.spring.graphql.domain.Organization;

@SpringBootTest
public class OrganizationMutationTests {

    @Autowired
    DgsQueryExecutor executor;

    @Test
    void addOrganization() {
        String query = "mutation { newOrganization(organization: { name: \"Test6\"}) { id } }";
        Organization organization = executor
                .executeAndExtractJsonPathAsObject(query, "data.newOrganization", Organization.class);
        Assertions.assertNotNull(organization);
        Assertions.assertNotNull(organization.getId());
    }
}
