public class Container<template> {
    private Node<template> node;

    static class Node<template> {
        template item;
        Node<template> next;
        Node<template> previous;

        Node() {}
    }

    Container() {
        node = new Node<>();
    }
}
