package gui;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class VPanelBorder {
	
	private static Border innerBorder;
	private static Border outerBorder;
	//TODO: study different kinds of borders
	public static Border createVPanelBorder(String title,int space){
		innerBorder = BorderFactory.createTitledBorder(title);
		outerBorder = BorderFactory.createEmptyBorder(space,space,space,space);
		return (BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}

}
