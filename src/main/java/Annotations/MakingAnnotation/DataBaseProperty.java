package Annotations.MakingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataBaseProperty {
        String url() default "jdbc:postgresql//localhost:8432/postgres";
        String username() default "Nodir";
        String password() default "556";
}
