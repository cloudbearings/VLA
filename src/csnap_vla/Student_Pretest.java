package csnap_vla;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.batik.svggen.font.Font;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.graphics.text.PageText;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;

import java.awt.SystemColor;

import javax.swing.JTextField;

public class Student_Pretest extends JFrame {

	private JPanel contentPane;
	private String directoryPath = Global_Path_Var.project_dir;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  
	private JTextField textField1;
	private String[] pretestAnswers = new String[4];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_Pretest frame = new Student_Pretest(null,null,null);
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
	public Student_Pretest(final UserInfo user_info, final String course_number, final String lab_name) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Student_Pretest");

		
		Path path = Paths.get(directoryPath + course_number + ".txt");
		
		//Create the quiz class to get/compare students pretest quiz answers
		final Quiz pretest_quiz = new Quiz(course_number, lab_name, "Pretest");
		
		
		// Create PDF Viewer
		// build a controller
		final SwingController controller = new SwingController();

		// Build a SwingViewFactory configured with the controller
		SwingViewBuilder factory = new SwingViewBuilder(controller);
				
		JPanel viewerComponentPanel = factory.buildViewerPanel();;
		viewerComponentPanel.setBounds(183, 11, 891, 470);
		contentPane.add(viewerComponentPanel);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Pretest");
		lblNewLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 141, 34);
		contentPane.add(lblNewLabel);
		
		
		// Question/Answer pane
			
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 124, 168, 357);
		contentPane.add(scrollPane);
		
		JPanel questionPanel = new JPanel();
		questionPanel.setLayout(null);
		
		if(pretest_quiz.get_number_questions() > 12){
			// 12 is the number of questions boxes that will fit in the frame - if there needs to be more then
			// resize the panel's height: original height + textPane padding + # of extra * 20
			questionPanel.setPreferredSize(new Dimension(questionPanel.getWidth(), questionPanel.getHeight()+113+(pretest_quiz.get_number_questions() * 20)));
		}
		
		scrollPane.setViewportView(questionPanel);
		
		JTextPane tpInstructions = new JTextPane();
		tpInstructions.setBackground(SystemColor.control);
		tpInstructions.setText("Enter each question's answer in its cooresponding answer box below. Press Next when finished.");
		tpInstructions.setBounds(10, 11, 146, 93);
		questionPanel.add(tpInstructions);
		
		JLabel[] question_labels = new JLabel[pretest_quiz.get_number_questions()];
		final JTextField[] answer_fields = new JTextField[pretest_quiz.get_number_questions()];
		
		JLabel label1 = new JLabel("1)");
		label1.setBounds(10, 112, 34, 20); // x,y,w,h
		questionPanel.add(label1);
		question_labels[0] = label1; //add the first label to the question_labels array
		
		textField1 = new JTextField();
		textField1.setBounds(54, 112, 48, 20);
		questionPanel.add(textField1);
		textField1.setColumns(10);
		answer_fields[0] = textField1; //add the first text field to the answer_fields array
		
		
		final JLabel lblNewLabel_1 = new JLabel("Please enter all answers.");
		lblNewLabel_1.setBounds(10, 332, 215, 23);
		questionPanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11));
		lblNewLabel_1.setVisible(false);
		
		//Dynamically add a question/answer
		for(int i = 1; i < pretest_quiz.get_number_questions(); i++){
			question_labels[i] = new JLabel((i+1) + ")"); //creates new JLabel starting at 2
			question_labels[i].setBounds(10, 112+(i*20), 34, 20);
			questionPanel.add(question_labels[i]);
			
			answer_fields[i] = new JTextField(); //creates new JTextField
			answer_fields[i].setBounds(54, 112+(i*20), 48, 20);
			answer_fields[i].setColumns(10);
			if(pretestAnswers[i] != null) {
				answer_fields[i].setText(pretestAnswers[i]);
			}
			questionPanel.add(answer_fields[i]);
			
			System.out.println(pretestAnswers[i-1]);
			
		} 
		
		
		//get Pretest PDF Path
		String pretest_PDF;
		String temp;
		   try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			   pretest_PDF = null;
		     while (scanner.hasNextLine()){
		       temp = scanner.nextLine();
		       if(temp.contains(lab_name)){
		    	   while(!temp.contains("Pretest Path: ")){
		    		   temp=scanner.nextLine();
		    	   }
		    	   pretest_PDF = temp.split("Pretest Path: ")[1];
		    	   break;
		       }
		     }      
		     scanner.close();
		   } catch (IOException er) {
			pretest_PDF = null;
			er.printStackTrace();
		}
		
		// Finish adding PDF viewer interface
		// add copy keyboard command
		ComponentKeyBinding.install(controller, viewerComponentPanel);

		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController().setAnnotationCallback(
		      new org.icepdf.ri.common.MyAnnotationCallback(
		             controller.getDocumentViewController()));
		controller.openDocument(pretest_PDF);
		
		controller.setToolBarVisible(false);	
		
		JButton btnNext = new JButton("Next");
		btnNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				boolean success = true;
				String student_answers = "";
				
				//check if any fields are empty, throw error if they are and do not proceed		
				//put all students answers into a string and compare it against the correct answers
				for(int i = 0; i < pretest_quiz.get_number_questions(); i++){
					
					if(answer_fields[i].getText().isEmpty() || !answer_fields[i].getText().replaceAll("[a-dA-D]", "").isEmpty()){
						success = false;
						lblNewLabel_1.setVisible(true);
						System.out.println("empty");

					} else {
						pretestAnswers[i] = answer_fields[i].getText();
						System.out.println(pretestAnswers[i]);
						student_answers += answer_fields[i].getText();
					}
										
				}
					
				//print correct amount to screen and move on
				if(success){
					
					System.out.println(pretest_quiz.check_answers(student_answers) + " out of " + pretest_quiz.get_number_questions());						
					Student_Prelab prelab = new Student_Prelab(user_info, course_number, lab_name);
					prelab.setVisible(true);
					dispose();
				}
				
			}
		});
		btnNext.setBounds(44, 56, 89, 23);
		contentPane.add(btnNext);
		
		//go back to the theory screen
				JButton btnBack = new JButton("Back");
				btnBack.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						for(int i = 0; i < pretest_quiz.get_number_questions(); i++){
							pretestAnswers[i] = answer_fields[i].getText();
						}
						Student_theory theory = new Student_theory(user_info, course_number, lab_name);
						theory.setVisible(true);
						dispose();
					}
				});
				btnBack.setBounds(44, 90, 89, 23);
				contentPane.add(btnBack);
		
		
	}
}
