package assignment8;

/*************************************************************************************************************
 *
 * TTTWindow class - main method extends JFrame to check the functionality of TTTPanel.java and TicTacToe.java\
 *
 * main - Displays the screen.
 *
 *************************************************************************************************************/

import javax.swing.JFrame;  //needed to use swing components e.g. JFrame
import java.awt.Color;
import java.awt.Container;


public class TTT_Window extends JFrame{
    
    private static final long serialVersionUID = 1L;
    // Set up constants for width and height of frame.
    static final int WIDTH = 405;
    static final int HEIGHT = 400;
    
    // Constructor.
    public TTT_Window(String title) {
        // Set the title of the frame, must be before variable declarations.
        super(title);
        
        TTTPanel basicPanel;
        Container container;
        
        // Instantiate and add the basicPanel to the frame.
        basicPanel = new TTTPanel();
        basicPanel.setBackground(Color.getHSBColor(120, 200, 150));
        container = getContentPane();
        
        setLocationByPlatform(true);
        container.add(basicPanel);
        container.validate();
    }
    
    public static void main(String args[]) {
        TTT_Window frame =  new TTT_Window("TicTacToe Game");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true); // Show the application (frame)
    }
    
}





package assignment8;

/****************************************************************************
 *
 * Name: Hannah Brooks		Date: January 6, 2018
 *
 * TTTPanel Class
 *
 * Purpose: This class extends JPanel Class and implements Action Listener and Mouse Listener interfaces
 * The purpose of this class is to add various components to the panel (i.e. buttons and labels) and
 * allow them react to the actions performed by the user (i.e. mouse clicked, pressed, released, etc.).
 *
 * Methods:
 * TTTPanel() - Constructor.
 * actionPerformed(ActionEvent) - Returns void - Event handler for the buttons.
 * mousePressed(MouseEvent) - Returns void.
 * mouseReleased(MouseEvent) - Returns void.
 * mouseEntered(MouseEvent) - Returns void.
 * mouseExited(MouseEvent) - Returns void.
 * mouseClicked(MouseEvent) - Returns void.
 * disableListeners() - Returns void.
 * resetGame() - Returns void.
 *
 ****************************************************************************/

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.*;

public class TTTPanel extends JPanel implements ActionListener, MouseListener {
    // Because TTTPanel does not declare.
    private static final long serialVersionUID = 1L;
    
    private TicTacToe tttGame;
    
    private JButton[][] button;
    private JButton 	exitButton, resetButton;
    private JLabel 		playerLabel, statusLabel;
    
    private static int bWidth = 100;
    private static int bHeight = 50;
    
    // Constructor.
    public TTTPanel() {
        
        // Set the layout for the panel to not have a layout manager.
        setLayout(null);
        
        // Make and add a player JLabel.
        playerLabel = new JLabel("Player 1's turn");
        playerLabel.setBounds(110, 25, 240, 45);
        playerLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        add(playerLabel); // Add label to frame.
        
        // Make a 2nd JLabel with a different font.
        statusLabel = new JLabel("");
        statusLabel.setBounds(170, 180, 230, 200);
        statusLabel.setFont(new Font("Serif", Font.ITALIC, 25));
        add(statusLabel); // Add the label to frame.
        
        // Make the JButtons.
        button = new JButton[3][3];
        for (int row = 0, n; row < 3; row++) {
            n = -50;
            for(int column = 0; column < 3; column++, n+=100) {
                button[row][column] = new JButton("");
                button[row][column].setLocation((n+(bWidth)), (100+(row*bHeight)));
                button[row][column].setSize(bWidth,bHeight);
                add(button[row][column]);
                
                // Set all buttons to work with the event handlers.
                button[row][column].addActionListener(this);
                button[row][column].addMouseListener(this);
            }
        }
        
        // Add exit button.
        exitButton = new JButton("Exit");
        exitButton.setLocation(250, 300);
        exitButton.setSize(100,40);
        add(exitButton);
        
        // Add New Game button.
        resetButton = new JButton("New Game");
        resetButton.setLocation(50, 300);
        resetButton.setSize(125,40);
        add(resetButton);
        
        // Add event listeners for the buttons.
        exitButton.addActionListener(this);
        resetButton.addActionListener(this);
        exitButton.addMouseListener(this);
        resetButton.addMouseListener(this);
        
        // Create instance of TicTacToe
        tttGame = new TicTacToe(button, playerLabel, statusLabel);
        
        validate();
    }
    
    // This is the event handler for the button.
    public void actionPerformed(ActionEvent e) {
        // Ask the event which button it represents.
        if (e.getActionCommand().equals("Exit"))
            System.exit(0);
        
        if (e.getActionCommand().equals("New Game")){
            // Reset the game.
            resetGame();
        }
        return;
    }
    
    public void mousePressed(MouseEvent event) {
        
        // Get location of mouse click.
        int x = event.getXOnScreen() - super.getLocationOnScreen().x;
        int y = event.getYOnScreen() - super.getLocationOnScreen().y;
        
        if((event.getButton()>0) && (y < 300)){
            // Determine the row and column;
            int buttonRow = (y-100)/bHeight;
            int buttonCol = (x-50)/bWidth;
            
            // Display 'x' or 'o' according to the button's location
            tttGame.displaySymbol(buttonRow, buttonCol);
            
            if (tttGame.isGameOver()){
                // Disable mouse listener and action listener for the buttons when the game ends.
                disableListeners();
            }
        }
        return;
    }
    
    public void mouseReleased(MouseEvent event) {
        // System.out.println("Mouse Released");
    }
    
    public void mouseEntered(MouseEvent event) {
        // System.out.println("Mouse Entered");*/
    }
    
    public void mouseExited(MouseEvent event) {
        // System.out.println("MouseExited");*/
    }
    
    public void mouseClicked(MouseEvent event) {
        // System.out.println("MouseClicked");*/
    }
    
    // Disable listeners when game over.
    void disableListeners(){
        for(int row = 0; row < 3; row++){
            for (int column = 0; column < 3; column++){
                button[row][column].removeActionListener(this);
                button[row][column].removeMouseListener(this);
            }
        }
    }
    
    void resetGame(){
        // Change everything back to how it was.
        for (int row = 0; row < 3; row++){
            for(int column = 0; column < 3; column++){
                button[row][column].setText("");  // Get rid of all the xs and os.
                
                // Reset all buttons to work with the event handlers.
                button[row][column].addActionListener(this);
                button[row][column].addMouseListener(this);
            }
        }
        tttGame.setTotalTurns(0);  // Reset the total number of turns between players to zero.
        // Reset texts on the labels.
        tttGame.setCurrentPlayer(1);
        statusLabel.setText("");
        statusLabel.setBounds(170, 180, 230, 200);  
        tttGame.setGameOver(false);
    }		
}





package assignment8;
import javax.swing.JButton;
import javax.swing.JLabel;

/****************************************************************************
 *
 * Name: Hannah Brooks		Date: January 6, 2018
 *
 * TicTacToe Class
 *
 * Purpose: This class implements the rules of Tic-Tac-Toe.
 *
 * Methods:
 * TicTacToe(JButton[][], JLabel, JLabel) - Constructor.
 * displaySymbol(int, int) - Returns void.
 * checkEmpty(int, int) - Returns boolean.
 * switchPlayers(int, int) - Returns void.
 * checkStatus() - Returns boolean.
 * checkRow() - Returns boolean.
 * checkCol() - Returns boolean.
 * checkDia() - Returns boolean.
 * getCurrentPlayer() - Return int.
 * setCurrentPlayer(int) - Setter for currentPlayer (void).
 * setTotalTurns(int) - Setter for totalTurns (void).
 * isGameOver() - Returns boolean.
 * setGameOver(boolean) - Setter for gameOver (void).
 *
 ****************************************************************************/

public class TicTacToe {
    private JButton[][] buttonCopy;
    private JLabel playerLabelCopy, statusLabelCopy;
    private int currentPlayer;
    private int totalTurns = 0;;  // use the total turn to check if the game is a tie (when total turn reaches nine with no one wins)
    private boolean gameOver = false;  // used to check if the game is over
    
    // Constructor.
    public TicTacToe(JButton[][] button, JLabel playerLabel, JLabel statusLabel) {
        buttonCopy = button;
        playerLabelCopy = playerLabel;
        statusLabelCopy = statusLabel;
    }
    
    // Place either 'X' or 'O'.
    public void displaySymbol(int row, int column) {
        boolean win;  // Record the final status.
        
        // If the button has not been clicked during the game.
        if (checkEmpty(row, column)) {
            // Display the symbol.
            switchPlayers(row, column);
            totalTurns++;
            
            win = checkStatus();
            
            // Now check if it's a tie.
            if (!win) {
                if (totalTurns == 9) {  // Max 9 turns per game.
                    // If it's a tie.
                    statusLabelCopy.setText("Draw! No winner");
                    statusLabelCopy.setBounds(120, 180, 230, 200);
                    gameOver = true;  // Mark as game over.
                }
            }
            else {  // If a player wins.
                statusLabelCopy.setBounds(120, 180, 230, 200);
                gameOver = true;  // Mark as game over
                // Current player is not the player who wins.
                if (currentPlayer == 2)
                    statusLabelCopy.setText("Player 1 Wins!");
                else
                    statusLabelCopy.setText("Player 2 Wins!");
            }
        }
    }
    
    // Check if the button has been clicked yet.
    public boolean checkEmpty(int row, int column) {
        if (buttonCopy[row][column].getText().isEmpty())
            return true;
        else
            return false;
    }
    
    // Switch between players.
    public void switchPlayers(int row, int column) {
        if(playerLabelCopy.getText().equals("Player 1's turn")) {
            buttonCopy[row][column].setText("X");
            playerLabelCopy.setText("Player 2's turn");
            currentPlayer = 2;  // Mark the current player .
        }
        else{
            buttonCopy[row][column].setText("O");
            playerLabelCopy.setText("Player 1's turn");
            currentPlayer = 1;  // Mark the current player.
        }
    }
    
    // Check which player wins or if it's a tie.
    public boolean checkStatus() {
        boolean rowWin, columnWin, diagonalWin;
        
        rowWin = checkRow();
        columnWin = checkCol();
        diagonalWin = checkDia();
        
        // Now check for a tie.
        if ((rowWin == false)&&(columnWin == false)&&(diagonalWin == false))
            return false;
        else
            return true;
    }
    
    // Check each row for three identical symbols.
    public boolean checkRow() {
        boolean win = false;
        for(int i = 0; i < 3; i++) {
            if ((buttonCopy[0][i].getText().equals(buttonCopy[1][i].getText())) && (buttonCopy[1][i].getText().equals(buttonCopy[2][i].getText()))) {
                if (!(buttonCopy[0][i].getText().equals(""))) // Blank button.
                    win = true;
            }
        }
        return win;
    }
    
    // Check if three symbols are the same on each column.
    public boolean checkCol() {
        boolean win = false;
        for(int i = 0; i < 3; i++) {
            if ((buttonCopy[i][0].getText().equals(buttonCopy[i][1].getText())) && (buttonCopy[i][1].getText().equals(buttonCopy[i][2].getText()))) {
                if (!(buttonCopy[i][0].getText().equals(""))) // Blank button.
                    win = true;
            }
        }
        return win;
    }
    
    // Check both diagonals if the symbols are the same.
    public boolean checkDia() {
        boolean win = false;
        if((buttonCopy[0][0].getText().equals(buttonCopy[1][1].getText())) && (buttonCopy[1][1].getText().equals(buttonCopy[2][2].getText()))) {
            if (buttonCopy[0][0].getText().equals(""))
                win = false;
            else
                win = true;
        }
        
        if ((buttonCopy[0][2].getText().equals(buttonCopy[1][1].getText())) && (buttonCopy[1][1].getText().equals(
                                                                                                                  buttonCopy[2][0].getText()))) {
            if (buttonCopy[0][2].getText().equals(""))
                win = false;
            else
                win = true;
        }
        return win;
    }
    
    // Return the current player as an int.
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    // Set the current player.
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    // Set the total number of turns between players (used for reset).
    public void setTotalTurns(int totalTurns) {
        this.totalTurns = totalTurns;
    }
    
    // Return boolean if game is over.
    public boolean isGameOver() {
        return gameOver;
    }
    
    // Set gameOver (used for reset).
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
