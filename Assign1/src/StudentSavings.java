/**
 * This is the Studnet Saving Account class that the client class uses
 * This class inherit from Accounts class
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */
public class StudentSavings extends Accounts {

	/*Class Variables*/
	private double interestRate = 0.00013;
	private boolean overDrawn = false;

	//calling the super class
	public StudentSavings(int accountNumber, String accountType, double accountBalance) {
		super(accountNumber, accountType, accountBalance);

	}

	/* Getters and Setters */
	public boolean isOverDrawn() {
		return overDrawn;
	}
	public void setOverDrawn(boolean overDrawn) {
		this.overDrawn = overDrawn;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

}
