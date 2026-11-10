package io.github.MakeOren;

import io.github.MakeOren.exception.LoadDictionaryException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class HangmanDictionary {

    public static List<String> getDictionary(String path, int minWordLength, int maxWordLength) {
        List<String> words = loadDictionary(path);

        return words.stream()
                .map(word-> word.trim().toLowerCase(Locale.ROOT))
                .filter(word-> word.length() >= minWordLength && word.length() <= maxWordLength)
                .toList();
    }

    private static List<String> loadDictionary(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new LoadDictionaryException("Error loading the dictionary: " + path, e);
        }
    }
}
