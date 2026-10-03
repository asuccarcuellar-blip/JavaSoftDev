//Andres Succar
//p.280

import java.util.*;


public class BowlingTeamDemo {
    
    public static void main(String[] args) {
        String name;
        BowlingTeam bowlTeam = BowlingTeam();
        int x;
        final int numTeamMembers = 4;
        Scanner input = new Scanner(System.in);
        System.out.print("Emter team name >> ");
        name = input.nextLine();
        bowlTeam.setTeamName(name);
        for (x = 0; x < numTeamMembers; ++)
    {
        System.out.print( "Enter team member's name >> ");
        name = input.nextLine();
        
    }
    
    
        }

}
