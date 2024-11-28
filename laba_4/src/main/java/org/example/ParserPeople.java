package org.example;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ParserPeople {
    // HashMap для хранения идентификаторов подразделений
    private static final HashMap<String, Integer> divisionId = new HashMap<>();

    static {
        // Заполнение HashMap divisionId буквами A-Z, соответствующими им номерами от 0 до 25
        for (char c = 'A'; c <= 'Z'; c++) {
            divisionId.put(String.valueOf(c), c - 'A');
        }
    }

        /**
     * Парсит CSV-файл и возвращает список объектов Person.
     *
     * @param path Путь к CSV-файлу для чтения.
     * @return Список объектов Person.
     * @throws IOException Если возникает ошибка ввода-вывода при чтении файла.
     * @throws CsvException Если возникает ошибка при разборе CSV файла.
     */
    static List<Person> getPersonList(String path) throws IOException, CsvException {
        // Создаем CSVParser с разделителем ';'
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        // Инициализируем список людей, который будет возвращён
        List<Person> people = new ArrayList<>();

        // Используем try-with-resources для обеспечения закрытия ридера
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(path))
                .withCSVParser(parser) // Устанавливаем CSVParser с нашим разделителем
                .build()) {
            String[] nextLine;

            // Чтение заголовка и игнорирование его
            reader.readNext();

            // Чтение каждой строки данных
            while ((nextLine = reader.readNext()) != null) {
                // Убедимся, что строка содержит достаточно полей
                if (nextLine.length > 5) {
                    // Подготавливаемся к разбору даты рождения
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date dateValue = null; // Инициализация dateValue

                    try {
                        // Парсим строку даты из CSV и обрабатываем возможные ошибки разбора
                        dateValue = dateFormat.parse(nextLine[3]);
                    } catch (ParseException e) {
                        // Логируем ошибку разбора даты и пропускаем эту запись
                        System.err.println("Ошибка разбора даты: " + e.getMessage());
                        continue; // Пропускаем эту запись, если разбор даты не удался
                    }

                    // Создаём и добавляем объект Person в список
                    people.add(new Person(
                            Integer.parseInt(nextLine[0]), // ID
                            nextLine[1],                   // Имя
                            nextLine[2],                   // Пол
                            new Division(divisionId.get(nextLine[4]), nextLine[4]), // Подразделение
                            Integer.parseInt(nextLine[5]), // Зарплата
                            dateValue                      // Дата рождения
                    ));
                }
            }
        }

        // Возвращаем заполненный список объектов Person
        return people;
    }
}
