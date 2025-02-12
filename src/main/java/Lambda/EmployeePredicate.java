package Lambda;

@FunctionalInterface
public interface EmployeePredicate {
    boolean testA(Object o); // abstract method
    //boolean testB(Object o);

    default void execute() {
        System.out.println("Executing...");
    }

    default void run() {
        System.out.println("Running...");
    }
}
