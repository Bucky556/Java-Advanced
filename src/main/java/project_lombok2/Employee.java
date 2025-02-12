package project_lombok2;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Builder
@ToString
public class Employee {
    private String fullName;
    private int age;

}
