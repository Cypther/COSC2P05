/**
 * This is the BinaryReader class, read fills buffer with data and returns
   the number of bytes read (which may be less than the buffer 
   size, but never be more than).
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class BinaryReader {

	/* Class Variables */
	private ArrayList<Order> orderQueue;

	public BinaryReader() {
		// name of the file to open.
		String fileName = "orders.bin";
		orderQueue = new ArrayList<Order>();
		List<String> items = null;
		Order order = new Order();

		// This reference one line at a time
		String lines = null;
		String[] line = null;

		try {
			/* Use this for reading the data */
			byte[] buffer = new byte[10000];

			FileInputStream inputStream = new FileInputStream(fileName);

			int totalBytes = 0;
			int nRead = 0;

			while ((nRead = inputStream.read(buffer)) != -1) {
				

				lines = new String(buffer);
				/* Changes from print out */
				System.out.println(lines.toString());
				line = lines.split("\\r?\\n");
				totalBytes += nRead;

				for (int j = 0; j < line.length - 1; j++) {

					/* breaking the string into and arraylist*/
					if (line[j] != null && !line[j].trim().isEmpty()) {
						items = Arrays.asList(line[j].split("\\s+"));
					}
					/* checking if the string is not empty 
					 * and check for the types in the switch cases */
					if (!items.get(0).isEmpty()) {

						String type = items.get(0).trim();

						switch (type) {
						case "Date:":
							DateFormat dateformat;
							Calendar myCal = Calendar.getInstance();
							Scanner scanDate = new Scanner(items.get(1)).useDelimiter("/");
							myCal.set(scanDate.nextInt(), scanDate.nextInt() - 1, scanDate.nextInt());
							dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
							Date curDate = myCal.getTime();
							order.setDate(curDate);
							break;
						case "Time:":
							DateFormat timeformat;
							timeformat = DateFormat.getTimeInstance(DateFormat.SHORT);
							Scanner scanTime = new Scanner(items.get(1)).useDelimiter(":| ");
							Date time = null;
							if (items.get(2).contains("PM")) {
								time = new Date(0, 0, 0, scanTime.nextInt() + 12, scanTime.nextInt());
							} else {
								time = new Date(0, 0, 0, scanTime.nextInt(), scanTime.nextInt());
							}
							order.setTime(time);
							break;
						case "Quantity:":
							order.setQuantity(Integer.parseInt(items.get(1)));
							break;
						case "ItemNumber:":
							order.setItemNumber(items.get(1));
							break;
						case "OrderNumber:":
							order.setOrderNumber(Integer.parseInt(items.get(1)));
							break;
						case "Address:":
							String text = "";
							for (int i = 1; i < items.size(); i++) {
								text = text + items.get(i) + " ";
							}
							order.setAddress(text);
							break;
						}
					}
					/* checking if it's not empty add to the array list Queue*/
					if (line[j].trim().isEmpty()) {
						orderQueue.add(order);
						if (line[j] != null) {
							order = new Order();
						}

					}

				}

			}

			/*close files*/
			inputStream.close();

			System.out.println("Read " + totalBytes + " bytes");
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			 ex.printStackTrace();
		}
	}

	// return the array list
	public ArrayList<Order> getFormatList() {
		return this.orderQueue;
	}

}