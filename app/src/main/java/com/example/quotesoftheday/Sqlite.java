package com.example.quotesoftheday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class Sqlite extends SQLiteOpenHelper {

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "quote.db";

        // Computer Table Name
        private static final String TABLE_FAVOURATE = "favourate";

        // Computer Table Columns
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_QUOTE_FAVOURATE = "favourate";



        String CREATE_QUOTE_TABLE = "CREATE TABLE " + TABLE_FAVOURATE + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY, " + COLUMN_QUOTE_FAVOURATE + " TEXT " +
                ")";

        public Sqlite(Context context) {


            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_QUOTE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURATE);

            onCreate(db);

        }

        // creat
        public void addFavourate(Quote favourate) {

            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_QUOTE_FAVOURATE,favourate.getFavourate());


            database.insert(TABLE_FAVOURATE, null, values);


            database.close();

        }

        // Getting all Computer Objects
        public List<Quote> getAllfavourates() {

            List<Quote> computerList = new ArrayList<>();

            String selectAllQuery = "SELECT * FROM " + TABLE_FAVOURATE;
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.rawQuery(selectAllQuery, null);


            if (cursor.moveToFirst()) {

                do {

                    Quote quote=new Quote();
                    quote.setId(Integer.parseInt(cursor.getString(0)));
                    quote.setFavourate(cursor.getString(1));



                    computerList.add(quote);

                } while (cursor.moveToNext());


            }

            return computerList;


        }


    // Deleteing a single computer
    public int deletefav(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
      return   database.delete(TABLE_FAVOURATE, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});

        }
    }


