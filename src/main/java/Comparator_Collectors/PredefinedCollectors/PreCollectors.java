package Comparator_Collectors.PredefinedCollectors;

import Streams.StreamAPI.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.*;

public class PreCollectors {
    public static void main(String[] args) throws IOException {
        /*List<String> toList = Stream.of("java", "kotlin", "python", "c++").map(String::toUpperCase).toList();
        System.out.println(toList);

        Set<String> toSet = Stream.of("html", "css", "react", "javascript").map(String::toUpperCase).collect(Collectors.toSet());
        System.out.println(toSet);

        TreeSet<String> treeSet = Stream.of("java", "kotlin", "python", "c++")
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);

        Map<Integer, String> map = Stream.of("java", "python", "c++", "react", "javascript","C++")
                .map(String::toUpperCase)
                //.collect(Collectors.toMap(String::length, String::toLowerCase));
                .collect(Collectors.toMap(String::length,String::toLowerCase,(k1,k2) -> k1));
        map.forEach((k, v) -> System.out.println(k + "::" + v));

        String joining = Stream.of("java", "python", "c++", "react", "javascript", "C++")
                .map(String::toUpperCase)
                .collect(Collectors.joining(", ","{","}"));
        System.out.println(joining);
        */

        String jsonData = Files.readString(Path.of("src/main/resources/employees.json"));
        Gson gson = new Gson();
        Type type = new TypeToken<List<Employee>>() {
        }.getType();
        List<Employee> employeeList = gson.fromJson(jsonData, type);

       /* Map<Integer, List<Employee>> listMap = employeeList.stream().collect(Collectors.groupingBy(e -> e.getAge()));
        listMap.forEach((k,v) -> {
            System.out.println(k);
            for (Employee employee : v) {
                System.out.println(employee);
            }
        });*/

        /*Map<Integer, Long> collect = employeeList.stream().collect(Collectors.groupingBy(e -> e.getAge(), Collectors.counting()));
        collect.forEach((k, v) -> System.out.println(k + " :: " + v));*/

        /*Map<Boolean, List<Employee>> booleanListMap = employeeList.stream()
                .collect(Collectors.partitioningBy(e -> e.getGender().equals("MALE")));
        booleanListMap.forEach((k,v) -> {
            System.out.println("k = " + k);
            for (Employee employee : v) {
                System.out.println(employee);
            }
        });*/

        /*
        Stream.of("Nodirjon", "Oybek", "Xushnid", "Otabek", "Akmal", "Akbar")
                .reduce((l1, l2) -> l1 + ", " + l2)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("There is no item")
                );
         */

        /*employeeList.stream()*//*.min*//*.max(Comparator.comparing(Employee::getAge)).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("There is no item")
        );
        */

       /* Double collect = employeeList.stream().collect(Collectors.averagingInt(Employee::getAge));
        System.out.println(collect);
       */

        /*IntSummaryStatistics statistics = employeeList.stream().collect(Collectors.summarizingInt(Employee::getAge));
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        */

        IntStream intStream = IntStream.rangeClosed(1, 30);
        //intStream.forEach(System.out::println);
        /*OptionalDouble average = intStream.average();
        System.out.println(average);*/
        Stream<Integer> boxed = intStream.boxed();
        boxed.forEach(System.out::println);


    }
}