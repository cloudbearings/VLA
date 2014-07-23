package csnap_vla;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModifyXMLFile {
	public String write(final String keyword, int levelNumber) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		Document doc = domFactory.newDocumentBuilder().parse("/Users/salvatoregiorgi/Desktop/NodeConfigFile.xml");

		String nodes = update(doc, "levelnode/level/node/synonym", keyword, levelNumber);
		
		// write the content into xml file
		String filepath = "/Users/salvatoregiorgi/Desktop/NodeConfigFile.xml";
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
		return nodes;
		//update(doc, "configuration/param2", "asdf");
		//update(doc, "configuration/test/param3", "true");
		/*   try {
			String filepath = "/Users/salvatoregiorgi/Desktop/NodeConfigFile.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
	 
			// Get the root element
			//Node levelNode = doc.getFirstChild();
	 
			// Get the staff element , it may not working if tag has spaces, or
			// whatever weird characters in front...it's better to use
			// getElementsByTagName() to get it directly.
			// Node staff = company.getFirstChild();
			
			Node level = doc.getElementsByTagName("level").item(levelNumber-1);
			
			// loop the level child node
			NodeList list = level.getChildNodes();
			int nodeNumber = 0;
			System.out.println(list.getLength());
			for (int i = 0; i < list.getLength(); i++) {
	 
	           Node element = list.item(i);
	           //element.
	           System.out.println(element.toString());
	           //System.out.println(element.getLocalName());
	           //System.out.println("" + element.getNodeName());
	           nodeNumber = nodeNumber + 1;
	           
	           NamedNodeMap eAttribute = element.getAttributes();
	           Node elementAttr = eAttribute.getNamedItem("no");
	           if (elementAttr.getTextContent() == "df") {
	        	   level.removeChild(element);
	           } else {
	        	   nodeNumber = nodeNumber + 1;
	           }
			}
			System.out.println(nodeNumber);
			
			// update number of nodes in level
			NamedNodeMap attribute = level.getAttributes();
			//Node nodeAttr = attribute.getNamedItem("nodes");
			//int nodeNumber = Integer.parseInt(nodeAttr.getTextContent()) + 1;
			//nodeAttr.setTextContent("" + nodeNumber + 1);
			
			// append a new node with keyword
			Element node = doc.createElement("node");
			node.setAttribute("no", "n" + (nodeNumber + 1));
			node.setAttribute("snos", "" + 1);
			Element synonym = doc.createElement("synonym");
			synonym.setAttribute("no", "" + 1);
			synonym.appendChild(doc.createTextNode(keyword));
			node.appendChild(synonym);
			level.appendChild(node);
			
			// append a new default node
			Element dfNode = doc.createElement("node");
			dfNode.setAttribute("no", "df");
			dfNode.setAttribute("snos", "" + 0);
			dfNode.appendChild(doc.createTextNode(""));
			//dfNode.appendChild(synonym);	
			level.appendChild(dfNode);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Done");
			
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }*/
		}
	
	public static String update(Document doc, String path, String def, int levelNumber){
		  String p[] = path.split("/");
		  String previousNodeNum= "";
		  int newNumber = 0;
		  //search nodes or create them if they do not exist
		  Node n = doc;
		  for (int i = 0; i < p.length; i++){
		    NodeList kids = n.getChildNodes();
		    Node nfound = null;
		    
		    for (int j = 0; j < kids.getLength(); j++) {
		      NamedNodeMap eAttribute = kids.item(j).getAttributes();
		      if (kids.item(j).getNodeName().equals(p[i])) {
		    	  //System.out.println(p[i]);
		    	  if (p[i].equalsIgnoreCase("levelnode")){
		    		  nfound = kids.item(j);
		    	  		//System.out.println("levelnode");
		    	  		//System.out.println("brake");
		    	  		break;
		    	  }
		    	  if (p[i].equalsIgnoreCase("level")){
		    		  Node elementAttr = eAttribute.getNamedItem("no");
		  		      String number = elementAttr.getTextContent();
		  		      //System.out.println("Number: " + number);
		  		      Pattern pat = Pattern.compile("\\d+");
		  		      Matcher m = pat.matcher(number); 
		  		      newNumber = 0;
		  		      while (m.find()){
		  		    	  newNumber = Integer.parseInt(m.group());}
		    		  if (levelNumber == newNumber){
		    			  nfound = kids.item(j);
		    			  //System.out.println("level");
		    			  //System.out.println("brake");
				    	  break;
		    		  }
		    	  }
		    	  if (p[i].equalsIgnoreCase("node")) {
		    		  Node elementAttr = eAttribute.getNamedItem("no");
		  		      String number = elementAttr.getTextContent();
		    		  //System.out.println("PN: " + previousNodeNum);
		  		      if (number.equalsIgnoreCase("df")){
		    			  nfound = kids.item(j);
		    			  //System.out.println("brake");
		    			  Pattern pat = Pattern.compile("\\d+");
			  		      Matcher m = pat.matcher(previousNodeNum); 
			  		      newNumber = 0;
			  		      while (m.find())
			  		    	  newNumber = Integer.parseInt(m.group());
			  		    	  //System.out.println("NUMBER: " + m.group());}
			  		      elementAttr.setTextContent("n" + (newNumber+1));
			  		      Node synAttr = eAttribute.getNamedItem("snos");
			  		      synAttr.setTextContent("1");
			    		  break;
		    		  }
		  		    previousNodeNum = number;
	    	  	  }
		    	  
		      }
		    }
		    if (nfound == null) { 
		      //System.out.println("nullfound");
		      n.appendChild(doc.createTextNode("\t"));
		      nfound = doc.createElement(p[i]);
		      NamedNodeMap synAttr = nfound.getAttributes();
		      
		      n.appendChild(nfound);
		      n.appendChild(doc.createTextNode("\n  ")); //add whitespace, so the result looks nicer. Not really needed
		      Node syn = n.getFirstChild();
		      //syn.
		    }
		    n = nfound;
		  
		  }
		  NodeList kids = n.getChildNodes();
		  for (int i=0;i<kids.getLength();i++){
		    if (kids.item(i).getNodeType() == Node.TEXT_NODE) {
		      //text node exists
		      kids.item(i).setNodeValue(def); //override
		      
		      return "l" + levelNumber + "n" + (newNumber+1);
		    }
		  }
		  //n.setAttribute("no", "" + 1);
		  n.appendChild(doc.createTextNode(def));  
		  //Node syn = n.getFirstChild();
		  Node previous = n.getParentNode();
		  previous = previous.getParentNode();
		  Element dfNode = doc.createElement("node");
		  dfNode.setAttribute("no", "df");
		  dfNode.setAttribute("snos", "" + 0);
		  dfNode.appendChild(doc.createTextNode("\n  "));
		  previous.appendChild(doc.createTextNode("  "));
		  previous.appendChild(dfNode);
		  previous.appendChild(doc.createTextNode("\n"));
		  //System.out.println("APPEND");
		  return "l" + levelNumber + "n" + (newNumber+1);
		}
	}
