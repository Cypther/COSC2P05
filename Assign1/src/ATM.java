/**
 * This is the ATM class, that does all the operation
 * for the ATM. The methods the ATM has are deposit, 
 * view account balance, withdraw and transfer balance
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */

import BasicIO.ASCIIOutputFile;
import BasicIO.BasicForm;

public class ATM {
	
	//reading in the file
	ASCIIOutputFile logs = new ASCIIOutputFile("log.txt");
	
	/*Class Variables*/
	BasicForm form;
	Client client;
	Reader read;

	/* Default Constructor */
	public ATM() {

		// reading in the account file
		read = new Reader();
		// creating the client;
		client = read.getClient(0);

		// creating the form
		form = new BasicForm("Balance", "Deposit", "Withdraw", "Transfer", "Quit");
		// setting the title
		form.setTitle("Bank of St.Kitts");

		// creating the radio button for the account
		form.addRadioButtons("Account", "Account", true, 30, 0, client.getAccountType(0), client.getAccountType(1),
				client.getAccountType(2), client.getAccountType(3), client.getAccountType(4));
	

		// creating the radio button for the other account
		form.addRadioButtons("Other Account", "Other Account", true, 350, 0, client.getAccountType(0),
				client.getAccountType(1), client.getAccountType(2), client.getAccountType(3), client.getAccountType(4));

		// Field for displaying the balance
		form.addTextField("Amount", "Amount", 15, 150, 50);
		// form.writeString("Amount", "this is field 1");
		form.addTextField("Displayfield", 65, 20, 135);

		//runs until user quits the program
		while (true) {

			int select = form.accept();

			// Viewing Balance on the account
			if (select == 0) {
				//if the user didn't select an account to view
				if (form.readInt("Account") == -1) {
					form.writeString("Displayfield", "");
					form.writeString("Displayfield", "Invalid");
					continue;
				}
				//if the user select savings
				if (form.readInt("Account") == 0) {
					viewBalance(0);
					form.writeString("Displayfield", "");
					form.accept("OK");
					form.writeString("Amount", "");
				}
				//if the user select vacation
				if (form.readInt("Account") == 1) {
					viewBalance(1);
					form.writeString("Displayfield", "");
					form.accept("OK");
					form.writeString("Amount", "");
				}
				//if the user select chequing
				if (form.readInt("Account") == 2) {
					viewBalance(2);
					form.writeString("Displayfield", "");
					form.accept("OK");
					form.writeString("Amount", "");
				}
				//if the user select investment saving
				if (form.readInt("Account") == 3) {
					viewBalance(3);
					form.writeString("Displayfield", "");
					form.accept("OK");
					form.writeString("Amount", "");
				}
				//if the user select student
				if (form.readInt("Account") == 4) {
					viewBalance(4);
					form.writeString("Displayfield", "");
					form.accept("OK");
					form.writeString("Amount", "");
				}

			}

			// Depositing in the the selected account
			if (select == 1) {
				
				String transaction = "Deposit";
				double deposite = form.readDouble("Amount");
				
				//if the value is invalid 
				if(deposite == 0.0 || deposite < 0.00){
					form.writeString("Displayfield", "Invalid input");
					continue;
				}
				
				//error handing if the user didn't put anything in the the field 
				if (form.readInt("Account") == -1 || form.readString("Amount").isEmpty()) {
					form.writeString("Displayfield", "");
					form.writeString("Displayfield", "Invalid: please enter amount");
					continue;
				}
				//The user can select OK or cancel the operation
				select = form.accept("OK", "Cancel");
				if (select == 1) {
					continue;
				}
				//if the user select savings
				if (form.readInt("Account") == 0) {

					deposite(transaction, 0,0, deposite);
				}
				//if the user select vacation
				if (form.readInt("Account") == 1) {
					deposite(transaction, 1,0, deposite);
				}
				//if the user select chequing
				if (form.readInt("Account") == 2) {
					deposite(transaction, 2,0, deposite);
				}
				//if the user select investment saving
				if (form.readInt("Account") == 3) {
					deposite(transaction, 3,0, deposite);
				}
				//if the user select student
				if (form.readInt("Account") == 4) {
					deposite(transaction, 4,0, deposite);
				}

			}

			// Withdraw in the the selected account
			if (select == 2) {

				String transaction = "Withdraw";
				double withdraw = form.readDouble("Amount");
				
				//if the value is invalid 
				if(withdraw == 0.0 || withdraw < 0.00){
					form.writeString("Displayfield", "Invalid input");
					continue;
				}

				//error handing if the user didn't put anything in the the field 
				if (form.readInt("Account") == -1 || form.readString("Amount").isEmpty()) {
					form.writeString("Displayfield", "");
					form.writeString("Displayfield", "Invalid: please enter amount");
					continue;
				}
				//The user can select OK or cancel the operation
				select = form.accept("OK", "Cancel");
				if (select == 1) {
					continue;
				}

				//if the user select savings
				if (form.readInt("Account") == 0) {
					withdraw(transaction, 0, 0, withdraw, client.getAccountBalance(0), 0);
				}
				//if the user select vacation
				if (form.readInt("Account") == 1) {
					withdraw(transaction, 1, 0, withdraw, client.getAccountBalance(1), 0);
				}
				//if the user select chequing
				if (form.readInt("Account") == 2) {
					withdraw(transaction, 2, 0, withdraw, client.getAccountBalance(2), 0);
				}
				//if the user select investment saving
				if (form.readInt("Account") == 3) {
					withdraw(transaction, 3, 0, withdraw, client.getAccountBalance(3), 0);
				}
				//if the user select student
				if (form.readInt("Account") == 4) {
					withdraw(transaction, 4, 0, withdraw, client.getAccountBalance(4), 0);
				}

			}

			// Transfer from the selected account to another account
			if (select == 3) {
				
				double transfer = form.readDouble("Amount");
				//if the value is invalid 
				if(transfer == 0.0 || transfer < 0.00){
					form.writeString("Displayfield", "Invalid input");
					continue;
				}
				//error handing if the user didn't put anything in the the field 
				if (form.readInt("Account") == -1 || form.readInt("Other Account") == -1 || form.readString("Amount").isEmpty()) {
					form.writeString("Displayfield", "");
					form.writeString("Displayfield", "Invalid: please enter amount");
					continue;
				}
				//The user can select OK or cancel the operation
				select = form.accept("OK", "Cancel");
				if (select == 1) {
					continue;
				}

				//calling the the method to transfer
				transfer(form.readInt("Account"), form.readInt("Other Account"), transfer);
			}

			// Quitting the program when the user pushes quit
			if (select == 4) {
				select = form.accept("OK", "Cancel");
				if (select == 1) {
					continue;
				}
				//calling the method to apply the interest rate before quiting the program
				applyingInterestRate();
				form.close();
			}
		}

	}

	/*
	 * Method for Viewing the Balance on the Account
	 * passing in the account to view and displaying the balance
	 */

	public void viewBalance(int account) {
		form.writeString("Amount", "");
		form.writeDouble("Amount", client.getAccountBalance(account));
	}

	/*
	 * Method for Depositing funds to the Account
	 * passing in the account, the amount to deposit
	 * and the type of transaction
	 * 
	 */

	public boolean deposite(String transaction, int account1, int account2, double deposite) {

		double originalBalance1 = client.getAccountBalance(account1);
		double originalBalance2 = client.getAccountBalance(account2);

		
		//checking if the deposite is less than 500, if it's not then it will apply the rates
		if ( transaction.equalsIgnoreCase("Transfer")  == true && (account1 == 3 || account2 == 3) &&  deposite < 500.00) {
		form.writeString("Displayfield", "");
		form.writeString("Displayfield", "Rejected");
		return false;
		}if(transaction.equalsIgnoreCase("Transfer") == true){
			double totalAmount = deposite + client.getAccountBalance(account2);
			client.setAccountBalance(account2, totalAmount);
			form.writeString("Amount", "");
			
		}else{
			double totalAmount = deposite + client.getAccountBalance(account1);
			//applying the deposite 
			client.setAccountBalance(account1, totalAmount);
			form.writeString("Amount", "");
			//calling the write to log method
			if (!transaction.equalsIgnoreCase("Transfer")) {
				writeToLog(transaction, account1, 0, deposite, originalBalance1, originalBalance2, true);
			}
		}
		return true;

	}
	
	/*
	 * withdraw method 
	 * this transfer money out of the selected account.
	 * This method does checks for overdrawn, if it's allow and apply the fees
	 * It will return true of false if the transaction is successful 
	 * */

	public boolean withdraw(String transaction, int account1, int account2, double withdraw, double originalBalance1,
			double originalBalance2) {


		double balance = client.getAccountBalance(account1);
		balance = balance - withdraw;

		//checking if the balance will be negative 
		if (balance < 0) {
			switch (account1) {
			//checking the types of account and applying the overdraft
			case 0: //saving account
				if (((SavingAccount) client.getAccount(account1)).isOverDrawn()) {
					client.setAccountBalance(account1, originalBalance1);
					form.writeString("Amount", "");
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					return false;
				} else {
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					return false;
				}
			case 1://saving account
				if (((SavingAccount) client.getAccount(account1)).isOverDrawn()) {
					client.setAccountBalance(account1, withdraw);
					form.writeString("Amount", "");
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					return false;
				} else {
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					return false;
				}
			case 2://chequing account
				if (((ChequingAccount) client.getAccount(account1)).isOverDrawn()) {
					balance = balance - ((ChequingAccount) client.getAccount(account1)).getWithdrawalFee();
					client.setAccountBalance(account1, balance);
					form.writeString("Amount", "");
					//System.out.println("Charge of $1.00 per withdrawal");
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
					return true;
				} else {
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
					return true;
				}
			case 3://investment saving
				if (((InvestmentSavings) client.getAccount(account1)).isOverDrawn()) {
					client.setAccountBalance(account1, withdraw);
					form.writeString("Amount", "");
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					return false;
				} else {
					// System.out.println("No overdraft transfer");
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					return false;
				}
			case 4://student savings
				if (((StudentSavings) client.getAccount(account1)).isOverDrawn()) {
					client.setAccountBalance(account1, withdraw);
					form.writeString("Amount", "");
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					return true;
				} else {
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					// System.out.println("No overdraft transfer");
					return false;
				}
			default://error if account not found
				writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
				return false;
			}
		}
		//if the balance is greater than zero
		if (balance >= 0) {

			switch (account1) {
			case 0://savings account, applying the new balance
				if (((SavingAccount) client.getAccount(account1)).getMinimumBalance() <= balance) {
					System.out.println(balance);
					client.setAccountBalance(account1, balance);
					form.writeString("Amount", "");
					//writing to log
					if (!transaction.equalsIgnoreCase("Transfer")) {
						writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
					}
					return true;
				}
				if(balance - ((SavingAccount) client.getAccount(account1)).getWithdrawalFee() < 0){
					if (!transaction.equalsIgnoreCase("Transfer")) {
						writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					}
					return false;
				}else {
					//savings account, applying the new balance if the minimum balance is met
					double withdrawalFees = balance - ((SavingAccount) client.getAccount(account1)).getWithdrawalFee();
					client.setAccountBalance(account1, withdrawalFees);
					form.writeString("Amount", "");
					//System.out.println("Charge of $0.50 per withdrawal");
					//writing to log
					if (!transaction.equalsIgnoreCase("Transfer")) {
						writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
					}
					return true;
				}
			case 1://savings account, applying the new balance
				if (((SavingAccount) client.getAccount(account1)).getMinimumBalance() <= balance) {
					client.setAccountBalance(account1, balance);
					form.writeString("Amount", "");
					//writing to log
					if (!transaction.equalsIgnoreCase("Transfer")) {
						writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
					}
					return true;
				}if(balance - ((SavingAccount) client.getAccount(account1)).getWithdrawalFee() < 0){
					if (!transaction.equalsIgnoreCase("Transfer")) {
						writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					}
					return false;
				}else {
					double withdrawalFees = balance - ((SavingAccount) client.getAccount(account1)).getWithdrawalFee();
					client.setAccountBalance(account1, withdrawalFees);
					form.writeString("Amount", "");
					System.out.println("Charge of $0.50 per withdrawal");
					if (!transaction.equalsIgnoreCase("Transfer")) {
						writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
					}
					return true;
				}
			case 2:// No miniumBalance for chequing account
				balance = balance - ((ChequingAccount) client.getAccount(account1)).getWithdrawalFee();
				client.setAccountBalance(account1, balance);
				form.writeString("Amount", "");
				//System.out.println("Charge of $1.00 per withdrawal");
				//writing to log
				if (!transaction.equalsIgnoreCase("Transfer")) {
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
				}
				return true;
			case 3:// Investment saving withdraw must be greater than 500
				
				
				if (((InvestmentSavings) client.getAccount(account1)).getMinimumBalance() <= balance
						&& withdraw >= 500.00) {
					client.setAccountBalance(account1, balance);
					form.writeString("Amount", "");
					//writing to log
					if (!transaction.equalsIgnoreCase("Transfer")) {
						writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
					}
					return true;
				} 
				
				if (((InvestmentSavings) client.getAccount(account1)).getMinimumBalance() > balance
						&& withdraw >= 500.00) {
					System.out.println(withdraw);
					double withdrawalFees = balance - ((InvestmentSavings) client.getAccount(account1)).getWithdrawalFee();
					client.setAccountBalance(account1, withdrawalFees);
					
					form.writeString("Amount", "");
					//writing to log
					if (!transaction.equalsIgnoreCase("Transfer")) {
						writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
					}
					return true;
				}else{
					if (!transaction.equalsIgnoreCase("Transfer")) {
						writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, false);
					}
					return false;
				}
			case 4:// No MinumBalance for student account
				client.setAccountBalance(account1, balance);
				form.writeString("Amount", "");
				if (!transaction.equalsIgnoreCase("Transfer")) {
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
				}
				return true;
			default:
				// System.out.println("No overdraft transfer");
				if (!transaction.equalsIgnoreCase("Transfer")) {
					writeToLog(transaction, account1, account2, withdraw, originalBalance1, originalBalance2, true);
				}
				return false;
			}

		}
		return false;

	}
	
	/*
	 * Transfer method, transfer funds from one account to another
	 * by using the withdraw and deposite method
	 * */

	public void transfer(int account, int otherAccout, double transfer) {

		//check if the user is not trying to transfer into the same account
		if (account == otherAccout) {
			form.writeString("Displayfield", "");
			form.writeString("Displayfield", "Invalid");
			return;
		}

		String transaction = "Transfer";
		double originalBalance1 = client.getAccountBalance(account);
		double originalBalance2 = client.getAccountBalance(otherAccout);

		//check if the the withdraw is successful
		if (withdraw(transaction, account, otherAccout, transfer, originalBalance1, originalBalance2) == true) {
			System.out.println(client.getAccountBalance(account));
			System.out.println(client.getAccountBalance(otherAccout));
			if(deposite(transaction, account, otherAccout, transfer) == true){
				//write to log if it's true
				System.out.println("true");
				System.out.println(client.getAccountBalance(account));
				System.out.println(client.getAccountBalance(otherAccout));
				writeToLog(transaction, account, otherAccout, transfer, originalBalance1, originalBalance2, true);
			}else{
				client.setAccountBalance(account, originalBalance1);
				client.setAccountBalance(otherAccout, originalBalance2);
				writeToLog(transaction, account, otherAccout, transfer, originalBalance1, originalBalance2, false);
			}
		} else {
			 writeToLog(transaction, account, otherAccout, transfer, originalBalance1, originalBalance2, false);
			// System.out.println("Transfer failed");
		}

	}
	
	/*
	 * Method to apply interest rates
	 * before the program closes
	 * 
	 * */

	public void applyingInterestRate() {

		double applyingInterest = 0;
		String transaction = "Interest";

		//check if the Saving account is not null
		if (client.getAccount(0) != null) {
			applyingInterest = ((SavingAccount) client.getAccount(0)).getBalance() + (((SavingAccount) client.getAccount(0)).getBalance()
					* ((SavingAccount) client.getAccount(0)).getInterestRate());

			double originalBalance = client.getAccountBalance(0);
			client.setAccountBalance(0, applyingInterest);
			writeToLog(transaction, 0, 0, 0, originalBalance, 0, true);

		}
		//check if the Vacation account is not null
		if (client.getAccount(1) != null) {
			applyingInterest = ((SavingAccount) client.getAccount(1)).getBalance() + (((SavingAccount) client.getAccount(1)).getBalance()
					* ((SavingAccount) client.getAccount(1)).getInterestRate());
			
			double originalBalance = client.getAccountBalance(1);
			client.setAccountBalance(1, applyingInterest);
			writeToLog(transaction, 1, 0, 0, originalBalance, 0, true);

		}
		//check if the Chequing account is not null
		if (client.getAccount(2) != null && client.getAccount(2).getBalance() < 0) {
			applyingInterest = ((ChequingAccount) client.getAccount(2)).getBalance()+(((ChequingAccount) client.getAccount(2)).getBalance()
					* ((ChequingAccount) client.getAccount(2)).getOverdrawnFee());

			double originalBalance = client.getAccountBalance(2);
			client.setAccountBalance(2, applyingInterest);
			writeToLog(transaction, 2, 0, 0, originalBalance, 0, true);

		}
		//check if the Investment account is not null
		if (client.getAccount(3) != null) {
			applyingInterest = ((InvestmentSavings) client.getAccount(3)).getBalance() + (((InvestmentSavings) client.getAccount(3)).getBalance()
					* ((InvestmentSavings) client.getAccount(3)).getInterestRate());
			
			double originalBalance = client.getAccountBalance(3);
			client.setAccountBalance(3, applyingInterest);
			writeToLog(transaction, 3, 0, 0, originalBalance, 0, true);

		}
		//check if the Student account is not null
		if (client.getAccount(4) != null) {
			applyingInterest = ((StudentSavings) client.getAccount(4)).getBalance() + (((StudentSavings) client.getAccount(4)).getBalance()
					* ((StudentSavings) client.getAccount(4)).getInterestRate());

			double originalBalance = client.getAccountBalance(4);
			client.setAccountBalance(4, applyingInterest);
			writeToLog(transaction, 4, 0, 0, originalBalance, 0, true);

		}

	}
	
	/*
	 * Method to write to log file
	 * after each transaction that is successful or rejected
	 * and displaying it to the text field 
	 * 
	 * */

	public void writeToLog(String transaction, int account1, int account2, double deposite, double originalBalance1,
			double originalBalance2, boolean completed) {

		String stringToLog = "";
		//System.out.println("completed " + completed);
		
		/* Checking if the transaction was successful or rejected
		and getting the string to to write to the log file */
		
		if (completed == true && transaction.equalsIgnoreCase("Transfer")) {
			form.writeString("Displayfield", "");
			form.writeString("Displayfield", "Successful");
		}
		if (completed == false && transaction.equalsIgnoreCase("Transfer")) {
			form.writeString("Displayfield", "");
			form.writeString("Displayfield", "Rejected");
		}

		if (transaction.equalsIgnoreCase("Deposit") && completed == true) {
			stringToLog = transaction + " " + deposite + " To: " + client.getAccount(account1).getAccountID()
					+ " orig: " + originalBalance1 + "\tnew: " + client.getAccountBalance(account1) + ": Successful";
		}
		if (transaction.equalsIgnoreCase("Deposit") && completed == false) {
			stringToLog = transaction + " " + deposite + " To: " + client.getAccount(account1).getAccountID()
					+ " orig: " + originalBalance1 + "\tnew: " + client.getAccountBalance(account1) + ": Rejected";
		}
		if (transaction.equalsIgnoreCase("Withdraw") && completed == true) {
			stringToLog = transaction + " " + deposite + " From: " + client.getAccount(account1).getAccountID()
					+ " orig: " + originalBalance1 + "\tnew: " + client.getAccountBalance(account1) + ": Successful";
		}
		if (transaction.equalsIgnoreCase("Withdraw") && completed == false) {
			stringToLog = transaction + " " + deposite + " From: " + client.getAccount(account1).getAccountID()
					+ " orig: " + originalBalance1 + "\tnew: " + client.getAccountBalance(account1) + ": Rejected";

		}
		if (transaction.equalsIgnoreCase("Transfer") && completed == true) {
			stringToLog = transaction + " " + deposite + " From: " + client.getAccount(account1).getAccountID()
					+ " orig: " + originalBalance1 + "\tTo: " + client.getAccount(account2).getAccountID() + " orig: "
					+ originalBalance2 + " From new: " + client.getAccountBalance(account1) + " To new: "
					+ client.getAccountBalance(account2) + " Successful ";
		}
		if (transaction.equalsIgnoreCase("Transfer") && completed == false) {
			stringToLog = transaction + " " + deposite + " From: " + client.getAccount(account1).getAccountID()
					+ " orig: " + originalBalance1 + "\tTo: " + client.getAccount(account2).getAccountID() + " orig: "
					+ originalBalance2 + " From new: " + client.getAccountBalance(account1) + " To new: "
					+ client.getAccountBalance(account2) + " Rejected ";

		}
		if (transaction.equalsIgnoreCase("Interest") && completed == true) {
			stringToLog = transaction + ": " + client.getClientNumber() + " : "
					+ client.getAccount(account1).getAccountID() + " orig: " + originalBalance1 + "\tnew: "
					+ client.getAccountBalance(account1) + ": Successful";
		}
		if (transaction.equalsIgnoreCase("Interest") && completed == false) {
			stringToLog = transaction + ": " + client.getClientNumber() + " : "
					+ client.getAccount(account1).getAccountID() + " orig: " + originalBalance1 + "\tnew: "
					+ client.getAccountBalance(account1) + ": Rejected";
		}
		
		if (!transaction.equalsIgnoreCase("Transfer")) {
			form.writeString("Displayfield", "");
			form.writeString("Displayfield", stringToLog);
		}

		//writing to the file
		logs.writeLine(stringToLog);

	}

}
