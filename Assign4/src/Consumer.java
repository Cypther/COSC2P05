import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Consumer extends JFrame implements Runnable {

	/* Field Variables */
	private BufferQueue<Order> queue = null;
	Order order;
	boolean onClick = false;
	boolean stopThead = false;
	
	JButton btnOk = new JButton("OK");
	ButtonListener2 buttonListener = new ButtonListener2();

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField dateTextField;
	private JTextField TimeTextField;
	private JTextField QuantityTextField;
	private JTextField OrderTextField;
	private JTextField ItemTextField;
	private JTextArea addressTextArea;

	final JLabel lblDate = new JLabel("Date");
	final JLabel lblOrder = new JLabel("Order #");
	final JLabel lblQuantity = new JLabel("Quantity");
	final JLabel lblTime = new JLabel("Time");
	final JLabel lblItemNumber = new JLabel("Item #");
	final JLabel lblAddress = new JLabel("Address");

	/* Default Constructor */
	public Consumer(BufferQueue<Order> queue) {
		this.queue = queue;
		initialize();
		getFrame().setVisible(true);

	}

	/* Initialize the field with data */
	private void initializeField() {

		order= this.queue.get();
	
		if (order == null) {
			dateTextField.setText("");
			TimeTextField.setText("");
			OrderTextField.setText("");
			ItemTextField.setText("");
			QuantityTextField.setText("");
			addressTextArea.setText("");
			return;
		}

		// 24 hour clock formatter
		DateFormat formatter = new SimpleDateFormat("kk:mm");
		// The Date:
		DateFormat dateformat;
		dateformat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.JAPAN);
		java.util.Date curDate = order.getDate();
		dateTextField.setText(dateformat.format(curDate));
		// The time
		dateformat = DateFormat.getTimeInstance(DateFormat.SHORT);
		java.util.Date time = order.getTime();
		TimeTextField.setText(formatter.format(time));

		OrderTextField.setText(Integer.toString(order.getOrderNumber()));
		ItemTextField.setText(order.getItemNumber());
		QuantityTextField.setText(Integer.toString(order.getQuantity()));
		addressTextArea.setText(order.getAddress());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame("Consumer"));
		getFrame().setBounds(100, 100, 650, 400);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);

		// Date: Information
		lblDate.setBounds(24, 25, 61, 16);
		getFrame().getContentPane().add(lblDate);
		dateTextField = new JTextField();

		dateTextField.setBounds(97, 19, 197, 28);
		dateTextField.setEditable(false);
		getFrame().getContentPane().add(dateTextField);
		dateTextField.setColumns(10);

		/* Order information */
		lblOrder.setBounds(24, 68, 61, 16);
		getFrame().getContentPane().add(lblOrder);
		OrderTextField = new JTextField();
		OrderTextField.setEditable(false);
		OrderTextField.setBounds(97, 59, 197, 28);
		getFrame().getContentPane().add(OrderTextField);
		OrderTextField.setColumns(10);

		/* Quantity information */
		lblQuantity.setBounds(24, 103, 61, 16);
		getFrame().getContentPane().add(lblQuantity);
		QuantityTextField = new JTextField();
		QuantityTextField.setEditable(false);

		/* When the field lose focus it checks the events */
		QuantityTextField.setBounds(97, 102, 197, 28);
		getFrame().getContentPane().add(QuantityTextField);
		QuantityTextField.setColumns(10);

		/* TIme information */
		lblTime.setBounds(331, 25, 61, 16);
		getFrame().getContentPane().add(lblTime);
		TimeTextField = new JTextField();

		/* When the field lose focus it checks the events */
		TimeTextField.setBounds(389, 19, 164, 28);
		getFrame().getContentPane().add(TimeTextField);
		TimeTextField.setEditable(false);
		TimeTextField.setColumns(10);

		/* ItemNumber information */
		lblItemNumber.setBounds(331, 68, 61, 16);
		getFrame().getContentPane().add(lblItemNumber);
		ItemTextField = new JTextField();

		/* When the field lose focus it checks the events */
		ItemTextField.setBounds(399, 62, 154, 28);
		ItemTextField.setEditable(false);
		getFrame().getContentPane().add(ItemTextField);
		ItemTextField.setColumns(10);

		/* Address information */
		lblAddress.setBounds(24, 209, 61, 16);
		getFrame().getContentPane().add(lblAddress);
		addressTextArea = new JTextArea();

		/* When the field lose focus it checks the events */
		addressTextArea.setBounds(97, 209, 454, 96);
		getFrame().getContentPane().add(addressTextArea);
		addressTextArea.setEditable(false);
		//JButton btnOk = new JButton("OK");
		frame.getRootPane().setDefaultButton(btnOk);

		/* OK button events on User input */
		btnOk.addActionListener(buttonListener);
		btnOk.setBounds(236, 328, 117, 29);
		getFrame().getContentPane().add(btnOk);

		/* Quit button */
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(buttonListener);
		btnQuit.setBounds(376, 328, 117, 29);
		getFrame().getContentPane().add(btnQuit);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/* ButtonListener Class for Consumer */
	class ButtonListener2 implements ActionListener {

		ButtonListener2() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("OK")) {
				onClick = true;
			}

			if (e.getActionCommand().equals("Quit")) {
				stopThead = true;	
				getFrame().setVisible(false);

			}

		}

	}

	/* When the thread start this gets run
	 * It initialized the field if there are orders
	 * in the queue, if the queue is empty it will
	 * wait/sleep until the Producer class puts something in the 
	 * queue. 
	 * */
	
	@Override
	public void run() {
		 /*if the user pushes quit, the thread will stop*/
			while(!stopThead){
			try {
				if(onClick){
					System.out.println("Queue size: " + queue.getSize());
					initializeField();
					onClick = false;
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				 System.out.println("Consumer interrupted while sleeping");
				//e.printStackTrace();
			}
			 System.out.println("Consumer Thread Running: ");
		}
	}

}
