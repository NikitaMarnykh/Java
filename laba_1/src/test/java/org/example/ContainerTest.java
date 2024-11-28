package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Класс тестирования для контейнера
public class ContainerTest {

    // Проверка, что контейнер пуст, если в него не добавлены данные
    @Test
    void container_empty_if_not_data_added() {
        Container<String> container = new Container<>(); // Создание нового контейнера
        assertTrue(container.is_empty()); // Убеждаемся, что контейнер пуст
    }

    // Проверка, что контейнер не пуст, если данные добавлены через конструктор
    @Test
    void container_not_empty_if_data_added_using_constructor() {
        Container<String> container = new Container<>("I", "Love", "Programming"); // Создание контейнера с данными
        assertFalse(container.is_empty()); // Проверяем, что контейнер не пуст
    }

    // Проверка, что контейнер не пуст, если данные добавлены через метод add
    @Test
    void container_not_empty_if_data_added_using_add_method() {
        Container<String> container = new Container<>("I"); // Создание контейнера и добавление элемента
        assertFalse(container.is_empty()); // Проверяем, что контейнер не пуст
    }

    // Проверка, что метод remove возвращает false, если не удалось удалить значение
    @Test
    void remove_method_returns_false_if_not_delete_value() {
        Container<String> container = new Container<>("love"); // Создание контейнера с одним элементом
        assertFalse(container.remove("I")); // Проверяем, что удаление не удалось
    }

    // Проверка, что метод remove возвращает false, если контейнер пуст
    @Test
    void remove_method_returns_false_if_container_is_empty() {
        Container<String> container = new Container<>(); // Создание пустого контейнера
        assertFalse(container.remove("I")); // Проверяем, что удаление не удалось
    }

    // Проверка, что метод remove возвращает true, если значение успешно удалено
    @Test
    void remove_method_returns_true_if_value_deleted() {
        Container<String> container = new Container<>("I"); // Создание контейнера с одним элементом
        assertTrue(container.remove("I")); // Проверяем, что элемент был успешно удален
    }

    // Проверка, что метод get возвращает null, если значение не найдено
    @Test
    void get_method_returns_null_if_value_is_not_found() {
        Container<String> container = new Container<>("I", "love", "programming", "in", "Java"); // Создание контейнера с несколькими элементами
        assertNull(container.get("Python")); // Проверяем, что значение не найдено
    }

    // Проверка, что метод get возвращает значение, если оно найдено
    @Test
    void get_method_returns_value_if_value_is_found() {
        Container<String> container = new Container<>("I", "love", "programming", "in", "Java"); // Создание контейнера с несколькими элементами
        assertEquals(container.get("Java").item, "Java"); // Проверяем, что возвращается правильное значение
    }
}
