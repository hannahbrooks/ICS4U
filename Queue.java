package assignment6;
/***************************************************************************************************
 * Name: Hannah Brooks 		Date: January 5, 2018
 *
 * BasicQueueTest Class that tests the use of BasicQueue class.
 *
 * methods:
 * menu - To show the options to the user.
 * main - Manipulate the queue.
 *
 **************************************************************************************************/

public class BasicQueueTest {
    
    public static void main(String[] args) {
        int option;  // To store the user's choice.
        char answer;  // Store the answer for whether or not the user wants to continue.
        BasicQueue bq = new BasicQueue();  // Create an instance of BasicQueue Class.
        
        do {
            display_menu();  // Show the menu first.
            option = Keyboard.getInteger();  // Let the user choose an option to continue.
            
            switch(option) {
                case 1: // Enqueue.
                    if (bq.isFull())
                        System.out.println("Queue is full!");
                    else {
                        System.out.println("Enter name for cup:\t");
                        String name = Keyboard.getString();
                        System.out.println("\nEnter drink order:\t");
                        String order = Keyboard.getString();
                        
                        bq.enqueue(name, order);
                    }
                    break;
                    
                case 2: // Dequeue.
                    if (bq.isEmpty())  // calling empty method in BasicQueue to check if the queue has no element
                        System.out.println("Queue is empty.");
                    else {
                        Orders dequeuedOrder = bq.dequeue();  // return the top element
                        System.out.println("'" + (Orders)dequeuedOrder + "' was dequeued.\n");
                    }
                    break;
                    
                case 3: // Show front.
                    if (bq.isEmpty())  // Calling empty method in BasicQueue to check if the queue has elements.
                        System.out.println("Queue is empty.");
                    else{
                        // Show the top element in the queue.
                        Orders front = bq.showFront();  //
                        System.out.println("Element '" + front + "' is at the front of the queue.\n");
                    }
                    break;
                    
                case 4: // Size of queue;
                    System.out.println("There are " + bq.size() + "customers in line.");
                    break;
                    
                case 5: // Empty.
                    if (bq.isEmpty())
                        System.out.println("Queue is empty!");
                    else
                        System.out.println("Queue is not empty!");
                    break;
                    
                case 6: // Full.
                    if (bq.isFull())
                        System.out.println("Queue is full!");
                    else
                        System.out.println("Queue is not full!");
                    break;
                    
                case 7: // Show queue;
                    System.out.println(bq);
                case 8: // Remove.
                    System.out.println("Enter the name and order of the spot you want to cancel:\nName:\t");
                    String name = Keyboard.getString();
                    System.out.println("\nOrder:");
                    String order = Keyboard.getString();
                    System.out.println("There was(were) " + bq.remove(name, order) + " orders matching that name and order.\n\n");
                    break;
                    
                default:
                    System.out.println("You've entered an invalid number!");
            }
            
            System.out.println("Do you want to continue? (Y/N)");
            answer = Keyboard.getCharacter();
            
            // If they want to quit.
            if ((answer == 'N')||(answer == 'n'))
                System.out.println("CYA.");
            
        } while ((answer == 'Y')||(answer == 'y'));
    }
    
    // Display the menu.
    public static void display_menu(){
        System.out.println("-----Menu----");
        System.out.println("1. Enqueue");
        System.out.println("2. Dequeue");
        System.out.println("3. Show Front");
        System.out.println("4. Length of queue");
        System.out.println("5. Check if the line is empty");
        System.out.println("6. Check if the line is full");
        System.out.println("7. Show queue");
        System.out.println("8. Cancel an order");
        
        return;
    }
}





package assignment6;

/********************************************************************************************************
 * Name: Hannah Brooks		Date: January 5, 2018
 *
 * BasicStack class contains all the methods used for manipulating the ArrayList (stack) of Characters
 * This class also has a toString method, equals method, and constructors
 *
 * Methods:
 * 	isEmpty - To check if the queue is empty.
 *  isFull - To check is the queue is full.
 *  size - To see how many elements are in the queue.
 *  enqueue - To add an order to back of the queue.
 *  dequeue - To remove an element from the front of the queue.
 * 	remove - To remove any element from the queue.
 * 	displaySameLetters - display the letters' offset
 * 	toString - Return a string with information of the queue.
 *
 ********************************************************************************************************/

public class BasicQueue {
    
    // Private fields.
    private final int SIZE = 10;
    private static Orders myQueue[];
    private static int front = -1;
    private static int back = -1;
    
    // Default constructor.
    public BasicQueue(){
        myQueue = new Orders[SIZE];
    }
    
    // Overloaded constructor.
    public BasicQueue(int queueSize){  // The queueSize is passed in by the user.
        myQueue = new Orders[queueSize];
    }
    
    // To enqueue an order.
    public Orders enqueue(String name, String order) {
        
        // Check if the queue is empty.
        if (isEmpty())
            front = 0;
        
        back++; // Make a spot for the next element.
        
        Orders enqueuedOrder = new Orders(name, order); // Instantiate the new element.
        
        myQueue[back] = enqueuedOrder; // Insert the order.
        return enqueuedOrder; // Return the inserted order.
    }
    
    // To dequeue an order.
    public Orders dequeue () {
        if (front == back) { // If there is only one order in the queue.
            int temp = front;
            // Make both front and back outside of the queue boundaries.
            front = -1;
            back = -1;
            return myQueue[temp]; // Return the dequeued order.
        }
        else
            front++; // Get rid of the front spot
        
        return myQueue[front-1]; // Return the dequeued order.
    }
    
    // Return the front of the queue.
    public Orders showFront() {
        return (myQueue[front]);
    }
    
    // Return how long the queue is.
    public int size() {
        return (back - front + 1);
    }
    
    // To check if the queue is empty.
    public boolean isEmpty() {
        return (front == -1 && back == -1);
    }
    
    // To check if the queue is full.
    public boolean isFull () {
        return (front==0 && back == SIZE-1);
    }
    
    // To remove an order from the queue.
    public int remove (String name, String order) {
        int count = 0; // Initialize the number of equal objects as 0.
        Orders isTheSame = new Orders(name, order); // Instantiate the order to be compared.
        
        if (front == back) { // Easy removal for when there is only one element.
            // Move front and back outside of the queue boundaries.
            front = -1;
            back = -1;
        }
        else {
            for(int i = front; i <= back; i++) { // To traverse the queue.
                if (myQueue[i].equals(isTheSame)) { // To check if the two objects are the same.
                    count++; // For each time an object that is the same is found.
                    
                    for(int j = i; j < back; j++) // Move each object one place up.
                        myQueue[j] = myQueue[j + 1];
                }
            }
        }
        back--; // Move the back up one spot.
        
        return count; // Return how many of the same objects there were.
    }
    
    
    // To show the queue.
    public String toString(){
        String content = "There is(are) " + size() + " element(s) in the queue.\n";
        for (int i = front, j = 0; i < back+1; i++, j++)
            content += (j+1) + ". " + myQueue[i].getName() + ", " + myQueue[i].getOrder() + "\n";  // the letters are displayed backwards
        return content;
    }
}





package assignment6;

/*************************************************************************************************
 * Name: Hannah Brooks			Date: January 5, 2018
 *
 * This class includes all the constructor, getter and setter methods for all private fields.
 * It also has a toString method used to return the information of the queue. It also has a
 * equals method to determine the equality of two objects.
 *
 *************************************************************************************************/

public class Orders {
    
    private String name;
    private String order;
    
    public Orders () {
        name = "";
        order = "";
    }
    
    // Overloaded constructor.
    public Orders (String name, String order) {this.name = name; this.order = order;}
    
    // Setter and getter for member name.
    public String getName() {return name;}
    
    public void setName(String name) {this.name = name;}
    
    // Setter and getter for member order.
    public String getOrder() {return order;}
    
    public void setOrder(String order) {this.order = order;}
    
    // ToString.
    public String toString() {return (name + ", " + order);}
    
    // Equals method.
    public boolean equals(Orders personsOrder) {
        
        if (this.getClass() != personsOrder.getClass()) // Check if the names are members of the same class.
            return false;
        
        if (this.name.equals(personsOrder.getName()) && this.order.equals(personsOrder.getOrder())) // Check if they are equal.
            return true;
        return false;
        
    }
}





package assignment6;

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


