package cwk4;

import java.io.*;
/**
 * Enumeration class ChallengeType - list possible types of challenges
 * 
 * @author A.A.Marczyk
 * @version 01/03/2024
 */

public enum ChallengeType implements Serializable {
    MAGIC("Magic"), FIGHT("Fight"), MYSTERY("Mystery");
    
    private String type;
    
    private ChallengeType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return type;
    }

    // Adding a method to parse from the human-readable string if needed
    public static ChallengeType fromString(String text) {
        for (ChallengeType b : ChallengeType.values()) {
            if (b.type.equalsIgnoreCase(text.trim())) {
                return b;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
