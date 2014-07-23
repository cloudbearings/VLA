package csnap_vla;

import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  

import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler;  

public class FindKeywordsInstructor extends DefaultHandler {
	public String[][] getNodes(String words){  
		
		   final String[] wordsString = words.split("\\s+");
		   for (int i = 0; i < wordsString.length; i++) {
		       // You may want to check for a non-word character before blindly
		       // performing a replacement
		       // It may also be necessary to adjust the character class
			   wordsString[i] = wordsString[i].replaceAll("[^\\p{Alnum}]+", "");
		       //System.out.println(wordsString[i]);
		   }  
		   final String[][] nodeMatrix = new String[11][22];
		try {  
		   // obtain and configure a SAX based parser  
		   SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();  

		   // obtain object for SAX parser  
		   SAXParser saxParser = saxParserFactory.newSAXParser();  
		  
		   // default handler for SAX handler class  
		   // all three methods are written in handler's body  
		   DefaultHandler defaultHandler = new DefaultHandler(){  
		      
		   String levelTag = "close";  
		   String nodeTag = "close";  
		   String synonymTag = "close";  
		   
		   //String levelString;
		   //String nodeString;
		   String synonymString;
 
		   Integer levelNumber = 0;
		   Integer nodeNumber = 0;
		   Integer synonymNumber = 1;
		   
		   Boolean nodeFlag = false;
		   
		    // this method is called every time the parser gets an open tag '<'  
		    // identifies which tag is being open at time by assigning an open flag  
		    public void startElement(String uri, String localName, String qName,  
		      Attributes attributes) throws SAXException { 
		     
		     if (qName.equalsIgnoreCase("LEVEL")) {  
		      levelTag = "open";  
		      levelNumber = levelNumber + 1;
		     }
		     
		     if (qName.equalsIgnoreCase("NODE")) {  
		      nodeTag = "open";  
		      nodeNumber = nodeNumber + 1;
		     }  
		     
		     if (qName.equalsIgnoreCase("SYNONYM")) {  
		      synonymTag = "open";  
		      synonymNumber = synonymNumber + 1;
		     }  
		    }  
		  
		    // prints data stored in between '<' and '>' tags  
		    public void characters(char ch[], int start, int length)  
		      throws SAXException {  

		     if (levelTag.equals("open")) {  
		      //System.out.println("First Name : " + new String(ch, start, length));  
		     }  
		     if (nodeTag.equals("open")) {  
		      //nodeString = new String(ch, start, length);  
		     }  
		     if (synonymTag.equals("open")) {  
		      synonymString = new String(ch, start, length); 
		      //System.out.println(synonymString);
		      
		      for (int j = 0; j < wordsString.length; j=j+1){
		    	  if (wordsString[j].equalsIgnoreCase(synonymString)){
		    		  nodeFlag = true;
		    		  //System.out.println(synonymString);
		    		  nodeMatrix[levelNumber-1][nodeNumber-1] = synonymString;
		    	  }  
		      }
		      
		      //System.out.println(nodeMatrix[levelNumber-1][nodeNumber-1]);
		     }    
		     
		    }  
		  
		    // calls by the parser whenever '>' end tag is found in xml   
		    // makes tags flag to 'close'  
		    public void endElement(String uri, String localName, String qName)  
		      throws SAXException {  

		     if (qName.equalsIgnoreCase("LEVEL")) {  
		    	 if(!nodeFlag){
			    	  //System.out.println("LN: " + levelNumber);
			    	  //System.out.println("NN: " + nodeNumber);
			    	  //nodeMatrix[levelNumber-1][0] = "l" + levelNumber + "df";
		    	 }
		    	 nodeFlag = false;
		    	 levelTag = "close";
		    	 nodeNumber = 0;
		     }  
		     if (qName.equalsIgnoreCase("NODE")) {  
		      nodeTag = "close"; 
		      synonymNumber = 0;
		     }  
		     if (qName.equalsIgnoreCase("SYNONYM")) {  
		      synonymTag = "close";  
		     }    
		    }  
		   };  
		     
		   // parse the XML specified in the given path and uses supplied  
		   // handler to parse the document  
		   // this calls startElement(), endElement() and character() methods  
		   // accordingly  
		   saxParser.parse("/Users/salvatoregiorgi/dropbox/Virtual_Lab/Sal/NodeConfigFile.xml", defaultHandler); 
		   
		  } catch (Exception e) {  
		   e.printStackTrace();  
		  }
		return nodeMatrix;
		  
	      //String[][] nodeMatrix = null;
		
		 } 
}



