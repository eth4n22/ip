/**
 * Parser class processes user input and extracts relevant command details.
 * It helps to interpret user commands for the chatbot.
 */
public class Parser {

    /**
     * Extracts the main command from user input.
     * @param userInput The full user input string.
     * @return The command as a string.
     */
    public String extractCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        return words[0].toLowerCase(); // First word is the command, converted to lowercase
    }

    /**
     * Extracts the search keyword for the "find" command.
     * @param userInput The full user input string.
     * @return The extracted search keyword.
     * @throws BabaException If no keyword is provided.
     */
    public String extractKeyword(String userInput) throws BabaException {
        String[] words = userInput.split(" ", 2);
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new BabaException("OOPS!!! The keyword for find cannot be empty.");
        }
        return words[1].trim(); // Return the search keyword
    }

    /**
     * Extracts the description and due date for the "deadline" command.
     * @param userInput The full user input string.
     * @return A string array with [0] = task description, [1] = due date.
     * @throws BabaException If the format is incorrect.
     */
    public String[] extractDeadlineDetails(String userInput) throws BabaException {
        String[] parts = userInput.substring(9).split(" /by ", 2);

        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BabaException("Invalid format! Use: deadline [task] /by YYYY-MM-DD");
        }

        return new String[]{parts[0].trim(), parts[1].trim()}; // Returns description and date
    }
}
