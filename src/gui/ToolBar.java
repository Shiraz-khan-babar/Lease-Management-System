package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar implements ActionListener{

	private static final long serialVersionUID = 6957613369074774670L;
	private JButton saveButton;
	private JButton refreshButton;
	private StringListener textListener;
	public ToolBar(){
		
		//initializing instances
		saveButton = new JButton("");
		saveButton.setToolTipText("Save");
		saveButton.setIcon(createIcon("/images/save.png"));
		
		refreshButton = new JButton("");
		refreshButton.setToolTipText("Refresh");
		refreshButton.setIcon(createIcon("/images/refresh.png"));
		
		//adding components to MainFrame
		add(saveButton);
		///addSeparator();
		add(refreshButton);
		
		//adding listener to components
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		
		
		//setting component properties
		   //get rid of the border if you wants to be tool bar draggable
		//setBorder(BorderFactory.createEtchedBorder());
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		
		if(clicked==saveButton){
			if(textListener!=null){
				textListener.textEmitted("Welcome\n");
			}
		}else{
			if(textListener!=null){
				textListener.textEmitted("GoodBye\n");
			}
		}
		
	}
	
	public ImageIcon createIcon(String path){
		URL url = getClass().getResource(path);
		if(url==null){
			System.err.println("Couldnot load the path"+path);
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}

	public void setStringListener(StringListener listener){
		this.textListener = listener;
	}
}
