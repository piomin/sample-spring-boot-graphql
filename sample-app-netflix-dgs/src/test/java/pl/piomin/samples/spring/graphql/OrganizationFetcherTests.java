package pl.piomin.samples.spring.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Organization;

@SpringBootTest
public class OrganizationFetcherTests {

    @Autowired
    DgsQueryExecutor executor;

    @Test
    void findAll() {
        String query = "{ organizations { id name } }";
        Organization[] organizations = executor
                .executeAndExtractJsonPathAsObject(query, "data.organizations[*]", Organization[].class);
        Assertions.assertTrue(organizations.length > 0);
        Assertions.assertNotNull(organizations[0].getId());
        Assertions.assertNotNull(organizations[0].getName());
    }

//    @Test
    void findById() {
        String query = "{ organization(id: 1) { id name departments { id } } }";
        Organization organization = executor
                .executeAndExtractJsonPathAsObject(query, "data.organization", Organization.class);
        Assertions.assertNotNull(organization);
        Assertions.assertNotNull(organization.getId());
        Assertions.assertTrue(organization.getDepartments().size() > 0);
    }
    
}
