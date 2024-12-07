package pack1;

import java.awt.EventQueue;
//import java.awt.Image;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;

public class ManagmentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDrinkName;
	private JTextField txtPrice;
	private JTextField txtDessertName;
	private JTextField txtDessertPrice;
	DefaultListModel<String> listModel = new DefaultListModel<>();
	static JList<String> list;

	Font font = new Font("Tahoma", Font.PLAIN, 13);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagmentFrame frame = new ManagmentFrame(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManagmentFrame(ArrayList<Drink> drinks, ArrayList<Dessert> desserts) {
		setTitle("Management");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 568, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane addPane = new JLayeredPane();
		addPane.setBounds(0, 0,138,311);
		contentPane.add(addPane);

		JLayeredPane addDrinkPane = new JLayeredPane();
		addDrinkPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDrinkPane.setBounds(138, 0, 207, 311);
		contentPane.add(addDrinkPane);
		addDrinkPane.setVisible(false);
		
		JLayeredPane addDessertPane = new JLayeredPane();
		addDessertPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDessertPane.setBounds(138, 0, 207, 311);
		contentPane.add(addDessertPane);
		addDessertPane.setVisible(false);
		
		JLayeredPane listPane = new JLayeredPane();
		listPane.setBounds(345, 0, 207, 311);
		contentPane.add(listPane);
		
		addPane(drinks, desserts, addDrinkPane, addDessertPane, addPane);

		addDessertPane(desserts, drinks, addDessertPane);

		addDrinkPane(drinks, desserts, addDrinkPane);

		listPane(drinks, desserts, listPane);

	
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

	private void refresh(ArrayList<Drink> drinks, ArrayList<Dessert> desserts) {
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
	
	private void addPane(ArrayList<Drink> drinks, ArrayList<Dessert> desserts, JLayeredPane addDrinkPane,
			JLayeredPane addDessertPane, JLayeredPane addPane) {
		JButton btnAddDrink = new JButton("add drink");
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
	
	private void listPane(ArrayList<Drink> drinks, ArrayList<Dessert> desserts, JLayeredPane listPane) {
		list = new JList<String>(listModel);
		list.setBounds(0, 0, 207, 249);
		listPane.add(list);
		
		JLabel lblSelected = new JLabel("for selected item:");
		lblSelected.setBounds(5, 255, 101, 16);
		listPane.add(lblSelected);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(drinks, desserts);
				refresh(drinks, desserts);
			}
		});
		btnRemove.setFont(font);
		btnRemove.setBounds(106, 269, 96, 42);
		listPane.add(btnRemove);
		
		JButton btnChangePrice = new JButton("<html>Change<br>Price");
		btnChangePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePrice(drinks, desserts);
				refresh(drinks, desserts);
			}
		});
		btnChangePrice.setFont(font);
		btnChangePrice.setBounds(5, 269, 96, 42);
		listPane.add(btnChangePrice);	
	}

	private void addDrinkPane(ArrayList<Drink> drinks, ArrayList<Dessert> desserts, JLayeredPane addDrinkPane) {
		JLabel lblDrinkName = new JLabel("Drink name", JLabel.CENTER);
		lblDrinkName.setFont(font);
		lblDrinkName.setBounds(70, 22, 67, 16);
		addDrinkPane.add(lblDrinkName);
		
		txtDrinkName = new JTextField();
		txtDrinkName.setBounds(50, 50, 107, 20);
		addDrinkPane.add(txtDrinkName);
		txtDrinkName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price", JLabel.CENTER);
		lblPrice.setFont(font);
		lblPrice.setBounds(70, 82, 67, 14);
		addDrinkPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(50, 108, 107, 20);
		addDrinkPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblHorC = new JLabel("Hot or cold", JLabel.CENTER);
		lblHorC.setFont(font);
		lblHorC.setBounds(65, 140, 77, 14);
		addDrinkPane.add(lblHorC);
		
		JCheckBox chckbxHot = new JCheckBox("Hot");
		chckbxHot.setFont(font);
		chckbxHot.setBounds(53, 166, 45, 25);
		addDrinkPane.add(chckbxHot);
		chckbxHot.setActionCommand("H");
		
		JCheckBox chckbxCold = new JCheckBox("Cold");
		chckbxCold.setFont(font);
		chckbxCold.setBounds(103, 166, 51, 25);
		addDrinkPane.add(chckbxCold);

		JButton btnSaveDrink = new JButton("Save Drink");
		btnSaveDrink.setFont(font);
		btnSaveDrink.setBounds(40, 258, 127, 42);
		addDrinkPane.add(btnSaveDrink);
		btnSaveDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNum(txtPrice.getText())) {
					if (chckbxHot.isSelected() || chckbxCold.isSelected()) {
						String name=txtDrinkName.getText();
						Double price = Double.parseDouble(txtPrice.getText());
						String horc="";
						if (chckbxHot.isSelected()) {
							horc+="H"+" ";
						}if (chckbxCold.isSelected()) {
							horc+="C";
						}
					
						drinks.add(new Drink(name,price,horc));
						POS.adDrink(name);
						JOptionPane.showMessageDialog(contentPane, drinks.getLast().name+" Saved with price "
								+drinks.getLast().getPrice()+" with "+ horc +" available");
						refresh(drinks, desserts);
						txtDrinkName.setText("");
						txtPrice.setText("");
						chckbxHot.setSelected(false);
						chckbxCold.setSelected(false);
					}else {
					JOptionPane.showMessageDialog(contentPane, "you must select either hot, cold or both");
					}
				}
			}
		});
	}

	private void addDessertPane(ArrayList<Dessert> desserts, ArrayList<Drink> drinks, JLayeredPane addDessertPane) {
		JLabel lblDessertName = new JLabel("Dessert name", JLabel.CENTER);
		lblDessertName.setFont(font);
		lblDessertName.setBounds(64, 22, 79, 16);
		addDessertPane.add(lblDessertName);
		
		txtDessertName = new JTextField();
		txtDessertName.setBounds(50, 50, 107, 20);
		addDessertPane.add(txtDessertName);
		txtDessertName.setColumns(10);
		
		JLabel lblDessertPrice = new JLabel("Price", JLabel.CENTER);
		lblDessertPrice.setFont(font);
		lblDessertPrice.setBounds(70, 82, 67, 14);
		addDessertPane.add(lblDessertPrice);
		
		txtDessertPrice = new JTextField();
		txtDessertPrice.setBounds(50, 108, 107, 20);
		addDessertPane.add(txtDessertPrice);
		txtDessertPrice.setColumns(10);
				
		JButton btnSaveDessert = new JButton("Save Dessert");
		btnSaveDessert.setFont(font);
		btnSaveDessert.setBounds(40, 258, 127, 42);
		addDessertPane.add(btnSaveDessert);

		btnSaveDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNum(txtDessertPrice.getText())) {
					String name=txtDessertName.getText();
					Double price = Double.parseDouble(txtDessertPrice.getText());
					desserts.add(new Dessert(name,price));
					POS.adDesserts(name);
					JOptionPane.showMessageDialog(contentPane, desserts.getLast().name+" Saved with price "
							+desserts.getLast().getPrice());
					refresh(drinks, desserts);
					txtDessertName.setText("");
					txtDessertPrice.setText("");
				}
			}
		});
	}


	private void remove(ArrayList<Drink> drinks, ArrayList<Dessert> desserts) {
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
	
	private void changePrice(ArrayList<Drink> drinks, ArrayList<Dessert> desserts) {
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
							refresh(drinks, desserts);
							return;
						}
					}
				}else {
					for(Dessert x : desserts) {
						if(x.name.equals(selected)) {
							x.setPrice(newPrice);
							JOptionPane.showMessageDialog(contentPane,  selected+"'s price changed to"+ x.getPrice());
							refresh(drinks, desserts);
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