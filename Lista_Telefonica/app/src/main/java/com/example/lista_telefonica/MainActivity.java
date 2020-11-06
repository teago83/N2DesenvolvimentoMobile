package com.example.lista_telefonica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper DB;

    EditText Nome, Telefone;

    Button Add, View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DatabaseHelper(this);

        Nome = (EditText)findViewById(R.id.contato);
        Telefone = (EditText)findViewById(R.id.telefone);

        Add = (Button)findViewById(R.id.contato_button);
        View = (Button)findViewById(R.id.listar_button);

        AddData();
        ShowData();
    }

    public void AddData() {
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                boolean Result = DB.InsertData(Nome.getText().toString(),
                                               Telefone.getText().toString());

                if (Result)
                    Toast.makeText(MainActivity.this, "Data successfully inserted!!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Unable to insert any bloody data...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ShowData() {
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Cursor Result = DB.ShowData();

                if (Result.getCount() == 0){
                    ShowMessage("Error!", "There's no data around here, kiddo.");
                    return;
                }

                StringBuffer Buffer = new StringBuffer();
                while (Result.moveToNext()) {
                    Buffer.append("Contato: " + Result.getString(0) + "\n");
                    Buffer.append("Telefone: " + Result.getString(1) + "\n");
                }

                ShowMessage("Data: ", Buffer.toString());
            }
        });
    }

    public void ShowMessage(String Title, String Message) {
        AlertDialog.Builder Builder = new AlertDialog.Builder(this);
        Builder.setCancelable(true);
        Builder.setTitle(Title);
        Builder.setMessage(Message);
        Builder.show();
    }
}