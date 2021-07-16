package unit;

import com.test.spring.parser.Parser;
import com.test.spring.parser.ResultValidation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;

public class ParserTest {
    private final Parser parser = new Parser(file -> new ResultValidation(true, ""));
    private final Parser parserNotValid = new Parser(file -> new ResultValidation(false, "file is not exist"));

    @Test
    public void shouldReturnEmptyMapIfFileEmpty() {
        HashMap<String, Integer> map = parser.countUniqueWordsFromFile(new File("resources/fileEmpty.txt"));
        Assertions.assertEquals(map, new HashMap<String, Integer>());
    }

    @Test
    public void shouldThrowExceptionIfFileNotExist() {
        Assertions.assertThrows(RuntimeException.class , () -> {
            HashMap<String, Integer> map = parser.countUniqueWordsFromFile(new File("resources/fileTesty.txt"));
        });
    }

    @Test
    public void shouldCountWordsFromFile() {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("file", 8);
            Assertions.assertEquals(map, parser.countUniqueWordsFromFile(new File("resources/fileTest.txt")));
    }

    @Test
    public void shouldCountWordsFromInputStream() throws IOException {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("file", 8);
        InputStream inputStream = new FileInputStream("resources/fileTest.txt");
        Assertions.assertEquals(map, parser.countUniqueWords(inputStream));
    }

    @Test
    public void shouldWorkingOnBigFile() {
        HashMap<String, Integer> map = parser.countUniqueWordsFromFile(new File("resources/fileBig.txt"));
        Assertions.assertNotEquals(map, new HashMap<String, Integer>());
    }

    @Test
    public void shouldThrowExceptionIfFileNonValid() {
        Assertions.assertThrows(RuntimeException.class , () -> {
            HashMap<String, Integer> map = parserNotValid.countUniqueWordsFromFile(new File("resources/fileTesty.txt"));
        });
    }

    @Test
    public void shouldReturnMapToString() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("file", 8);
        Assertions.assertEquals("file - 8\n", parser.mapOfWordsToString(map));

    }
}
