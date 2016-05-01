package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PurchaseDialog extends JDialog{
	
	private static final long serialVersionUID = 6256318885986033034L;

	private int row;
	
	private JLabel nameLabel;
	private JTextField nameField;
	
	private JLabel priceLabel;
	private JTextField priceField;
	
	private JLabel installmenMonthstLabel;
	private JSpinner installmentsMonthField;
	
	private JLabel profitLabel;
	private JTextField profitField;
	
	private JLabel referenceLabel;
	private JTextArea referenceArea;

	private JButton okButton;
	private JButton cancelButton;

	private PurchaseListener purchaseListener;
	
	public PurchaseDialog(JFrame parent){
		super(parent,true);
		nameLabel = new JLabel("Name: ");
		nameField = new JTextField(10);
		
		priceLabel = new JLabel("Price: ");
		priceField = new JTextField(10);
		
		profitLabel = new JLabel("Profit %: ");
		profitField = new JTextField(10);
		
		referenceLabel = new JLabel("Reference:");
		referenceArea = new JTextArea(5,10);
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(12, 0, 36, 6); 
		installmenMonthstLabel = new JLabel("Months: ");
		installmentsMonthField = new JSpinner(spinnerModel);
		
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		layoutControl();
		
		
		//adding listeners to buttons
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
				String name = nameField.getText();
				
				String priceText = priceField.getText();
				double price = (priceText.equals("")?0:Double.parseDouble(priceText));
				
				
				int installmentMonths = (Integer)installmentsMonthField.getValue();
				
				String profitText = profitField.getText();
				int profit = Integer.parseInt(profitText);
				
				String reference = referenceArea.getText();
				
				PurchaseEvent pe = new PurchaseEvent(arg0, row, name, price, installmentMonths, profit, reference);
				if(purchaseListener!=null){
					purchaseListener.purchaseEventOccurred(pe);
				}
				setVisible(false);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(PurchaseDialog.this, "Please enter valid input");
				}
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset();
				setVisible(false);
				
			}
		});
		
		setLocationRelativeTo(parent);
		setSize(400, 350);
	}
	
	private void layoutControl() {
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		GridBagConstraints gc = new GridBagConstraints();
		int space =15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space ,space,space,space);
		Border titleBorder = BorderFactory.createTitledBorder("Product Purchasement");
		
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder )); 
		
		controlsPanel.setLayout(new GridBagLayout());
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);
		
		gc.gridy = 0;
		 gc.weightx =1;
		 gc.weighty =1;
		 
		 gc.fill = GridBagConstraints.NONE;
		 //////////////////////first row////////////////////////////
		 gc.gridx = 0;
		 gc.anchor = GridBagConstraints.EAST;
		 gc.insets = rightPadding;
		 controlsPanel.add(nameLabel,gc);
		 
		 gc.gridx++;
		 gc.insets = noPadding;
		 gc.anchor = GridBagConstraints.WEST;
		 controlsPanel.add(nameField, gc);
		 
		 //////////////////////next row/////////////////////////////
		 gc.gridy++;
		 gc.gridx = 0;
		 gc.anchor = GridBagConstraints.EAST;
		 gc.insets = rightPadding;
		 controlsPanel.add(priceLabel,gc);
		 
		 gc.gridx++;
		 gc.anchor = GridBagConstraints.WEST;
		 gc.insets = noPadding;
		 controlsPanel.add(priceField, gc);
		 
		 //////////////////////next row/////////////////////////////
		 gc.gridy++;
		 gc.gridx = 0;
		 gc.anchor = GridBagConstraints.EAST;
		 gc.insets = rightPadding;
		 controlsPanel.add(profitLabel,gc);
		 
		 gc.gridx++;
		 gc.anchor = GridBagConstraints.WEST;
		 gc.insets = noPadding; 
		 controlsPanel.add(profitField, gc);
		 
		 //////////////////////next row/////////////////////////////
		 gc.gridy++;
		 gc.gridx = 0;
		 gc.anchor = GridBagConstraints.EAST;
		 gc.insets = rightPadding;
		 controlsPanel.add(installmenMonthstLabel,gc);
		 
		 gc.gridx++;
		 gc.anchor = GridBagConstraints.WEST;
		 gc.insets = noPadding; 
		 controlsPanel.add(installmentsMonthField, gc);
		 
		 //////////////////////next row/////////////////////////////
		 gc.gridy++;
		 gc.gridx = 0;
		 gc.anchor = GridBagConstraints.EAST;
		 gc.insets = rightPadding;
		 controlsPanel.add(referenceLabel,gc);
		 
		 gc.gridx++;
		 gc.anchor = GridBagConstraints.WEST;
		 gc.insets = noPadding; 
		 controlsPanel.add(new JScrollPane(referenceArea), gc);
		 
		 
		 
		 //////////////////////next row/////////////////////////////		 
		 buttonsPanel.add(okButton);
		 buttonsPanel.add(cancelButton);
		 
		 //setting sub panels
		 Dimension btnSize = cancelButton.getPreferredSize();
		 okButton.setPreferredSize(btnSize); //TODO: what is difference between setPreferedSize and setSize
		 
		 Dimension dim= installmentsMonthField.getPreferredSize();
		 dim.width=112;
		 installmentsMonthField.setPreferredSize(dim);
		 
		 referenceArea.setBorder(BorderFactory.createEtchedBorder());
		 
		 //add subPanel to Dialog
		 setLayout(new BorderLayout());
		 add(controlsPanel,BorderLayout.CENTER);
		 add(buttonsPanel, BorderLayout.SOUTH);
		
	}
	
	public void reset(){
	
		nameField.setText("");
		priceField.setText("");
		profitField.setText("");
		referenceArea.setText("");

	}
	
	public void showDialog(String title,int row){
		setTitle(title);
		this.row = row;
		setVisible(true);
	}

	public void setPurchaseListener(PurchaseListener purchaseListener) {
		this.purchaseListener = purchaseListener;
		
	}
}
