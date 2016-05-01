package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FormPanel extends VPanel{

	private static final long serialVersionUID = 7795861631077240899L;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel nicLabel;
	private JTextField nicField;
	private JLabel addressLabel;
	private JTextField addressField;
	private JLabel contactLabel;
	private JTextField contactField;
	private JLabel referenceLabel;
	private JTextArea referenceFieldArea;
	private JButton addBtn;
	private JButton resetBtn; 
	
	
	private FormListener formListener;
	public FormPanel(){
		super("Add Customer",0);
		//initializing instances
		
		nameLabel = new JLabel("Name: ");
		nameField = new JTextField(10);
		
		nicLabel = new JLabel("NIC: ");
		nicField = new JTextField(10);
		addressLabel= new JLabel("Address: ");
		addressField = new JTextField(10);
		contactLabel= new JLabel("Contact: ");
		contactField = new JTextField(10);
		referenceLabel= new JLabel("Reference: ");
		referenceFieldArea = new JTextArea(5,10);
		addBtn = new JButton("Create");
		resetBtn = new JButton("Reset");
		//Setting the layout
		setLayout(new GridBagLayout());//TODO: Study GridBagLayout and GridBagConstraints
		
		//setting for formPanel property
		
		//adding listener to components
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(nameField.getText().equals("")){
					JOptionPane.showMessageDialog(FormPanel.this, "Please enter customer name");
				}
				else{
				String name = nameField.getText();
				String address = addressField.getText();
				String nic = nicField.getText();
				String contact = contactField.getText();
				String reference = referenceFieldArea.getText();
				FormEvent e= new FormEvent(this,name,address,nic,contact,reference);
				if(formListener!=null){
					formListener.formEventOccurred(e);
				}
				reset();
				}
			}
		});
		
		resetBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset();
				
			}
		});
		
		//setting component properties
		referenceFieldArea.setBorder(BorderFactory.createEtchedBorder());
		
		//setup mnemonics
		addBtn.setMnemonic(KeyEvent.VK_C);
		resetBtn.setMnemonic(KeyEvent.VK_R);
		
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
	    
		layoutComponents();

	}
	
	protected void reset() {
		
		nameField.setText("");
		addressField.setText("");
		nicField.setText("");
		contactField.setText("");
		referenceFieldArea.setText("");
		
	}

	public void layoutComponents(){
		GridBagConstraints gc = new GridBagConstraints(); // we use GridBagConstraints object with GridBagLayout
		
		gc.weightx = 1;//TODO: Study the weight property of GridBagConstraints
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;//TODO: Study the fill property of gc

		
		//adding components to Panel
		//////////////////////////row1////////////////////////////////////////////////////
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel,gc);
		
		gc.gridx++;
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField,gc);
		
		//////////////////////////next row////////////////////////////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		

		gc.gridx--;
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(addressLabel,gc);
		
		gc.gridx++;
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(addressField,gc);
		
		//////////////////////////next row////////////////////////////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		

		gc.gridx--;
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nicLabel,gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nicField,gc);
		
		//////////////////////////next row////////////////////////////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;

		
		gc.gridx--;
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(contactLabel,gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(contactField,gc);
		
		//////////////////////////next row////////////////////////////////////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;

		
		gc.gridx--;
		
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(referenceLabel,gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(new JScrollPane(referenceFieldArea),gc);
		

		//////////////////////////next row////////////////////////////////////////////////	
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 3.0;
		

		gc.gridx--;
		gc.insets = new Insets(0,5, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(addBtn,gc);
		
		gc.gridx++;
		gc.insets = new Insets(0, 3, 0, 0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(resetBtn,gc);
		
	}
	
	

	public void setFormListener(FormListener formListener) {
		this.formListener  = formListener;
		
	}
}

