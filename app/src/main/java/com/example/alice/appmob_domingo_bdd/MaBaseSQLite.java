package com.example.alice.appmob_domingo_bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by Alice on 15/03/2017.
 */


public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_CHAPITRE = "table_chapitre";
    private static final String COL_ID = "ID";
    private static final String COL_ISBN = "ISBN";
    private static final String COL_TITRE = "Titre";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_CHAPITRE + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ISBN + " TEXT NOT NULL, "
            + COL_TITRE + " TEXT NOT NULL);";

    public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_CHAPITRE + ";");
        onCreate(db);
    }

}