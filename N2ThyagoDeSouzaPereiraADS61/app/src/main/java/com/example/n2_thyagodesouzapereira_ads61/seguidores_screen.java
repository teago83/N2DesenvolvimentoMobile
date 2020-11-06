package com.example.n2_thyagodesouzapereira_ads61;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class seguidores_screen extends AppCompatActivity {

    private TextView Username;
    private TextView ID;

    private ProgressDialog Load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguidores_screen);

        DownloadFollower Download = new DownloadFollower();

        Username = (TextView)findViewById(R.id.username);
        ID = (TextView)findViewById(R.id.id);

        Download.execute();

    }

    private class DownloadFollower extends AsyncTask<Void, Void, FollowerGit> {

        @Override
        protected void onPreExecute() {
            Load = ProgressDialog.show(seguidores_screen.this,
                    "Please, wait...", "Obtaining info...");
        }

        @Override
        protected FollowerGit doInBackground(Void... voids) {
            Converter Converter = new Converter();
            return Converter.GetSomeInfo("https://api.github.com/users/giselezrossi/followers");
        }

        @Override
        protected void onPostExecute (FollowerGit FollowerGit) {
            Username.setText(FollowerGit.GetUsername().substring(0,1).toUpperCase()+FollowerGit.GetUsername().substring(1));
            ID.setText(FollowerGit.GetID().substring(0,1).toUpperCase()+FollowerGit.GetID().substring(1));

            Load.dismiss();
        }
    }
}