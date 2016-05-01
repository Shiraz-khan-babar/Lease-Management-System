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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PrefsDialog extends JDialog{

	private static final long serialVersionUID = -903557027721834495L;
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField userField;
	private JPasswordField passField;
	
	private PrefsListener prefsListener;
	
	public PrefsDialog(JFrame parent){
		 super(parent,"Preferenes",false);
		 
		 okButton = new JButton("OK");
		 cancelButton = new JButton("Cancel");
		 userField = new JTextField(10);
		 
		 passField = new JPasswordField(10);
		 passField.setEchoChar('F');
		 
		 spinnerModel = new SpinnerNumberModel(3306,0,9999,1);
		 portSpinner = new JSpinner(spinnerModel);
		
		 
		 layoutControl();
		 
		 okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer port=(Integer)portSpinner.getValue();
				String user = userField.getText();
				char[] password = passField.getPassword();
			    
				if(prefsListener!=null){
					prefsListener.preferencesSet(user, new String(password), port);
				}
				setVisible(false);
			}
		});
		 
		 
		 cancelButton.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		 
		 setLocationRelativeTo(parent);
		 setSize(320,230);
	}

	private void layoutControl() {
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		GridBagConstraints gc = new GridBagConstraints();
		int space =15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space ,space,space,space);
		Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");
		
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
		 controlsPanel.add(new JLabel("User: "),gc);
		 
		 gc.gridx++;
		 gc.insets = noPadding;
		 gc.anchor = GridBagConstraints.WEST;
		 controlsPanel.add(userField, gc);
		 
		 //////////////////////next row/////////////////////////////
		 gc.gridy++;
		 gc.gridx = 0;
		 gc.anchor = GridBagConstraints.EAST;
		 gc.insets = rightPadding;
		 controlsPanel.add(new JLabel("Password: "),gc);
		 
		 gc.gridx++;
		 gc.anchor = GridBagConstraints.WEST;
		 gc.insets = noPadding;
		 controlsPanel.add(passField, gc);
		 
		 //////////////////////next row/////////////////////////////
		 gc.gridy++;
		 gc.gridx = 0;
		 gc.anchor = GridBagConstraints.EAST;
		 gc.insets = rightPadding;
		 controlsPanel.add(new JLabel("Port: "),gc);
		 
		 gc.gridx++;
		 gc.anchor = GridBagConstraints.WEST;
		 gc.insets = noPadding;
		 controlsPanel.add(portSpinner, gc);
		 
		 //////////////////////next row/////////////////////////////		 
		 buttonsPanel.add(okButton);
		 buttonsPanel.add(cancelButton);
		 
		 //setting sub panels
		 Dimension btnSize = cancelButton.getPreferredSize();
		 okButton.setPreferredSize(btnSize); //TODO: what is difference between setPreferedSize and setSize
		 
		 //add subPanel to Dialog
		 setLayout(new BorderLayout());
		 add(controlsPanel,BorderLayout.CENTER);
		 add(buttonsPanel, BorderLayout.SOUTH);
		
	}

	public void setPrefsListener(PrefsListener prefsListener) {
		this.prefsListener = prefsListener;
	}
	
	public void setDefaults(String user,String password,int port){
		userField.setText(user);
		passField.setText(password);
		portSpinner.setValue(port);
	}

}
