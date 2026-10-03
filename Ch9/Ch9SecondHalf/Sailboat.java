package Ch9SecondHalf;
// Andres Succar
//p. 355
import javax.swing.*;
public class Sailboat extends Vehicle{
    
    private int length;
    public Sailboat()
    {
        super("Wind", 0);
        setLength();
    }
    public void setLength()
    {
        String entry;
        entry = JOptionPane.showInputDialog( "Enter sailboat length in feet ");
        length = Integer.parseInt(entry);
    }
    public int getLength()
        {
            return length;
        }
        @Override
        public void setPrice() 
        {
            String entry;
            final int max = 10000;
            entry = JOptionPane.showInputDialog("Enter sailboat price");
            price = Integer.parseInt(entry);
            if(price > max)
                price = max;

        }
        @Override
        public String toString()
        {
            return("The " + getLength() + " foot sailboat is powered by "
             + getPowerSource() + "; it has " + getWheels() + " wheels and costs $" + getPrice());
        }
}
