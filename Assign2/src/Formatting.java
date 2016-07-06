/**
 * This is the Formatting class, that keeps
 * tracks of the textFields, label and types
 * from the fields
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 * */

import java.util.ArrayList;
import java.util.Date;

public class Formatting implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Date> data;
	private ArrayList<Date> time;
	private ArrayList<Double> currency;
	private ArrayList<Double> decimal;
	private ArrayList<Integer> integer;
	private ArrayList<Double> percent;
	private ArrayList<String> string;
	private ArrayList<String> text;

	private boolean valid = false;

	/* Default Constructor */
	public Formatting() {

		this.data = new ArrayList<Date>();
		this.time = new ArrayList<Date>();
		this.currency = new ArrayList<Double>();
		this.decimal = new ArrayList<Double>();
		this.integer = new ArrayList<Integer>();
		this.percent = new ArrayList<Double>();
		this.string = new ArrayList<String>();
		this.text = new ArrayList<String>();
		this.valid = false;

	}

	/* Getters and Setters */

	public Date getData(int index) {
		return data.get(index);
	}

	public void setData(Date data) {
		this.data.add(data);
	}

	public Date getTime(int index) {
		return time.get(index);
	}

	public void setTime(Date time) {
		this.time.add(time);
	}

	public double getCurrency(int index) {
		return currency.get(index);
	}

	public void setCurrency(double currency) {
		this.currency.add(currency);
	}

	public double getDecimal(int index) {
		return decimal.get(index);
	}

	public void setDecimal(double decimal) {
		this.decimal.add(decimal);
	}

	public int getInteger(int index) {
		return integer.get(index);
	}

	public void setInteger(int integer) {
		this.integer.add(integer);
	}

	public double getPercent(int index) {
		return percent.get(index);
	}

	public void setPercent(double percent) {
		this.percent.add(percent);
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getString(int index) {
		return string.get(index);
	}

	public void setString(String string) {
		this.string.add(string);
	}

	public String getText(int index) {
		return text.get(index);
	}

	public void setText(String text) {
		this.text.add(text);
	}

}
