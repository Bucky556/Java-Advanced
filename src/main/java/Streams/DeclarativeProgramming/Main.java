package Streams.DeclarativeProgramming;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {
        //Stream<String> stream = Files.newBufferedReader(path).lines();
        Path path = Path.of("src/main/resources/students.json");
        String jsonData = Files.readString(path);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Students>>() {
        }.getType();
        List<Students> studentsList = gson.fromJson(jsonData, type);

        /*Predicate<Students> ByName = s -> s.getFullName().equals("Лаврентий Матвеев");
        Predicate<Students> ByAge = students -> students.getAge() < 25;
        Predicate<Students> studentsPredicate = ByName.and(ByAge);*/

        studentsList.stream().filter(students -> students.getAge() < 25).limit(10).toList();
        studentsList.forEach(System.out::println);

        //imperative(studentsList);

    }

    private static void imperative(List<Students> students) {
        List<Students> studentsUnder30 = new ArrayList<>();
        int count = 0;
        for (Students student : students) {
            if (student.getAge() < 30) {
                studentsUnder30.add(student);
                count = count + 1;
            }
            if (count == 10) break;
        }
        studentsUnder30.forEach(System.out::println);
    }
}
