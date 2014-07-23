package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JTextField;

public class Instructor_mainFrame extends JFrame {
	
	private JFileChooser fc;
	private String directoryPath = Global_Path_Var.project_dir;
	private String classInfoPath = Global_Path_Var.class_info_path;
	private String helpTextFile;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  
	private JPanel contentPane;
	private JTextField classNumberField;
	private JTextField classTitleField;
	private JTextField questionField;
	private JTextField textFileField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instructor_mainFrame frame = new Instructor_mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Instructor_mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Instructor_mainFrame");

		String selectedClass;
		
		JButton btnAdminPanel = new JButton("Admin Panel\r\n");
		btnAdminPanel.setBounds(10, 66, 138, 23);
		contentPane.add(btnAdminPanel);
		
		JLabel lblOr = new JLabel("- OR -");
		lblOr.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOr.setBounds(56, 100, 66, 44);
		contentPane.add(lblOr);
		
		final JComboBox comboBoxSelectCourse = new JComboBox();
		//get classes from class_info
		Path path = Paths.get(classInfoPath);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        comboBoxSelectCourse.addItem(scanner.nextLine());; //add class in text file to comboBox
	      }      
	    } catch (IOException e) {
			e.printStackTrace();
		}
		
		comboBoxSelectCourse.setBounds(10, 180, 328, 20);
		contentPane.add(comboBoxSelectCourse);
		
		JLabel lblNewLabel = new JLabel("Select a Course");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 155, 182, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblW = new JLabel("Welcome Admin!");
		lblW.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblW.setBounds(10, 11, 249, 44);
		contentPane.add(lblW);
		
		JButton btnSelectCourse = new JButton("OK");
		btnSelectCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//opens up the frame to add or edit a lab when pressed
				add_edit_lab labFrame = new add_edit_lab((String)comboBoxSelectCourse.getSelectedItem());
				labFrame.setVisible(true);
				
				dispose();
			}
		});
		btnSelectCourse.setBounds(348, 179, 64, 23);
		contentPane.add(btnSelectCourse);
		
		JLabel label = new JLabel("- OR -");
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label.setBounds(56, 211, 66, 44);
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("Add a new course");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 266, 138, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Class Number (e.g. ECE1234)");
		lblNewLabel_2.setBounds(10, 291, 166, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Class Title");
		lblNewLabel_3.setBounds(10, 316, 138, 14);
		contentPane.add(lblNewLabel_3);
		
		classNumberField = new JTextField();
		classNumberField.setBounds(186, 288, 152, 20);
		contentPane.add(classNumberField);
		classNumberField.setColumns(10);
		
		classTitleField = new JTextField();
		classTitleField.setBounds(186, 313, 152, 20);
		contentPane.add(classTitleField);
		classTitleField.setColumns(10);
		
		JLabel thirdOr = new JLabel("- OR -");
		thirdOr.setFont(new Font("Tahoma", Font.PLAIN, 24));
		thirdOr.setBounds(56, 350, 66, 44);
		contentPane.add(thirdOr);
		
		JLabel helpModLabel = new JLabel("Edit the Help Module (upload *.txt file or enter a question)");
		helpModLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		helpModLabel.setBounds(10, 405, 350, 14);
		contentPane.add(helpModLabel);
		
		JLabel helpModTextFile = new JLabel("Upload a *.txt file:");
		helpModTextFile.setBounds(10, 430, 166, 14);
		contentPane.add(helpModTextFile);
		
		JLabel helpModAsk = new JLabel("Enter a question:");
		helpModAsk.setBounds(10, 480, 166, 14);
		contentPane.add(helpModAsk);
		
		textFileField = new JTextField();
		textFileField.setEditable(false);
		textFileField.setBounds(186, 430, 152, 20);
		contentPane.add(textFileField);
		textFileField.setColumns(10);
		
		JButton btnAddHelpChoose = new JButton("Choose");
		btnAddHelpChoose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(Instructor_mainFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                helpTextFile = file.getAbsolutePath();
	                if(file.getName().contains(".txt")){
	                	textFileField.setText(file.getAbsolutePath());
	                }
	                else textFileField.setText("File is not a TXT");
	            } else {
	            	textFileField.setText(textFileField.getText());
	            }
			}
		});
		btnAddHelpChoose.setBounds(348, 430, 84, 23);
		contentPane.add(btnAddHelpChoose);
		
		JButton btnAddHelpText = new JButton("Upload");
		btnAddHelpText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					AddHelpQuestions.write(helpTextFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAddHelpText.setBounds(348, 455, 84, 23);
		contentPane.add(btnAddHelpText);
		
		questionField = new JTextField();
		questionField.setBounds(186, 480, 152, 20);
		contentPane.add(questionField);
		questionField.setColumns(10);
		
		JButton btnAddHelpAsk = new JButton("Ask");
		btnAddHelpAsk.setBounds(348, 480, 84, 23);
		btnAddHelpAsk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//opens up the frame to add or edit a lab when pressed
				if(questionField.getText().equals("Ask a question.")){
					questionField.setText("Ask a question.");
                }
                else if(!questionField.getText().equals("")) {
					AddSingleQuestion labFrame = new AddSingleQuestion(questionField.getText());
					labFrame.setVisible(true);
					
					dispose();
                }
                else questionField.setText("Ask a question.");
				
			}
		});
		contentPane.add(btnAddHelpAsk);
		
		JButton btnAddCourse = new JButton("Add");
		btnAddCourse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//if course number contains a space, do not proceed
				if(classNumberField.getText().contains(" ")){
					classNumberField.setText("No Spaces Here!");
				}
				//if neither fields are empty, add class to text file
				else if(!classNumberField.getText().isEmpty() || !classTitleField.getText().isEmpty()){
					try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(classInfoPath, true)))) {
					    out.println(classNumberField.getText() + " " + classTitleField.getText()); //adds new class to class list	
					    out.close();
					}catch (IOException er) {
					    //exception handling 
					}
					// add it to the comboBox
					comboBoxSelectCourse.addItem(classNumberField.getText() + " " + classTitleField.getText());
					
					//create class file to add labs under
					PrintWriter writer;
					try {
						writer = new PrintWriter(directoryPath + classNumberField.getText() + ".txt", "UTF-8");
						writer.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnAddCourse.setBounds(348, 307, 64, 23);
		contentPane.add(btnAddCourse);
		fc = new JFileChooser();
	}
}
