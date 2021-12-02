package com.example.spor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);
    }

    public void SignUpOnClick(View view) throws InterruptedException {
        EditText regEmailField = findViewById(R.id.regEmailField);
        EditText regPasswordField = findViewById(R.id.regPasswordField);
        EditText regPasswordField2 = findViewById(R.id.regPasswordField2);
        EditText nameField = findViewById(R.id.personNameField);
        EditText surnameField = findViewById(R.id.personSurnameField);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (regEmailField.getText().toString().equals("")
                || regPasswordField.getText().toString().equals("")
                || regPasswordField2.getText().toString().equals("")
                || nameField.getText().toString().equals("")
                || surnameField.getText().toString().equals(""))
        {
            builder.setTitle("Ошибка!").setMessage("Необходимо заполнить все поля!");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        if (!MyHelper.checkEmail(regEmailField.getText().toString()))
        {
            builder.setTitle("Ошибка!").setMessage("Неверный email!");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        if (!regPasswordField.getText().toString().equals(regPasswordField2.getText().toString()))
        {
            builder.setTitle("Ошибка!").setMessage("Пароли не совпадают!");
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        builder.setTitle("Успешно!").setMessage("Вы зарегестрированы!");
        AlertDialog dialog = builder.create();
        dialog.show();

        postTask sendPost = new postTask ();
        sendPost.execute (regEmailField.getText ().toString (), regPasswordField.getText ().toString (), nameField.getText ().toString (), surnameField.getText ().toString ());

        while ( MyHelper.response == null )
        {
            Thread.sleep (500);
        }

        if (MyHelper.response.code () == 200)
        {
            builder.setTitle("Успешно!").setMessage("Вы зарегестрированы!").setNegativeButton ("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish ();
                }

            }).setOnCancelListener (new DialogInterface.OnCancelListener ( ) {
                @Override
                public void onCancel (DialogInterface dialog) {
                    finish ();
                }
            });
            dialog = builder.create();
            dialog.show();
        }
        else if (MyHelper.response.code () != 200)
        {
            builder.setTitle("Ошибка!").setMessage("Проблема при регистрации!");
            dialog = builder.create();
            dialog.show();
        }
    }

    public void SignInOnClick(View view)
    {
        finish();
    }

}