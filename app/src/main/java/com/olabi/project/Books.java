package com.olabi.project;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Books {
    private static ArrayList<Book> books = new ArrayList<>();

    public static ArrayList<Book> getBooks() {
        return books;
    }

    public static void setBooks(ArrayList<Book> books) {
        Books.books = books;
    }

    public static void lodeData(Context context, String pathFile) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(pathFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray obj = new JSONArray(json);
            for (int j = 0; j < obj.length(); j++) {
                Book t = new Book();

                t.setTitle(obj.getJSONObject(j).getString("title"));
                if (obj.getJSONObject(j).get("cover").equals("cover1.png"))
                    t.setImage(R.drawable.cover1);
                else if (obj.getJSONObject(j).get("cover").equals("cover2.png"))
                    t.setImage(R.drawable.cover2);
                else if (obj.getJSONObject(j).get("cover").equals("cover3.png"))
                    t.setImage(R.drawable.cover3);
                else if (obj.getJSONObject(j).get("cover").equals("cover4.png"))
                    t.setImage(R.drawable.cover4);

                books.add(t);
            }


        } catch (IOException | JSONException ex) {
            ex.printStackTrace();
        }


    }


}
