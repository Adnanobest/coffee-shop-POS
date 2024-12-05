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
	}


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDrinkName;
	private JTextField txtPrice;
	private JTextField txtDessertName;
	private JTextField txtDessertPrice;
	private JLayeredPane addPane;
	private JLayeredPane addDessertPane;	
	private JLayeredPane addDrinkPane;
	JButton btnAddDrink;
	JButton btnAddDessert;
	Font font = new Font("Tahoma", Font.PLAIN, 13);
	DefaultListModel<String> listModel = new DefaultListModel<>();

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

		addDrinkPane = new JLayeredPane();
		addDrinkPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDrinkPane.setBounds(138, 0, 207, 311);
		contentPane.add(addDrinkPane);
		addDrinkPane.setVisible(false);
		
		addDessertPane = new JLayeredPane();
		addDessertPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDessertPane.setBounds(138, 0, 207, 311);
		contentPane.add(addDessertPane);
		addDessertPane.setVisible(false);
		
		JLayeredPane listPane = new JLayeredPane();
		listPane.setBounds(345, 0, 207, 311);
		contentPane.add(listPane);
		
		JList<String> list = new JList<String>(listModel);
		list.setBounds(0, 0, 207, 249);
		listPane.add(list);
		
		JButton btnRemove = new JButton("Remove Selected Item");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRemove.setBounds(106, 269, 96, 42);
		listPane.add(btnRemove);
		
		JButton btnChangePrice = new JButton("<html>Change <br>Price");
		btnChangePrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChangePrice.setBounds(5, 269, 96, 42);
		listPane.add(btnChangePrice);

		addPane = new JLayeredPane();
		addPane.setBounds(0, 0,138,311);
		contentPane.add(addPane);
		
		btnAddDrink = new JButton("add drink");
		btnAddDrink.setFont(font);
		btnAddDrink.setBounds(5, 11,128, 42 );
		addPane.add(btnAddDrink);
		btnAddDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDessertPane.setVisible(false);
				addDrinkPane.setVisible(true);
			}
		});
		
		btnAddDessert = new JButton("add dessert");
		btnAddDessert.setFont(font);
		btnAddDessert.setBounds(5, 65,128, 42 );
		addPane.add(btnAddDessert);
		
		JButton btnRefreshList = new JButton("Refresh List");
		btnRefreshList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh(drinks, desserts);
			}
		});
		btnRefreshList.setFont(font);
		btnRefreshList.setBounds(5, 119, 128, 42);
		addPane.add(btnRefreshList);
		btnAddDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDrinkPane.setVisible(false);
				addDessertPane.setVisible(true);
			}
		});

	/*	ImageIcon icon = new ImageIcon(new ImageIcon("pics/coffee.jpg").getImage().getScaledInstance(118, 28, Image.SCALE_DEFAULT));
		JLabel label= new JLabel(new ImageIcon(new ImageIcon("pics/coffee.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
		contentPane.add(label);
		label.setBounds(100, 100, 150, 150); */


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
					POS.ad(name);
					txtDessertName.setText("");
					txtDessertPrice.setText("");
				}
			}
		});
				
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
		
		JLabel lblSize = new JLabel("Hot or cold", JLabel.CENTER);
		lblSize.setFont(font);
		lblSize.setBounds(65, 140, 77, 14);
		addDrinkPane.add(lblSize);
		
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
				if(isNum(txtPrice.getText()) &&
						(chckbxHot.isSelected() || chckbxCold.isSelected()) ) {
					String name=txtDrinkName.getText();
					Double price = Double.parseDouble(txtPrice.getText());
					String horc="";
					if (chckbxHot.isSelected()) {
						horc+="H"+" ";
					}if (chckbxCold.isSelected()) {
						horc+="C";
					}
					
					drinks.add(new Drink(name,price,horc));
					POS.ad(name);
					JOptionPane.showMessageDialog(contentPane, drinks.getLast().name+" Saved with price "
							+drinks.getLast().getPrice()+" with available sizes "+ horc);
					refresh(drinks, desserts);
					txtDrinkName.setText("");
					txtPrice.setText("");
					chckbxHot.setSelected(false);
					chckbxCold.setSelected(false);
				}else {
					JOptionPane.showMessageDialog(contentPane, "you must select either hot, cold or both");
				}
			}
		});
		
	
	}
}

		
/*
 Repository https://github.com/Adnanobest/coffee-shop-POS.git
	Create a pull request for 'WIP' on GitHub by visiting:
     https://github.com/Adnanobest/coffee-shop-POS/pull/new/WIP
*/