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
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class AddSingleQuestion extends JFrame {

	private JFileChooser fc;
	private JPanel contentPane;
	private JTextField newAnswerField;
	private JTextField newVideoField;
	private JTextField newPDFField;
	private JTextField newKeywordField1;
	private JTextField newKeywordField2;
	private JTextField newKeywordField3;
	private JTextField newKeywordField4;
	private JTextField newKeywordField5;
	private JTextField newLevelField1;
	private JTextField newLevelField2;
	private JTextField newLevelField3;
	private JTextField newLevelField4;
	private JTextField newLevelField5;
	private String helpPDFFile;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  
	private String directoryPath = Global_Path_Var.project_dir;
	private FindKeywordsInstructor keywordFind = new FindKeywordsInstructor();
	private FindRulesInstructor ruleFind = new FindRulesInstructor();
	private FindNodesInstructor nodeFind = new FindNodesInstructor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSingleQuestion frame = new AddSingleQuestion("");
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
	public AddSingleQuestion(String questionString) {
		//Get course number from class_name
		//courseNumber = class_name.split(" ")[0];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Add a Single Question to the Help Module");
		
		JLabel yourQuestion = new JLabel("Your Question: ");
		yourQuestion.setFont(new Font("Tahoma", Font.BOLD, 11));
		yourQuestion.setBounds(10, 20, 247, 31);
		contentPane.add(yourQuestion);
		
		JLabel printedQuestion = new JLabel(questionString);
		printedQuestion.setFont(new Font("Tahoma", Font.BOLD, 11));
		printedQuestion.setBounds(200, 20, 360, 31);
		contentPane.add(printedQuestion);
		
		String[][] nodeString2 = nodeFind.getNodes(questionString);
		int next = 0;
		   //String nodeString[] = new String[11];
		   for(int i = 0; i < nodeString2.length;i=i+1){
			   for(int j = 0; j < nodeString2[i].length; j = j+1){
				   if (nodeString2[i][j] != null){
					   //System.out.println("i: " + i + ", j: " + j + ", text: " + nodeString2[i][j]);
					   //nodeString[next] = nodeString2[i][j];
					   //next = next + 1;
					   //System.out.println("keyword: " + nodeString2[i][j]);
				   }
				   if (nodeString2[i][j] == null){
					   nodeString2[i][j] = "no" + (j+1);
				   }
			   }
		   }
		String answerString[] = ruleFind.getRules(nodeString2);
		
		JLabel possibleAnswer = new JLabel("Possible Answers: ");
		possibleAnswer.setFont(new Font("Tahoma", Font.BOLD, 11));
		possibleAnswer.setBounds(10, 60, 247, 31);
		contentPane.add(possibleAnswer);
		
		JLabel printedAnswer = new JLabel(answerString[0]);
		printedAnswer.setFont(new Font("Tahoma", Font.BOLD, 11));
		printedAnswer.setBounds(200, 60, 360, 31);
		contentPane.add(printedAnswer);
		
		String[][] keywordString = keywordFind.getNodes(questionString);
		String printedKeys = "";
		for (int row = 0; row < keywordString.length; row++) {
			for (int column = 0; column < keywordString[row].length; column++) {
	            if(keywordString[row][column] != null){	
	            	//System.out.print(keywordString[row][column] + " ");
	            	printedKeys = printedKeys + keywordString[row][column] + " ";
	            }
			}
    	}
		if (printedKeys.equals("")) printedKeys = "*No Keywords were found in the database*";
		
		JLabel foundKeywords = new JLabel("Found Keywords: ");
		foundKeywords.setFont(new Font("Tahoma", Font.BOLD, 11));
		foundKeywords.setBounds(10, 100, 247, 31);
		contentPane.add(foundKeywords);
		
		JLabel printedKeywords = new JLabel(printedKeys);
		printedKeywords.setFont(new Font("Tahoma", Font.BOLD, 11));
		printedKeywords.setBounds(200, 100, 360, 31);
		contentPane.add(printedKeywords);
		
		JLabel newKeywords = new JLabel("Add New Keywords / Levels: ");
		newKeywords.setFont(new Font("Tahoma", Font.BOLD, 11));
		newKeywords.setBounds(10, 140, 247, 31);
		contentPane.add(newKeywords);
		
		newKeywordField1 = new JTextField();
		newKeywordField1.setBounds(200, 140, 180, 31);
		contentPane.add(newKeywordField1);
		newKeywordField1.setColumns(10);
		newKeywordField1.setText("Enter Keywords");
		
		newLevelField1 = new JTextField();
		newLevelField1.setBounds(395, 140, 180, 31);
		contentPane.add(newLevelField1);
		newLevelField1.setColumns(10);
		newLevelField1.setText("Enter Level numbers");
		
		newKeywordField2 = new JTextField();
		newKeywordField2.setBounds(200, 170, 180, 31);
		contentPane.add(newKeywordField2);
		newKeywordField2.setColumns(10);
		
		newLevelField2 = new JTextField();
		newLevelField2.setBounds(395, 170, 180, 31);
		contentPane.add(newLevelField2);
		newLevelField2.setColumns(10);
		
		newKeywordField3 = new JTextField();
		newKeywordField3.setBounds(200, 200, 180, 31);
		contentPane.add(newKeywordField3);
		newKeywordField3.setColumns(10);
		
		newLevelField3 = new JTextField();
		newLevelField3.setBounds(395, 200, 180, 31);
		contentPane.add(newLevelField3);
		newLevelField3.setColumns(10);
		
		newKeywordField4 = new JTextField();
		newKeywordField4.setBounds(200, 230, 180, 31);
		contentPane.add(newKeywordField4);
		newKeywordField4.setColumns(10);
		
		newLevelField4 = new JTextField();
		newLevelField4.setBounds(395, 230, 180, 31);
		contentPane.add(newLevelField4);
		newLevelField4.setColumns(10);
		
		newKeywordField5 = new JTextField();
		newKeywordField5.setBounds(200, 260, 180, 31);
		contentPane.add(newKeywordField5);
		newKeywordField5.setColumns(10);
		
		newLevelField5 = new JTextField();
		newLevelField5.setBounds(395, 260, 180, 31);
		contentPane.add(newLevelField5);
		newLevelField5.setColumns(10);
		
		JLabel addAnswer = new JLabel("Add text answer for question: ");
		addAnswer.setFont(new Font("Tahoma", Font.BOLD, 11));
		addAnswer.setBounds(10, 300, 247, 31);
		contentPane.add(addAnswer);
		
		newAnswerField = new JTextField();
		newAnswerField.setBounds(200, 300, 375, 31);
		contentPane.add(newAnswerField);
		newAnswerField.setColumns(10);
		
		JLabel addVideo = new JLabel("Add YouTube video Link answer: ");
		addVideo.setFont(new Font("Tahoma", Font.BOLD, 11));
		addVideo.setBounds(10, 340, 247, 31);
		contentPane.add(addVideo);
		
		newVideoField = new JTextField();
		newVideoField.setBounds(200, 340, 375, 31);
		contentPane.add(newVideoField);
		newVideoField.setColumns(10);
		newVideoField.setText("Ex: https://www.youtube.com/embed/XXXXXXXXXX");
		
		JLabel addPDF = new JLabel("Add answer PDF: ");
		addPDF.setFont(new Font("Tahoma", Font.BOLD, 11));
		addPDF.setBounds(10, 380, 247, 31);
		contentPane.add(addPDF);
		
		newPDFField = new JTextField();
		newPDFField.setEditable(false);
		newPDFField.setBounds(200, 380, 180, 31);
		contentPane.add(newPDFField);
		newPDFField.setColumns(10);
		
		JButton btnAddHelpChoose = new JButton("Choose");
		btnAddHelpChoose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(AddSingleQuestion.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
	                helpPDFFile = file.getAbsolutePath();
	                if(file.getName().contains(".pdf")){
	                	newPDFField.setText(file.getAbsolutePath());
	                }
	                else newPDFField.setText("File is not a PDF");
	            } else {
	            	newPDFField.setText(newPDFField.getText());
	            }
			}
		});
		btnAddHelpChoose.setBounds(395, 380, 180, 31);
		contentPane.add(btnAddHelpChoose);
		
		JLabel finishText = new JLabel("FINISH: ");
		finishText.setFont(new Font("Tahoma", Font.BOLD, 11));
		finishText.setBounds(10, 420, 247, 31);
		contentPane.add(finishText);
		
		// Upload and enter a new question button
		JButton btnAddHelpText = new JButton("Upload / New Question");
		btnAddHelpText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					AddHelpQuestions.write(helpPDFFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAddHelpText.setBounds(200, 420, 180, 31);
		contentPane.add(btnAddHelpText);
		
		// Upload and exit button
		JButton btnUploadExit = new JButton("Upload / Exit");
		btnUploadExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					AddHelpQuestions.write(helpPDFFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUploadExit.setBounds(395, 420, 180, 31);
		contentPane.add(btnUploadExit);
		
		// Back button
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Instructor_mainFrame IMF = new Instructor_mainFrame();
				IMF.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(200, 460, 180, 31);
		contentPane.add(btnBack);
		
		// Exit button
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnExit.setBounds(395, 460, 180, 31);
		contentPane.add(btnExit);
		
		// Instructions Text
		String instructionsText = "<html><body width='475'><ul><li>If 'Possible Answers' includes the answer to your question, click Exit or Back.</li>"
									+ "<li> Check spelling of all words in your question. If incorrect, click back and reenter question. </li>" + 
									"<li> Click check box next to each keyword to be included. </li>" + 
									"<li> To add new keywords, write a single keyword in the left column and a level number in the right. </li>" + 
									"<li> Level Number: Category Name (examples)"
									+ " <ol><li>Circuit elements and equipment (resistor, breadboard, dc source)</li>" 
								    + "<li>Action word (compute, change, use)</li>"
								    + "<li>Question word (why, what, how)</li>"
								    + "<li>Problem word (wrong, don't, cannot)</li>"
								    + "<li>Circuit concepts (resistance, power, energy)</li>"
								    + "<li>(positive, gain, division, unit)</li>"
								    + "<li>Circuit Laws (thevenin, conservation, superposition)</li>"
								    + "<li>Circuit diagram and measurements (series, parallel, rms, ground)</li>"
								    + "<li>Math words (sum, difference, equivalent)</li>"
								    + "<li>Units (ohm, watt, joule)</li>"
								    + "<li>Help</li></ol></li>"
								    + "<li>Enter at least one of the following: text answer, pdf, video link</li>"
								    + "<li>Format YouTube link as shown</li>"
								    + "<li>When finished, hit Upload / New Question to upload current question and enter new question, or click Upload / Exit to enter only one question</li>"
								    + "<li>To exit at any point, click Exit or Back</li></ul>";
		JLabel instructions = new JLabel(instructionsText);
		instructions.setBounds(600, 20, 485, 490);
		
		// Instructions boarder
		TitledBorder instructionsBoarder = new TitledBorder("Instructions");
		instructions.setVerticalAlignment(JLabel.TOP);
		instructions.setBorder(instructionsBoarder);
		contentPane.add(instructions);
		
		fc = new JFileChooser();
		
	}

}
