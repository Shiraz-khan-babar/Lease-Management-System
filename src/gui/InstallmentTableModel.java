package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Installment;

public class InstallmentTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -620318332462818364L;
	private List<Installment> db;
	private String[] colNames= {"S.No","Price","Date"}; 
	public InstallmentTableModel() {
		super();
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		if(db==null){
			return 0;
		}
		return db.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	public void setData(List<Installment> db){
		this.db = db;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Installment installment = db.get(row);
		switch (col) {
		case 0:
			return ++row;
		case 1:
			return installment.getPrice();
		case 2:
			return installment.getInstallmentDate();
		}
		return null;
	}
	

}
