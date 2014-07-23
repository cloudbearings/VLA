package csnap_vla;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.graphics.text.PageText;

public class Quiz {
	
	private String directoryPath = Global_Path_Var.project_dir;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  

	private String answers_file_text;
	private String answers = "";
	private int numQuestions;
	private int numCorrect;
	
	public Quiz(String course_number, String lab_name, String quiz_type){
		Path path = Paths.get(directoryPath + course_number + ".txt");
		
		String answers_PDF;
		//get Pretest Answers PDF Path
		String temp;
		   try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			   answers_PDF = null;
		     while (scanner.hasNextLine()){
		    	 
		       temp = scanner.nextLine();
		       if(temp.contains(lab_name)){
		    	   if(quiz_type.equals("Pretest")){
		    		   while(!temp.contains("Pretest Answers Path: ")){
			    		   temp=scanner.nextLine();
			    	   }
			    	   answers_PDF = temp.split("Pretest Answers Path: ")[1];
			    	   break;
		    	   } else { //prelab type
		    		   while(!temp.contains("Prelab Answers Path: ")){
			    		   temp=scanner.nextLine();
			    	   }
			    	   answers_PDF = temp.split("Prelab Answers Path: ")[1];
			    	   break;
		    	   }	       
		    	   
		       }
		     }  
		     scanner.close();
		   } catch (IOException er) {
			answers_PDF = null;
			er.printStackTrace();
		}
		
		//get answers from answers PDF into a string
		try {   
			   // load the file
			   //Path pdf_path = Paths.get(answers_PDF);
			   Document document = new Document();
			   document.setFile(answers_PDF);
			   
			   // create an output file
			   PageText pageText = document.getPageText(0);
			   answers_file_text = pageText.toString();
			   
			} catch (Throwable e) {
			   e.printStackTrace();
			   answers_file_text = null;
			}
		
		//put actual answers into an answers String for comparison (e.g. "abbd" or "cadb")
		String temp_char;
		for(int i = 0; i < answers_file_text.length(); i++){
			
			temp_char = Character.toString(answers_file_text.charAt(i));
			if(temp_char.equals(":")) answers += Character.toString(answers_file_text.charAt(i+1));	
		}
		
		answers = answers.toLowerCase();
		
	}
	
	
	
	//returns the question number/answers
	public String get_answers(){
		return answers_file_text;
	}

	//returns the number of questions being asked
	public int get_number_questions(){
		//count how many questions by counting the number of ":" seperators
		//since the required format for answers is 1:a, 2:d, etc...
		numQuestions = answers_file_text.length() - answers_file_text.replace(":","").length();
		return numQuestions;
	}
	
	//checks the student's answers against the answers on file and returns the number of correct
	public int check_answers(String student_answers){
		numCorrect = 0;
		student_answers = student_answers.toLowerCase();
				
		for(int i = 0; i < answers.length(); i++){		
			if(Character.toString(answers.charAt(i)).equals(Character.toString(student_answers.charAt(i)))) numCorrect++;			
		}
		
		return numCorrect;
	}
}
