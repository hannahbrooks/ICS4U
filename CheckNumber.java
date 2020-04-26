package Assignment1;

public class CheckNumber {

	int num, n;
	
	public CheckNumber(int num1) {
		num = num1; // Make num accessible.
	}

	boolean isPositive() { 
		
		if (num > 0) // If input is greater than zero.
			return true;
		else // If input is less than zero.
			return false;
	}

	boolean isPrime() {
		n = num;
		
		if (n < 0) // If the number is negative, make it positive.
			n = n * -1;
		
		for (int i = 2; i < n; i++) {
			if (n % i == 0) // If input divisible by incremental numbers aka 2, 3 and so on.
				return false;
		}
		return true; // If input is indivisible by numbers up to the input.
	}

	boolean isPalindrome() {

		int rev = 0, rmd;
		n = num;
		
		if (n < 0) // Change the number to positive.
			n = n * -1;

		while (n > 0) { // To keep number greater than zero.
			rmd = n % 10; // To get the units place aka remainder.
			rev = rev * 10 + rmd; // Adds new units to end of rev number.
			n = n / 10; // Get the tens slot in the units slot.
		}
		if (rev == num || rev == (-1 * num)) // Check to see if the numbers match.
			return true;
		return false;
	}

	int numberOfDigits() {
		
		int a = 0; // Setting counter
		
		n = num;
		
		if (n < 0) // If the number is negative, make it positive.
			n = n * - 1;
		
		while (n > 0) {
			n = n/10; // Divide to get the tens to the units aka shift the number's digits along.
			a++; // Add one each time there is a place holder.
		}
		return a;
	}

	void displayNumberCharacteristics() { // Print all characteristics.
		System.out.println("The characteristics of " + num + " are:\n" +"Positive?: " + isPositive());
		System.out.println("Palindrome?: " + isPalindrome());
		System.out.println("Prime?: " + isPrime());
		System.out.println("Number of digits?: " + numberOfDigits());
	}
}

package Assignment1;

/* Name: Hannah Brooks
 * Date: 20-09-17
 * 
 * Purpose: Java program to determine number characteristics. These characteristics include whether or
 * not the number is positive, whether or not it is a palindrome, whether or not it is prime and the 
 * number of digits in the number.
 * 
 * Function:
 * isPositive(); - determines if the number is positive, returns boolean
 * isPalindrome(); - determines if the number is spelled the same way backwards as forwards, returns
 * boolean
 * isPrime(); - if the number has factors other than 1 and itself, returns boolean
 * numberOfDigits(); - finds number of place holders, returns integer
 */

public class CheckNumberTest {

	public static void main(String[] args) {
		int num;
		System.out.println("Enter a number!"); // Prompt for user input
		num = Keyboard.getInteger();
		while (num == 0) { // To make sure number isn't zero.
			System.out.println("Please enter a non-zero number:");
			num = Keyboard.getInteger();
		}
		CheckNumber number = new CheckNumber (num); // 
		number.displayNumberCharacteristics();
	}
}
