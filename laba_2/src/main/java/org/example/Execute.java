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
}
