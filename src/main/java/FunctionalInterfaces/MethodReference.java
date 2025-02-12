package FunctionalInterfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodReference {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();             // 1
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            numbers.add(random.nextInt(7, 77));
        }
        Consumer<List<Integer>> sortList = Collections::sort;
        sortList.accept(numbers);
        System.out.println(numbers);

        MethodReference m = new MethodReference();              // 2
        Function<String, Integer> f = m::toInteger;
        System.out.println(f.apply("72"));

        Function<String,Integer> charsCount = String::length;   // 3
        Integer fullName = charsCount.apply("Okilov Nodirjon");
        System.out.println(fullName);
    }

    public int toInteger(String s) {
        return Integer.parseInt(s) * 2;
    }
}
