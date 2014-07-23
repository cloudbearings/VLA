package csnap_vla;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ModifyRuleXMLFile {
	
	public void write(final String[][] keywords, String pdfName, String videoName, String resultName) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		Document doc = domFactory.newDocumentBuilder().parse("/Users/salvatoregiorgi/Desktop/RuleBaseFile.xml");

		update(doc, "rulebase/rule", keywords, pdfName, videoName, resultName);
		
		// write the content into xml file
		String filepath = "/Users/salvatoregiorgi/Desktop/RuleBaseFile.xml";
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
	}
	
	public static void update(Document doc, String path, String[][] keywords, String pdfName, String videoName, String resultName){
		Node n = doc;
		Node ruleBase = n.getChildNodes().item(0);
		String numOfRules = ruleBase.getAttributes().getNamedItem("rules").getTextContent();
		ruleBase.getAttributes().getNamedItem("rules").setTextContent("" + (Integer.parseInt(numOfRules)+1));
		Element newRule = doc.createElement("rule");
		newRule.setAttribute("no", "" + (Integer.parseInt(numOfRules)+1));
		for (int i = 0; i < 11; i++) {
			Element newCondition = doc.createElement("condition");
			newCondition.setAttribute("no", "" + (i+1));
			newCondition.appendChild(doc.createTextNode("\n\t\t"));
			for (int j = 0; j < 12; j++) {
				int count = 0;
				if(keywords[i][j] != null) {
					count=count+1;
					Element newOption = doc.createElement("option");
					newOption.setAttribute("no", "" + count);
					newOption.setTextContent(keywords[i][j]);
					newCondition.appendChild(doc.createTextNode("\t"));
					newCondition.appendChild(newOption);
					newCondition.appendChild(doc.createTextNode("\n\t\t"));
					  
				} 
				if(count > 1)
					newCondition.appendChild(doc.createTextNode("\n\t\t"));
			}
			newRule.appendChild(doc.createTextNode("\n\t\t")); 
			newRule.appendChild(newCondition); 
		}
		  
		Element newPDF = doc.createElement("pdf");
		newPDF.setTextContent(pdfName);
		newRule.appendChild(doc.createTextNode("\n\t\t"));
		newRule.appendChild(newPDF);
		Element newVideo = doc.createElement("video");
		newVideo.setTextContent(videoName);
		newRule.appendChild(doc.createTextNode("\n\t\t"));
		newRule.appendChild(newVideo);
		Element newResult = doc.createElement("result");
		newResult.setTextContent(resultName);
		newRule.appendChild(doc.createTextNode("\n\t\t"));
		newRule.appendChild(newResult);
		newRule.appendChild(doc.createTextNode("\n\t"));
		  
		ruleBase.appendChild(doc.createTextNode("\t"));
		ruleBase.appendChild(newRule);
		ruleBase.appendChild(doc.createTextNode("\n"));
	}
}
