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
    }

    Container() {
        head = new Node<>();
    }

        boolean is_empty() {
        return head.is_empty();
    }
}
