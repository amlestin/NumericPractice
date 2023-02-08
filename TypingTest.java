import java.util.Scanner;
import java.util.Random;

public class TypingTest
{
   final static int MAX_ERRORS = 4;

   public static void print_instructions() {
      System.out.println("This game will allow you to demonstrate and develop your number typing skills! \n");
      System.out.println("A random number (0-9) will appear, and you will have to enter it in a specified amount of time. \n");
      System.out.println("If you fail to enter the correct number " + MAX_ERRORS + " times in a row, you lose. Try to get the highest score!");
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

   public static void print_results(int[] row, int score) {
      System.out.println("Your correctly entered: " + score + " numbers!");
      for(int i = 0; i < row.length; i++)
      {
         // use float and percentage instead
         System.out.println("Your accuracy for the number " + i + ": " + row[i]);
      }
   }
      public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);

      print_instructions();
      get_start_signal(keyboard);

      int score = 0;
      int errors = 0;
      boolean alive = true;

      Random generator = new Random();
      int[] row = new int[10];
      while (alive)
      {
         int answer = generator.nextInt(10);
         System.out.println("The number is: " + answer);

         int response = get_user_input(keyboard);
         if (response == answer)
         {
            score++;
            row[answer]++;
         }
         else
         {
            errors++;
            row[answer]--;
         }

         alive = errors < MAX_ERRORS;
      }

      print_results(row, score);

      keyboard.close();
   }
}