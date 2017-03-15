package com.example.alice.appmob_domingo_bdd;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alice on 15/03/2017.
 */


public class ChapitreBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "eleves.db";

    private static final String TABLE_CHAPITRE = "table_chapitre";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_ISBN = "ISBN";
    private static final int NUM_COL_ISBN = 1;
    private static final String COL_TITRE = "Titre";
    private static final int NUM_COL_TITRE = 2;

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public ChapitreBDD(Context context){
        // Création de la table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){

        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la base de donnée
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertChapitre(Chapitre chapitre){
        ContentValues values = new ContentValues();
        values.put(COL_ISBN, chapitre.getIsbn());
        values.put(COL_TITRE, chapitre.getTitre());
        return bdd.insert(TABLE_CHAPITRE, null, values);
    }

    public int updateChapitre(int id, Chapitre chapitre){
        ContentValues values = new ContentValues();
        values.put(COL_ISBN, chapitre.getIsbn());
        values.put(COL_TITRE, chapitre.getTitre());
        return bdd.update(TABLE_CHAPITRE, values, COL_ID + " = " +id, null);
    }

    public int removeChapitreWithID(int id){
        //Supprime un chapitre
        return bdd.delete(TABLE_CHAPITRE, COL_ID + " = " +id, null);
    }

    public Chapitre getChapitreWithTitre(String titre){
        //Selection d'un chapitre via son titre
        Cursor c = bdd.query(TABLE_CHAPITRE, new String[] {COL_ID, COL_ISBN, COL_TITRE}, COL_TITRE + " LIKE \"" + titre +"\"", null, null, null, null);
        return cursorToChapitre(c);
    }


    private Chapitre cursorToChapitre(Cursor c){
        //si rien n'est trouvé, on renvoit null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un chapitre
        Chapitre chapitre = new Chapitre();
        //on lui affecte toutes les infos via cursor
        chapitre.setId(c.getInt(NUM_COL_ID));
        chapitre.setIsbn(c.getString(NUM_COL_ISBN));
        chapitre.setTitre(c.getString(NUM_COL_TITRE));
        //On ferme le cursor
        c.close();

        //On retourne le chapitre
        return chapitre;
    }
}