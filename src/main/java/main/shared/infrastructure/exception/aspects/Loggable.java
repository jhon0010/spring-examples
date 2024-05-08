package main.shared.infrastructure.exception.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Loggable {

    /**
     * Method that allow to log the execution of http request logic on methods annotated with @Loggable.
     */
    boolean logRequest() default false;
}
