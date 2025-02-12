package project_lombok2;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@ToString(callSuper = true)
public class Manager extends Employee {
    private String role;

    @Builder(builderMethodName = "childBuilder")
    public Manager(String fullName, int age, String role) {
        super(fullName, age);
        this.role = role;
    }
}
