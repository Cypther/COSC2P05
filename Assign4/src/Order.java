/**
 * This is the Order class, holds the information for the
 * Order from the textfile
 * for checking the data
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

import java.util.Date;

public class Order implements java.io.Serializable{
	
	/* Field Variables */
	private static final long serialVersionUID = 1L;
	private Date date;
	private Date time;
	private int quantity;
	private int orderNumber;
	private String itemNumber;
	private String address;
	
	/* Default Constructor */
	public Order(){
		
		this.date = new Date();
		this.time = new Date();
		this.quantity = 0;
		this.orderNumber = 0;
		this.itemNumber = "";
		this.address = "";
		
	}

	/*Getters and Setters */
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String string) {
		this.itemNumber = string;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
