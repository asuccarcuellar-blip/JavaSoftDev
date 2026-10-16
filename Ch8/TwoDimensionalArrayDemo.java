// Andres Succar
// p 305

import java.util.Scanner;

public class TwoDimensionalArrayDemo
{
    public static void main(String[] args)
    {
        int[][] count = new int[3][3];
        Scanner input = new Scanner(System.in);
        int row, column;
        final int quit = 99;
        System.out.print("Enter a row or " + quit + " to quit >> ");
        row = input.nextInt();
        while(row != quit)
        {
            System.out.print("Enter a column >> ");
            column = input.nextInt();
            if(row < count.length && column < count[row].length)
            {
                ++count[row][column];
                System.out.println("Count for row " + row +
                    " column " + column + " is " + count[row][column]);
            }
            else
                System.out.println("Invalid row or column -- try again");
            System.out.print("Enter a row or " + quit + " to quit >> ");
            row = input.nextInt();
        }
        System.out.println("\nFinal counts:");
        for(row = 0; row < count.length; ++row)
        {
            for(column = 0; column < count[row].length; ++column)
                System.out.print(count[row][column] + " ");
            System.out.println();
        }
    }
}
