package Annotations;

public class Calculator {
    @Deprecated(since = "1.3", forRemoval = true)
    public int sum(int a, int b) {
        return a + b;
    }
}
