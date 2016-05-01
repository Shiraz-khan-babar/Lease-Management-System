package gui;

import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		
		//TODO: Check for what purpose SwingUtiities.invokeLater is used
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainFrame();
				
			}
		});

	}

}
