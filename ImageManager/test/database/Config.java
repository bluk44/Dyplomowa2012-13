package database;

public class Config {

	public static String HOST = "localhost";
	public static String PORT = "3306";
	public static String DB_NAME = "image_test";
	
	public static String USER = "imgmanager";
	public static String PASS = "idefix"; 
	
	public static String getUrl(){
		return "jdbc:mysql://"+Config.HOST+":"+Config.PORT+"/"+Config.DB_NAME;
	}
}
