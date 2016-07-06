
/**
 * This is the CheckedForm class, that does all the operation
 * for the Order Forms. The methods it has are addField, 
 * readField, addText, readText, accept, close
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */

import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import BasicIO.ASCIIOutputFile;
import BasicIO.BasicForm;
import BasicIO.Formats;

public class CheckedForm {


	/* Class Variables */
	ArrayList<String> components = new ArrayList<String>();
	ArrayList<FieldInformation> componentsInformation = new ArrayList<FieldInformation>();
	
	//outlog
	ASCIIOutputFile logs = new ASCIIOutputFile("logPartA.txt");

	BasicForm form;

	//Formatting readingFormat = new Formatting();
	ArrayList<Formatting> readingFormatQueue = new ArrayList<Formatting>();

	/* Default Constructor */
	public CheckedForm() {

		// creating the form
		form = new BasicForm("OK", "Quit");
		// setting the title
		form.setTitle("BasicForm");

	}
	
	/*
	 * Adding each components to the forms with the text and label and types
	 * 
	 * */

	public void addField(String textField, String label, Type kind) {
		
		
		FieldInformation info = new FieldInformation(textField, label, kind);
		componentsInformation.add(info);

		Object type = null;

		/*checking with types format for the for addTextField*/
		if (kind.equals(kind.DATE)) {
			type = Formats.getDateTimeInstance();
			type = (DateFormat) type;
		}
		if (kind.equals(kind.TEXT)) {
			type = Formats.getTimeInstance();
			type = (DateFormat) type;
		}
		if (kind.equals(kind.CURRENCY)) {
			type = Formats.getCurrencyInstance();
			type = (NumberFormat) type;
		}
		if (kind.equals(kind.DECIMAL)) {
			type = Formats.getDecimalInstance(4);
			type = (NumberFormat) type;
		}
		if (kind.equals(kind.INTEGER)) {
			type = Formats.getIntegerInstance();
			type = (NumberFormat) type;
		}
		if (kind.equals(kind.PERCENT)) {
			type = Formats.getPercentInstance();
			type = (NumberFormat) type;
		}

		//adding the textfelids
		if (label.equalsIgnoreCase("Text")) {
			form.addTextArea(textField, textField, 10, 60, 5, 250);
			return;
		}

		// Adding the first field
		if (components.size() % 2 == 0 && components.isEmpty()) {
			form.addTextField(textField, textField, (Format) type, 20);
			components.add(textField);
			return;

			// Adding the field to the left format
		} else if (components.size() % 2 == 0) {
			form.addTextField(textField, textField, (Format) type, 20,
					form.getX((String) components.get(components.size() - 2)),
					form.getY((String) components.get(components.size() - 2)) + 60);

			components.add(textField);
			return;
			// Adding the field to the right format
		} else {
			form.addTextField(textField, textField, (Format) type, 20,
					form.getX((String) components.get(components.size() - 1)) + 250,
					form.getY((String) components.get(components.size() - 1)));

			components.add(textField);
			return;
		}

	}
	
	/*
	 * reading in the fields and passing the object back
	 * 
	 * */

	public Object readField(String name) {
		
		String info = "";
		String informationLabel = "";
		for(int i = 0; i < componentsInformation.size(); i++){
			//if(name.compareTo(componentsInformation.get(i).getKind().getName()) == 0){
			if(name.compareTo(componentsInformation.get(i).getTextField()) == 0){
				info = componentsInformation.get(i).getKind().getName();
				informationLabel = componentsInformation.get(i).getLabel();
			}
		}

		Object data = null;

		// Reading the string in the field
		String readingTheField = form.readString(name);
		
		readingTheField.trim();
		//String readingTheField = readText(name);

		DateFormat dateformat;
		NumberFormat format = null;

		/*the try catch */
		try {

			switch (info) {
			case "Date":

				Calendar myCal = Calendar.getInstance();
				// myCal.set( theYear, theMonth, theDay );
				Scanner scanDate = new Scanner(readingTheField).useDelimiter("/");

				myCal.set(scanDate.nextInt(), scanDate.nextInt() - 1, scanDate.nextInt());
				dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
				Date curDate = myCal.getTime();
				form.setLabel(informationLabel, "");

				//System.out.println(dateformat.format(curDate));

				data = curDate;
				return data;

			case "Time":
				dateformat = DateFormat.getTimeInstance(DateFormat.SHORT);

				Scanner scanTime = new Scanner(readingTheField).useDelimiter(":| ");
				Date curTime = null;
				if (readingTheField.contains("PM")) {
					curTime = new Date(0, 0, 0, scanTime.nextInt() + 12, scanTime.nextInt());
				} else {
					curTime = new Date(0, 0, 0, scanTime.nextInt(), scanTime.nextInt());
				}
				form.setLabel(informationLabel, "");
				//System.out.println(dateformat.format(curTime));

				data = curTime;
				return data;
			case "Currency":
				double currecny;
				format = Formats.getCurrencyInstance();
				currecny = Double.parseDouble(readingTheField);
				form.setLabel(informationLabel, "");
				//System.out.println(currecny);

				data = currecny;
				return data;
			case "Decimal":
				double decimal;
				format = Formats.getDecimalInstance(5);
				// testing = format.format(Double.parseDouble(readingTheField));
				decimal = Double.parseDouble(readingTheField);
				form.setLabel(informationLabel, "");
				//System.out.println(decimal);

				data = decimal;
				return data;
			case "Integer":
				int integer;
				format = Formats.getIntegerInstance();
				// testing = format.format(Integer.parseInt(readCurrency));
				integer = Integer.parseInt(readingTheField);
				form.setLabel(informationLabel, "");
				//System.out.println(integer);

				data = integer;
				return data;
			case "Percent":
				double percent;
				// format = Formats.getPercentInstance();
				// testing = format.format(Double.parseDouble(readCurrency));
				percent = Double.parseDouble(readingTheField);
				form.setLabel(informationLabel, "");
				//System.out.println(percent);
				data = percent;
				return data;
			// return percent;
			case "String":
				// return readingTheField;
				// return tryFormatting;
				return readingTheField;

			}

			// } catch (NumberFormatException e) {
			//if there's an error throw an exception and return null
		} catch (Exception e) {
			// not a double
			// System.out.println(label);
			form.setLabel(informationLabel, "Bad Format, re-enter");
			 System.out.println("Not working?");
			return null;
		}
		return null;
	}

	/* Adding the text to each fields in the form */
	public void addText(String name, String label) {

		int size = components.size();
		size = size - 1;

		if (size % 2 == 0 && components.get(0).equalsIgnoreCase(name)) {
			//System.out.println("True");
			form.addLabel(label, "                                           ", 5, 20);

		} else if (size % 2 == 0) {

			form.addLabel(label, "                                      ",
					form.getX((String) components.get(components.size() - 1)),
					form.getY((String) components.get(components.size() - 1)) + 20);

		} else {

			form.addLabel(label, "                                       ",
					form.getX((String) components.get(components.size() - 2)) + 250,
					form.getY((String) components.get(components.size() - 2)) + 20);

		}

	}
	
	/*
	 * 
	 * reading in the text textfields
	 * 
	 * */
	
	public String readText( String name ){
		
		// Reading the string in the field
		String readingTheField = "";
		char readingTextArea = ' ';
		//readingTextArea
		
		//end of text 
		while(true){
			
			readingTextArea  = form.readC(name);
			if(readingTextArea == 65535){
				break;
			}else{
				readingTheField += readingTextArea;
			}

		}
		
		return readingTheField.trim();	
	}
	
	/*
	 * Getting the user's input for the forms
	 * 0 = OK
	 * 1 = Quit
	 * 
	 * when the user pushings 1 it writes out the 
	 * fields to a file logPartA.txt
	 * 
	 * */
	public void accept(){
		
		while (true) {
			int select = form.accept();

			// if the user select okay
			if (select == 0) {

				Formatting readingFormat = new Formatting();
				
				if(readField("Date") == null){
					readingFormat.setValid(false);
					continue;
				}else{
					// readField("Date");
					readingFormat.setData((Date) readField("Date"));
					readingFormat.setValid(true);
				}
				
				if(readField("Time") == null){
					readingFormat.setValid(false);
					continue;
				}else{
					// readField("Time");
					readingFormat.setTime((Date) readField("Time"));
					readingFormat.setValid(true);
				}
				
				if(readField("Currency") == null){
					readingFormat.setValid(false);
					continue;
				}else{// readField("Currency");
					readingFormat.setCurrency((double) readField("Currency"));
					readingFormat.setValid(true);
				}

				if(readField("Decimal") == null){
					readingFormat.setValid(false);
					continue;
				}else{
					// = readField("Decimal");
					readingFormat.setDecimal((double) readField("Decimal"));
					readingFormat.setValid(true);
				}
				
				if(readField("Integer") == null){
					readingFormat.setValid(false);
					continue;
				}else{
					readingFormat.setInteger((int) readField("Integer"));
					readingFormat.setValid(true);
				}
	
				if(readField("Percent") == null){
					readingFormat.setValid(false);
					continue;
				}else{
					readingFormat.setPercent((double) readField("Percent"));
					readingFormat.setString((String) readField("String"));
					readingFormat.setText((String) readText("Text"));
					readingFormat.setValid(true);
				}
				
				if(readingFormat.isValid()){
					readingFormatQueue.add(readingFormat);
					form.clearAll();
				}
	

			}
			if (select == 1) {
				System.out.println(" Array have elements: " + readingFormatQueue.size());
				
				for(int i = 0; i < readingFormatQueue.size(); i++){
					
					//Writing the Date
					Date currentDate = readingFormatQueue.get(i).getData(0);
					DateFormat dateformat;
					dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
					System.out.println(dateformat.format(currentDate));
					logs.writeLine(dateformat.format(currentDate));
					
					//Writing the time
					Date time = readingFormatQueue.get(i).getTime(0);
					dateformat = DateFormat.getTimeInstance(DateFormat.SHORT);
					System.out.println(dateformat.format(time));
					logs.writeLine(dateformat.format(time));
					
					//Writing Currency
					System.out.println(readingFormatQueue.get(i).getCurrency(0));
					logs.writeString(String.valueOf(readingFormatQueue.get(i).getCurrency(0)));
					logs.writeString("\n");
					
					//Writing Decimal
					System.out.println(readingFormatQueue.get(i).getDecimal(0));
					logs.writeString(String.valueOf(readingFormatQueue.get(i).getDecimal(0)));
					logs.writeString("\n");
					
					//Writing Integer
					System.out.println(readingFormatQueue.get(i).getInteger(0));
					logs.writeString(String.valueOf(readingFormatQueue.get(i).getInteger(0)));
					logs.writeString("\n");
					
					//Writing Percent
					System.out.println(readingFormatQueue.get(i).getPercent(0));
					logs.writeString(String.valueOf(readingFormatQueue.get(i).getPercent(0)));
					logs.writeString("\n");
					
					//Writing String
					System.out.println(readingFormatQueue.get(i).getString(0));
					logs.writeLine(readingFormatQueue.get(i).getString(0));
					//Writing Text Field
					System.out.println(readingFormatQueue.get(i).getText(0));
					logs.writeLine(readingFormatQueue.get(i).getText(0));
					logs.writeLine("");
					
				//}
				System.out.println(" ");

			}
				logs.close();
			break;	
				//form.close();
				//form.hide();
			}

		}
		
	}
	/*closing the forms*/
	
	public void close(){
		//form.hide();
		logs.close();
		form.close();
	}
}