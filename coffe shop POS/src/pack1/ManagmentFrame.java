package pack1;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagmentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDrinkName;
	private JTextField txtPrice;

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
		
		JLabel lblDrinkName = new JLabel("drink name");
		lblDrinkName.setBounds(10, 11, 62, 14);
		contentPane.add(lblDrinkName);
		
		txtDrinkName = new JTextField();
		txtDrinkName.setBounds(82, 8, 105, 20);
		contentPane.add(txtDrinkName);
		txtDrinkName.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(10, 40, 46, 14);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(82, 37, 105, 20);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnNewButton = new JButton("Save Drink");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=txtDrinkName.getText();
				int price = Integer.parseInt(txtPrice.getText());
				menu.add(new Menu(name,price));
				POS.add(name);
				txtDrinkName.setText("");
				txtPrice.setText("");
			}
		});
		btnNewButton.setBounds(178, 91, 118, 28);
		contentPane.add(btnNewButton);
	}
}
