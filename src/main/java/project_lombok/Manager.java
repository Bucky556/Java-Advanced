package project_lombok;

import lombok.ToString;

@ToString(callSuper = true)
public class Manager extends User{
    private final String role;

    public Manager(Integer id, String username, String email, String role) {
        super(id, username, email);
        this.role = role;
    }

}
