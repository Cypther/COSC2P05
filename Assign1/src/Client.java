/**
 * This is the Client class use by the reader class
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */

public class Client {

	/*Class Variables*/
	private int clientNumber;
	private int clientPIN;
	private Accounts[] accountTypes;

	/* Default Constructor */
	public Client() {

	}
	/* Overload Constructor */
	public Client(int clientNumber, int clientPIN, Accounts[] numberOfAccounts) {
		this.clientNumber = clientNumber;
		this.clientPIN = clientPIN;
		this.accountTypes = numberOfAccounts;

	}

	/* Getters and Setters */
	
	public int getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}

	public int getClientPIN() {
		return clientPIN;
	}

	public void setClientPIN(int clientPIN) {
		this.clientPIN = clientPIN;
	}

	public double getAccountBalance(int i) {
		return accountTypes[i].getBalance();
	}

	public void setAccountBalance(int account, double newbalance) {
		
		accountTypes[account].setBalance(newbalance);
	}

	public String getAccountType(int i) {
		return accountTypes[i].getAccountType();
	}
	
	public Accounts getAccount(int i) {
		return accountTypes[i];
	}
	
	public void setNumberOfAccounts(int accounts) {
		this.accountTypes = new Accounts[accounts];
	}
	
	public void setAccounts(Accounts types, int i) {
		this.accountTypes[i] = types;
	}
	
	public int getNumberOfAccounts() {
		return this.accountTypes.length;
	}
	
}
