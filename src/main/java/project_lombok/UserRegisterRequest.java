package project_lombok;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRegisterRequest {
    private final String username;
    @NonNull
    private String password;
    private String email;

}
