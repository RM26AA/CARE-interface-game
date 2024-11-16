package cwk4;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the game
 * 
 * @author A.A.Marczyk
 * @version 20/01/24
 */
public class GameGUI 
{
    private CARE gp = new Tournament("Fred", "challengesAM.txt");
    private JFrame myFrame = new JFrame("Championships at Rare Earth Game");
    private JTextArea listing = new JTextArea();
    private JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

    public static void main(String[] args)
    {
        new GameGUI();
    }
    
    public GameGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());

        // Title Panel with background color
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(230, 230, 250)); // Lavender background
        titlePanel.setLayout(new BorderLayout());
    
        // Title
        JLabel titleLabel = new JLabel("Championships at Rare Earth Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setOpaque(true); // Make JLabel background color visible
        titleLabel.setBackground(new Color(230, 230, 250)); // Matching background with the panel
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        myFrame.add(titlePanel, BorderLayout.NORTH);
    
        // Text area and scroll pane with modern font
        listing.setFont(new Font("Helvetica", Font.PLAIN, 14));
        listing.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listing);
        myFrame.add(scrollPane, BorderLayout.CENTER);

        // Button Panel (modern aesthetic)
        createButton("Meet Challenge", e -> meetChallenge(), new ImageIcon("challenge_icon.png"));
        createButton("View State", e -> viewState(), new ImageIcon("view_icon.png"));
        createButton("Clear", e -> listing.setText(""), new ImageIcon("clear_icon.png"));
        createButton("Quit", e -> quitGame(), new ImageIcon("quit_icon.png"));
        buttonPanel.setBackground(new Color(230, 230, 250)); // Lavender background
        myFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Frame properties
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(600, 400); // Size can be adjusted
        myFrame.setLocationRelativeTo(null); // Center on screen
        myFrame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Champions menu
        JMenu championMenu = new JMenu("Champions");
        menuBar.add(championMenu);

        addMenuItem(championMenu, "List Champions in reserve", e -> listChampionsInReserve(), new ImageIcon("list_icon.png"));
        addMenuItem(championMenu, "View Champion", e -> viewChampion(), new ImageIcon("view_icon.png"));
        addMenuItem(championMenu, "Enter Champion", e -> enterChampion(), new ImageIcon("enter_icon.png"));

        // Challenges menu
        JMenu challengeMenu = new JMenu("Challenges");
        menuBar.add(challengeMenu);

        addMenuItem(challengeMenu, "List All Challenges", e -> listAllChallenges(), new ImageIcon("list_challenges_icon.png"));
    }
    
    private void createButton(String text, ActionListener action, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        button.addActionListener(action);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        buttonPanel.add(button);
    }
    
    private void addMenuItem(JMenu menu, String title, ActionListener actionListener, ImageIcon icon) {
        JMenuItem menuItem = new JMenuItem(title, icon);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
    }
    
    // Implementation of action handlers:
    private void listChampionsInReserve() 
    {
        listing.setVisible(true);
        String champions = gp.getReserve();
        listing.setText(champions);
    }
    
    private void viewChampion() 
    { 
        String name = JOptionPane.showInputDialog(myFrame, "Enter the name of the Champion to view:");
        if (name != null && !name.isEmpty()) {
            String details = gp.getChampionDetails(name);
            JOptionPane.showMessageDialog(myFrame, details, "Champion Details", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    
    private void enterChampion() 
    { 
        String name = JOptionPane.showInputDialog(myFrame, "Enter the name of the Champion to enter:");
        if (name != null && !name.isEmpty()) {
            int result = gp.enterChampion(name);
            String message = switch (result) {
                case 0 -> "Champion entered successfully.";
                case 1 -> "Champion is not in reserve.";
                case 2 -> "Not enough money in the treasury.";
                case -1 -> "No such champion.";
                default -> "Unexpected result: " + result;
            };
            JOptionPane.showMessageDialog(myFrame, message, "Entering Champion", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    
    private void listAllChallenges() 
    { 
        listing.setVisible(true);
        String challenges = gp.getAllChallenges();
        listing.setText(challenges);
    }
    
    private void meetChallenge() 
    { 
        String inputValue = JOptionPane.showInputDialog(myFrame, "Enter Challenge Number:");
        if (inputValue != null && !inputValue.isEmpty()) {
            try {
                int challengeNum = Integer.parseInt(inputValue);
                int result = gp.meetChallenge(challengeNum);
                String message = switch (result) {
                    case 0 -> "Challenge won by champion.";
                    case 1 -> "Challenge lost on skills, champion disqualified.";
                    case 2 -> "Challenge lost as no suitable champion is available.";
                    case 3 -> "Challenge lost and vizier completely defeated.";
                    case -1 -> "No such challenge.";
                    default -> "Unexpected result: " + result;
                };
                JOptionPane.showMessageDialog(myFrame, message);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(myFrame, "Invalid challenge number format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void viewState() 
    { 
        listing.setVisible(true);
        String state = gp.toString();
        listing.setText(state);
    }
    
    private void quitGame() 
    { 
        int answer = JOptionPane.showConfirmDialog(myFrame, "Are you sure you want to quit?", "Finish", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        } 
    }
    
}
   
