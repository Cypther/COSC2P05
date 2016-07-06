/**
 * This is the ChequingAccount class that the client class uses
 * This class inherit from Accounts class
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */
public class ChequingAccount extends Accounts{
	
	/*Class Variables*/
	private double withdrawalFee = 1.00;
	private double overdrawnFee = 0.00018;
	private boolean overDrawn = true;
	
	//calling the super class
	public ChequingAccount(int accountNumber,String accountType, double accountBalance){
		super(accountNumber,accountType, accountBalance);
		
	}

	/* Getters and Setters */
	public double getWithdrawalFee() {
		return withdrawalFee;
	}

	public void setWithdrawalFee(double withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}

	public double getOverdrawnFee() {
		return overdrawnFee;
	}

	public void setOverdrawnFee(double overdrawnFee) {
		this.overdrawnFee = overdrawnFee;
	}

	public boolean isOverDrawn() {
		return overDrawn;
	}

	public void setOverDrawn(boolean overDrawn) {
		this.overDrawn = overDrawn;
	}

}
