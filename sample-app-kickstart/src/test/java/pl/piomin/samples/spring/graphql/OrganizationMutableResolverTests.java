package pl.piomin.samples.spring.graphql;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.samples.spring.graphql.domain.Organization;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationMutableResolverTests {

    @Autowired
    GraphQLTestTemplate template;

    @Test
    void newOrganization() throws IOException {
        Organization organization = template.postForResource("newOrganization.graphql")
                .get("$.data.newOrganization", Organization.class);
        Assertions.assertNotNull(organization);
        Assertions.assertNotNull(organization.getId());
    }
}
