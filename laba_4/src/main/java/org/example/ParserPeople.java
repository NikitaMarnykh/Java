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
}
