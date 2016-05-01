package gui;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.Border;

public class VPanel extends JPanel{

	private static final long serialVersionUID = 2854486656796090906L;

	public VPanel(String title,int size){
		
		//setting component properties
		Dimension dim = getPreferredSize(); //TODO: why we aren't set the size of panel by setSize method
		dim.width = 260;
		setPreferredSize(dim);
		   //setting border to the panel
		Border compoundBorder = VPanelBorder.createVPanelBorder(title,size);
		setBorder(compoundBorder);
		
	}

}
