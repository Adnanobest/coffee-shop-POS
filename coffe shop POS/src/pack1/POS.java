package pack1;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

public class POS extends JFrame {
	
	double drinkSize() {
		if (rdbtnL.isSelected()) {
			return 1.25;
		}else if (rdbtnM.isSelected()) {
			return 1.0;
		}else {
			return 0.75;
		}
	}
	
	static ActionListener drinkBtnActionListener(Drink drink, JButton btndrink) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedButton=btndrink;
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
		};
	}

	static void removeButton(String x, int z) {
		if (z==0) {
			for (JButton y : drinkButtons) {
				if (y.getText().equals(x)) {
					drinkButtons.remove(y);
					resetDrinks();
					break;
				}
			}
		}else {
			for (JButton y : dessertButtons) {
				if (y.getText().equals(x)) {
					dessertButtons.remove(y);
					resetDesserts();
					break;
				}
			}
		}
	}
	
	static void addDrink(String name, Double price, String horc) {
		Drink drink = new Drink(name,price,horc);
		drinks.add(drink);
		
		JButton btndrink = new JButton(name);
		btndrink.addActionListener(drinkBtnActionListener(drink, btndrink));
		btndrink.setActionCommand(price.toString());
		drinkButtons.add(btndrink);
		btndrink.setToolTipText(price.toString());
		resetDrinks();
	}
	
	static void resetDrinks() {
		drinksPanel.removeAll();
		drinksPanel.repaint();

		drinksPanel.add(lblHorC);
		drinksPanel.add(rdbtnHot);
		drinksPanel.add(rdbtnCold);
		drinksPanel.add(lblSize);
		drinksPanel.add(rdbtnL);
		drinksPanel.add(rdbtnM);
		drinksPanel.add(rdbtnS);
		drinksPanel.add(btnAddDrinkToCart);

		if (drinkButtons.isEmpty()) {
			return;
		}
		int pos=spc;
		int i=0;
		height=60;		
		for (JButton x : drinkButtons) {
			if(i!=0 && i%6 == 0) {
				pos=spc;
				height+=70;
			} i++;
			drinksPanel.add(x);
			x.setBounds(pos, height, btnWidth, 50);
			pos+=btnWidth+spc;
		}
	}

	static void addDesserts(String name, Double price) {
		desserts.add(new Dessert(name,price));

		JButton dessert = new JButton(name);
		dessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedButton=dessert;
			}
		});
		
		dessertButtons.add(dessert);
		resetDesserts();
	}
	
	static void resetDesserts() {
		dessertsPanel.removeAll();
		dessertsPanel.repaint();
		dessertsPanel.add(btnAddDessertToCart);
		
		if (dessertButtons.isEmpty()) {
			return;
		}
		int pos=spc;
		int i=0;
		height=20;		
		for (JButton x : dessertButtons) {
			if(i!=0 && i%6 == 0) {
				pos=spc;
				height+=70;
			} i++;
			dessertsPanel.add(x);
			x.setBounds(pos, height, btnWidth, 50);
			pos+=btnWidth+spc;
		}
	}
	
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JPanel panel;
	private static JPanel drinksPanel;
	private static JPanel dessertsPanel;
	private static JPanel totalPanel;
	private static JLabel lblHorC;
	private static ButtonGroup btngrpHorC;
	private static JRadioButton rdbtnHot;
	private static JRadioButton rdbtnCold;
	private static JLabel lblSize;
	private static ButtonGroup btngrpSize;
	private static JRadioButton rdbtnL;
	private static JRadioButton rdbtnM;
	private static JRadioButton rdbtnS;
	private static JButton selectedButton = null;
	static JLabel lblTotal;
	static double total;
	static ArrayList<Object> cart = new ArrayList<Object>(0);
	static JList<String> list;
	static ArrayList<Drink> drinks = new ArrayList<Drink>(0);
	static ArrayList<Dessert> desserts = new ArrayList<Dessert>(0);
	static ArrayList<JButton> drinkButtons = new ArrayList<JButton>(0);
	static ArrayList<JButton> dessertButtons = new ArrayList<JButton>(0);
	static int btnWidth=85;
	JButton btnRefresh;
	static int spc=24;
	static int height= 5;
	private static JButton btnAddDessertToCart;
	private static JButton btnAddDrinkToCart;
	private DefaultListModel<String> listModel= new DefaultListModel<>();

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
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 1015, 500);
		setMinimumSize(new Dimension(999, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 111, 461);
		contentPane.add(panel);
		
		setPanel();

		drinksPanel = new JPanel();
		drinksPanel.setBounds(111, 0, 555, 461);
		contentPane.add(drinksPanel);
		drinksPanel.setVisible(false);
		
		setDrinksPane();
		
		dessertsPanel = new JPanel();
		dessertsPanel.setBounds(111, 0, 555, 461);
		contentPane.add(dessertsPanel);
		
		setDessertPanel();

		totalPanel = new JPanel();
		totalPanel.setBounds(666, 0, 333, 461);
		contentPane.add(totalPanel);
		
		setTotalPanel();
		
//		scroll = new JScrollPane(drinkPanel);
//		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
//		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		scroll.setVisible(true);

	}

	private void setDessertPanel() {
		dessertsPanel.setLayout(null);
		dessertsPanel.setVisible(false);
		
		btnAddDessertToCart = new JButton("add to cart");
		btnAddDessertToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedButton == null) {
					JOptionPane.showMessageDialog(contentPane, "you have to select a dessert first");
					return;
				}
				for (Dessert dessert : desserts) {
					if (dessert.name.equals(selectedButton.getText())) {
						total +=dessert.getPrice();
						listModel.addElement(dessert.name +", "+ dessert.getPrice());
						lblTotal.setText("Total: "+ Double.toString(total));
						list.setModel(listModel);
						selectedButton = null;
						return;
					}
				}
				
			}
		});
		btnAddDessertToCart.setBounds(337, 32, 100, 33);
		dessertsPanel.add(btnAddDessertToCart);
	}

	private void setTotalPanel() {
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(121, 423, 83, 38);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame x = new LoginFrame(drinks, desserts);
				x.setVisible(true);
			}
		});
		totalPanel.setLayout(null);
		
		list = new JList<String>();
		list.setBounds(41, 67, 243, 249);
		totalPanel.add(list);
		totalPanel.add(btnAdmin);
		
		lblTotal = new JLabel("Total: 0");
		lblTotal.setBounds(41, 340, 100, 14);
		totalPanel.add(lblTotal);
		
		JButton btnRemoveCart = new JButton("Remove");
		btnRemoveCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = list.getSelectedIndex();
				if(selectedIndex != -1) {
					
					total -= Double.parseDouble(list.getSelectedValue().split(",")[1]);
					lblTotal.setText("Total: "+total);
					listModel.remove(list.getSelectedIndex());
					JOptionPane.showMessageDialog(contentPane, "Item removed from the cart!");
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Please choose an item to remove");
				}
			}
		});
		btnRemoveCart.setBounds(197, 326, 100, 21);
		totalPanel.add(btnRemoveCart);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double paid = Double.parseDouble(JOptionPane.showInputDialog(contentPane, "Enter the paid amount: "));
				if(paid<total) {
					JOptionPane.showMessageDialog(contentPane, "Paid amount is less than the total");
				}
				else {
					double remainder = paid - total;
					JOptionPane.showMessageDialog(contentPane, "The remainder is: "+remainder);
					listModel.removeAllElements();
					total = 0;
					lblTotal.setText("Total: 0");
				}
			}
		});
		btnPay.setBounds(197, 359, 100, 21);
		totalPanel.add(btnPay);
	}

	private void setPanel() {
		JButton btnDrinks = new JButton("Drinks");
		btnDrinks.setBounds(10, 11, 89, 23);
		btnDrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dessertsPanel.setVisible(false);
				drinksPanel.setVisible(true);
			}
		});
		panel.setLayout(null);
		panel.add(btnDrinks);
		
		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.setBounds(10, 45, 89, 23);
		btnDesserts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drinksPanel.setVisible(false);
				dessertsPanel.setVisible(true);
			}
		});
		panel.add(btnDesserts);
	}

	private void setDrinksPane() {
		drinksPanel.setLayout(null);
		drinksPanel.setVisible(false);
		lblHorC = new JLabel("Hot or cold");
		lblHorC.setBounds(10, 11, 87, 14);
		drinksPanel.add(lblHorC);
		
		btngrpHorC = new ButtonGroup();
		
		rdbtnHot = new JRadioButton("Hot");
		rdbtnHot.setBounds(81, 7, 58, 23);
		rdbtnHot.setActionCommand("H");
		rdbtnHot.setEnabled(false);
		drinksPanel.add(rdbtnHot);
		btngrpHorC.add(rdbtnHot);
		
		rdbtnCold = new JRadioButton("Cold");
		rdbtnCold.setBounds(141, 7, 69, 23);
		rdbtnCold.setActionCommand("C");
		rdbtnCold.setEnabled(false);
		drinksPanel.add(rdbtnCold);
		btngrpHorC.add(rdbtnCold);
		
		lblSize = new JLabel("Size");
		lblSize.setBounds(10, 36, 52, 14);
		drinksPanel.add(lblSize);
		
		btngrpSize = new ButtonGroup();
		
		rdbtnL = new JRadioButton("L");
		rdbtnL.setActionCommand("L");
		rdbtnL.setBounds(69, 32, 43, 23);
		drinksPanel.add(rdbtnL);
		btngrpSize.add(rdbtnL);
		
		rdbtnM = new JRadioButton("M");
		rdbtnM.setActionCommand("M");
		rdbtnM.setBounds(114, 32, 47, 23);
		drinksPanel.add(rdbtnM);
		btngrpSize.add(rdbtnM);
		
		rdbtnS = new JRadioButton("S");
		rdbtnS.setActionCommand("S");
		rdbtnS.setBounds(163, 32, 47, 23);
		drinksPanel.add(rdbtnS);
		btngrpSize.add(rdbtnS);
		
		btnAddDrinkToCart = new JButton("add to cart");
		btnAddDrinkToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedButton == null) {
					JOptionPane.showMessageDialog(contentPane, "you have to select a drink first");
					return;
				}else if (btngrpHorC.getSelection()== null || btngrpSize.getSelection()==null) {
					JOptionPane.showMessageDialog(contentPane, "you have to select hot or cold and a size");
					return;
				}
				for (Drink drink : drinks) {
					if (drink.name.equals(selectedButton.getText())){
						double price = drink.getPrice() * drinkSize();
						total += price;
						lblTotal.setText("Total: " + Double.toString(total));
						listModel.addElement(drink.name+", "+ price
								+", "+ btngrpSize.getSelection().getActionCommand()
								+", "+ btngrpHorC.getSelection().getActionCommand());
						btngrpHorC.clearSelection();
						btngrpSize.clearSelection();
						selectedButton=null;
					}
				}
				list.setModel(listModel);
			}
		});
				
		btnAddDrinkToCart.setBounds(337, 11, 100, 33);
		drinksPanel.add(btnAddDrinkToCart);
	}
}
