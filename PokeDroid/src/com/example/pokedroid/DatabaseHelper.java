package com.example.pokedroid;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
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

	public List<String> getAllTypeNames() {
		List<String> typeNames = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		String name;

		try {
			cursor = db.rawQuery("SELECT * FROM Type", null);
			if (cursor == null) {
				return null;
			} 

			cursor.moveToFirst();

			do {
				name = cursor.getString(1);            
				typeNames.add(name);
			} while (cursor.moveToNext()); 

			cursor.close();

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}

		db.close();
		return typeNames;
	}

	public List<String> getAllPokemonNamesAndId() {
		List<String> pokemonNames = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		String temp;
		String name;
		int id;

		try {
			cursor = db.rawQuery("SELECT * FROM Pokemon", null);
			if (cursor == null) {
				return null;
			} 

			cursor.moveToFirst();

			do {
				id = cursor.getInt(0);
				name = cursor.getString(1);

				//Capitalizes first letter
				String output = name.substring(0, 1).toUpperCase() + name.substring(1);
				name = output;

				//Adds in pokemon number with leading zeroes before name
				temp = String.format("%03d", id);
				temp = temp + "\t\t" + name;

				pokemonNames.add(temp);
			} while (cursor.moveToNext()); 

			cursor.close();

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}

		db.close();
		return pokemonNames;
	}

	public List<String> getAllPokemonNames() {
		List<String> pokemonNames = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		String temp;
		String name;
		int id;

		try {
			cursor = db.rawQuery("SELECT * FROM Pokemon", null);
			if (cursor == null) {
				return null;
			} 

			cursor.moveToFirst();

			do {
				id = cursor.getInt(0);
				name = cursor.getString(1);

				//Capitalizes first letter
				String output = name.substring(0, 1).toUpperCase() + name.substring(1);
				name = output;

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
			cursor = db.rawQuery("SELECT * FROM Pokemon "
					+ "JOIN Stats ON p_id = s_id "
					+ "JOIN TP ON pokemon = p_id "
					+ "JOIN Type ON type = t_id "
					+ "WHERE p_name = '" + name + "' COLLATE NOCASE", null);

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
			pokemon.setType1(cursor.getString(15));

			if (cursor.getString(16) == null) {
				pokemon.setType2(cursor.getString(16));
			} else {
				pokemon.setType2("-");
			}

			cursor.close();

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
		db.close();
		return pokemon;
	}

	public List<String> getAllMoveNames() {
		List<String> moveNames = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		String name;

		try {
			cursor = db.rawQuery("SELECT * FROM Moves", null);
			if (cursor == null) {
				return null;
			} 

			cursor.moveToFirst();

			do {
				name = cursor.getString(1);            
				moveNames.add(name);
			} while (cursor.moveToNext()); 

			cursor.close();

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}

		db.close();
		return moveNames;
	}
	public Move getMove(String name) {
		Move move = new Move();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;

		try {
			cursor = db.rawQuery("SELECT * FROM Moves JOIN Typen ON t_id = type WHERE m_name = '" + name + "' COLLATE NOCASE", null);

			if (cursor == null) {
				return null;
			} cursor.moveToFirst();

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}

		db.close();
		return move;
	}

	private byte[] getImage(String url){ //helper method
		try {
			URL imageUrl = new URL(url);
			URLConnection ucon = imageUrl.openConnection();

			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);

			ByteArrayBuffer baf = new ByteArrayBuffer(500);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}

			return baf.toByteArray();
		} catch (Exception e) {
			Log.d("ImageManager", "Error: " + e.toString());
		}
		return null;
	}

	public void insertImage(byte[] img, String name){ //helper method
		SQLiteDatabase db               =   getWritableDatabase();
		ContentValues newValues = new ContentValues();
		newValues.put("image", img);
		db.update("Pokemon", newValues, "p_name = " + name, null);
		db.close();
	}

	public List<byte[]> getImageList() {
	    SQLiteDatabase db       =   getWritableDatabase();
	    Cursor cursor;
	    List<byte[]> list = new ArrayList<byte[]>();

	    try {
			cursor = db.rawQuery("SELECT image FROM Pokemon", null);
			if (cursor == null) {
				return null;
			} 

			cursor.moveToFirst();

			do {
				byte[] temp = cursor.getBlob(0);            
				list.add(temp);
			} while (cursor.moveToNext()); 

			cursor.close();

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
	    return list;
	}
	
	public void insertImages(){ //insert all images *hopefully*
		List<String> names = getAllPokemonNames();
		for(int i = 0; i < names.size(); i++){
			byte[] entry = getImage("http://img.pokemondb.net/artwork/"+ names.get(i) +".jpg");
			insertImage(entry, names.get(i));
		}
	}
}
