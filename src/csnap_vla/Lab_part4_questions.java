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
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.JLabel;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//RIGHT NOW JUST HAS A TABLE HARDCODED IN
public class Lab_part4_questions extends JFrame {

	private JPanel contentPane;
	private static JTable table_1;
	private static JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab_part4_questions frame = new Lab_part4_questions();
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
	public Lab_part4_questions() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("lab_part4_questions");
		
		final JTextPane txtpnOneOrMore = new JTextPane();
		txtpnOneOrMore.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnOneOrMore.setForeground(Color.RED);
		txtpnOneOrMore.setText("One or more cells contain letters or punctuation. Are you sure this is correct?");
		txtpnOneOrMore.setBounds(32, 212, 141, 82);
		contentPane.add(txtpnOneOrMore);
		txtpnOneOrMore.setVisible(false);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Exercises exercise = new Exercises();
				exercise.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(44, 56, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Lab_part2_questions questions = new Lab_part2_questions();
				questions.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(44, 90, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Results");
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
		
		JLabel lblNewLabel_1 = new JLabel("Repeat the process from the previous section, but replace the 1000 Ohm Resistor with a 3300 Ohm Resistor.");
		lblNewLabel_1.setBounds(207, 27, 778, 14);
		contentPane.add(lblNewLabel_1);
		
		
		String[] column_names = {"Vr (VOM)", "Ir (DMM) mA", "Ir = Vr/R mA", "% Difference"};
		Object[][] data = {{"0 V", "0 mA", "0 mA", "0%"}, {"2 V", "","",""}, {"4 V", "","",""}, {"6 V", "","",""}, {"8 V", "","",""}, {"10 V", "","",""}}; 
		
		final String[] Ir_data = {"0","","","","",""};
		final String[] Ir_Vr_over_r_data = {"0","","","","",""};
		final String[] percent_diff = {"0","","","","",""};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(204, 56, 429, 119);
		contentPane.add(scrollPane);
		table_1 = new JTable(data, column_names);
		table_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtpnOneOrMore.setVisible(false);
				for(int i = 1; i < 6; i++){
					for(int j = 1; j < 4; j++){
						//every time a key is pressed, check if the cells contain any letters. if they do then display warning
						if(!(table_1.getValueAt(i,j).toString().matches("[0-9]+") || table_1.getValueAt(i,j).toString().isEmpty() || table_1.getValueAt(i,j).toString().contains("."))) {
							txtpnOneOrMore.setVisible(true);
						}
					}
				}
			}
		});
		scrollPane.setViewportView(table_1);
		
		JLabel lblNewLabel_3 = new JLabel("Using the data from the table, plot I (DMM) vs. Vr (VOM) manually. Once the curve is drawn, the level of resistance can be determined at any level of");
		lblNewLabel_3.setBounds(207, 187, 867, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("voltage or current. Using this graph, fill in the missing cells in the table below.");
		lblNewLabel_4.setBounds(207, 201, 722, 14);
		contentPane.add(lblNewLabel_4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(207, 226, 274, 55);
		contentPane.add(scrollPane_1);
		
		String[] columns = {"","Ir = 2.4 mA", "Ir = 0.8 mA"};
		Object[][] table_2_data = {{"Vr","",""},{"R","",""}}; 

		contentPane.add(scrollPane_1);
		table_2 = new JTable(table_2_data, columns);
		table_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtpnOneOrMore.setVisible(false);
				for(int i = 0; i < 2; i++){
					for(int j = 1; j < 3; j++){
						//every time a key is pressed, check if the cells contain any letters. if they do then display warning
						if(!(table_2.getValueAt(i,j).toString().matches("[0-9]+") || table_2.getValueAt(i,j).toString().isEmpty() || table_2.getValueAt(i,j).toString().contains("."))) {
							txtpnOneOrMore.setVisible(true);
						}
					}
				}
			}
		});
		scrollPane_1.setViewportView(table_2);
		
		JLabel lblNewLabel_2 = new JLabel("How does the magnitude of the slope compare to the magnitude for the 1000 Ohm Resistor? Does larger resistance give a lesser slope?");
		lblNewLabel_2.setBounds(204, 297, 833, 14);
		contentPane.add(lblNewLabel_2);
		
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(207, 322, 440, 79);
		contentPane.add(textPane);
		textPane.setBorder(BorderFactory.createLineBorder(Color.black));

		//adds table values to appropriate strings
		JButton btnNewButton_3 = new JButton("save");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				org.docx4j.wml.ObjectFactory factory;
				WordprocessingMLPackage wordMLPackage;
				try {
					wordMLPackage = WordprocessingMLPackage.load(new java.io.File("./test_lab_report.docx"));
					factory = Context.getWmlObjectFactory();
					
					wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading1","Part 2");
					wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading3","Replacing the 1000 Ohm "
							+ "Resistor with 3300 Ohms:");
					Tbl table = createFirstTable(factory, wordMLPackage);
					addBorders(table);
					wordMLPackage.getMainDocumentPart().addObject(table);

					wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading3","\nData Extrapolation from graphing the"
							+ " above table:");
					
					Tbl table2 = createSecondTable(factory, wordMLPackage);
			        addBorders(table2);
			        wordMLPackage.getMainDocumentPart().addObject(table2);
			 
					wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Heading3","How does the magnitude"
							+ " of the slope compare to the magnitude for the 1000 Ohm Resistor? Does larger "
							+ "resistance give a lesser slope?");
					wordMLPackage.getMainDocumentPart().addParagraphOfText(textPane.getText());
			        
					
					
					wordMLPackage.save(new java.io.File("./test_lab_report.docx"));
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Docx4JException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
			}
		});
		btnNewButton_3.setBounds(44, 179, 89, 23);
		contentPane.add(btnNewButton_3);
		
		
	}
	
	private static void addBorders(Tbl table) {
        table.setTblPr(new TblPr());
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);
 
        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);
        table.getTblPr().setTblBorders(borders);
    }
 
    private static Tbl createFirstTable(org.docx4j.wml.ObjectFactory factory, WordprocessingMLPackage wordMLPackage) {
        Tbl table = factory.createTbl();
        Tr tableRow1 = factory.createTr();
        Tr tableRow2 = factory.createTr();
        Tr tableRow3 = factory.createTr();
        Tr tableRow4 = factory.createTr();
        Tr tableRow5 = factory.createTr();
        Tr tableRow6 = factory.createTr();
        Tr tableRow7 = factory.createTr();
 
        addTableCell(tableRow1, "Vr (VOM)", factory, wordMLPackage);
        addTableCell(tableRow1, "Ir (DMM) mA", factory, wordMLPackage);
        addTableCell(tableRow1, "Ir = Vr/R mA", factory, wordMLPackage);
        addTableCell(tableRow1, "% Difference", factory, wordMLPackage);
        
        addTableCell(tableRow2, "0 V", factory, wordMLPackage);
        addTableCell(tableRow2, table_1.getValueAt(0,1).toString(), factory, wordMLPackage);
        addTableCell(tableRow2, table_1.getValueAt(0,2).toString(), factory, wordMLPackage);
        addTableCell(tableRow2, table_1.getValueAt(0,3).toString(), factory, wordMLPackage);
        
        addTableCell(tableRow3, "2 V", factory, wordMLPackage);
        addTableCell(tableRow3, table_1.getValueAt(1,1).toString(), factory, wordMLPackage);
        addTableCell(tableRow3, table_1.getValueAt(1,2).toString(), factory, wordMLPackage);
        addTableCell(tableRow3, table_1.getValueAt(1,3).toString(), factory, wordMLPackage);

        
        addTableCell(tableRow4, "4 V", factory, wordMLPackage);
        addTableCell(tableRow4, table_1.getValueAt(2,1).toString(), factory, wordMLPackage);
        addTableCell(tableRow4, table_1.getValueAt(2,2).toString(), factory, wordMLPackage);
        addTableCell(tableRow4, table_1.getValueAt(2,3).toString(), factory, wordMLPackage);

        addTableCell(tableRow5, "6 V", factory, wordMLPackage);
        addTableCell(tableRow5, table_1.getValueAt(3,1).toString(), factory, wordMLPackage);
        addTableCell(tableRow5, table_1.getValueAt(3,2).toString(), factory, wordMLPackage);
        addTableCell(tableRow5, table_1.getValueAt(3,3).toString(), factory, wordMLPackage);

        addTableCell(tableRow6, "8 V", factory, wordMLPackage);
        addTableCell(tableRow6, table_1.getValueAt(4,1).toString(), factory, wordMLPackage);
        addTableCell(tableRow6, table_1.getValueAt(4,2).toString(), factory, wordMLPackage);
        addTableCell(tableRow6, table_1.getValueAt(4,3).toString(), factory, wordMLPackage);

        addTableCell(tableRow7, "10 V", factory, wordMLPackage);
        addTableCell(tableRow7, table_1.getValueAt(5,1).toString(), factory, wordMLPackage);
        addTableCell(tableRow7, table_1.getValueAt(5,2).toString(), factory, wordMLPackage);
        addTableCell(tableRow7, table_1.getValueAt(5,3).toString(), factory, wordMLPackage);

        table.getContent().add(tableRow1);
        table.getContent().add(tableRow2);
        table.getContent().add(tableRow3);
        table.getContent().add(tableRow4);
        table.getContent().add(tableRow5);
        table.getContent().add(tableRow6);
        table.getContent().add(tableRow7);

        return table;
    }
	
    private static Tbl createSecondTable(org.docx4j.wml.ObjectFactory factory, WordprocessingMLPackage wordMLPackage) {
        Tbl table = factory.createTbl();
        Tr tableRow1 = factory.createTr();
        Tr tableRow2 = factory.createTr();
        Tr tableRow3 = factory.createTr();
 
        addTableCell(tableRow1, "", factory, wordMLPackage);
        addTableCell(tableRow1, "Ir = 2.4 mA", factory, wordMLPackage);
        addTableCell(tableRow1, "Ir = 0.8 mA", factory, wordMLPackage);
        
        addTableCell(tableRow2, "Vr (V)", factory, wordMLPackage);
        addTableCell(tableRow2, table_2.getValueAt(0,1).toString(), factory, wordMLPackage);
        addTableCell(tableRow2, table_2.getValueAt(0,2).toString(), factory, wordMLPackage);
        
        addTableCell(tableRow3, "R (Ohms)", factory, wordMLPackage);
        addTableCell(tableRow3, table_2.getValueAt(1,1).toString(), factory, wordMLPackage);
        addTableCell(tableRow3, table_2.getValueAt(1,2).toString(), factory, wordMLPackage);

        table.getContent().add(tableRow1);
        table.getContent().add(tableRow2);
        table.getContent().add(tableRow3);

        return table;
    }
    
	private static void addTableCell(Tr tableRow, String content, org.docx4j.wml.ObjectFactory factory, WordprocessingMLPackage wordMLPackage) {
	    Tc tableCell = factory.createTc();
	    tableCell.getContent().add(
	        wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
	    tableRow.getContent().add(tableCell);
	    }
	
}
