package pl.piomin.samples.spring.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.test.tester.ExecutionGraphQlServiceTester;
import org.springframework.graphql.test.tester.GraphQlTester;
import pl.piomin.samples.spring.graphql.domain.Organization;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OrganizationQueryResolverTests {

    @Autowired
    private ExecutionGraphQlService graphQlService;

    private GraphQlTester tester() {
        return ExecutionGraphQlServiceTester.create(graphQlService);
    }

    @Test
    void organizations() {
        String query = "{ organizations { id name } }";
        List<Organization> organizations = tester().document(query)
                .execute()
                .path("data.organizations[*]")
                .entityList(Organization.class)
                .get();
        Assertions.assertNotNull(organizations);
        Assertions.assertTrue(organizations.size() > 0);
    }

    @Test
    void organizationById() {
        String query = "{ organization(id: 1) { id name } }";
        Organization organization = tester().document(query)
                .execute()
                .path("data.organization")
                .entity(Organization.class)
                .get();
        Assertions.assertNotNull(organization);
        Assertions.assertNotNull(organization.getId());
    }

}
