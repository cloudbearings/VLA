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




public class YouTubeHelp extends JPanel {

	
	public YouTubeHelp(String youtube_link) {
	    super(new BorderLayout());
	    JPanel webBrowserPanel = new JPanel(new BorderLayout());
	    final JWebBrowser webBrowser = new JWebBrowser();
	    webBrowser.navigate(youtube_link);
	    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
	    add(webBrowserPanel, BorderLayout.CENTER);
	    webBrowser.setBarsVisible(false);
	    webBrowser.setStatusBarVisible(true);
	    
	    
	    
	
	}
		
	  
	
}
