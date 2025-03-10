import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date.
 * Extends the Task class and adds date handling using LocalDate.
 */
public class Deadline extends Task {
    protected LocalDate by;  // Stores the deadline date

    /**
     * Constructs a Deadline task with a description and a due date.
     * @param description The task description.
     * @param by The due date as a LocalDate object.
     */
    public Deadline(String description, LocalDate by) { // Accepts LocalDate instead of String
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with a description, status, and due date.
     * @param description The task description.
     * @param isDone Whether the task is completed.
     * @param by The LocalDate object representing the due date.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Converts the deadline task into a string format.
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy"); // Example: Dec 01 2024
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Converts the deadline task into a format suitable for file storage.
     * @return A formatted string for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.toString();
    }
}
