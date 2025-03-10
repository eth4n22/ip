import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Check if file exists, else create it
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            tasks.add(parseTask(scanner.nextLine()));
        }
        scanner.close();
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        try {
            switch (type) {
                case "T":
                    return new ToDo(description, isDone);

                case "D":
                    LocalDate dueDate = LocalDate.parse(parts[3]); // ✅ Converts String to LocalDate
                    return new Deadline(description, isDone, dueDate);

                case "E":
                    return new Event(description, isDone, parts[3], parts[4]);

                default:
                    System.out.println("Warning: Unknown task type in storage file.");
                    return null;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date for Deadline task: " + parts[3]);
            return null; // Handle error gracefully
        }
    }
}
