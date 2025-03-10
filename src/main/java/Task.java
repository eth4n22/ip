/**
 * Represents a task in the task list.
 * This is the base class for all other task types (ToDo, Deadline, Event).
 */
public abstract class Task {  // Make it abstract since it's a base class
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a new task.
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for loading a task with status.
     * @param description The task description.
     * @param isDone Whether the task is already completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the task description.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts task to a readable string format.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Converts task to file storage format.
     * @return A formatted string to store in a file.
     */
    public abstract String toFileFormat(); // Ensures subclasses implement it
}
