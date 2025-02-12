package Comparator_Collectors.Test;

import Streams.StreamAPI.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Comparator_Collector {
    public static void main(String[] args) throws IOException {
        /*IntStream range = IntStream.rangeClosed(1, 6);
        range.forEach(System.out::println);*/

        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 0, 9, 8, 7, 6);
        //System.out.println(intStream.sum());
        //intStream.filter(n -> n %2 ==0).sorted().forEach(System.out::println);
        //intStream.map(n -> n*n).sorted().forEach(System.out::println);
        IntStream intStream1 = IntStream.rangeClosed(1, 4);
        //System.out.println(intStream1.reduce((a, b) -> a * b));
        /*int[] array = intStream1.toArray();
        System.out.println(Arrays.toString(array));*/
        /*OptionalLong reduce = LongStream.rangeClosed(1, 5).reduce((x, y) -> x * y);
        System.out.println("5! = " + reduce);*/
        String jsonData = Files.readString(Path.of("src/main/resources/employees.json"));
        Gson gson = new Gson();
        Type type = new TypeToken<List<Employee>>() {
        }.getType();
        List<Employee> employeeList = gson.fromJson(jsonData, type);
        /*List<Employee> collect = employeeList.stream().toList();
        collect.forEach(System.out::println);*/

        /*Map<Integer, Long> collect = employeeList.stream().collect(Collectors.groupingBy(Employee::getAge,Collectors.counting()));
        collect.forEach((k, v) -> System.out.println(k + "::" + v));*/

        /*Stream<String> java = Stream.of("Java", "Kotlin", "Python", "HTML", "CSS");
        System.out.println(java.collect(Collectors.joining(", ", "[", "]")));*/

        Double collect = Stream.of(1, 4, 5, 2, 4).collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(collect);
    }
}