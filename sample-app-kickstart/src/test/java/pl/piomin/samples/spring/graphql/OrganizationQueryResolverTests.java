package pl.piomin.samples.spring.graphql;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Organization;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationQueryResolverTests {

    @Autowired
    GraphQLTestTemplate template;

    @Test
    void organizations() throws IOException {
        Organization[] organizations = template.postForResource("organizations.graphql")
                .get("$.data.organizations", Organization[].class);
        Assertions.assertTrue(organizations.length > 0);
    }

    @Test
    void organizationById() throws IOException {
        Organization organization = template.postForResource("organizationById.graphql")
                .get("$.data.organization", Organization.class);
        Assertions.assertNotNull(organization);
        Assertions.assertNotNull(organization.getId());
    }

}
