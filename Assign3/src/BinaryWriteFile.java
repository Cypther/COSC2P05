
/**
 * This is the BinaryWriteFile class, put some bytes in a buffer so we can
   write them. Usually this would be image data or something.
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BinaryWriteFile {

	public BinaryWriteFile(ArrayList<Order> dataList) {
		/* file to create. */
		String fileName = "orders.bin";

		try {

			String bytes = "";
			for (int i = 0; i < dataList.size(); i++) {
				// Date:
				DateFormat dateformat;
				dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
				Date curDate = dataList.get(i).getDate();
				bytes += "Date: " + dateformat.format(curDate);
				bytes += "\n";
				// Time
				DateFormat timeFormat;
				timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
				java.util.Date time = dataList.get(i).getTime();
				bytes += "Time: " + timeFormat.format(time).toString();
				bytes += "\n";
				bytes += "Quantity: " + dataList.get(i).getQuantity();
				bytes += "\n";
				bytes += "ItemNumber: " + dataList.get(i).getItemNumber();
				bytes += "\n";
				bytes += "OrderNumber: " + dataList.get(i).getOrderNumber();
				bytes += "\n";
				bytes += "Address: " + dataList.get(i).getAddress();
				bytes += "\n";
				bytes += "\n";
				
				

			}

			byte[] buffer = bytes.getBytes();
			FileOutputStream outputStream = new FileOutputStream(fileName);
			outputStream.write(buffer);

			/*close files.*/
			outputStream.close();

			/* Changes from print out */
			System.out.println("Saving: ");
			System.out.println("Wrote " + buffer.length + " bytes");
			System.out.println(bytes.toString());
			/* Changes from print out */
			
		} catch (IOException ex) {
			System.out.println("Error writing file '" + fileName + "'");
			// Or we could just do this:
			ex.printStackTrace();
		}
	}

}
