package linkedList;
/*************************************************************************************************************
 * Name: Hannah Brooks		Date: January 29, 2017
 *
 * LinkedListTest Class
 *
 * Purpose: Contains the main method which tests the functionality of LinkedScores.Java and GameEntry.java.
 *
 *************************************************************************************************************/
public class LinkedListTest {
    
    public static void main(String args[]){
        
        LinkedScores lScore = new LinkedScores();  // Initialize Scores object.
        
        // Add objects.
        lScore.add(new GameEntry("Mike",1105));
        lScore.add(new GameEntry("Paul",720));
        lScore.add(new GameEntry("Rose",590));
        lScore.add(new GameEntry("Jack",510));
        lScore.add(new GameEntry("Anna",660));
        lScore.add(new GameEntry("Rob",750));
        
        // Output number of entries.
        System.out.println(lScore.getNumEntries() + " entries in total\n");
        
        // Output top 5 entries.
        System.out.println(lScore);
        
        lScore.add(new GameEntry("Jill",720));
        System.out.println(lScore);
        
        GameEntry removedEntry = lScore.remove("Paul");  // Remove an entry.
        
        if (removedEntry == null)
            System.out.println("Entry is not found! Can't be removed\n");
        else
            System.out.println(removedEntry + " is found and removed\n");
        
        System.out.println(lScore);
        
        lScore.add(new GameEntry("Phil",870));
        lScore.add(new GameEntry("Don",470));
        lScore.add(new GameEntry("Donna",630));
        lScore.add(new GameEntry("Erin",420));
        lScore.add(new GameEntry("George",500));
        
        // Capture invalid arguments using try...catch block.
        try{
            lScore.add(new GameEntry(6,"Tom"));
        }catch(Exception e){
            System.out.println(e);
        }
        
        System.out.println(lScore.getNumEntries()+ " entries in total\n");
        System.out.println(lScore);
    }
}



package linkedList;
/****************************************************************************
 * Name: Hannah Brooks		Date: January 29, 2017
 *
 * LinkedScores Class
 *
 * Purpose: This class implements a single linked list of the GameEntry nodes.
 *
 * Methods:
 * LinkedScores() - Constructor.
 * add(GameEntry) - Void.
 * findLast() - Returns GameEntry.
 * addFirst(GameEntry) - Void.
 * addLast(GameEntry) - Void.
 * remove(String) - Returns GameEntry.
 * hasNext(GameEntry) - Returns boolean.
 * toString() - Returns string.
 * getNumEntries() - Returns int.
 * getMaxNumEntries() - Return int.
 *
 ****************************************************************************/
public class LinkedScores {
    
    private GameEntry head;  // Head node of the list.
    private int size;  // Number of nodes in the list.
    private final int maxNumEntries = 10;
    
    // Default constructor that creates an empty list.
    public LinkedScores(){
        head = null;
        size = 0;
    }
    
    // Add a game entry node.
    public void add(GameEntry entry){
        
        if (size == getMaxNumEntries())
            System.out.println("You are allowed to add a maximum number of 10 entries, can't add more (maybe remove one :))\n");
        else{
            // Check if the list is empty or if the score of the new entry is greater than the score of the first entry in the list.
            if ((size == 0)||((size != 0)&&(entry.getScore() > head.getScore())))
                addFirst(entry);  // add the entry to the beginning of the list
            else{
                // Compare the score of new entry to the last entry which hold the lowest score.
                if (entry.getScore() < (findLast().getScore()))
                    addLast(entry);
                else{
                    // Insert the new entry properly according to its score in the middle of the list.
                    GameEntry temp = head;
                    
                    while (entry.getScore() < temp.getNext().getScore())
                        temp = temp.getNext();
                    entry.setNext(temp.getNext());
                    temp.setNext(entry);
                }
            }
            size++;  // Increase the size of the list.
        }
    }
    
    // Find the last node in the list.
    public GameEntry findLast(){
        GameEntry temp = head;
        
        // Traverse the list to find the last node.
        while (hasNext(temp))
            temp = temp.getNext();
        
        return temp;
    }
    
    // Add the new entry to the beginning of the list.
    public void addFirst(GameEntry entry){
        entry.setNext(head);
        head = entry;
        
        return;
    }
    
    // Add the new entry to the end of the list.
    public void addLast(GameEntry entry){
        GameEntry tail;  // Store the last entry.
        
        tail = findLast();  // Find the last entry to be stored in tail.
        
        // Let the new entry 'point' to null.
        entry.setNext(null);
        
        // Then, make the second last entry's next node 'point' to the new entry.
        tail.setNext(entry);
        
        tail = entry;  // The new entry is now the tail.
        
        return;
    }
    
    // Remove a game entry based on the player's name.
    public GameEntry remove(String name){
        
        GameEntry removedEntry;
        GameEntry temp = head;
        
        //  Check if the list is empty.
        if(getNumEntries() == 0)
            removedEntry = null;
        else{
            // Entry is found at the beginning of the list.
            if (temp.getName() == name){
                removedEntry = temp;
                head = head.getNext();
                size--;
            }
            // Entry is not found at the beginning of the list.
            else if (head.getName() != name){
                // Find the entry that needs to be removed in the rest of the list.
                while (temp.getNext().getName() != name)
                    temp = temp.getNext();
                
                removedEntry = temp.getNext();  // Store the removed entry.
                
                if(temp.getNext().getNext() == null)  // Entry is found at the end of the list.
                    temp.setNext(null);
                else  // Entry is found in the middle of the list.
                    temp.setNext(temp.getNext().getNext());
                
                size--;  // Decrease the list size.
            }
            // Entry is not found.
            else
                removedEntry = null;
        }
        
        return removedEntry;
    }
    
    // Check if there is more entry.
    public boolean hasNext(GameEntry head){
        if (head.getNext() != null)
            return true;
        else
            return false;
    }
    
    // Display the top 5 GameEntries.
    public String toString(){
        String s = "The top 5 game entries are:\n";
        int count = 0;  // Only show the top five entries.
        GameEntry temp = head;
        
        while ((count < 5) && (temp.getNext() != null)){
            s += temp + "\n";
            temp = temp.getNext();  // Move temp to the next node.
            count++; 
        }
        // If count is still less then five, it means '(temp.getNext() == null)', then print out the last entry on the list.
        if (count < 5)
            s += temp;  // Output the last Game Entry on the list.
        
        return s;
    }
    
    // Return the number of game entries in the list.
    public int getNumEntries(){
        return size;
    }
    
    // Return the maximum number of entries.
    public int getMaxNumEntries() {
        return maxNumEntries;
    }
}



package linkedList;
/********************************************************************************
 * Name: Hannah Brooks		Date: January 29, 2017
 *
 * GameEntry Class
 *
 * Purpose: This class stores the statistics of the play scores and serves as the
 * 'node' class for the Linked List class.
 *
 * Methods:
 * GameEntry(String, int) - Overloaded constructor.
 * GameEntry(Object, Object) - Overloaded constructor used to deal with exception.
 * setScore(int) - Void.
 * setName(String) - Void.
 * setNext(GameEntry) - Void.
 * getScore() - Returns int.
 * getName() - Returns string.
 * getNext() - Returns GameEntry.
 * toString() - Rturns string.
 *
 ********************************************************************************/
public class GameEntry {
    
    // Private fields.
    private int score;
    private String name;
    private GameEntry next;
    
    // Overloaded constructor.
    public GameEntry(String s, int i) {
        name = s;
        score = i;
        next = null;
    }
    
    // Dealing with exception.
    public GameEntry(int score, String name) throws Exception{
        throw new Exception("Game entry cannot be added\n");
    }
    
    // Set the element score of this node.
    public void setScore(int score) {
        this.score = score;
    }
    
    // Set the element name of this node.
    public void setName(String name) {
        this.name = name;
    }
    
    // Set the 'next' node of this node.
    public void setNext(GameEntry next) {
        this.next = next;
    }
    
    // Return the score of this node.
    public int getScore(){
        return score;
    }
    
    // Return the name element of this node.
    public String getName() {
        return name;
    }
    
    // Get the 'next' node of this node.
    public GameEntry getNext() {
        return next;
    }
    
    // Returns the player's name and score.
    public String toString(){ 	
        return getName() + "		" + getScore();
    }
}


















