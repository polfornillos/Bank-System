package BankingSystem;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

public class bank {
	public static void main(String[] args) {
		
		bankAccount account = new bankAccount();
		
		int userchoice=0;
		String name;
		String pass;
		int bal = 0;
		int id = 0;
		
		do {
			Scanner scanner = new Scanner(System.in);  //Create a Scanner object to be used by the whole bank class
			System.out.println("================================");
			System.out.println("     Welcome to AdonisBank");
			System.out.println("================================");
			System.out.println(" ");
			System.out.println("    Please Select A Number");
			System.out.println(" ");
			System.out.println("================================");
			System.out.println("(1) User List");
			System.out.println("(2) Print User Information");
			System.out.println("(3) Create New Account");
			System.out.println("(4) Deposit Money into Account");
			System.out.println("(5) Withdraw Money from Account");
			System.out.println("(6) Setup Automatic Payment");
			System.out.println("(7) Exit");
			System.out.println("================================");
			System.out.print("Enter Choice: ");
			userchoice = scanner.nextInt(); 
			scanner.nextLine();
			System.out.println("================================");
			
			switch (userchoice) {
				
				case 1:
					if(account.checkIfEmpty() == true) { //Checks if the Database is empty
						System.out.println("There are no Available Users!"); 
						break;
					}
					else {
						account.printInfoList(); //Shows all the users created
						break;
					}
					
				case 2:
					if(account.checkIfEmpty() == true) {
						System.out.println("There are no Available Users!"); 
						break;
					}
					else {
						System.out.print("Enter Account Number: ");
						id = scanner.nextInt(); //Prints a certain Users Information based on the ID
						if(account.checkIfValid(id)) //Checks if the user is valid
						{
							account.printInfo(id);  //Checks if ID is valid and requires authentication by password
							break;
						}
						else {
							System.out.println("User Not Found!");
							break;
						}
					}
		
				case 3:
					System.out.print("Enter Name: ");
					name = scanner.nextLine();
					boolean isDigit = false;
					
					Pattern alphabet = Pattern.compile("[a-zA-Z\\s']+"); //Pattern for [A-Z] and White spaces
					Matcher m = alphabet.matcher(name);
					if(m.matches() == true){		
					}
					else {
						do{
							System.out.println(" ");
							System.out.println("Name Contains Invalid Character(s)");
						    System.out.print("Please enter a valid name: ");  //Loops until the name has no numbers
						    name = scanner.nextLine();
						    
						    alphabet = Pattern.compile("[a-zA-Z\\s']+"); //Pattern for [A-Z] and White spaces
							m = alphabet.matcher(name);
							
						    if(m.matches() == true) { //Checks if the name contains digit
						    	isDigit = false; //Breaks the loop if there are no digits
							}
						    else {
						    	isDigit = true;
						    }
						}while(isDigit == true);  //Loops until user enters a valid name without numbers
					}
					
					System.out.println(" ");
					System.out.println("Password Must be at least 3 Characters");
					System.out.print("Create Password: ");
					pass = scanner.nextLine();
					
					if (pass.length() < 3) //Checks if password entered is at least 3 Characters
					{
						while(pass.length() < 3) { //Loops until user has at least 3 Characters
							System.out.println(" ");
							System.out.println("Password Must be at least 3 Characters");
							System.out.print("Enter a Valid Password: ");
							pass = scanner.nextLine();
						}
						
					}
					System.out.println(" ");
					try { //Displays Invalid Input if the User Enters a non Digit Input
						System.out.println("Please Enter An Amount More than $1000");
						System.out.print("Enter Balance: ");
						bal = scanner.nextInt(); //User Input must be 1000 or more
						
						while(bal < 1000){ //Loops if User Input is less than 1000
							System.out.println(" ");
							System.out.println("Balance Should be $1000 Minimum");
						    System.out.print("Enter Balance: ");
						    bal = scanner.nextInt();
						}
						
						System.out.println("================================");
						System.out.println("   Account has Been Created!");
						account.createAccount(name, pass, bal); //Creates an Account
						System.out.println("================================");
						
					}catch(Exception e) {			
						System.out.println("Invalid Input");
					}
					break;
					
				case 4: //Deposit Account
					if(account.checkIfEmpty() == true) {
						System.out.println("There are no Available Users!"); 
						break;
					}
					else {
						System.out.print("Enter Account Number: ");
						id = scanner.nextInt();
						
						if(account.checkIfValid(id)) //Checks if the user is valid
						{
							System.out.print("Enter Password: ");
							pass = scanner.next();
							account.depositMoney(id,pass);
							break;
						}
						else {
							System.out.println("User Not Found!");
							break;
						}
						
					}
					
					
				case 5: //Withdraw Account
					if(account.checkIfEmpty() == true) {
						System.out.println("There are no Available Users!"); 
						break;
					}
					else {
						System.out.print("Enter Account Number: ");
						id = scanner.nextInt();
						
						if(account.checkIfValid(id)) //Checks if the user is valid
						{
							System.out.print("Enter Password: ");
							pass = scanner.next();
							account.withdrawMoney(id,pass);
							break;
						}
						else {
							System.out.println("User Not Found!");
							break;
						}
					}
				case 6:
					if(account.checkIfEmpty() == true) {
						System.out.println("There are no Available Users!"); 
						break;
					}
					else {
						System.out.print("Enter Account Number: ");
						id = scanner.nextInt();
						
						if(account.checkIfValid(id)) //Checks if the user is valid
						{
							System.out.print("Enter Password: ");
							pass = scanner.next();
							account.reccuringPayment(id,pass);
							
							Timer timer = new Timer();
					        TimerTask task = new scheduler();
					        timer.scheduleAtFixedRate(task, 0, 5000);
							break;
						}
						else {
							System.out.println("User Not Found!");
							break;
						}
					}
				case 7:
					System.out.println(" ");
					System.out.println(" Thank You for using Our Bank!"); 
					System.out.println(" ");
					System.out.println("================================");
					break;
				default: System.out.println("Invalid Option!");
			}
		}while (userchoice != 7); 	
	}
}
