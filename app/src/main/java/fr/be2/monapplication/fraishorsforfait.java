
package fr.be2.monapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class fraishorsforfait extends AppCompatActivity {


    SQLHelper bdd;
    EditText libelle;
    EditText montant;
    EditText date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraishorsforfait);
        bdd= new SQLHelper(this);
        libelle=findViewById(R.id.libelle);
        date=findViewById(R.id.Date);
        montant=findViewById(R.id.montant);
    }

    public void save_DATA(View view){
        String libelle1 = libelle.getText().toString();
        Double montant1 = Double.parseDouble(montant.getText().toString());
        String date1 = date.getText().toString();
        if( bdd.insertData(libelle1, libelle1,0, montant1, date1)) {
            libelle.setText("");
            montant.setText("");
            date.setText("");

            Toast.makeText(fraishorsforfait.this,"Frais enregistré" ,Toast.LENGTH_LONG).show();

        }
    }

    public void clique_retour(View view) {
        finish();
    }

}