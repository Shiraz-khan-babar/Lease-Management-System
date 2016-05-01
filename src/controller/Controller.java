package controller;

import gui.FormEvent;
import gui.PurchaseEvent;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import model.DataBase;
import model.Installment;
import model.Person;
import model.Product;

public class Controller {
	private DataBase db = new DataBase();
	public List<Person> getPeople(){
		return db.getPeople();
	}	
	
	public void addPerson(FormEvent ev){
		String name = ev.getName();
		String address = ev.getAddress();
		String nic = ev.getNic();
		String contact = ev.getContact();
		String reference = ev.getReference();
		Person person = new Person(name,address,nic,contact,reference);
		db.addPerson(person);
	}
	
	public List<Product> getProducts(int row){
		return db.getPeople().get(row).getProducts();
	}
	
	public void doInstallment(double price,int perRow,int proRow){
		Product pro = db.getPeople().get(perRow).getProduct(proRow);
		pro.addInstallment(new Installment(LocalDate.now(), price));
		if(!db.getPeople().get(perRow).getProduct(proRow).isActive()){
			pro.setState(false);
		}
	}
	
	public String getProductDetails(int perRow,int proRow){
		return db.getPeople().get(perRow).getProduct(proRow).toString();
	}
	public void removeProduct(){
		
	}
	
	public void addProduct(PurchaseEvent pe) {
		LocalDate purchaseDate = LocalDate.now();
		String name = pe.getName();
		int row = pe.getRow();
		double price = pe.getPrice();
		int profit = pe.getProfit();
		String reference = pe.getReference();
		int installmentMonths = pe.getInstallmentMonths();
		
		Product product = new Product(name, price, installmentMonths, profit, reference, purchaseDate);
		
		Person person =db.getPeople().get(row);
		person.addProduct(product);
		
	}
	
	public void saveToFile(File file) throws IOException{
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException{
		db.loadFromFile(file);
	}
	public void removePerson(int row) {
		db.removePerson(row);
		
	}

	public boolean isPersonActive(int perRow,int proRow) {
		return db.getPeople().get(perRow).getProduct(proRow).isActive();
	}
	
}
