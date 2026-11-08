package io.github.MakeOren;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HangmanGame {

    private static final int MAX_FAILED_ATTEMPT = 6;
    private final Set<Character> wrongGuesses;
    private String answer;
    private String hint;
    private Random random;
    private int failedAttemptCount;
    private boolean isWin;
    private boolean isGameOver;


    public HangmanGame(List<String> dictionary) {
        this.wrongGuesses = new HashSet<>();
        this.failedAttemptCount = 0;
        this.random = new Random();
        this.answer = dictionary.get(random.nextInt(0, dictionary.size()));
        this.hint = "*".repeat(answer.length());
        this.isWin = false;
        this.isGameOver = false;
    }

    public void makeMove(char letter) {
        if (containsLetter(letter) && !hint.contains(String.valueOf(letter))) {
            updateHint(letter);
        } else if(!containsWrongGuesses(letter) && !hint.contains(String.valueOf(letter))) {
            failedAttemptCount ++;
            wrongGuesses.add(letter);
        }

        updateWinStatus();
        updateGameOver();

    }

    public String getHint() {
        return hint;
    }

    public String getAnswer() {
        return answer;
    }

    public int getFailedAttemptCount() {
        return failedAttemptCount;
    }

    public String getHangman() {
        return HangmanStage.get(failedAttemptCount);
    }

    public boolean isWin() {
        return isWin;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private void updateHint(char letter) {
        StringBuilder hintBuilder = new StringBuilder(hint);

        for (int i = 0; i < answer.length(); i++) {
            if(answer.charAt(i) == letter) {
                hintBuilder.setCharAt(i, letter);
            }
        }

        hint = hintBuilder.toString();
    }

    private boolean containsLetter(char letter) {
        return answer.contains(Character.toString(letter));
    }

    private boolean containsWrongGuesses(char letter) {
        return wrongGuesses.contains(letter);
    }

    private void updateWinStatus() {
        if (hint.equals(answer)) {
            isWin = true;
        }
    }

    private void updateGameOver() {
        if (isWin || failedAttemptCount >= MAX_FAILED_ATTEMPT) {
            isGameOver = true;
        }
    }

    private static class HangmanStage {
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

        private static String get(int countError) {
            return STAGES[countError];
        }
    }
}
