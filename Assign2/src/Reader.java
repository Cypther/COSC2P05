
/**
 * This is the Read class, that reads in the 
 * file and parse it and creates a persistent objects
 * for the data
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import BasicIO.ASCIIDataFile;

public class Reader {

	/* Class Variables */
	private ArrayList<Formatting> readingFormatQueue;

	/* Default Constructor */
	public Reader() {

		// Getting the client file to read
		ASCIIDataFile file = new ASCIIDataFile("orders.txt");
		readingFormatQueue = new ArrayList<Formatting>();

		List<String> items = null;

		String readFile = null;

		Formatting readingFormat = new Formatting();

		// while not until the end of file
		while (file.isEOF() != true) {
			readFile = file.readString();

			// breaking the string into and arraylist
			if (readFile != null && !readFile.trim().isEmpty()) {
				items = Arrays.asList(readFile.split("\\s+"));
			}

			// checking if the string is not empty
			if (!items.get(0).isEmpty()) {

				// adding to the persistent object Data fields
				if (items.get(0).compareTo("Date:") == 0) {
					DateFormat dateformat;
					Calendar myCal = Calendar.getInstance();
					// myCal.set( theYear, theMonth, theDay );
					Scanner scanDate = new Scanner(items.get(1)).useDelimiter("/");
					myCal.set(scanDate.nextInt(), scanDate.nextInt() - 1, scanDate.nextInt());
					dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
					Date curDate = myCal.getTime();
					// System.out.println(dateformat.format(curDate));
					readingFormat.setData(curDate);
				}
				// adding to the persistent object Time fields
				if (items.get(0).compareTo("Time:") == 0) {
					DateFormat dateformat;
					dateformat = DateFormat.getTimeInstance(DateFormat.SHORT);
					Scanner scanTime = new Scanner(items.get(1)).useDelimiter(":| ");
					Date time = null;
					if (items.get(2).contains("PM")) {
						time = new Date(0, 0, 0, scanTime.nextInt() + 12, scanTime.nextInt());
					} else {
						time = new Date(0, 0, 0, scanTime.nextInt(), scanTime.nextInt());
					}
					// System.out.println(dateformat.format(time));
					readingFormat.setTime(time);
				}
				// adding to the persistent object Integer fields
				if (items.get(0).compareTo("Integer:") == 0) {
					readingFormat.setInteger(Integer.parseInt(items.get(1)));
					// System.out.println(items.get(1));
				}
				// adding to the persistent object String fields
				if (items.get(0).compareTo("String:") == 0) {
					readingFormat.setString(items.get(1));
					// System.out.println(items.get(1));
				}
				// adding to the persistent object Text fields
				if (items.get(0).compareTo("Text:") == 0) {
					String text = "";
					for (int i = 1; i < items.size(); i++) {
						text = text + items.get(i) + " ";
					}
					readingFormat.setText(text);
				}

			}

			// checking if it's not empty add to the array list
			if (readFile.trim().isEmpty()) {
				readingFormatQueue.add(readingFormat);
				if (readFile != null) {
					readingFormat = new Formatting();
				}

			}

		}

	}

	//return the array list 
	public ArrayList<Formatting> getFormatList() {
		return this.readingFormatQueue;
	}

}
