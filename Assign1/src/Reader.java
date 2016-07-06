/**
 * This is the Reader Class, that does all the operation
 * reading in the file. This class created the client object
 * and pass it back
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */

import java.util.Arrays;
import java.util.List;

import BasicIO.ASCIIDataFile;

public class Reader {
	
	private Client[] clients;

	public Reader() {

		//Getting the client file to read
		ASCIIDataFile file = new ASCIIDataFile("client.txt");

		String balanceSaving = "";
		String balanceSavingID = "";
		
		String balanceVacation = "";
		String balanceVacationID = "";
		
		String balanceChequing = "";
		String balanceChequingID = "";
		
		String balanceInvestment = "";
		String balanceInvestmentID = "";
		
		String balanceStudent = "";
		String balanceStudentID = "";

		List<String> items = null;

		String readFile = null;
		
		//checking if it's not the end of file
		while (file.isEOF() != true) {
			readFile = file.readLine();
		
			//breaking the string into and arraylist
			if (readFile != null) {
				items = Arrays.asList(readFile.split("\\s+"));
			}
			
			//if there are only 3 field
			if(items.size() == 3){
				//creating a new client object
				this.clients = new Client[1]; 
				clients[0] = new Client();
				//setting the client number from the array list
				clients[0].setClientNumber(Integer.parseInt(items.get(0)));
				//setting up the PIN number from the array list
				clients[0].setClientPIN(Integer.parseInt(items.get(1)));
				//setting up the number of accounts the client has
				clients[0].setNumberOfAccounts(Integer.parseInt(items.get(2)));
			}
			

			//setting up the types of accounts the client has from the file that been read in
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).equals("s") && items.get(i + 2).equals("savings")) {
					balanceSavingID = items.get(i + 1);
					balanceSaving = items.get(i + 3);
				}
				if (items.get(i).equals("s") && items.get(i + 2).equals("vacation")) {
					balanceVacationID = items.get(i + 1);
					balanceVacation = items.get(i + 3);
				}
				if (items.get(i).equals("c") && items.get(i + 2).equals("chequing")) {
					balanceChequingID = items.get(i + 1);
					balanceChequing = items.get(i + 3);
				}
				if (items.get(i).equals("i") && items.get(i + 2).equals("investment")) {
					balanceInvestmentID = items.get(i + 1);
					balanceInvestment = items.get(i + 3);
				}
				if (items.get(i).equals("x") && items.get(i + 2).equals("student")) {
					balanceStudentID = items.get(i + 1);
					balanceStudent = items.get(i + 3);
				}

			}

		}
		
		//instantiate the types from account the client has 
		for (int i = 0; i < clients[0].getNumberOfAccounts(); i++) {
			switch (i) {
			case 0:
				SavingAccount saving = new SavingAccount(Integer.parseInt(balanceSavingID), "Saving",Double.parseDouble(balanceSaving));
				clients[0].setAccounts(saving, i);
				break;
			case 1:
				SavingAccount vacation = new SavingAccount(Integer.parseInt(balanceVacationID), "Vacation",Double.parseDouble(balanceVacation));
				clients[0].setAccounts(vacation, i);
				break;
			case 2:
				ChequingAccount chequing = new ChequingAccount(Integer.parseInt(balanceChequingID), "Chequing", Double.parseDouble(balanceChequing));
				clients[0].setAccounts(chequing, i);
				break;
			case 3:
				InvestmentSavings investment = new InvestmentSavings(Integer.parseInt(balanceInvestmentID), "Investment", Double.parseDouble(balanceInvestment));
				clients[0].setAccounts(investment, i);
				break;
			case 4:
				StudentSavings student = new StudentSavings(Integer.parseInt(balanceStudentID), "Student", Double.parseDouble(balanceStudent));
				clients[0].setAccounts(student, i);
			}
		}

	}
	
	//return the client object
	public Client getClient(int i) {
		return clients[i];
	}
	
}
