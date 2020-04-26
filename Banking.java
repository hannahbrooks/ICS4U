package assignment2;

/*
 * Name: Hannah Brooks
 * Date: October 4, 2017-10-04
 *
 * Purpose: To hold bank accounts for four clients. Has debit and credit and displays information.
 *
 * Functions:
 * 		credit - adds amount to balance
 * 		debit - withdraws
 * 		getBalance - returns the current balance
 *		setFirstName - edit the first name
 *		setLastName - edit the last name
 *		toString - show account statistics
 *		getName - returns the clients first and last name
 */

public class Account {
	
	// Instance variables.
	double balance; 
	String lastName;
	String firstName;
	
	public Account () { // Constructor.
		balance = 0;
		lastName = "";
		firstName = "Unknown";
	}
	
	public Account (String str, String str2, int i) { // Overloaded constructor.
		// If the person tries to withdraw with nothing in their bank account.
		if (i < 0)
			System.out.println (str2 + " " + str + " has no money available money to withdraw.");
		else
			balance = i;
		
		firstName = str2;
		lastName = str;
	}
		
	void credit (double a){ // To put money into the bank.
		balance = balance + a;
	}
	
	void debit (double b) { // To take money out of the bank.
		if (b > balance) // If they try to take out more money than they have.
			System.out.println("\nAccount needs to be open for longer than 30 days to withdraw money without money. Please contact your local branch.\n");
		else
			balance = balance - b;
	}
		
	double getBalance (){ // Shows you have much money you have.
		return balance;
	}
	
	void setFirstName (String firstName1) { // Change the value of first name variable.
		firstName = firstName1;
	}
	
	void setLastName (String lastName1) { // Change value of last name variable.
		lastName = lastName1;
	}
		
	public String toString () { // Print everything as a string for easy display.
		return "Name: " + firstName + " " + lastName + "\n" + "Balance: " + balance;
	}
	
	String getName () { // Show user the name.
		return (firstName + " " + lastName);
	}
}

package assignment2;

public class AccountTester {

	public static void main(String[] args) {
		// From Mrs. Forrest.
		
		// Create objects.
		 Account client1 = new Account();
		  Account client2 = new Account("Smith", "George", 300);
		  Account client3 = new Account("Thomas", "Sally", -20); 
		  Account client4 = new Account("Jefferson", "Trish", 0);

		  // Print current info of bank account.
		  System.out.println(client1);
		  System.out.println(client2);
		  System.out.println(client3);
		  System.out.println(client4);

		  // Initialize info about bank account client1.
		  client1.setFirstName("Fred");
		  client1.setLastName("Booth");
		  client1.credit(400);

		  // Show name and balance.
		  System.out.println("\n" + client1.getName());
		  System.out.println("\n" + client1.getBalance());

		  // Add money to bank accounts.
		  client2.credit(20);
		  client3.credit(200);
		  client4.credit(40); 

		  // Print client info.
		  System.out.println(client1);
		  System.out.println(client2);
		  System.out.println(client3);
		  System.out.println(client4);

		  // Take money out of bank accounts.
		  client1.debit(60);
		  client2.debit(400);
		  client3.debit(200);
		  client4.debit(20);

		  // Print current info of bank account.
		  System.out.println(client1);
		  System.out.println(client2);
		  System.out.println(client3);
		  System.out.println(client4);
		  
		  // Take money out of bank accounts.
		  client1.credit(15);
		  client2.credit(42);
		  client3.credit(23.70);
		  client4.credit(55);

		  // Print current info of bank account.
		  System.out.println(client1);
		  System.out.println(client2);
		  System.out.println(client3);
		  System.out.println(client4);;
	  }
}
