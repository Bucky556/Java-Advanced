package FunctionalInterfaces;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] array = {5, 4, 8, -3, -4, 10};

        Predicate<Integer> odd = (number) -> number % 2 != 0;
        System.out.println(filter(array, odd));

        Predicate<Integer> even = (number) -> number % 2 == 0;
        System.out.println(filter(array, even));

        Predicate<Integer> negative = (number) -> number < 0;
        System.out.println(filter(array, negative));

        Predicate<Integer> negativeAndEven = negative.and(even);
        System.out.println(filter(array, negativeAndEven));

        Predicate<Integer> negativeOrOdd = negative.or(odd);
        System.out.println(filter(array, negativeOrOdd));
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
        List<Employer> empList = List.of(
                new Employer("Javohir Elmurodov", "UZB", "SOFTWARE ENGINEER", 28),
                new Employer("John Doe", "US", "MANAGER", 21),
                new Employer("Akmal Turdiyev", "UZB", "EMPLOYEE", 25),
                new Employer("John Leg", "GER", "MANAGER", 35),
                new Employer("Akbar Akbarov", "US", "SOFTWARE ENGINEER", 47)
        );

        Consumer<Employer> printOnConsole = e -> System.out.println(e);
        Consumer<Employer> storeInDB = e -> System.out.println(e.toString() + " saving data");
        Consumer<Employer> printConsumerAndStore = printOnConsole.andThen(storeInDB);

        forEach(empList, printConsumerAndStore);
        //forEach(empList, printOnConsole);
        //forEach(empList, storeInDB);
    }

    private static <T> void forEach(List<T> list, Consumer<T> consumer) {
        int nullCount = 0;
        for (T t : list) {
            if (t != null) {
                consumer.accept(t);
            } else {
                nullCount++;
            }
        }
        System.out.printf("%d null entries count in the list.\n", nullCount);
    }
}

@Data
@AllArgsConstructor
class Employer {
    private String name;
    private String country;
    private String position;
    private int age;
}

class FunctionTest {
    public static void main(String[] args) {
        Function<String, Integer> charsCount = s -> s.length();
        Integer name = charsCount.apply("Nodirjon");
        System.out.println(name);
    }
}

class SupplierTest {
    public static void main(String[] args) {
        Supplier<Throwable> supplier = () -> new RuntimeException("Exception occured!!!");
        Throwable throwable = supplier.get();
        System.out.println(throwable);
    }
}

class PrimitiveFunctionalInterfaces {
    public static void main(String[] args) {
        int[] arr = IntStream.rangeClosed(1, 500000).toArray();
        BinaryOperator<Integer> f1 = (((integer, integer2) -> integer + integer2));
        IntBinaryOperator f2 = ((x, y) -> x + y);
        RunningTime.calculate(v -> reduceWrapper(arr, f1));
        RunningTime.calculate(v -> reducePrimitive(arr, f2));
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
}

class RunningTime {
    public static void calculate(Consumer<Void> consumer) {
        long begin = System.currentTimeMillis();
        consumer.accept(null);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}

