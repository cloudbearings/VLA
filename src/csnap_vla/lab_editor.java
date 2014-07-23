package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import java.util.*;

public class lab_editor extends JFrame {

	private JPanel contentPane;
	private String theory_path;
	private String pretest_path;
	private String prelab_path;
	private String prelab_questions_path;
	private String pretest_answers_path;
	private String prelab_answers_path;
	private ArrayList lab_parts = new ArrayList();
	private int lab_parts_original_size;
	private JTextField theoryTextField;
	private JTextField pretestTextField;
	private JTextField prelabTextField;
	private JTextField prelabQnATextField;
	private JFileChooser fc;
	private String className;
	private String labName;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  
	private String directoryPath = Global_Path_Var.project_dir;
	private JTextField pretestAnswersField;
	private JTextField prelabAnswersField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lab_editor frame = new lab_editor(null,null);
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
	public lab_editor(final String className, final String labName) {
		setTitle(labName);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 790, 506);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("lab_editor");
		
		this.className = className;
		this.labName = labName;		
		
		JLabel lblNewLabel = new JLabel("Edit Theory... (Upload a .pdf file)");
		lblNewLabel.setBounds(10, 11, 388, 14);
		contentPane.add(lblNewLabel);
		
		theoryTextField = new JTextField();
		theoryTextField.setEditable(false);
		theoryTextField.setBounds(10, 36, 565, 20);
		contentPane.add(theoryTextField);
		theoryTextField.setColumns(10);
		
		//Opens up a file viewer and checks that it is a PDF file. If it is, it will add the selected file's
		//path to the specified field
		JButton btnBrowseTheory = new JButton("Browse");
		btnBrowseTheory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(lab_editor.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                if(file.getName().contains(".pdf")) {
	                	theoryTextField.setText(file.getAbsolutePath());
	                	theory_path = file.getAbsolutePath();
	                }
	                else theoryTextField.setText("File is not a PDF");
	            } else {
	                theoryTextField.setText(theoryTextField.getText());
	            }
			}
		});
		btnBrowseTheory.setBounds(585, 35, 89, 23);
		contentPane.add(btnBrowseTheory);
		
		JLabel lblNewLabel_1 = new JLabel("Edit Pretest... (Upload a .pdf file)");
		lblNewLabel_1.setBounds(10, 67, 388, 14);
		contentPane.add(lblNewLabel_1);
		
		pretestTextField = new JTextField();
		pretestTextField.setEditable(false);
		pretestTextField.setBounds(10, 92, 565, 20);
		contentPane.add(pretestTextField);
		pretestTextField.setColumns(10);
		
		JButton btnBrowsePretest = new JButton("Browse");
		btnBrowsePretest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(lab_editor.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                if(file.getName().contains(".pdf")){
	                	pretestTextField.setText(file.getAbsolutePath());
	                	pretest_path = file.getAbsolutePath();
	                }
	                else pretestTextField.setText("File is not a PDF");
	            } else {
	                pretestTextField.setText(theoryTextField.getText());
	            }
			}
		});
		btnBrowsePretest.setBounds(585, 91, 89, 23);
		contentPane.add(btnBrowsePretest);
		
		JLabel lblNewLabel_2 = new JLabel("Edit Prelab... (Upload a .pdf file)");
		lblNewLabel_2.setBounds(10, 180, 388, 14);
		contentPane.add(lblNewLabel_2);
		
		prelabTextField = new JTextField();
		prelabTextField.setEditable(false);
		prelabTextField.setBounds(10, 205, 563, 20);
		contentPane.add(prelabTextField);
		prelabTextField.setColumns(10);
		
		JButton btnBrowsePrelab = new JButton("Browse");
		btnBrowsePrelab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBrowsePrelab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(lab_editor.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                if(file.getName().contains(".pdf")){
	                	prelabTextField.setText(file.getAbsolutePath());
	                	prelab_path = file.getAbsolutePath();
	                }
	                else prelabTextField.setText("File is not a PDF");
	            } else {
	                prelabTextField.setText(theoryTextField.getText());
	            }
			}
		});
		btnBrowsePrelab.setBounds(585, 204, 89, 23);
		contentPane.add(btnBrowsePrelab);
		
		JLabel lblNewLabel_3 = new JLabel("Edit Prelab Questions... (Upload a .pdf file)");
		lblNewLabel_3.setBounds(10, 236, 388, 14);
		contentPane.add(lblNewLabel_3);
		
		prelabQnATextField = new JTextField();
		prelabQnATextField.setEditable(false);
		prelabQnATextField.setBounds(10, 261, 565, 20);
		contentPane.add(prelabQnATextField);
		prelabQnATextField.setColumns(10);
		
		JButton btnPrelabQnA = new JButton("Browse");
		btnPrelabQnA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(lab_editor.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                if(file.getName().contains(".pdf")){
	                	prelabQnATextField.setText(file.getAbsolutePath());
	                	prelab_questions_path = file.getAbsolutePath();
	                }
	                else prelabQnATextField.setText("File is not a PDF");
	            } else {
	                prelabQnATextField.setText(theoryTextField.getText());
	            }
			}
		});
		btnPrelabQnA.setBounds(585, 260, 89, 23);
		contentPane.add(btnPrelabQnA);
		
		JButton btnDone = new JButton("Done");
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//save PDF locations to lab file if file paths have ".pdf" in name
				if(theoryTextField.getText().contains(".pdf") && pretestTextField.getText().contains(".pdf") 
						&& prelabTextField.getText().contains(".pdf") && prelabQnATextField.getText().contains(".pdf")){
					
					Path path = Paths.get(directoryPath + className + ".txt");
					
					//create a temp file to add paths to class file, then rename temp to the class name
					String temp;
					try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(directoryPath + "temp.txt", true)))) {
						try (Scanner scanner =  new Scanner(path, ENCODING.name())){
						     while (scanner.hasNextLine()){
						       temp = scanner.nextLine();						       
						       if (temp.contains(labName)){
						    	   out.println(temp); //print lab name
						    	   out.println("Theory Path: " + theory_path);
						    	   out.println("Pretest Path: " + pretest_path);
						    	   out.println("Pretest Answers Path: " + pretest_answers_path);
								   out.println("Prelab Path: " + prelab_path);
								   out.println("Prelab Questions Path: " + prelab_questions_path);
								   out.println("Prelab Answers Path: " + prelab_answers_path); 
								   for(int i = 0; i < lab_parts.size(); i++){
									   out.println("Lab Part: " + lab_parts.get(i));
								   }
								   //jump scanner ahead for the printed lines
								   temp = scanner.nextLine();
								   temp = scanner.nextLine();
								   temp = scanner.nextLine();
								   temp = scanner.nextLine();
								   temp = scanner.nextLine();
								   temp = scanner.nextLine();
								   for(int i = 0; i < lab_parts_original_size; i++){
									   temp = scanner.nextLine();
								   }
						       } else out.println(temp);
						     }     
						     scanner.close();
						   } catch (IOException e) {
							e.printStackTrace();
							System.out.println("ERROR");
						   }
						out.close();
					}catch (IOException er) {
					    //exception handling 
						System.out.println("ERROR");
					}
					
					
					//delete old class file, replace it with temp file.
					File tempFile = new File(directoryPath + "temp.txt");
					File newClassFile = new File(directoryPath + className + ".txt");
					newClassFile.delete();
					tempFile.renameTo(newClassFile);
					tempFile.delete();					   
				}
			}
		});
		btnDone.setBounds(10, 434, 89, 23);
		contentPane.add(btnDone);
		
		JButton btnGoBack = new JButton("Add/Edit Labs");
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				add_edit_lab labFrame = new add_edit_lab(className);
				labFrame.setVisible(true);	
				
				setVisible(false);
				dispose();		
			}
		});
		btnGoBack.setBounds(110, 434, 135, 23);
		contentPane.add(btnGoBack);
		
		JButton btnInstructorPanel = new JButton("Instructor Panel");
		btnInstructorPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Instructor_mainFrame IMF = new Instructor_mainFrame();
				IMF.setVisible(true);	
				
				setVisible(false);
				dispose();		
			}
		});
		btnInstructorPanel.setBounds(255, 434, 163, 23);
		contentPane.add(btnInstructorPanel);
		
		JButton btnNewButton_7 = new JButton("Preview");
		btnNewButton_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!theoryTextField.getText().isEmpty()){
					PreviewPDF preview1 = new PreviewPDF(theoryTextField.getText());
					preview1.setVisible(true);
				} else { 
					theoryTextField.setText("Please choose a PDF file to preview");
				}
			}
		});
		btnNewButton_7.setBounds(684, 35, 89, 23);
		contentPane.add(btnNewButton_7);
		
		JButton button = new JButton("Preview");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!prelabTextField.getText().isEmpty()){
					PreviewPDF preview3 = new PreviewPDF(prelabTextField.getText());
					preview3.setVisible(true);
				} else { 
					prelabTextField.setText("Please choose a PDF file to preview");
				}
			}
		});
		button.setBounds(684, 204, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Preview");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!pretestTextField.getText().isEmpty()){
					PreviewPDF preview2 = new PreviewPDF(pretestTextField.getText());
					preview2.setVisible(true);
				} else { 
					pretestTextField.setText("Please choose a PDF file to preview");
				}
			}
		});
		button_1.setBounds(684, 91, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Preview");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!prelabQnATextField.getText().isEmpty()){
					PreviewPDF preview4 = new PreviewPDF(prelabQnATextField.getText());
					preview4.setVisible(true);
				} else { 
					prelabQnATextField.setText("Please choose a PDF file to preview");
				}
			}
		});
		button_2.setBounds(684, 260, 89, 23);
		contentPane.add(button_2);
		
		JLabel lblEditPretestAnswers = new JLabel("Edit Pretest Answers... (Upload a .pdf file)");
		lblEditPretestAnswers.setBounds(10, 127, 388, 14);
		contentPane.add(lblEditPretestAnswers);
		
		pretestAnswersField = new JTextField();
		pretestAnswersField.setEditable(false);
		pretestAnswersField.setBounds(13, 152, 562, 20);
		contentPane.add(pretestAnswersField);
		pretestAnswersField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int returnVal = fc.showOpenDialog(lab_editor.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                if(file.getName().contains(".pdf")){
	                	pretestAnswersField.setText(file.getAbsolutePath());
	                	pretest_answers_path = file.getAbsolutePath();
	                }
	                else pretestAnswersField.setText("File is not a PDF");
	            } else {
	                pretestAnswersField.setText(theoryTextField.getText());
	            }				
			}
		});
		btnNewButton.setBounds(585, 151, 89, 23);
		contentPane.add(btnNewButton);
		
		//opens up the target PDF in a viewer window for preview
		JButton btnNewButton_1 = new JButton("Preview");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!pretestAnswersField.getText().isEmpty()){
					PreviewPDF preview5 = new PreviewPDF(pretestAnswersField.getText());
					preview5.setVisible(true);
				} else { 
					pretestAnswersField.setText("Please choose a PDF file to preview");
				}
				
			}
		});
		btnNewButton_1.setBounds(684, 151, 89, 23);
		contentPane.add(btnNewButton_1);
		
		prelabAnswersField = new JTextField();
		prelabAnswersField.setEditable(false);
		prelabAnswersField.setBounds(10, 317, 565, 20);
		contentPane.add(prelabAnswersField);
		prelabAnswersField.setColumns(10);
		
		JLabel lblEditPrelabAnswers = new JLabel("Edit Prelab Answers... (Upload a .pdf file)");
		lblEditPrelabAnswers.setBounds(10, 292, 388, 14);
		contentPane.add(lblEditPrelabAnswers);
		
		JButton btnNewButton_2 = new JButton("Browse");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int returnVal = fc.showOpenDialog(lab_editor.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                if(file.getName().contains(".pdf")){
	                	prelabAnswersField.setText(file.getAbsolutePath());
	                	prelab_answers_path = file.getAbsolutePath();
	                }
	                else prelabAnswersField.setText("File is not a PDF");
	            } else {
	                prelabAnswersField.setText(theoryTextField.getText());
	            }	
				
			}
		});
		btnNewButton_2.setBounds(585, 316, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Preview");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(!prelabAnswersField.getText().isEmpty()){
					PreviewPDF preview6 = new PreviewPDF(prelabAnswersField.getText());
					preview6.setVisible(true);
				} else { 
					prelabAnswersField.setText("Please choose a PDF file to preview");
				}
				
			}
		});
		btnNewButton_3.setBounds(684, 316, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Formatting Help");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			
			//opens a window to show how the PDF files should be formatted
			public void mouseClicked(MouseEvent arg0) {
				UploadHelp UH = new UploadHelp();
				UH.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(428, 434, 180, 23);
		contentPane.add(btnNewButton_4);
		
		final JLabel lblNewLabel_5 = new JLabel("File is not a PDF!");
		lblNewLabel_5.setBounds(529, 409, 145, 14);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setVisible(false);
		
		JLabel lblNewLabel_4 = new JLabel("Add Lab Parts... (Upload a .pdf file)");
		lblNewLabel_4.setBounds(10, 348, 300, 14);
		contentPane.add(lblNewLabel_4);
		
		final JComboBox LabComboBox = new JComboBox();
		LabComboBox.setBounds(10, 373, 565, 20);
		contentPane.add(LabComboBox);
		
		//adds lab parts to a combobox so that multiple can be entered
		JButton btnNewButton_5 = new JButton("Add");
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int returnVal = fc.showOpenDialog(lab_editor.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                if(file.getName().contains(".pdf")){
	                	LabComboBox.addItem(file.getAbsolutePath());
	                	lab_parts.add(file.getAbsolutePath());
		        		lblNewLabel_5.setVisible(false);
	                }
	                else lblNewLabel_5.setVisible(true);

	            } else {
	                //do nothing
	            }	
				
			}
		});
		btnNewButton_5.setBounds(585, 372, 89, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Remove");
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				lab_parts.remove(LabComboBox.getSelectedItem()); //remove from array list
				LabComboBox.removeItem(LabComboBox.getSelectedItem()); //remove from combo box
				
				
			}
		});
		btnNewButton_6.setBounds(684, 372, 89, 23);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_8 = new JButton("Preview");
		btnNewButton_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(!((String)LabComboBox.getSelectedItem()).isEmpty()){
					PreviewPDF preview6 = new PreviewPDF((String)LabComboBox.getSelectedItem());
					preview6.setVisible(true);
				} else { 
					prelabAnswersField.setText("Please choose a PDF file to preview");
				}
				
			}
		});
		btnNewButton_8.setBounds(684, 406, 89, 23);
		contentPane.add(btnNewButton_8);
		
		
		
		fc = new JFileChooser();
		
		//initialize text boxes with current PDF locations when window is first opened
		String temp;
		Scanner scanner;
		try {
			scanner = new Scanner(new File(directoryPath + className + ".txt"));
			while (scanner.hasNextLine()){
				temp = scanner.nextLine();
				if(temp.contains(labName)){
					temp = scanner.nextLine();
					if(temp.contains(".pdf")) {
						theoryTextField.setText(temp.split("Theory Path: ")[1]);
						theory_path = temp.split("Theory Path: ")[1];
					}
					temp = scanner.nextLine();
					if(temp.contains(".pdf")) {
						pretestTextField.setText(temp.split("Pretest Path: ")[1]);
						pretest_path = temp.split("Pretest Path: ")[1];
					}
					temp = scanner.nextLine();
					if(temp.contains(".pdf")) {
						pretestAnswersField.setText(temp.split("Pretest Answers Path: ")[1]);
						pretest_answers_path = temp.split("Pretest Answers Path: ")[1];
					}
					temp = scanner.nextLine();
					if(temp.contains(".pdf")) {
						prelabTextField.setText(temp.split("Prelab Path: ")[1]);
						prelab_path = temp.split("Prelab Path: ")[1];
					}
					temp = scanner.nextLine();
					if(temp.contains(".pdf")) {
						prelabQnATextField.setText(temp.split("Prelab Questions Path: ")[1]);
						prelab_questions_path = temp.split("Prelab Questions Path: ")[1];
					}
					temp = scanner.nextLine();
					if(temp.contains(".pdf")) {
						prelabAnswersField.setText(temp.split("Prelab Answers Path: ")[1]);
						prelab_answers_path = temp.split("Prelab Answers Path: ")[1];
					}
					temp = scanner.nextLine();
					while(temp.contains(".pdf")) {
						LabComboBox.addItem(temp.split("Lab Part: ")[1]);	
						lab_parts.add(temp.split("Lab Part: ")[1]);
						if(scanner.hasNextLine()) temp = scanner.nextLine();
						else break;
					}
					lab_parts_original_size = lab_parts.size();
				}
			 }
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
	}
}


