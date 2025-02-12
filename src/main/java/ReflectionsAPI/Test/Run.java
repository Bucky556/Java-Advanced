package ReflectionsAPI.Test;


import java.lang.reflect.Field;

public class Run {
    public static void main(String[] args) throws Exception{
        SubClass subClass = new SubClass("Nodirjon",19);
        Class<? extends SubClass> aClass = subClass.getClass();
        for (Field declaredField : aClass.getDeclaredFields()) {
            System.out.println(declaredField.getName());
        }

        Field declaredField = aClass.getDeclaredField("name");
        declaredField.setAccessible(true);
        declaredField.set(subClass,"Hey now, you are Xushnid");
        System.out.println(subClass.getName());


    }
}
