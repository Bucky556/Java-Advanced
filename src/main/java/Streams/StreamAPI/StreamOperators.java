package Streams.StreamAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperators {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("src/main/resources/employees.json");
        String jsonData = Files.readString(path);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Employee>>() {
        }.getType();
        List<Employee> employeeList = gson.fromJson(jsonData, type);
        /*
        Predicate<Employee> byGender = e -> e.getGender().equals("MALE");
        Predicate<Employee> byAge = e -> e.getAge() > 20;
        Predicate<Employee> employeePredicate = byGender.and(byAge);
        employeeList.stream()
                .filter(employeePredicate)
                .skip(3)
                .limit(10)
                .peek(System.out::println)
                .toList();
               // .forEach(System.out::println);
               */
        //Matching
        /*
        Stream<Integer> integerStream = Stream.of(2, 7, 9, 10, 5);
        boolean anyMatch = integerStream.anyMatch(n -> n % 2 == 0);
        System.out.println("anyMatch = " + anyMatch);

        integerStream = Stream.of(2, 8, 4, 10, 50);
        boolean allMatch = integerStream.allMatch(n -> n % 2 == 0);
        System.out.println("allMatch = " + allMatch);

        integerStream = Stream.of(1, 7, 9, 11, 5);
        boolean noneMatch = integerStream.noneMatch(n -> n % 2 == 0);
        System.out.println("noneMatch = " + noneMatch);
        */
        /*
        Stream<Employee> employeeStream = employeeList.stream();
        Stream<EmpInfo> empInfoStream = employeeStream.map(employee -> new EmpInfo(employee.getFullName(), employee.getAge()));
        //empInfoStream.forEach(System.out::println);
        IntStream integerStream = empInfoStream.mapToInt(EmpInfo::age);
        //Stream<Integer> => IntStream
        */
        /*
        List<Book> javaBooks = new ArrayList<>();
        javaBooks.add(new Book("1", "Concurrency in Practise"));
        javaBooks.add(new Book("2", "Modern Java in Action"));

        List<Book> postgresqlBooks = new ArrayList<>();
        postgresqlBooks.add(new Book("7", "Postgresql 14 Administration"));
        postgresqlBooks.add(new Book("10", "SQL for Dummies"));

        List<List<Book>> lists = List.of(javaBooks, postgresqlBooks);
        Stream<Book> bookStream = lists.stream().flatMap(Collection::stream);
        bookStream.forEach(System.out::println);
        */
        /*
        List<Book> javaBooks = new ArrayList<>();
        javaBooks.add(new Book("1", "Concurrency in Practise"));
        javaBooks.add(new Book("2", "Modern Java in Action"));
        javaBooks.parallelStream()//.stream()//.findFirst()
                .findAny()
                .filter(book -> book.id().equals("1"))
                .ifPresentOrElse(
                book -> System.out.println(book.name()),
                () -> System.out.println("Not Found")
        );
        */
        /*
        Stream<String> languages = Stream.of("Java", "Kotlin", "Python", "C++");
        languages.reduce((l1, l2) -> l1 + ", " + l2).ifPresentOrElse(
                lang -> System.out.println(lang),
                () -> System.out.println("No items found")
        );
        */
        /*
        Stream<String> languages = Stream.of("Java", "Kotlin", "Python", "C++");
        String[] array = languages.toArray(String[]::new);
        System.out.println(Arrays.toString(array));
        */
        Stream.iterate(1, x -> x + 1)
                .limit(10)
                .forEach(System.out::println);
        Stream.generate(UUID::randomUUID)
                .limit(10)
                .forEach(System.out::println);
    }
}

record EmpInfo(String fullName, int age) {
}

record Book(String id, String name) {
}