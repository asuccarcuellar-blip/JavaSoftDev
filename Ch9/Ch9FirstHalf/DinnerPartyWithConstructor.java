package Ch9FirstHalf;
// Andres Succar
// p.333

public class DinnerPartyWithConstructor extends PartyWithConstructor
{
    private int dinnerChoice;

    public int getDinnerChoice()
    {
        return dinnerChoice;
    }
    public void setDinnerChoice(int choice)
    {
        dinnerChoice = choice;
    }
    @Override public void displayInvitation()
    {
        System.out.println("Please come to my dinner party!");
    }
}
