public class Container<template> {
    private Node<template> head;

    static class Node<template> {
        template item;
        Node<template> next;
        Node<template> previous;

        Node() {
        }

        Node(template item) {
            this.item = item;
        }
    }

    Container() {
    }

    Container(template... items) {
        for (template item : items) add(item);
    }

    boolean is_empty() {
        return (head == null);
    }

    void add(template item) {
        Node<template> current = head;

        if (is_empty()) {
            head = new Node<>(item);
        }
        else {
            while (current.next != null) {
                current = current.next;
            }

            Node<template> new_node = new Node<>(item);
            new_node.previous = current;
            current.next = new_node;
        }
    }

    boolean remove(template item) {
        if (is_empty())
            return false;
        else {
            Node<template> current = head;

            while (current != null && current.item != item) {
                current = current.next;
            }
            if (current == null)
                return false;

            if (current.previous != null)
                current.previous.next = current.next;
            if (current.next != null)
                current.next.previous = current.previous;

        }

        return true;
    }

    void print() {
        Node<template> current = head;

        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }
}


