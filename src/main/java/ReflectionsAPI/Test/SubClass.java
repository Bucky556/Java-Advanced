package ReflectionsAPI.Test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubClass {
    private String name;
    private int age;

    public SubClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void greet(){
        System.out.println("Hey, Hello!");
    }

    private void heyPrivate(){
        System.out.println("How did you get access?");
    }

    private static void run(){
        System.out.println("Running");
    }
}
