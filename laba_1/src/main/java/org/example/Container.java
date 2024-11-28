package org.example;

// Общий класс Container, реализующий список
public class Container<template> {
    private Node<template> head; // Указатель на первый элемент списка

    // Вложенный класс Node, представляющий элемент списка
    static class Node<template> {
        template item; // Хранит значение элемента
        Node<template> next; // Указатель на следующий элемент
        Node<template> previous; // Указатель на предыдущий элемент

        // Конструктор без аргументов
        Node() {
        }

        // Конструктор с одним аргументом для инициализации item
        Node(template item) {
            this.item = item;
        }
    }

    // Конструктор Container без параметров
    Container() {
    }

    // Конструктор Container с переменным числом аргументов
    Container(template... items) {
        for (template item : items) add(item); // Добавляем каждый элемент в контейнер
    }

    // Метод для проверки, пуст ли контейнер
    boolean is_empty() {
        return (head == null); // Возвращает true, если head равно null
    }

    // Метод для добавления элемента в конец контейнера
    void add(template item) {
        Node<template> current = head; // Начинаем с первого элемента

        if (is_empty()) { // Если контейнер пуст
            head = new Node<>(item); // Создаем новый элемент и устанавливаем его как head
        } else {
            // Перемещение к последнему элементу списка
            while (current.next != null) {
                current = current.next;
            }

            // Создаем новый элемент и обновляем ссылки
            Node<template> new_node = new Node<>(item);
            new_node.previous = current; // Устанавливаем ссылку на предыдущий элемент
            current.next = new_node; // Устанавливаем ссылку на новый элемент
        }
    }

    // Метод для извлечения элемента из контейнера по значению
    Node<template> get(template item) {
        Node<template> current = head; // Начинаем с первого элемента
        // Перебираем список, пока не найдем нужный элемент
        while (current != null && current.item != item) {
            current = current.next; // Переходим к следующему элементу
        }

        return current; // Возвращаем найденный элемент или null, если не найден
    }

    // Метод для удаления элемента из контейнера по значению
    boolean remove(template item) {
        if (is_empty()) { // Проверка на пустоту контейнера
            return false; // Нельзя удалить элемент из пустого контейнера
        }

        // Если элемент, который нужно удалить, - это head
        else if (item == head.item) {
            head = head.next; // Обновляем head на следующий элемент

            if (!is_empty()) { // Если контейнер не пуст после удаления
                head.previous = null; // Устанавливаем предыдущий элемент для нового head
            }

            return true; // Успешное удаление
        } else {
            Node<template> current = head.next; // Начинаем поиск со второго элемента

            // Поиск элемента в списке
            while (current != null && current.item != item) {
                current = current.next; // Переходим к следующему элементу
            }
            if (current == null) { // Если элемент не найден
                return false; // Возвращаем false
            }

            // Удаляем найденный элемент, обновляя ссылки
            current.previous.next = current.next; // Соединяем предыдущий элемент с следующим
            if (current.next != null) // Если существует следующий элемент
                current.next.previous = current.previous; // Устанавливаем ссылку на предыдущий элемент для следующего

        }

        return true; // Успешное удаление
    }

    // Метод для вывода всех элементов контейнера
    void print() {
        Node<template> current = head; // Начинаем с первого элемента

        // Перебираем все элементы и выводим их
        while (current != null) {
            System.out.println(current.item); // Выводим значение элемента
            current = current.next; // Переходим к следующему элементу
        }
    }
}
