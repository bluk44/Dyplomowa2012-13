package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionTest {
	private static String HOST = "localhost";
	private static String PORT = "3306";
	private static String DB_NAME = "board_images";
	
	private static String USER = "imgmanager";
	private static String PASS = "idefix";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Connection sqlConnection = null;
		PreparedStatement preparedStatement = null;
		try {
			String connect = "jdbc:mysql://"+HOST+":"+PORT+"/"+DB_NAME;
			System.out.println("connecting to: "+ connect);
			sqlConnection = DriverManager.getConnection(connect, USER, PASS);
			System.out.println("connection successful");
			
			addUser(sqlConnection, preparedStatement, "gruby");
			addUser(sqlConnection, preparedStatement, "przecinek");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Closing sql connection...");
			if(sqlConnection != null) try { sqlConnection.close(); } catch (SQLException e) {}		
			System.out.println("sql connection closed");
			if(preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) {}		

		}
	}
	
	public static void addUser(Connection connection, PreparedStatement preparedStatement, String name) throws SQLException{
		preparedStatement = connection.prepareStatement("INSERT INTO users (name) VALUES(?)");
		preparedStatement.setString(1, name);
		preparedStatement.executeUpdate();
	}
}
