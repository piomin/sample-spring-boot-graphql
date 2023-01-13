package pl.piomin.sample.spring.graphql.filter;

import lombok.Data;

@Data
public class EmployeeFilter {
	private FilterField salary;
	private FilterField age;
	private FilterField position;
}
