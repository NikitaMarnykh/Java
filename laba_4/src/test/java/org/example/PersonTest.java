package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonTest {

  @Test
  public void testPersonConstructor() {
    // Тестируем конструктор класса Person
    int expectedId = 1;
    String expectedName = "John Doe";
    String expectedGender = "Male";
    Division expectedDivision = new Division(0, "A");
    int expectedSalary = 50000;
    Date expectedDateOfBirth = new Date();  // Здесь можно использовать статичную дату для детального тестирования

    // Создаем новый объект Person
    Person person = new Person(expectedId, expectedName, expectedGender,
                               expectedDivision, expectedSalary, expectedDateOfBirth);
    // Проверяем, что все поля инициализировались правильно
    Assertions.assertEquals(expectedId, person.getId(), "ID должен быть равен " + expectedId);
    Assertions.assertEquals(expectedName, person.getName(), "Имя должно быть '" + expectedName + "'");
    Assertions.assertEquals(expectedGender, person.getGender(), "Пол должен быть '" + expectedGender + "'");
    Assertions.assertEquals(expectedDivision, person.getDivision(), "Подразделение должно совпадать");
    Assertions.assertEquals(expectedSalary, person.getSalary(), "Зарплата должна быть равна " + expectedSalary);
    Assertions.assertEquals(expectedDateOfBirth, person.getDateOfBirth(), "Дата рождения должна совпадать");
  }

  @Test
  public void testDataToString() {
    // Тестируем метод dataToString
    int id = 2;
    String name = "Jane Smith";
    String gender = "Female";
    Division division = new Division(1, "B");
    int salary = 60000;
    Date dateOfBirth = new Date(); // Здесь можно использовать статичную дату для детального тестирования

    // Создаем новый объект Person
    Person person = new Person(id, name, gender, division, salary, dateOfBirth);

    // Ожидаемое строковое представление, имя и дата можно форматировать статично
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    String formattedDate = dateFormat.format(dateOfBirth);
    String expectedOutput = "ID: " + id +
                ", Name: " + name +
                ", Gender: " + gender +
                ", Division: [" + division.dataToString() + "]" +
                ", Salary: " + salary +
                ", Date of Birth: " + formattedDate;
    // Проверяем, что метод возвращает правильный результат
    Assertions.assertEquals(expectedOutput, person.dataToString(),
              "Строковое представление должно соответствовать ожидаемому формату");
    }
}
