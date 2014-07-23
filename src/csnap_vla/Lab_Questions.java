package csnap_vla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

//RIGHT NOW JUST HAS A TABLE HARDCODED IN
public class Lab_Questions extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab_Questions frame = new Lab_Questions();
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
	public Lab_Questions() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setBounds(44, 56, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
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
		
		JLabel lblNewLabel_1 = new JLabel("Enter the data from table 3.3");
		lblNewLabel_1.setBounds(207, 27, 470, 14);
		contentPane.add(lblNewLabel_1);
		
		
		String[] column_names = {"Vr (VOM)", "Ir (DMM) mA", "Ir = Vr/R mA", "% Difference"};
		Object[][] data = {{"0 V", "0 mA", "0 mA", "0%"}, {"2 V", "","",""}, {"4 V", "","",""}, {"6 V", "","",""}, {"8 V", "","",""}, {"10 V", "","",""}}; 
		
		final String[] Ir_data = {"0","","","","",""};
		final String[] Ir_Vr_over_r_data = {"0","","","","",""};
		final String[] percent_diff = {"0","","","","",""};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(204, 56, 429, 120);
		contentPane.add(scrollPane);
		table = new JTable(data, column_names);
		scrollPane.setViewportView(table);
		

		//adds table values to appropriate strings
		JButton btnNewButton_3 = new JButton("save");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				final XYSeries series1 = new XYSeries("Current (DMM)");
		        final XYSeries series2 = new XYSeries("Current (Calculated)");

		        series1.add(0,0);
		        series2.add(0,0);
				
				for(int i = 1; i<6; i++){
					Ir_data[i] = (String)table.getValueAt(i, 1);
					series1.add(i*2,Double.parseDouble((String)table.getValueAt(i, 1)));
				}
				
				for(int i = 1; i<6; i++){
					Ir_Vr_over_r_data[i] = (String)table.getValueAt(i, 2);
					series2.add(i*2,Double.parseDouble((String)table.getValueAt(i,2)));
				}
				
				for(int i = 1; i<6; i++){
					percent_diff[i] = (String)table.getValueAt(i, 3);
				}
				
				for(int i = 0; i < 6; i++){
					System.out.println(Ir_data[i] + " " + Ir_Vr_over_r_data[i] + " " + percent_diff[i]);
				}
				
		        final XYSeriesCollection dataset = new XYSeriesCollection();
		        dataset.addSeries(series1);
		        dataset.addSeries(series2);
		        JFreeChart lineChartObject = ChartFactory.createXYLineChart("Voltage Vs. Current", "Volts", "Current", dataset);
		        
		        int width=640; /* Width of the image */
                int height=480; /* Height of the image */                
                File lineChart=new File("line_Chart_example.png");              
                try {
					ChartUtilities.saveChartAsPNG(lineChart,lineChartObject,width,height);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
				
			}
		});
		btnNewButton_3.setBounds(44, 179, 89, 23);
		contentPane.add(btnNewButton_3);	
		
		
		
	}
}
