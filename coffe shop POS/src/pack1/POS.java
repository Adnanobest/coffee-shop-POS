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

public class POS extends JFrame {

	private void refresh() {
		int pos=spc;
		for (int i=0;i<menu.size();i++) {
			JButton y = new JButton(menu.get(i).name);
			y.setBounds(pos, 20, btnWidth, 50);
			contentPane.add(y);
			pos+=btnWidth+spc;
			buttons.add(y);
		}
	}
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ArrayList<Menu> menu = new ArrayList<Menu>();
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	int btnWidth=125, spc=35;
	//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

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
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension dim = contentPane.getSize();
				btnWidth= (int) (dim.getWidth()/8);
				spc = (int) (dim.getWidth()/28.5);
				int pos=spc;
				for (JButton x : buttons) {
					x.setBounds(pos, 20, btnWidth, 50);
					pos+=btnWidth+spc;
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);

		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Admin");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame x = new LoginFrame(menu);
				x.setVisible(true);
			}
		});
		btnNewButton.setBounds(895, 427, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("refresh");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		btnNewButton_1.setBounds(895, 400, 89, 23);
		contentPane.add(btnNewButton_1);

		int pos=spc;
		for (int i=0;i<menu.size();i++) {
			JButton y = new JButton(menu.get(i).name);
			y.setBounds(pos, 20, btnWidth, 50);
			contentPane.add(y);
			pos+=btnWidth+spc;
			buttons.add(y);
		}
		
		/*
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(pos, 20, btnWidth, 50);
		contentPane.add(btnNewButton);
		*/
	}
}
