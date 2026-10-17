//Andres Succar
//p. 192

public class PreschoolDemo 
{
    public static void main(String[] args) 
    {
        PreSchoolStudent ps1 = new PreSchoolStudent(2345, 5);
        PreSchoolStudent ps2 = new PreSchoolStudent( 6890, 8);   
        display(ps1);
        display(ps2);
        ps1.setAge(6);
        display(ps1);
        ps1.setAge(7);
        display(ps1);
    }    
    public static void display(PreSchoolStudent ps)
    {
        System.out.print("Student ID " + ps.getIdNum());
        System.out.println("  Student age " + ps.getAge());
        System.out.println();
    }
}
