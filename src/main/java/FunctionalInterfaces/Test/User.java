package FunctionalInterfaces.Test;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class User {
    private String name;
    private String role;
    private String password;
    private LocalDateTime createdAt;

    public User(UserRegister u){
        this.setName(u.getName());
        this.setPassword(u.getPassword());
        this.setRole("MANAGER");
        this.setCreatedAt(LocalDateTime.now());
    }
}
