package cwk4;
import java.io.Serializable;


/**
 * The Warrior class depicts a warrior champion in the CARE game, characterized by their physical prowess
 * and combat skills. Warriors specialize in Fight challenges, leveraging their preferred weapon to gain
 * an advantage in battle. Unlike Wizards and Dragons, Warriors do not engage in Magic or Mystery challenges,
 * focusing solely on combat. Each warrior sets their own entry fee, which reflects their skill level and
 * combat effectiveness.
 *
 * Attributes include the warrior's name, skill level, entry fee, and preferred weapon, defining their unique
 * capabilities and role within the game's challenges.
 * 
 * @R.Maunick, D.Ntuka, R.Mujsaku, T.Hau
 * @V9 - 23/04/2024
 */
public class Warrior extends Champion implements Serializable {
    private String preferredWeapon;

    public Warrior(String name, int skillLevel, int entryFee, String preferredWeapon) {
        super(name, skillLevel, entryFee);
        this.preferredWeapon = preferredWeapon;
    }

    @Override
    public boolean canEngageInChallenge(ChallengeType challengeType) {
        // Warriors can only participate in Fight challenges
        return challengeType == ChallengeType.FIGHT;
    }

    public String getPreferredWeapon() {
        return preferredWeapon;
    }   //return Warrior's Weapon

    @Override
    //return details of the Warrior
    public String toString() {
        return super.toString() + ", Preferred Weapon: " + preferredWeapon;
    }
}
