package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;


public class ImageInsertionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Connection sqlConnection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			System.out.println("connecting to: "+ Config.getUrl());
			sqlConnection = DriverManager.getConnection(Config.getUrl(), Config.USER, Config.PASS);
			System.out.println("connection successful");
			
			File imageFile = new File("image/allblack.bmp");
			String fileName = imageFile.getName();
			
			FileInputStream in =new FileInputStream(imageFile);
			
				preparedStatement =sqlConnection.prepareStatement("insert into MyPictures(name, photo) values (?, ?)");
				preparedStatement.setString(1, "blob");
				preparedStatement.setBlob(2, in, (int)fileName.length());
				preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("file was not found.");
		} finally {
			if(sqlConnection != null) try { sqlConnection.close(); } catch (SQLException e) {}		
			System.out.println("sql connection closed...");
			if(preparedStatement != null) try { preparedStatement.close(); } catch (SQLException e) {}
			System.out.println("prepared statement closed...");

		}
	}
	
}
