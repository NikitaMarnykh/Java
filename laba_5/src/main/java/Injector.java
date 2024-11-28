import annotation.AutoInjectable;

import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;

/**
 * Класс, который реализует инъекцию зависимостей для любого объекта.
 */
public class Injector {
    private Properties properties = new Properties(); // Хранение свойств приложения
    FileInputStream filename; // Объект для работы с входным потоком файла

    /**
     * Конструктор загрузки данных из файла ресурсов.
     */
    public Injector() {
        try {
            // Попытка открыть файл свойств
            filename = new FileInputStream("src/main/resources/props.properties");
            // Загрузка свойств из файла
            properties.load(filename);
        } catch (Exception e) {
            // Вывод сообщения об ошибке, если файл не найден
            System.out.println("Файл свойств не найден!");
        }
    }

    /**
     * Метод для инъекции зависимостей в переданный объект.
     *
     * @param object любой объект, в который будут инъецироваться зависимости
     * @return экземпляр объекта с установленными зависимостями
     */
    public <T> T inject(T object) {
        try {
            // Получение класса объекта, который нужно инъецировать
            Class<?> injectable = object.getClass();
            // Создание нового экземпляра данного класса
            Object instance = injectable.getDeclaredConstructor().newInstance();
            // Получение всех полей класса
            Field[] fields = injectable.getDeclaredFields();

            // Итерация по всем полям класса
            for (Field field: fields) {
                // Получение аннотаций, применённых к полю
                Annotation[] annotations = field.getAnnotations();
                // Проверка наличия аннотации @AutoInjectable
                if (Arrays.stream(annotations).anyMatch(annotation -> annotation.annotationType() == AutoInjectable.class)) {
                    field.setAccessible(true); // Доступ к приватным полям
                    Class<?> fieldType = field.getType(); // Получение типа поля
                    String fieldTypeName = fieldType.getName(); // Получение имени типа поля

                    // Получение значения для инъекции из файла свойств
                    Object typeToInject = this.properties.get(fieldTypeName);
                    String typeToInjectName = typeToInject.toString(); // Преобразование значения в строку

                    // Получение класса для инъекции по имени
                    Class<?> fieldClass = Class.forName(typeToInjectName);
                    // Создание нового экземпляра указанного класса
                    Object fieldInstance = fieldClass.getDeclaredConstructor().newInstance();
                    // Установка созданного экземпляра в поле объекта
                    field.set(instance, fieldInstance);
                }
            }
            return (T)instance; // Возвращение экземпляра с установленными зависимостями
        } catch (Exception e) {
            // Вывод сообщения об ошибке и возврат null в случае неудачи
            System.out.println(e.toString());
            return null;
        }
    }
}
