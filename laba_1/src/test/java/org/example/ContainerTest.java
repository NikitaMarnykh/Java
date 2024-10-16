package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerTest {
    @Test
    void container_empty_if_not_data_added() {
        Container<String> container = new Container<>();
        assertTrue(container.is_empty());
    }

    @Test
    void container_not_empty_if_data_added_using_constructor() {
        Container<String> container = new Container<>("I", "Love", "Programming");
        assertFalse(container.is_empty());
    }

    @Test
    void container_not_empty_if_data_added_using_add_method() {
        Container<String> container = new Container<>("I");
        assertFalse(container.is_empty());
    }

    @Test
    void remove_method_returns_false_if_not_delete_value() {
        Container<String> container = new Container<>("love");
        assertFalse(container.remove("I"));
    }

    @Test
    void remove_method_returns_false_if_container_is_empty() {
        Container<String> container = new Container<>();
        assertFalse(container.remove("I"));
    }

    @Test
    void remove_method_returns_true_if_value_deleted() {
        Container<String> container = new Container<>("I");
        assertTrue(container.remove("I"));
    }

    @Test
    void get_method_returns_null_if_value_is_not_found() {
        Container<String> container = new Container<>("I", "love", "programming", "in", "Java");
        assertNull(container.get("Python"));

    }

    @Test
    void get_method_returns_value_if_value_is_found() {
        Container<String> container = new Container<>("I", "love", "programming", "in", "Java");
        assertEquals(container.get("Java").item, "Java");
    }
}
