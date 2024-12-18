package pack1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;

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
	
	ActionListener drinkBtnActionListener(Drink drink, JButton btndrink) {
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

	void removeButton(String x, int z) {
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
	
	void addDrink(String name, Double price, String horc) {
		Drink drink = new Drink(name,price,horc);
		drinks.add(drink);
		
		JButton btndrink = new JButton(name);
		btndrink.addActionListener(drinkBtnActionListener(drink, btndrink));
		drinkButtons.add(btndrink);
		btndrink.setToolTipText(price.toString());
		resetDrinks();
	}
	
	void resetDrinks() {
		drinkMenuPanel.removeAll();
		drinkMenuPanel.repaint();
		
		if (drinkButtons.isEmpty()) {
			return;
		}
		int i=0;
		for (JButton button: drinkButtons) {
			button.setToolTipText(drinks.get(i++).getPrice().toString());
			drinkMenuPanel.add(button, "grow");
		}
		//drinkMenuPanel.setPreferredSize(new Dimension(625, height+70));
	}

	void addDesserts(String name, Double price) {
		desserts.add(new Dessert(name,price));

		JButton dessert = new JButton(name);
		dessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.addElement(name +", "+ df.format(price));
				total+=price;
				
				lblTotal.setText("Total: "+df.format(total));
				list.setModel(listModel);
			}
		});
		
		dessertButtons.add(dessert);
		resetDesserts();
	}
	
	void resetDesserts() {
		dessertMenuPanel.removeAll();
		dessertMenuPanel.repaint();
		
		btnWidth=drinkScroll.getWidth()/7;
		spc=btnWidth/7;

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
			dessertMenuPanel.add(x);
			x.setBounds(pos, height, btnWidth, 50);
			pos+=btnWidth+spc;
			dessertMenuPanel.setPreferredSize(new Dimension(dessertsPanel.getWidth(), height+75));
		}
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel drinksPanel;
	private JPanel drinkMenuPanel;
	private JPanel dessertsPanel;
	private JPanel totalPanel;
	private JScrollPane drinkScroll;
	private JLabel lblHorC;
	private ButtonGroup btngrpHorC;
	private JRadioButton rdbtnHot;
	private JRadioButton rdbtnCold;
	private JLabel lblSize;
	private ButtonGroup btngrpSize;
	private JRadioButton rdbtnL;
	private JRadioButton rdbtnM;
	private JRadioButton rdbtnS;
	private JButton selectedButton = null;
	JLabel lblTotal;
	double total;
	ArrayList<Object> cart = new ArrayList<Object>(0);
	JList<String> list;
	ArrayList<Drink> drinks = new ArrayList<Drink>(0);
	ArrayList<Dessert> desserts = new ArrayList<Dessert>(0);
	ArrayList<JButton> drinkButtons = new ArrayList<JButton>(0);
	ArrayList<JButton> dessertButtons = new ArrayList<JButton>(0);
	int btnWidth=85;
	JButton btnRefresh;
	int spc=24;
	int height= 5;
	private JButton btnAddDrinkToCart;
	private DefaultListModel<String> listModel= new DefaultListModel<>();
	DecimalFormat df = new DecimalFormat("#.##");
	private JLabel lblDrink;
	private JLabel lblDessert;
	private JScrollPane cartScroll;
	private JPanel dessertMenuPanel;
	private JScrollPane dessertScroll;
	Font font = new Font("Tahoma", Font.PLAIN, 13);
	private JLabel lblCart;

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
		setBounds(100, 100, 1016, 500);
		setMinimumSize(new Dimension(999, 500));
		contentPane = new JPanel(new MigLayout("debug, fill, insets 0, hidemode 3", "[:12.5%:250][62.5%][25%]", "[]"));


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
		
		for (int i = 0; i < 100; i++) {
			addDrink("drink"+i, 6.5, "H C");
			addDesserts("dessert "+i, 5.2);
		}
	}

	private void setDessertPanel() {
		dessertsPanel.setLayout(new MigLayout("debug, fill, insets 0", "", ""));
		dessertsPanel.setVisible(false);
		
		dessertMenuPanel = new JPanel();
		dessertMenuPanel.setLayout(new MigLayout("debug, fill, insets 0, wrap 6", "", ""));
		
		dessertScroll = new JScrollPane(dessertMenuPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
										, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dessertScroll.getVerticalScrollBar().setUnitIncrement(16);
		dessertsPanel.add(dessertScroll, "grow");
		
		dessertMenuPanel.add(new JButton("ddd"), "grow");
		lblDessert = new JLabel("Click on   the selected dessert");
		dessertScroll.setColumnHeaderView(lblDessert);

	}

	private void setTotalPanel() {
		totalPanel.setLayout(new MigLayout("debug, wrap 1, fill", "[]", "[85%][]"));
		
		
		list = new JList<String>();
		
		cartScroll = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
											 , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		totalPanel.add(cartScroll, "grow");
		
		lblCart = new JLabel("Items in cart:");
		lblCart.setFont(font);
		cartScroll.setColumnHeaderView(lblCart);

		lblTotal = new JLabel("Total: 0");
		lblTotal.setFont(font);
		totalPanel.add(lblTotal, "grow, split");
		
		JButton btnRemove = new JButton("Remove item");
		btnRemove.setFont(font);
		btnRemove.setMnemonic('V');
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedValue()==null) {
					JOptionPane.showMessageDialog(contentPane, "Select an item to remove");
					return;
				}
				total -= Double.parseDouble(list.getSelectedValue().split(",")[1]);
				listModel.remove(list.getSelectedIndex());
				lblTotal.setText("Total: "+ df.format(total));
				list.setModel(listModel);
			}
		});
		totalPanel.add(btnRemove, "grow");
	}

	private void setPanel(POS pos) {
		panel.setLayout(null);
		
		JButton btnDrinks = new JButton("Drinks");
		btnDrinks.setFont(font);
		btnDrinks.setMnemonic('K');
		btnDrinks.setBounds(14, 11, 97, 35);
		btnDrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dessertsPanel.setVisible(false);
				drinksPanel.setVisible(true);
			}
		});
		panel.add(btnDrinks);
		
		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.setFont(font);
		btnDesserts.setMnemonic('R');
		btnDesserts.setBounds(14, 57, 97, 35);
		btnDesserts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drinksPanel.setVisible(false);
				dessertsPanel.setVisible(true);
			}
		});
		panel.add(btnDesserts);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(14, 415, 97, 35);
		panel.add(btnAdmin);
		btnAdmin.setFont(font);
		btnAdmin.setMnemonic('N');
		btnAdmin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LoginFrame x = new LoginFrame(drinks, desserts, pos);
						x.setVisible(true);
					}
				});
	}

	private void setDrinksPane() {
		drinksPanel.setLayout(new MigLayout("debug, fill, insets 5", "", "[5%!][5%!][5%!][]"));
		drinksPanel.setVisible(false);
		
		btngrpHorC = new ButtonGroup();
		
		lblDrink = new JLabel("Select the drink, the size and either hot or cold then press add to cart");
		lblDrink.setFont(font);
		drinksPanel.add(lblDrink, "span 3, grow");

		lblHorC = new JLabel("Hot or Cold");
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
		
		lblSize = new JLabel("Size");
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
		
		btnAddDrinkToCart = new JButton("Add to cart");
		btnAddDrinkToCart.setFont(font);
		btnAddDrinkToCart.setMnemonic('A');
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
						lblTotal.setText("Total: " + df.format(total));
						listModel.addElement(drink.name+", "+ price
								+", "+ btngrpHorC.getSelection().getActionCommand()
								+", "+ btngrpSize.getSelection().getActionCommand());
						list.setModel(listModel);
						btngrpHorC.clearSelection();
						btngrpSize.clearSelection();
						selectedButton=null;
						return;
					}
				}
			}
		});
				
		drinksPanel.add(btnAddDrinkToCart, "cell 3 0 1 3, grow");
		
		drinkMenuPanel = new JPanel();
		
		drinkMenuPanel.setLayout(null);
		drinkScroll=new JScrollPane(drinkMenuPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
										, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		drinkScroll.getVerticalScrollBar().setUnitIncrement(16);
		drinksPanel.add(drinkScroll, "cell 0 3 4 1, grow");
	}
}