// Andres Succar
//p.71 aug 31

import java.util.Scanner;

public class ArithmeticDemo3 {
    public static void main(String[] args) 
    {
        int firstNumber;
        int secondNumber;
        int sum;
        int difference;
        double average;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an double >> ");
        firstNumber = input.nextInt();
        System.out.println("Please enter another double >> ");
        secondNumber = input.nextInt();
        sum = firstNumber + secondNumber;
        difference = firstNumber - secondNumber;
        average = (double) sum / 2;
        System.out.println(firstNumber + " + " + secondNumber + " is " + sum);
        System.out.println( firstNumber + " - " + secondNumber  + " is " + difference);
        System.out.println("The average of " + firstNumber + " and " + secondNumber + " is " + average);

    
    }
}
