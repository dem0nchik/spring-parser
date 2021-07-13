package com.test.spring.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;

@Service
public class Parser {
    private Validator validator;

    @Autowired
    public Parser(@Qualifier("fileValidator") Validator validator) {
        this.validator = validator;
    }

    public HashMap<String, Integer> countUniqueWordsFromFile(File file)  {
        HashMap<String, Integer> map = new HashMap<>();
        ResultValidation validation = validator.validate(file);

        if(validation.isValid()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                map = countUniqueWords(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Exception parse file");
            }
        } else {
            System.out.println(validation.getError());
                if(validation.getError().equals("file is not exist"))
                    throw new RuntimeException("Error parse file");
        }
        return map;
    }

    public HashMap<String, Integer> countUniqueWords(InputStream inputStream) throws IOException {
        HashMap<String, Integer> map = new HashMap<>();

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8")) {
            int data = inputStreamReader.read();
            String word = "";

            while (data != -1) {
                char sym = (char) data;

                if (sym != '\r' && sym != '\n'
                    && sym != ' ' && sym != ','
                    && sym != '.' && sym != ';'
                ) {
                    word += sym;
                } else if(word.length() != 0) {
                    if (map.containsKey(word))
                        map.put(word, map.get(word) + 1);
                    else
                        map.put(word, 1);

                    word = "";
                } else {
                    word = "";
                }
                data = inputStreamReader.read();
            }
            if(word.length() != 0)
                if (map.containsKey(word))
                    map.put(word, map.get(word) + 1);
                else
                    map.put(word, 1);


        } catch (IOException e) {
            throw new IOException("Exception parse data");
        }
        return map;
    }

    public String mapOfWordsToString(HashMap<String, Integer> map) {
        StringBuilder data = new StringBuilder();
        for (String key : map.keySet()) {
            data.append(key).append(" - ").append(map.get(key)).append("\n");
        }
        return data.toString();
    }

    public void printMapOfWords(HashMap<String, Integer> map) {
        map.keySet().forEach(key -> System.out.println(key + " - " + map.get(key)));
    }
}
