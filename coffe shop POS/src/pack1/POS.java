package pack1;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

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
	
	static ActionListener drinkBtnActionListener(Drink drink) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
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
	
//	static ActionListener drinkBtnActionListener(Drink drink) {
//		return new ActionListener() {
//			public void actionPerformed(ActionEvent e) { 
//				
//			}
//		};
//	}

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
		btndrink.addActionListener(drinkBtnActionListener(drink));
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
		btngrpHorC.add(rdbtnHot);
		drinksPanel.add(rdbtnCold);
		btngrpHorC.add(rdbtnCold);
		drinksPanel.add(lblSize);
		drinksPanel.add(rdbtnL);
		btngrpSize.add(rdbtnL);
		drinksPanel.add(rdbtnM);
		btngrpSize.add(rdbtnM);
		drinksPanel.add(rdbtnS);
		btngrpSize.add(rdbtnS);

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
		
		
		dessertButtons.add(dessert);
		resetDesserts();
	}
	
	static void resetDesserts() {
		dessertsPanel.removeAll();
		dessertsPanel.repaint();
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
	private static JScrollPane scroll;
	private static JLabel lblHorC;
	private static ButtonGroup btngrpHorC;
	private static JRadioButton rdbtnHot;
	private static JRadioButton rdbtnCold;
	private static JLabel lblSize;
	private static ButtonGroup btngrpSize;
	private static JRadioButton rdbtnL;
	private static JRadioButton rdbtnM;
	private static JRadioButton rdbtnS;
	static double total;
	static ArrayList<Object> cart = new ArrayList<Object>(0);
	
	static ArrayList<Drink> drinks = new ArrayList<Drink>(0);
	static ArrayList<Dessert> desserts = new ArrayList<Dessert>(0);
	static ArrayList<JButton> drinkButtons = new ArrayList<JButton>(0);
	static ArrayList<JButton> dessertButtons = new ArrayList<JButton>(0);
	static int btnWidth=85;
	JButton btnRefresh;
	static int spc=24;
	static int height= 5;
	private JButton btnAddDessertToCart;
	private JButton btnAddDrinkToCart;

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
		dessertsPanel.setLayout(null);
		
		btnAddDessertToCart = new JButton("add to cart");
		btnAddDessertToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				for (JButton button : dessertButtons) {
					if (button.isSelected()) {
						total += Double.parseDouble(button.getActionCommand());
						cart.add(desserts.get(i));
					}
					i++;
				}
			}
		});
		btnAddDessertToCart.setBounds(235, 5, 85, 23);
		dessertsPanel.add(btnAddDessertToCart);

		totalPanel = new JPanel();
		totalPanel.setBounds(666, 0, 333, 461);
		contentPane.add(totalPanel);
		
		setTotalPanel();
		
//		scroll = new JScrollPane(drinkPanel);
//		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
//		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		scroll.setVisible(true);

	}

	private void setTotalPanel() {
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(122, 438, 61, 23);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame x = new LoginFrame(drinks, desserts);
				x.setVisible(true);
			}
		});
		totalPanel.setLayout(null);
		totalPanel.add(btnAdmin);
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
		lblHorC = new JLabel("Hot or cold");
		lblHorC.setBounds(10, 11, 52, 14);
		drinksPanel.add(lblHorC);
		
		btngrpHorC = new ButtonGroup();
		
		rdbtnHot = new JRadioButton("Hot");
		rdbtnHot.setBounds(69, 7, 43, 23);
		rdbtnHot.setEnabled(false);
		drinksPanel.add(rdbtnHot);
		btngrpHorC.add(rdbtnHot);
		
		rdbtnCold = new JRadioButton("Cold");
		rdbtnCold.setBounds(114, 7, 47, 23);
		rdbtnCold.setEnabled(false);
		drinksPanel.add(rdbtnCold);
		btngrpHorC.add(rdbtnCold);
		
		lblSize = new JLabel("Size");
		lblSize.setBounds(10, 36, 52, 14);
		drinksPanel.add(lblSize);
		
		btngrpSize = new ButtonGroup();
		
		rdbtnL = new JRadioButton("L");
		rdbtnL.setBounds(69, 32, 43, 23);
		drinksPanel.add(rdbtnL);
		btngrpSize.add(rdbtnL);
		
		rdbtnM = new JRadioButton("M");
		rdbtnM.setBounds(114, 32, 47, 23);
		drinksPanel.add(rdbtnM);
		btngrpSize.add(rdbtnM);
		
		rdbtnS = new JRadioButton("S");
		rdbtnS.setBounds(163, 32, 47, 23);
		drinksPanel.add(rdbtnS);
		btngrpSize.add(rdbtnS);
		
		btnAddDrinkToCart = new JButton("add to cart");
		btnAddDrinkToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btngrpHorC.getSelection().equals(null) || btngrpSize.getSelection().equals(null)) {
					JOptionPane.showMessageDialog(contentPane, "you have to select hot or cold and a size");
					return;
				}int i=0;
				for (JButton button : drinkButtons) {
					if (button.isSelected()) {
						total += (Double.parseDouble(button.getActionCommand())) * drinkSize();
						cart.add(drinks.get(i));
					}
					i++;
				}
			}
		});
		btnAddDrinkToCart.setBounds(337, 32, 89, 23);
		drinksPanel.add(btnAddDrinkToCart);
	}
}
