package com.example.n2_thyagodesouzapereira_ads61;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView Username;
    private TextView ID;

    private ProgressDialog Load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadUser Download = new DownloadUser();

        Username = (TextView)findViewById(R.id.username);
        ID = (TextView)findViewById(R.id.id);

        Download.execute();

        Button Seguidores = (Button)findViewById(R.id.seguidores);

        Seguidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SeguidoresScreen = new Intent (MainActivity.this, seguidores_screen.class);
                startActivity(SeguidoresScreen);
            }
        });

    }

    private class DownloadUser extends AsyncTask<Void, Void, UserGit> {

        @Override
        protected void onPreExecute() {
            Load = ProgressDialog.show(MainActivity.this,
                    "Please, wait...", "Obtaining info...");
        }

        @Override
        protected UserGit doInBackground(Void... voids) {
            Converter Converter = new Converter();
            return Converter.GetInfo("https://api.github.com/users/giselezrossi");
        }

        @Override
        protected void onPostExecute (UserGit UserGit) {
            Username.setText(UserGit.GetUsername().substring(0,1).toUpperCase()+UserGit.GetUsername().substring(1));
            ID.setText(UserGit.GetID().substring(0,1).toUpperCase()+UserGit.GetID().substring(1));

            Load.dismiss();
        }
    }
}