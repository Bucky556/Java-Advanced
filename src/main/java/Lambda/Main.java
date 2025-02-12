package Lambda;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    // private Function<Integer,String> function = i -> "Hello Nodirjon";
    public static void main(String[] args) {

        //introductionToLambdas();

        List<Ishchilar> empList = List.of(
                new Ishchilar("Javohir Elmurodov", "UZB", "SOFTWARE ENGINEER", 28),
                new Ishchilar("John Doe", "US", "MANAGER", 21),
                new Ishchilar("Akmal Turdiyev", "UZB", "EMPLOYEE", 25),
                new Ishchilar("John Leg", "GER", "MANAGER", 35),
                new Ishchilar("Akbar Akbarov", "US", "SOFTWARE ENGINEER", 47)
        );
        List<Ishchilar> uzb = getEmployeesBy(empList, ishchilar -> ishchilar.getCountry().equals("UZB"));
        List<Ishchilar> us = getEmployeesBy(empList, ishchilar -> ishchilar.getCountry().equals("US"));
        System.out.println(uzb);
    }

    private static List<Ishchilar> getEmployeesBy(List<Ishchilar> employeeList, Filter filter) {
        List<Ishchilar> employees = new ArrayList<>();
        for (Ishchilar employee : employeeList) {
            if (filter.test(employee)) {
                employees.add(employee);
            }
        }
        return employees;
    }


    private static void introductionToLambdas() {
        //     = args -> System.out.println("Assalom alekum");

              /* = (arg1,arg2) -> {
            System.out.println("Assalom alekum");
            System.out.println("Hello");
             }*/

        int b = 90;
        Runnable runnable = () -> {
            int a = 12;
            System.out.println(a);
            System.out.println(b);
        };
        runnable.run();
    }
}