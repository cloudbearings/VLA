package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class add_edit_lab extends JFrame {

	private JPanel contentPane;
	private JTextField newLabField;
	private String courseNumber;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  
	private String directoryPath = Global_Path_Var.project_dir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_edit_lab frame = new add_edit_lab(null);
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
	public add_edit_lab(String class_name) {
		//Get course number from class_name
		courseNumber = class_name.split(" ")[0];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("add_edit_lab");
		
		JLabel lblNewLabel = new JLabel(class_name);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 0, 247, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewLab = new JButton("New Lab");
		btnNewLab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//add lab name to class file
				if(!newLabField.getText().isEmpty()){
					try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(directoryPath + courseNumber + ".txt", true)))) {
					    out.println("Lab Name: " + newLabField.getText()); //adds new class to class list	
					    out.println("Theory Path: ");
					    out.println("Pretest Path: ");
					    out.println("Pretest Answers Path: ");
					    out.println("Prelab Path: ");
					    out.println("Prelab Questions Path: ");
					    out.println("Prelab Answers Path: ");
					    out.println("   ");
					    out.close();
					}catch (IOException er) {
					    //exception handling 
					}
					System.out.println(courseNumber + newLabField.getText());
					lab_editor labEdit = new lab_editor(courseNumber, newLabField.getText());
					labEdit.setVisible(true);
					
					dispose();
				}
				
			}
		});
		btnNewLab.setBounds(285, 70, 89, 23);
		contentPane.add(btnNewLab);
		
		JLabel lblNewLabel_1 = new JLabel("-OR-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setBounds(188, 113, 55, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblEditExistingLab = new JLabel("Edit Existing Lab...");
		lblEditExistingLab.setBounds(165, 155, 103, 14);
		contentPane.add(lblEditExistingLab);
		
		final JComboBox comboBoxLabSelect = new JComboBox();
		//get labs from class_info and add them to the combobox
		Path path = Paths.get(directoryPath + courseNumber + ".txt");
		String temp;
		   try (Scanner scanner =  new Scanner(path, ENCODING.name())){
		     while (scanner.hasNextLine()){
		       temp = scanner.nextLine();
		       if(temp.contains("Lab Name: ")) comboBoxLabSelect.addItem(temp.split("Lab Name: ")[1]); //add lab in text file to comboBox
		     }     
		     scanner.close();
		   } catch (IOException e) {
			e.printStackTrace();
		}
		comboBoxLabSelect.setBounds(67, 180, 190, 20);
		contentPane.add(comboBoxLabSelect);
		
		newLabField = new JTextField();
		newLabField.setBounds(100, 71, 157, 20);
		contentPane.add(newLabField);
		newLabField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New Lab Name...");
		lblNewLabel_2.setBounds(100, 46, 117, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Go to the edit lab page for the selected lab
				System.out.println(courseNumber + (String)comboBoxLabSelect.getSelectedItem());
				lab_editor labEdit = new lab_editor(courseNumber, (String)comboBoxLabSelect.getSelectedItem());
				labEdit.setVisible(true);
				
				setVisible(false); //hide window so that we can go back to it
			}
		});
		btnEdit.setBounds(285, 179, 89, 23);
		contentPane.add(btnEdit);
	}

}
