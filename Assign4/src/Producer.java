
/**
 * This is the AcmeDistributing form class, this is the GUI
 * that the user use to input or change the information that is 
 * display
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Producer extends JFrame implements Runnable {

	/* Field Variables */
	private BufferQueue<Order> queue = null;
	
	boolean onClick = false;
	boolean stopThead = false;

	FieldListener fieldListener = new FieldListener(this);
	ButtonListener buttonListener = new ButtonListener(this);

	JButton btnOk = new JButton("OK");
	JButton btnQuit = new JButton("Quit");

	private static final long serialVersionUID = 1L;
	private FormValidation valid = new FormValidation();
	private String[] dataFieldList = new String[6];
	private JFrame frame;
	private JTextField dateTextField;
	private JTextField TimeTextField;
	private JTextField QuantityTextField;
	private JTextField OrderTextField;
	private JTextField ItemTextField;
	private JTextArea addressTextArea;

	private Order orderData = new Order();

	final JLabel lblDate = new JLabel("Date");
	final JLabel lblOrder = new JLabel("Order #");
	final JLabel lblQuantity = new JLabel("Quantity");
	final JLabel lblTime = new JLabel("Time");
	final JLabel lblItemNumber = new JLabel("Item #");
	final JLabel lblAddress = new JLabel("Address");

	/* Default Constructor */
	public Producer(BufferQueue<Order> queue) {
		this.queue = queue;
		initialize();
		getFrame().setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame("Producer"));
		getFrame().setBounds(100, 100, 650, 400);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);

		// Date: Information
		lblDate.setBounds(24, 25, 61, 16);
		getFrame().getContentPane().add(lblDate);
		dateTextField = new JTextField();
		/* When the field lose focus it checks the events */
		dateTextField.addFocusListener(fieldListener);
		dateTextField.setBounds(97, 19, 197, 28);
		getFrame().getContentPane().add(dateTextField);
		dateTextField.setColumns(10);

		/* Order information */
		lblOrder.setBounds(24, 68, 61, 16);
		getFrame().getContentPane().add(lblOrder);
		OrderTextField = new JTextField();

		/* When the field lose focus it checks the events */
		OrderTextField.addFocusListener(fieldListener);

		OrderTextField.setBounds(97, 59, 197, 28);
		getFrame().getContentPane().add(OrderTextField);
		OrderTextField.setColumns(10);

		/* Quantity information */
		lblQuantity.setBounds(24, 103, 61, 16);
		getFrame().getContentPane().add(lblQuantity);
		QuantityTextField = new JTextField();

		/* When the field lose focus it checks the events */
		QuantityTextField.addFocusListener(fieldListener);
		QuantityTextField.setBounds(97, 102, 197, 28);
		getFrame().getContentPane().add(QuantityTextField);
		QuantityTextField.setColumns(10);

		/* TIme information */
		lblTime.setBounds(331, 25, 61, 16);
		getFrame().getContentPane().add(lblTime);
		TimeTextField = new JTextField();

		/* When the field lose focus it checks the events */
		TimeTextField.addFocusListener(fieldListener);
		TimeTextField.setBounds(389, 19, 164, 28);
		getFrame().getContentPane().add(TimeTextField);
		TimeTextField.setColumns(10);

		/* ItemNumber information */
		lblItemNumber.setBounds(331, 68, 61, 16);
		getFrame().getContentPane().add(lblItemNumber);
		ItemTextField = new JTextField();

		/* When the field lose focus it checks the events */
		ItemTextField.addFocusListener(fieldListener);
		ItemTextField.setBounds(399, 62, 154, 28);
		getFrame().getContentPane().add(ItemTextField);
		ItemTextField.setColumns(10);

		/* Address information */
		lblAddress.setBounds(24, 209, 61, 16);
		getFrame().getContentPane().add(lblAddress);
		addressTextArea = new JTextArea();

		/* When the field lose focus it checks the events */
		addressTextArea.addFocusListener(fieldListener);
		addressTextArea.setBounds(97, 209, 454, 96);
		getFrame().getContentPane().add(addressTextArea);
		// JButton btnOk = new JButton("OK");
		frame.getRootPane().setDefaultButton(btnOk);

		/* OK button events on User input */
		btnOk.addActionListener(buttonListener);
		btnOk.setBounds(236, 328, 117, 29);
		getFrame().getContentPane().add(btnOk);

		/* Quit button */
		btnQuit.addActionListener(buttonListener);
		btnQuit.setBounds(376, 328, 117, 29);
		getFrame().getContentPane().add(btnQuit);

	}
	
	/* Clears all the data in the display field on the form*/
	public void clearField(){
		dateTextField.setText("");
		TimeTextField.setText("");
		OrderTextField.setText("");
		QuantityTextField.setText("");
		ItemTextField.setText("");
		addressTextArea.setText("");
	}

	public void addData() {
		// Date:
		Date curDate = new DateFormating().getDateFormat(dateTextField.getText());
		orderData.setDate(curDate);
		// Time
		Date time = new DateFormating().getTimeFormat(TimeTextField.getText());
		orderData.setTime(time);
		// Order
		orderData.setOrderNumber(Integer.parseInt(OrderTextField.getText()));
		// Quantity
		orderData.setQuantity(Integer.parseInt(QuantityTextField.getText()));
		// Item
		orderData.setItemNumber(ItemTextField.getText());
		// Text Area Address
		orderData.setAddress(addressTextArea.getText());
	}

	public String[] getDataField() {
		dataFieldList[0] = dateTextField.getText();
		dataFieldList[1] = TimeTextField.getText();
		dataFieldList[2] = QuantityTextField.getText();
		dataFieldList[3] = OrderTextField.getText();
		dataFieldList[4] = ItemTextField.getText();
		dataFieldList[5] = addressTextArea.getText();
		return dataFieldList;
	}

	public Order getOrderData() {
		addData();
		return this.orderData;
	}

	public FormValidation getFormValidation() {
		return this.valid;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void OrderfocusLost() {
		if (valid.isValidInteger(OrderTextField.getText().trim())) {
			lblOrder.setForeground(Color.black);
		} else {
			lblOrder.setForeground(Color.red);
		}
	}

	public void DatefocusLost() {
		if (valid.isValidDate(dateTextField.getText().trim())) {
			lblDate.setForeground(Color.black);
		} else {
			lblDate.setForeground(Color.red);
		}
	}

	public void QuantityfocusLost() {
		if (valid.isValidInteger(QuantityTextField.getText().trim())) {
			lblQuantity.setForeground(Color.black);
		} else {
			lblQuantity.setForeground(Color.red);
		}
	}

	public void TimefocusLost() {
		if (valid.isValidTime(TimeTextField.getText().trim())) {
			lblTime.setForeground(Color.black);
		} else {
			lblTime.setForeground(Color.red);
		}
	}

	public void ItemfocusLost() {
		if (valid.isValidItemNumber(ItemTextField.getText().trim())) {
			lblItemNumber.setForeground(Color.black);
		} else {
			lblItemNumber.setForeground(Color.red);
		}
	}

	public void AddressfocusLost() {
		if (valid.isValidAddress(addressTextArea.getText())) {
			lblAddress.setForeground(Color.black);
		} else {
			lblAddress.setForeground(Color.red);
		}
	}
	
	/* When the thread start this gets run
	 * it gets the order from the clerk to be added in 
	 * in the queue, if the queue is full it will
	 * wait/sleep until the Consumer class dequeue something in the 
	 * queue. 
	 * */

	@Override
	public void run() {
		/*if the user pushes quit, the thread will stop*/
		while (!stopThead) {
			try {				
				if(onClick){
					addData();
					queue.put(getOrderData());
					System.out.println("Queue size: " + queue.getSize());
					clearField();
					onClick = false;
				}
					Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Producer interrupted while sleeping");
				//e.printStackTrace();
			}
			System.out.println("Producer Thread Running: ");	
		}
		
	}


}