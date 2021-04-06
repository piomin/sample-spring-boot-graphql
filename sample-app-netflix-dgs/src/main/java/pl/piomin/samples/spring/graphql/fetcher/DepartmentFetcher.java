package pl.piomin.samples.spring.graphql.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import pl.piomin.samples.spring.graphql.repository.DepartmentRepository;

@DgsComponent
public class DepartmentFetcher {

    private DepartmentRepository repository;

    DepartmentFetcher(DepartmentRepository repository) {
        this.repository = repository;
    }

}
