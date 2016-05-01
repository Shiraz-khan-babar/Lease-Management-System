package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Product implements Serializable {

	private static final long serialVersionUID = -2255245512998678433L;
	private static int count = 2141;
	private final int id;
	private String name;
	private double price;
	private int installmentsMonths;
	private int profit;
	private boolean state;
	private LocalDate purchaseDate;
	private String reference;
	private List<Installment> productInstallment;
	private double totalPrice;

	public Product(String name, double price, int installmentsMonth, int profit, String reference,
			LocalDate purchaseDate) {
		this.name = name;
		this.price = price;
		this.installmentsMonths = installmentsMonth;
		this.reference = reference;
		this.profit = profit;
		this.purchaseDate = purchaseDate;
		this.state = true;
		this.productInstallment = new LinkedList<Installment>();
		this.totalPrice = getTotalPrice();
		id = count;
		count++;
	}

	public double getTotalPrice() {
		return Math.ceil(((price / 100) * profit) + price);
	}

	public double singleInstallmentPrice() {

		double sInstallment = totalPrice / installmentsMonths;
		return Math.ceil(sInstallment);
	}

	@Override
	public String toString() {
		return String.format(
				"Name:            %s\nPrice:            %.2f\nTotal Price:   %.2f\nS.Instament:  %.2f\nIns Done:          %d\nIns.Required:   %d",
				this.name, this.price, this.totalPrice, singleInstallmentPrice(), productInstallment.size(),
				installmentsMonths - productInstallment.size());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInstallmentsMonths() {
		return installmentsMonths;
	}

	public void setInstallmentsMonths(int installmentsMonths) {
		this.installmentsMonths = installmentsMonths;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<Installment> getProductInstallment() {
		return productInstallment;
	}

	public void setProductInstallment(List<Installment> productInstallment) {
		this.productInstallment = productInstallment;
	}

	public int getId() {
		return id;
	}

	public void addInstallment(Installment installment) {
		productInstallment.add(installment);
	}

	public void removeInstallment(int row) {
		productInstallment.remove(row);
	}

	public Installment getInstallment(int row) {
		return productInstallment.get(row);
	}

	public double getRequiredBlance() {
		if (productInstallment.size() != 0) {
			int paidBalance = 0;
			for (Installment installment : productInstallment) {
				paidBalance += installment.getPrice();
			}
			return price - paidBalance;
		}
		return price;
	}

	public boolean isActive() {
		double required = 0;
		for (Installment installment : productInstallment) {
			required += installment.getPrice();
		}
		required = totalPrice - required;
		if (required <= 0) {
			return false;
		}
		return true;
	}
}
