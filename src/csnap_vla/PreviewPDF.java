package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.AbstractListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import javax.swing.JButton;

public class PreviewPDF extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreviewPDF frame = new PreviewPDF(null);
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
	public PreviewPDF(String displayPDF) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("PDF_Web_viewer");

		
		// build a controller
		final SwingController controller = new SwingController();

		// Build a SwingViewFactory configured with the controller
		SwingViewBuilder factory = new SwingViewBuilder(controller);
				
		JPanel viewerComponentPanel = factory.buildViewerPanel();;
		viewerComponentPanel.setBounds(183, 11, 891, 470);
		contentPane.add(viewerComponentPanel);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setBounds(44, 54, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBounds(44, 101, 89, 23);
		contentPane.add(btnNewButton_1);
		
		// add copy keyboard command
		ComponentKeyBinding.install(controller, viewerComponentPanel);

		// add interactive mouse link annotation support via callback
		controller.getDocumentViewController().setAnnotationCallback(
		      new org.icepdf.ri.common.MyAnnotationCallback(
		             controller.getDocumentViewController()));
		controller.openDocument(displayPDF);
		
		controller.setToolBarVisible(false);
		
		
	}
}
