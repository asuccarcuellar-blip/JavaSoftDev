// This class uses a DebugCircle class
// to instantiate a Circle object
import java.util.Scanner;
public class DebugFour1
{
   public static void main(String args[])
   {
      Scanner input = new Scanner(System.in);
      int radius;
      DebugCircle c = new Circle();
      System.out.print("Enter a radius for a circle >> ");
      radius = input.nextInt();
      c = new DebugCircle(c);
      System.out.println("The radius is " + c.getRad());
      System.out.println("The diameter is " + c.getDiam());
      System.out.println("The area is " + c.getArea());
   }
}
