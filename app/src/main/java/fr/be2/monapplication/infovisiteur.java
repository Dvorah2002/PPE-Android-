package fr.be2.monapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class infovisiteur extends AppCompatActivity {
    //declaration des variables
        SQLHelper2 bdd;
        EditText editText ;
        EditText editText2 ;
        EditText editText3 ;
        EditText editText4 ;
        EditText editText6 ;
        EditText editText7 ;
        EditText editText8 ;
        Button btnAjouter ;
        private Object infovisiteur ;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_infovisiteur);
            bdd = new SQLHelper2(this);
        }

        private void init() {
        editText= findViewById(R.id.editText);
        editText2= findViewById(R.id.editText2);
        editText3= findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText6= findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);
        btnAjouter= findViewById(R.id.btnAjouter);
    }

    public void afficherMessage(String titre,String message){
        AlertDialog.Builder Builder=new AlertDialog.Builder(this ) ;
        Builder.setCancelable(true);
        Builder.setTitle(titre);
        Builder.setMessage(message);
        Builder.show();
    }

    public void clique_retour(View view) {
        finish();
    }

    public void MonClick(View view ) {
        switch (view.getId()) {
            case R.id.btnAjouter:

              /*  if (editText2.getText().toString().length() == 0 ) {
                    afficherMessage("Erreur !", "Vous n'avez saisi aucune valeur ");
                    return;

                } else */
              {
                        String Nom = editText.getText().toString();
                        String Prenom = editText2.getText().toString();
                        String Adresse = editText3.getText().toString();
                        Integer Code_Postal = Integer.parseInt(editText4.getText().toString());
                        String Ville = editText6.getText().toString();
                        String Email = editText7.getText().toString();
                        String Tel = editText8.getText().toString();
                        if (bdd.insertData(Nom, Prenom, Adresse, Code_Postal, Ville, Email , Tel)) {
                            afficherMessage("Succès", "Vos information ont bien etait enregistrés. " );
                        }
                    }
                }
    }
    }

