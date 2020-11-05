package com.example.n2_thyagodesouzapereira_ads61;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
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

    }

    private class DownloadUser extends AsyncTask<Void, Void, User> {

        @Override
        protected void onPreExecute() {
            Load = ProgressDialog.show(MainActivity.this,
                    "Please, wait...", "Obtaining info...");
        }

        @Override
        protected User doInBackground(Void... voids) {
            Converter Converter = new Converter();
            return Converter.GetInfo("https://api.github.com/users/giselezrossi");
        }

        @Override
        protected void onPostExecute (User User) {
            Username.setText(User.GetUsername().substring(0,1).toUpperCase()+User.GetUsername().substring(1));
            ID.setText(User.GetID().substring(0,1).toUpperCase()+User.GetID().substring(1));

            Load.dismiss();
        }
    }
}