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
public class T3ChampionsTest {
    CARE game;
    
    public T3ChampionsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Tournament("Ola");
    }
    
    @After
    public void tearDown() {
    }

     
    //a local method to check a String for contents - not a test as no @test
    private boolean containsText(String text, String[] str) {
        boolean result = true;
        for (String temp : str) {
            result = result && (text.toLowerCase()).contains(temp.toLowerCase());
        }
        return result;
    }
    
    @Test
    public void isChampionTest()
    {
        boolean actual = game.isInReserve("Flimsi");
        assertTrue(actual);
    }
    
    @Test
    public void getChampionDetailsTest(){
        String details = game.getChampionDetails("Argon");
        String[] xx = {"Argon","9", "900", "mace","Warrior"};
        boolean actual = containsText(details,xx );
        assertTrue(actual);
    }
    
    @Test
    public void championInReserveGanfrankDisplayed() {
        String result = game.getReserve();
        String[] xx = {"Ganfrank","true", "7", "400","transmutation", "Wizard"};
        boolean actual = containsText(result,xx );
        assertTrue(actual);
    }
    
    @Test
    public void ChampionInReserveRudolfDisplayed() {
        String result = game.getReserve();
        String[] xx = {"Rudolf", "true", "6", "400","invisibility","Wizard"};
        boolean actual = containsText(result,xx );
        assertTrue(actual);
    }
    
    @Test
    public void ChampionInReserveElblondDisplayed() {
        String result = game.getReserve();
        String[] xx = {"Elblond", "1", "150","sword", "Warrior"};
        boolean actual = containsText(result,xx );
        assertTrue(actual);
    }    
    
    @Test
    public void ChampionInReserveDrabinaDisplayed() {
        String result = game.getReserve();
        String[] xx = {"Drabina", "7", "500","false", "Dragon"};
        boolean actual = containsText(result,xx );
        assertTrue(actual);
    }
    
    @Test
    public void ChampionInReserveGolumDisplayed() {
        String result = game.getReserve();
        String[] xx = {"Golum", "7", "500","true"};
        boolean actual = containsText(result,xx );
        assertTrue(actual);
    }
    
    @Test
    public void ChampionInReserveNeonDisplayed() {
        String result = game.getReserve();
        String[] xx = {"Neon","2", "false", "300","translocation", "Wizard"};
        boolean actual = containsText(result,xx );
        assertTrue(actual);
    }

    @Test
    public void ChampionInReserveFlimsiDisplayed() {
        String result = game.getReserve();
        String[] xx = {"Flimsi"};
        boolean actual = containsText(result,xx );
        assertTrue(actual);
    }
    
    //Why were those chosen? You can add more but is it worth it ?

    @Test
    public void ChampionInReserveArgonDisplayed() {
        String result = game.getReserve();
        String[] xx = {"Argon", "9", "900", "Waiting in reserve","mace"};
        boolean actual = containsText(result,xx );
        assertTrue(actual);
    }

    @Test
    public void championInReserveGanfrankDisplayed2() {
        String result = game.getReserve();
        String[] xx = {"Ganfrank", "7", "400", "Waiting in reserve", "Yes", "transmutation"};
        boolean actual = containsText(result, xx);
        assertTrue(actual);
    }

    @Test
    public void retireChampionFromTeamTest() {
        String championName = "Flimsi";
        game.enterChampion(championName); //enter the champion first
        int result = game.retireChampion(championName);
        assertEquals(0, result); //expected 0 for successful retirement
        assertFalse(game.isInViziersTeam(championName)); //verify champion is not in the team
        assertTrue(game.isInReserve(championName)); //verify champion is back in reserve
    }

    @Test
    public void getTeamWithChampionsTest() {
        String championName = "Flimsi";
        game.enterChampion(championName);
        String teamOutput = game.getTeam();
        assertTrue(teamOutput.contains("Vizier's Team of Champions:"));
        assertTrue(teamOutput.contains(championName));
    }

    @Test
    public void getTeamWithNoChampionsTest() {
        String teamOutput = game.getTeam();
        assertEquals("No champions entered", teamOutput);
    }




}
