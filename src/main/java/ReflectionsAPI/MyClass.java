package ReflectionsAPI;

import java.util.Date;

public class MyClass {

    private String message;

    private int age;

    public MyClass(String message, int age) {
        this.message = message;
        this.age = age;
    }

    private MyClass() {

    }

    public void greet() {
        System.out.println("Hello Nodirjon!!!");
        System.out.println(message);
        System.out.println(age);
    }

    public String run() {
        System.out.println("Running");
        return "I am running";
    }

    public int sum(int a, int b){
        System.out.print("sum = ");
        return a + b;
    }

    public String getMessage() {
        return message;
    }
}
