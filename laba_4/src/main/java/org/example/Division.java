package org.example;

public class Division {
  // Поля (атрибуты) класса Division
  private final int id;              // Уникальный идентификатор подразделения
  private final String name;         // Название подразделения

  // Конструктор класса Division
  Division(int id, String name) {
    this.id = id;                    // Инициализация идентификатора
    this.name = name;                // Инициализация названия
  }

  // Геттер для получения идентификатора
  int getId()  { 
    return id;                       // Возвращаем идентификатор
  }

  // Геттер для получения названия
  String getName() { 
    return name;                     // Возвращаем название
  }

  // Метод для получения строкового представления объекта Division
    String dataToString() { 
      return "ID: " + id + ", Name: " + name; // Формируем строку с информацией о подразделении
    }
}
