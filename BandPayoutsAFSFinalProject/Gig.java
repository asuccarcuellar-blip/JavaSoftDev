//Andres Succar
//**Gig.java Class for a Gig. It saves the income,
// the expenses and calculates net profit
//  */

public class Gig
{
    private String description;
    private double income;
    private double expenses;

    public Gig(String description, double income, double expenses) throws BandException
    {
        setDescription(description);
        setIncome(income);
        setExpenses(expenses);
    }

    public String getDescription()
    {
        return description;
    }

    public double getIncome()
    {
        return income;
    }

    public double getExpenses()
    {
        return expenses;
    }

    public void setDescription(String description) throws BandException
    {
        if(description == null || description.trim().isEmpty())
        {
            throw new BandException("The gig needs a description");
        }
        this.description = description.trim();
    }

    public void setIncome(double income) throws BandException
    {
        if(Double.isNaN(income) || income < 0)
        {
            throw new BandException("The income cannot be negative");
        }
        this.income = income;
    }

    public void setExpenses(double expenses) throws BandException
    {
        if(Double.isNaN(expenses) || expenses < 0)
        {
            throw new BandException("The expenses cannot be negative");
        }
        this.expenses = expenses;
    }

    // what is left of the income after paying the costs of the gig
    public double getNet()
    {
        return income - expenses;
    }

    @Override
    public String toString() {
        return description + " (net $" + getNet() + ")";
    }
}
