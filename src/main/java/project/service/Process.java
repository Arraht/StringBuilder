package project.service;

import project.builder.CustomStringBuilder;

import java.util.Arrays;
import java.util.Scanner;

public class Process {
    private final Scanner scanner = new Scanner(System.in);
    private final CustomStringBuilder stringBuilder = new CustomStringBuilder();
    private boolean running = true;

    public void process() {
        while (running) {
            System.out.println("""
                    Для удаления ввести число 0
                    Для добавления слова ввести число 1
                    Для вывода строки ввести число 2
                    Для вывода истории ввода ввести число 3
                    Для выхода ввести число 4
                    """);
            String command = scanner.nextLine().trim();

            if (!command.matches("[0-4]")) {
                System.out.println("Ошибка! Введите только одну цифру от 0 до 4.\n");
                continue;
            }

            switch (command) {
                case "0" -> callUndo();
                case "1" -> callAppend();
                case "2" -> callGetString();
                case "3" -> callHistory();
                case "4" -> running = false;
            }
        }
    }

    private void callHistory() {
        System.out.println("Выводим историю ввода...");
        String[] output;
        try {
            output = stringBuilder.getHistoryAsArray();
        } catch (Exception e) {
            System.out.println("Ошибка во время вывода истории ввода: " + e.getMessage());
            return;
        }
        System.out.println("История ввода:\n" + Arrays.toString(output));
    }

    private void callGetString() {
        System.out.println("Выводим строку...");
        String output;
        try {
            output = stringBuilder.getStringValue();
        } catch (Exception e) {
            System.out.println("Ошибка во время вывода " + e.getMessage());
            return;
        }
        System.out.println("Строка:\n" + output);
    }

    private void callAppend() {
        System.out.print("Ожидание ввода:\n");
        String input = scanner.nextLine();
        System.out.println("Выполняем добавление...");
        try {
            stringBuilder.append(input);
        } catch (Exception e) {
            System.out.println("Ошибка во время добавления " + e.getMessage());
            return;
        }
        System.out.println("Добавление выполнено");
    }

    private void callUndo() {
        System.out.println("Выполняем удаление...");
        try {
            stringBuilder.undo();
        } catch (Exception e) {
            System.out.println("Ошибка во время удаления " + e.getMessage());
            return;
        }
        System.out.println("Удаление выполнено");
    }
}