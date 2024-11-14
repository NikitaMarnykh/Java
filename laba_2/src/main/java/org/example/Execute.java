package org.example;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


public class Execute
{
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

    private Execute() {
    }

    public static Execute get_instance() {
        if (instance == null)
            instance = new Execute();
        return instance;
    }

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

        while (!(stack.isEmpty())) {
            postfix += stack.pop();
        }

        return postfix;
    }
}
