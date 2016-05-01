package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	public TextPanel(){
		
		        //Setting the layout
				setLayout(new BorderLayout());
				
				//initializing instances
				textArea = new JTextArea();
				
				//adding components to MainFrame
				add(new JScrollPane(textArea), BorderLayout.CENTER);
				
				
				//setting component properties
				
				
				}
	
	//interacting methods
	public void appendText(String text){
		textArea.append(text);
	}
}