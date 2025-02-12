package Comparator_Collectors;

import Streams.StreamAPI.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {
    public static void main(String[] args) throws Exception {
        /*
        Stream.of("Java","Kotlin","Python","React","C++")
                .sorted(Comparator.comparing(String::length,Comparator.reverseOrder()))
                .forEach(System.out::println);
        */

        /*Stream.of(1,6,-2,10,-5).sorted(Comparator.comparingInt(Math::abs)).forEach(System.out::println);*/

        Path path = Path.of("src/main/resources/employees.json");
        String jsonData = Files.readString(path);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Employee>>() {
        }.getType();
        List<Employee> employees = gson.fromJson(jsonData, type);
        employees.stream()
                .sorted(Comparator.comparing(Employee::getFullName, Comparator.nullsLast(String::compareTo))
                        .thenComparing(Employee::getAge)
                        .thenComparing(Employee::getGender)
                ).forEach(System.out::println);

    }
}
