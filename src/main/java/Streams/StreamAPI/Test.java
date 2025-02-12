package Streams.StreamAPI;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        /*
        List<Books> javaBooks = new ArrayList<>();
        javaBooks.add(new Books("2","Concurency in Practise"));
        javaBooks.add(new Books("3","OOP"));

        List<Books> sqlBooks = new ArrayList<>();
        sqlBooks.add(new Books("5","Modern coding"));
        sqlBooks.add(new Books("5","Java in Action"));

        List<List<Books>> books = List.of(javaBooks, sqlBooks);
        Stream<Books> booksStream = books.stream().flatMap(Collection::stream);
        booksStream.forEach(System.out::println);
        */

        /*List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Nodirjon",19));
        studentList.add(new Student("Akbar",20));
        studentList.add(new Student("Xushnid",25));
        studentList.add(new Student("Otabek",18));
        studentList.stream().findFirst().filter(student -> student.age() == 19).ifPresentOrElse(
                s -> System.out.println(s.name()),
                () -> System.out.println("Not Found")
        );
*/
       /* Stream<String> languages = Stream.of("Java", "Kotlin", "Python", "JavaScript");
        String[] array = languages.toArray(String[]::new);
        System.out.println(Arrays.toString(array));*/

       /* Stream.iterate(1, x -> x + 1).limit(77).peek(System.out::println).toList();
        Random random = new Random();
        Stream.generate(() -> random.nextInt(1,100)).limit(50).sorted().forEach(System.out::println);*/


    }
}

record Books(String id, String name) {
}

record Student(String name, int age) {
}
