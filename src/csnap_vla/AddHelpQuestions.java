package csnap_vla;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddHelpQuestions {
	public static void write(String path) throws FileNotFoundException{
		ModifyXMLFile modifyNode = new ModifyXMLFile();
		ModifyRuleXMLFile modifyRule = new ModifyRuleXMLFile();
		FindNodesInstructor nodeFind = new FindNodesInstructor();
		FindKeywordsInstructor keywordFind = new FindKeywordsInstructor();
		
		ArrayList<ArrayList<String>> newRules = read(path);
		ArrayList<String> questions = newRules.get(0);
		ArrayList<String> keywords = newRules.get(1);
		ArrayList<String> levelNumbers = newRules.get(2);
		ArrayList<String> answers = newRules.get(3);
		ArrayList<String> youtube = newRules.get(4);
		ArrayList<String> pdf = newRules.get(5);
		
		for (int i=0; i < questions.size(); i++) {
			ArrayList<String> newNode = new ArrayList<String>();
			String[][] findNodes = nodeFind.getNodes(questions.get(i));
			
			String[] splitKW = keywords.get(i).split("\\s+");
			String[] splitLN = levelNumbers.get(i).split("\\s+");
			splitKW = findMissingKeywords(keywordFind.getNodes(questions.get(i)), splitKW);
			for (int j = 0; j < splitKW.length; j++) {
				if (splitKW != null) {
					try {
						   newNode.add(modifyNode.write(splitKW[j], Integer.parseInt(splitLN[j])));
						} catch (Exception e) {
					   		// TODO Auto-generated catch block
					   		e.printStackTrace();
					   	}
				}	
			}
			for(int j=0; j < newNode.size(); j++){
				int[] nodeLocation = new int[2];
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(newNode.get(j)); 
				int newCount = 0;
				while (m.find()) {
					nodeLocation[newCount] = Integer.parseInt(m.group());
					newCount = newCount + 1;
				} 
				for (int k = 0; k < findNodes[nodeLocation[0]-1].length; k++) {
					if (findNodes[nodeLocation[0]-1][k]!= null && findNodes[nodeLocation[0]-1][k].equalsIgnoreCase("l" + (nodeLocation[0]) + "df"))
						findNodes[nodeLocation[0]-1][k] = null;
				}
				findNodes[nodeLocation[0]-1][nodeLocation[1]-1] = newNode.get(j);
			}
			try {
				modifyRule.write(findNodes, pdf.get(i), youtube.get(i), answers.get(i));
				//writeRules.write(findNodes, pdf.get(i), youtube.get(i), answers.get(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	public static String[] findMissingKeywords(String[][] keywordString, String[] questionString) {
		String[] missingKeys = questionString;
		ArrayList<String> foundKeywords = new ArrayList<String>();
		for (int row = 0; row < keywordString.length; row++) {
			for (int column = 0; column < keywordString[row].length; column++) {
	            if(keywordString[row][column] != null){	
	            	foundKeywords.add(keywordString[row][column]);
	            	System.out.print(keywordString[row][column] + " ");
	            }
	        }
		}
		for (int i = 0; i < questionString.length; i++) {
			if (foundKeywords.contains(questionString[i])){
				missingKeys[i] = null;
			}
		}
	   
	   return missingKeys;
	}
	
	public static ArrayList<ArrayList<String>> read(String fileLocation) throws FileNotFoundException{
		ArrayList<String> newQuestions = new ArrayList<String>();  
		ArrayList<String> newKeywords = new ArrayList<String>();
		ArrayList<String> newLevelNum = new ArrayList<String>();
		ArrayList<String> newAnswers = new ArrayList<String>();  
		ArrayList<String> newYouTube = new ArrayList<String>();
		ArrayList<String> newPDF = new ArrayList<String>();
		ArrayList<ArrayList<String>> finalQuestions = new ArrayList<ArrayList<String>>();
		Scanner scan = new Scanner(new File(fileLocation));
		String line = "", answer = "", answerType = "";
		while (scan.hasNextLine())
		{
			line = scan.nextLine();
			if(line.length() > 1) {
				answerType = line.substring(0, 3);
				answer  = line.substring(3, line.length());
				if(answerType.equals("Q: ")){
					newQuestions.add(answer);
					//System.out.println(answerType + answer);
				} else if (answerType.equals("K: ")){
					newKeywords.add(answer);
					//System.out.println(answerType + answer);
				} else if (answerType.equals("L: ")){
					newLevelNum.add(answer);
					//System.out.println(answerType + answer);
				} else if (answerType.equals("A: ")){
					newAnswers.add(answer);
					//System.out.println(answerType + answer);
				} else if (answerType.equals("Y: ")){
					newYouTube.add(answer);
					//System.out.println(answerType + answer);
				} else if (answerType.equals("P: ")){
					newPDF.add(answer);
					//System.out.println(answerType + answer);
				} 
			}
		} 
		finalQuestions.add(newQuestions);
		finalQuestions.add(newKeywords);
		finalQuestions.add(newLevelNum);
		finalQuestions.add(newAnswers);
		finalQuestions.add(newYouTube);
		finalQuestions.add(newPDF);
		System.out.println("Complete.");
		scan.close();
		return finalQuestions;
	}
}
