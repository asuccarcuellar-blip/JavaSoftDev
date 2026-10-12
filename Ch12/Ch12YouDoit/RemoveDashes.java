//Andres Succar
//p.496

import java.util.Scanner;

public class RemoveDashes 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        String phone;
        System.out.print("Enter phone number >> ");
        phone = input.nextLine();
        removeDashes(phone);
        System.out.println();
    }
    public static void removeDashes(String s)
    {
        int dashloc = s.indexOf('-');
        if(dashloc == -1)
        {
            System.out.print(s);
        }
        else
        {
            removeDashes(s.substring(0, dashloc));
            removeDashes(s.substring(dashloc + 1));
        }
    }
}
