package csnap_vla;

import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class LoginHandler {
	
	private final static Charset ENCODING = StandardCharsets.UTF_8;  
	String username, password;
	String databasePath = Global_Path_Var.db_path;
	
	//constructor
	public LoginHandler(){
		username = null;
		password = null;
	}
	
	//set login credentials
	public void loginCredentials(String username, String password){
		this.username = username;
		this. password = password;
	}
	
	//checks if login credentials are in the database
	public boolean loginPassed() throws IOException{
	    
		return processLineByLine();
		
	}	
	
	private boolean processLineByLine() throws IOException {
		Path path = Paths.get(databasePath);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        if(processLine(scanner.nextLine())) return true; //return true if username/password found
	      }      
	    }
	    return false; //return false if a login does not match the password or nothing was found
	}
	
	private boolean processLine(String aLine){
	    //use a second Scanner to parse the content of each line 
	    Scanner scanner = new Scanner(aLine);
	    scanner.useDelimiter(" ");
	    if (scanner.hasNext()){
	      //assumes the line has a certain structure
	      String name = scanner.next();
	      String pwd = scanner.next();
	      if(name.equals(username) && pwd.equals(password)){
	    	  scanner.close();
	    	  return true;
	      }
	      else {
	    	  scanner.close();
	    	  return false;
	      }
	    }
	    scanner.close();
	    return false;
	  }
	
	public void addEntry(String name, String pwd){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(databasePath, true)))) {
		    out.println(name + " " + pwd + " s "); //adds new user to database with a default type student (s)
		}catch (IOException e) {
		    //exception handling 
		}
	}
	
	
}
