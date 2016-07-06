/**
 * This is the Accounts class that the client class uses
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */

public class Accounts {

	/*Class Variables*/
	private int accountID;
	private String accountType;
	private double accountBalance;

	/* Default Constructor */
	public Accounts() {
		
		this.accountID = 0;
		this.accountType = "";
		this.accountBalance = 0;

	}

	/* Overload Constructor */
	public Accounts(int accountNumber,String accountType, double accountBalance) {
		this.accountID = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;

	}

	/* Getters and Setters */
	public double getBalance() {
		return accountBalance;
	}

	public double setBalance(double balance) {
		return this.accountBalance = balance;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
