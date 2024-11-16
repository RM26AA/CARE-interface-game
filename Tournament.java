package cwk4;
import java.util.*;
import java.io.*;
/**
 * This interface specifies the behaviour expected from CARE
 * as required for 5COM2007 Cwk 4
 * 
 * @R.Maunick, D.Ntuka, R.Mujsaku, T.Hau
 * @V9 - 23/04/2024
 */

public class Tournament implements CARE
{
   
    private String vizierName;
    private int treasury = 1000; // Initial treasury as specified
    private List<Champion> championsReserve = new ArrayList<>();
    private List<Champion> championsTeam = new ArrayList<>();
    private List<Challenge> challenges = new ArrayList<>();


//**************** CARE ************************** 
    /** Constructor requires the name of the vizier
     * @param *viz the name of the vizier
     */  
    public Tournament(String vizierName)
    {
      
       this.vizierName = vizierName; 
       setupChampions();
       setupChallenges();
    }
    
    /** Constructor requires the name of the vizier and the
     * name of the file storing challenges
     * @param viz the name of the vizier
     * @param filename name of file storing challenges
     */  
    public Tournament(String viz, String filename)  //Task 3.5
    {
      
        
       setupChampions();
       readChallenges(filename);
    }
    
    
    /**Returns a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     * 
     * @return a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     **/
    public String toString() {

        return "Tournament{" +
                "vizierName='" + vizierName + '\'' +
                ", treasury=" + treasury +
                ", defeated=" + (isDefeated() ? "Yes" : "No") +
                ", championsReserve=" + championsReserve +  //added
                ", championsTeam=" + championsTeam +
                ", challenges=" + challenges +  //added
                '}';
    }


    /** returns true if Treasury <=0 and the vizier's team has no 
     * champions which can be retired. 
     * @returns true if Treasury <=0 and the vizier's team has no 
     * champions which can be retired. 
     */
    public boolean isDefeated()
    {
        // Simplified check, expand as needed
        return treasury <= 0 && championsTeam.stream().noneMatch(c -> c.getState() != ChampionState.DISQUALIFIED);
    }
    
    /** returns the amount of money in the Treasury
     *
     */
    public int getMoney()
    {
        return treasury;
    }
    
    
    /**Returns a String representation of all champions in the reserves
     *
     **/
    public String getReserve()
    {   
        StringBuilder sb = new StringBuilder();
        for (Champion champ : championsReserve) {
            sb.append(champ.toString()).append("\n");
        }
        return sb.toString();
    }
    
        
    /** Returns details of the champion with the given name. 
     * Champion names are unique.
     *
     **/
    public String getChampionDetails(String nme) {
        //searches for the champion in both reserves and team
        for (Champion champ : championsReserve) {
            if (champ.getName().equalsIgnoreCase(nme)) {
                return champ.toString();
            }
        }
        for (Champion champ : championsTeam) {
            if (champ.getName().equalsIgnoreCase(nme)) {
                return champ.toString();
            }
        }
        return "No such champion";
    }


    /** returns whether champion is in reserve
    * @param nme champion's name
    * @return true if champion in reserve, false otherwise
    */
    public boolean isInReserve(String nme)
    {
        return championsReserve.stream().anyMatch(champ -> champ.getName().equalsIgnoreCase(nme));
    }
 
    // ***************** Team champions ************************   
     /** Allows a champion to be entered for the vizier's team, if there 
     * is enough money in the Treasury for the entry fee.The champion's 
     * state is set to "active"
     * 0 if champion is entered in the vizier's team, 
     * 1 if champion is not in reserve, 
     * 2 if not enough money in the treasury, 
     * -1 if there is no such champion 
     * @param nme represents the name of the champion
     * @return as shown above
     **/
     public int enterChampion(String nme) {
         for (Champion champion : championsReserve) {
             if (champion.getName().equalsIgnoreCase(nme)) {
                 if (treasury >= champion.getEntryFee()) {
                     treasury -= champion.getEntryFee();
                     championsReserve.remove(champion);
                     champion.setState(ChampionState.ENTERED);
                     championsTeam.add(champion);
                     return 0; // Champion entered successfully
                 } else {
                     return 2; // Not enough money in the treasury
                 }
             }
         }
         return championsTeam.stream().anyMatch(champ -> champ.getName().equalsIgnoreCase(nme)) ? 1 : -1;
         // 1 if champion not in reserve anymore, -1 if no such champion found
     }
     /** Returns true if the champion with the name is in 
     * the vizier's team, false otherwise.
     * @param nme is the name of the champion
     * @return returns true if the champion with the name
     * is in the vizier's team, false otherwise.
     **/
    public boolean isInViziersTeam(String nme)
    {
        return championsTeam.stream().anyMatch(champ -> champ.getName().equalsIgnoreCase(nme));
    }
    
    /** Removes a champion from the team back to the reserves (if they are in the team)
     * Pre-condition: isChampion()
     * 0 - if champion is retired to reserves
     * 1 - if champion not retired because disqualified
     * 2 - if champion not retired because not in team
     * -1 - if no such champion
     * @param nme is the name of the champion
     * @return as shown above
     **/
    public int retireChampion(String nme) {
        Champion championToRemove = null;
        for (Champion champ : championsTeam) {
            if (champ.getName().equalsIgnoreCase(nme)) {
                championToRemove = champ;

            }
        }

        if (championToRemove != null) {
            if (championToRemove.getState() != ChampionState.DISQUALIFIED) {
                championsTeam.remove(championToRemove);
                championsReserve.add(championToRemove);
                championToRemove.setState(ChampionState.WAITING);
                treasury += championToRemove.getEntryFee() / 2; //refund half the entry fee to the treasury
                return 0; //champion retired successfully
            } else {
                return 1; //champion cannot be retired because disqualified
            }
        } else {
            return championsReserve.stream().anyMatch(champ -> champ.getName().equalsIgnoreCase(nme)) ? 2 : -1;
        }
    }



    /**Returns a String representation of the champions in the vizier's team
     * or the message "No champions entered"
     *
     **/
    public String getTeam()
    {
        if (championsTeam.isEmpty()) {
        return "No champions entered";
        }
        StringBuilder sb = new StringBuilder("Vizier's Team of Champions:\n");
        for (Champion champ : championsTeam) {
            sb.append(champ.toString()).append("\n");
        }
        return sb.toString();
    }
    
     /**Returns a String representation of the disquakified champions in the vizier's team
     * or the message "No disqualified champions "
     *
     **/
    public String getDisqualified()
    {
        StringBuilder sb = new StringBuilder("Disqualified Champions:\n");
        championsTeam.stream().filter(champ -> champ.getState() == ChampionState.DISQUALIFIED)
            .forEach(champ -> sb.append(champ.toString()).append("\n"));
        return sb.length() > 23 ? sb.toString() : "No disqualified champions";
    }
    
//**********************Challenges************************* 
    /** returns true if the number represents a challenge
     * @param num is the  number of the challenge
     *
     **/
     public boolean isChallenge(int num)
     {
         return num > 0 && num <= challenges.size();
     }    
   
    /**
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    public String getChallenge(int num)
    {
        if (isChallenge(num)) {
        return challenges.get(num - 1).toString();
        }
        return "No such challenge";
    }
    
    /**
     * @return returns a String representation of all challenges
     **/
    public String getAllChallenges()
    {
        StringBuilder sb = new StringBuilder("All Challenges:\n");
        for (Challenge challenge : challenges) {
            sb.append(challenge.toString()).append("\n");
        }
        return sb.toString();
    }
    
    
       /** Retrieves the challenge represented by the challenge 
     * number.Finds a champion from the team who can meet the 
     * challenge. The results of meeting a challenge will be 
     * one of the following:  
     * 0 - challenge won by champion, add reward to the treasury, 
     * 1 - challenge lost on skills  - deduct reward from
     * treasury and record champion as "disqualified"
     * 2 - challenge lost as no suitable champion is  available, deduct
     * the reward from treasury 
     * 3 - If a challenge is lost and vizier completely defeated (no money and 
     * no champions to withdraw) 
     * -1 - no such challenge 
     * @param chalNo is the number of the challenge
     * @return an int showing the result(as above) of fighting the challenge
     */
       public int meetChallenge(int chalNo) {
           if (chalNo < 1 || chalNo > challenges.size()) {
               return -1; // No such challenge
           }

           Challenge challenge = challenges.get(chalNo - 1);
           boolean challengeWon = false;

           for (Champion champion : championsTeam) {
               if (champion.canEngageInChallenge(challenge.getType()) && champion.getSkillLevel() >= challenge.getSkillRequired()) {
                   if (treasury >= challenge.getReward()) {
                       treasury += challenge.getReward(); // Add reward to treasury
                       challengeWon = true;
                       break; // Challenge won, exit loop
                   }
               }
           }

           if (!challengeWon) {
               treasury -= challenge.getReward(); //deduct reward from treasury as penalty for losing

               //check if any champion loses due to insufficient skill level
               for (Champion champion : championsTeam) {
                   if (champion.canEngageInChallenge(challenge.getType()) && champion.getSkillLevel() < challenge.getSkillRequired()) {
                       champion.setState(ChampionState.DISQUALIFIED);
                       return 1; //Champion loses due to insufficient skill level
                   }
               }

               //additional logic to handle disqualified champions or complete defeat condition
               return isDefeated() ? 3 : 2; //Challenge lost, check if defeated completely
           }

           return 0; //Challenge won
       }





    //****************** private methods for Task 3 functionality*******************
    //*******************************************************************************
    private void setupChampions()
    {
        // Populate championsReserve list based on Appendix A data
        championsReserve.add(new Wizard("Ganfrank", 7, 400, "transmutation", true));
        championsReserve.add(new Wizard("Rudolf", 6, 400, "invisibility", true));
        championsReserve.add(new Warrior("Elblond", 1, 150, "sword"));
        championsReserve.add(new Warrior("Flimsi", 2, 200, "bow"));
        championsReserve.add(new Dragon("Drabina", 7, 500, false));
        championsReserve.add(new Dragon("Golum", 7, 500, true));
        championsReserve.add(new Warrior("Argon", 9, 900, "mace"));
        championsReserve.add(new Wizard("Neon", 2, 300, "translocation", false));
        championsReserve.add(new Dragon("Xenon", 7, 500, true));
        championsReserve.add(new Warrior("Atlanta", 5, 500, "bow"));
        championsReserve.add(new Wizard("Krypton", 8, 300, "fireballs", false));
        championsReserve.add(new Wizard("Hedwig", 1, 400, "flying", true));
    }
     
    private void setupChallenges()
    {
        // Populate challenges list based on Appendix A data
        challenges.add(new Challenge(1, ChallengeType.MAGIC, "Borg", 3, 100));
        challenges.add(new Challenge(2, ChallengeType.FIGHT, "Huns", 3, 120));
        challenges.add(new Challenge(3, ChallengeType.MYSTERY, "Ferengi", 3, 150));
        challenges.add(new Challenge(4, ChallengeType.MAGIC, "Vandal", 9, 200));
        challenges.add(new Challenge(5, ChallengeType.MYSTERY, "Borg", 7, 90));
        challenges.add(new Challenge(6, ChallengeType.FIGHT, "Goth", 8, 45));
        challenges.add(new Challenge(7, ChallengeType.MAGIC, "Frank", 10, 200));
        challenges.add(new Challenge(8, ChallengeType.FIGHT, "Sith", 10, 170));
        challenges.add(new Challenge(9, ChallengeType.MYSTERY, "Cardashian", 9, 300));
        challenges.add(new Challenge(10, ChallengeType.FIGHT, "Jute", 2, 300));
        challenges.add(new Challenge(11, ChallengeType.MAGIC, "Celt", 2, 250));
        challenges.add(new Challenge(12, ChallengeType.MYSTERY, "Celt", 1, 250));
    }
        
    // Possible useful private methods
//     private Challenge getAChallenge(int no)
//     {
//         
//         return null;
//     }
//    
//     private Champion getChampionForChallenge(Challenge chal)
//     {
//         
//         return null;
//     }

    //*******************************************************************************
    //*******************************************************************************
  
    //************************ Task 3.5 ************************************************/
    
    // ***************   file write/read  *********************
    /**
     * reads challenges from a comma-separated textfile and stores in the game
     * @param filename of the comma-separated textfile storing information about challenges
     */
    public void readChallenges(String filename)
    { 
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if(line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if(parts.length < 4) { // Ensure there are enough parts to parse
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                try {
                    String typeStr = parts[0].trim();
                    String enemy = parts[1].trim();
                    int skillRequired = Integer.parseInt(parts[2].trim());
                    int reward = Integer.parseInt(parts[3].trim());
                
                    ChallengeType type = ChallengeType.fromString(typeStr); // Use fromString for human-readable string
                    challenges.add(new Challenge(challenges.size() + 1, type, enemy, skillRequired, reward)); // Assuming 'number' is sequential and not provided in the file
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing numbers from line: " + line + ". Error: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid challenge type in file: " + parts[0] + ". Error: " + e.getMessage());
                }
            }
            System.out.println("Challenges loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("The challenges file was not found: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the challenges file: " + e.getMessage());
        }     
    }
    
    

            
            
        
     
    
     /** reads all information about the game from the specified file 
     * and returns a CARE reference to a Tournament object, or null
     * @param fname name of file storing the game
     * @return the game (as a Tournament object)
     */
    public Tournament loadGame(String fname)
    {   // uses object serialisation 
       Tournament tournament = null;
       try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))) {
            tournament = (Tournament) in.readObject();
            System.out.println("Game loaded successfully.");
       } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
       }
       return tournament;
    }
       
    
        
       

   
   /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
   public void saveGame(String fname)
   {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fname))) {
            out.writeObject(this);
            System.out.println("The game was saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while attempting to save the game: " + e.getMessage());
            
        }
   }
 

}



