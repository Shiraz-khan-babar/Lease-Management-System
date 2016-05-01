package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1586364165544995502L;
	private TextPanel textPanel;
	private ToolBar toolBar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private PersonDetailPanel personDetailPanel;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	private PurchaseDialog purchaseDialog;
	private InstallmentDialog installmentDialog;

	public MainFrame() {
		super("United Star Traders");
		// Setting the layout
		 
		setLayout(new BorderLayout());

		// initializing instances
		textPanel = new TextPanel();
		toolBar = new ToolBar();
		formPanel = new FormPanel();
		fileChooser = new JFileChooser();
		controller = new Controller();
		tablePanel = new TablePanel();
		personDetailPanel = new PersonDetailPanel("Product Details");
		prefsDialog = new PrefsDialog(this);
		prefs = Preferences.userRoot().node("db");
		purchaseDialog = new PurchaseDialog(this);
		installmentDialog = new InstallmentDialog(this);

		try {
			controller.loadFromFile(new File("C:/United Star Traders/database.per"));
		} catch (IOException e) {
			System.out.println("File doesnot exist");
			e.printStackTrace();
		}

		// adding components to MainFrame
		add(formPanel, BorderLayout.WEST);
		add(tablePanel, BorderLayout.CENTER);
		add(toolBar, BorderLayout.PAGE_START);
		add(personDetailPanel, BorderLayout.EAST);

		// adding Listeners to components
		toolBar.setStringListener(new StringListener() {

			@Override
			public void textEmitted(String text) {
				textPanel.appendText(text);

			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent ev) {
				controller.addPerson(ev);
				tablePanel.refresh();
			}
		});

		purchaseDialog.setPurchaseListener(new PurchaseListener() {

			@Override
			public void purchaseEventOccurred(PurchaseEvent pe) {
				controller.addProduct(pe);
				personDetailPanel.refresh();
			}
		});

		// setting fileChooser
		fileChooser.addChoosableFileFilter(new PersonFileFilter());

		// setting tablePanel
		tablePanel.setData(controller.getPeople());

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				
				File file = new File("C:/United Star Traders/database.per");
				try {
					controller.saveToFile(file);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Database file is not exist",
							"Database Error!", JOptionPane.ERROR_MESSAGE, null);
				}
				dispose();
				System.gc();
			}
			

		});

		// communication between text panel and tool bar via reference passing

		// setting MainFrame properties
		setJMenuBar(createMenuBar());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(100, 20);
		setMinimumSize(new Dimension(1000, 700));
		setSize(1000, 700);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// setting up file menu
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		// setting up window menu
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");

		JCheckBoxMenuItem showPersonItem = new JCheckBoxMenuItem("Person Form");
		showPersonItem.setSelected(true);

		JMenuItem prefsItem = new JMenuItem("Preferences...");

		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		showMenu.add(showPersonItem);

		// adding menus to menuBar
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		// adding action listener to menuItems

		prefsItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				prefsDialog.setVisible(true);

			}
		});

		prefsDialog.setPrefsListener(new PrefsListener() {

			@Override
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);

			}
		});

		Integer port = prefs.getInt("port", 3306);
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");

		prefsDialog.setDefaults(user, password, port);

		showPersonItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				formPanel.setVisible(menuItem.isSelected());

			}
		});

		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit", "Confirm Exit",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					WindowListener[] listener = getWindowListeners();
					for (WindowListener windowListener : listener) {
						windowListener.windowClosing(new WindowEvent(MainFrame.this, 0));
					}
				}

			}
		});

		importDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e) {
						System.err.println("File is not loaded");
						JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file", "Error",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}

			}
		});

		exportDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e) {
						System.err.println("couldn't save to file");
						JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file", "Error",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}

			}
		});

		tablePanel.setPersonTableListener(new PersonTableListener() {

			public void rowDeleted(int row) {
				controller.removePerson(row);
			}

			public void purchaseNewProduct(int row, String personName) {
				String title = "Customer: " + personName + " (New Product)";
				purchaseDialog.showDialog(title, row);
			}

			@Override
			public void selectCustomer(int row) {
				personDetailPanel.setData(controller.getProducts(row), row);
				personDetailPanel.refresh();
			}
		});

		personDetailPanel.setProductTableListener(new ProductTableListener() {

			@Override
			public void showDetails(int perRow, int proRow) {
				JOptionPane.showMessageDialog(MainFrame.this, controller.getProductDetails(perRow, proRow));
			}

			@Override
			public void doInstallment(int perRow, int proRow) {
				if (controller.isPersonActive(perRow, proRow)) {
					installmentDialog.showDialog(perRow, proRow);
				} else
					JOptionPane.showMessageDialog(MainFrame.this, "Product have complete there installments");
			}

			@Override
			public void selectProduct(int perRow, int proRow) {
				if (controller.getPeople().get(perRow).getProduct(proRow).getProductInstallment().size() > 0) {
					personDetailPanel.setInsData(
							controller.getPeople().get(perRow).getProduct(proRow).getProductInstallment(), perRow);
					personDetailPanel.refreshIns();
				}
			}
		});

		installmentDialog.setInsAmount(new InstallmentListener() {

			@Override
			public void addAmount(double price, int perRow, int proRow) {
				controller.doInstallment(price, perRow, proRow);
			}
		});

		// add mnemonics : is actually alto + any_key
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);

		// TODO:adding accelerator: which can only be add to menus
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		return menuBar;
	}

}
