package com.example.pokedroid;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static String DB_PATH = "/data/data/com.example.pokedroid/databases/";
	public static String DB_NAME = "Pokemon";
	public static final int DB_VERSION = 1;
	
	private SQLiteDatabase myDB;
	private Context context;
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	@Override
	public SQLiteDatabase getWritableDatabase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        
        return myDB;
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public synchronized void close(){
	    if(myDB!=null){
	        myDB.close();
	    }
	    super.close();
	}
	
	/*
	 * Check if the database is exist on device or not
	 */
	private boolean checkDataBase() {
	    SQLiteDatabase tempDB = null;
	    try {
	        String myPath = DB_PATH + DB_NAME;
	        tempDB = SQLiteDatabase.openDatabase(myPath, null,
	                SQLiteDatabase.OPEN_READWRITE);
	    } catch (SQLiteException e) {
	        Log.e("tle99 - check", e.getMessage());
	    }
	    if (tempDB != null)
	        tempDB.close();
	    return tempDB != null ? true : false;
	}
	
	/*
	 * Copy database from source code assets to device
	 * @throws IOException
	 */
	public void copyDataBase() throws IOException{
	    try {
	        InputStream myInput = context.getAssets().open(DB_NAME);
	        String outputFileName = DB_PATH + DB_NAME;
	        OutputStream myOutput = new FileOutputStream(outputFileName);

	        byte[] buffer = new byte[1024];
	        int length;

	        while((length = myInput.read(buffer))>0){
	            myOutput.write(buffer, 0, length);
	        }

	        myOutput.flush();
	        myOutput.close();
	        myInput.close();
	    } catch (Exception e) {
	        Log.e("tle99 - copyDatabase", e.getMessage());
	    }
	}
	
	public void openDataBase() throws SQLException{
	    String myPath = DB_PATH + DB_NAME;
	    myDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	/***
	 * Check if the database doesn't exist on device, create new one
	 * @throws IOException
	 */
	public void createDataBase() throws IOException {
	    boolean dbExist = checkDataBase();        

	    if (dbExist) {

	    } else {
	        this.getReadableDatabase();
	        try {
	            copyDataBase();
	        } catch (IOException e) {
	            Log.e("tle99 - create", e.getMessage());
	        }
	    }
	}
	
	public List<String> getAllPokemonNames() {
		List<String> pokemonNames = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		String name;

		try {
			cursor = db.rawQuery("SELECT * FROM Pokemon", null);
			if (cursor == null) {
				return null;
			} 
			
			cursor.moveToFirst();
			
			do {
	            name = cursor.getString(1);            
	            pokemonNames.add(name);
	        } while (cursor.moveToNext()); 
			
	        cursor.close();
			
		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
		
		db.close();
		return pokemonNames;
	}
	
	public Pokemon getPokemon(String name) {
		Pokemon pokemon = new Pokemon(name);
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;

		try {
			cursor = db.rawQuery("SELECT * FROM Pokemon JOIN Stats ON p_id = s_id WHERE p_name = " + name, null);
			
			if (cursor == null) {
				return null;
			} cursor.moveToFirst();
			
			pokemon.setId(cursor.getInt(0));
			pokemon.setHeight(cursor.getDouble(2)/10);
			pokemon.setWeight(cursor.getDouble(3)/10);
			pokemon.setBase_exp(cursor.getInt(4));
			pokemon.setDefence(cursor.getInt(6));
			pokemon.setAttack(cursor.getInt(7));
			pokemon.setHp(cursor.getInt(8));
			pokemon.setSpecial_attack(cursor.getInt(9));
			pokemon.setSpecial_defence(cursor.getInt(10));
			pokemon.setSpeed(cursor.getInt(11));
			
			cursor.close();
			
		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
		db.close();
		return pokemon;
	}
}
