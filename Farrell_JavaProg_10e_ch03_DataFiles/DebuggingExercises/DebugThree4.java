// This program calculates tuition bills
// as credits times rate per credit hour
import java.util.Scanner;
public class DebugThree4
{
   public static void main(String args[])
   {
      int credits1;
      int credits2;
      final double RATE = 75.84;
      Scanner input = new Scanner(System.in);
      System.out.print("Enter credits for first student >> ");
      credits1 = inpput.nextInt();
      System.out.print(Enter credits for second student >> ");
      credits2 = inpput.nextInt();
      computeTuitionBill(credits1, RATE, 1);
      computeTuitionBill(credits2, RATE);
   }
   public static void computeTuitionBill(int c, double r, int student)
   {
      System.out.println("Total due for student #" + student +
         " is $" + r * c));
   }
}
