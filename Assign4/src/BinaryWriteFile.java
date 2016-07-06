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
import java.util.Queue;

public class BinaryWriteFile {

	public BinaryWriteFile(BufferQueue<Order> dataList) {
		/* file to create. */
		String fileName = "ordersOutPut.bin";

		try {

			String bytes = "";

			// access via new while-loop
			Order object = null;
			while (!dataList.isEmpty()) {

				object = dataList.get();

				// Date:
				DateFormat dateformat;
				dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
				Date curDate = object.getDate();
				bytes += "Date: " + dateformat.format(curDate);
				bytes += "\n";
				// Time
				DateFormat timeFormat;
				timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
				java.util.Date time = object.getTime();
				bytes += "Time: " + timeFormat.format(time).toString();
				bytes += "\n";
				bytes += "Quantity: " + object.getQuantity();
				bytes += "\n";
				bytes += "ItemNumber: " + object.getItemNumber();
				bytes += "\n";
				bytes += "OrderNumber: " + object.getOrderNumber();
				bytes += "\n";
				bytes += "Address: " + object.getAddress();
				bytes += "\n";
				bytes += "\n";

			}

			byte[] buffer = bytes.getBytes();
			FileOutputStream outputStream = new FileOutputStream(fileName);
			outputStream.write(buffer);

			/* close files. */
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
