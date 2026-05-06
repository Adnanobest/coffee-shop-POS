package pack1;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

public class POS extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel drinksPanel;
	private JPanel drinkMenuPanel;
	private JPanel dessertsPanel;
	private JPanel dessertMenuPanel;
	private JPanel totalPanel;
	private JScrollPane drinkScroll;
	private JScrollPane dessertScroll;
	private ButtonGroup btngrpHorC;
	private ButtonGroup btngrpSize;
	private JRadioButton rdbtnHot;
	private JRadioButton rdbtnCold;
	private JRadioButton rdbtnL;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnS;
	private JButton selectedButton = null;
	private JLabel lblTotal;
	private double total;
	private ArrayList<JButton> drinkButtons = new ArrayList<JButton>(0);
	private ArrayList<JButton> dessertButtons = new ArrayList<JButton>(0);
	ArrayList<Drink> drinks = new ArrayList<Drink>(0);
	ArrayList<Dessert> desserts = new ArrayList<Dessert>(0);
	private JList<String> list;
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private DecimalFormat df = new DecimalFormat("#.##");
	private Font font = new Font("Tahoma", Font.PLAIN, 13);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POS frame = new POS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public POS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 1016, 500);
		setMinimumSize(new Dimension(999, 500));
		contentPane = new JPanel(new MigLayout("fill, insets 0 , hidemode 3", "[:12.5%:250][62.5%][25%]", "[]"));

		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, "grow");

		drinksPanel = new JPanel();
		contentPane.add(drinksPanel, "cell 1 0,grow");

		dessertsPanel = new JPanel();
		contentPane.add(dessertsPanel, "cell 1 0,grow");

		totalPanel = new JPanel();
		contentPane.add(totalPanel, "cell 2 0,grow");

		setPanel(this);

		setDrinksPane();

		setDessertPanel();

		setTotalPanel();

		for (int i = 0; i < 32; i++) {
			addDrink("drink" + i, 6.5, "H C");
			addDesserts("dessert " + i, 5.2);
		}
	}

	double drinkSize() {
		if (rdbtnL.isSelected()) {
			return 1.25;
		} else if (rdbtnM.isSelected()) {
			return 1.0;
		} else {
			return 0.75;
		}
	}

	void removeButton(String name, int isDessert) {
		if (isDessert == 0) {
			for (JButton button : drinkButtons) {
				if (button.getText().equals(name)) {
					drinkButtons.remove(button);
					resetDrinks();
					break;
				}
			}
		} else {
			for (JButton button : dessertButtons) {
				if (button.getText().equals(name)) {
					dessertButtons.remove(button);
					resetDesserts();
					break;
				}
			}
		}
	}

	private void setPanel(POS pos) {
		panel.setLayout(new MigLayout("fill, wrap 1, insets 10", "", "12.5%"));

		JButton btnDrinks = new JButton("Drinks");
		btnDrinks.setFont(font);
		btnDrinks.setMnemonic('K');
		btnDrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dessertsPanel.setVisible(false);
				drinksPanel.setVisible(true);
				resetDrinks();
			}
		});
		panel.add(btnDrinks, "grow");

		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.setFont(font);
		btnDesserts.setMnemonic('R');
		btnDesserts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drinksPanel.setVisible(false);
				dessertsPanel.setVisible(true);
				resetDesserts();
			}
		});
		panel.add(btnDesserts, "grow");

		JButton btnAdmin = new JButton("Admin");
		panel.add(btnAdmin, "cell 0 8, grow");
		btnAdmin.setFont(font);
		btnAdmin.setMnemonic('N');
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame x = new LoginFrame(drinks, desserts, pos);
				x.setVisible(true);
			}
		});
	}

	private void setTotalPanel() {
		totalPanel.setLayout(new MigLayout("fill, insets 0", "[50%][50%]", "[85%]0[:4%:25]0[]"));

		list = new JList<String>();

		JScrollPane cartScroll = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		totalPanel.add(cartScroll, "cell 0 0 2 1,grow");

		JLabel lblCart = new JLabel("Items in cart:");
		lblCart.setFont(font);
		cartScroll.setColumnHeaderView(lblCart);

		lblTotal = new JLabel("Total: 0");
		lblTotal.setFont(font);
		totalPanel.add(lblTotal, "cell 0 1 2 1,grow");

		JButton btnPay = new JButton("Pay");
		btnPay.setFont(font);
		btnPay.setMnemonic('P');
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (total == 0) {
					JOptionPane.showMessageDialog(contentPane, "The cart is empty");
					return;
				}
				
				String enteredAmount = JOptionPane.showInputDialog(contentPane, "Enter the paid amount: ");
				if(isNum(enteredAmount)) {
					double paid = Double.parseDouble(enteredAmount);
					if (paid < total) {
						JOptionPane.showMessageDialog(contentPane, "Paid amount is less than the total");
					}
					else {
						double remainder = paid - total;
						JOptionPane.showMessageDialog(contentPane, "The remainder is: " + df.format(remainder));
						listModel.removeAllElements();
						total = 0;
						lblTotal.setText("Total: 0");
					}
				}
			}
		});
		totalPanel.add(btnPay, "cell 0 2,grow");

		JButton btnRemove = new JButton("Remove item");
		btnRemove.setFont(font);
		btnRemove.setMnemonic('V');
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue() == null) {
					JOptionPane.showMessageDialog(contentPane, "Select an item to remove");
					return;
				}
				total -= Double.parseDouble(list.getSelectedValue().split(",")[1]);
				listModel.remove(list.getSelectedIndex());
				lblTotal.setText("Total: " + df.format(total));
				list.setModel(listModel);
			}
		});
		totalPanel.add(btnRemove, "cell 1 2,grow");

	}

	private void setDrinksPane() {
		drinksPanel.setLayout(new MigLayout(" fill, insets 0", "", "[3%!]2[5%!]2[5%!]2[]"));
		drinksPanel.setVisible(false);

		btngrpHorC = new ButtonGroup();

		JLabel lblDrink = new JLabel("Select the drink, the size and either hot or cold then press add to cart");
		lblDrink.setFont(font);
		drinksPanel.add(lblDrink, "span 3, grow");

		JLabel lblHorC = new JLabel("Hot or Cold");
		lblHorC.setFont(font);
		drinksPanel.add(lblHorC, "cell 0 1, grow");

		rdbtnHot = new JRadioButton("Hot");
		rdbtnHot.setMnemonic('H');
		rdbtnHot.setActionCommand("H");
		rdbtnHot.setEnabled(false);
		drinksPanel.add(rdbtnHot, "grow, split 2");
		btngrpHorC.add(rdbtnHot);

		rdbtnCold = new JRadioButton("Cold");
		rdbtnCold.setMnemonic('C');
		rdbtnCold.setActionCommand("C");
		rdbtnCold.setEnabled(false);
		drinksPanel.add(rdbtnCold, "grow");
		btngrpHorC.add(rdbtnCold);

		JLabel lblSize = new JLabel("Size");
		lblSize.setFont(font);
		drinksPanel.add(lblSize, "cell 0 2, grow");

		btngrpSize = new ButtonGroup();

		rdbtnL = new JRadioButton("L");
		rdbtnL.setMnemonic('L');
		rdbtnL.setActionCommand("L");
		drinksPanel.add(rdbtnL, "grow, split 3");
		btngrpSize.add(rdbtnL);

		rdbtnM = new JRadioButton("M");
		rdbtnM.setMnemonic('M');
		rdbtnM.setActionCommand("M");
		drinksPanel.add(rdbtnM, "grow");
		btngrpSize.add(rdbtnM);

		rdbtnS = new JRadioButton("S");
		rdbtnS.setMnemonic('S');
		rdbtnS.setActionCommand("S");
		drinksPanel.add(rdbtnS, "grow");
		btngrpSize.add(rdbtnS);

		JButton btnAddDrinkToCart = new JButton("Add to cart");
		btnAddDrinkToCart.setFont(font);
		btnAddDrinkToCart.setMnemonic('A');
		btnAddDrinkToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedButton == null) {
					JOptionPane.showMessageDialog(contentPane, "you have to select a drink first");
					return;
				} else if (btngrpHorC.getSelection() == null || btngrpSize.getSelection() == null) {
					JOptionPane.showMessageDialog(contentPane, "you have to select hot or cold and a size");
					return;
				}
				for (Drink drink : drinks) {
					if (drink.name.equals(selectedButton.getText())) {
						double price = drink.getPrice() * drinkSize();
						total += price;
						lblTotal.setText("Total: " + df.format(total));
						listModel.addElement(
								drink.name + ", " + price + ", " + btngrpHorC.getSelection().getActionCommand() + ", "
										+ btngrpSize.getSelection().getActionCommand());
						list.setModel(listModel);
						btngrpHorC.clearSelection();
						btngrpSize.clearSelection();
						selectedButton = null;
						return;
					}
				}
			}
		});
		drinksPanel.add(btnAddDrinkToCart, "cell 3 0 1 3, grow, gap 5 5 5 5");

		drinkMenuPanel = new JPanel(new MigLayout(" fill, ins 0 0 5 5, wrap 6", "[][][][][][]", "[::100]5"));
		drinkScroll = new JScrollPane(drinkMenuPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		drinkScroll.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resetDrinks();
			}
		});
		drinkScroll.getVerticalScrollBar().setUnitIncrement(16);

		drinksPanel.add(drinkScroll, "cell 0 3 4 1, grow");

	}

	void addDrink(String name, Double price, String horc) {
		Drink drink = new Drink(name, price, horc);
		drinks.add(drink);

		JButton btndrink = new JButton(name);
		btndrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedButton = btndrink;
				btngrpHorC.clearSelection();
				btngrpSize.clearSelection();

				switch (drink.state) {
				case 0:
					rdbtnHot.setEnabled(true);
					rdbtnCold.setEnabled(true);
					break;
				case 1:
					rdbtnHot.setEnabled(true);
					rdbtnCold.setEnabled(false);
					break;
				case 2:
					rdbtnHot.setEnabled(false);
					rdbtnCold.setEnabled(true);
					break;
				}
			}
		});
		drinkButtons.add(btndrink);
		btndrink.setToolTipText(price.toString());
		resetDrinks();
	}

	void resetDrinks() {
		String h = Integer.toString(drinkScroll.getHeight() / 6);
		((MigLayout) drinkMenuPanel.getLayout()).setRowConstraints("[:"+ h + ": 150]:5:20");

		drinkMenuPanel.removeAll();
		drinkMenuPanel.repaint();
		drinkMenuPanel.revalidate();
		

		if (drinkButtons.isEmpty()) {
			return;
		}

		int i = 0;
		for (JButton button : drinkButtons) {
			button.setToolTipText(drinks.get(i++).getPrice().toString());
			drinkMenuPanel.add(button, "grow");
		}
	}

	private void setDessertPanel() {
		dessertsPanel.setLayout(new MigLayout("fill, insets 0", "", ""));
		dessertsPanel.setVisible(false);

		dessertMenuPanel = new JPanel(new MigLayout("fill, insets 0, wrap 6", "", ""));

		dessertScroll = new JScrollPane(dessertMenuPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dessertScroll.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				resetDesserts();
			}
		});
		dessertScroll.getVerticalScrollBar().setUnitIncrement(16);
		dessertsPanel.add(dessertScroll, "grow");

		JLabel lblDessert = new JLabel("Click on   the selected dessert");
		dessertScroll.setColumnHeaderView(lblDessert);
	}

	void addDesserts(String name, Double price) {
		desserts.add(new Dessert(name, price));

		JButton dessert = new JButton(name);
		dessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.addElement(name + ", " + df.format(price));
				total += price;

				lblTotal.setText("Total: " + df.format(total));
				list.setModel(listModel);
			}
		});

		dessertButtons.add(dessert);
		resetDesserts();
	}

	void resetDesserts() {
		String h = Integer.toString(dessertScroll.getHeight() / 7);
		((MigLayout) dessertMenuPanel.getLayout()).setRowConstraints(h + "!");

		dessertMenuPanel.removeAll();
		dessertMenuPanel.repaint();

		if (dessertButtons.isEmpty()) {
			return;
		}
		int i = 0;
		for (JButton button : dessertButtons) {
			button.setToolTipText(desserts.get(i++).getPrice().toString());
			dessertMenuPanel.add(button, "grow");
		}
	}
	
	public boolean isNum(String str) {
		int dot = 0;
		
		if(str.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, "You must enter a number for the price");
			return false;
		}
		else if(str.equals(".")) {
			JOptionPane.showMessageDialog(contentPane, "price can only be a number");
			return false;
		}
		else {
			for (char x : str.toCharArray()) {
				if (x == '.') {
					if (++dot > 1) {
						JOptionPane.showMessageDialog(contentPane, "price can only be a number");
						return false;
					}
				} else if (x - '0' < 0 || x - '0' > 9) {
					JOptionPane.showMessageDialog(contentPane, "price can only be a number");
					return false;
				}
			}
			return true;
		}
	}
}
