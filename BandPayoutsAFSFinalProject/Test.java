//Andres Succar
//Test.java. a test for what we have done so far

public class Test 
{
public static void main(String[] args) {
        try {
            Member alex = new Member("Alex", 30);
            System.out.println(alex + " cobra " + alex.calculatePay(580));  // Alex (CORE) cobra 174.0

            Member malo = new Member("Sam", 150);   // debe fallar
        } catch (BandException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
