package gui;

import java.util.EventObject;

public class FormEvent extends EventObject{

	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String nic;
	private String contact;
	private String reference;
	
	public FormEvent(Object arg0, String name, String address, String nic, String contact, String reference) {//TODO:Accept the object which are the source of the event
		super(arg0);
		this.name = name;
		this.nic = nic;
		this.address = address;
		this.contact = contact;
		this.reference = reference;
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getNic() {
		return nic;
	}

	public String getContact() {
		return contact;
	}

	public String getReference() {
		return reference;
	}
	
	

	
}
