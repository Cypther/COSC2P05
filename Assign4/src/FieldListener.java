/**
 * This is the FieldListener class, that takes care of the 
 * Validation if the field has been in focus then losses focus,
 * check if the field are correctly formatted before the data get
 * stored in the order queue.
 * @author Long Nguyen
 * 
 * @version 1.0 (March 2016) Compiler Version Java 1.7
 */


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class FieldListener implements FocusListener {

	Producer producer;
	
	public FieldListener(Producer producer) {
		this.producer = producer;
	}

	@Override
	public void focusGained(FocusEvent e) {
		producer.DatefocusLost();
		producer.TimefocusLost();
		producer.OrderfocusLost();
		producer.QuantityfocusLost();
		producer.ItemfocusLost();
		producer.AddressfocusLost();
	}

	@Override
	public void focusLost(FocusEvent e) {
		producer.DatefocusLost();
		producer.TimefocusLost();
		producer.OrderfocusLost();
		producer.QuantityfocusLost();
		producer.ItemfocusLost();
		producer.AddressfocusLost();
	}
}