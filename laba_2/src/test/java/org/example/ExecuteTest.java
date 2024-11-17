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

    /**
     * Тестирование выполнения постфиксного выражения, требующего ввода значений переменных.
     * Заполняет вводимые значения переменных через System.in.
     */
    @Test
    void calculating_postfix_expression() {
        InputStream originalIn = System.in;
        String userInput = "10\n15\n25\n30\n"; // Значения для переменных A, B, C, D
        ByteArrayInputStream testIn = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testIn);

        String infix = "(A ^ 2 + B) * C - (D / 2)";
        String postfix = execute.infix_to_postfix(infix);

        double expected = 2860.0;
        double result = execute.execute(postfix);

        assertEquals(expected, result);
        System.setIn(originalIn);
    }

    /**
     * Тестирование преобразования инфиксного выражения с пробелами и скобками в постфиксное.
     */
    @Test
    void infix_to_postfix_with_parentheses() {
        String infix = "( A + B ) * C";
        String expected = "A B +C *";
        String result = execute.infix_to_postfix(infix);
        assertEquals(expected, result);
    }

}
