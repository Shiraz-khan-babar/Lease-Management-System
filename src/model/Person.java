package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	public static int count = 3141;
	private int id;
	//private final int primaryKey;
	private String name;
	private String address;
	private String nic;
	private String contact;
	private String reference;
	private List<Product> products;
	
	public Person(String name, String address, String nic,
			String contact, String reference) {
		this.name = name;
		this.address = address;
		this.nic =nic;
		this.contact = contact;
		this.reference = reference;
		this.products = new LinkedList<Product>();
		this.id = count;
		count++;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product){
		this.products.add(product);
	}
	
	public void removeProduct(int row){
		this.products.remove(row);
	}
	
	public Product getProduct(int row){
		return this.products.get(row);
	}
}
