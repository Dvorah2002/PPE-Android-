package fr.be2.monapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class envoidesdonneesvers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoidesdonneesvers);
    }
    public void clique_btn6 (View view){
        finish();
    }
}
