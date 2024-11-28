package org.example;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Main {
    // Вложенный класс для получения файла из ресурсов
    public static class GetFile {

        /**
         * Получает файл из ресурсов по указанному имени.
         *
         * @param fileName Имя файла, который необходимо получить.
         * @return Файл из ресурсов.
         * @throws IllegalArgumentException если файл не найден.
         */
        protected File getFileFromResources(String fileName) {
            // Получение ClassLoader для текущего класса
            ClassLoader classLoader = getClass().getClassLoader();

            // Попытка получить ресурс по имени файла
            URL resource = classLoader.getResource(fileName);
            if (resource == null) {
                // Генерация исключения, если файл не найден
                throw new IllegalArgumentException("Файл не найден!");
            } else {
                // Возвращаем файл, если он найден
                return new File(resource.getFile());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // Получаем файл из ресурсов
        File file = new GetFile().getFileFromResources("foreign_names.csv");

        // Получаем путь к файлу в виде строки
        String path = String.valueOf(file);

        // Читаем список людей из CSV-файла
        List<Person> people = ParserPeople.getPersonList(path);

        // Выводим информацию о каждом человеке
        ParserPeople.print(people);
    }
}
