package BankingSystem;

import java.util.*;

final class bankAccount{
	static Random rand= new Random();
	static Scanner scanner = new Scanner(System.in);  
	
	public static accounts[] accountsBank; // All Account in the bank
	private static int accountCount; // Counts how many account are currently in the bank
	
	public bankAccount() {
		accountsBank = new accounts[100];
		accountCount = 0;	
	}
	public boolean checkIfEmpty() {
		if (accountCount == 0) {
			return true;
		}
		return false;
	}
	
	public boolean checkIfValid(int accountNum) {
		for (int i = 0; i<accountCount; i++) {
			if(accountNum == accountsBank[i].accountID()){
				return true;
			}
		}
		return false;
	}
	
	public void createAccount(String name, String pass, int balance) {
		
		int accNum = (int)Math.floor(Math.random() * (5000 - 1000 + 1) + 1000);
		
		accounts accounts = new accounts(name,pass,balance,accNum);
		accountsBank[accountCount] = accounts; //Saves Account details to an array
		accountCount++; //Increments account Count for every new account created
		
		System.out.println(" Your Account Number is: " + accNum); //Prints the users ID after account Creation
	}

	public void printInfo(int accountNum) {
		for (int i = 0; i<accountCount; i++) {
			if(accountNum == accountsBank[i].accountID()) { //Checks if the user has an account
				
				System.out.print("Enter Password: ");
				String pass = scanner.next();
				System.out.println(" ");
				
				if(pass.equals(accountsBank[i].accountPass())) //Validates user password before showing User Information
				{
					if(accountsBank[i].getBalance() == 0) { //Will Warn The User if balance reaches 0
						System.out.println("----------------------------------");
						System.out.println("Warning: You Have No Balance Left!"); 
						System.out.println("----------------------------------");
					}
					else if(accountsBank[i].getBalance() == 500) { //Will Warn The User if balance reaches 500
						System.out.println("--------------------------------------------------");
						System.out.println("Warning: Account Balance has Reached the Threshold"); 
						System.out.println("--------------------------------------------------");
					}
					else if(accountsBank[i].getBalance() < 500) { //Will Warn The User if balance is below 600
						System.out.println("-------------------------------------------");
						System.out.println("Warning: Account Balance is below threshold"); 
						System.out.println("-------------------------------------------");
					}
					
					System.out.println("Account ID: " + accountsBank[i].accountID());
					System.out.println("Account Name: " + accountsBank[i].accountName());
					System.out.println("Account Password: " + accountsBank[i].accountPass());
					System.out.println("Account Balance: " + accountsBank[i].accountBal());
					System.out.println(" ");
					System.out.println("================================");
					System.out.println("    Previous Transactions");
					System.out.println("================================");
						
					int counter = 0;
					int tc = accountsBank[i].getTransactionCount();
					for (int j = 0; j<tc; j++) 
					{	
						System.out.println(accountsBank[i].previousTransaction(counter)); //prints all the previous transaction of the selected user
						counter++;
					}				
				}
				else {
					System.out.println("Invalid Password!");
				}
			}
		}
	}
	
	public void printInfoList() { 
		for (int i = 0; i<accountCount; i++)  //Print all Users
		{
			System.out.println(accountsBank[i].accountInfo());
		}
	}
	
	public void depositMoney(int accountNum, String pass){
		int userMoney = 0;
		for(int i = 0; i<accountCount; i++) {
			if(accountNum == accountsBank[i].accountID()) { //Checks if the user has an account
				if(pass.equals(accountsBank[i].accountPass()))
				{
					System.out.println(" ");
					System.out.println("Deposit Money into Account");
					System.out.print("Enter Amount: ");
					userMoney = scanner.nextInt();
					
					System.out.println(" ");
					System.out.println("--------------------------------");
					System.out.println("      Deposit Successful");
					System.out.println("--------------------------------");
					accountsBank[i].deposit(userMoney); 
					accountsBank[i].setTransactionCount(); //increments transactionCount
					
					
				}
				else {
					System.out.println("Incorrect Password. Try again!");
					break;
				}
			}
		}
	}
	
	
	public void withdrawMoney(int accountNum, String pass){
		int userMoney = 0;
		for(int i = 0; i < accountCount; i++) {
			if(accountNum == accountsBank[i].accountID()) { //Checks if the user has an account
				if(pass.equals(accountsBank[i].accountPass())){
					boolean x = true;
					do {
						if(accountsBank[i].getBalance() == 0) { //Will Warn The User if balance reaches 0
							System.out.println(" ");
							System.out.println("----------------------------------");
							System.out.println("Warning: You Have No Balance Left!"); 
							System.out.println("----------------------------------");
							return;
						}
						else if(accountsBank[i].getBalance() == 500) { //Will Warn The User if balance reaches 500
							System.out.println(" ");
							System.out.println("--------------------------------------------------");
							System.out.println("Warning: Account Balance has Reached the Threshold"); 
							System.out.println("--------------------------------------------------");
						}
						else if(accountsBank[i].getBalance() < 500) { //Will Warn The User if balance is below 600
							System.out.println(" ");
							System.out.println("-------------------------------------------");
							System.out.println("Warning: Account Balance is below threshold"); 
							System.out.println("-------------------------------------------");
						}
						
						System.out.println(" ");
						System.out.println("Withdraw Money from Account");
						System.out.print("Enter Amount: ");
						userMoney = scanner.nextInt();
						System.out.println(" ");
						
						if(userMoney == accountsBank[i].getBalance()) { //If user enters the same amount with the balance it warns the user
							do {
								System.out.print("Withdraw all your balance? [Y/N]: ");
								String userChoice = scanner.next();
								
								if (userChoice.toUpperCase().equals("Y")){
									System.out.println(" ");
									System.out.println("--------------------------------");
									System.out.println("      Withdraw Successful");
									System.out.println("--------------------------------");
									accountsBank[i].withdraw(userMoney);
									accountsBank[i].setTransactionCount(); //increments transactionCount
									return;
								}
								else if (userChoice.toUpperCase().equals("N")){
									System.out.println("--------------------------------");
									System.out.println("     Transaction Cancelled");
									System.out.println("--------------------------------");
									return;
								}
								else {
									System.out.println("Invalid Input!");
								}
							}while(x == true);
						}
						else if (userMoney <= accountsBank[i].getBalance()) {
							System.out.println("--------------------------------");
							System.out.println("      Withdraw Successful");
							System.out.println("--------------------------------");
							accountsBank[i].withdraw(userMoney);
							accountsBank[i].setTransactionCount(); //increments transactionCount
							return;
						}
						else {
							System.out.println("Amount Entered is Invalid!");
						}
					}while(x == true);
					
				}
				else {
					System.out.println("Incorrect Password. Try again!");
					break;
				}
			}
		}
	}
	
	public void reccuringPayment(int accountNum, String pass){
		int userMoney = 0;
		for(int i = 0; i<accountCount; i++) {
			if(accountNum == accountsBank[i].accountID()) { //Checks if the user has an account
				if(pass.equals(accountsBank[i].accountPass()))
				{
					int hours = 0;
					int minutes = 0;
					int seconds = 0;
					int Timeinterval = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000) + (seconds * 1000);
					
					System.out.println(" ");
					System.out.println("----------------------------------");
					System.out.println("   Schedule Automatic Payment!");
					System.out.println("----------------------------------");
					
					System.out.print("Enter Hour(s): ");
					hours = scanner.nextInt(); 
					scanner.nextLine();
					
					System.out.print("Enter Minute(s): ");
					minutes = scanner.nextInt(); 
					scanner.nextLine();
					
					System.out.print("Enter Second(s): ");
					seconds = scanner.nextInt(); 
					scanner.nextLine();
					
					System.out.print("Enter Amount: ");
					userMoney = scanner.nextInt();
					
					scheduler scheduler = new scheduler();
					scheduler.setMoney(userMoney);
					scheduler.setCount(i);
			        
					System.out.println(" ");
					System.out.println("--------------------------------");
					System.out.println(" Automatic Payment has been Set");
					System.out.println("--------------------------------");			
					
				}
				else {
					System.out.println("Incorrect Password. Try again!");
					break;
				}
			}
		}
	}

}
	
