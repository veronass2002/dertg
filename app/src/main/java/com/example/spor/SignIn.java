package com.example.spor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signin);
    }

    public void SignInOnClick(View view) throws InterruptedException {
        EditText emailField = findViewById(R.id.emailField);
        EditText passwordField = findViewById(R.id.passwordField);
        String URL = "";

        if (emailField.getText().toString().equals("") || passwordField.getText().toString().equals(""))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Ошибка!").setMessage("Необходимо заполнить все поля!");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        postTask sendPost = new postTask();
        sendPost.execute(emailField.getText().toString(), passwordField.getText().toString());

        while ( MyHelper.response == null )
        {
            Thread.sleep (500);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (MyHelper.response.code () == 200)
        {
            builder.setTitle("Успех!").setMessage("Вы авторизованы!").setNegativeButton ("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(getApplicationContext (), MainPage.class);
                    startActivity(intent);
                }

        }).setOnCancelListener (new DialogInterface.OnCancelListener ( ) {
                @Override
                public void onCancel (DialogInterface dialog) {
                    Intent intent = new Intent(getApplicationContext (), MainPage.class);
                    startActivity(intent);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if (MyHelper.response.code () != 200)
        {
            builder.setTitle("Ошибка!").setMessage("Проблема при авторизации!");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void SignUpOnClick(View view)
    {
        Intent intent = new Intent(getApplicationContext(), Registration.class);
        startActivity(intent);
    }
}