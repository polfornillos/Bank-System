package BankingSystem;
import java.util.TimerTask;

public class scheduler extends TimerTask{
	private static int i = 0;
	private static int userMoney = 0;
	
	public void setCount(int i){
		this.i = i;
	}
	public static int getCount(){
		return i;
	}
	public void setMoney(int userMoney){
		this.userMoney = userMoney;
	}
	public static int getMoney(){
		return userMoney;
	}
	@Override
	public void run() {	
		accounts value = bankAccount.accountsBank[getCount()]; 
		value.autoPay(userMoney);
		value.setTransactionCount();
	} 
}
