package com.example.pokedroid;

import java.sql.*;
public class PokeQuery {
	public static void query1(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("SELECT * FROM Pokemon JOIN Stats ON p_id = s_id"
												+ "	JOIN TP ON p_id = pokemon"
												+" JOIN Type ON type = t_id"
												+" WHERE p_name = 'charizard'");
			while (rs.next()){
				System.out.println("p_id = " + rs.getString("p_id"));
				System.out.println("p_name = " + rs.getString("p_name"));
				System.out.println("height = " + rs.getString("height"));
				System.out.println("weight = " + rs.getString("weight"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query2(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("SELECT * FROM Pokemon"
												+" ORDER BY p_name DESC");
			while (rs.next()){
				System.out.println("p_id = " + rs.getString("p_id"));
				System.out.println("p_name = " + rs.getString("p_name"));
				System.out.println("height = " + rs.getString("height"));
				System.out.println("weight = " + rs.getString("weight"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query3(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("");
			while (rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query4(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("SELECT * FROM Locations "
+"JOIN Region ON l_regid = r_id");
			while (rs.next()){
				System.out.println("LocationId = " + rs.getString("l_locId"));
				System.out.println("RegionId = " + rs.getString("l_regId"));
				System.out.println("LocationName = " + rs.getString("l_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query5(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("");
			while (rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query6(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("SELECT defence+attack+hp+special_attack+special_defence+speed AS total"
											+" FROM Stats WHERE s_id = 1");
			while (rs.next()){
				System.out.println("Total stats for Bulbasaur = " + rs.getString("total"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query7(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("");
			while (rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query8(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("");
			while (rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query9(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("");
			while (rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query10(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("INSERT INTO Locations"
	+ " VALUES (4, 4, “Sunyshore City”)");
			while (rs.next()){
				System.out.println("Success");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query11(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("");
			while (rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query12(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("");
			while (rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query13(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("");
			while (rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query14(Statement stat){
		System.out.println("Executing first query.");
		try {
			ResultSet rs = stat.executeQuery("");
			while (rs.next()){
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query15(Statement stat){
		System.out.println("Executing first query.");
		try {
			stat.executeQuery("UPDATE Moves"
					+" SET power = 100"
					+" WHERE m_name = 'tackle'");	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void query16(Statement stat){
		System.out.println("Executing first query.");
		try {
			stat.executeQuery("UPDATE Evolution"
							+" SET evolve_level = 16"
							+" WHERE e_id = (SELECT p_id FROM Pokemon WHERE p_name = 'bulbasaur')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
