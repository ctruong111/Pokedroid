package com.example.pokedroid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;

public class imageSnatcher {

	private int IO_BUFFER_SIZE = 64;
    //private int IO_BUFFER_SIZE = 8192;
    private URL urlObject = null;
    private URLConnection myConn = null;
    ByteArrayOutputStream os = null;
	/**
	 * @param args
	 */
	public Bitmap Base64ImageFromURL(String url) {
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;

        try {
            urlObject = new URL(url);
            myConn = urlObject.openConnection();
            in = myConn.getInputStream();

            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);

            copyCompletely(in, out);

            final byte[] data = dataStream.toByteArray();
            BitmapFactory.Options options = new BitmapFactory.Options();

            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        } catch (IOException e) {
            Log.e("TAG", "Could not load Bitmap from: " + url);
        } finally {
            //closeStream(in);
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            //closeStream(out);
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        return bitmap;
    }
	
	public String convertToBase64(Bitmap bitmap) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
        byte[] byteArray = os.toByteArray();
        return Base64.encodeToString(byteArray, 0);
    }
	
	public Bitmap convertToBitmap(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap bitmapResult = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return bitmapResult;
    }

	private void copyCompletely(InputStream input, OutputStream output) throws IOException {
        // if both are file streams, use channel IO
        if ((output instanceof FileOutputStream) && (input instanceof FileInputStream)) {
            try {
                FileChannel target = ((FileOutputStream) output).getChannel();
                FileChannel source = ((FileInputStream) input).getChannel();

                source.transferTo(0, Integer.MAX_VALUE, target);

                source.close();
                target.close();

                return;
            } catch (Exception e) { /* failover to byte stream version */
            }
        }

        byte[] buf = new byte[8192];
        while (true) {
            int length = input.read(buf);
            if (length < 0)
                break;
            output.write(buf, 0, length);
        }

        try {
            input.close();
        } catch (IOException ignore) {
        }
        try {
            output.close();
        } catch (IOException ignore) {}
    }
	
	public void main(String[] args) {
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/assets/Pokemon");
			Statement stat = connection.createStatement();
			Scanner input = new Scanner(System.in);
			ResultSet rs = stat.executeQuery("SELECT p_name FROM Pokemon;");
			while(rs.next()){
				String pokemon = rs.getString("p_id");
				System.out.println("Snatching " + pokemon + "...");
				String goingIn = "http://img.pokemondb.net/artwork/"+pokemon+".jpg";
				Bitmap img = null;
				img = Base64ImageFromURL(goingIn);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				img.compress(Bitmap.CompressFormat.PNG, 100, bos);
				byte[] bArray = bos.toByteArray();
				stat.executeUpdate("UPDATE Pokemon " +
						"SET p_img = " + bos
						+ " WHERE p_name = " + pokemon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}