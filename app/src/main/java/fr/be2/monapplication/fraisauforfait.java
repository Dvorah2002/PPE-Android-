package fr.be2.monapplication;

import androidx.appcompat.app.AlertDialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class fraisauforfait extends menuprincipal  {
    //declaration des variables
    SQLHelper bdd;
    Spinner typefrais;
    EditText quantite;
    Button btnAjouter  ;
    EditText MaDate;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance();
    int aaaa = calendrier.get(Calendar.YEAR);
    int mm = calendrier.get(Calendar.MONTH);
    int jj = calendrier.get(Calendar.DAY_OF_MONTH);
    //tableaux des montants frais au forfait
    Double montantfrais[]=new Double[]{0.62,110.00,80.00,25.00};
    String Typefrais[]=new String []{"","Forfait Km","Forfait étape","Forfait hotel","Repas Restaurant"};
    private Object fraisauforfait;


    //constructeur
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraisauforfait);
        init();
        bdd = new SQLHelper (this);

    }

    private void init() {
        typefrais= findViewById(R.id.typefrais);
        quantite= findViewById(R.id.prenom);
        btnAjouter= findViewById(R.id.btnAjouter);
        MaDate= findViewById(R.id.Date);
    }

    public void ShowCal(View v)
    {
        picker = new DatePickerDialog (fraisauforfait.this ,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        MaDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },aaaa, mm, jj );
        picker.show(); }


    public void clique_btn6 (View view){
        finish();

    }


    public void afficherMessage(String titre,String message){
        AlertDialog.Builder Builder=new AlertDialog.Builder(this ) ;
        Builder.setCancelable(true);
        Builder.setTitle(titre);
        Builder.setMessage(message);
        Builder.show();

    }


    public void MonClick(View v ) {
        switch (v.getId()) {
            case R.id.btnAjouter:

                if (quantite.getText().toString().length() == 0) {
                    afficherMessage("Erreur !", "Vous n'avez saisi aucune valeur ");
                    return;

                } else {
                    Integer quantite1 = Integer.parseInt(quantite.getText().toString()); //cette ligne a un pb. Il narrive pas à inserer les donnees
                    String forfait1 = typefrais.getSelectedItem().toString();
                    String Date1= MaDate.getText().toString();
                    int posForfait = typefrais.getSelectedItemPosition();
                    /* for (int i=0; i<= 4; i++){
                    for (int k=0; k<= 4; k++){
                    if (TypeFraisForfait[i] == elementSelectionne){
                    montantCalcule=(FraisAuForfait[k]*quantite);
                    }
                    }*/
                    Double montantCalcule = quantite1 * montantfrais[posForfait];
                    if (bdd.insertData(forfait1, forfait1, quantite1, montantCalcule, Date1)) {
                       afficherMessage("Succès", "Valeur ajoutée. " + "Montant= " + montantCalcule);
                    }
                }
        }
    }

    public void clique_retour(View view) {
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




    private void afficherMessage() {
    }
}


