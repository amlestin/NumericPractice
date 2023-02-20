package src.main.java.typingtest;

import java.util.Scanner;
import java.util.Random;
public class TypingTest
{
   final static int MAX_ERRORS = 4;

      public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      GameManager gm = new GameManager(MAX_ERRORS);
      gm.print_instructions();
      GameManager.get_start_signal(keyboard);


      Random generator = new Random();
      while (gm.alive)
      {
         int answer = generator.nextInt(10);
         System.out.println("The number is: " + answer);

         int response = GameManager.get_user_input(keyboard);
         if (response == answer)
         {
            gm.score++;
            gm.row[answer]++;
         }
         else
         {
            gm.errors++;
            gm.row[answer]--;
         }

         gm.alive = gm.errors < gm.MAX_ERRORS;
      }

      gm.print_results();
      keyboard.close();
   }
}