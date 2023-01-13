package pl.piomin.sample.spring.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import pl.piomin.sample.spring.graphql.domain.Organization;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class OrganizationControllerTests {

    @Autowired
    private GraphQlTester tester;

    @Test
    void addOrganization() {
        String query = "mutation { newOrganization(organization: { name: \"Test10\" }) { id } }";
        Organization organization = tester.document(query)
                .execute()
                .path("data.newOrganization")
                .entity(Organization.class)
                .get();
        Assertions.assertNotNull(organization);
        Assertions.assertNotNull(organization.getId());
    }

    @Test
    void findAll() {
        String query = "{ organizations { id name } }";
        List<Organization> organizations = tester.document(query)
                .execute()
                .path("data.organizations[*]")
                .entityList(Organization.class)
                .get();
        Assertions.assertTrue(organizations.size() > 0);
        Assertions.assertNotNull(organizations.get(0).getId());
        Assertions.assertNotNull(organizations.get(0).getName());
    }

    @Test
    void findById() {
        String query = "{ organization(id: 1) { id name departments { id } } }";
        Organization organization = tester.document(query)
                .execute()
                .path("data.organization")
                .entity(Organization.class)
                .get();
        Assertions.assertNotNull(organization);
        Assertions.assertNotNull(organization.getId());
        Assertions.assertTrue(organization.getDepartments().size() > 0);
    }
    
}
