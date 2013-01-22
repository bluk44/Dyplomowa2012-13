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
			
			Blob imageBlob = sqlConnection.createBlob();
			OutputStream output = imageBlob.setBinaryStream(1);
			
			File imageFile = new File("image/street_RGB.bmp");
			String fileName = imageFile.getName();
			
			boolean writeBlob = readImage(imageFile, output);
			
			// dlaczego 0 ??
			System.out.println("blob length: "+imageBlob.length());
			if(writeBlob){
				preparedStatement =sqlConnection.prepareStatement("INSERT INTO images VAULES(?,?,?)");
				preparedStatement.setNull(1, Types.NULL);
				preparedStatement.setBlob(2, imageBlob);
				preparedStatement.setNull(3, Types.NULL);
				preparedStatement.executeUpdate();
			}
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
	
	static boolean readImage(File imageFile, OutputStream out){
		FileInputStream in = null;
			try {
				in = new FileInputStream(imageFile);
				int b = -1;
				while((b = in.read())!= -1){
					out.write(b);
				}
				
				return true;
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally{
				try {in.close();} catch (IOException e) {e.printStackTrace();}
			}
	}

}
