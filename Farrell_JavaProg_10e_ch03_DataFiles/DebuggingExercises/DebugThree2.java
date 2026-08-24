// This application displays some math facts
import java.util.Scanner;
public class DebugThree2
{
   public static void main(String args[])
   {
      int a, b, c;
      Scanner input = new Scanner(System.in);
      System.out.print("Enter an integer >> ");
      a = input.nextInt();
      System.out.print("Enter a second integer >> ");
      a = input.nextInt();
      System.out.print("Enter a third integer >> ");
      a = input.nextInt();
      add(a, b);
      add(b, c);
      add(a, c);
      subtact(a, b);
      subtact(b, c);
      subtact(a, c);  
   }
   public static int add(int a, int  b)
   {
      System.out.println("The sum of " + a +
         " and " + b + " is " + a + b);
   }
   public static void subtract(int a, int b)
   {
      System.out.println("The difference between " +
         a + " and  + b + " is  +  (a - b));
   }
}
