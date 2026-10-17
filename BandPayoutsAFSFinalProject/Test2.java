//Andres Succar
//*Test for secon benchmark */

public class Test2 
{
    
    public static void main(String[] args) {
        try {
            // Una variable de tipo Member puede guardar cualquiera de los 3 tipos
            Member[] banda = {
                new Member("Alex", 30),
                new LeadMember("Sam", 40, 50),
                new SessionMember("Chris", 120)
            };

            double sobra = 580;
            for (Member m : banda) {
                // Java decide cuál calculatePay usar según el objeto real: POLIMORFISMO
                System.out.println(m + " cobra " + m.calculatePay(sobra));
            }
        } catch (BandException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

