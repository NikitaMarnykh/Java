package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    private List<Integer> array_list;

    @BeforeEach
    void set_up() {
        array_list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            array_list.add(i);
        }
    }

    @Test
    void when_added_100_elements_then_size_must_be_100() {
        assertEquals(100, array_list.size());
    }

    @Test
    void when_element_removed_by_index_then_size_must_be_decreased() {
        assertTrue(array_list.remove(5) != null);
        assertEquals(99, array_list.size());
    }

    @Test
    void when_element_removed_then_size_must_be_decreased() {
        array_list.add(102);
        assertEquals(101, array_list.size());
        assertTrue(array_list.remove(Integer.valueOf(102)));
        assertEquals(100, array_list.size());
    }

    @Test
    void when_list_cleared_then_size_must_be_0() {
        array_list.clear();
        assertEquals(0, array_list.size());
    }

    @Test
    void when_index_out_of_bounds_then_thrown_exception() {
        assertThrows(IndexOutOfBoundsException.class, () -> array_list.get(100));
    }

    @Test
    void method_get_returned_right_value() {
        int value = array_list.get(0);
        assertEquals(0, value);
    }

    @Test
    void insert_into_middle() {
        array_list.add(50, 66);
        assertEquals(66, array_list.get(50).intValue());
    }

    @Test
    void insert_into_first_position() {
        array_list.add(0, 0);
        assertEquals(0, array_list.get(0).intValue());
    }

    @Test
    void insert_into_last_position() {
        array_list.add(100, 100);
        assertEquals(100, array_list.get(100).intValue());
    }
}
