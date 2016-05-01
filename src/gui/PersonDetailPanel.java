package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Installment;
import model.Product;

public class PersonDetailPanel extends VPanel{

	private static final long serialVersionUID = 3732636788416832053L;
	private JTable productTable;
	private JTable installmentTable;
	private JPanel productsPanel;
	private JPanel instalmentPanel;
	private JPopupMenu popup;
	private ProductTableModel productModel;
	private int perRow;
	private ProductTableListener productTableListener;
	private InstallmentTableModel installmentModel;

	public PersonDetailPanel(String title) {
		super(title,0);
		//setting layout
		setLayout(new GridLayout(2,1));
		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);
		//initializing components
		productModel = new ProductTableModel();
		productTable = new JTable(productModel);
		installmentModel = new InstallmentTableModel();
		installmentTable = new JTable(installmentModel);
		popup = new JPopupMenu();
		instalmentPanel = new JPanel();
		productsPanel = new JPanel();
		this.perRow = -1;
		
		//setting components
		productsPanel.setLayout(new BorderLayout());
		productsPanel.setBorder(VPanelBorder.createVPanelBorder("Products", 4));
		
		
		instalmentPanel.setLayout(new BorderLayout());
		instalmentPanel.setBorder(VPanelBorder.createVPanelBorder("Installments", 4));
		
		//adding components to Panel
		productsPanel.add(new JScrollPane(productTable),BorderLayout.CENTER);
		instalmentPanel.add(new JScrollPane(installmentTable),BorderLayout.CENTER);
		add(productsPanel);
		add(instalmentPanel);
		
		//setting components properties
		JMenuItem installmentItem = new JMenuItem("Do Installment");
		popup.add(installmentItem);
		
		JMenuItem productDetails = new JMenuItem("Show Details");
		popup.add(productDetails);
		
		//adding listeners to tables
		productTable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent ev){
				if(ev.getButton() == MouseEvent.BUTTON3){
					int row = productTable.rowAtPoint(ev.getPoint());
					productTable.getSelectionModel().setSelectionInterval(row, row  );
					popup.show(productTable, ev.getX(), ev.getY());
				}
				
				if(ev.getButton()==MouseEvent.BUTTON1){
					if(productTableListener!=null){
						int row = productTable.getSelectedRow();
						int proRow = row;
						productTableListener.selectProduct(perRow, proRow);
						
					}
				}
			}
		}
			
		);
	
		installmentItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(productTableListener!=null){
					int row = productTable.getSelectedRow();
					productTableListener.doInstallment(perRow,row );
				}
				
			}
		});
		
		productDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(productTableListener!=null){
					int row = productTable.getSelectedRow();
					productTableListener.showDetails(perRow, row);
				}
				
			}
		});
	}
	
	
	
	public void setData(List<Product> db,int perRow){
		productModel.setData(db);
		this.perRow = perRow;
	}
	
	public void setInsData(List<Installment> db,int perRow){
		if(db!=null){
		installmentModel.setData(db);
		}
		this.perRow = perRow;
	}
	
	public void refresh() {
		productModel.fireTableDataChanged();
	}
	
	public void setProductTableListener(ProductTableListener productTableListener){
		this.productTableListener= productTableListener;
	}
	
	public void refreshIns(){
		installmentModel.fireTableDataChanged();
	}
}
