package csnap_vla;
import java.util.Arrays;

import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  

import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler;  
  
public class FindRules extends DefaultHandler {  
   
public String[] getRules(final String[][] nodes){  
	final String[] answerString = new String[3];// = "No answer found.";
  try {  
   // obtain and configure a SAX based parser  
   SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();  

   // obtain object for SAX parser  
   SAXParser saxParser = saxParserFactory.newSAXParser();  
  
   // default handler for SAX handler class  
   // all three methods are written in handler's body  
   DefaultHandler defaultHandler = new DefaultHandler(){  
      
    String ruleTag = "close";  
    String conditionTag = "close";  
    String optionTag = "close";  
    String resultTag = "close";  
    String typeTag = "close";  
    String pdfTag = "close";
    String videoTag = "close";
    
    String typeString[] = {"general","theory","video", "safety"};
    String resultString;
    String pdfString;
    String videoString;
    
    Integer conditionNumber = 0;
    Integer typeNumber = 4;
    
    Boolean[] typeMatrix = new Boolean[typeNumber];
    Boolean conditionMet = false;
    Boolean ruleMet = false;
    Boolean previousCondition = true;
    
    // this method is called every time the parser gets an open tag '<'  
    // identifies which tag is being open at time by assigning an open flag  
    public void startElement(String uri, String localName, String qName,  
      Attributes attributes) throws SAXException { 
    
     if (qName.equalsIgnoreCase("RULE")) {  
      ruleTag = "open";  
      typeNumber = 0;
     }  
     if (qName.equalsIgnoreCase("CONDITION")) {  
      conditionTag = "open";  
     }  
     if (qName.equalsIgnoreCase("OPTION")) {  
      optionTag = "open";  
     }  
     if (qName.equalsIgnoreCase("RESULT")) {  
      resultTag = "open";  
     }
     if (qName.equalsIgnoreCase("TYPE")) {  
         typeTag = "open";  
     }
     if (qName.equalsIgnoreCase("PDF")) {  
         pdfTag = "open";  
     }
     if (qName.equalsIgnoreCase("VIDEO")) {  
         videoTag = "open";  
     }
    }  
  
    // prints data stored in between '<' and '>' tags  
    public void characters(char ch[], int start, int length)  
      throws SAXException {  

     if (ruleTag.equals("open")) {  
    	 
     }  
     if (conditionTag.equals("open")) {  
       
     }  
     if (optionTag.equals("open")) {  
         if(!conditionMet && previousCondition){
        	 String optionString = new String(ch, start, length);
        	 for (int j = 0; j < nodes[conditionNumber].length; j = j+1){
        		 //System.out.println("CN: " + conditionNumber);
        		 if(nodes[conditionNumber][j].equals(optionString)) {
        			 conditionMet = true;
        		 }
        	 }
         }
     }  
     if (resultTag.equals("open")) {  
    	 resultString = new String(ch, start, length);  
     }
     if (typeTag.equals("open")) {  
    	 //String typeString = new String(ch, start, length);
    	 //if (typeString.equalsIgnoreCase("GENERAL")) {
    	//	 typeMatrix[typeNumber] = true;
    	 //} else if (typeString.equalsIgnoreCase("THEORY")) {
    	//	 typeMatrix[typeNumber] = true;
    	 //} else if (typeString.equalsIgnoreCase("VIDEO")) {
    	//	 typeMatrix[typeNumber] = true;
    	 //} else if (typeString.equalsIgnoreCase("SAFETY")) {
    	//	 typeMatrix[typeNumber] = true;
    	 //} 
     }
     if (pdfTag.equals("open")) {  
    	 pdfString = new String(ch, start, length);
    	 
     } 
     if (videoTag.equals("open")) {  
    	 videoString = new String(ch, start, length);
    	 
     } 
    }  
  
    // calls by the parser whenever '>' end tag is found in xml   
    // makes tags flag to 'close'  
    public void endElement(String uri, String localName, String qName)  
      throws SAXException {  

     if (qName.equalsIgnoreCase("RULE")) {  
      ruleTag = "close";  
      conditionNumber = 0;
      typeNumber = 0;
      
      if(ruleMet && previousCondition){
    	  answerString[0] = resultString;
    	  answerString[1] = pdfString;
    	  answerString[2] = videoString;
    	  //System.out.println("Answer: " + resultString);
    	  //System.out.print("Found in: ");
    	  //for(int j = 0; j < 4; j = j + 1) {
          //    if (typeMatrix[j] == true){
          //  	// write types to files
          //  	if(j == 0) {
          //  		System.out.print(typeString[j]);
          //  	} else{
          //  		System.out.print(", " + typeString[j]);
          //  	}     		
          //    }
          // }
    	  //System.out.print(".");
    	  //System.out.println();System.out.println();
      }
      previousCondition = true;
      conditionMet = false;
      ruleMet = false;
      Arrays.fill(typeMatrix, Boolean.FALSE);
     }
     if (qName.equalsIgnoreCase("CONDITION")) {  
      conditionTag = "close";  
      if (conditionMet) {
    	  ruleMet = true;
    	  previousCondition = true;
      } else {
    	  previousCondition = false;
      }
      conditionMet = false;
      conditionNumber = conditionNumber + 1;
     }  
     if (qName.equalsIgnoreCase("OPTION")) {  
      optionTag = "close";  
     }  
     if (qName.equalsIgnoreCase("RESULT")) {  
      resultTag = "close";  
     }
     if (qName.equalsIgnoreCase("TYPE")) {  
      typeTag = "close";  
      //typeNumber = typeNumber + 1;
     }
     if (qName.equalsIgnoreCase("VIDEO")) {  
         videoTag = "close";  
        }
     if (qName.equalsIgnoreCase("PDF")) {  
         pdfTag = "close";  
        }
    }  
   };  
     
   // parse the XML specified in the given path and uses supplied  
   // handler to parse the document  
   // this calls startElement(), endElement() and character() methods  
   // accordingly  
   saxParser.parse(Global_Path_Var.rules_path, defaultHandler);  
  } catch (Exception e) {  
   e.printStackTrace();  
  } 
  if (answerString[0] == null){
	  answerString[0] = "No answer found.";
  }
  return answerString;
 }  
}  