package csnap_vla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Help_Module extends JFrame {

	String printString[];
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help_Module frame = new Help_Module();
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
	public Help_Module() {

		
		setTitle("Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Help_Module");

		
		final JButton btnNewButton_2 = new JButton("View Description");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PDF_Web_viewer pdf_viewer = new PDF_Web_viewer(printString[1], "pdf");
				pdf_viewer.setVisible(true);
			}
		});
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setBounds(195, 238, 130, 23);
		contentPane.add(btnNewButton_2);
		
		final JButton btnNewButton_3 = new JButton("Watch Video");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PDF_Web_viewer watch_video = new PDF_Web_viewer(printString[2], "web");
				watch_video.setVisible(true);
			}
		});
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBounds(55, 238, 130, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Enter your question here...");
		lblNewLabel.setBounds(10, 11, 237, 14);
		contentPane.add(lblNewLabel);
		
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 36, 414, 65);
		contentPane.add(textPane);
		textPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		final JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(10, 162, 414, 65);
		contentPane.add(textPane_1);
		textPane_1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				FindNodes nodeFind = new FindNodes();
				FindRules ruleFind = new FindRules();
				String[][] nodeString2 = nodeFind.getNodes(textPane.getText());
				   
				int next = 0;
				   String nodeString[] = new String[11];
				   for(int i = 0; i < nodeString2.length;i=i+1){
					   for(int j = 0; j< nodeString2[i].length; j = j+1){
						   if (nodeString2[i][j] != null){
							   nodeString[next] = nodeString2[i][j];
							   next = next + 1;
							   //System.out.println("keyword: " + nodeString2[i][j]);
						   }
						   if (nodeString2[i][j] == null){
							   nodeString2[i][j] = "no" + (j+1);
						   }
					   }
				   }
				
				printString = ruleFind.getRules(nodeString2);
				System.out.println(printString[1]);
				System.out.println(printString[2]);
				textPane_1.setText(printString[0]);
				if(!printString[0].equals("No answer found.")){
					btnNewButton_2.setEnabled(true);
					btnNewButton_3.setEnabled(true);
				} else {
					btnNewButton_2.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}
				
			}
		});
		btnNewButton.setBounds(335, 112, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Results...");
		lblNewLabel_1.setBounds(10, 137, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				
			}
		});
		btnNewButton_1.setBounds(335, 238, 89, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
