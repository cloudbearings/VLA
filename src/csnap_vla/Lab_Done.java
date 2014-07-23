package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Lab_Done extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab_Done frame = new Lab_Done();
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
	public Lab_Done() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Lab_Done");

		
		JLabel lblFinished = new JLabel("Finished!");
		lblFinished.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblFinished.setBounds(157, 11, 210, 65);
		contentPane.add(lblFinished);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Exercises back = new Exercises();
				back.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(167, 87, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("OR");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(197, 121, 57, 44);
		contentPane.add(lblNewLabel);
		
		//opens a file browser to choose the directory you want to save the word document to
		JButton btnNewButton_1 = new JButton("Generate Word Document");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				String save_path;
				int returnVal = fc.showOpenDialog(Lab_Done.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
	                File file = fc.getSelectedFile();
                	save_path = file.getAbsolutePath() + "/test_lab_report.docx";
                	file = new java.io.File(save_path);
                    FileChannel source = null;
                    FileChannel destination = null;
                	try {
                        source = new FileInputStream(new java.io.File("./test_lab_report.docx")).getChannel();
                        destination = new FileOutputStream(file).getChannel();
                        destination.transferFrom(source, 0, source.size());
                    } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    finally {
                        if(source != null) {
                            try {
								source.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        }
                        if(destination != null) {
                            try {
								destination.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        }
                    }
				}
				
			}
		});
		btnNewButton_1.setBounds(105, 176, 210, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
