package entities.entitiy;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)



public @interface ConfigurationAnimal {

   String name() default "";
    Specifications specifications() default Specifications.AGGRESSIVE;
    double mass ();
    int numberOfAnimalsInCage ();
    int speed ();
    int numberOfStart();
}
