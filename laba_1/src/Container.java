public class Container<template> {
    private Node<template> head;

    static class Node<template> {
        template item;
        Node<template> next;
        Node<template> previous;

        Node() {}

        boolean is_empty() {
            return (item == null);
        }

        void add(template item) {

            if (is_empty())
                this.item = item;
            else {
                Node<template> current = this;

                while (current.next != null) {
                    current = current.next;
                }

                Node<template> new_node = new Node<>();
                new_node.item = item;
                new_node.previous = current;
                current.next = new_node;
            }
        }
    }

    Container() {
        head = new Node<>();
    }

    Container(template... items) {
        head = new Node<>();
        for (template item : items) head.add(item);
    }

    void add(template item) {
        head.add(item);
    }

        boolean is_empty() {
        return head.is_empty();
    }
}
