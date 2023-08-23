package BankingSystem;

import java.util.Scanner;

public class accounts {

	static Scanner scanner = new Scanner(System.in);
	
	private String name;
	private String pass;
	private int balance;
	private int userMoney;
	private int accountCount;
	private String status;
	private int id;
	private int transactionCount = 0;
	private int counter = 0;
	private int[] transactionBal = new int[100];
	private String[] transactStatus = new String[100];

	
	public accounts(String name, String pass, int balance, int id){
		this.name = name;
		this.balance = balance;
		this.id = id;
		this.pass = pass;
	}
	
	public String previousTransaction(int transactionCount) {
		int tempTC = transactionCount + 1;
		return("[" + tempTC + "] " + transactionBal[transactionCount] + " was " + transactStatus[transactionCount]); 
	}
	
	public void deposit(int userMoney) {

		int oldBal = accountBal();
		int newBal = oldBal + userMoney;
		String name = accountName();
		
		this.name = name;
		this.balance = newBal;
		
		setStatus("deposited");
		
		transactionBal[counter] = userMoney; //stores current transaction to an array
		counter++;
	}
	
	public void withdraw(int userMoney) {

		int oldBal = accountBal();
		int newBal = oldBal - userMoney;
		String name = accountName();
		
		this.name = name;
		this.balance = newBal;
		
		setStatus("withdrawn");
		
		transactionBal[counter] = userMoney; //stores current transaction to an array
		counter++;
	}
	
	public void autoPay(int userMoney) {

		int oldBal = accountBal();
		int newBal = oldBal - userMoney;
		String name = accountName();
		
		this.name = name;
		this.balance = newBal;
		
		setStatus("automatically deducted");
		
		transactionBal[counter] = userMoney; //stores current transaction to an array
		counter++;
	}
	
	public String accountInfo() {
		return("User ID: "+ id +" | "+"Name: "+ name +" | "+ "Balance: "+ balance); //Show the Account Info
	}
	public int accountID() {
		return id;
	}
	public String accountName() {
		return name;
	}
	public String accountPass() {
		return pass;
	}
	public int accountBal() {
		return balance;
	}
	public void setAccountCount(int accountCount) {
		this.accountCount = accountCount;
	}
	public int getAccountCount() {
		return accountCount;
	}
	public void setMoney(int userMoney) {
		this.userMoney = userMoney;
	}
	public int getMoney() {
		return userMoney;
	}
	public void setStatus(String status) { //Stores status on the database
		this.status = status;
		transactStatus[counter] = this.status;
	}
	public void setTransactionCount() {
		transactionCount++;
	}
	public int getTransactionCount() {
		return transactionCount;
	}
	public int getBalance() {
		return balance;
	}
}
