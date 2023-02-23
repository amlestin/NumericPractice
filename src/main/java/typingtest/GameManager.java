package src.main.java.typingtest;

import java.util.Random;
import java.util.Scanner;

public class GameManager {
    static int DEFAULT_MAX_ERRORS = 4;
    private int score;
    private int errors;
    private boolean alive;
    private final int[] row;

    private int MAX_ERRORS;

    public GameManager() {
        this.alive = true;
        this.row = new int[10];
    }

    public void printInstructions() {
        System.out.println("This game will allow you to demonstrate and develop your number typing skills! \n");
        System.out.println("A random number (0-9) will appear, and you will have to enter it in a specified amount of time. \n");
        System.out.println("If you fail to enter the correct number " + this.MAX_ERRORS + " times in a row, you lose. Try to get the highest score!");
        System.out.println("======================================================================================================");
    }

    public static void getStartSignal(Scanner keyboard) {
        boolean wait = true;
        while (wait) {
            System.out.println("Are you ready? Enter \"Y\" to continue.");
            String input = keyboard.nextLine();
            wait = !input.equalsIgnoreCase("y");
        }
    }

    public static int getUserInput(Scanner keyboard) {
        int response = keyboard.nextInt();
        keyboard.nextLine();
        return response;
    }

    public void printResults() {
        System.out.println("Your correctly entered: " + this.score + " numbers!");
        for (int i = 0; i < this.row.length; i++) {
            // use float and percentage instead
            System.out.println("Your accuracy for the number " + i + ": " + this.row[i]);
        }
    }

    public void incrementScore() {
        this.score++;
    }

    public void incrementErrors() {
        this.errors++;
    }

    public void updateKey(boolean correct, int answer) {
        if (correct) {
            this.row[answer]++;
        } else {
            this.row[answer]--;
        }
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void testKey(Scanner keyboard, Random generator) {
        int answer = generator.nextInt(10);
        System.out.println("The number is: " + answer);
        int response = GameManager.getUserInput(keyboard);
        if (response == answer) {
            this.incrementScore();
            this.updateKey(true, answer);
        } else {
            this.incrementErrors();
            this.updateKey(false, answer);
        }

        this.alive = this.errors < this.MAX_ERRORS;
    }

    public void runGame() {
        Scanner keyboard = new Scanner(System.in);
        Random generator = new Random();

        promptMaxErrors(keyboard);

        this.printInstructions();
        GameManager.getStartSignal(keyboard);

        while (this.isAlive()) {
            this.testKey(keyboard, generator);
        }

        this.printResults();
        keyboard.close();
    }

    public void runTraining() {
    }


        public void promptMaxErrors(Scanner keyboard) {
        int max_errors;
        System.out.println("What is your maximum number of errors? ");
        int user_max_errors = GameManager.getUserInput(keyboard);
        if (user_max_errors <= 0)
            max_errors = DEFAULT_MAX_ERRORS;
        else
            max_errors = user_max_errors;
        this.MAX_ERRORS = max_errors;
    }
}
