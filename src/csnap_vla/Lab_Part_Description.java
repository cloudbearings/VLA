package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.JLabel;

import java.awt.Font;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Lab_Part_Description extends JFrame {
	
	private String directoryPath = Global_Path_Var.project_dir;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab_Part_Description frame = new Lab_Part_Description(null,null,null,null);
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
	public Lab_Part_Description(final UserInfo user_info, final String course_number, final String lab_name, final String part_num) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Lab_Part_Description");

		
		// build a controller
		final SwingController controller = new SwingController();

		// Build a SwingViewFactory configured with the controller
		SwingViewBuilder factory = new SwingViewBuilder(controller);
				
		JPanel viewerComponentPanel = factory.buildViewerPanel();;
		viewerComponentPanel.setBounds(183, 11, 891, 470);
		contentPane.add(viewerComponentPanel);
		
		//go to the questions screen
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Lab_part2_questions questions = new Lab_part2_questions();
				questions.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(44, 56, 89, 23);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(part_num.equals("1")){ //if part_num == 1, then go back to the prelab
					Student_PrelabQuiz goBack = new Student_PrelabQuiz(user_info, course_number, lab_name);
					goBack.setVisible(true);
					dispose();
				} else { //otherwise, go back to the previous description screen.
					Lab_Part_Description back = new Lab_Part_Description(user_info, course_number, lab_name, Integer.toString(Integer.parseInt(part_num)-1));
					back.setVisible(true);
					dispose();
				}
				
			}
		});
		btnNewButton_1.setBounds(44, 90, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Part " + part_num);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 141, 34);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Help");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Help_Module help = new Help_Module();
				help.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(44, 124, 89, 23);
		contentPane.add(btnNewButton_2);
		
		String lab_part_PDF;
		//get Theory PDF Path
		Path path = Paths.get(directoryPath + course_number + ".txt");
		String temp;
		   try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			   lab_part_PDF = null;
		     while (scanner.hasNextLine()){
		       temp = scanner.nextLine();
		       if(temp.contains(lab_name)){
		    	   
		    	   while(!temp.contains("Lab Part: ")){
		    		   temp=scanner.nextLine();
		    	   }
		    	   
		    	   //grab the next lab part based on the part_num
		    	   for(int i = 1; i < Integer.parseInt(part_num); i++){
		    		   temp=scanner.nextLine();
		    	   }
		    	   
		    	   lab_part_PDF = temp.split("Lab Part: ")[1];
		    	   break;
		       }
		     }     
		     scanner.close();
		   } catch (IOException er) {
			lab_part_PDF = null;
			er.printStackTrace();
		}
		
		// add copy keyboard command
		ComponentKeyBinding.install(controller, viewerComponentPanel);

		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController().setAnnotationCallback(
		      new org.icepdf.ri.common.MyAnnotationCallback(
		             controller.getDocumentViewController()));
		controller.openDocument(lab_part_PDF);
		
		controller.setToolBarVisible(false);		
		
	}

}
