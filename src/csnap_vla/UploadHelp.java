package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

public class UploadHelp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadHelp frame = new UploadHelp();
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
	public UploadHelp() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("UploadHelp");

		
		JTextPane txtpnTheoryPdfMay = new JTextPane();
		txtpnTheoryPdfMay.setEditable(false);
		txtpnTheoryPdfMay.setText("Theory PDF: May contain any kind of text and visuals.\r\nPretest/Prelab Questions PDF: Must be in multiple choice format. May contain any kind of text or visuals.\r\nPretest/Prelab Answers PDF: Must be in the format Question#:Answer, as seen below\r\n1:a\r\n2:b\r\n3:d\r\nPrelab PDF: May contain any text or visuals.");
		txtpnTheoryPdfMay.setBounds(10, 11, 414, 240);
		contentPane.add(txtpnTheoryPdfMay);
	}

}
