package io.github.MakeOren;


import io.github.MakeOren.exception.InvalidUserInputException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Hangman {

    private final static String PATH = "dictionary.txt";
    private static final String PATH_LOG = "log.txt";
    private static PrintWriter log;

    public static void main(String[] args) {
        try {
            log = new PrintWriter(new BufferedWriter(new FileWriter(PATH_LOG, StandardCharsets.UTF_8)));
            List<String> dictionary = HangmanDictionary.getDictionary(PATH);
            HangmanConsole.printStartMessage();

            boolean running = HangmanConsole.askToStartGame();

            while (running) {
                HangmanGame hangmanGame = new HangmanGame(dictionary);

                while (true) {
                    HangmanConsole.printHangman(hangmanGame.getHangman());
                    HangmanConsole.printHint(hangmanGame.getHint());
                    HangmanConsole.printFailedAttemptCount(hangmanGame.getFailedAttemptCount());

                    char userAnswer;
                    try {
                        userAnswer = HangmanConsole.readGuess();
                    } catch (InvalidUserInputException e) {
                        HangmanConsole.printInvalidInputMessage();
                        continue;
                    }

                    hangmanGame.makeMove(userAnswer);

                    if (hangmanGame.isGameOver()) {
                        if (!hangmanGame.isWin()) {
                            HangmanConsole.printHangman(hangmanGame.getHangman());
                        }
                       HangmanConsole.printGameOver(hangmanGame.getAnswer(), hangmanGame.getFailedAttemptCount(), hangmanGame.isWin());
                       running = HangmanConsole.askToStartGame();
                       break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(log);
        } finally {
            if (log != null) {
                log.close();
            }
        }
    }
}
