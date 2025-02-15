package Annotations.MakingAnnotation;

import java.lang.reflect.Method;

@MyAnnotation
public class Main {
    @MyAnnotation
    private String field;

    @MyAnnotation
    public void greet() {
        System.out.println("Hello");
    }

    public static void main(String[] args) throws Exception{
        //System.out.println(Main.class.isAnnotationPresent(MyAnnotation.class));

        UserDAO userDAO = new UserDAO();
        Method method = userDAO.getClass().getDeclaredMethod("save", null);
        DataBaseProperty dataBaseProperty = method.getAnnotation(DataBaseProperty.class);
        String url = dataBaseProperty.url();
        String userName = dataBaseProperty.username();
        String password = dataBaseProperty.password();
        method.invoke(userDAO,null);
        System.out.printf("Saved to DataBase with %s\nCredentials: %s:%s".formatted(url,userName,password));
    }
}
