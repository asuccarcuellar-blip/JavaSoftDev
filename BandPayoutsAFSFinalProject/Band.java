//Andres Succar
//** Creates the class Band. It keeps the list of members and the list of gigs.
// it also calculates how the money is split */

import java.util.ArrayList;

public class Band
{
    private static final double TOLERANCE = 0.001;

    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Gig> gigs = new ArrayList<>();

    public void addMember(Member newMember) throws BandException
    {
        for (Member m : members)
        {
            if (m.getName().equalsIgnoreCase(newMember.getName()))
            {
                throw new BandException("There is already a member named " + newMember.getName());
            }
        }
        members.add(newMember);
    }

    public void addGig(Gig gig)
    {
        gigs.add(gig);
    }
    public ArrayList<Member> getMembers()
    {
        return members;
    }

    public ArrayList<Gig> getGigs()
    {
        return gigs;
    }

    public String[] getMemberNames()
    {
        String[] names = new String[members.size()];
        for (int i = 0; i < members.size(); i++)
        {
            names[i] = members.get(i).getName();
        }
        return names;
    }

    public double[] splitGig(Gig gig) throws BandException
    {
        if (members.isEmpty())
        {
            throw new BandException("The band has no members");
        }

        double totalPercent = 0;
        double totalFixed = 0;
        for (Member m : members)
        {
            totalPercent += m.getPercent();
            totalFixed += m.getFixedPay();
        }

        if (Math.abs(totalPercent - 100) > TOLERANCE)
        {
            throw new BandException("The percentages must add up to 100 (now "
                + totalPercent + ")");
        }

        double remainder = gig.getNet() - totalFixed;
        if (remainder < 0)
        {
            throw new BandException("Not enough money in \"" + gig.getDescription()
                + "\" to cover the fixed pay");
        }

        double[] payouts = new double[members.size()];
        for (int i = 0; i < members.size(); i++)
        {
            payouts[i] = members.get(i).calculatePay(remainder);
        }
        return payouts;
    }

    public double[][] buildPayoutMatrix() throws BandException
    {
        double[][] matrix = new double[gigs.size()][];
        for (int row = 0; row < gigs.size(); row++)
        {
            matrix[row] = splitGig(gigs.get(row));
        }
        return matrix;
    }
}
