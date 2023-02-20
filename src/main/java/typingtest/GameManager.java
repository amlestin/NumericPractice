package src.main.java.typingtest;

import java.util.Scanner;

public class GameManager {
    public int score;
    public int errors;
    public boolean alive;
    public int[] row;

    public int MAX_ERRORS;

    public GameManager(int max_errors) {
        this.MAX_ERRORS = max_errors;
        this.alive = true;
        this.row = new int[10];
    }
    public void print_instructions() {
        System.out.println("This game will allow you to demonstrate and develop your number typing skills! \n");
        System.out.println("A random number (0-9) will appear, and you will have to enter it in a specified amount of time. \n");
        System.out.println("If you fail to enter the correct number " + this.MAX_ERRORS + " times in a row, you lose. Try to get the highest score!");
        System.out.println("======================================================================================================");
    }

    public static void get_start_signal(Scanner keyboard) {
        boolean wait = true;
        while(wait)
        {
            System.out.println("Are you ready? Enter \"Y\" to continue.");
            String input = keyboard.nextLine();
            wait = !input.equalsIgnoreCase("y");
        }
    }
    public static int get_user_input(Scanner keyboard) {
        int response = keyboard.nextInt();
        keyboard.nextLine();
        return response;
    }

    public void print_results() {
        System.out.println("Your correctly entered: " + this.score + " numbers!");
        for(int i = 0; i < this.row.length; i++)
        {
            // use float and percentage instead
            System.out.println("Your accuracy for the number " + i + ": " + this.row[i]);
        }
    }
}
