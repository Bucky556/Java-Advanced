package BehaviorParameterization;

import lombok.*;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Employee> empList = List.of(
                new Employee("Javohir Elmurodov", "UZB", "SOFTWARE ENGINEER", 28),
                new Employee("John Doe", "US", "MANAGER", 21),
                new Employee("Akmal Turdiyev", "UZB", "EMPLOYEE", 25),
                new Employee("John Leg", "GER", "MANAGER", 35),
                new Employee("Akbar Akbarov", "US", "SOFTWARE ENGINEER", 47)
        );
        List<Employee> uzbEmployees = getEmployeesBy(empList, new getEmployeesByCountry("UZB")); // hamma methodni bittaga quyish!!!
        System.out.println(uzbEmployees);
        List<Employee> usEmployees = getEmployeesBy(empList, new getEmployeesByCountry("US"));
        List<Employee> employeesByAge = getEmployeesBy(empList, new getEmployeesByAge(25));
        List<Employee> employeesByPosition = getEmployeesBy(empList, new getEmployeesByPosition("MANAGER"));

    }

    private static List<Employee> getEmployeesBy(List<Employee> employeeList, Filter filter) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (filter.test(employee)) {
                employees.add(employee);
            }
        }
        return employees;
    }
}

interface Filter {
    boolean test(Employee employee);
}

class getEmployeesByCountry implements Filter{
    private final String country;

    public getEmployeesByCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getCountry().equals(country);
    }
}

class getEmployeesByPosition implements Filter {
    private final String position;

    public getEmployeesByPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getPosition().equals(position);
    }
}

class getEmployeesByAge implements Filter {
    private final int age;

    public getEmployeesByAge(int age) {
        this.age = age;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() > age;
    }
}


@Data
@AllArgsConstructor
 class Employee {
    private String name;
    private String country;
    private String position;
    private int age;
}
