/**
 * Baba Class represents main chatbot system
 * Initializes storage, UI, Tasks handling, command parsing
 */

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class Baba {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

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

    public void run() {
        ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String userInput = scanner.nextLine().trim();
                String command = parser.extractCommand(userInput);

                if (command.equals("bye")) {
                    ui.printExitMessage();
                    break;
                } else if (command.equals("list")) {
                    taskList.printTaskList();
                } else if (command.equals("find")) {  // New case for finding tasks
                    String keyword = parser.extractKeyword(userInput);
                    taskList.findTasks(keyword);
                } else {
                    taskList.processCommand(userInput, parser);
                }

                storage.save(taskList.getTasks());

            } catch (BabaException e) {
                ui.printError(e.getMessage());
            } catch (Exception e) {
                ui.printError("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Baba("./data/baba.txt").run();
    }
}