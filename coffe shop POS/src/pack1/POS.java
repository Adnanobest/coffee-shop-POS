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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class POS extends JFrame {
	

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
	
	static void adDrink(String name) {
		drinkButtons.add(new JButton(name));
		resetDrinks();
	}
	
	static void resetDrinks() {
		drinksPanel.removeAll();
		drinksPanel.repaint();
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

	static void adDesserts(String name) {
		dessertButtons.add(new JButton(name));
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
	
	static ArrayList<Drink> menuDrink = new ArrayList<Drink>();
	static ArrayList<Dessert> menuDessert = new ArrayList<Dessert>();
	static ArrayList<JButton> drinkButtons = new ArrayList<JButton>();
	static ArrayList<JButton> dessertButtons = new ArrayList<JButton>();
	static int btnWidth=85;
	JButton btnRefresh;
	static int spc=24;
	static int height= 5;

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
		panel.setLayout(null);
		
		setPanel();

		drinksPanel = new JPanel();
		drinksPanel.setBounds(111, 0, 555, 461);
		contentPane.add(drinksPanel);
		drinksPanel.setLayout(null);
		drinksPanel.setVisible(false);
		
		setDrinksPane();
		
		dessertsPanel = new JPanel();
		dessertsPanel.setBounds(111, 0, 555, 461);
		contentPane.add(dessertsPanel);
		dessertsPanel.setVisible(false);

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
				LoginFrame x = new LoginFrame(menuDrink, menuDessert);
				x.setVisible(true);
			}
		});
		totalPanel.setLayout(null);
		totalPanel.add(btnAdmin);
	}

	private void setPanel() {
		JButton btnDrinks = new JButton("Drinks");
		btnDrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dessertsPanel.setVisible(false);
				drinksPanel.setVisible(true);
			}
		});
		btnDrinks.setBounds(10, 11, 89, 23);
		panel.add(btnDrinks);
		
		JButton btnDesserts = new JButton("Desserts");
		btnDesserts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drinksPanel.setVisible(false);
				dessertsPanel.setVisible(true);
			}
		});
		btnDesserts.setBounds(10, 45, 89, 23);
		panel.add(btnDesserts);
	}

	private void setDrinksPane() {
		JLabel lblHorC = new JLabel("Hot or cold");
		lblHorC.setBounds(10,11,52,14);
		drinksPanel.add(lblHorC);
		
		ButtonGroup btngrpHorC = new ButtonGroup();
		
		JRadioButton rdbtnHot = new JRadioButton("Hot");
		rdbtnHot.setBounds(69, 7, 43, 23);
		drinksPanel.add(rdbtnHot);
		btngrpHorC.add(rdbtnHot);
		
		JRadioButton rdbtnCold = new JRadioButton("Cold");
		rdbtnCold.setBounds(114, 7, 47, 23);
		drinksPanel.add(rdbtnCold);
		btngrpHorC.add(rdbtnCold);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(10, 36, 52, 14);
		drinksPanel.add(lblSize);
		
		ButtonGroup btngrpSize = new ButtonGroup();
		
		JRadioButton rdbtnL = new JRadioButton("L");
		rdbtnL.setBounds(69, 32, 43, 23);
		drinksPanel.add(rdbtnL);
		btngrpSize.add(rdbtnL);
		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.setBounds(114, 32, 47, 23);
		drinksPanel.add(rdbtnM);
		btngrpSize.add(rdbtnM);
		
		JRadioButton rdbtnS = new JRadioButton("S");
		rdbtnS.setBounds(163, 32, 47, 23);
		drinksPanel.add(rdbtnS);
		btngrpSize.add(rdbtnS);
	}
}
