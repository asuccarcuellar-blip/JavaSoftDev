//Andres Succar
//**
// SessionMember.java creates the class for a musician
// who is not a band owner. They have no percentage
// and charge a fixed payment for each concert */

public class SessionMember extends Member 
{
    private double fee;
    
    public SessionMember(String name, double fee) throws BandException
    {
        super(name, 0.0);
        setFee(fee);
    }

    public double getFee()
    {
        return(fee);
    }

    public void setFee(double fee) throws BandException 
    {
        if(Double.isNaN(fee) || fee < 0)
        {
            throw new BandException("Fee cannot be negative");
        }
        this.fee = fee;
    }

    @Override 
    public String getType()
    {
        return "SESSION";
    }

    @Override 
    public double getFixedPay()
    {
        return fee;
    }

    @Override 
    public double calculatePay(double remainder)
    {
        return fee;
    }
}
