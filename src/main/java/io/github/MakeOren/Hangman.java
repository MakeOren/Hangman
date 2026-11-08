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
        List<String> dictionary = HangmanDictionary.getDictionary(PATH);

        while (HangmanConsole.wantsToPlay()) {
            HangmanGame hangmanGame = new HangmanGame(dictionary);
            playRound(hangmanGame);
        }
    }

    private static void playRound(HangmanGame hangmanGame) {

        while (!hangmanGame.isGameOver()) {
            HangmanConsole.printState(hangmanGame.getHangman(), hangmanGame.getHint(), hangmanGame.getFailedAttemptCount());

            char letter = HangmanConsole.readRusLetter();

            hangmanGame.makeMove(letter);
        }

        HangmanConsole.printState(hangmanGame.getHangman(), hangmanGame.getHint(), hangmanGame.getFailedAttemptCount());

        if (hangmanGame.isWin()) {
            HangmanConsole.printWin(hangmanGame.getAnswer());
        } else {
            HangmanConsole.printLose(hangmanGame.getAnswer());
        }
    }
}
