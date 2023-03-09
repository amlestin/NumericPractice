package src.main.java.typingtest;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
    static int DEFAULT_MAX_ERRORS = 4;
    private int score;
    private int errors;
    private boolean alive;
    private final int[] row;
    boolean exit_requested;
    private int MAX_ERRORS;

    public GameManager() {
        alive = true;
        row = new int[10];
    }

    public void printGameInstructions() {
        System.out.println("This game will allow you to demonstrate and develop your number typing skills! \n");
        System.out.println("A random number (0-9) will appear, and you will have to enter it in a specified amount of time. \n");
        System.out.println("If you fail to enter the correct number " + MAX_ERRORS + " times in a row, you lose. Try to get the highest score!");
        System.out.println("======================================================================================================");
    }

    public void printTrainingInstructions() {
        System.out.println("This training will allow you to develop your number typing skills! \n");
        System.out.println("A random number (0-9) will appear, and you will have to type it\n");
        System.out.println("If you type the letter 'q' or 'Q', the training will end. Try your best!");
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

    public static int getUserInput(Scanner keyboard) throws InputMismatchException {
        int response = keyboard.nextInt();
        keyboard.nextLine();
        return response;
    }

    public void printResults() {
        System.out.println("Your correctly entered: " + score + " numbers!");
        for (int i = 0; i < row.length; i++) {
            // use float and percentage instead
            System.out.println("Your accuracy for the number " + i + ": " + row[i]);
        }
    }

    public void incrementScore() {
        score++;
    }

    public void incrementErrors() {
        errors++;
    }

    public void updateKey(boolean correct, int answer) {
        if (correct) {
            row[answer]++;
        } else {
            row[answer]--;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void testKey(Scanner keyboard, Random generator) {
        int answer = generator.nextInt(10);
        int response;

        System.out.println("The number is: " + answer);

        try {
            response = GameManager.getUserInput(keyboard);
        } catch (InputMismatchException e) {
            exit_requested = true;
            return;
        }

        if (response == answer) {
            incrementScore();
            updateKey(true, answer);
        } else {
            incrementErrors();
            updateKey(false, answer);
        }
    }

    public void setAlive() {
        alive = errors < MAX_ERRORS;
    }

    public void runGame() {
        Scanner keyboard = new Scanner(System.in);
        Random generator = new Random();

        promptMaxErrors(keyboard);

        printGameInstructions();
        GameManager.getStartSignal(keyboard);

        while (isAlive()) {
            testKey(keyboard, generator);
            setAlive();
        }

        printResults();
        keyboard.close();
    }

    // TODO: Add training mode
    public void runTraining() {
        Scanner keyboard = new Scanner(System.in);
        Random generator = new Random();

        // explain training mode
        printTrainingInstructions();
        // run game with no max_errors; end game after 'q' or 'Q' is typed
        MAX_ERRORS = 0;
        GameManager.getStartSignal(keyboard);
        while (!exit_requested) {
            testKey(keyboard, generator);
        }
        printResults();
        keyboard.close();
    }


        public void promptMaxErrors(Scanner keyboard) {
        System.out.println("What is your maximum number of errors? ");
        int user_max_errors = GameManager.getUserInput(keyboard);
        if (user_max_errors <= 0) {
            MAX_ERRORS = DEFAULT_MAX_ERRORS;
            // TODO: explain default max errors set
        } else {
            MAX_ERRORS = user_max_errors;
        }
    }

    public void askGameTypeAndRun() {
        // TODO: use class keyboard
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter 'g' for game or 't' to train your typing skills: ");

//        keyboard.useDelimiter("");
//        String userChoice = keyboard.next();

        String userChoice = keyboard.nextLine();


        if (userChoice.equalsIgnoreCase("g'")) {
            runGame();
        } else if (userChoice.equalsIgnoreCase("t")) {
            runTraining();
        } else {
            // run game by default? TODO
            runGame();
        }
        }
    }
