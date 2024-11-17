package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс тестирования для проверки корректности работы класса Execute.
 *
 * В этом классе используются тесты для подтверждения правильности
 * преобразования арифметических выражений из инфиксной нотации в
 * постфиксную, а также для выполнения вычислений на основе постфиксных
 * выражений.
 */
class ExecuteTest {
    private Execute execute;

    /**
     * Метод, который выполняется перед каждым тестом.
     * Инициализирует экземпляр класса Execute.
     */
    @BeforeEach
    void setUp() {
        execute = Execute.get_instance();
    }

    /**
     * Тестирование преобразования инфиксного выражения с числами в постфиксное.
     */
    @Test
    void infix_to_postfix_numbers() {
        String infix = "10 + 5 * 3 / 2";
        String expected = "10 5 3 *2 /+";
        String result = execute.infix_to_postfix(infix);
        assertEquals(expected, result);
    }

    /**
     * Тестирование преобразования инфиксного выражения с переменными в постфиксное.
     */
    @Test
    void infix_to_postfix_variables() {
        String infix = "(A + B) * C - (D / 2)";
        String expected = "A B +C *D 2 /-";
        String result = execute.infix_to_postfix(infix);
        assertEquals(expected, result);
    }

}
