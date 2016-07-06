import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is the Main class, that instantiate the CheckedForm class to run the
 * program
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */
public class Main {

	public static void main(String[] args) {
		
		/*
		 * Part A
		 * 
		 * */
		
	/*	CheckedForm partA = new CheckedForm();

		partA.addField("Date", "Invalid Date", Type.DATE);
		partA.addText("Date", "Invalid Date");

		// Field for displaying the Date
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
		String DateToStr = format.format(curDate);
		partA.form.writeString("Date", DateToStr);

		partA.addField("Time", "Invalid Time", Type.TIME);
		partA.addText("Time", "Invalid Time");

		DateToStr = DateFormat.getTimeInstance(DateFormat.SHORT).format(curDate);
		partA.form.writeString("Time", DateToStr);

		partA.addField("Currency", "Invalid Currency", Type.CURRENCY);
		partA.addText("Currency", "Invalid Currency");

		partA.addField("Decimal", "Invalid Decimal", Type.DECIMAL);
		partA.addText("Decimal", "Invalid Decimal");

		partA.addField("Integer", "Invalid Integer", Type.INTEGER);
		partA.addText("Integer", "Invalid Integer");

		partA.addField("Percent", "Invalid Percent", Type.PERCENT);
		partA.addText("Percent", "Invalid Percent");

		partA.addField("String", "Invalid String", Type.STRING);
		partA.addField("Text", "Text", Type.STRING);

		partA.accept();
		partA.close(); */

		/* Part B */
		
		OrderForm partB = new OrderForm();
		partB.close();

	}

}
