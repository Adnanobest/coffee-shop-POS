package pack1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;

public class ManagmentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDrinkName;
	private JTextField txtDrinkPrice;
	private JTextField txtDessertName;
	private JTextField txtDessertPrice;
	DefaultListModel<String> listModel = new DefaultListModel<>();
	JList<String> list;
	ArrayList<Drink> drinks;
	ArrayList<Dessert> desserts;
	JPanel panel;
	JPanel addDrinkPanel;
	JPanel addDessertPanel;
	JPanel listPanel;
	POS pos;

	Font font = new Font("Tahoma", Font.PLAIN, 13);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagmentFrame frame = new ManagmentFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManagmentFrame(POS pos) {
		this.pos=pos;
		setTitle("Management");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 576, 350);
		setMinimumSize(new Dimension(450, 275));

		contentPane = new JPanel(
				new MigLayout("fill, insets 0 0, gap 0 0, hidemode 3", "[:20%:200]3[40%]3[40%]", "[]"));
		setContentPane(contentPane);
		
		drinks = pos.drinks;
		desserts = pos.desserts;

		panel = new JPanel();
		contentPane.add(panel, "grow");

		addDrinkPanel = new JPanel();
		addDrinkPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(addDrinkPanel, "grow");
		addDrinkPanel.setVisible(false);

		addDessertPanel = new JPanel();
		addDessertPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(addDessertPanel, "cell 1 0, grow");
		addDessertPanel.setVisible(false);

		listPanel = new JPanel();
		contentPane.add(listPanel, "cell 2 0, grow");

		addPane();

		addDessertPane();

		addDrinkPane();

		listPane();
		
		refresh();

	}	
	
	private void refresh() {
		if (!drinks.isEmpty() || !desserts.isEmpty()) {
			listModel.clear();
			listModel.addElement("Drinks:");
			for (Drink x : drinks) {
				listModel.addElement(x.name + ", price: " + x.getPrice());
			}
			listModel.addElement("Desserts:");
			for (Dessert x : desserts) {
				listModel.addElement(x.name + ", price: " + x.getPrice());
			}
			list.setModel(listModel);
		}
	}

	private void addPane() {
		panel.setLayout(new MigLayout("wrap 1, fill, insets 5", "center", "[33%][][][33%]"));

		JButton btnAddDrink = new JButton("add drink");
		btnAddDrink.setMnemonic('K');
		btnAddDrink.setFont(font);
		panel.add(btnAddDrink, "cell 0 1, grow");
		btnAddDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDessertPanel.setVisible(false);
				addDrinkPanel.setVisible(true);
			}
		});

		JButton btnAddDessert = new JButton("add dessert");
		btnAddDessert.setMnemonic('R');
		btnAddDessert.setFont(font);
		panel.add(btnAddDessert, "cell 0 2, grow");
		btnAddDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDrinkPanel.setVisible(false);
				addDessertPanel.setVisible(true);
			}
		});
	}

	private void listPane() {
		listPanel.setLayout(new MigLayout("wrap 1, fill, insets 0 0, gap 0 0", "[]", "[80%][15:5%][]"));

		list = new JList<String>(listModel);
		JScrollPane scroll = new JScrollPane(list);

		listPanel.add(scroll, "grow");

		JLabel lblSelected = new JLabel("for selected item:");
		listPanel.add(lblSelected, "gapx 5");

		JButton btnChangePrice = new JButton("<html><p style='text-align:center;'>Change<br>Price</p>");
		btnChangePrice.setMnemonic('G');
		btnChangePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(contentPane, "Select an item to change the price");
					return;
				}
				changePrice();
				refresh();
			}
		});
		btnChangePrice.setFont(font);
		listPanel.add(btnChangePrice, "split 2, grow, gapx 5");

		JButton btnRemove = new JButton("Remove");
		btnRemove.setMnemonic('V');
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(contentPane, "Select an item to remove");
					return;
				}
				remove();
				refresh();
			}
		});
		btnRemove.setFont(font);
		listPanel.add(btnRemove, "grow, gapx 5:2.5% 5");

	}

	private void addDrinkPane() {
		addDrinkPanel.setLayout(new MigLayout("wrap 1, fill, insets 10", "center", ""));

		JLabel lblDrinkName = new JLabel("Drink name:", JLabel.CENTER);
		lblDrinkName.setFont(font);
		lblDrinkName.setDisplayedMnemonic('N');
		addDrinkPanel.add(lblDrinkName);

		txtDrinkName = new JTextField(1000);
		addDrinkPanel.add(txtDrinkName);

		lblDrinkName.setLabelFor(txtDrinkName);

		JLabel lblDrinkPrice = new JLabel("Price for size M:", JLabel.CENTER);
		lblDrinkPrice.setFont(font);
		lblDrinkPrice.setDisplayedMnemonic('P');
		addDrinkPanel.add(lblDrinkPrice);

		txtDrinkPrice = new JTextField(1000);
		addDrinkPanel.add(txtDrinkPrice);

		lblDrinkPrice.setLabelFor(txtDrinkPrice);

		JLabel lblHorC = new JLabel("Hot or cold", JLabel.CENTER);
		lblHorC.setFont(font);
		addDrinkPanel.add(lblHorC);

		JCheckBox chckbxHot = new JCheckBox("Hot");
		chckbxHot.setFont(font);
		addDrinkPanel.add(chckbxHot, "split 2");
		chckbxHot.setMnemonic('H');
		chckbxHot.setActionCommand("H");

		JCheckBox chckbxCold = new JCheckBox("Cold");
		chckbxCold.setMnemonic('C');
		chckbxCold.setFont(font);
		addDrinkPanel.add(chckbxCold);

		JButton btnSaveDrink = new JButton("Save Drink");
		btnSaveDrink.setMnemonic('S');
		btnSaveDrink.setFont(font);
		addDrinkPanel.add(btnSaveDrink, "grow, cell 0 8");
		btnSaveDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtDrinkName.getText().equals("")) {
					if (pos.isNum(txtDrinkPrice.getText())) {
						if (chckbxHot.isSelected() || chckbxCold.isSelected()) {
							String name = txtDrinkName.getText();
							Double price = Double.parseDouble(txtDrinkPrice.getText());
							String horc = "";
							if (chckbxHot.isSelected()) {
								horc += "H" + " ";
							}
							if (chckbxCold.isSelected()) {
								horc += "C";
							}

							pos.addDrink(name, price, horc);
							JOptionPane.showMessageDialog(contentPane,
									drinks.getLast().name + " Saved with price "
											+ drinks.getLast().getPrice() + " with " + horc + " available");
							refresh();
							txtDrinkName.setText("");
							txtDrinkPrice.setText("");
							chckbxHot.setSelected(false);
							chckbxCold.setSelected(false);
						} else {
							JOptionPane.showMessageDialog(contentPane, "you must select either hot, cold or both");
						}
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "you have to enter a name for the drink");
				}
			}
		});
	}

	private void addDessertPane() {
		addDessertPanel.setLayout(new MigLayout("wrap 1, fill, insets 10", "center", ""));

		JLabel lblDessertName = new JLabel("Dessert name:", JLabel.CENTER);
		lblDessertName.setFont(font);
		lblDessertName.setDisplayedMnemonic('N');
		addDessertPanel.add(lblDessertName);

		txtDessertName = new JTextField(1000);
		addDessertPanel.add(txtDessertName);
		lblDessertName.setLabelFor(txtDessertName);

		JLabel lblDessertPrice = new JLabel("Price:", JLabel.CENTER);
		lblDessertPrice.setFont(font);
		lblDessertPrice.setDisplayedMnemonic('P');
		addDessertPanel.add(lblDessertPrice);

		txtDessertPrice = new JTextField(1000);
		addDessertPanel.add(txtDessertPrice);
		lblDessertPrice.setLabelFor(txtDessertPrice);

		JButton btnSaveDessert = new JButton("Save Dessert");
		btnSaveDessert.setMnemonic('S');
		btnSaveDessert.setFont(font);
		addDessertPanel.add(btnSaveDessert, "grow, cell 0 10");

		btnSaveDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtDessertName.getText().equals("")) {
					if (pos.isNum(txtDessertPrice.getText())) {
						int exist = alreadyExists(txtDessertName.getText());
						switch (exist) {
						case 0:
							String name = txtDessertName.getText();
							Double price = Double.parseDouble(txtDessertPrice.getText());
							pos.addDesserts(name, price);
							JOptionPane.showMessageDialog(contentPane, name + " Saved with price " + price);
							refresh();
							txtDessertName.setText("");
							txtDessertPrice.setText("");
							break;
						case 1:
							JOptionPane.showMessageDialog(btnSaveDessert,
									"drink " + txtDessertName.getText() + " already exists");
							break;
						case 2:
							JOptionPane.showMessageDialog(btnSaveDessert,
									"dessert " + txtDessertName.getText() + " already exists");
						}
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "you have to enter a name for the dessert");
				}
			}
		});

	}

	int alreadyExists(String name) {
		for (Drink drink : drinks) {
			if (drink.name.equals(name)) {
				return 1;
			}
		}
		for (Dessert dessert : desserts) {
			if (dessert.name.equals(name)) {
				return 2;
			}
		}
		return 0;
	}

	private void remove() {
		if (!list.isSelectionEmpty()) {
			if (list.getSelectedIndex() <= drinks.size()) {
				for (Drink x : drinks) {
					if (x.name.equals(list.getSelectedValue().split(",")[0])) {
						JOptionPane.showMessageDialog(contentPane, list.getSelectedValue() + " deleted");
						drinks.remove(x);
						pos.removeButton(list.getSelectedValue().split(",")[0], 0);
						pos.resetDrinks();
						return;
					}
				}
			} else {
				for (Dessert x : desserts) {
					if (x.name.equals(list.getSelectedValue().split(",")[0])) {
						JOptionPane.showMessageDialog(contentPane, list.getSelectedValue() + " deleted");
						desserts.remove(x);
						pos.removeButton(list.getSelectedValue().split(",")[0], 1);
						pos.resetDesserts();
						return;
					}
				}
			}
		}
	}

	private void changePrice() {
		if (!list.isSelectionEmpty()) {
			if (list.getSelectedValue().equals("Drinks:") || list.getSelectedValue().equals("Desserts:")) {
				JOptionPane.showMessageDialog(contentPane, "Not Valid!");
			} else {
				String selected = list.getSelectedValue().split(",")[0];
				String str = JOptionPane.showInputDialog(contentPane, "enter new price for " + selected);
				while (!pos.isNum(str)) {
					str = JOptionPane.showInputDialog(contentPane, "enter new price for " + selected);
				}
				double newPrice = Double.parseDouble(str);
				if (list.getSelectedIndex() <= drinks.size()) {
					for (Drink x : drinks) {
						if (x.name.equals(selected)) {
							x.setPrice(newPrice);
							JOptionPane.showMessageDialog(contentPane, selected + "'s price changed to" + x.getPrice());
							refresh();
							return;
						}
					}
				} else {
					for (Dessert x : desserts) {
						if (x.name.equals(selected)) {
							x.setPrice(newPrice);
							JOptionPane.showMessageDialog(contentPane, selected + "'s price changed to" + x.getPrice());
							refresh();
							return;
						}
					}
				}
			}

		}
	}

}