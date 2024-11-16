package cwk4;
import java.io.Serializable;


/**
 * The Dragon class models a dragon champion within the CARE game universe. Dragons are powerful creatures
 * known for their formidable combat abilities. They can participate in Fight challenges and, if they possess
 * the rare ability to talk, Mystery challenges as well. Each dragon has a fixed skill level and entry fee,
 * and their ability to talk extends their utility in various game scenarios.
 *
 * Key attributes include the dragon's name, skill level, entry fee, and their capability to communicate verbally.
 * 
 * @R.Maunick, D.Ntuka, R.Mujsaku, T.Hau
 * @V9 - 23/04/2024
 */
public class Dragon extends Champion implements Serializable {
    private boolean canTalk;

    public Dragon(String name, int skillLevel, int entryFee, boolean canTalk) {
        super(name, skillLevel, entryFee);
        this.canTalk = canTalk;
    }

    @Override
    public boolean canEngageInChallenge(ChallengeType challengeType) {
        // Dragons can participate in Fight challenges, and Mystery if they can talk
        return challengeType == ChallengeType.FIGHT || (canTalk && challengeType == ChallengeType.MYSTERY);
    }

    public boolean canTalk() {
        return canTalk;
    }   //return Dragon can talk or not

    @Override
    //return detail of the Dragon
    public String toString() {
        return super.toString() + ", Can Talk: " + (canTalk ? "Yes" : "No");
    }
}
