package com.keydi.petagram;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.keydi.petagram.mail.SendMailTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ContactoActivity extends AppCompatActivity {

    TextInputEditText ti_name;
    TextInputEditText ti_email;
    EditText et_mensaje;
    Button btn_enviar;
    List<String> to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar miActionBar = findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ti_name = findViewById(R.id.ti_name);
        ti_email = findViewById(R.id.ti_email);
        et_mensaje = findViewById(R.id.editTextMultiLine);
        btn_enviar = findViewById(R.id.btn_enviar);

        to = new ArrayList<>();
        to.add(ti_email.getText().toString());

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SendMailTask(ContactoActivity.this).execute("poneremail@gmail.com",
                        "ponerpassword", to, "emailSubject", et_mensaje.getText().toString());
            }
        });





    }
}