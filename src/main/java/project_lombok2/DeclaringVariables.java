package project_lombok2;

import lombok.val;

public class DeclaringVariables {
    public static void main(String[] args) {
        var message1 = "Hello Java!";
        System.out.println(message1);
        val message2 = "Hello Java2 !";
        System.out.println(message2);
    }
}
