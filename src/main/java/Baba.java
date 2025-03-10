/**
 * Baba Class represents the main chatbot system.
 * Initializes storage, UI, task handling, and command parsing.
 */

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Baba {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes Baba chatbot with storage, UI, task handling, and parser.
     * @param filePath Path to the storage file.
     */
    public Baba(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError("Error loading tasks: " + e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the chatbot, processing user input in a loop.
     */
    public void run() {
        ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine().trim();
                String command = parser.extractCommand(userInput);

                switch (command) {
                    case "bye":
                        ui.printExitMessage();
                        return;

                    case "list":
                        taskList.printTaskList();
                        break;

                    case "find":
                        String keyword = parser.extractKeyword(userInput);
                        taskList.findTasks(keyword);
                        break;

                    default:
                        taskList.processCommand(userInput, parser);
                }

                storage.save(taskList.getTasks());

            } catch (BabaException e) {
                ui.printError(e.getMessage());
            } catch (Exception e) {
                ui.printError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Main entry point for the chatbot.
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new Baba("./data/baba.txt").run();
    }
}
