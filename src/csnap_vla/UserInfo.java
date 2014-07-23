package csnap_vla;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class UserInfo {

	private String username, access_type;
	private String[] labs_completed, labs_in_progress;
	String databasePath = Global_Path_Var.db_path;
	private final static Charset ENCODING = StandardCharsets.UTF_8;  

	
	public UserInfo(String username){
		String temp;

		this.username = username;

		//get user's access type
		Path path = Paths.get(databasePath);
		try (Scanner scanner =  new Scanner(path, ENCODING.name())){
			while (scanner.hasNextLine()){
				temp = scanner.nextLine();
				if (temp.startsWith(username + " ")){
					if(temp.contains(" s")){
						this.access_type = "s";
					} else this.access_type = "a"; 
				}
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public String getType(){
		return access_type;
	}
	
	public void setType(String userType){
		access_type = userType;
	}
	
	public String getName(){
		return username;
	}
	
}
