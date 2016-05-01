package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import model.Person;

public class TablePanel extends JPanel {
	private static final long serialVersionUID = -420743314001223183L;
	private JTable table;
	private PersonTableModel tableModel;
	private JPopupMenu popup;
	private PersonTableListener personListener;
	public TablePanel() {
		// Setting the layout
		setLayout(new BorderLayout());

		// initializing instances
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();

		// setting components
		
		//adding components to Panel
		add(new JScrollPane(table), BorderLayout.CENTER);
		

		// setting panel
		Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		Border innerBorder = BorderFactory.createTitledBorder("Cutomer List");
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		//table.setAutoCreateRowSorter(true);
		// setting component properties
		JMenuItem selectCustomerItem = new JMenuItem("Select Customer");
		popup.add(selectCustomerItem);

		JMenuItem newProductItem = new JMenuItem("New Product Purchase");
		popup.add(newProductItem);

		JMenuItem removeItem = new JMenuItem("Delete");
		popup.add(removeItem);

		// adding Listeners to the components
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent ev) {
				if (ev.getButton() == MouseEvent.BUTTON3) {
					int row = table.rowAtPoint(ev.getPoint());
					table.getSelectionModel().setSelectionInterval(row, row);
					popup.show(table, ev.getX(), ev.getY());
				}

				if (ev.getButton() == MouseEvent.BUTTON1) {
					if (personListener != null) {
						int row = table.getSelectedRow();
						personListener.selectCustomer(row);
					}
				}
			}

		});
		
		removeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (personListener != null) {
					personListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
			}
		});

		newProductItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				String personName = (String) tableModel.getValueAt(row, 0);
				if (personListener != null) {
					personListener.purchaseNewProduct(row, personName);
				}

			}
		});


		
		

	}

	public void setData(List<Person> db) {
		tableModel.setData(db);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();

	}

	public void setPersonTableListener(PersonTableListener personListener) {
		this.personListener = personListener;
	}

}
