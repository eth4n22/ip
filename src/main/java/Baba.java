import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class Baba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("./data/baba.txt");
        ArrayList<Task> tasks;

        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        }

        System.out.println("_____________________________");
        System.out.println("Hello! I'm Baba");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        while (true) {
            try {
                String userInput = scanner.nextLine().trim();

                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("_____________________________");
                    System.out.println("Bye! Hope to see you again soon!");
                    System.out.println("_____________________________");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    printTaskList(tasks);
                } else if (userInput.startsWith("delete")) {
                    deleteTask(tasks, userInput);
                } else if (userInput.startsWith("mark ")) {
                    markTaskAsDone(tasks, userInput);
                } else if (userInput.startsWith("unmark ")) {
                    unmarkTask(tasks, userInput);
                } else if (userInput.startsWith("todo")) {
                    addTodo(tasks, userInput);
                } else if (userInput.startsWith("deadline")) {
                    addDeadline(tasks, userInput);
                } else if (userInput.startsWith("event")) {
                    addEvent(tasks, userInput);
                } else {
                    throw new BabaException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                storage.save(tasks);

            } catch (BabaException e) {
                System.out.println("_____________________________");
                System.out.println(e.getMessage());
                System.out.println("_____________________________");
            } catch (Exception e) {
                System.out.println("_____________________________");
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("_____________________________");
            }
        }

        scanner.close();
    }

private static void printTaskList(ArrayList<Task> tasks) {
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

    private static void deleteTask(ArrayList<Task> tasks, String userInput) throws BabaException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new BabaException("Task index out of bounds");
            }

            Task removedTask = tasks.remove(taskIndex);
            System.out.println("_____________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in your list.");
            System.out.println("_____________________________");
        } catch (NumberFormatException e) {
            throw new BabaException("Invalid format. Use: delete [task_number]");
        }
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, String userInput) throws BabaException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new BabaException("Invalid task number.");
            }
            tasks.get(taskIndex).markAsDone();
            System.out.println("_____________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskIndex));
            System.out.println("_____________________________");
        } catch (NumberFormatException e) {
            throw new BabaException("Invalid format. Use: mark [task_number]");
        }
    }

    private static void unmarkTask(ArrayList<Task> tasks, String userInput) throws BabaException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new BabaException("Invalid task number.");
            }
            tasks.get(taskIndex).markAsNotDone();
            System.out.println("_____________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskIndex));
            System.out.println("_____________________________");
        } catch (NumberFormatException e) {
            throw new BabaException("Invalid format. Use: unmark [task_number]");
        }
    }

    private static void addTodo(ArrayList<Task> tasks, String userInput) throws BabaException {
        if (userInput.equals("todo")) {
            throw new BabaException("OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = userInput.substring(5).trim();
        if (taskDescription.isEmpty()) {
            throw new BabaException("OOPS!!! The description of a todo cannot be empty.");
        }
        tasks.add(new ToDo(taskDescription));
        System.out.println("_____________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("_____________________________");
    }

    private static void addDeadline(ArrayList<Task> tasks, String userInput) throws BabaException {
        if (userInput.equals("deadline")) {
            throw new BabaException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] parts = userInput.substring(9).split(" /by ", 2);
        if (parts[0].trim().isEmpty()) {
            throw new BabaException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (parts.length < 2) {
            throw new BabaException("Invalid format. Use: deadline [task] /by [date]");
        }

        tasks.add(new Deadline(parts[0], parts[1]));
        System.out.println("_____________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("_____________________________");
    }

    private static void addEvent(ArrayList<Task> tasks, String userInput) throws BabaException {
        if (userInput.equals("event")) {
            throw new BabaException("OOPS!!! The description of an event cannot be empty.");
        }

        String[] parts = userInput.substring(6).split(" /from ", 2);
        if (parts[0].trim().isEmpty()) {
            throw new BabaException("OOPS!!! The description of an event cannot be empty.");
        }
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new BabaException("Invalid format. Use: event [task] /from [start] /to [end]");
        }

        String[] timeParts = parts[1].split(" /to ", 2);
        tasks.add(new Event(parts[0], timeParts[0], timeParts[1]));
        System.out.println("_____________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("_____________________________");
    }
}
