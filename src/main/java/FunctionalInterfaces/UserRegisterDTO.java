package FunctionalInterfaces;

import lombok.*;

@Data
@AllArgsConstructor
public class UserRegisterDTO {
    private final String userName;
    private final String password;
}
