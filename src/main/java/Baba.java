import java.util.ArrayList;
import java.util.Scanner;

public class Baba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

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
            } else if (userInput.startsWith("todo ")) {
                String taskDescription = userInput.substring(5).trim();
                tasks.add(new ToDo(taskDescription));
                System.out.println("_____________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("_____________________________");
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ", 2);
                if (parts.length < 2) {
                    System.out.println("Invalid format. Use: deadline [task] /by [date]");
                } else {
                    tasks.add(new Deadline(parts[0], parts[1]));
                    System.out.println("_____________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("_____________________________");
                }
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from ", 2);
                if (parts.length < 2 || !parts[1].contains(" /to ")) {
                    System.out.println("Invalid format. Use: event [task] /from [start] /to [end]");
                } else {
                    String[] timeParts = parts[1].split(" /to ", 2);
                    tasks.add(new Event(parts[0], timeParts[0], timeParts[1]));
                    System.out.println("_____________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("_____________________________");
                }
            } else {
                System.out.println("Unknown command! Try using 'todo', 'deadline', or 'event'.");
            }
        }

        scanner.close();
    }
}

