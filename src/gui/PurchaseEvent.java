package gui;

import java.util.EventObject;

public class PurchaseEvent extends EventObject {

	private static final long serialVersionUID = 7941499614195526043L;
	private int row;
	private String name;
	private double price;
	private int installmentMonths;
	private int profit;
	private String reference;
	public PurchaseEvent(Object arg0, int row, String name, double price,
			int installmentMonths, int profit, String reference) {
		super(arg0);
		this.row = row;
		this.name = name;
		this.price = price;
		this.installmentMonths = installmentMonths;
		this.profit = profit;
		this.reference = reference;
		
	}
	
	public int getRow() {
		return row;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getInstallmentMonths() {
		return installmentMonths;
	}
	public int getProfit() {
		return profit;
	}
	public String getReference() {
		return reference;
	}
	
	

}
