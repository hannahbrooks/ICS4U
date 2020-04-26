package assignment4;
import java.util.Scanner;
import java.io.*;

/***************************************************************************************************
 * Name: Hannah Brooks		Date: January 5, 2018
 * 
 * This class tests the use of DisplayScore and GameEntry classes.
 * 
 **************************************************************************************************/

public class GameInterface {
    static Scanner input = new Scanner (System.in);  // To get their choice.
    
    public static void main (String[] args) {
        DisplayScore scores = new DisplayScore(25);  // Making an instance for DisplayScore.
        File currFile = new File("MinesweeperScores.txt");  // Getting the file.
        DisplayScore.fileReadMethod(currFile);  // Read the file
        char answer = 0;  // The flag to quit.
        
        do {
            int option = menu();  // Give them the menu.
            
            switch (option) {
                case 1:
                    // Add a score.
                		GameEntry tempStore = scores.addScore();
                    DisplayScore.add(tempStore);
                    System.out.println(scores);
                    break;
                    
                case 2:
                    // Remove a score.
                    scores.remove();
                    break;
                    
                case 3:
                    // Sort the array.
                    System.out.println("Please choose a method for sorting:\n1) sort by score\n2) sort by name\n3) sort by level");
                    int choice = Keyboard.getInteger();  // Ask how they want to sort it.
                    
                    if (choice == 1) {
                        scores.sortByScore();
                        System.out.println(scores);
                    }
                    else if (choice == 2) {
                        scores.sortByName();
                        System.out.println(scores);
                    }
                    else if (choice == 3) {
                        scores.sortByLevel();
                        System.out.println(scores);
                    }
                    else
                        System.out.println("You've entered an invalid number!");
                    break;
                    
                case 4:
                    // Display the scores.
                    System.out.println(scores);
                    break;
                    
                default:
                    System.out.println("You've entered an invalid number!");
            }
            
            
            System.out.println("Do you want to continue?(Y/N)");
            answer = input.next().charAt(0);
            
            if ((answer == 'N')||(answer == 'n'))
                System.out.println("Byeeeee.");
            
        } while ((answer == 'y')||(answer == 'Y'));
    }
	
	public static int menu () {
		// Menu for the operations.
		System.out.println("\t\tMenu");
  		System.out.println("-------------------------------------\n");
        System.out.println("Please choose an option from below (enter the coresponding number 1-4):");
        System.out.println("1) Add an additional entry\n2) remove an entry by name\n3) sort the array of scores"
                           + "\n4) print out the scores");
        int choice = Keyboard.getInteger();        
        return choice;
    }
	
}






package assignment4;
import java.io.*;
import java.util.*;

/********************************************************************************************************
 * Name: Hannah Brooks			Date: January 5, 2018
 *
 * GameInterface class contains all the methods used for manipulating the array of scores.
 * This class also has a toString method and constructors.
 *
 * Methods:
 * 	fileReadMethod - Uses FileReader class to read the initial MinesweeperScores.txt file.
 * 	add - Add a score from the file to the array based on insertion sort.
 * 	addScore - Allow the user to add a score (input information of the player).
 * 	remove - Find and remove any score based on their name.
 * 	sortByName - Sort the array in alphabetical order based on bubble sort from their names.
 * 	sortByScore - Sort the array in order from lowest score to highest score based on selection sort.
 *  sortByLevel - Sort the array in order from lowest to highest within their level.
 * 	toString - Return a string with information of the scores.
 *
 ********************************************************************************************************/

public class DisplayScore {
    private static int count;  // Total number of scores.
    public static GameEntry[] minesweeperScores;  // Array to store the scores.
    
    Scanner input = new Scanner(System.in);  // To get input from the user.
    
    // Default constructor.
    public DisplayScore() {
        count = 0;
    }
    
    // Overloaded constructor.
    public DisplayScore(int LENGTH) {
        minesweeperScores = new GameEntry[LENGTH];  // Initialize array size.
        count = 0;
    }
    
    public static void fileReadMethod(File file) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line;  // Each score will be stored here.
            
            while ((line = read.readLine()) != null) {
                add(new GameEntry(line));  // Calling add method to add scores into the array in order of score.
            }
            
            read.close();
        } catch (FileNotFoundException e) {
            // Error when opening a file has failed.
            System.out.println("Error: Cannot open file for reading.");
        } catch (EOFException e) {
            // Error if attempting to read more data than exists in the file.
            System.out.println("Error: EOF encountered, file may be corrupted.");
        } catch (IOException e) {
            // Error when i/o operation has failed to execute
            System.out.println("Error: Cannot read from file.");
        }
        return;
    }
    
    // Add the score from the file to the array based on insertion sort.
    public static void add(GameEntry newEntry) {
        if (count == 0)
            minesweeperScores[count] = newEntry;
        else if (count == 24) {
            if (newEntry.getScore() > minesweeperScores[23].getScore())
                return;
            else
                minesweeperScores[23] = null;
            
        }
        // Compare the score of the new player to the level of the score already inside the array.
        int i = 0;
        while ((i < count) && (minesweeperScores[count] == null)) {
            if (minesweeperScores[i].getScore() > newEntry.getScore()) {
                int p = i;  // Store the position when the spot is.
                
                // Move every element after position i one down.
                for (int j = count; j > p; j--) {
                    minesweeperScores[j] = minesweeperScores[j-1];
                }
                minesweeperScores[p] = newEntry; // Put it in the right spot.
                i = count; // Break out of the loop.
            }
            i++;  // If the right spot is not found.
        }
        // Check if no suitable position is found, place the new score right after the last element inside the array.
        if (minesweeperScores[count] == null)
            minesweeperScores[count] = newEntry;
        
        count++;  // One more score on the list.
        
        return;
    }
    
    // To add a new score.
    public GameEntry addScore() {
        GameEntry anotherEntry = new GameEntry();  // Store the information of the added score.
        
        System.out.println("Please enter the player's information:");
        
        // Get information from the user.
        System.out.println("Name:");
        anotherEntry.setName(Keyboard.getString().trim());
        System.out.println("Score:");
        anotherEntry.setScore(Keyboard.getInteger());
        System.out.println("Level:");
        anotherEntry.setLevel(Keyboard.getString().trim());
        
        return anotherEntry;  // Return the entry with filled information.
    }
    
    // Remove a score based on the player's name.
    public void remove() {
        String playerName;
        System.out.println("Please enter the player's name of the score you wish to remove:");
        playerName = Keyboard.getString().trim();
        
        int i = 1;  // Used to traverse the array.
        int deleted = 0;  // Count the number of deleted scores.
        
        // Go through the array to find the one to be deleted.
        while (i < count) {
            if (minesweeperScores[i].getName().trim().compareTo(playerName) == 0) {
                // Delete the score.
                for (int j = i; j < count; j++) {
                    if (j+1 < count)
                        minesweeperScores[j] = minesweeperScores[j+1];  // Remove the element by moving each element up.
                    else
                        minesweeperScores[j] = null; // Last on the list is null.
                }
                deleted++;
                count--;  // One less score on the list.
            }
            i++;  // Keep going through.
        }
        if (deleted == 0)  // If the name wasn't found.
            System.out.println("No player is found with such name, sorry!");
        else
            System.out.println(deleted + " player(s) named " + playerName + " have been removed from the list.");
        
        return;
    }
    
    // Sort the array by score based on selection sort (in alphabetical order).
    public void sortByScore() {
        for (int i = 0; i < count; i++) {
            int min = i;  // Smallest value of the array.
            GameEntry temp;  // Used for swapping.
            
            // Find the smallest value.
            for (int j = i+1; j < count; j++) {
                if (minesweeperScores[i].getScore() > minesweeperScores[j].getScore())
                    min = j;  // Store the position of the smallest value.
            }
            
            // To check in case the smallest value is the same as the element we are comparing to, no swapping is needed.
            if (minesweeperScores[min].getScore() > minesweeperScores[i].getScore()) {
                // swap
                temp = minesweeperScores[i];
                minesweeperScores[i] = minesweeperScores[min];
                minesweeperScores[min] = temp;
            }
        }
        return;
    }
    
    // Sort the array by name based on bubble sort by name.
    public void sortByName() {
        for (int i = 0; i < count; i++) {
            GameEntry temp;  // For swapping.
            
            // Comparison for every two elements.
            for (int j = 0; j < count-1; j++) {
                // Swap if the previous name belongs in front of the current name.
                if (minesweeperScores[j].getName().compareToIgnoreCase(minesweeperScores[j+1].getName()) > 0) {
                    temp = minesweeperScores[j];
                    minesweeperScores[j] = minesweeperScores[j+1];
                    minesweeperScores[j+1] = temp;
                }
            }
        }
        return;
    }
    
    // Sort the array by level based on insertion sort.
    public void sortByLevel() {
        
        for (int i=1; i< count; ++i) {
            int j = i-1;
            
            // If the current element has the same level and is less than the one above it.
            while (j>=0 && minesweeperScores[j].getScore() > minesweeperScores[i].getScore() && minesweeperScores[j].getLevel() == minesweeperScores[i].getLevel()) {
                minesweeperScores[j+1] = minesweeperScores[j]; // Swap them
                j--;
            }
            minesweeperScores[j+1] = minesweeperScores[i]; // Fill the one that was just switched.
        }
    }
    // ToString().
    public String toString() {
        System.out.println("\nThere are " + count + " scores in total.\n");
        String s = "";
        for (int i = 0; i < count; i++) {
            if (i > 0)
                s += "\n\n";
            s += minesweeperScores[i];  // Calling the other toString().
        }
        return s;
    }
}






package assignment4;
import java.util.StringTokenizer;

/*************************************************************************************************
 * Name: Hannah Brooks			Date: January 5, 2018
 *
 * This class includes all the constructor, getter and setter methods for all private fields.
 * It also has a toString method used to return the information of each score.
 *
 *************************************************************************************************/

public class GameEntry {
    // Declaring members of GameEntry.
    String name;
    int score;
    String level;
    
    // Default constructor
    public GameEntry () {
        
        name = "";
        score = 0;
        level = "Beginner";
    }
    
    // Overloaded constructor.
    public GameEntry (String line) {
        StringTokenizer words = new StringTokenizer(line,",");
        
        while(words.hasMoreTokens()) {
            this.name = words.nextToken().trim();
            this.score = Integer.parseInt(words.nextToken().trim());
            this.level = words.nextToken().trim();
        }
    }
    
    // Getter and setter for member name.
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    // Getter and setter for member score.
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    // Getter and setter for member level.
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    // ToString().
    public String toString() {
        return "Name: " + name + ", Score: " + score + ", Level: " + level;
    }
}






package assignment4;

import java.io.*; //tell the java compiler that we'll be doing 	i/o

public class Keyboard {
    
    private static BufferedReader inputStream = new BufferedReader(new 	InputStreamReader(System.in));
    
    /* Get an integer from the user and return it */
    public static int getInteger() {
        try {
            return(Integer.valueOf(inputStream.readLine().trim()).intValue());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /* Get a double from the user and return it */
    public static double getDouble() {
        try {
            return(Double.valueOf(inputStream.readLine().trim()).doubleValue());
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
    
    /* Get a float from the user and return it */
    public static float getFloat() {
        try {
            return(Float.valueOf(inputStream.readLine().trim()).floatValue());
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }
    
    /* Get a string of text from the user and return it */
    public static String getString() {
        try {
            return inputStream.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    /* Get a char from the user and return it */
    public static char getCharacter() {
        try {
            String in = inputStream.readLine().trim();
            if (in.length() == 0)
                return (char)0;
            else
                return (in.charAt(0));
        } catch (Exception e) {
            e.printStackTrace();
            return (char)0;
        }
    }
    
    /* Get a boolean from the user and return it */
    public static boolean getBoolean() {
        try {
            return(Boolean.valueOf(inputStream.readLine().trim()).booleanValue());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}

