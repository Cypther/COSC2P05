/**
 * This is the InvestmentSaving Account class that the client class uses
 * This class inherit from Accounts class
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */
public class InvestmentSavings extends Accounts {

	/*Class Variables*/
	private double minimumBalance = 2000.00;
	private double withdrawalFee = 1.00;
	private double interestRate = 0.00015;
	private double depositWithdrawalTransaction = 500.00;
	private boolean overDrawn = false;

	//calling the super class
	public InvestmentSavings(int accountNumber, String accountType, double accountBalance) {
		super(accountNumber, accountType, accountBalance);
	}

	/* Getters and Setters */
	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public double getWithdrawalFee() {
		return withdrawalFee;
	}

	public void setWithdrawalFee(double withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getDepositWithdrawalTransaction() {
		return depositWithdrawalTransaction;
	}

	public void setDepositWithdrawalTransaction(double depositWithdrawalTransaction) {
		this.depositWithdrawalTransaction = depositWithdrawalTransaction;
	}

	public boolean isOverDrawn() {
		return overDrawn;
	}

	public void setOverDrawn(boolean overDrawn) {
		this.overDrawn = overDrawn;
	}

}
