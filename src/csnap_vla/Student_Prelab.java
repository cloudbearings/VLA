package csnap_vla;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.UIManager;




import java.awt.BorderLayout;

import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;


public class Student_Prelab extends JFrame {

	private JPanel contentPane;
	private String directoryPath = Global_Path_Var.project_dir;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIUtils.setPreferredLookAndFeel();
	    NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_Prelab frame = new Student_Prelab(null,null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	    NativeInterface.runEventPump();
	}

	/**
	 * Create the frame.
	 */
	public Student_Prelab(final UserInfo user_info, final String course_number, final String lab_name) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1, 1, 1365, 730); //bounds of my screen specifically
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Student_Prelab");

		
		// build a controller
		final SwingController controller = new SwingController();

		// Build a SwingViewFactory configured with the controller
		SwingViewBuilder factory = new SwingViewBuilder(controller);
				
		final JPanel viewerComponentPanel = factory.buildViewerPanel();;
		viewerComponentPanel.setBounds(183, 11, 1160, 710);

		contentPane.add(viewerComponentPanel);
		
		//go to the prelab quiz screen
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Student_PrelabQuiz pretest = new Student_PrelabQuiz(user_info, course_number, lab_name);
				pretest.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(44, 56, 89, 23);
		contentPane.add(btnNewButton);
		
		//go back to the student_pretest screen
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Student_Pretest goBack = new Student_Pretest(user_info, course_number, lab_name);
				goBack.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(44, 90, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Prelab");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 11, 141, 34);
		contentPane.add(lblNewLabel);
		
		//go to the help module
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
		
		JLabel lblNewLabel_1 = new JLabel("Simulate");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(62, 184, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		//initialize the circuitlab webbrowser screen
	    NativeInterface.open();
		final Circuitlab cl = new Circuitlab();
		cl.setBounds(183, 375, 1160, 375);
		contentPane.add(cl);
		cl.setVisible(false);
		
		//when clicked, resize the PDF viewer to half the window, so the browser takes up the other half
		final JButton btnNewButton_3 = new JButton("Circuitlab");
		final JButton btnPrelab = new JButton("Prelab");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				viewerComponentPanel.setBounds(183, 11, 1160, 375); //half screen

				cl.setVisible(true);
				btnNewButton_3.setVisible(false);
				btnPrelab.setVisible(true);
				
			}
		});
		btnNewButton_3.setBounds(44, 341, 89, 23);
		contentPane.add(btnNewButton_3);
		
		//resize the PDF viewer to full screen if needed
		btnPrelab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				viewerComponentPanel.setBounds(183, 11, 1160, 710);
				btnNewButton_3.setVisible(true);
				btnPrelab.setVisible(false);
				cl.setVisible(false);
				viewerComponentPanel.setVisible(true);
			}
		});
		btnPrelab.setBounds(44, 341, 89, 23);
		contentPane.add(btnPrelab);
		
		JTextPane txtpnChooseCircuitlabTo = new JTextPane();
		txtpnChooseCircuitlabTo.setBackground(UIManager.getColor("EditorPane.disabledBackground"));
		txtpnChooseCircuitlabTo.setText("Choose Circuitlab to simulate the circuit online. This option requires you to log in with a username and pasword. Otherwise, choose one of the installed circuit simulators that you have installed below.");
		txtpnChooseCircuitlabTo.setBounds(20, 209, 141, 118);
		contentPane.add(txtpnChooseCircuitlabTo);
		
		
		final JTextPane txtpnIfMultisimIs = new JTextPane();
		txtpnIfMultisimIs.setText("If Multisim is installed on your computer, open it and simulate the prelab and generate a netlist.");
		txtpnIfMultisimIs.setBounds(10, 404, 163, 77);
		contentPane.add(txtpnIfMultisimIs);
		txtpnIfMultisimIs.setBorder(BorderFactory.createLineBorder(Color.black));


		txtpnIfMultisimIs.setVisible(false);
		
		//displays some help text on using multisim
		JButton btnNewButton_4 = new JButton("Multisim");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtpnIfMultisimIs.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(44, 375, 89, 23);
		contentPane.add(btnNewButton_4);
		
		
		String theory_PDF;
		//get Theory PDF Path
		Path path = Paths.get(directoryPath + course_number + ".txt");
		String temp;
		   try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			   theory_PDF = null;
		     while (scanner.hasNextLine()){
		       temp = scanner.nextLine();
		       if(temp.contains(lab_name)){
		    	   while(!temp.contains("Prelab Path: ")){
		    		   temp=scanner.nextLine();
		    	   }
		    	   theory_PDF = temp.split("Prelab Path: ")[1];
		    	   break;
		       }
		     }     
		     scanner.close();
		   } catch (IOException er) {
			theory_PDF = null;
			er.printStackTrace();
		}
		
		// add copy keyboard command
		ComponentKeyBinding.install(controller, viewerComponentPanel);

		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController().setAnnotationCallback(
		      new org.icepdf.ri.common.MyAnnotationCallback(
		             controller.getDocumentViewController()));
		controller.openDocument(theory_PDF);
		
		controller.setToolBarVisible(false);		
	}
}
