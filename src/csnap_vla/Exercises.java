package csnap_vla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.JLabel;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.JTextField;
import javax.swing.JTextPane;

//RIGHT NOW JUST HAS A TABLE HARDCODED IN
public class Exercises extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exercises frame = new Exercises();
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
	public Exercises() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Exercises");

		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Lab_Done next = new Lab_Done();
				next.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(44, 56, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Lab_part4_questions questions = new Lab_part4_questions();
				questions.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(44, 90, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Exercises");
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
		
		JLabel lblNewLabel_1 = new JLabel("Plot the linear curve for 100 Ohms and 10,000 Ohms. As the resistance increases, does the slope (defined by (Change in current)/(Change in Voltage)) increase or decrease?");
		lblNewLabel_1.setBounds(207, 27, 867, 14);
		contentPane.add(lblNewLabel_1);		
		
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(217, 56, 367, 34);
		contentPane.add(textPane);
		textPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lblNewLabel_2 = new JLabel("Under ideal conditions, is the plot of Ir vs. Vr for a fixed resistor always a striaght line coming from (0,0)?");
		lblNewLabel_2.setBounds(207, 124, 579, 14);
		contentPane.add(lblNewLabel_2);
		
		final JTextPane textPane_1 = new JTextPane();
		textPane_1.setBorder(BorderFactory.createLineBorder(Color.black));
		textPane_1.setBounds(217, 149, 367, 34);
		contentPane.add(textPane_1);
		
		JLabel lblNewLabel_3 = new JLabel("Which two terminals of a dc power supply must always be connected to obtain the desired voltage?");
		lblNewLabel_3.setBounds(207, 220, 528, 14);
		contentPane.add(lblNewLabel_3);
		
		final JTextPane textPane_2 = new JTextPane();
		textPane_2.setBorder(BorderFactory.createLineBorder(Color.black));
		textPane_2.setBounds(217, 245, 367, 34);
		contentPane.add(textPane_2);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				org.docx4j.wml.ObjectFactory factory;
				WordprocessingMLPackage wordMLPackage;
				try {
					wordMLPackage = WordprocessingMLPackage.load(new java.io.File("./test_lab_report.docx"));
					factory = Context.getWmlObjectFactory();
					
					wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading1","Exercises");

					wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading3","Plot the linear "
							+ "curve for 100 Ohms and 10,000 Ohms. As the resistance increases, does the slope "
							+ "(defined by (Change in current)/(Change in Voltage)) increase or decrease?");
					wordMLPackage.getMainDocumentPart().addParagraphOfText(textPane.getText());			
					
					wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading3","Under ideal "
							+ "conditions, is the plot of Ir vs. Vr for a fixed resistor always a striaght line "
							+ "coming from (0,0)?");
					wordMLPackage.getMainDocumentPart().addParagraphOfText(textPane_1.getText());	
					
					wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading3","Which two terminals"
							+ " of a dc power supply must always be connected to obtain the desired voltage?");
					wordMLPackage.getMainDocumentPart().addParagraphOfText(textPane_2.getText());	
					
					wordMLPackage.save(new java.io.File("./test_lab_report.docx"));
				} catch (InvalidFormatException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				} catch (Docx4JException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}	
				
			}
		});
		btnSave.setBounds(44, 158, 89, 23);
		contentPane.add(btnSave);

		
		
	}
}
