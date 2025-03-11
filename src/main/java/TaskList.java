import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages the list of tasks, allowing operations like adding, deleting, and marking tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.ui = new Ui();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Prints all tasks in the list.
     */
    public void printTaskList() {
        System.out.println("_____________________________");
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("_____________________________");
    }

    /**
     * Searches for tasks that contain a keyword.
     */
    public void findTasks(String keyword) {
        System.out.println("_____________________________");
        System.out.println("Here are the matching tasks in your list:");

        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((i + 1) + ". " + task);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching tasks found.");
        }

        System.out.println("_____________________________");
    }

    /**
     * Processes user input commands and executes corresponding actions.
     */
    public void processCommand(String input, Parser parser) throws BabaException {
        String command = parser.extractCommand(input);

        switch (command) {
            case "delete":
                deleteTask(input);
                break;
            case "mark":
                markTaskAsDone(input);
                break;
            case "unmark":
                unmarkTask(input);
                break;
            case "todo":
                addTodoTask(input);
                break;
            case "deadline":
                addDeadlineTask(input, parser);
                break;
            case "event":
                addEventTask(input);
                break;
            default:
                throw new BabaException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private void deleteTask(String input) throws BabaException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new BabaException("Task index out of bounds");
            }
            Task removedTask = tasks.remove(taskIndex);
            ui.printSuccess("Noted. I've removed this task: " + removedTask);
            ui.printInfo("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new BabaException("Invalid format. Use: delete [task_number]");
        }
    }

    private void markTaskAsDone(String input) throws BabaException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BabaException("Invalid task number.");
        }
        tasks.get(taskIndex).markAsDone();
        ui.printSuccess("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
    }

    private void unmarkTask(String input) throws BabaException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new BabaException("Invalid task number.");
        }
        tasks.get(taskIndex).markAsNotDone();
        ui.printSuccess("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex));
    }

    private void addTodoTask(String input) throws BabaException {
        if (input.equals("todo")) {
            throw new BabaException("OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = input.substring(5).trim();
        tasks.add(new ToDo(taskDescription));
        ui.printSuccess("Added: " + taskDescription);
    }

    /**
     * Adds a deadline task with LocalDate.
     */
    private void addDeadlineTask(String input, Parser parser) throws BabaException {
        String[] details = parser.extractDeadlineDetails(input);

        try {
            LocalDate dueDate = LocalDate.parse(details[1].trim()); // Parse date
            tasks.add(new Deadline(details[0], dueDate));
            ui.printSuccess("Added: " + details[0] + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
        } catch (DateTimeParseException e) {
            throw new BabaException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15).");
        }
    }

    private void addEventTask(String input) throws BabaException {
        String[] parts = input.substring(6).split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new BabaException("Invalid format. Use: event [task] /from [start] /to [end]");
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        tasks.add(new Event(parts[0], timeParts[0], timeParts[1]));
        ui.printSuccess("Added: " + parts[0] + " (from: " + timeParts[0] + " to: " + timeParts[1] + ")");
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
