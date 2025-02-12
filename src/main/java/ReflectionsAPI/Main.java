package ReflectionsAPI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
        MyClass myClass = new MyClass("Hello from reflections!", 21);
        Class<? extends MyClass> aClass = myClass.getClass();
        /*Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }*/

        Method sum = aClass.getDeclaredMethod("sum", int.class, int.class);
        sum.setAccessible(true);
        Object o1 = sum.invoke(myClass, 5, 10);
        System.out.println(o1);

        Method run = aClass.getDeclaredMethod("run");
        run.setAccessible(true);
        Object o2 = run.invoke(myClass);
        System.out.println(o2);

        System.out.println(myClass.getMessage());
        Field message = aClass.getDeclaredField("message");
        message.setAccessible(true);
        message.set(myClass,"Yeah, I changed value with reflections!!!");
        System.out.println(myClass.getMessage());

    }

    private static void workingWithPrivateDatatypeInMethod() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<MyClass> clazz = (Class<MyClass>) Class.forName("ReflectionsAPI.MyClass");
        Constructor<MyClass> constructor = clazz.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        MyClass myClass = constructor.newInstance("Oyda hova you?", 19);
        myClass.greet();
    }
}
