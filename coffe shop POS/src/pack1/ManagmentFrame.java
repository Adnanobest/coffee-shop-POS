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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
	private JLayeredPane addPane;
	private JLayeredPane addDessertPane;	
	private JLayeredPane addDrinkPane;
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 * @param menu 
	 */
	public ManagmentFrame(ArrayList<Drink> drinks, ArrayList<Dessert> desserts) {
		setTitle("Management");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				addPane.setBounds(0, 0,contentPane.getWidth()/5, contentPane.getHeight());
				addDrinkPane.setBounds(contentPane.getWidth()/5, 0
						,(int) (contentPane.getWidth()/2.5), contentPane.getHeight());
				addDessertPane.setBounds(contentPane.getWidth()/5, 0
						,(int) (contentPane.getWidth()/2.5), contentPane.getHeight());
				
				
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		addDrinkPane = new JLayeredPane();
		addDrinkPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDrinkPane.setBounds(90, 0, 180, 300);
		contentPane.add(addDrinkPane);
		addDrinkPane.setVisible(false);
		
		addDessertPane = new JLayeredPane();
		addDessertPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		addDessertPane.setBounds(90, 0, 180, (contentPane.getHeight()));
		contentPane.add(addDessertPane);
		addDessertPane.setVisible(false);

		addPane = new JLayeredPane();
		addPane.setBounds(0, 0,90,261);
		contentPane.add(addPane);
		
		JButton btnAddDrink = new JButton("add drink");
		btnAddDrink.setBounds(5, 5,(int) (addPane.getSize().getWidth() -10), (int) (addPane.getSize().getHeight()/10) );
		addPane.add(btnAddDrink);
		btnAddDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDessertPane.setVisible(false);
				addDrinkPane.setVisible(true);
			}
		});
		
		JButton btnAddDessert = new JButton("add dessert");
		btnAddDessert.setBounds(5, 50,(int) (addPane.getSize().getWidth() -10), 10);
		addPane.add(btnAddDessert);
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


		JLabel lblDessertName = new JLabel("Dessert name");
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
				if(isAllInt(txtDessertPrice.getText())) {
					String name=txtDessertName.getText();
					int price = Integer.parseInt(txtDessertPrice.getText());
					desserts.add(new Dessert(name,price));
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
		
		JLabel lblSize = new JLabel("Hot or cold");
		lblSize.setBounds(10, 68, 76, 14);
		addDrinkPane.add(lblSize);
		
		JCheckBox chckbxHot = new JCheckBox("Hot");
		chckbxHot.setBounds(82, 64, 38, 23);
		addDrinkPane.add(chckbxHot);
		chckbxHot.setActionCommand("H");
		
		JCheckBox chckbxCold = new JCheckBox("Cold");
		chckbxCold.setBounds(82, 89, 38, 23);
		addDrinkPane.add(chckbxCold);

		JButton btnSaveDrink = new JButton("Save Drink");
		btnSaveDrink.setBounds(44, 222, 118, 28);
		addDrinkPane.add(btnSaveDrink);
		btnSaveDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isAllInt(txtPrice.getText()) &&
						(chckbxHot.isSelected() || chckbxCold.isSelected()) ) {
					String name=txtDrinkName.getText();
					int price = Integer.parseInt(txtPrice.getText());
					String horc="";
					if (chckbxHot.isSelected()) {
						horc+="H"+" ";
					}if (chckbxCold.isSelected()) {
						horc+="C";
					}
					
					drinks.add(new Drink(name,price,horc));
					POS.ad(name);
					JOptionPane.showMessageDialog(contentPane, drinks.getLast().name+" Saved with price "
							+drinks.getLast().price+" with available sizes "+ horc);
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