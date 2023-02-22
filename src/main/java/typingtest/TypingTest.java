package src.main.java.typingtest;

import java.util.Scanner;
import java.util.Random;
public class TypingTest
{
      static int DEFAULT_MAX_ERRORS = 4;

      public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      Random generator = new Random();
      int max_errors;

      System.out.println("What is your maximum number of errors? ");
      int user_max_errors = GameManager.getUserInput(keyboard);
      if (user_max_errors <= 0)
         max_errors = DEFAULT_MAX_ERRORS;
      else
         max_errors = user_max_errors;

      GameManager gm = new GameManager(max_errors);

      gm.printInstructions();
      GameManager.getStartSignal(keyboard);

      while (gm.isAlive())
      {
         gm.testKey(keyboard, generator);
      }

      gm.printResults();
      keyboard.close();
   }
}