// Andres Succar


/*BandException.java
it is a program excpetion that it is launched 
when invalid data is provided or when a rule is broken
*/

public class BandException extends Exception
{
   
    public BandException(String message) {
        super(message);
    }
}