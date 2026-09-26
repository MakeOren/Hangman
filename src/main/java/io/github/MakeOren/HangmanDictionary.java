package io.github.MakeOren;

import io.github.MakeOren.exception.LoadDictionaryException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class HangmanDictionary {
    private static final int MIN_WORD_LENGTH = 5;
    private static final int MAX_WORD_LENGTH = 9;


    private static List<String> loadDictionary(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new LoadDictionaryException("Error loading the dictionary in the HangmanDictionary.loadDictionary method", e);
        }
    }

    public static List<String> getDictionary(String path) {
        List<String> words = loadDictionary(path);

        return words.stream()
                .map(word-> word.trim().toLowerCase(Locale.ROOT))
                .filter(word-> word.length() >= MIN_WORD_LENGTH && word.length() <= MAX_WORD_LENGTH)
                .toList();
    }
}
