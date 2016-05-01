package gui;

import java.util.EventListener;

public interface FormListener extends EventListener{//TODO:Why we extends FormListener from EventListener
	public void formEventOccurred(FormEvent e);
}
