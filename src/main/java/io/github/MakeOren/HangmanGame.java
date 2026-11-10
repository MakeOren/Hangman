package io.github.MakeOren;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HangmanGame {

    private static final int MAX_FAILED_ATTEMPT = 6;
    private final Set<Character> wrongGuesses;
    private final String answer;
    private String hint;


    public HangmanGame(List<String> dictionary) {
        this.wrongGuesses = new LinkedHashSet<>();
        Random random = new Random();
        this.answer = dictionary.get(random.nextInt(0, dictionary.size()));
        this.hint = "*".repeat(answer.length());
    }

    public void makeMove(char letter) {
        if (isLetterEntered(letter)) {
            return;
        }

        if (answerContainsLetter(letter)) {
            updateHint(letter);
        } else {
            wrongGuesses.add(letter);
        }
    }

    public String getHint() {
        return hint;
    }

    public String getAnswer() {
        return answer;
    }

    public int getFailedAttemptCount() {
        return wrongGuesses.size();
    }

    public Set<Character> getWrongGuesses() {
        return new LinkedHashSet<>(wrongGuesses);
    }

    public boolean isWin() {
        return hint.equals(answer);
    }

    public boolean isLose() {
        return getFailedAttemptCount() >= MAX_FAILED_ATTEMPT;
    }

    public boolean isGameOver() {
        return isWin() || isLose();
    }

    private void updateHint(char letter) {
        StringBuilder hintBuilder = new StringBuilder(hint);

        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == letter) {
                hintBuilder.setCharAt(i, letter);
            }
        }

        hint = hintBuilder.toString();
    }

    private boolean isLetterEntered(char letter) {
        return hint.contains(String.valueOf(letter)) || wrongGuesses.contains(letter);
    }

    private boolean answerContainsLetter(char letter) {
        return answer.contains(String.valueOf(letter));
    }
}
