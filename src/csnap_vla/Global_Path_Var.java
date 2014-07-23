package csnap_vla;

public class Global_Path_Var {
	
	//public static String project_dir = "./vla_2014/"; //Change Me!
	public static String project_dir = System.getProperty("user.dir") + "/";

	public static String db_path =  project_dir + "db.txt";
	public static String img_path = project_dir + "TVLA.jpg";
	public static String node_path = project_dir + "NodeConfigFile.xml";
	public static String rules_path = project_dir + "RuleBaseFile.xml";
	public static String class_info_path = project_dir + "class_info.txt";
	
}
