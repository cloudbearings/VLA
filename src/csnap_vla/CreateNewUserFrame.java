package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateNewUserFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewUserFrame frame = new CreateNewUserFrame();
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
	public CreateNewUserFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("CreateNewUserFrame");

		
		JTextPane txtpnWelcomeToTemples = new JTextPane();
		txtpnWelcomeToTemples.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnWelcomeToTemples.setBackground(UIManager.getColor("EditorPane.disabledBackground"));
		txtpnWelcomeToTemples.setEditable(false);
		txtpnWelcomeToTemples.setText("Welcome to Temple's Virtual Lab Assistant!");
		txtpnWelcomeToTemples.setBounds(10, 24, 274, 21);
		contentPane.add(txtpnWelcomeToTemples);
		
		JTextPane txtpnToCreateA = new JTextPane();
		txtpnToCreateA.setText("To create a new user, please provide us with a TUID, email, and a password.\r\n\r\nPassword Tips:\r\n    -Must include at least 6 characters\r\n    -May include any combination of\r\n     letters/numbers");
		txtpnToCreateA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnToCreateA.setEditable(false);
		txtpnToCreateA.setBackground(SystemColor.menu);
		txtpnToCreateA.setBounds(10, 56, 264, 121);
		contentPane.add(txtpnToCreateA);
		
		JLabel lblNewLabel = new JLabel("TUID:");
		lblNewLabel.setBounds(20, 188, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("e-mail:");
		lblNewLabel_1.setBounds(20, 213, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(20, 238, 78, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(100, 188, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 213, 125, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(100, 238, 125, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		//This adds a new user entry to the user-database text file. This must be updated for the server
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				LoginHandler lh = new LoginHandler();		
				lh.addEntry(textField_1.getText(), textField_2.getText());
				dispose();
			}
		});
		btnSubmit.setBounds(89, 269, 89, 23);
		contentPane.add(btnSubmit);
	}
}
