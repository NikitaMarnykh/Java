/*
 * Общий класс Container реализован на основе списка и содержит:
 * поле Node, содержащее экземпляр общего класса Node.
 * Методы:
 * Container() - конструктор класса без аргументов.
 * Container(template... items) - конструктор класса с переменным числом аргументов.
 * is_empty()  - проверка на пустоту, тип boolean.
 * add(template item) - добавление значение в конец Container, тип void.
 * remove(template item) - удаление значение из Container, тип boolean.
 * get(template item) - извлечение значения из Container, тип Node<template>.
 * print() - вывод всех элементов, содержащихся в Container, тип void.
 * Общий класс Node содержит:
 * поля:
 * item- для хранения значения, тип template.
 * next - для хранения ссылки на следующее значение, тип Node<template>.
 * previous - для хранения ссылки на предыдущее значение, тип Node<template>.
 * Конструкторы:
 * Node() - конструктор без аргументов.
 * Node(template item) - конструктор с одним аргументов item.
 * */

package org.example;


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
        } else {
            while (current.next != null) {
                current = current.next;
            }

            Node<template> new_node = new Node<>(item);
            new_node.previous = current;
            current.next = new_node;
        }
    }

    Node<template> get(template item) {
        Node<template> current = head;
        while (current != null && current.item != item) {
            current = current.next;
        }

        return current;
    }

    boolean remove(template item) {
        if (is_empty()) {
            return false;
        }
        else if (item == head.item) {
            head = head.next;

            if (!is_empty())
            {
                head.previous = null;
            }

            return true;
        }
        else {
            Node<template> current = head.next;

            while (current != null && current.item != item) {
                current = current.next;
            }
            if (current == null) {
                return false;
            }

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
