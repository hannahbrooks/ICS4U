package assignment3;
import java.io.*;

public class InventoryTester {
	
	// allocate 10 places in memory for the inventory for first half of test
 	public static InventoryFile[] myInventory = new InventoryFile[10];
 	
 	// allocate a second InventoryFile for the object portion of the test
 	public static InventoryFile[] myStuff = null;

	public static void main (String args[]){
		
		// define the File to open	
      	File currFile = new File("Assign3Inv.txt");
      	
      	/***********************************************************/ 	
		// open and read the original text file and instantiate
      	// the objects for the InventoryFile class
      	/**********************************************************/
		
		int count =	FileIO.fileReadMethod (currFile);
		
				
		// add another employee.
		myInventory[count]= new InventoryFile();
		
		myInventory[count].setItem("Snickers Bar");
		myInventory[count].setDescription("Candy Bar");
		myInventory[count].setDistributer("Harveys Candy distributing");
		myInventory[count].setTimeToDeliver("every Tuesday");
		myInventory[count].setInventoryNo(2356);
		myInventory[count].setNumberLeft(24);  // would have been better as InStock - oh well)
		myInventory[count].setWholeSaleCost(0.56f);
		myInventory[count].setPurchaseCost(1.45f);
		
		count++;
		
		//print out all of the Inventory
		/* for(int i=0; i<count;i++){
			System.out.println(myInventory[i]);
		}
		
		/*************************************************************/
		// write the object out as a text file with new addition
		/*************************************************************/
		File backFile = new File("invBackUp.txt");
		
		FileIO.writeFileMethod(backFile, count);
		
		
		/*************************************************************/
		// export file as a class file.
		/*************************************************************/
	File newFile = new File("Inventory.dat");
		
	FileIO.writeObjectMethod(newFile);
		
		
		/*************************************************************/
		// import the file as a class
		// to prove you have written the file,  load and then show
		/************************************************************/
		
		// a new Inventory File to load and prove the object was saved
		//InventoryFile[] myStuff = null; //(defined at beginning of file)
		
		FileIO.objectInputMethod(newFile);  
		
		//Final check of your results.
		
		System.out.println("There are "+ count +" items in the inventory.\n" + "The inventory is: ");
		
		for(int i=0; i<count; i++){
			System.out.print((i+1)+":\n");
			System.out.print(myStuff[i]);
			System.out.println();
		}

		
	}
}

/*
 * Name: Hannah Brooks
 * Date: October 27, 2017-10-27
 *
 * Purpose: Declare and initialize the variables as well as provide access to private variables and modify them.
 *
 * Functions:
 * 		ToString - returns text representation of the variables.
 * 		Setters - sets private variables
 * 		Getters - returns private variables.
 */

package assignment3;
import java.io.Serializable;
import java.util.StringTokenizer;

// Make the yarn! Serializable turns the object into bytes so it can be saved easily to storage.
public class InventoryFile implements java.io.Serializable {

	// Instance variables.
	private String item, description, distributer, timeToDeliver;
	private int inventoryNo, numberLeft;
	private float wholeSaleCost, purchasePrice;
	
	// Default constructor.
	public InventoryFile () {
		item = "";
		description = "";
		distributer = "";
		timeToDeliver = "";
		inventoryNo = 0;
		numberLeft = 0;
		wholeSaleCost = 0;
		purchasePrice = 0;
	}
	
	// Overloaded constructor.
	public InventoryFile(String line) {
		
		// New tokenizer, make the delimeter and the words variable.
		StringTokenizer words = new StringTokenizer(line,",");
		
		// While the line has more words.
		while(words.hasMoreTokens()) {
			
			// Tokenize the string, fill each variable according to how soon they appear in the line.
			this.item = words.nextToken();
			this.description = words.nextToken();
			this.distributer = words.nextToken();
			this.timeToDeliver = words.nextToken();
			this.inventoryNo = Integer.parseInt(words.nextToken());
			this.numberLeft = Integer.parseInt(words.nextToken());
			this.wholeSaleCost = Float.parseFloat(words.nextToken());
			this.purchasePrice = Float.parseFloat(words.nextToken());
		}
	}
	
	// Setters.
	public void setItem (String item) {
		this.item = item;
	}
	public void setDescription (String description) {
		this.description = description;
	}
	public void setDistributer (String distributer) {
		this.distributer = distributer;
	}
	public void setTimeToDeliver (String timeToDeliver) {
		this.timeToDeliver = timeToDeliver;
	}
	public void setInventoryNo (int inventoryNo) {
		this.inventoryNo = inventoryNo;
	}
	public void setNumberLeft (int numberLeft) {
		this.numberLeft = numberLeft;
	}
	public void setWholeSaleCost (float wholeSaleCost) {
		this.wholeSaleCost = wholeSaleCost;
	}
	public void setPurchaseCost (float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	// Getters.
	public String getItem () {
		return item;
	}
	public String getDescription () {
		return description;
	}
	public String getDistributer () {
		return distributer;
	}
	public String getTimeToDeliver () {
		return timeToDeliver;
	}
	public int getInventoryNo () {
		 return inventoryNo;
	}
	public int getNumberLeft () {
		return numberLeft;
	}
	public float getWholeSaleCost () {
		return wholeSaleCost;
	}
	public float getPurchaseCost () {
		return purchasePrice;
	}
	
	
	// ToString.
	public String toString () {
		return this.item + ", " + this.description + ", " + this.distributer + ", " + this.timeToDeliver + ", " + this.inventoryNo + ", " + this.numberLeft + ", " + this.wholeSaleCost + ", " +  this.purchasePrice + "\n";
	}

}


/*
 * Name: Hannah Brooks
 * Date: October 27, 2017-10-27
 * 
 * Purpose: Read in information from a file and then write it to a backup file. Following that, write the information to a new file and subsequently read from that.
 *
 * Functions:
 * 		fileReadMethod - uses FileReader class to read the initial Assign3.txt file provided
 * 		writeFileMethod - uses FileWriter class to write the new list to a backup file called invBackUp.txt
 * 		writeObjectMethod - uses the ObjectOutputStream to save the object in a file called employees.dat
 * 		objectInputMethod - uses the ObjectInputStream to read the object from the inventory.dat file.
 */

package assignment3;
import java.io.*;
import java.io.BufferedReader;

public class FileIO {
	
	
	static int fileReadMethod (File myFile) {
		
		int count = 0;
		try
		{
			// New BufferedReader created.
			BufferedReader read = new BufferedReader(new FileReader(myFile));
			// Make my string.
			String line;
			
			// Reading the whole file and printing to console.
			while((line = read.readLine()) !=null) {
				// Taking what line has and putting it in the index of myInventory according to count.
				InventoryTester.myInventory[count] = new InventoryFile(line);
				count++;
			}
				
			read.close(); //Closing the input stream.
			
		} catch(FileNotFoundException e)	{
			//Error when opening a file has failed.
			System.out.println("Error: Cannot open file for reading.");
			
		} 	catch(EOFException e) {
			//Error if attempting to read more data than exists in the file.
			System.out.println("Error: EOF encountered, file may be corrupted.");
			
		} catch(IOException e) {
			//Error when i/o operation has failed to execute.
			System.out.println("Error: Cannot read from file.");
		}
		
		System.out.println("\n");
		
		return count; // Give count back to InventoryTester to keep adding inventory.
		
	}
	static void writeFileMethod(File myFile, int counter) {
		
		try{
		
		// If my file isn't already made, create it.
		if (!myFile.exists()) {
			myFile.createNewFile();
		}
		 
		// New PrintWriter for FileWriter for myFile (which will be passed from InventoryTester).
		 PrintWriter out1 = new PrintWriter(new FileWriter(myFile)); 
		
		 // Print each line from myInventory to out1 for myFile.
         for (int count1 = 0; count1 < counter; count1++ ) {
        	 	out1.println(InventoryTester.myInventory[count1]);
		 }
            
        out1.close(); // Close PrintWriter stream.
            
        } catch (FileNotFoundException e) { 
            System.out.println("Error: Cannot open file for writing."); 
        } catch (IOException e) { 
            System.out.println("Error: Cannot write to file."); 
        } 
	}
	
	static void writeObjectMethod(File myFile) {
		
		//Creating file bankData and an Account object and reference variable for streams.
				//myFile = new File("inventory.dat");
				ObjectOutputStream outToInv;
				// InventoryFile inventory = new InventoryFile();
				
				//Write object to bankData.
				try
				{
					//Making output stream write objects to bankData (myFile). 
					outToInv = new ObjectOutputStream(new FileOutputStream(myFile));
				
					//Getting the account object written to bankData.
					outToInv.writeObject(InventoryTester.myInventory); 
					
					//Flush buffered data of object to file and then closing the output stream.
					outToInv.flush();
					outToInv.close();
					
				} catch(FileNotFoundException e) {
					//Error when opening a file has failed.
					System.out.println("Error: Cannot open file for writing.");
					
				} catch(IOException e) {
					//Error when i/o operation has failed to execute.
					System.out.println("Error: Cannot write to file");
					
				}
		
	}
	static void objectInputMethod (File myFile) {
		
		
		// Creates inFromInv as an ObjectInputStream.
		ObjectInputStream inFromInv;
		
		/* File inventoryData = new File(myFile);
		InventoryFile item = new InventoryFile();*/
		
		//Reading object from bankData.
		try
		{
			//Making input stream read objects from bankData.
			inFromInv = new ObjectInputStream( new FileInputStream(myFile));
			
			//Reading the object's data from the file and printing it to the console. 
			InventoryTester.myStuff=(InventoryFile[])inFromInv.readObject(); 
			// System.out.println(item);
			
			inFromInv.close(); //Closing the input stream.
			
		} catch(FileNotFoundException e) {
			//Error when opening a file has failed.
			System.out.println("Error: Cannot open file for reading.");
			
		} catch(EOFException e) {
			//Error if attempting to read more data than exists in the file.
			System.out.println("Error: EOF encountered, file may be corrupted.");
			
		} catch(IOException e) {
			//Error when i/o operation has failed to execute.
			System.out.println("Error: Cannot read from file.");
			
		} catch(ClassNotFoundException e) {
			//Error when the class of the serialized object cannot be found.
			System.out.println("Error: Class of serialized object cannot be found.");
		}
	}
}
