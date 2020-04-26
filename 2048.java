package cummulativeAssignment;

/*************************************************************************************************************
 *
 * Name: Hannah Brooks		Date: January 24, 2018
 *
 * Window2048 class - main method extends JFrame to check the functionality of Panel2048.java and Game2048.java.
 *
 * main - Displays the screen.
 *
 *************************************************************************************************************/

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;

public class Window2048 extends JFrame {
    
    private static final long serialVersionUID = 1L;
    // Set up constants for width and height of frame.
    static final int WIDTH = 540;
    static final int HEIGHT = 800;
    
    // Constructor.
    public Window2048(String title) {
        // Set the title of the frame, must be before variable declarations.
        super(title);
        
        Panel2048 basicPanel;
        Container container;
        
        // Instantiate and add the basicPanel to the frame.
        basicPanel = new Panel2048();
        basicPanel.setBackground(new Color(250, 248, 229));
        container = getContentPane();
        
        setLocationByPlatform(true);
        container.add(basicPanel);
        container.validate();
    }
    
    // Main.
    public static void main(String args[]) {
        Window2048 frame =  new Window2048("2048");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true); // Show the application (frame).
    }
    
}


package cummulativeAssignment;

/****************************************************************************
 *
 * Name: Hannah Brooks		Date: January 24, 2018
 *
 * Panel2048 Class
 *
 * Purpose: This class extends JPanel Class and implements Action Listener, Mouse Listener and Key
 * Listener interfaces. The purpose of this class is to add various components to the panel (i.e. buttons and labels) and
 * allow them react to the actions performed by the user (i.e. mouse clicked, key pressed, etc.).
 *
 * Methods:
 * Panel2048() - Constructor.
 * resetGame() - Returns void.
 * actionPerformed(ActionEvent) - Returns void - Event handler for the buttons.
 * keyPressed(KeyEvent) - Returns void.
 * mousePressed(MouseEvent) - Returns void.
 * mouseReleased(MouseEvent) - Returns void.
 * mouseEntered(MouseEvent) - Returns void.
 * mouseExited(MouseEvent) - Returns void.
 * mouseClicked(MouseEvent) - Returns void.
 * KeyTyped(KeyEvent) - Returns void.
 * KeyReleased(KeyEvent) - Returns void.
 *
 ****************************************************************************/

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

public class Panel2048 extends JPanel  implements ActionListener, MouseListener, KeyListener{
    // Because Panel2048 does not declare.
    private static final long serialVersionUID = 1L;
    
    // Instance of Game2048.
    private Game2048 play2048;
    
    // Private fields.
    private JLabel[][] board;
    private JButton 	resetButton;
    private JLabel 		scoreLabel, backLabel, label2048, highscoreLabel, statusLabel;
    
    // Tile width and height.
    private static int bWidth = 110;
    private static int bHeight = 110;
    
    // Constructor.
    public Panel2048() {
        
        // Set the layout for the panel to not have a layout manager.
        setLayout(null);
        
        // Make and add scoreLabel and highscoreLabel.
        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setVerticalAlignment(JLabel.TOP);
        scoreLabel.setBackground(new Color(199, 198, 184));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setOpaque(true);
        scoreLabel.setBounds(260, 20, 120, 40);
        scoreLabel.setFont(new Font("Clear Sans", Font.BOLD, 13));
        add(scoreLabel); // Add the label to frame.
        
        highscoreLabel = new JLabel("", SwingConstants.CENTER);
        highscoreLabel.setVerticalAlignment(JLabel.TOP);
        highscoreLabel.setBackground(new Color(199, 198, 184));
        highscoreLabel.setForeground(Color.WHITE);
        highscoreLabel.setOpaque(true);
        highscoreLabel.setBounds(400, 20, 120, 40);
        highscoreLabel.setFont(new Font("Clear Sans", Font.BOLD, 13));
        add(highscoreLabel); // Add the label to frame.
        
        // Make and add the status label to display rules, wins, losses and highest tile.
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Courier New", Font.BOLD, 20));
        statusLabel.setBackground(new Color(250, 248, 229));
        statusLabel.setOpaque(true);
        statusLabel.setVisible(true);
        statusLabel.setBounds(260, 120, 260, 120);
        add(statusLabel);
        
        // Make and add label for the logo.
        label2048 = new JLabel("2048", SwingConstants.CENTER);
        label2048.setVerticalAlignment(JLabel.CENTER);
        label2048.setBackground(new Color(235, 222, 42));
        label2048.setFont(new Font("Clear Sans", Font.BOLD, 50));
        label2048.setForeground(Color.WHITE);
        label2048.setOpaque(true);
        label2048.setBounds(20, 20, 220, 220);
        add(label2048);
        
        // Make the JLabels that make up the board.
        board = new JLabel[4][4];
        for (int row = 0, b = 272, n = 0; row < 4; row++, b+=12) {
            n = -78;
            for(int column = 0; column < 4; column++, n+=122) {
                board[row][column] = new JLabel("");
                board[row][column].setBackground(new Color(199, 198, 184));
                board[row][column].setOpaque(true);
                board[row][column].setLocation((n+(bWidth)), (b+(row*bHeight)));
                board[row][column].setSize(bWidth,bHeight);
                add(board[row][column]);
            }
        }
        
        // Label to fill the background of the board.
        backLabel = new JLabel("");
        backLabel.setBackground(new Color(176, 175, 158));
        backLabel.setOpaque(true);
        backLabel.setBounds(20, 260, 500, 500);
        add(backLabel);
        
        // Add New Game button.
        resetButton = new JButton("New Game");
        resetButton.setBackground(new Color (102, 104, 75));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Clear Sans", Font.BOLD, 30));
        resetButton.setBorderPainted(false);
        resetButton.setOpaque(true);
        resetButton.setLocation(260, 70);
        resetButton.setSize(260, 40);
        add(resetButton);
        
        // Create instance of TicTacToe
        play2048 = new Game2048(board, scoreLabel, highscoreLabel, statusLabel);
        play2048.start();
        
        // Add key listeners for the panel.
        addKeyListener(this);
        this.setFocusable(true);
        
        // Add event and mouse listener for the resetButton.
        resetButton.setFocusable(false);
        resetButton.addMouseListener(this);
        resetButton.addActionListener(this);
        
        validate();
        return;
    }
    
    void resetGame(){
        // Change everything back to how it was.
        for (int row = 0; row < 4; row++){
            for(int column = 0; column < 4; column++){
                board[row][column].setBackground(new Color(199, 198, 184));
                board[row][column].setText("");
                board[row][column].setOpaque(true);
                board[row][column].setVisible(true);
            }
        }
        
        // Start the game again.
        play2048.start();
        
        resetButton.setFocusable(false);
        return;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Game"))
            resetGame();
        return;
    }
    
    public void keyPressed(KeyEvent event) {
        int keyCode = event.getKeyCode();
        
        // The corresponding direction from the input.
        switch(keyCode) {
            case KeyEvent.VK_UP:
                play2048.moveTiles(1);
                break;
                
            case KeyEvent.VK_DOWN:
                play2048.moveTiles(2);
                break;
                
            case KeyEvent.VK_LEFT:
                play2048.moveTiles(3);
                break;
                
            case KeyEvent.VK_RIGHT :
                play2048.moveTiles(4);
                break;
        }
        return;
    }
    
    // Unused mouse and key event methods.
    public void mousePressed(MouseEvent event) {}
    
    public void mouseReleased(MouseEvent event) {}
    
    public void mouseEntered(MouseEvent event) {}
    
    public void mouseExited(MouseEvent event) {}
    
    public void mouseClicked(MouseEvent event) {}
    
    public void keyTyped(KeyEvent event) {}
    
    public void keyReleased(KeyEvent e) {	}
    
}


package cummulativeAssignment;

/****************************************************************************
 *
 * Name: Hannah Brooks		Date: January 24, 2018
 *
 * Game2048 Class
 *
 * Purpose: This class implements the rules of 2048.
 *
 * Methods:
 * Game2048(JLabel[][], JLabel, JLabel, JLabel) - Constructor.
 * generateNewTile - Returns void.
 * start() - Returns void.
 * newTile() - Returns void.
 * newNumber() - Returns int.
 * clearTile() - Returns void.
 * moveTile(int) - Returns void.
 * loss() - Returns void.
 * winner() - Returns void.
 * largestTile() - Returns int.
 * setScore(int) - Setter for score (void).
 * getScore() - Returns int.
 * setHighScore(int) - Setter for highScore (void).
 * getHighScore() - Returns int.
 *
 ****************************************************************************/

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.swing.JLabel;

public class Game2048 {
    
    // Private field declaration.
    private JLabel[][] boardCopy;
    private JLabel scoreLabelCopy, highscoreLabelCopy, statusLabelCopy;
    private int score, highScore;
    
    // Tile colour declarations.
    private Color tile2 = new Color (235, 229, 222);
    private Color tile4 = new Color (230, 224, 180);
    private Color tile8 = new Color (236, 191, 140);
    private Color tile16 = new Color (236, 176, 56);
    private Color tile32 = new Color (246, 154, 34);
    private Color tile64 = new Color (243, 74, 102);
    private Color tile128 = new Color (252, 242, 109);
    private Color tile256 = new Color (242, 221, 36);
    private Color tile512 = new Color (240, 225, 91);
    private Color tile1024 = new Color (247, 224, 16);
    private Color tile2048 = new Color (255, 255, 51);
   	
    // Constructor.
    public Game2048(JLabel[][] board, JLabel scoreLabel, JLabel highscoreLabel, JLabel statusLabel) {
        boardCopy = board;
        scoreLabelCopy = scoreLabel;
        highscoreLabelCopy = highscoreLabel;
        statusLabelCopy = statusLabel;
        scoreLabelCopy.setText("<html>Score:<BR>0");
        highscoreLabelCopy.setText("<html>High Score:<BR>0");
    }
    
    // New tile generator after each move.
    public void generateNewTile() {
        // New random generator.
        Random rand = new Random();
        int row, col;
        
        while (true) {
            // Find a random row and column choice.
            row = rand.nextInt(4);
            col = rand.nextInt(4);
            
            // Find a row and column with a free spot.
            if (boardCopy[row][col].getText() == "") {
                boardCopy[row][col].setText(Integer.toString(newNumber()));
                newTile(boardCopy[row][col]);
                break;
            }
        }
        return;
    }
    
    // The start method to get the game going.
    public void start() {
        // Generate new tiles;
        generateNewTile();
        generateNewTile();
        
        // Set score and highScore labels.
        setScore(0);
        scoreLabelCopy.setText("<html>Score:<BR>" + getScore());
        statusLabelCopy.setText("<html><BR><center>Join the numbers together to get the 2048 tile!");
        return;
    }
    
    // Make the JLabel look like a tile.
    public void newTile(JLabel tile) {
        // Assign the standard properties to the tile.
        tile.setVerticalAlignment(JLabel.CENTER);
        tile.setHorizontalAlignment(JLabel.CENTER);
        tile.setFont(new Font("Clear Sans", Font.BOLD, 30));
        tile.setForeground(Color.WHITE);
        tile.setOpaque(true);
        
        // Set the tiles to their appropriate color.
        if (tile.getText().equals("2"))
            tile.setBackground(tile2);
        else if (tile.getText().equals("4"))
            tile.setBackground(tile4);
        else if (tile.getText().equals("8"))
            tile.setBackground(tile8);
        else if (tile.getText().equals("16"))
            tile.setBackground(tile16);
        else if (tile.getText().equals("32"))
            tile.setBackground(tile32);
        else if (tile.getText().equals("64"))
            tile.setBackground(tile64);
        else if (tile.getText().equals("128"))
            tile.setBackground(tile128);
        else if (tile.getText().equals("256"))
            tile.setBackground(tile256);
        else if (tile.getText().equals("512"))
            tile.setBackground(tile512);
        else if (tile.getText().equals("1024"))
            tile.setBackground(tile1024);
        else if (tile.getText().equals("2048"))
            tile.setBackground(tile2048);
        else
            tile.setBackground(Color.BLACK);
        return;
    }
    
    // Make a number for the tiles.
    public int newNumber() {
        // New random generator.
        Random rand = new Random();
        
        // Find the random number.
        int rng = rand.nextInt(2) + 1;
        
        // Return either 2 or 4.
        return (rng * 2);
    }
    
    // Clear the tile.
    public void clearTile (JLabel tile) {
        // Reset the tile to it's original state.
        tile.setText("");
        tile.setBackground(new Color(199, 198, 184));
        tile.setOpaque(true);
        return;
    }
    
    // Merge and move the tiles.
    public void moveTiles (int num) {
        boolean canMove = false;
        
        if (!loss()) {
            switch (num) {
                case 1: // Move up.
                    // Merge up.
                    for (int i = 0; i < 4; i++) { // Row loop.
                        for (int k = 0; k < 4; k++) { // Column loop.
                            if (!boardCopy[i][k].getText().equals("")) { // If the tile is not null.
                                for (int j = i +1; j < 4; j++) { // Move one tile up and search for tiles of the same value.
                                    if (boardCopy[i][k].getText().equals(boardCopy[j][k].getText())) {
                                        boardCopy[i][k].setText("" + 2*Integer.valueOf(boardCopy[i][k].getText())); // Find new number and insert it into the tile.
                                        score+=Integer.valueOf(boardCopy[i][k].getText()); // Increase score
                                        newTile(boardCopy[i][k]); // Add a new tile with it's new score.
                                        clearTile(boardCopy[j][k]); // Clear the previous score.
                                        canMove = true; // Confirm that there was a move.
                                    }
                                }
                            }
                        }
                    }
                    // Shift up.
                    for (int i = 0; i < 4; i++) {
                        for (int k = 0; k < 4; k++) {
                            if (!boardCopy[i][k].getText().equals("")) { // If the tile is not null.
                                for (int j = i-1; j >= 0; j--) { // Starting from the top.
                                    if (boardCopy[j][k].getText().equals("")) { // If there is a tile above that is null.
                                        boardCopy[j][k].setText(boardCopy[j+1][k].getText()); // Switch those tiles.
                                        ;        								newTile(boardCopy[j][k]);
                                        clearTile(boardCopy[j+1][k]);
                                        canMove = true; // If there were no merges, there could have been a shift; to re-confirm.
                                    }
                                }
                                
                            }
                        }
                    }
                    break;
                    
                case 2: // Move down.
                    // Merge down.
                    for (int i = 3; i >= 0; i--) {
                        for (int k = 0; k < 4; k++) {
                            if (!boardCopy[i][k].getText().equals("")) {
                                for (int j = i - 1; j >= 0; j--) {
                                    if (boardCopy[i][k].getText().equals(boardCopy[j][k].getText())) {
                                        boardCopy[i][k].setText("" + 2*Integer.valueOf(boardCopy[i][k].getText()));
                                        score+=Integer.valueOf(boardCopy[i][k].getText());
                                        newTile(boardCopy[i][k]);
                                        clearTile(boardCopy[j][k]);
                                        canMove = true;
                                    }
                                }
                            }
                        }
                    }
                    // Shift down.
                    for (int i = 3; i >= 0; i--) {
                        for (int k = 0; k < 4; k++) {
                            if (!boardCopy[i][k].getText().equals("")) {
                                for (int j = i+1; j < 4; j++) {
                                    if (boardCopy[j][k].getText().equals("")) {
                                        boardCopy[j][k].setText(boardCopy[j-1][k].getText());
                                        ;        								newTile(boardCopy[j][k]);
                                        clearTile(boardCopy[j-1][k]);
                                        canMove = true;
                                    }
                                }
                                
                            }
                        }
                    }
                    break;
                    
                case 3: // Move left.
                    // Merge left.
                    for (int i = 0; i < 4; i++) {
                        for (int k = 0; k < 4; k++) {
                            if (!boardCopy[i][k].getText().equals("")) {
                                for (int j = k+1; j < 4; j++) {
                                    if (boardCopy[i][k].getText().equals(boardCopy[i][j].getText())) {
                                        boardCopy[i][k].setText("" + 2*Integer.valueOf(boardCopy[i][k].getText()));
                                        scoreLabelCopy.setText("<html>Score:<BR>" + (score+=Integer.valueOf(boardCopy[i][k].getText())));
                                        score+=Integer.valueOf(boardCopy[i][k].getText());
                                        newTile(boardCopy[i][k]);
                                        clearTile(boardCopy[i][j]);
                                        canMove = true;
                                    }
                                }
                            }
                        }
                    }
                    // Shift left.
                    for (int i = 0; i < 4; i++) {
                        for (int k = 0; k < 4; k++) {
                            if (!boardCopy[i][k].getText().equals("")) {
                                for (int j = k-1; j >= 0; j--) {
                                    if (boardCopy[i][j].getText().equals("")) {
                                        boardCopy[i][j].setText(boardCopy[i][j+1].getText());
                                        ;        								newTile(boardCopy[i][j]);
                                        clearTile(boardCopy[i][j+1]);
                                        canMove = true;
                                    }
                                }
                                
                            }
                        }
                    }
                    break;
                    
                case 4: // Move right.
                    // Merge right.
                    for (int i = 0; i < 4; i++) {
                        for (int k = 3; k >= 0; k--) {
                            if (!boardCopy[i][k].getText().equals("")) {
                                for (int j = k-1; j >= 0; j--) {
                                    if (boardCopy[i][k].getText().equals(boardCopy[i][j].getText())) {
                                        boardCopy[i][k].setText("" + 2*Integer.valueOf(boardCopy[i][k].getText()));
                                        scoreLabelCopy.setText("<html>Score:<BR>" + (score+=Integer.valueOf(boardCopy[i][k].getText())));
                                        score+=Integer.valueOf(boardCopy[i][k].getText());
                                        newTile(boardCopy[i][k]);
                                        clearTile(boardCopy[i][j]);
                                        canMove = true;
                                    }
                                }
                            }
                        }
                    }
                    // Shift right.
                    for (int i = 0; i < 4; i++) {
                        for (int k = 3; k >= 0; k--) {
                            if (!boardCopy[i][k].getText().equals("")) {
                                for (int j = k+1; j < 4; j++) {
                                    if (boardCopy[i][j].getText().equals("")) {
                                        boardCopy[i][j].setText(boardCopy[i][j-1].getText());
                                        newTile(boardCopy[i][j]);
                                        clearTile(boardCopy[i][j-1]);
                                        canMove = true;
                                    }
                                }
                                
                            }
                        }
                    }
                    break;
            }
        }
        // Set score and highScore labels.
        scoreLabelCopy.setText("<html>Score:<BR>" + score);
        
        if (score >= highScore) {
            highScore = score;
            highscoreLabelCopy.setText("<html>High Score:<BR>" + highScore);
        }
        
        // If the user has neither won nor lost - check for both.
        if (statusLabelCopy.getText().equals("<html><BR><center>Join the numbers together to get the 2048 tile!")) {
            if (winner()) 
                statusLabelCopy.setText("YOU WIN");
            else if (loss())
                statusLabelCopy.setText("GAME OVER");
        }
        else if (!loss()) // If the user has won, but are still playing.
            statusLabelCopy.setText("Highest Tile = " + largestTile());  
        
        // If the user can move, generate a new tile.
        if (canMove)	
            generateNewTile(); 
        return;
    }
    
    // Check if the user has lost.
    public boolean loss() {
        // Check for spaces.
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (boardCopy[i][j].getText().equals(""))
                    return false;
            }
        } 
        
        // Check for tiles of the same value beside each other.
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                // Check the tile in the right of the chosen tile. Ignore last column.
                if (j < 3){
                    if (boardCopy[i][j].getText().equals(boardCopy[i][j + 1].getText()))
                        return false;
                }
                // Check the tile below the chosen tile. Ignore last row.
                if (i < 3){
                    if (boardCopy[i][j].getText().equals(boardCopy[i + 1][j].getText()))
                        return false;
                }
            }
        }
        return true;
    }
    
    // Check if the user has won.
    public boolean winner() {
        // Find the 2048 tile.
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                if (boardCopy[i][k].getText().equals("2048")) {
                    return true;
                }
            }
        }   	
        return false;
    }
    
    // Find the largest tile in the array.
    public int largestTile () {
        int max = 0;
        
        // Find the maximum number in the board.
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                if (boardCopy[i][k].getText() != "" && Integer.valueOf(boardCopy[i][k].getText()) > max)
                    max = Integer.valueOf(boardCopy[i][k].getText());
            }
        }   	
        return max;
    }
    
    // Setter and Getter for score and highScore.
    public void setScore (int score) {this.score = score; return;}
    public int getScore () {return this.score;}
    
    public void sethighscore (int score) {this.highScore = score; return;}
    public int gethighscore () {return this.highScore;}
}

