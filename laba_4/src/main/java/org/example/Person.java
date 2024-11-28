package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    // Поля (атрибуты) класса Person
    private final int id;                    // Уникальный идентификатор человека
    private final String name;               // Имя человека
    private final String gender;             // Пол человека
    private final Division division;         // Подразделение, к которому принадлежит человек
    private final int salary;                // Зарплата человека
    private final Date date_of_birth;        // Дата рождения человека

    // Конструктор класса Person
    Person(int id, String name, String gender,
           Division division, int salary, Date date_of_birth) {
        this.id = id;                          // Инициализация идентификатора
        this.name = name;                      // Инициализация имени
        this.gender = gender;                  // Инициализация пола
        this.division = division;              // Инициализация подразделения
        this.salary = salary;                  // Инициализация зарплаты
        this.date_of_birth = date_of_birth;    // Инициализация даты рождения
    }

    // Геттеры для доступа к приватным полям
    int getId() {
        return id;                             // Возвращаем идентификатор
    }

    String getName() {
        return name;                           // Возвращаем имя
    }

    String getGender() {
        return gender;                         // Возвращаем пол
    }

    Division getDivision() {
        return division;                       // Возвращаем подразделение
    }

    double getSalary() {
        return salary;                         // Возвращаем зарплату
    }

    Date getDateOfBirth() {
        return date_of_birth;                  // Возвращаем дату рождения
    }

    // Метод для получения строкового представления объекта Person
    String dataToString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); // Определяем формат даты
        String formattedDate = dateFormat.format(date_of_birth); // Преобразуем дату в строку

        // Формируем строку с информацией о человеке
        return "ID: " + id +
                ", Name: " + name +
                ", Gender: " + gender +
                ", Division: [" + division.dataToString() + "]" + // Включаем строковое представление подразделения
                ", Salary: " + salary +
                ", Date of Birth: " + formattedDate; // Включаем форматированную дату
    }
}
