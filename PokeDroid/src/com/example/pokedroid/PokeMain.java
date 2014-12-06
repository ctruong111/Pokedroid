package com.example.pokedroid;

import java.sql.*;
import java.util.*;

public class PokeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection = DriverManager.getConnection("jdbc:sqlite:Pokemon");
			Statement stat = connection.createStatement();
			Scanner in = new Scanner(System.in);
			int i = 1;
			while(i != 0){
				System.out.println("Enter in a number from 1-16 to execute queries. 0 to exit.");
				i = in.nextInt();
				switch (i) {
				case 1:  
					PokeQuery.query1(stat);
					break;
				case 2:  
					PokeQuery.query2(stat);
					break;
				case 3:  
					PokeQuery.query3(stat);
					break;
				case 4:  
					PokeQuery.query4(stat);
					break;
				case 5:  
					PokeQuery.query5(stat);
					break;
				case 6:  
					PokeQuery.query6(stat);
					break;
				case 7:  
					PokeQuery.query7(stat);
					break;
				case 8:  
					PokeQuery.query8(stat);
					break;
				case 9:  
					PokeQuery.query9(stat);
					break;
				case 10: 
					PokeQuery.query10(stat);
					break;
				case 11: 
					PokeQuery.query11(stat);
					break;
				case 12: 
					PokeQuery.query12(stat);
					break;
				case 13: 
					PokeQuery.query13(stat);
					break;
				case 14: 
					PokeQuery.query14(stat);
					break;
				case 15: 
					PokeQuery.query15(stat);
					break;
				case 16: 
					PokeQuery.query16(stat);
					break;
				default: 
					break;
				}
				if (i == 0)
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
