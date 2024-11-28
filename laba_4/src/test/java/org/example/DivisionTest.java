package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DivisionTest {

  @Test
  public void testDivisionConstructor() {
    // Тестируем конструктор класса Division
    int expectedId = 1;
    String expectedName = "Test Division";

    // Создаем новый объект Division
    Division division = new Division(expectedId, expectedName);

    // Проверяем, что идентификатор и название инициализировались правильно
    Assertions.assertEquals(expectedId, division.getId(), "ID должен быть равен" + expectedId);
    Assertions.assertEquals(expectedName, division.getName(), "Название должео быть" + expectedName);
  }

}
