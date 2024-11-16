package cwk4;


/**
 * The Challenge class encapsulates the details of a challenge within the CARE game. Challenges are
 * pivotal events where champions can demonstrate their skills and contribute to their team's treasury
 * when successful. Each challenge is characterized by a unique number, type (e.g., Magic, Fight, Mystery),
 * an enemy, a required skill level for participation, and a reward for success. This class supports
 * the game's dynamics by defining the various challenges that champions can undertake, influencing
 * the strategic decisions players make when assembling their champion team.
 * 
 * @R.Maunick, D.Ntuka, R.Mujsaku, T.Hau
 * @V9 - 23/04/2024
 */


public class Challenge {
    private int number;
    private ChallengeType type;
    private String enemy;
    private int skillRequired;
    private int reward;

    public Challenge(int number, ChallengeType type, String enemy, int skillRequired, int reward) {
        this.number = number;
        this.type = type;
        this.enemy = enemy;
        this.skillRequired = skillRequired;
        this.reward = reward;
    }

    // Getters and Setters

    public int getNumber() {
        return number;
    }   //return number of the challenge

    public ChallengeType getType() {
        return type;
    }   //return type of the challenge

    public String getEnemy() {
        return enemy;
    }   //return enemy's name

    public int getSkillRequired() {
        return skillRequired;
    }   //return skill level that required for the challenge

    public int getReward() {
        return reward;
    }   //return reward amount

    @Override
    //return detail of the challenge
    public String toString() {
        return String.format("Challenge %d: Type: %s, Enemy: %s, Skill Required: %d, Reward: %d",
                number, type, enemy, skillRequired, reward);
    }
}

