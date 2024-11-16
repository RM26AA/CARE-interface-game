package cwk4;
import java.io.Serializable;

/**
 * The Wizard class represents a wizard champion in the Championships at Rare Earth (CARE) game.
 * Wizards are versatile champions capable of engaging in all types of challenges (Magic, Fight, Mystery)
 * thanks to their spellcasting abilities. Each wizard has a unique spell speciality and may also be
 * a necromancer, which can influence certain game mechanics or challenges.
 *
 * Attributes include the wizard's name, skill level, entry fee, specialty spell, and necromancer status.
 *
 * @R.Maunick, D.Ntuka, R.Mujsaku, T.Hau
 * @V9 - 23/04/2024
 */

public class Wizard extends Champion implements Serializable {
    private boolean isNecromancer;
    private String specialtySpell;

    public Wizard(String name, int skillLevel, int entryFee, String specialtySpell, boolean isNecromancer) {
        super(name, skillLevel, entryFee);
        this.specialtySpell = specialtySpell;
        this.isNecromancer = isNecromancer;
    }

    @Override
    public boolean canEngageInChallenge(ChallengeType challengeType) {
        // Wizards can participate in any challenge type
        return true;
    }
    
    public boolean isNecromancer() {
        return isNecromancer;
    }   //return Wizard is Necromancer or not

    public String getSpecialtySpell() {
        return specialtySpell;
    }   //return Wizard's specialtySpell

    @Override
    //return detail of the Wizard
    public String toString() {
        return super.toString() + ", Necromancer: " + (isNecromancer ? "Yes" : "No") + ", Specialty Spell: " + specialtySpell;
    }

    
}