package pack1;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDrinkName;
	private JTextField txtDrinkPrice;
	private JTextField txtDessertName;
	private JTextField txtDessertPrice;
	DefaultListModel<String> listModel = new DefaultListModel<>();
	static JList<String> list;
	ArrayList<Drink> drinks;
	ArrayList<Dessert> desserts;
	JPanel addPane;
	JPanel addDrinkPane;
	JPanel addDessertPane;
	JPanel listPane;
	
	Font font = new Font("Tahoma", Font.PLAIN, 13);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Test(ArrayList<Drink> drinksaAL, ArrayList<Dessert> dessertsAL) {
		setTitle("Management");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 568, 350);
		contentPane = new JPanel();
		contentPane.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				resize();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		drinks=drinksaAL;
		desserts=dessertsAL;

		addPane = new JPanel();
		addPane.setBounds(0, 0,138,311);
		contentPane.add(addPane);

		addDrinkPane = new JPanel();
		addDrinkPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDrinkPane.setBounds(138, 0, 207, 311);
		contentPane.add(addDrinkPane);
		addDrinkPane.setVisible(false);
		
		addDessertPane = new JPanel();
		addDessertPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDessertPane.setBounds(138, 0, 207, 311);
		contentPane.add(addDessertPane);
		addDessertPane.setVisible(false);
		
		listPane = new JPanel();
		listPane.setBounds(345, 0, 207, 311);
		contentPane.add(listPane);
		
		addPane(addDrinkPane, addDessertPane, addPane);

		addDessertPane(addDessertPane);

		addDrinkPane(addDrinkPane);

		listPane(listPane);

	
	}
	
	protected void resize() {
		
		addPane.setSize(contentPane.getWidth()/4, contentPane.getHeight());
		addDrinkPane.setBounds(addPane.getWidth(), 0, contentPane.getWidth()*3/8, contentPane.getHeight());
		addDessertPane.setBounds(addPane.getWidth(), 0, contentPane.getWidth()*3/8, contentPane.getHeight());
		listPane.setBounds(contentPane.getWidth()*5/8, 0, contentPane.getWidth()*3/8, contentPane.getHeight());		
	}

	private boolean isNum(String str) {
		int dot=0;
		for (char x : str.toCharArray()) {
			if(x=='.') {
				if (++dot>1) {
					JOptionPane.showMessageDialog(contentPane, "price can only be a number");
					return false;
				}
			}else if (x-'0'<0 || x-'0'>9) {
					JOptionPane.showMessageDialog(contentPane, "price can only be a number");
					return false;
			}
		}
		return true;
	}

	private void refresh() {
		listModel.clear();
		listModel.addElement("Drinks:");
		for (Drink x : drinks) {
			listModel.addElement(x.name +", price: "+ x.getPrice());
		}
		listModel.addElement("Desserts:");
		for (Dessert x : desserts) {
			listModel.addElement(x.name +", price: "+ x.getPrice());
		}
		
		list.setModel(listModel);
	}
	
	private void addPane(JPanel addDrinkPane,
			JPanel addDessertPane, JPanel addPane) {
		addPane.setLayout(null);
		JButton btnAddDrink = new JButton("add drink");
		btnAddDrink.setMnemonic('K');
		btnAddDrink.setFont(font);
		btnAddDrink.setBounds(5, 103,128, 42 );
		addPane.add(btnAddDrink);
		btnAddDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDessertPane.setVisible(false);
				addDrinkPane.setVisible(true);
			}
		});
		
		JButton btnAddDessert = new JButton("add dessert");
		btnAddDessert.setMnemonic('R');
		btnAddDessert.setFont(font);
		btnAddDessert.setBounds(5, 161,128, 42 );
		addPane.add(btnAddDessert);
		btnAddDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDrinkPane.setVisible(false);
				addDessertPane.setVisible(true);
			}
		});
	}
	
	private void listPane(JPanel listPane) {
		listPane.setLayout(null);
		
		list = new JList<String>(listModel);
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBounds(0, 0, 207, 249);
		
		listPane.add(scroll);
		
		JLabel lblSelected = new JLabel("for selected item:");
		lblSelected.setBounds(5, 255, 101, 16);
		listPane.add(lblSelected);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setMnemonic('V');
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();
				refresh();
			}
		});
		btnRemove.setFont(font);
		btnRemove.setBounds(106, 269, 96, 42);
		listPane.add(btnRemove);
		
		JButton btnChangePrice = new JButton("<html><p style='text-align:center;'>Change<br>Price</p>");
		btnChangePrice.setMnemonic('G');
		btnChangePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePrice();
				refresh();
			}
		});
		btnChangePrice.setFont(font);
		btnChangePrice.setBounds(5, 269, 96, 42);
		listPane.add(btnChangePrice);

	}

	private void addDrinkPane(JPanel addDrinkPane) {
		addDrinkPane.setLayout(null);
		JLabel lblDrinkName = new JLabel("Drink name", JLabel.CENTER);
		lblDrinkName.setFont(font);
		lblDrinkName.setBounds(25, 8, 65, 16);
		lblDrinkName.setDisplayedMnemonic('N');
		addDrinkPane.add(lblDrinkName);
		
		txtDrinkName = new JTextField();
		txtDrinkName.setBounds(95, 6, 86, 20);
		addDrinkPane.add(txtDrinkName);
		txtDrinkName.setColumns(10);
		
		lblDrinkName.setLabelFor(txtDrinkName);
		
		JLabel lblDrinkPrice = new JLabel("Price for size: M", JLabel.CENTER);
		lblDrinkPrice.setFont(font);
		lblDrinkPrice.setBounds(11, 33, 93, 16);
		lblDrinkPrice.setDisplayedMnemonic('P');
		addDrinkPane.add(lblDrinkPrice);
		
		txtDrinkPrice = new JTextField();
		txtDrinkPrice.setBounds(109, 31, 86, 20);
		addDrinkPane.add(txtDrinkPrice);
		txtDrinkPrice.setColumns(10);
		lblDrinkPrice.setLabelFor(txtDrinkPrice);
		
		JLabel lblHorC = new JLabel("Hot or cold", JLabel.CENTER);
		lblHorC.setFont(font);
		lblHorC.setBounds(19, 60, 62, 16);
		addDrinkPane.add(lblHorC);
		
		JCheckBox chckbxHot = new JCheckBox("Hot");
		chckbxHot.setFont(font);
		chckbxHot.setBounds(86, 56, 45, 25);
		addDrinkPane.add(chckbxHot);
		chckbxHot.setMnemonic('H');
		chckbxHot.setActionCommand("H");
		
		JCheckBox chckbxCold = new JCheckBox("Cold");
		chckbxCold.setMnemonic('C');
		chckbxCold.setFont(font);
		chckbxCold.setBounds(136, 56, 51, 25);
		addDrinkPane.add(chckbxCold);

		JButton btnSaveDrink = new JButton("Save Drink");
		btnSaveDrink.setMnemonic('S');
		btnSaveDrink.setFont(font);
		btnSaveDrink.setBounds(57, 86, 93, 25);
		addDrinkPane.add(btnSaveDrink);
		btnSaveDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtDrinkName.getText().equals("")) {
					if(isNum(txtDrinkPrice.getText())) {
						if (chckbxHot.isSelected() || chckbxCold.isSelected()) {
							String name=txtDrinkName.getText();
							Double price = Double.parseDouble(txtDrinkPrice.getText());
							String horc="";
							if (chckbxHot.isSelected()) {
								horc+="H"+" ";
							}if (chckbxCold.isSelected()) {
								horc+="C";
							}
						
							POS.addDrink(name, price, horc);
							JOptionPane.showMessageDialog(contentPane, drinks.get(drinks.size()).name+" Saved with price "
									+drinks.get(drinks.size()).getPrice()+" with "+ horc +" available");
							refresh();
							txtDrinkName.setText("");
							txtDrinkPrice.setText("");
							chckbxHot.setSelected(false);
							chckbxCold.setSelected(false);
						}else {
						JOptionPane.showMessageDialog(contentPane, "you must select either hot, cold or both");
						}
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "you have to enter a name for the drink");
				}
			}
		});
	}

	private void addDessertPane(JPanel addDessertPane) {
		addDessertPane.setLayout(null);
		JLabel lblDessertName = new JLabel("Dessert name", JLabel.CENTER);
		lblDessertName.setFont(font);
		lblDessertName.setBounds(18, 8, 79, 16);
		lblDessertName.setDisplayedMnemonic('N');
		addDessertPane.add(lblDessertName);
		
		txtDessertName = new JTextField();
		txtDessertName.setBounds(102, 6, 86, 20);
		addDessertPane.add(txtDessertName);
		txtDessertName.setColumns(10);
		lblDessertName.setLabelFor(txtDessertName);
		
		JLabel lblDessertPrice = new JLabel("Price", JLabel.CENTER);
		lblDessertPrice.setFont(font);
		lblDessertPrice.setBounds(44, 33, 28, 16);
		lblDessertPrice.setDisplayedMnemonic('P');
		addDessertPane.add(lblDessertPrice);
		
		txtDessertPrice = new JTextField();
		txtDessertPrice.setBounds(77, 31, 86, 20);
		addDessertPane.add(txtDessertPrice);
		txtDessertPrice.setColumns(10);
		lblDessertPrice.setLabelFor(txtDessertPrice);
				
		JButton btnSaveDessert = new JButton("Save Dessert");
		btnSaveDessert.setMnemonic('S');
		btnSaveDessert.setFont(font);
		btnSaveDessert.setBounds(50, 56, 107, 25);
		addDessertPane.add(btnSaveDessert);

		btnSaveDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtDessertName.getText().equals("")) {
					if(isNum(txtDessertPrice.getText())) {
						int exist = alreadyExists(txtDessertName.getText());
						switch (exist) {
						case 0:
							String name=txtDessertName.getText();
							Double price = Double.parseDouble(txtDessertPrice.getText());
							POS.addDesserts(name, price);
							JOptionPane.showMessageDialog(contentPane, name+" Saved with price "
									+price);
							refresh();
							txtDessertName.setText("");
							txtDessertPrice.setText("");							
							break;
						case 1:
							JOptionPane.showMessageDialog(btnSaveDessert, "drink " +txtDessertName.getText()
									+" already exists");
							break;
						case 2:
							JOptionPane.showMessageDialog(btnSaveDessert, "dessert " +txtDessertName.getText()
									+" already exists");
						}
					}		
				}else {
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
			if (list.getSelectedIndex()<=drinks.size()) {
				for (Drink x : drinks) {
					if (x.name.equals(list.getSelectedValue().split(",")[0])) {
						JOptionPane.showMessageDialog(contentPane, list.getSelectedValue()+" deleted");
						drinks.remove(x);
						POS.removeButton(list.getSelectedValue().split(",")[0],0);
						return;
					}
				}
			}else {
				for (Dessert x : desserts) {
					if (x.name.equals(list.getSelectedValue().split(",")[0])) {
						JOptionPane.showMessageDialog(contentPane, list.getSelectedValue()+" deleted");
						desserts.remove(x);
						POS.removeButton(list.getSelectedValue().split(",")[0],1);
						return;
					}
				}
			}
		}
	}
	
	private void changePrice() {
		if(!list.isSelectionEmpty()) {
			if(list.getSelectedValue().equals("Drinks:")||list.getSelectedValue().equals("Desserts:")) {
				JOptionPane.showMessageDialog(contentPane, "Not Valid!");
			}
			else {
				String selected = list.getSelectedValue().split(",")[0];
				String str = JOptionPane.showInputDialog(contentPane, "enter new price for "+ selected);
				while (!isNum(str)) {
					str = JOptionPane.showInputDialog(contentPane, "enter new price for "+ selected);
				}
				double newPrice =Double.parseDouble(str);
				if(list.getSelectedIndex()<=drinks.size()) {
					for(Drink x : drinks) {
						if(x.name.equals(selected)) {
							x.setPrice(newPrice);
							JOptionPane.showMessageDialog(contentPane,  selected+"'s price changed to"+ x.getPrice());
							refresh();
							return;
						}
					}
				}else {
					for(Dessert x : desserts) {
						if(x.name.equals(selected)) {
							x.setPrice(newPrice);
							JOptionPane.showMessageDialog(contentPane,  selected+"'s price changed to"+ x.getPrice());
							refresh();
							return;
						}	
					}
				}
			}
			
		}
	}


}


		
/*
 Repository https://github.com/Adnanobest/coffee-shop-POS.git
	Create a pull request for 'WIP' on GitHub by visiting:
     https://github.com/Adnanobest/coffee-shop-POS/pull/new/WIP
*/