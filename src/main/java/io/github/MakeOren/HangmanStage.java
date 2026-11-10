package io.github.MakeOren;

public class HangmanStage {
    private static final String[] STAGES = {
            """
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
              |   |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
             /|   |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
             /|\\  |
                  |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
             /|\\  |
             /    |
                  |
            =========""",
            """
              +---+
              |   |
              O   |
             /|\\  |
             / \\  |
                  |
            ========="""
    };

    public static String get(int countError) {
        return STAGES[countError];
    }
}
