import annotation.AutoInjectable;

// Интерфейс для примера с одним методом
interface SomeInterface {
    public void doSomething(); // Метод, который будут реализовывать классы
}

// Другой интерфейс с одним методом
interface SomeOtherInterface {
    public void doSomeOther(); // Метод, который будет реализован в других классах
}

// Реализация интерфейса SomeInterface, выводит "A"
class SomeImpl implements SomeInterface {
    public void doSomething() {
        System.out.println("A");
    }
}

// Другая реализация интерфейса SomeInterface, выводит "B"
class OtherImpl implements SomeInterface {
    public void doSomething() {
        System.out.println("B");
    }
}

// Реализация интерфейса SomeOtherInterface, выводит "C"
class SODoer implements SomeOtherInterface {
    public void doSomeOther() {
        System.out.println("C");
    }
}

// Класс, в котором будут инъецироваться зависимости
class SomeBean {
    @AutoInjectable private SomeInterface field1; // Поле, которое будет инъецировано реализацией SomeInterface
    @AutoInjectable private SomeOtherInterface field2; // Поле, которое будет инъецировано реализацией SomeOtherInterface

    // Метод, который вызывает методы инъецированных зависимостей
    public void function() {
        field1.doSomething(); // Вызов метода doSomething() из инъецированной реализации SomeInterface
        field2.doSomeOther(); // Вызов метода doSomeOther() из инъецированной реализации SomeOtherInterface
    }
}

// Главный класс с методом main
public class Reflection {
    public static void main(String[] args) {
        // Создание экземпляра SomeBean с инъекцией зависимостей
        SomeBean sb = (new Injector()).inject(new SomeBean());
        // Вызов метода function() для демонстрации работы инъекций
        sb.function(); // Ожидается вывод "A" и "C" (или "B" в зависимости от конфигурации)
    }
}
