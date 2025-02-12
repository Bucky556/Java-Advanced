package BehaviorParameterization.Test;

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
        List<Employee> uzbEmployees = getEmployeesBy(empList, employee -> employee.getCountry().equals("UZB")); // hamma methodni bittaga quyish!!!
        System.out.println(uzbEmployees);
        List<Employee> employeesByAge = getEmployeesBy(empList, employee -> employee.getAge() > 25);
        System.out.println(employeesByAge);

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

class getEmployeesByCountry implements Filter {
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