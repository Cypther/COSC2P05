/**
 * This is the FieldInformation class, that keeps
 * tracks of the textFields, label and types
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */

class FieldInformation {

	/* Class Variables */
	private String textField;
	private String label;
	private Type kind;

	/* Default Constructor */
	public FieldInformation() {
		this.textField = "";
		this.label = "";
		this.kind = null;
	}
	
	/* Default overloading Constructor */
	public FieldInformation(String textField, String label, Type kind) {
		this.textField = textField;
		this.label = label;
		this.kind = kind;
	}

	/*Gettings and setters*/
	public String getTextField() {
		return textField;
	}

	public void setTextField(String textField) {
		this.textField = textField;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Type getKind() {
		return kind;
	}

	public void setKind(Type kind) {
		this.kind = kind;
	}

}