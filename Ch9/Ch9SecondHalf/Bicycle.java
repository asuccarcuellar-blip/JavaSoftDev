package Ch9SecondHalf;
// Andres Succar
//p. 355
import javax.swing.*;
public class Bicycle extends Vehicle{

    public Bicycle()
    {
        super(" a person", 2);

    }

        @Override
        public void setPrice()
        {
            String entry;
            final int max = 4000;
            entry = JOptionPane.showInputDialog("Enter bicycle price");
            price = Integer.parseInt(entry);
            if(price > max)
                price = max;

        }
        @Override
        public String toString()
        {
            return("The bicycle is powered by "
             + getPowerSource() + "; it has " + getWheels() + " wheels and costs $" + getPrice());
        }
}
