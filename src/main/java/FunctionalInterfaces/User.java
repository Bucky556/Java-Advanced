package FunctionalInterfaces;

import lombok.*;

import java.time.*;

@Data
public class User {
    private String userName;
    private String role;
    private String password;
    private LocalDateTime createdAt;

    public User(UserRegisterDTO dto) {
        this.setUserName(dto.getUserName());
        this.setPassword(dto.getPassword());
        this.setRole("ADMIN");
        this.setCreatedAt(LocalDateTime.now());
    }
}
