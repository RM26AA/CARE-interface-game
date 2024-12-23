/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cwk4.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aam
 */
public class T5MeetChallengeTest {
    CARE game;
    
    public T5MeetChallengeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Tournament("Jean");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
//Wizards    
    // Wizard facing magic
    @Test
    public void wizardFacingMagicWins() {
        int expected = 0;
        game.enterChampion("Ganfrank");
        int actual = game.meetChallenge(1);
        assertEquals(expected, actual);
    }
    
    @Test
    public void wizardFacingMagicWinsMoney() {
        int expected = 1000-400+100;
        game.enterChampion("Ganfrank");
        game.meetChallenge(1);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void wizardFacingMagicLosesOnSkill() {
        int expected = 1;
        game.enterChampion("Neon");
        int actual = game.meetChallenge(1);
        assertEquals(expected, actual);
    }
    
    @Test
    public void wizardFacingMagicLosesOnSkillMoneyDeducted() {
        int expected = 1000-300-100;
        game.enterChampion("Neon");
        game.meetChallenge(1);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
      
    @Test
    public void wizardLosingIsDisqualified() {
        game.enterChampion("Neon");
        game.meetChallenge(1);
        boolean actual = game.getChampionDetails("Neon").toLowerCase().contains("disqualified");
        assertTrue(actual);
    }
    
    //checking withdrawal of disqualified champion
    @Test
    public void cantWithdrawDead() {
        int expected = 1;
        game.enterChampion("Neon");
        game.meetChallenge(1);
        int actual = game.retireChampion("Neon");
        assertEquals(expected,actual);
    }
    
    @Test
    public void cantWithdrawDisqualifiedMoneyNotAffected() {
        int expected = 1000-300-100;
        game.enterChampion("Neon");
        game.meetChallenge(1);
        game.retireChampion("Neon");
        int actual= game.getMoney();
        assertEquals(actual,expected);
    }

    @Test
    public void wizardFacingNoSuchMagic() {
        int expected = -1;
        game.enterChampion("Ganfrank");
        int actual = game.meetChallenge(14);
        assertEquals(expected, actual);
    } 
    
    // Wizard facing fight
    @Test
    public void wizardFacingFightWins() {
        int expected = 0;
        game.enterChampion("Ganfrank");
        int actual = game.meetChallenge(2);
        assertEquals(expected, actual);
    }
    
   @Test
    public void wizardFacingFightWinsMoneyAdded() {
        int expected = 1000-400+120;
        game.enterChampion("Ganfrank");
        game.meetChallenge(2);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    } 
    
    @Test
    public void wizardFacingFightLosesOnSkill() {
        int expected = 1;
        game.enterChampion("Neon");
        int actual = game.meetChallenge(2);
        assertEquals(expected, actual);
    }
    
    @Test
    public void wizardFacingFightLosesOnSkillMoneyDeducted() {
        int expected = 1000-300-100;
        game.enterChampion("Neon");
        game.meetChallenge(1);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    // Wizard facing mystery
    @Test
    public void wizardFacingMysteryWins() {
        int expected = 0;
        game.enterChampion("Ganfrank");
        int actual = game.meetChallenge(3);
        assertEquals(expected, actual);
    }
    
   @Test
    public void wizardFacingMysteryWinsMoneyAdded() {
        int expected = 1000-400+150;
        game.enterChampion("Ganfrank");
        game.meetChallenge(3);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    } 
    
    @Test
    public void wizardFacingMysteryLosesOnSkill() {
        int expected = 1;
        game.enterChampion("Neon");
        int actual = game.meetChallenge(3);
        assertEquals(expected, actual);
    }
    
    @Test
    public void wizardFacingMysteryLosesOnSkillMoneyDeducted() {
        int expected = 1000-300-150;
        game.enterChampion("Neon");
        game.meetChallenge(3);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
   
//Warriors
    //Warrior facing magic - not allowed
    @Test
    public void warriorFacingMagicNotAllowed() {
        int expected = 2;
        game.enterChampion("Argon");
        game.meetChallenge(1);
        int actual = game.meetChallenge(1);
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorFacingMagicNotAllowedMoneyDeducted() {
        int expected = 0;
        game.enterChampion("Argon");
        game.meetChallenge(1);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
  
    //Warrior facing fight
    @Test
    public void warriorFacingFightAllowedWins() {
        int expected = 0;
        game.enterChampion("Argon");
        int actual = game.meetChallenge(2);
        assertEquals(expected, actual);
    }
      
    @Test
    public void warriorFacingFightAllowedWinsMoneyAdded() {
        int expected = 220;
        game.enterChampion("Argon");
        game.meetChallenge(2);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorFacingFightAllowedLosesOnSkill() {
        int expected = 1;
        game.enterChampion("Flimsi");
        int actual = game.meetChallenge(2);
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorFacingFightAllowedLosesMoneyDeducted() {
        int expected = 680;
        game.enterChampion("Flimsi");
        game.meetChallenge(2);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    //Warrior facing mystery - not allowed
    @Test
    public void warriorFacingMysteryNotAllowed() {
        int expected = 2;
        game.enterChampion("Argon");
        game.meetChallenge(3);
        int actual = game.meetChallenge(1);
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorFacingMysteryNotAllowedMoneyDeducted() {
        int expected = -50;
        game.enterChampion("Argon");
        game.meetChallenge(3);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    //Dragons - write your own tests

    // dragon facing fight
    @Test
    public void dragonFacingFightWins() {
        int expected = 0;
        game.enterChampion("Golum");
        int actual = game.meetChallenge(2);
        assertEquals(expected, actual);
    }
    //if a dragon wins a fight plus wins the reward for winning.
    @Test
    public void dragonFacingFightWinsMoney() {
        int expected = 1000-500+120;
        game.enterChampion("Golum");
        game.meetChallenge(2);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    //dragon loses a fight because their skill level is lower than the challenge
    @Test
    public void dragonFacingFightLosesOnSkill() {
        int expected = 1;
        game.enterChampion("Drabina");
        int actual = game.meetChallenge(8);
        assertEquals(expected, actual);
    }
    //dragon loses their fight because its skill level is lower than the challenge plus money is also deducted
    @Test
    public void dragonFacingFightLosesOnSkillMoneyDeducted() {
        int expected = 1000-500-170;
        game.enterChampion("Drabina");
        game.meetChallenge(8);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    //dragon that have the ability to talk, wins while facing mystery
    @Test
    public void dragonFacingMysteryIfTheyCanTalkWin() {
        int expected = 0;
        game.enterChampion("Golum");
        int actual = game.meetChallenge(3);
        assertEquals(expected, actual);
    }
    //dragon losing based of insufficient skill level plus they can talk
    @Test
    public void dragonFacingMysteryIfTheyCanTalkLose() {
        int expected = 1;
        game.enterChampion("Golum");
        int actual = game.meetChallenge(9);
        assertEquals(expected, actual);
    }
    //when dragon tries to face mystery when they can't talk
    @Test
    public void dragonTryingToFacingMysteryButTheyCantTalk() {
        int expected = 2;
        game.enterChampion("Drabina");
        game.meetChallenge(9);
        int actual =  game.meetChallenge(9);
        assertEquals(expected, actual);
    }
    //when dragon tries to face mystery when they can't talk plus gets money deducted from the treasury
    @Test
    public void dragonFacingMysteryNotAllowedMoneyDeducted() {
        int expected = 200;
        game.enterChampion("Drabina");
        game.meetChallenge(9);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }












}
