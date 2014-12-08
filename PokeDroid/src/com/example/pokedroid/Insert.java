package com.example.pokedroid;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.Properties;
import java.util.Scanner;
import java.sql.*;

public class Insert {
	static Connection connection = null;
	static PreparedStatement statement = null;
	static Statement something = null;
	static File file = new File("C:\\bulbasaur.jpg");
	static int s = 0;
	static byte[] image = null;
	static byte[] buffer = new byte[1024];
	static Blob image1;
	
	public static void main(String[] args) throws SQLException,
			FileNotFoundException {
		//connection = DriverManager.getConnection("jdbc:sqlite:Pokemon");
		/*
		FileInputStream input = new FileInputStream(file);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			for (int readNum; (readNum = input.read(buffer)) != -1;) {
				output.write(buffer, 0, readNum);
				System.out.println("read " + readNum + " bytes,");
			}
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		image = output.toByteArray();
		*/
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Pokemon");
			
			something = connection.createStatement();
			ResultSet rs1 = something.executeQuery("SELECT p_name FROM Pokemon;");
			while(rs1.next()){
				String name = rs1.getString("p_name");
				byte[] blob = getImage("http://img.pokemondb.net/artwork/"+name+".jpg");
				statement = connection.prepareStatement("UPDATE Pokemon SET image = ? WHERE p_name = '" + name + "';");
				statement.setBytes(1, image);
				s = statement.executeUpdate();
				if (s > 0) {
					System.out.println("Image Uploaded");
				}
				
			}
			something = connection.createStatement();
			
			ResultSet rs = something.executeQuery( "SELECT image FROM Pokemon;" );
			while(rs.next()){
				image1 = rs.getBlob("image");
				
				System.out.println(image1);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static byte[] getImage(String url){
	     try {
	             URL imageUrl = new URL(url);
	             URLConnection ucon = imageUrl.openConnection();

	             InputStream is = ucon.getInputStream();
	             BufferedInputStream bis = new BufferedInputStream(is);
	             ByteArrayOutputStream output = new ByteArrayOutputStream();
	             try {
	     			for (int readNum; (readNum = bis.read(buffer)) != -1;) {
	     				output.write(buffer, 0, readNum);
	     				// no doubt here is 0
	     				/*
	     				 * Writes len bytes from the specified byte array starting at
	     				 * offset off to this byte array output stream.
	     				 */
	     				System.out.println("read " + readNum + " bytes,");
	     			}
	     		} catch (IOException ex) {
	     			System.err.println(ex.getMessage());
	     		}
	     		image = output.toByteArray();
	     } catch (Exception e) {
	    	 System.out.println(e);
	     }
	     return null;
	}
}