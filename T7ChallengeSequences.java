/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cwk4.CARE;
import cwk4.Tournament;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author comqaam
 */
public class T7ChallengeSequences {
    CARE game;
    public T7ChallengeSequences() {
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
    
    //Action sequences
    @Test
    public void warriorDeadUsedAgain() {
        int expected = 2;
        game.enterChampion("Flimsi");
        game.meetChallenge(2);  //should be dead
        int actual = game.meetChallenge(2); //re-used ?
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorDeadUsedAgainMoney() {
        int expected = 1000-200-120-120;
        game.enterChampion("Flimsi");
        game.meetChallenge(2);  //should be dead
        game.meetChallenge(2); //re-used ?
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorWithdrawnBeforeFight() {
        int expected = 2;
        game.enterChampion("Argon");
        game.retireChampion("Argon");
        int actual = game.meetChallenge(2); //used ?
        assertEquals(expected, actual);
    }
    
    @Test
    public void warriorWithdrawnBeforeFightMoney() {
        int expected = 1000-900+450-120;
        game.enterChampion("Argon");
        game.retireChampion("Argon");
        game.meetChallenge(2);  //not available
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }

    @Test
    public void defeatAchieved() {
        int expected = 3;
        game.enterChampion("Rudolf");
        game.enterChampion("Flimsi");
        game.meetChallenge(8);  //lose
        game.meetChallenge(8);  //lose
        game.meetChallenge(8);  //no one left
        int actual = game.meetChallenge(8);  //no one left
        assertEquals(expected, actual);
    }

    @Test
    public void defeatWithWithdrawalMoney() {
        int expected = 1000-400-200-170+100-170-170;
        game.enterChampion("Rudolf");
        game.enterChampion("Flimsi");
        game.meetChallenge(8);  //lose
        game.retireChampion("Flimsi");
        game.meetChallenge(8);  //no one left
        game.meetChallenge(8);  //no one left
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatNotAchieved() {
        int expected = 2;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        int actual = game.meetChallenge(4);  //lose as no one available
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatNotAchievedMoney() {
        int expected = 1000-500-200-200-200-200;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }

    @Test
    public void defeatAchievedAfterWithdraw() {
        int expected = 3;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        game.retireChampion("Drabina");
        game.retireChampion("Flimsi"); //no one in Team
        game.meetChallenge(4);  //lose as no one available
        int actual = game.meetChallenge(4);  //lose as no one available
        assertEquals(expected, actual);
    }
    
    @Test
    public void defeatAchievedAfterWithdrawMoney() {
        int expected = 1000-500-200-200-200+250+100-200-200;
        game.enterChampion("Drabina");
        game.enterChampion("Flimsi");
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        game.retireChampion("Drabina");
        game.retireChampion("Flimsi"); //no one in Team
        game.meetChallenge(4);  //lose as no one available
        game.meetChallenge(4);  //lose as no one available
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    
// Add your own tests

    // winning 2 challenges and the money for it
    @Test
    public void WinningMoneyAfter2Challenges() {
        int expected = 920;
        game.enterChampion ("Krypton");
        game.meetChallenge(1);
        game.meetChallenge(2);
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }

    // adding a champion after losing a challenge with another
    @Test
    public void addChampionAfterLosingChallenge() {
        int expected = 1000-500+300+250-500;
        game.enterChampion ("Drabina");
        game.meetChallenge(10);
        game.retireChampion("Drabina");
        game.enterChampion("Atlanta");
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }

    //trying to add a champion with insufficient funds
    @Test
    public void tryToAddChampionWithInsufficientFunds() {
        int expected = 1000-200;
        game.enterChampion ("Flimsi");
        game.enterChampion("Argon");
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    //a sequence designed in our test plan to test multiple key features of the game.
    @Test
    public void testPlanSequence() {
        int expected = 1000-200+300-200+100;
        game.enterChampion ("Flimsi");
        game.enterChampion("Argon");
        game.enterChampion("Gandalf");
        game.meetChallenge(10);
        game.meetChallenge(4);
        game.retireChampion("Flimsi");

        int actual = game.getMoney();
        assertEquals(expected, actual);
    }

    //code that attempts to beat every fight challenge
    @Test
    public void beatEveryFightAttemptChallenge() {
        int expected = 1000-500+300+120-900-170+450-170;
        game.enterChampion("Atlanta");
        game.meetChallenge(10);
        game.meetChallenge(2);
        game.enterChampion("Argon");
        game.meetChallenge(8);
        game.retireChampion("Argon");
        game.meetChallenge(8);
        game.retireChampion("Atlanta");
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }
    //test to see if you can load into a non-existent challenge
    @Test
    public void meetNonExistentChallenge() {
        int expected = -1;
        game.enterChampion("Ganfrank");
        int actual = game.meetChallenge(99); // Non-existent challenge
        assertEquals(expected, actual);
    }

    @Test
    public void meetChallengeWithEmptyTeam() {
        int expected = 2;
        int actual = game.meetChallenge(1); // No champion entered
        assertEquals(expected, actual);
    }
    @Test
    public void meetChallengeWithEmptyTeamMoneyDeducted() {
        int initialTreasury = game.getMoney();
        int challengeReward = 100; //Assuming challenge 1 has a reward of 100
        game.meetChallenge(1); //No champion entered
        int expected = initialTreasury - challengeReward;
        int actual = game.getMoney();
        assertEquals(expected, actual);
    }





}
