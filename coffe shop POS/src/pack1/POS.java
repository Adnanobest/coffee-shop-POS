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
	
	static void ad(String name) {
		buttons.add(new JButton(name));
		reset();
	}
	
	static void reset() {
		int pos=spc;
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
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	static ArrayList<Menu> menu = new ArrayList<Menu>();
	static ArrayList<JButton> buttons = new ArrayList<JButton>();
	static int btnWidth=125;
	JButton btnAdmin;
	JButton btnRefresh;
	static int spc=35;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setBounds(100, 100, 1000, 500);
		setMinimumSize(new Dimension(1000, 500));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame x = new LoginFrame(menu);
				x.setVisible(true);
			}
		});
		btnAdmin.setBounds(895, 427, btnWidth, 25);
		contentPane.add(btnAdmin);
		

	}
}
