
/**
 * This is the ButtonListener class, that takes care of the 
 * event for the buttons for producer class
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonListener implements ActionListener {

	Producer producer;
	BinaryReader orderQueue;

	ButtonListener(Producer producer) {
		this.producer = producer;
	}

	/*On OK button click*/
	@Override
	public void actionPerformed(ActionEvent e) {
		producer.getDataField();
		if (e.getActionCommand().equals("OK")) {
			if (producer.getFormValidation().isValidForm(producer.getDataField())) {
				producer.onClick = true;
			} else {
				producer.DatefocusLost();
				producer.TimefocusLost();
				producer.OrderfocusLost();
				producer.QuantityfocusLost();
				producer.ItemfocusLost();
				producer.AddressfocusLost();
			}
		}

		/*On Quit button click*/
		if (e.getActionCommand().equals("Quit")) {
			producer.stopThead = true;
			producer.getFrame().setVisible(false);
			Thread.currentThread().interrupt();

		}

	}
}
