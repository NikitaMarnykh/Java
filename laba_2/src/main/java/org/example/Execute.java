package org.example;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * Основной класс для выполнения арифметических операций.
 * Этот класс реализует алгоритм преобразования инфиксной нотации в постфиксную
 * (обратную польскую нотацию) и выполнения арифметических операций на
 * полученной постфиксной записи выражения.
 */
public class Execute {

    private static Execute instance;
    private static final HashMap<Character, Integer> PRIORITY;

    static {
        PRIORITY = new HashMap<>();
        PRIORITY.put('(', 0);
        PRIORITY.put('+', 1);
        PRIORITY.put('-', 1);
        PRIORITY.put('*', 2);
        PRIORITY.put('/', 2);
        PRIORITY.put('^', 3);
        PRIORITY.put(')', 0);
    }

    /**
     * Приватный конструктор для реализации паттерна Singleton.
     */
    private Execute() {
    }

    /**
     * Получает экземпляр класса Execute (Singleton).
     *
     * @return экземпляр Execute
     */
    public static Execute get_instance() {
        if (instance == null)
            instance = new Execute();
        return instance;
    }

    /**
     * Преобразует строку инфиксного выражения в постфиксное.
     * Этот метод принимает строку, представляющую арифметическое выражение в
     * инфиксной нотации и возвращает строку с этим же выражением в постфиксной
     * нотации.
     *
     * @param infix_str Строка с арифметическим выражением в инфиксной нотации.
     * @return Строка с арифметическим выражением в постфиксной нотации.
     */
    String infix_to_postfix(String infix_str) {
        char[] chars = infix_str.toCharArray();
        String postfix = "";
        Stack<Character> stack = new Stack<>();
        int index = 0;

        while (index < chars.length) {
            char chr = chars[index];

            if (chars[index] == '.') {
                check_for_valid_decimal_point(index, chars);
            } else if (Character.isDigit(chr) || Character.isLetter(chr) || chr == '_') {
                int left_bord = left_border(index, chars);
                String var_or_num = "";
                for (int ind = index; ind <= index + left_bord; ind++) {
                    var_or_num += chars[ind];
                }
                postfix += var_or_num + " ";

                index += left_bord;
            } else if (chr == '(') {
                stack.push('(');
            } else if (chr == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    postfix += stack.pop();
                stack.pop();
            } else if (is_operator(chr)) {
                while (!stack.isEmpty() && PRIORITY.get(stack.peek()) >= PRIORITY.get(chr))
                    postfix += stack.pop();
                stack.push(chr);
            }

            index++;
        }

        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }

        return postfix;
    }

    /**
     * Выполняет арифметическое выражение в постфиксной нотации.
     * Этот метод принимает строку, представляющую арифметическое выражение
     * в постфиксной нотации и вычисляет его значение.
     *
     * @param postfix_str Строка с арифметическим выражением в постфиксной нотации.
     * @return Результат выполнения выражения в виде числа с плавающей точкой.
     * @throws ArithmeticException В случае деления на ноль или неправильного выражения.
     */
    double execute(String postfix_str) {
        Stack<Double> stack = new Stack<>();
        char[] chars = postfix_str.toCharArray();
        Scanner scanner = new Scanner(System.in);
        int index = 0;

        while (index < chars.length) {
            char chr = chars[index];
            is_valid_char(chr);

            if (chars[index] == '.') {
                check_for_valid_decimal_point(index, chars);
            } else if (Character.isDigit(chr) || Character.isLetter(chr) || chr == '_') {
                int left_bord = left_border(index, chars);
                String var_or_num = "";
                for (int ind = index; ind <= index + left_bord; ind++) {
                    var_or_num += chars[ind];
                }
                if (Character.isLetter(chars[index])) {
                    System.out.print("Введите значение переменной " + var_or_num + ":\n");
                    var_or_num = scanner.nextLine();
                }
                index += left_bord;
                stack.push(Double.parseDouble(var_or_num));
            } else if (PRIORITY.containsKey(chr)) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();

                switch (chr) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;
                    case '-':
                        stack.push(operand1 - operand2);
                        break;
                    case '*':
                        stack.push(operand1 * operand2);
                        break;
                    case '/':
                        if (operand2 == 0) {
                            throw new ArithmeticException("Деление на ноль");
                        }
                        stack.push(operand1 / operand2);
                        break;
                    case '^':
                        stack.push(Math.pow(operand1, operand2));
                        break;
                    default:
                        throw new IllegalArgumentException("Неверный оператор: " + chr);
                }
            }

            index++;
        }

        double answer = stack.pop();

        if (!stack.isEmpty()) {
            throw new ArithmeticException("В выражении пропущен оператор");
        }

        scanner.close();

        return answer;
    }

    /**
     * Проверяет, является ли символ оператором.
     *
     * @param chr Символ для проверки.
     * @return true, если символ - оператор, иначе false.
     */
    boolean is_operator(char chr) {
        return PRIORITY.containsKey(chr);
    }

    /**
     * Проверяет, является ли символ цифрой.
     *
     * @param chr Символ для проверки.
     * @return true, если символ - цифра, иначе false.
     */
    boolean is_digit(char chr) {
        return Character.isDigit(chr);
    }

    /**
     * Проверяет, является ли символ буквой.
     *
     * @param chr Символ для проверки.
     * @return true, если символ - буква, иначе false.
     */
    boolean is_letter(char chr) {
        return Character.isLetter(chr);
    }

    /**
     * Проверяет, является ли символ допустимым для арифметического выражения.
     *
     * @param chr Символ для проверки.
     * @throws RuntimeException Если символ недопустим.
     */
    void is_valid_char(char chr) {
        if (!Character.isDigit(chr) && !Character.isLetter(chr) && !is_operator(chr)
                && chr != '_' && chr != '.' && chr != ' ' && chr != '(' && chr != ')') {
            throw new RuntimeException("Недопустимый символ: " + chr);
        }
    }

    /**
     * Проверяет правильность расположения десятичной точки.
     *
     * Должен проверяться индекс десятичной точки, чтобы убедиться, что
     * она расположена правильно, то есть между двумя цифрами.
     *
     * @param index Индекс, на который указывает точка.
     * @param chars Символы выражения.
     * @throws NumberFormatException Если формат десятичной точки некорректен.
     */
    void check_for_valid_decimal_point(int index, char[] chars) throws NumberFormatException {
        if (chars[index] != '.') {
            throw new NumberFormatException("Индекс должен указывать на точку.");
        } else if ((index == 0 || index == chars.length - 1) ||
                (!Character.isDigit(chars[index - 1]) || !Character.isDigit(chars[index + 1]))) {
            throw new NumberFormatException("Некорректный формат вещественного числа.");
        }
    }

    /**
     * Проверяет, является ли символ допустимым для чисел.
     *
     * @param chr Символ для проверки.
     * @throws RuntimeException Если символ недопустим.
     */
    void is_valid_number_symbol(char chr) {
        if (!Character.isDigit(chr) && chr != '.') {
            throw new RuntimeException("Некорректный символ для числа: " + chr);
        }
    }

    /**
     * Находит границу левого края числа или переменной.
     *
     * Этот метод помогает определить, как много символов следует считать
     * частью числа или названия переменной, начиная с начального индекса.
     *
     * @param start_index Начальный индекс для поиска.
     * @param chars Символы выражения.
     * @return Количество символов, представляющих число или переменную.
     */
    int left_border(int start_index, char[] chars) {
        int ind = start_index;
        boolean is_digit_first = Character.isDigit(chars[start_index]);
        boolean is_letter_first = Character.isLetter(chars[start_index]) || (chars[start_index] == '_');

        while (ind < chars.length && !is_operator(chars[ind]) && chars[ind] != ' ') {
            if (ind + 1 < chars.length) {
                is_valid_char(chars[ind + 1]);
            }

            if (chars[ind] == '.') {
                check_for_valid_decimal_point(ind, chars);
            }

            if (is_digit_first && (!Character.isDigit(chars[ind]) && chars[ind] != '.')) {
                throw new RuntimeException("Некорректный символ для числа (или имя переменной не может начинаться с цифры): " + chars[ind]);
            }

            if (is_letter_first && (!Character.isLetter(chars[ind])
                    && !Character.isDigit(chars[ind]) && chars[ind] != '_')) {
                throw new RuntimeException("Некорректный символ для имени переменной: " + chars[ind]);
            }

            ind++;
        }

        return ind - start_index - 1;
    }
}
