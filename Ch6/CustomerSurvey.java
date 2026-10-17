// Andres Succar
// p.209

import java.util.*;

public class CustomerSurvey {
    public static void main(String[] args)
    {
        int rating;
        final int min = 1;
        final int max = 5;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a value that");
        System.out.println("represents your satisfaction with");
        System.out.println("our service");
        System.out.println("Enter a value between " + min);
        System.out.println("and  " + max);
        System.out.println("with " + max + " meaning highly");
        System.out.println("satisfied and");
        System.out.println(min + " meaning not at all satisfied.");
        System.out.print("Enter your rating >>");
        rating = input.nextInt();
        while(rating < min || rating > max)
        {
            System.out.println("You must enter a value");
            System.out.println("between " + min + " and " + max);
            System.out.print("Please try again >> ");
            rating = input.nextInt();
        }
        System.out.println("Thank you.");
    }
}
