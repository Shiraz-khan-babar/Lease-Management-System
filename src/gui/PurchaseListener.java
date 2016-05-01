package gui;

import java.util.EventListener;

public interface PurchaseListener extends EventListener{//TODO:Why we extends FormListener from EventListener
	public void purchaseEventOccurred(PurchaseEvent e);
}