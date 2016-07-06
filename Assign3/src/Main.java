/**
 * This is the Main class, that launch the application
 * for checking the data
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {

		/* If the application can't be launch 
		 * it will catch and exception
		 * */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					AcmeDistributing window = new AcmeDistributing();
					window.getFrame().setVisible(true);

				} catch (Exception e) {
					System.out.println(" Unable to load");
					e.printStackTrace();
				}
			}
		});

	}

}
