package Annotations;

import java.util.ArrayList;
import java.util.List;

public class Main {
    //    @SuppressWarnings({"unchecked","rawtypes"})
    public static void main(String[] args) {
        /*B b = new B();
        b.abcd();
        List list = new ArrayList();
        list.add("1232"); */

        /*Machine<String> machine = new Machine<>();
        machine.safe("Nodirjon", "Xushnid", "Akbar");*/

        Calculator calculator = new Calculator();
        System.out.println(calculator.sum(2, 4));
    }
}

class Machine<T> {
    private List<T> versions = new ArrayList<>();

    @SafeVarargs
    public final void safe(T... toAdd) {
        for (T version : toAdd) {
            versions.add(version);
        }
    }
}
