/**
 * Class for finding tasks based on keyword search
 */

import java.util.ArrayList;

public class Find {
    public static void findTask(ArrayList<Task> tasks, String keyword) throws BabaException {
        if (keyword.trim().isEmpty()) {
            throw new BabaException("OOPS!!! The search keyword cannot be empty.");
        }

        System.out.println("_____________________________");
        System.out.println("Here are the matching tasks in your list:");

        int count = 1;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(count + ". " + task);
                count++;
            }
        }

        if (count == 1) {
            System.out.println("No matching tasks found.");
        }

        System.out.println("_____________________________");
    }
}

