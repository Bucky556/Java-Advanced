package Annotations.MakingAnnotation;

public class UserDAO {
    @DataBaseProperty
    public void save(){
        System.out.println("Saving to Database...");
    }
}
