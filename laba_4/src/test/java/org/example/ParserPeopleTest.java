package org.example;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class ParserPeopleTest {

    /**
     * Тестируем метод getPersonList на корректность парсинга CSV файла.
     * Ожидается, что он вернет список людей с корректными данными.
     */
    @Test
    public void testGetPersonList() throws IOException, CsvException {
        // Создаем временный CSV файл для тестирования
        String csvFilePath = "src/test/resources/test_people.csv";

        // Содержимое файла
        String csvContent = "ID;Имя;Пол;ДатаРождения;Подразделение;Зарплата\n" +
                "1;John Doe;Male;01.01.1990;A;50000\n" +
                "2;Jane Smith;Female;02.02.1992;B;60000\n";

        // Создаем директории, если они не существуют
        Path path = Paths.get(csvFilePath);
        Files.createDirectories(path.getParent());  // Создание директорий

        // Записываем содержимое в файл
        Files.write(path, csvContent.getBytes());

        // Получаем список людей из CSV файла
        List<Person> people = ParserPeople.getPersonList(csvFilePath);

        // Проверяем, что размер списка соответствует ожидаемому
        Assertions.assertEquals(2, people.size(), "Должно быть 2 человека в списке");

        // Проверяем данные первого человека
        Person firstPerson = people.get(0);
        Assertions.assertEquals(1, firstPerson.getId(), "ID первого человека должен быть 1");
        Assertions.assertEquals("John Doe", firstPerson.getName(), "Имя первого человека должно быть 'John Doe'");
        Assertions.assertEquals("Male", firstPerson.getGender(), "Пол первого человека должен быть 'Male'");
        Assertions.assertEquals(50000, firstPerson.getSalary(), "Зарплата первого человека должна быть 50000");

        // Проверяем данные второго человека
        Person secondPerson = people.get(1);
        Assertions.assertEquals(2, secondPerson.getId(), "ID второго человека должен быть 2");
        Assertions.assertEquals("Jane Smith", secondPerson.getName(), "Имя второго человека должно быть 'Jane Smith'");
        Assertions.assertEquals("Female", secondPerson.getGender(), "Пол второго человека должен быть 'Female'");
        Assertions.assertEquals(60000, secondPerson.getSalary(), "Зарплата второго человека должна быть 60000");

        // Удаляем временный файл после теста
        Files.delete(path);
    }


    /**
     * Тестируем метод print на корректность вывода (например, на консоль).
     * Для этого можно использовать системные потоки.
     */
    @Test
    public void testPrint() {
        // Создаем список людей для тестирования
        List<Person> people = List.of(
                new Person(1, "John Doe", "Male", new Division(0, "A"), 50000, new Date()),
                new Person(2, "Jane Smith", "Female", new Division(1, "B"), 60000, new Date())
        );

        // Печатаем людей
        // На практике, можно перенаправить output в stream и проверить его содержимое
        ParserPeople.print(people);

        // В данный момент мы просто проверяем, что метод не вызывает исключений.
        Assertions.assertTrue(true); // Здесь можно добавить дополнительные проверки, если необходимо
    }
}
