package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;



public class Student_mainFrame extends JFrame {

	private String directoryPath = Global_Path_Var.project_dir;
	private String classInfoPath = Global_Path_Var.class_info_path;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_mainFrame frame = new Student_mainFrame(null);
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
	public Student_mainFrame(final UserInfo user_info) {
		System.out.println(Global_Path_Var.project_dir);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Student_mainFrame");

		
		JLabel lblNewLabel = new JLabel("Welcome " + user_info.getName() + "!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 414, 32);
		contentPane.add(lblNewLabel);
		
		//Opens up the help module for asking questions
		JButton btnHelp = new JButton("Help");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Help_Module help = new Help_Module();
				help.setVisible(true);
				
			}
		});
		btnHelp.setBounds(10, 197, 89, 23);
		contentPane.add(btnHelp);
		
		JLabel lblNewLabel_1 = new JLabel("-OR-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(28, 153, 71, 44);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Choose a Course/Lab");
		lblNewLabel_2.setBounds(10, 54, 201, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Course");
		lblNewLabel_3.setBounds(10, 79, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Lab");
		lblNewLabel_4.setBounds(10, 104, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		final JComboBox course_dropDown = new JComboBox();
		
		//add course names to the drop down box from the course file
		Path path = Paths.get(classInfoPath);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        course_dropDown.addItem(scanner.nextLine());
	      }      
	    } catch (IOException e) {
			e.printStackTrace();
		}
	
		course_dropDown.setBounds(66, 79, 288, 20);
		contentPane.add(course_dropDown);
		
		final JComboBox lab_dropDown = new JComboBox();
		lab_dropDown.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
				String course_number = (String)course_dropDown.getSelectedItem();

				//get the first word (just the course number)
				if(course_number.contains(" ")) course_number = course_number.substring(0, course_number.indexOf(" ")); 
					
				//populate dropdown with the courses labs
				Path path = Paths.get(directoryPath + course_number + ".txt");
				String temp;
				   try (Scanner scanner =  new Scanner(path, ENCODING.name())){
				     while (scanner.hasNextLine()){
				       temp = scanner.nextLine();
				       if(temp.contains("Lab Name: ")) lab_dropDown.addItem(temp.split("Lab Name: ")[1]); //add lab in text file to comboBox
				     }     
				     scanner.close();
				   } catch (IOException er) {
					er.printStackTrace();
				}
			}
		});
		
		lab_dropDown.setBounds(66, 101, 288, 20);
		contentPane.add(lab_dropDown);
		
		//reset the lab dropdown whenever the course dropdown is changed
		course_dropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lab_dropDown.removeAllItems();
			}
		});
		
		final JLabel lblNewLabel_5 = new JLabel("");
		
		lblNewLabel_5.setFont(UIManager.getFont("ColorChooser.font"));
		lblNewLabel_5.setBounds(213, 169, 141, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Go!");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String lab_name = (String)lab_dropDown.getSelectedItem();		
				String course_number = (String)course_dropDown.getSelectedItem();

				//get the first word (just the course number)
				if(course_number.contains(" ")) course_number = course_number.substring(0, course_number.indexOf(" ")); 
				
				//Go to theory frame if a lab is selected
				if(lab_name != null && course_number != null){
					Student_theory2 theory = new Student_theory2(user_info, course_number, lab_name);
					theory.setVisible(true);
					dispose();
				} else lblNewLabel_5.setText("Error: Select a lab");
			}
		});
		btnNewButton.setBounds(268, 132, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
