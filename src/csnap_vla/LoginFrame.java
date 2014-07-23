package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPasswordField;
import javax.swing.BoxLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private String usernameString;
	private String passwordString;
	private String dbPath = Global_Path_Var.db_path;
	private JPasswordField passwordField;
	
	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 550);
		this.setTitle("LoginFrame");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(24, 169, 71, 14);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(105, 166, 112, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(24, 194, 71, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Opens the createNewUserFrame when clicked
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ForgotPasswordFrame passwordFrame = new ForgotPasswordFrame();
							passwordFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnForgotPassword.setBounds(69, 251, 148, 23);
		contentPane.add(btnForgotPassword);
		
		//VLA icon placement
		JLabel lblNewLabel_1 = new JLabel("TVLA");
		//System.out.println("dir: " + Global_Path_Var.dir);
		System.out.println("dir: " + Global_Path_Var.project_dir);
		lblNewLabel_1.setIcon(new ImageIcon(Global_Path_Var.img_path));
		lblNewLabel_1.setBounds(10, 11, 274, 115);
		contentPane.add(lblNewLabel_1);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//Opens the createNewUserFrame when clicked
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							CreateNewUserFrame userFrame = new CreateNewUserFrame();
							userFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}); 
			}
		});
		btnCreateAccount.setBounds(69, 274, 148, 23);
		contentPane.add(btnCreateAccount);
		
		JButton btnSubmit = new JButton("Submit");
		getRootPane().setDefaultButton(btnSubmit);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				//Checks login, and continues to new screen if passed. Otherwise, does nothing
				proceed();						
			}
		});
		btnSubmit.setBounds(69, 228, 148, 23);
		contentPane.add(btnSubmit);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					proceed(); //Enter information, same as above, but if enter is pressed
				}
			}
		});
		passwordField.setBounds(105, 191, 112, 20);
		contentPane.add(passwordField);
		
		
	}
	
	public void proceed(){
		usernameString = usernameField.getText();
		passwordString = passwordField.getText();
		
		//check whether login information matches what's on file
		LoginHandler lh = new LoginHandler();		
		lh.loginCredentials(usernameString, passwordString);
		
		boolean passed;
		try {
			passed = lh.loginPassed();
		} catch (IOException e1) {
			passed = false;
		}				
		
		//if login was successful, go to appropriate user screen
		if(passed){
			//get user information
			UserInfo ui = new UserInfo(usernameString);

			if(ui.getType().equals("s")){
				//go to student UI
				Student_mainFrame SMF = new Student_mainFrame(ui);
				SMF.setVisible(true);
				dispose();
				
			} else if(ui.getType().equals("a")){
				//go to admin UI
				Instructor_mainFrame IMF = new Instructor_mainFrame();
				IMF.setVisible(true);
				dispose();
			}
		}
	}
}