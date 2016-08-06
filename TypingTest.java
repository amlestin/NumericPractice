import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.InputMismatchException;


public class TypingTest
{
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.println("This game will allow you to demonstrate and develop your number typing skills! \n");
      System.out.println("A random number (0-9) will appear, and you will have to enter it in a specified amount of time. \n");
      System.out.println("If you fail to enter the correct number three times in a row, you lose. Try to get the highest score!");
      
      System.out.println("======================================================================================================");
      
      boolean wait = true;
      while(wait)
      {
         System.out.println("Are you ready? Enter \"Y\" to continue.");
         String input = keyboard.nextLine();
         wait = (input.equals("y") || input.equals("Y")) ? false : true;
      }
      
      int score = 0;
      int fail = 0;
      boolean Alive = true;
      
      
      Random generator = new Random();
      
      int[] row = new int[10];
         
      while (Alive)
      {
         int answer = generator.nextInt(10);
         System.out.println("The number is: " + answer);
         
         
          int response = keyboard.nextInt();
          keyboard.nextLine();
        
         
         if (response == answer)
         {
            score++;
            row[answer]++;
         }
         
         else
         {
            fail++;
            row[answer]--;
         }
         
         Alive = (fail < 4) ? true : false;
      }
         
        
   
         
   
      System.out.println("Your score was: " + score);
      
      for(int i = 0; i < row.length; i++)
      {
         System.out.println("Your accuracy for the number " + i + ": " + row[i]);
      }
   }


}