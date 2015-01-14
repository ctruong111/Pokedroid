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
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
	public static String DB_PATH = "/data/data/com.example.pokedroid/databases/";
	public static String DB_NAME = "Pokemon";
	public static final int DB_VERSION = 1;
	
	private SQLiteDatabase myDB;
	private Context context;
	Pokemon pokemon;
	Move move;
	
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
                cursor.close();
                db.close();
				return null;
			} 
			
			cursor.moveToFirst();
			
			do {
                String temp = cursor.getString(1);
                temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
                typeNames.add(temp);
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
			cursor = db.rawQuery("SELECT p_id, p_name FROM Pokemon", null);
			
			if (cursor == null) {
                cursor.close();
                db.close();
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
                cursor.close();
                db.close();
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
		pokemon = new Pokemon(name);
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;

		try {
			cursor = db.rawQuery("SELECT * FROM Pokemon "
					+ "JOIN Stats ON p_id = s_id "
					+ "JOIN TP ON p_id = tp_id "
					+ "JOIN Type t1 ON t1.t_id = type1 "
					+ "JOIN Type t2 ON t2.t_id = type2 "
					+ "WHERE p_name = '" + name + "' COLLATE NOCASE", null);
			
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			} cursor.moveToFirst();
			
			pokemon.setId(cursor.getInt(0));
			pokemon.setHeight(cursor.getInt(2));
			pokemon.setWeight(cursor.getInt(3));
			pokemon.setBase_exp(cursor.getInt(4));
			pokemon.setImage(cursor.getBlob(5));
			pokemon.setDefence(cursor.getInt(7));
			pokemon.setAttack(cursor.getInt(8));
			pokemon.setHp(cursor.getInt(9));
			pokemon.setSpecial_attack(cursor.getInt(10));
			pokemon.setSpecial_defence(cursor.getInt(11));
			pokemon.setSpeed(cursor.getInt(12));
			pokemon.setType1(cursor.getString(17));
			pokemon.setType2(cursor.getString(19));
			
			cursor.close();
			
		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
		db.close();
		return pokemon;
	}
	
	public List<String> getMovePokemon(String name) {
		List<String> moves = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String move;
		Cursor cursor;

		try {
			cursor = db.rawQuery("SELECT p_name FROM MP "
					+ "JOIN Pokemon ON MP.p_id = Pokemon.p_id "
					+ "JOIN Moves ON Moves.m_id = MP.m_id "
					+ "WHERE m_name = '" + name + "' COLLATE NOCASE "
					+ "GROUP BY p_name ORDER BY p_name ASC", null);
			
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			}
			
			cursor.moveToFirst();
			
			do {
				move = cursor.getString(0);
                String output = move.substring(0, 1).toUpperCase() + move.substring(1);
                moves.add(output);
			} while (cursor.moveToNext()); 
			
	        cursor.close();

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		} db.close();
		
		return moves;
	}
	
	public List<String> getPokemonLocations(String pokemon){
		List<String> locs = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String location;
		Cursor cursor;
		try {
			cursor = db.rawQuery("SELECT l_name, r_name FROM Locations "
					+ "JOIN LP ON LP.l_id = Locations.l_locId "
					+ "JOIN Pokemon ON Pokemon.p_id = LP.p_id " 
					+ "JOIN Region ON Locations.l_regId = Region.r_id "
					+ "WHERE p_name = '" + pokemon + "' COLLATE NOCASE "
					+ "GROUP BY l_name ORDER BY l_name DESC", null);
			
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			}
			
			cursor.moveToFirst();
			
			do {
				location = cursor.getString(0);
				String temp = cursor.getString(1);
				location.concat(" - " + temp);
				locs.add(location);
			} while (cursor.moveToNext()); 
			
	        cursor.close();

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		} db.close();
		return locs;
	}
	public List<String> getAllMoveNames() {
		List<String> moveNames = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		String name;

		try {
			cursor = db.rawQuery("SELECT * FROM Moves", null);
			
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			} cursor.moveToFirst();
			
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
		move = new Move();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		Log.e("tle99", "!!!!!!!!!!!!!!" + name + "!!!!!!!!!!!!");

		try {
			cursor = db.rawQuery("SELECT * FROM Moves "
					+ "JOIN Type ON t_id = type "
					+ "WHERE m_name = '" + name + "' COLLATE NOCASE", null);

			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			} 
			
			cursor.moveToFirst();
			
			move.setName(cursor.getString(1));
			move.setPower(cursor.getInt(3));
			move.setPp(cursor.getInt(4));
			move.setAccuracy(cursor.getInt(5));
			move.setAttack_type(cursor.getInt(6));
			move.setType(cursor.getString(10));
			
			cursor.close();
		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
		db.close();
		return move;
	}
	
	public List<String> getPokemonMove(String name) {
		List<String> pokemonNames = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String temp;
		Cursor cursor;		

		try {
			cursor = db.rawQuery("SELECT m_name FROM Pokemon "
					+ "JOIN MP ON Pokemon.p_id = MP.p_id "
					+ "JOIN Moves ON MP.m_id = Moves.m_id "
					+ "WHERE p_name = '" + name + "' COLLATE NOCASE "
					+ "GROUP BY m_name ORDER BY m_name ASC;", null);
			
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			}
			
			cursor.moveToFirst();
			
			do {
				temp = cursor.getString(0);
				pokemonNames.add(temp);
			} while (cursor.moveToNext());
			
			cursor.close();
		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
		db.close();
		return pokemonNames;
	}
	
	public byte[] getImage(String name) {
		byte[] pokemonImage = null;
		Cursor cursor;		
		SQLiteDatabase db = this.getWritableDatabase();

		try {
			cursor = db.rawQuery("SELECT image FROM Pokemon WHERE p_name = '" + name + "' COLLATE NOCASE;", null);
			
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			} cursor.moveToFirst();
			
			pokemonImage = cursor.getBlob(0);
            cursor.close();

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}

		db.close();
		return pokemonImage;
	}
	
	private byte[] getImageOnline(String url){
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
	
	public void insertImage(byte[] blob, String name){
	    SQLiteDatabase db               =   getWritableDatabase();
	    ContentValues val = new ContentValues();
	    val.put("image", blob);
	    db.update("Pokemon", val, "p_name = " + name, null);
	    db.close();
	}
	
	public List<byte[]> getImageList() {
	    List<byte[]> imageList = new ArrayList<byte[]>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		byte[] image;
	    
	    try {
			cursor = db.rawQuery("SELECT 'image' FROM Pokemon", null);
			if (cursor == null) {
                cursor.close();
                db.close();
				return null;
			} 
			
			cursor.moveToFirst();
			
			do {
	            image = cursor.getBlob(5);            
	            imageList.add(image);
	        } while (cursor.moveToNext()); 
			
	        cursor.close();
			
		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
	    db.close();
	    
	    return imageList;
	}
	
	public void insertImages(){
		List<String> names = this.getAllPokemonNames();
		try {
			for(int i = 0; i < names.size(); i++){
				byte[] blob = this.getImageOnline("http://img.pokemondb.net/artwork/"+names.get(i)+".jpg");
				this.insertImage(blob, names.get(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("tle99", e.getMessage());
		}
	}
	
	public List<Abilities> getAbilities(String name) {
		List<Abilities> abilities = new ArrayList<Abilities>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		
		try {
			cursor = db.rawQuery("SELECT * FROM Abilities "
				+ "JOIN AP ON Abilities.a_id = AP.a_id "
				+ "JOIN Pokemon ON AP.p_id = Pokemon.p_id "
				+ "WHERE p_name = '" + name + "' COLLATE NOCASE;", null);
		
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			} cursor.moveToFirst();

			do {
                Abilities ability = new Abilities();
                ability.setName(cursor.getString(1));
                Log.e("tle99", cursor.getString(1));
                ability.setDescription(cursor.getString(2));
                Log.e("tle99", cursor.getString(2));
                Log.e("tle99", "ADDED INTO ABILITIES");
				abilities.add(ability);
	        } while (cursor.moveToNext()); 
			cursor.close();
		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
        db.close();
		return abilities;
	}
	
	public List<String> getLocationNames() {
		List<String> locationNames = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		String name;
		try {
			cursor = db.rawQuery("SELECT l_name, r_name FROM Locations JOIN Region ON l_regId = r_id", null);
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
                return null;
			} 
			
			cursor.moveToFirst();
			
			do {
	            name = cursor.getString(0);
	            name = name + " - " + cursor.getString(1);
	            locationNames.add(name);
	        } while (cursor.moveToNext()); 
			
	        cursor.close();
			
		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
		
		db.close();
		return locationNames;
	}

	public List<String> getEvolutionChain(String name) {
		List<String> evolutionChain = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		String temp;
		
		try {
			cursor = db.rawQuery("SELECT p_name FROM pokemon "
					+ "	JOIN evolution ON e_id = p_id "
					+ "	WHERE evolution_chain_id = "
					+ "		(SELECT evolution_chain_id FROM pokemon "
					+ "			JOIN evolution ON e_id = p_id "
					+ "			WHERE p_name = '" + name + "' COLLATE NOCASE)", null);
			
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			} cursor.moveToFirst();
			
			do {
	            temp = cursor.getString(0);            
	            evolutionChain.add(temp);
	        } while (cursor.moveToNext()); 
			
	        cursor.close();
			
		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
		db.close();
		return evolutionChain;
	}

	public List<String> getPokemonType(String type) {
		List<String> pokemonNames = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor;
		Log.e("tle99", "FUNCTION STARTED");

		try {
			cursor = db.rawQuery("SELECT p_name FROM Pokemon "
					+ "JOIN TP ON tp_id = p_id "
					+ "JOIN Type t1 ON t1.t_id = type1 "
					+ "JOIN Type t2 ON t2.t_id = type2 "
					+ "WHERE t1.t_name = '" + type + "' OR t2.t_name = '" + type +"' COLLATE NOCASE", null);
			Log.e("tle99","QUERY FINISHED");
			if (cursor.getCount() == 0) {
                cursor.close();
                db.close();
				return null;
			} 
			Log.e("tle99", "NOT NULL");

			cursor.moveToFirst();
			Log.e("tle99", "MOVED TO FIRST");

			do {
	            String temp = cursor.getString(0);    
				temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
	            pokemonNames.add(temp);
	        } while (cursor.moveToNext()); 
			
			cursor.close();
			Log.e("tle99", "MOVED INTO STRING LIST");

		} catch (Exception e) {
			Log.e("tle99", e.getMessage());
		}
		Log.e("tle99", "RETURNING NOW");
		
		db.close();
		return pokemonNames;
	}
}
