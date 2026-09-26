package io.github.MakeOren;

import io.github.MakeOren.exception.InvalidUserInputException;

import java.util.Scanner;

public class HangmanConsole {

    private static final Scanner scanner;

    static  {
        scanner = new Scanner(System.in);
    }

    public static void printStartMessage() {
        System.out.println("Добро пожаловать в игру 'Виселица'");
    }

    public static boolean askToStartGame() {
        System.out.println("Введите 1 чтобы начать новую игру");
        System.out.println("Введите любой символ чтобы выйти из игры");

        String answer = scanner.nextLine();

        return answer.equals("1");
    }

    public static void printInvalidInputMessage() {
        System.out.println("Некорректный ввод, повторите попытку");
    }

    public static char readGuess() {
        printRules();
        String guess = scanner.nextLine();
        guess = guess.toLowerCase();

        if(guess.matches("[а-яё]")) {
            return guess.toCharArray()[0];
        }else {
            throw new InvalidUserInputException("Invalid user input in the HangmanConsole.readGuess method");
        }
    }

    private static void printRules() {
        System.out.println("Введите любую русскую букву");
    }

    public static void printHint(String hint) {
        System.out.println("Подсказка: " + hint);
    }

    public static void printFailedAttemptCount(int failedAttemptCount) {
        System.out.println("Количество ошибочных попыток: " + failedAttemptCount);
    }

    public static void printHangman(String hangman) {
        System.out.println(hangman);
    }

    public static void printGameOver(String answer, int failedAttemptCount, boolean isWin) {
        if (isWin) {
            System.out.println("Вы победили!");
            System.out.println("Отгаданное слово: " + answer);
            printFailedAttemptCount(failedAttemptCount);
        } else {
            System.out.println("Вы проиграли!");
            System.out.println("Загаданное слово: " + answer);
            printFailedAttemptCount(failedAttemptCount);
        }
    }
}
