package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InstallmentDialog extends JDialog{

	private static final long serialVersionUID = -3885885716900494645L;
	private JLabel installmentBalanceLabel;
	private JTextField installmentBalanceField;
	private int perRow;
	private int proRow;
	private JButton doneButton;
	private InstallmentListener installmentListener;
	public InstallmentDialog(JFrame parent){
		super(parent);
		perRow = -1;
		proRow = -1;
		installmentBalanceLabel = new JLabel("Balance: ");
		installmentBalanceField = new JTextField(10);
		doneButton = new JButton("Done");
		setTitle("Product Installment");
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx =1;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		
		add(installmentBalanceLabel,gc);
		gc.gridx++;
		add(installmentBalanceField,gc);
		
		gc.gridy++;
		add(doneButton,gc);
		
		doneButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double balance = Double.parseDouble(installmentBalanceField.getText());
				installmentListener.addAmount(balance,perRow,proRow);
				setVisible(false);
				
			}
		});
		
		setLocationRelativeTo(parent);
		setSize(200, 100);
	}

	public void showDialog(int perRow,int proRow){
		this.perRow = perRow;
		this.proRow = proRow;
		setVisible(true);
	}
	
	public void setInsAmount(InstallmentListener installmentListener){
		this.installmentListener = installmentListener;
	}
}
