package io.github.MakeOren;

import io.github.MakeOren.exception.LoadDictionaryException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Hangman {

    private static final String PATH = "dictionary.txt";
    private static final String PATH_LOG = "log.txt";
    private static final int MIN_WORD_LENGTH = 5;
    private static final int MAX_WORD_LENGTH = 9;

    public static void main(String[] args) throws IOException {
        try(PrintWriter log = new PrintWriter(new BufferedWriter(new FileWriter(PATH_LOG, StandardCharsets.UTF_8, true)))) {
            startSessionAndHandleErrors(log);
        }
    }

    private static void startSessionAndHandleErrors(PrintWriter log) {
        try {
            startSession();
        } catch (LoadDictionaryException e) {
            e.printStackTrace(log);

            HangmanConsole.printLoadDictionaryError();
        } catch (Exception e) {
            e.printStackTrace(log);

            HangmanConsole.printSystemError();
        }
    }

    private static void startSession() {
        HangmanConsole.printStartMessage();
        List<String> dictionary = HangmanDictionary.getDictionary(PATH, MIN_WORD_LENGTH, MAX_WORD_LENGTH);

        while (HangmanConsole.wantsToPlay()) {
            HangmanGame hangmanGame = new HangmanGame(dictionary);
            playRound(hangmanGame);
        }
    }

    private static void playRound(HangmanGame hangmanGame) {

        while (!hangmanGame.isGameOver()) {
            HangmanConsole.printState(getGameState(hangmanGame));

            char letter = HangmanConsole.readRusLetter();

            hangmanGame.makeMove(letter);
        }

        HangmanConsole.printState(getGameState(hangmanGame));

        if (hangmanGame.isWin()) {
            HangmanConsole.printWin(hangmanGame.getAnswer());
        }

        if (hangmanGame.isLose()) {
            HangmanConsole.printLose(hangmanGame.getAnswer());
        }
    }

    private static GameState getGameState(HangmanGame hangmanGame) {
        return new GameState(
                hangmanGame.getWrongGuesses(),
                hangmanGame.getHint(),
                HangmanStage.get(hangmanGame.getFailedAttemptCount()),
                hangmanGame.getFailedAttemptCount());
    }
}
