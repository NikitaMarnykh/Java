package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Method       Size    ArrayList_Time    LinkedList_Time");
        time();
    }

    public static void time() {
        List<Integer> array_list = new ArrayList<>();
        List<Integer> linked_list = new LinkedList<>();
        Random random = new Random();
        int size = 1000;

        long start_time = System.nanoTime();
        for (int i = 0; i < size; i++) {
            array_list.add(i);
        }
        long run_time_1 = System.nanoTime() - start_time;

        start_time = System.nanoTime();   
        for (int i = 0; i < size; i++) {
            linked_list.add(i);
        }
        long run_time_2 = System.nanoTime() - start_time;

        print_result("ADD", size, run_time_1, run_time_2);

        start_time = System.nanoTime();
        for (int i = 0; i < size; i++) {
            array_list.get(i);
        }
        run_time_1 = System.nanoTime() - start_time;

        start_time = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linked_list.get(i);
        }
        run_time_2 = System.nanoTime() - start_time;

        print_result("GET", size, run_time_1, run_time_2);
        
        start_time = System.nanoTime();
        int random_number = random.nextInt(size);
        for (int i = 0; i < size; i++) {
            array_list.add(random_number, i);
            random_number = random.nextInt(size);
        }
        run_time_1 = System.nanoTime() - start_time;

        start_time = System.nanoTime();
        random_number = random.nextInt(size);
        for (int i = 0; i < size; i++) {
            linked_list.add(random_number, i);
            random_number = random.nextInt(size);
        }
        run_time_2 = System.nanoTime() - start_time;

        print_result("ADD_MIDDLE", size, run_time_1, run_time_2);

        start_time = System.nanoTime();
        for (int i = 0; i < size; i++) {
            array_list.remove(0);
        }
        run_time_1 = System.nanoTime() - start_time;

        start_time = System.nanoTime();
        for (int i = 0; i < size; i++) {
            linked_list.remove(0);
        }
        run_time_2 = System.nanoTime() - start_time;

        print_result("REMOVE", size, run_time_1, run_time_2);
    }

    private static void print_result(String method, int size, long time_array_list, long time_linked_list) {
        System.out.printf("%-12s| %-4d| %15d| %16d%n", method, size, time_array_list, time_linked_list);
    }
}
