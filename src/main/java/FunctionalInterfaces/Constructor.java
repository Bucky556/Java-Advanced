package FunctionalInterfaces;

import java.util.function.*;

public class Constructor {
    public static void main(String[] args) {
        Function<UserRegisterDTO, User> toUserMapper = User::new; // User class da constructor ichida yozildi

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO("Nodir", "556");
        User user = toUserMapper.apply(userRegisterDTO);
        System.out.println(user);
    }
}
