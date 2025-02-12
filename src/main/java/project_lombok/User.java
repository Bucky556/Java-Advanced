package project_lombok;

import lombok.*;

import java.time.LocalDateTime;


@Data
public class User {
    private Integer id;
    private String username;
    private String email;

    public User(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
