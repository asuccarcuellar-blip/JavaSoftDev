//Andres Succar
//p. 191


public class PreSchoolStudent 
{
    int idNum;
    int age;
    public final int high_id = 9999;
    public final int high_age = 6;

    public PreSchoolStudent(int num, int studentAge)
    {
       setIdNum(num);
       setAge(studentAge);
    }
    public void setIdNum(int num){
        if(num <= high_id)
            idNum = num;
        else
            idNum = 0;
    }    
    public void setAge(int studentAge)
    {
        if(studentAge <= high_age)
            age = studentAge;
        else
            age = 0;
    }
    public int getIdNum()
    {
        return idNum;
    }
    public int getAge()
    {
        return age;
    }
}
