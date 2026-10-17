//Andres Succar
/**
 * Member.java
 * Program that creates a class for a normal member of the band. 
 * This kind of member charges a 
 * a percentage of the money that it is left after cotsts and fixed expenses.
 * It is a
 * parent class for LeadMember and SessionMember.
 */ 

public class Member 
{
    private String name;
    private double percent;
    
    public Member(String name, double percent)throws BandException
    {
        setName(name);
        setPercent(percent);
    }
    public String getName()
    {
        return name;
    }
    public double getPercent()
    {
        return percent;
    }
    public void setName(String name) throws BandException
    {
        if(name == null || name.trim().isEmpty())
        {
            throw new BandException("The namefield cannot be empty");
        }
        this.name = name.trim();
    }

    public void setPercent(double percent) throws BandException
    {
        if(Double.isNaN(percent) || percent < 0 || percent > 100)
        {
            throw new BandException("The percentage must be between 0 and 100%");
        }
        this.percent = percent;

    }

    public String getType() 
    {
        return "CORE";
    }

    public double getFixedPay() 
    {
        return 0;
    }

    public double calculatePay(double remainder) 
    {
        return remainder * percent / 100;
    }

    @Override
    public String toString() {
        return name + " (" + getType() + ")";
    }
}
