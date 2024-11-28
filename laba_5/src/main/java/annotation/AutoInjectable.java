package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Указываем, что аннотация будет доступна во время выполнения
@Retention(RetentionPolicy.RUNTIME)
// Указываем, что аннотация может применяться только к полям класса
@Target(ElementType.FIELD)
public @interface AutoInjectable {
    // Аннотация без параметров, используемая для маркировки полей,
    // которые должны быть автоматически инъецированы.
}
