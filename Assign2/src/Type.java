/**
 * This is the Type class, it's and enumerated type 
 * for checking the data
 * @author Long Nguyen
 * 
 * @version 1.0 (February 2016) Compiler Version Java 1.7
 */

public enum Type {
	
	/* Field Variables */
	DATE("Date", "Invalid Date"),
	TIME("Time", "Invalid Time"),
	CURRENCY("Currency", "Invalid Currency"),
	DECIMAL("Decimal", "Invalid Decimal"),
	INTEGER("Integer", "Invalid Integer"),
	PERCENT("Percent", "Invalid Percent"),
	STRING("String", "Invalid String"),
	TEXT("Text", "Invalid Text");
	
	private final String name;
	private final String label;
	
	/* Enumerater Constructor */
	Type(String name, String label){
		this.name = name;
		this.label = label;
	}
	
	/*Getters and Setters */
	public String getName(){
		return this.name;
	}
	
	public String getLabel(){
		return this.label;
	}

}
