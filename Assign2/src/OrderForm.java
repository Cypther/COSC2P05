/**
 * This is the OrderForm class for Acme Distributing, that does the operation
 * extends CheckedForms and use its methods. Also 
 * it prints it out to file logsPartB.txt
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import BasicIO.ASCIIOutputFile;

public class OrderForm extends CheckedForm {

	private ArrayList<Formatting> readingFormatArray = new ArrayList<Formatting>();
	//Outputfile in the file
	ASCIIOutputFile logs = new ASCIIOutputFile("logPartB.txt");

	/* Class Variables */
	CheckedForm form = new CheckedForm();

	/* Default Constructor */
	public OrderForm() {

		//reading in from the file
		Reader read = new Reader();

		readingFormatArray = read.getFormatList();

		form.addField("Date", "Invalid Date", Type.DATE);
		form.addText("Date", "Invalid Date");

		// Field for displaying the Date and format it
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
		String DateToStr = format.format(curDate); 

		curDate = readingFormatArray.get(0).getData(0);
		DateToStr = format.format(curDate);

		form.addField("Time", "Invalid Time", Type.TIME);
		form.addText("Time", "Invalid Time");

		//Time
		DateToStr = DateFormat.getTimeInstance(DateFormat.SHORT).format(readingFormatArray.get(0).getTime(0));

		//Order #
		form.addField("Order #", "Invalid Order", Type.INTEGER);
		form.addText("Order #", "Invalid Order");

		//Item #
		form.addField("Item #", "Invalid String", Type.STRING);

		//Quantity
		form.addField("Quantity", "Invalid Quantity", Type.INTEGER);
		form.addText("Quantity", "Invalid Quantity");

		//Textfield
		form.addField("Address", "Text", Type.STRING);


		int i = 0;
		
		if (i < readingFormatArray.size() - 1) {
			populateForms(i);
			//i++;
		}
		if (i == readingFormatArray.size() - 1) {
			i = 0;
		}

		// runs until user quits the program
		while (true) {

			int select = form.form.accept();

			// System.out.println(select);

			// if the user select ok
			if (select == 0) {

				Formatting readingFormat = new Formatting();
				
				if (form.readField("Date") == null) {
					readingFormat.setValid(false);
					continue;
				} else {
					// readField("Date");
					readingFormat.setData((Date) form.readField("Date"));
					readingFormat.setValid(true);
				}

				if (form.readField("Time") == null) {
					readingFormat.setValid(false);
					continue;
				} else {
					// readField("Time");
					readingFormat.setTime((Date) form.readField("Time"));
					readingFormat.setValid(true);
				}

				if (form.readField("Order #") == null) {
					readingFormat.setValid(false);
					continue;
				} else {
					readingFormat.setInteger((int) form.readField("Order #"));
					readingFormat.setValid(true);
				}

				if (form.readField("Item #") == null) {
					readingFormat.setValid(false);
					continue;
				} else {
					readingFormat.setString((String) form.readField("Item #"));
					readingFormat.setValid(true);
				}
				if (form.readField("Quantity") == null) {
					readingFormat.setValid(false);
					continue;
				} else {
					readingFormat.setInteger((int) form.readField("Quantity"));
					readingFormat.setValid(true);
					readingFormat.setText((String) form.readField("Address"));
				}

				if (readingFormat.isValid()) {
					form.readingFormatQueue.add(readingFormat);
					//readingFormatArray.set(i, readingFormat);
					//form.form.clearAll();
					if (i < readingFormatArray.size() - 1) {
						System.out.println(i);
						//readingFormatArray.set(i, readingFormat);
						populateForms(i);
						i++;
					}
					if (i == readingFormatArray.size() - 1) {
						i = 0;
					}
				}

			}
			if (select == 1) {
				System.out.println(" Queue have elements: " + form.readingFormatQueue.size());
				
				for(int j = 0; j < form.readingFormatQueue.size(); j++){
					
					//Writing the Date
					Date currentDate = form.readingFormatQueue.get(j).getData(0);
					DateFormat dateformat;
					dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
					System.out.println(dateformat.format(currentDate));
					logs.writeLine(dateformat.format(currentDate));
					
					//Writing the time
					Date time = form.readingFormatQueue.get(j).getTime(0);
					dateformat = DateFormat.getTimeInstance(DateFormat.SHORT);
					System.out.println(dateformat.format(time));
					logs.writeLine(dateformat.format(time));
					
					//Writing Integer
					System.out.println(String.valueOf(form.readingFormatQueue.get(j).getInteger(0)));
					logs.writeString(String.valueOf(form.readingFormatQueue.get(j).getInteger(0)));
					logs.writeString("\n");
					//Writing String
					System.out.println(form.readingFormatQueue.get(j).getString(0));
					logs.writeLine(form.readingFormatQueue.get(j).getString(0));
					//Writing Integer
					System.out.println(form.readingFormatQueue.get(j).getInteger(1));
					logs.writeString(String.valueOf(form.readingFormatQueue.get(j).getInteger(1)));
					logs.writeString("\n");
					//Writing Text Field
					System.out.println(form.readingFormatQueue.get(j).getText(0));
					logs.writeLine(form.readingFormatQueue.get(j).getText(0));
					logs.writeLine("");
					
				System.out.println(" ");
				//logs.writeString("\n");
				System.out.println(" ");
				//logs.writeString("\n");

			}
				logs.close();
				break;

			}

		}

	}
	
	/*closing the form*/
	
	public void close(){
		form.close();
	}

	/*populating the forms from the textfield*/
	private void populateForms(int i) {

		String DateToStr;
		Date curDate;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

		if (i < readingFormatArray.size() - 1) {
			form.form.clearAll();
			// Date
			curDate = readingFormatArray.get(i).getData(0);
			DateToStr = format.format(curDate);
			form.form.writeString("Date", DateToStr);
			// Time
			DateToStr = DateFormat.getTimeInstance(DateFormat.SHORT).format(readingFormatArray.get(i).getTime(0));
			form.form.writeString("Time", DateToStr);

			
			form.form.writeString("Order #", String.valueOf(readingFormatArray.get(i).getInteger(0)));
			form.form.writeString("Item #", readingFormatArray.get(i).getString(0));
			form.form.writeString("Quantity", String.valueOf(readingFormatArray.get(i).getInteger(1)));
			form.form.writeString("Address", readingFormatArray.get(i).getText(0));
			//i++;
			//System.out.println(i);
		}

	}

}
