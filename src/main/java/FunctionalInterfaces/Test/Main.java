package FunctionalInterfaces.Test;

import lombok.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

    }
}

class PredicateTest {
    public static void main(String[] args) {
        int[] numbers = {2, -10, 5, 8, 1, -6};

        Predicate<Integer> even = number -> number % 2 == 0;
        Predicate<Integer> odd = number -> number % 2 != 0;
        Predicate<Integer> positive = number -> number > 0;
        Predicate<Integer> negative = number -> number < 0;
        Predicate<Integer> negativeAndEven = even.and(negative);
        Predicate<Integer> positiveOrOdd = odd.or(positive);

        System.out.println(filter(numbers, even));
        System.out.println(filter(numbers, negativeAndEven));
    }

    public static List<Integer> filter(int[] array, Predicate<Integer> predicate) {
        List<Integer> result = new ArrayList<>();
        for (int i : array) {
            if (predicate.test(i)) {
                result.add(i);
            }
        }
        return result;
    }
}

class ConsumerTest {
    public static void main(String[] args) {
        List<Employer> employers = List.of(
                new Employer("Nodirjon", "5556", "MANAGER"),
                new Employer("Akbar", "1234", "SALES_MANAGER"),
                new Employer("Xushnid", "0980", "EMPLOYEE")
        );

        Consumer<Employer> toConsole = System.out::println;
        Consumer<Employer> savingData = e -> System.out.println(e.toString() + " saved databse");
        Consumer<Employer> consoleThenSave = toConsole.andThen(savingData);
        forEach(employers, consoleThenSave);

    }


    private static <E> void forEach(List<E> list, Consumer<E> consumer) {
        int nullCount = 0;
        for (E e : list) {
            if (e != null) {
                consumer.accept(e);
            } else {
                nullCount++;
            }
        }
        System.out.printf("%d null count entries in the list.\n", nullCount);
    }
}


@Data
@AllArgsConstructor
class Employer {
    private String name;
    private String password;
    private String position;
}

class FunctionTest {
    public static void main(String[] args) {
        Function<String, Integer> charsCount = String::length;
        Integer name = charsCount.apply("Nodirjon");
        System.out.println(name);
    }
}

class SupplierTest {
    public static void main(String[] args) {
        Supplier<Throwable> throwableSupplier = () -> new RuntimeException("Exception occured!!!");
        Throwable throwable = throwableSupplier.get();
        System.out.println(throwable);
    }
}

class PrimitiveFunctionalInterfaces {
    public static void main(String[] args) {
        int[] array = IntStream.rangeClosed(1, 500000).toArray();
        BinaryOperator<Integer> f1 = (Integer::sum);
        IntBinaryOperator f2 = (Integer::sum);
        Runtime.calculate(v -> reduceWrapper(array, f1));
        Runtime.calculate(v -> reducePrimitive(array, f2));
    }

    static int reduceWrapper(int[] arr, BinaryOperator<Integer> operator) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = operator.apply(result, arr[i]); // Boxing and Unboxing here
        }
        return result;
    }

    static int reducePrimitive(int[] arr, IntBinaryOperator operator) { // primitive interface
        int result = arr[0];
        for (int i = 1; 1 < arr.length; i++) {
            result = operator.applyAsInt(result, arr[i]);
        }
        return result;
    }

    class Runtime {
        public static void calculate(Consumer<Void> consumer) {
            long begin = System.currentTimeMillis();
            consumer.accept(null);
            long end = System.currentTimeMillis();
            System.out.println(end - begin);
        }
    }
}

