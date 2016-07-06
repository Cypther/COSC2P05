/**
 * This is the FormaValidation class, that check if the 
 * form fields are valid
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidation {

	/* Default Constructor */
	public FormValidation() {
	}

	/* Check if the address field is not empty */
	public boolean isValidAddress(String input) {
		if (input.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Checking for the Item number if it's valid with regex matching
	 */
	public boolean isValidItemNumber(String input) {

		String itemNumberRegex = "\\d+\\W\\d+\\W\\d+";
		Pattern pattern = Pattern.compile(itemNumberRegex);
		Matcher matcher;

		matcher = pattern.matcher(input);
		return matcher.matches();

	}

	/* Checking if the type is a valid integer */
	public boolean isValidInteger(String numberString) {
		try {
			int number = Integer.parseInt(numberString);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	/* Checking if the Time is a valid with regex 24 hour clock */
	public boolean isValidTime(String timeString) {

		String time24HourRegex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		Pattern pattern = Pattern.compile(time24HourRegex);
		Matcher matcher;
		matcher = pattern.matcher(timeString);
		return matcher.matches();

	}

	/* Checking if the Date is valid */
	public boolean isValidDate(String input) {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");

		if (input == null || !input.matches("\\d{2}/\\d{2}/\\d{2}")) {
			return false;
		}

		try {
			dateFormatter.setLenient(false);
			dateFormatter.parse(input);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/*Checking all the fields if they are valid */
	public boolean isValidForm(String[] formField) {

		while (true) {
			if (!isValidDate(formField[0])) {
				System.out.println("Not Valid " + formField[0]);
				return false;
			}
			if (!isValidTime(formField[1])) {
				System.out.println("Not Valid " + formField[1]);
				return false;
			}
			if (!isValidInteger(formField[2])) {
				System.out.println("Not Valid " + formField[2]);
				return false;
			}
			if (!isValidInteger(formField[3])) {
				System.out.println("Not Valid " + formField[3]);
				return false;
			}
			if (!isValidItemNumber(formField[4])) {
				System.out.println("Not Valid " + formField[4]);
				return false;
			}
			if (!isValidAddress(formField[5])) {
				System.out.println("Not Valid " + formField[5]);
				return false;
			} else {
				return true;
			}
		}

	}

}
