import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {  // ✅ Added "throws IOException"
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Check if file exists, else create it
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Ensure directory exists
            file.createNewFile();  // ✅ Now this method can throw IOException
            return tasks; // Return empty task list
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            tasks.add(parseTask(scanner.nextLine())); // Read task line-by-line
        }
        scanner.close();
        return tasks;
    }


    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath); // Overwrites existing file
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator()); // Save each task line-by-line
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

        switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                return new Deadline(description, isDone, parts[3]);
            case "E":
                return new Event(description, isDone, parts[3], parts[4]);
            default:
                return null; // Handle corrupted data separately if needed
        }
    }
}
