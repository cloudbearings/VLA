package csnap_vla;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;




public class Circuitlab extends JPanel {

	/* Standard main method to try that test as a standalone application. */
	  
	  public static void main(String[] args) {
	    UIUtils.setPreferredLookAndFeel();
	    NativeInterface.open();
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        JFrame frame = new JFrame("DJ Native Swing Test");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.getContentPane().add(new Circuitlab(), BorderLayout.CENTER);
	        frame.setSize(1000, 600);
	        frame.setLocationByPlatform(true);
	        frame.setVisible(true);
	      }
	    });
	    NativeInterface.runEventPump();
	  } 
	
	public Circuitlab() {
	    super(new BorderLayout());
	    JPanel webBrowserPanel = new JPanel(new BorderLayout());
	    final JWebBrowser webBrowser = new JWebBrowser();
	    webBrowser.navigate("https://www.circuitlab.com/");
	    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
	    add(webBrowserPanel, BorderLayout.CENTER);
	    webBrowser.setBarsVisible(false);
	    webBrowser.setStatusBarVisible(true);

	    
	    
	    webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
	    	
	    	//prevents any links except to open except circuitlab
	        public void locationChanging(WebBrowserNavigationEvent e) {
	          String newResourceLocation = e.getNewResourceLocation();
	          if(!newResourceLocation.startsWith("https://www.circuitlab.com/")) {
	            e.consume();
	          }
	        } 
	        
	        //prevents new windows being opened - like when ads are clicked.
	        public void windowWillOpen(WebBrowserWindowWillOpenEvent e) {
	            e.getNewWebBrowser().addWebBrowserListener(new WebBrowserAdapter() {
	              @Override
	              public void locationChanging(WebBrowserNavigationEvent e) {
	                final JWebBrowser webBrowser = e.getWebBrowser();
	                webBrowser.removeWebBrowserListener(this);
	                String newResourceLocation = e.getNewResourceLocation();
	                boolean isBlocked = false;
	                if(newResourceLocation.startsWith("http")) {
	                  isBlocked = true;
	                } 
	                if(isBlocked) {
	                  e.consume();
	                  // The URL Changing event is special: it is synchronous so disposal must be deferred.
	                  SwingUtilities.invokeLater(new Runnable() {
	                    public void run() {
	                      webBrowser.getWebBrowserWindow().dispose();
	                    }
	                  });
	                }
	              }
	            });
	          }
	    }); 
	
	}
		
	  
	
}
