package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.JLabel;

import java.awt.Font;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Student_theory2 extends JFrame {

	private JPanel contentPane;
	private String directoryPath = Global_Path_Var.project_dir;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_theory frame = new Student_theory(null,null,null);
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
	public Student_theory2(final UserInfo user_info, final String course_number, final String lab_name) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Student_theory");

		
		// build a controller
		final SwingController controller = new SwingController();

		// Build a SwingViewFactory configured with the controller
		//SwingViewBuilder factory = new SwingViewBuilder(controller);
				
		//JPanel viewerComponentPanel = factory.buildViewerPanel();;
		//viewerComponentPanel.setBounds(183, 11, 891, 470);
		//contentPane.add(viewerComponentPanel);
		//JPanel middlePanel = new JPanel ();
	    //middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );

		JTextPane display = new JTextPane ( );
		display.setContentType("text/html");
	    display.setEditable ( false ); // set textArea non-editable
	    
	    byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(directoryPath + "OhmsLawTheory" + ".html"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    String str = new String(encoded);
	    //String str = "";
        //for (int i = 0; i < 50; ++i)
            //str += "Some text\n";
        display.setText(str);

	    JScrollPane scroll = new JScrollPane ( display );
	    //scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scroll.setBounds(183, 11, 891, 470); 
	    //Add Textarea in to middle panel
	    contentPane.add ( scroll );

	    // My code
	    //JFrame frame = new JFrame ();
	    //contentPane.add ( middlePanel );
	    //frame.pack ();
	    //frame.setLocationRelativeTo ( null );
	    //frame.setVisible ( true );
		
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
		//JLabel instructions = new JLabel(instructionsText);
		//instructions.setBounds(183, 11, 891, 470);

		// Instructions boarder
		//TitledBorder instructionsBoarder = new TitledBorder("Theory");
		//instructions.setVerticalAlignment(JLabel.TOP);
		//instructions.setBorder(instructionsBoarder);
		//contentPane.add(instructions);
		
		//go to the pretest frame when clicked
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Student_Pretest pretest = new Student_Pretest(user_info, course_number, lab_name);
				pretest.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(44, 56, 89, 23);
		contentPane.add(btnNewButton);
		
		//go back to the student_mainFrame when clicked
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Student_mainFrame goBack = new Student_mainFrame(user_info);
				goBack.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(44, 90, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Theory");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 141, 34);
		lblNewLabel.setToolTipText("Here is the definition of theory");
		contentPane.add(lblNewLabel);
		
		//open the help module
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
		
		String theory_PDF;
		//get Theory PDF Path
		Path path = Paths.get(directoryPath + course_number + ".txt");
		String temp;
		   try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			   theory_PDF = null;
		     while (scanner.hasNextLine()){
		       temp = scanner.nextLine();
		       if(temp.contains(lab_name)){
		    	   while(!temp.contains("Theory Path: ")){
		    		   temp=scanner.nextLine();
		    	   }
		    	   theory_PDF = temp.split("Theory Path: ")[1];
		    	   break;
		       }
		     }     
		     scanner.close();
		   } catch (IOException er) {
			theory_PDF = null;
			er.printStackTrace();
		}
		
		// add copy keyboard command
		//ComponentKeyBinding.install(controller, viewerComponentPanel);

		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController().setAnnotationCallback(
		      new org.icepdf.ri.common.MyAnnotationCallback(
		             controller.getDocumentViewController()));
		controller.openDocument(theory_PDF);
		
		controller.setToolBarVisible(false);		
		
	}
	

}
