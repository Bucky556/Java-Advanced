package FunctionalInterfaces.Test;

import java.util.*;
import java.util.function.*;

public class MethodReference {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            numbers.add(random.nextInt(10, 100));
        }

        Consumer<List<Integer>> consumer = Collections::sort;
        consumer.accept(numbers);
        System.out.println(numbers);

        System.out.println("----------------------------");

        MethodReference mm = new MethodReference();
        Function<String, Integer> function = mm::toInteger;
        System.out.println(function.apply("123"));

        System.out.println("----------------------------");

        Function<String, Integer> function1 = String::length;
        Integer name = function1.apply("Nodirjon");
        System.out.println(name);
    }

    public int toInteger(String s) {
        return Integer.parseInt(s) * 2;
    }
}
