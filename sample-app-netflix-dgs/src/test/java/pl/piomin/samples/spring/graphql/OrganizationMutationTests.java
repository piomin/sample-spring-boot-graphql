package pl.piomin.samples.spring.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ExecutionGraphQlService;
import org.springframework.graphql.test.tester.ExecutionGraphQlServiceTester;
import org.springframework.graphql.test.tester.GraphQlTester;
import pl.piomin.samples.spring.graphql.domain.Organization;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OrganizationMutationTests {

    @Autowired
    private ExecutionGraphQlService graphQlService;

    private GraphQlTester tester() {
        return ExecutionGraphQlServiceTester.create(graphQlService);
    }

    @Test
    void addOrganization() {
        String query = "mutation { newOrganization(organization: { name: \"Test6\"}) { id } }";
        Organization organization = tester().document(query)
                .execute()
                .path("data.newOrganization")
                .entity(Organization.class)
                .get();
        Assertions.assertNotNull(organization);
        Assertions.assertNotNull(organization.getId());
    }
}
