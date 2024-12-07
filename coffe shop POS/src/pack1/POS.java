package pack1;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class POS extends JFrame {
	
	static void removeButton(String x) {
		for (JButton y : buttons) {
			if (y.getText().equals(x)) {
				buttons.remove(y);
				reset();
				break;
			}
		}
	}
	
	static void ad(String name) {
		buttons.add(new JButton(name));
		reset();
	}
	
	static void reset() {
		menuPane.removeAll();
		menuPane.repaint();
		int pos=spc;
		int i=0;
		height=20;
		if (buttons.isEmpty()) {
			return;
		}
		for (JButton x : buttons) {
			if(i!=0 && i%6 == 0) {
				pos=spc;
				height+=70;
			} i++;
			menuPane.add(x);
			x.setBounds(pos, height, btnWidth, 50);
			pos+=btnWidth+spc;
		}
		
	}
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JScrollPane menuPane;
	private static JPanel totalPanel;
	
	static ArrayList<Drink> menuDrink = new ArrayList<Drink>();
	static ArrayList<Dessert> menuDessert = new ArrayList<Dessert>();
	static ArrayList<JButton> buttons = new ArrayList<JButton>();
	static int btnWidth=85;
	JButton btnRefresh;
	static int spc=24;
	static int height= 5;
	private JButton btnClear;

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

	/**
	 * Create the frame.
	 */
	public POS() {
					/*
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension dim = contentPane.getSize();
				btnWidth= (int) (dim.getWidth()/8);
				spc = (int) (dim.getWidth()/28.5);
				int pos=spc;
				btnAdmin.setBounds(dim.width-btnWidth-10, dim.height-35, btnWidth, 25);
				int i=0;
				height=20;
				for (JButton x : buttons) {
					if(i!=0 && i%6 == 0) {
						pos=spc;
						height+=70;
					} i++;
					contentPane.add(x);
					x.setBounds(pos, height, btnWidth, 50);
					pos+=btnWidth+spc;
				}
				
			}
		}); 
		*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);

		setBounds(100, 100, 1015, 500);
		setMinimumSize(new Dimension(999, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuPane = new JScrollPane();
		menuPane.setBounds(0, 0, 666, 461);
		contentPane.add(menuPane);
		
		totalPanel = new JPanel();
		totalPanel.setBackground(new Color(255, 250, 205));
		totalPanel.setBounds(656, 0, 333, 461);
		contentPane.add(totalPanel);
		totalPanel.setLayout(null);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setBounds(122, 438, 61, 23);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame x = new LoginFrame(menuDrink, menuDessert);
				x.setVisible(true);
			}
		});
		totalPanel.add(btnAdmin);
		
		btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAll();
			}
		});
		btnClear.setBounds(71, 182, 89, 23);
		totalPanel.add(btnClear);

		

	}

	private void clearAll() {
		for (int i=0;i<buttons.size();i++) {
			menuPane.remove(i);

		}
	}
}
