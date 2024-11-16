package cwk4;
import java.io.Serializable;

/**
 *
 * The Champion class serves as the base for all champion types in the Championships at Rare Earth (CARE) game.
 * It outlines the common properties and behaviors that all champions share, including their name, skill level,
 * and entry fee. The class is designed to be extended by specific champion types (e.g., Wizard, Warrior, Dragon)
 * that can have additional attributes and abilities. Champions are the key participants in challenges within the game,
 * and their performance is crucial to the player's success.
 *
 * @R.Maunick, D.Ntuka, R.Mujsaku, T.Hau
 * @V9 - 23/04/2024
 */


public abstract class Champion implements Serializable {
    protected String name;
    protected int skillLevel;
    protected int entryFee;
    protected ChampionState state;

    public Champion(String name, int skillLevel, int entryFee) {
        this.name = name;
        this.skillLevel = skillLevel;
        this.entryFee = entryFee;
        this.state = ChampionState.WAITING; // Default state
    }

    // Getters and Setters
    public String getName() {
        return name;
    }   //return champion's name

    public int getSkillLevel() {
        return skillLevel;
    }   //return champion's skill level

    public int getEntryFee() {
        return entryFee;
    }   //return entry fee of the champion

    public ChampionState getState() {
        return state;
    }   //return Champion's state

    public void setState(ChampionState state) {     //set Champion's state(WAITTING,ENTERED,DISQUALIFIED)
        this.state = state;
    }

    @Override
    //return detail of the champion
    public String toString() {
        return String.format("%s: Skill Level: %d, Entry Fee: %d, State: %s",
                name, skillLevel, entryFee, state);
    }

    // Abstract method to be implemented by subclasses for determining if they can engage in a challenge
    /**
     * Determines if the champion can engage in a specific type of challenge.
     * @param challengeType The type of challenge.
     * @return true if the champion can engage in the challenge, false otherwise.
     */
    public abstract boolean canEngageInChallenge(ChallengeType challengeType);
}

