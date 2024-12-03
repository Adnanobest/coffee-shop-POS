package pack1;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class ManagmentFrame extends JFrame {

	private boolean isAllInt(String str) {
		char[] x = str.toCharArray();
		for (int i=0;i<x.length;i++) {
			if (x[i]-'0'<0 || x[i]-'0'>9) {
				JOptionPane.showMessageDialog(contentPane, "price can only be numbers");
				return false;
			}
		}
		return true;
	}


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDrinkName;
	private JTextField txtPrice;
	private JTextField txtDessertName;
	private JTextField txtDessertPrice;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 * @param menu 
	 */
	public ManagmentFrame(ArrayList<Menu> menu) {
		setTitle("Management");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane addDrinkPane = new JLayeredPane();
		addDrinkPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDrinkPane.setBounds(118, 0, 199, 261);
		contentPane.add(addDrinkPane);
		addDrinkPane.setVisible(false);
		
		JLayeredPane addDessertPane = new JLayeredPane();
		addDessertPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDessertPane.setBounds(118, 0, 199, 261);
		contentPane.add(addDessertPane);
		addDessertPane.setVisible(false);

		JButton btnAddDrink = new JButton("add drink");
		btnAddDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDessertPane.setVisible(false);
				addDrinkPane.setVisible(true);
			}
		});
		btnAddDrink.setBounds(0, 11, 118, 28);
		contentPane.add(btnAddDrink);
		
		JButton btnAddDessert = new JButton("add dessert");
		btnAddDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDrinkPane.setVisible(false);
				addDessertPane.setVisible(true);
			}
		});
		btnAddDessert.setBounds(0, 44, 118, 28);
		contentPane.add(btnAddDessert);


		JLabel lblDessertName = new JLabel("Dessert%nname");
		lblDessertName.setBounds(10, 14, 62, 14);
		addDessertPane.add(lblDessertName);
		
		txtDessertName = new JTextField();
		txtDessertName.setBounds(82, 11, 105, 20);
		addDessertPane.add(txtDessertName);
		txtDessertName.setColumns(10);
		
		JLabel lblDessertPrice = new JLabel("Price");
		lblDessertPrice.setBounds(10, 43, 46, 14);
		addDessertPane.add(lblDessertPrice);
		
		txtDessertPrice = new JTextField();
		txtDessertPrice.setBounds(82, 40, 105, 20);
		addDessertPane.add(txtDessertPrice);
		txtDessertPrice.setColumns(10);
				
		JButton btnSaveDessert = new JButton("Save Dessert");
		btnSaveDessert.setBounds(44, 222, 118, 28);
		addDessertPane.add(btnSaveDessert);

		btnSaveDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isAllInt(txtDessertPrice.getText())) {
					String name=txtDessertName.getText();
					int price = Integer.parseInt(txtDessertPrice.getText());
					menu.add(new Menu(name,price));
					POS.ad(name);
					txtDessertName.setText("");
					txtDessertPrice.setText("");
				}
			}
		});
				
		JLabel lblDrinkName = new JLabel("drink name");
		lblDrinkName.setBounds(10, 14, 62, 14);
		addDrinkPane.add(lblDrinkName);
		
		txtDrinkName = new JTextField();
		txtDrinkName.setBounds(82, 11, 105, 20);
		addDrinkPane.add(txtDrinkName);
		txtDrinkName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 43, 46, 14);
		addDrinkPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(82, 40, 105, 20);
		addDrinkPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblSize = new JLabel("Available Sizes");
		lblSize.setBounds(10, 68, 76, 14);
		addDrinkPane.add(lblSize);
		
		JButton btnSaveDrink = new JButton("Save Drink");
		btnSaveDrink.setBounds(44, 222, 118, 28);
		addDrinkPane.add(btnSaveDrink);

		ArrayList<JCheckBox> sizes = new ArrayList<JCheckBox>();
		
		JCheckBox chckbxS = new JCheckBox("S");
		chckbxS.setBounds(82, 64, 38, 23);
		addDrinkPane.add(chckbxS);
		sizes.add(chckbxS);
		
		JCheckBox chckbxM = new JCheckBox("M");
		chckbxM.setBounds(82, 89, 38, 23);
		addDrinkPane.add(chckbxM);
		sizes.add(chckbxM);
		
		JCheckBox chckbxL = new JCheckBox("L");
		chckbxL.setBounds(82, 115, 38, 23);
		addDrinkPane.add(chckbxL);
		sizes.add(chckbxL);
				
		btnSaveDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isAllInt(txtPrice.getText())) {
					String name=txtDrinkName.getText();
					int price = Integer.parseInt(txtPrice.getText());
					menu.add(new Menu(name,price));
					POS.ad(name);
					txtDrinkName.setText("");
					txtPrice.setText("");
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