package com.example.p04_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etID;
    EditText etName;
    EditText etGPA;
    Button btnInsert;
    Button btnRetrieve;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etGPA = findViewById(R.id.etGPA);
        tvInfo = findViewById(R.id.textView4);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String gpa = etGPA.getText().toString();
                Double gpas = Double.parseDouble(gpa);
                DBHelper db = new DBHelper(MainActivity.this);
                db.insertTask(name,gpas);
                db.close();
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTasks();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i + ". " + data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvInfo.setText(txt);
            }
        });
    }
}
