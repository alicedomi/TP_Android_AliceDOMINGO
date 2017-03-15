package com.example.alice.appmob_domingo_bdd;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChapitreBDD chapitreBdd = new ChapitreBDD(this);

        //Création d'un chapitre
        Chapitre chapitre = new Chapitre("Programmation", "Chapitre 1");

        //ouvre la base de données
        chapitreBdd.open();
        //insère le chapitre créer
        chapitreBdd.insertChapitre(chapitre);

        //Pour vérifier que l'on a bien créé notre chapitre
        //extrait le chapitre de la base de donnée grâce au titre du chapitre que l'on a créé précédemment
        Chapitre chapitreFromBdd = chapitreBdd.getChapitreWithTitre(chapitre.getTitre());
        if(chapitreFromBdd != null){
            //On affiche les infos
            Toast.makeText(this, chapitreFromBdd.toString(), Toast.LENGTH_LONG).show();
            //On modifie le titre
            chapitreFromBdd.setTitre("J'ai modifié le titre du chapitre");
            //on met à jour
            chapitreBdd.updateChapitre(chapitreFromBdd.getId(), chapitreFromBdd);
        }

        //extrait le chapitre de la BDD grâce au nouveau titre
        chapitreFromBdd = chapitreBdd.getChapitreWithTitre("J'ai modifié le titre du chapitre");
        //S'il existe un chapitre possédant ce titre
        if(chapitreFromBdd != null){
            //affiche les nouvelles informations du chapitre pour vérifier que le titre du chapitre a bien été mis à jour
            Toast.makeText(this, chapitreFromBdd.toString(), Toast.LENGTH_LONG).show();
            //supprime le chapitre de la BDD grâce à son ID
            chapitreBdd.removeChapitreWithID(chapitreFromBdd.getId());
        }

        //extrait un nouveau le chapitre de la BDD toujours grâce à son nouveau titre
        chapitreFromBdd = chapitreBdd.getChapitreWithTitre("J'ai modifié le titre du chapitre");
        //Si aucun chapitre n'est retourné
        if(chapitreFromBdd == null){
            //On affiche un message indiquant que le chapitre n'existe pas dans la BDD
            Toast.makeText(this, "Ce chapitre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        //Si le chapitre existe (mais normalement il ne devrait pas)
        else{
            //on affiche un message indiquant que le chapitre existe dans la BDD
            Toast.makeText(this, "Ce chapitre existe dans la BDD", Toast.LENGTH_LONG).show();
        }

        chapitreBdd.close();
    }
}