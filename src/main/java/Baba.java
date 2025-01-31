import java.util.ArrayList;
import java.util.Scanner;

public class Baba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // Greeting message
        System.out.println("_____________________________");
        System.out.println("Hello! I'm Baba");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("_____________________________");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("_____________________________");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
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
            } else if (userInput.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsDone();
                        System.out.println("_____________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(tasks.get(taskIndex));
                        System.out.println("_____________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid format. Use: mark [task_number]");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markAsNotDone();
                        System.out.println("_____________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(tasks.get(taskIndex));
                        System.out.println("_____________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid format. Use: unmark [task_number]");
                }
            } else {
                tasks.add(new Task(userInput));
                System.out.println("_____________________________");
                System.out.println("Added: " + userInput);
                System.out.println("_____________________________");
            }
        }

        scanner.close();
    }
}

// Task class to manage tasks
class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

