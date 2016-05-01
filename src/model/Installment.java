package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Installment implements Serializable{

	private static final long serialVersionUID = -3593868511356326552L;
	private static int count = 1141;
	private final int id;
	private final LocalDate installmentDate;
	private final double price;
	
		public Installment(LocalDate installmentDate,double price){
			this.installmentDate = installmentDate;
			this.price = price;
			this.id = count;
			count++;
		}

		public int getId() {
			return id;
		}
		
		public double getPrice(){
			return this.price;
		}

		public LocalDate getInstallmentDate() {
			return installmentDate;
		}


		public static int getCount() {
			return count;
		}
		
}
