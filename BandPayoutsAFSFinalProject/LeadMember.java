//Andres Succar
/**
 * 
 * LeadMember.java
 * Class for the band Leader. 
 * They charge fixed fee, a percentage AND a bonus per show.
 * It inherits (extends) from Member
 */


public class LeadMember extends Member
{
    private double bonus;

    public LeadMember(String name, double percent, double bonus) throws BandException 
    {
        super(name, percent);
        setBonus(bonus);
    }

    public double getBonus()
    {
        return bonus;
    }

    // validates the bonus is not negative
    public void setBonus(double bonus) throws BandException
    {
        if(Double.isNaN(bonus) || bonus < 0)
        {
            throw new BandException("The bonus cannot be negative");
        }
        this.bonus = bonus;
    }
// replaces parent method
    @Override 
    public String getType()
    {
        return "LEAD";
    }
    // bonus is a fixed payment. it is discounted before paying percentages
    @Override 
    public double getFixedPay()
        {
            return bonus;
        }

        // Lead Member payment 
    @Override 
    public double calculatePay(double remainder)
    {
        return super.calculatePay(remainder) + bonus;
    }
}
