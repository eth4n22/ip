public class Parser {
    public String extractCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        return words[0]; // First word is the command
    }

    public String extractKeyword(String userInput) throws BabaException {
        String[] words = userInput.split(" ", 2);
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new BabaException("OOPS!!! The keyword for find cannot be empty.");
        }
        return words[1].trim(); // Return the search keyword
    }
}


