package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import model.Product;

public class ProductTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7463791522316698885L;
	private List<Product> db;
	private String[] colNames= {"Name", "Price","Date","State","S.Ins","T.Price"}; 
	private int perRow;
	
	public  ProductTableModel() 
	{	
		super();
		perRow = -1;
		
	}
	@Override
	public int getColumnCount() {
		return 6;
	}
	
	@Override
	public int getRowCount() {
		if(db== null){
		return 0;
		}
		return db.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<Product> db){
		this.db = db;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Product product = db.get(row);
		switch (col) {
		case 0:
			return product.getName();
		case 1:
			return product.getPrice();
		case 2:
			return product.getPurchaseDate();
		case 3:
			return (product.isState()?"Active":"Inactive");
		case 4:
			return product.singleInstallmentPrice();
		case 5:
			return product.getTotalPrice();
		}
		
		return null;
	}
	public int getPerRow() {
		return perRow;
	}
	public void setPerRow(int perRow) {
		this.perRow = perRow;
	}

}
