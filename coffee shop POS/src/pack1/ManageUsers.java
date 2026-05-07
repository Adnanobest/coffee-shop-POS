package pack1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import javax.swing.JTextField;

public class ManageUsers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<String> list;
	private ListModel<String> listModel;
	private Button btnAdd;
	private Button btnDelete;
	private JTextField textField;
	private Font font = new Font("Tahoma", Font.PLAIN, 15);


	public ManageUsers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList<>(listModel);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 0, 211, 261);
		contentPane.add(scrollPane);
				
		btnAdd = new Button("Add");
		btnAdd.setFont(font);
		btnAdd.setBounds(267, 207, 85, 21);
		contentPane.add(btnAdd);

		btnDelete = new Button("Delete");
		btnDelete.setFont(font);
		btnDelete.setBounds(267, 234, 85, 21);
		contentPane.add(btnDelete);
		
		Label lblUsername = new Label("Username:");
		lblUsername.setFont(font);
		lblUsername.setBounds(246, -1, 91, 21);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(221, 26, 131, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
	}
}
