package cwk4; 


/**
 * Details of your team
 * 
 * @R.Maunick, D.Ntuka, R.Mujsaku, T.Hau
 * @V9 - 23/04/2024
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "CS80";
        
        details[1] = "Ntuka";
        details[2] = "Daniel";
        details[3] = "21033892";

        details[4] = "Mujsaku";
        details[5] = "Raldi";
        details[6] = "21033635";

        details[7] = "Maunick";
        details[8] = "Romeo";
        details[9] = "21032039";


        details[10] = "Hau";
        details[11] = "To Ming";
        details[12] = "21033699";

    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
