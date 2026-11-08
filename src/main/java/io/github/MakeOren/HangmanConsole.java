package io.github.MakeOren;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HangmanConsole {

    private static final String START = "1";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String REGEX = "[а-яёА-ЯЁ]";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static void printStartMessage() {
        System.out.println("Добро пожаловать в игру 'Виселица'");
    }

    public static boolean wantsToPlay() {
        System.out.printf("Введите %s чтобы начать новую игру \n", START);
        System.out.println("Введите любой символ чтобы выйти из игры");

        String answer = scanner.nextLine();

        return answer.equals(START);
    }

    public static void printInvalidInputMessage() {
        System.out.println("Некорректный ввод, повторите попытку");
    }

    public static char readRusLetter() {
        String letter;

        while (true) {
            printRules();
            letter = scanner.nextLine();

            if (isOneRusLetter(letter)) {
                return letter.toLowerCase().charAt(0);
            }

            printInvalidInputMessage();
        }
    }

    public static void printState(String hangman, String hint, int failedAttemptCount) {
        printHangman(hangman);
        printHint(hint);
        printFailedAttemptCount(failedAttemptCount);
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

    public static void printWin(String answer) {
        System.out.println("Вы победили!");
        System.out.println("Отгаданное слово: " + answer);
    }

    public static void printLose(String answer) {
        System.out.println("Вы проиграли!");
        System.out.println("Загаданное слово: " + answer);
    }

    public static void printLoadDictionaryError() {
        System.out.println("Программа завершилась из-за системной ошибки при загрузке словаря");
    }

    public static void printSystemError() {
        System.out.println("Программа завершилась из-за системной ошибки");
    }

    private static void printRules() {
        System.out.println("Введите любую русскую букву");
    }

    private static boolean isOneRusLetter(String letter) {
        Matcher matcher = PATTERN.matcher(letter);

        return matcher.matches();
    }

}
