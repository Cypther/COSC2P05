/**
 * This is the AcmeDistributing form class, this is the GUI
 * that the user use to input or change the information that is 
 * display
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class AcmeDistributing extends JFrame {

	/* Field Variables */
	private static final long serialVersionUID = 1L;
	private ArrayList<Order> dataList = new ArrayList<Order>();
	private FormValidation valid = new FormValidation();
	private String[] dataFieldList = new String[6];
	private JFrame frame;
	private JTextField dateTextField;
	private JTextField TimeTextField;
	private JTextField QuantityTextField;
	private JTextField OrderTextField;
	private JTextField ItemTextField;
	private JTextArea addressTextArea;
	private int dataIndex = 0;

	/* Default Constructor */
	public AcmeDistributing() {
		initialize();
		try {
			BinaryReader reading = new BinaryReader();
			this.dataList = reading.getFormatList();
			initializeField(dataIndex);

		} catch (Exception e) {

			System.out.println("Null Pointer File not fould");
			System.exit(0);
		}
	}

	/* Initialize the field with data */
	private void initializeField(int dataIndex) {
		// 24 hour clock formatter
		DateFormat formatter = new SimpleDateFormat("kk:mm"); 
		// The Date:
		DateFormat dateformat;
		dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
		java.util.Date curDate = dataList.get(dataIndex).getDate();
		dateTextField.setText(dateformat.format(curDate));
		// The time
		dateformat = DateFormat.getTimeInstance(DateFormat.SHORT);
		java.util.Date time = dataList.get(dataIndex).getTime();
		TimeTextField.setText(formatter.format(time));
		
		OrderTextField.setText(Integer.toString(dataList.get(dataIndex).getOrderNumber()));
		ItemTextField.setText(dataList.get(dataIndex).getItemNumber());
		QuantityTextField.setText(Integer.toString(dataList.get(dataIndex).getQuantity()));
		addressTextArea.setText(dataList.get(dataIndex).getAddress());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame("AcmeDistributing"));
		getFrame().setBounds(100, 100, 650, 400);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		// Date: Information
		final JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(24, 25, 61, 16);
		getFrame().getContentPane().add(lblDate);
		dateTextField = new JTextField();
		
		dateTextField.addFocusListener(new FieldListener());
		
		/*When the field lose focus it checks the events*/
		dateTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (valid.isValidDate(dateTextField.getText().trim())) {
					lblDate.setForeground(Color.black);
				} else {
					lblDate.setForeground(Color.red);
				}
			}
		});
		dateTextField.setBounds(97, 19, 197, 28);
		getFrame().getContentPane().add(dateTextField);
		dateTextField.setColumns(10);

		/* Order information */
		final JLabel lblOrder = new JLabel("Order #");
		lblOrder.setBounds(24, 68, 61, 16);
		getFrame().getContentPane().add(lblOrder);
		OrderTextField = new JTextField();
		
		/*When the field lose focus it checks the events*/
		OrderTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (valid.isValidInteger(OrderTextField.getText().trim())) {
					lblOrder.setForeground(Color.black);
				} else {
					lblOrder.setForeground(Color.red);
				}
			}
		});
		OrderTextField.setBounds(97, 59, 197, 28);
		getFrame().getContentPane().add(OrderTextField);
		OrderTextField.setColumns(10);

		/* Quantity information */
		final JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(24, 103, 61, 16);
		getFrame().getContentPane().add(lblQuantity);
		QuantityTextField = new JTextField();
		
		/*When the field lose focus it checks the events*/
		QuantityTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if (valid.isValidInteger(QuantityTextField.getText().trim())) {
					lblQuantity.setForeground(Color.black);
				} else {
					lblQuantity.setForeground(Color.red);
				}

			}
		});
		QuantityTextField.setBounds(97, 102, 197, 28);
		getFrame().getContentPane().add(QuantityTextField);
		QuantityTextField.setColumns(10);

		/* TIme information */
		final JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(331, 25, 61, 16);
		getFrame().getContentPane().add(lblTime);
		TimeTextField = new JTextField();
		
		/*When the field lose focus it checks the events*/
		TimeTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (valid.isValidTime(TimeTextField.getText().trim())) {
					lblTime.setForeground(Color.black);
				} else {
					lblTime.setForeground(Color.red);
				}

			}
		});
		TimeTextField.setBounds(389, 19, 164, 28);
		getFrame().getContentPane().add(TimeTextField);
		TimeTextField.setColumns(10);

		/* ItemNumber information */
		final JLabel lblItemNumber = new JLabel("Item #");
		lblItemNumber.setBounds(331, 68, 61, 16);
		getFrame().getContentPane().add(lblItemNumber);
		ItemTextField = new JTextField();
		
		/*When the field lose focus it checks the events*/
		ItemTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (valid.isValidItemNumber(ItemTextField.getText().trim())) {
					lblItemNumber.setForeground(Color.black);
				} else {
					lblItemNumber.setForeground(Color.red);
				}
			}
		});
		ItemTextField.setBounds(399, 62, 154, 28);
		getFrame().getContentPane().add(ItemTextField);
		ItemTextField.setColumns(10);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDataField();
				if (valid.isValidForm(dataFieldList)) {
					BinaryWriteFile saving = new BinaryWriteFile(dataList);
					getFrame().dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		btnQuit.setBounds(376, 328, 117, 29);
		getFrame().getContentPane().add(btnQuit);

		/* Address information */
		final JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(24, 209, 61, 16);
		getFrame().getContentPane().add(lblAddress);
		addressTextArea = new JTextArea();
		
		/*When the field lose focus it checks the events*/
		addressTextArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if (valid.isValidAddress(addressTextArea.getText())) {
					lblAddress.setForeground(Color.black);
				} else {
					lblAddress.setForeground(Color.red);
				}

			}
		});
		addressTextArea.setBounds(97, 209, 454, 96);
		getFrame().getContentPane().add(addressTextArea);
		JButton btnOk = new JButton("OK");
		frame.getRootPane().setDefaultButton(btnOk);

		/* OK button events on User input */
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getDataField();
				if (valid.isValidForm(dataFieldList)) {
					updateData(dataIndex);
					if (dataIndex < dataList.size() - 1) {
						dataIndex++;
					} else {
						dataIndex = 0;
					}
				} else {
					return;
				}
				initializeField(dataIndex);
			}
		});
		btnOk.setBounds(236, 328, 117, 29);
		getFrame().getContentPane().add(btnOk);
	}

	public void getDataField() {
		dataFieldList[0] = dateTextField.getText();
		dataFieldList[1] = TimeTextField.getText();
		dataFieldList[2] = QuantityTextField.getText();
		dataFieldList[3] = OrderTextField.getText();
		dataFieldList[4] = ItemTextField.getText();
		dataFieldList[5] = addressTextArea.getText();
	}

	public void updateData(int index) {
		// Date:
		DateFormat dateformat;
		Calendar myCal = Calendar.getInstance();
		// myCal.set( theYear, theMonth, theDay );
		Scanner scanDate = new Scanner(dateTextField.getText()).useDelimiter("/");
		myCal.set(scanDate.nextInt(), scanDate.nextInt() - 1, scanDate.nextInt());
		dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
		Date curDate = myCal.getTime();
		dataList.get(index).setDate(curDate);
		// Time
		DateFormat timeFormat;
		timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
		Scanner scanTime = new Scanner(TimeTextField.getText()).useDelimiter(":| ");
		Date time = new Date(0, 0, 0, scanTime.nextInt(), scanTime.nextInt());
		//System.out.println("Time: " + time.toString());
		dataList.get(index).setTime(time);
		// Order
		dataList.get(index).setOrderNumber(Integer.parseInt(OrderTextField.getText()));
		// Quantity
		dataList.get(index).setQuantity(Integer.parseInt(QuantityTextField.getText()));
		// Item
		dataList.get(index).setItemNumber(ItemTextField.getText());
		// Text Area
		dataList.get(index).setAddress(addressTextArea.getText());// Address
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

class FieldListener implements FocusListener{
	FieldListener(){}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Black");
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		/*if (valid.isValidDate(((JTextField) e.getSource()).getText().trim())) {
		if (e.getActionCommand().equals("OK")) {
			//lblDate.setForeground(Color.black);
			System.out.println("Black");
		} else {
			//lblDate.setForeground(Color.red);
			System.out.println("Red");
		}*/
		//System.out.println("Red");
		
	}


	
}
