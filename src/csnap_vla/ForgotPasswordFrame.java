package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ForgotPasswordFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String emailString;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPasswordFrame frame = new ForgotPasswordFrame();
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
	public ForgotPasswordFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("ForgotPasswordFrame");

		//This frame does nothing at the moment, but will be used to reset passwords once the server is running
		
		JLabel lblNewLabel = new JLabel("Please enter your email to reset your password.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 43, 264, 20);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(95, 98, 134, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("e-mail:");
		lblNewLabel_1.setBounds(39, 101, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				emailString = textField.getText();
				dispose();
			}
		});
		btnNewButton.setBounds(95, 165, 89, 23);
		contentPane.add(btnNewButton);
	}

}
