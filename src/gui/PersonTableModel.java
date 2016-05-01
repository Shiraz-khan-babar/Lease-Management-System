package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Person;

public class PersonTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 6040239047016425144L;
	
	private List<Person> db;
	private String[] colNames = {"Name","Address","Contact"};
	
	public PersonTableModel(){
		
		super();
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

    
	public void setData(List<Person> db){
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}


	@Override
	public int getRowCount() {
		if(db!=null){
		return db.size();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);
		switch (col) {
		case 0:
			return person.getName();
		case 1:
			return person.getAddress();
		case 2:
			return person.getContact();
		}
		return null;
	}
}
