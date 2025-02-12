package FunctionalInterfaces.Test;

import java.util.function.Function;

public class Constructor {
    public static void main(String[] args) {
        Function<UserRegister,User> userRegisterFunction = User::new;

        UserRegister userRegister = new UserRegister("Otabek","609");
        User apply = userRegisterFunction.apply(userRegister);
        System.out.println(apply);
    }
}
