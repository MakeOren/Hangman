package io.github.MakeOren;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HangmanGame {

    private String answer;
    private String hint;
    private Random random;
    private int failedAttemptCount;
    private boolean isWin;
    private boolean isGameOver;
    private final static int MAX_FAILED_ATTEMPT = 6;
    private final Set<Character> wrongGuessesSet;


    public HangmanGame(List<String> dictionary) {
        this.wrongGuessesSet = new HashSet<>();
        this.failedAttemptCount = 0;
        this.random = new Random();
        this.answer = dictionary.get(random.nextInt(0, dictionary.size()));
        this.hint = "*".repeat(answer.length());
        this.isWin = false;
        this.isGameOver = false;
    }

    public void makeMove(char userLetter) {
        if (containsLetter(userLetter) && !hint.contains(String.valueOf(userLetter))) {
            calculateHint(userLetter);
        } else if(!containsWrongGuesses(userLetter) && !hint.contains(String.valueOf(userLetter))) {
            failedAttemptCount += 1;
            wrongGuessesSet.add(userLetter);
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
        return HangmanStage.getHangmanStage(failedAttemptCount);
    }

    public boolean isWin() {
        return isWin;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private void calculateHint(char userLetter) {
        StringBuilder hintBuilder = new StringBuilder(hint);

        for (int i = 0; i < answer.length(); i++) {
            if(answer.charAt(i) == userLetter) {
                hintBuilder.setCharAt(i, userLetter);
            }
        }

        hint = hintBuilder.toString();
    }

    private boolean containsLetter(char letter) {
        return answer.contains(Character.toString(letter));
    }

    private boolean containsWrongGuesses(char letter) {
        return wrongGuessesSet.contains(letter);
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
                "  +---+\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "=========",
                "  +---+\n" +
                        "  |   |\n" +
                        "  O   |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "========="
        };

        private static String getHangmanStage(int countError) {
            return STAGES[countError];
        }
    }
}
