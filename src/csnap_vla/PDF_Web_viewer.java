package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class PDF_Web_viewer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PDF_Web_viewer frame = new PDF_Web_viewer(null,null);
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
	public PDF_Web_viewer(String target_location, String web_or_pdf) {
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
				
		final JPanel viewerComponentPanel = factory.buildViewerPanel();;
		viewerComponentPanel.setBounds(183, 11, 891, 470);
		contentPane.add(viewerComponentPanel);
		
		NativeInterface.open();
		final YouTubeHelp yth = new YouTubeHelp(target_location);
		yth.setBounds(183, 11, 891, 470);
		contentPane.add(yth);
		yth.setVisible(false);
		
		if(web_or_pdf.equals("web")){
			viewerComponentPanel.setVisible(false);
			yth.setVisible(true);
		} else {
			yth.setVisible(false);
			viewerComponentPanel.setVisible(true);
			// add copy keyboard command
			ComponentKeyBinding.install(controller, viewerComponentPanel);

			// add interactive mouse link annotation support via callback
			controller.getDocumentViewController().setAnnotationCallback(
			      new org.icepdf.ri.common.MyAnnotationCallback(
			             controller.getDocumentViewController()));
			controller.openDocument(target_location);
					
			controller.setToolBarVisible(false);
		}
		
		
		
		
	}

}
