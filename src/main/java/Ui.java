/**
 * UI Class
 */

public class Ui {
    public void printWelcomeMessage() {
        System.out.println("_____________________________");
        System.out.println("Hello! I'm Baba");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");
    }

    public void printExitMessage() { //message for bye input
        System.out.println("_____________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("_____________________________");
    }

    public void printError(String message) {
        System.out.println("_____________________________");
        System.out.println("[ERROR] " + message);
        System.out.println("_____________________________");
    }

    public void printSuccess(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    public void printInfo(String message) {
        System.out.println("[INFO] " + message);
    }
}
