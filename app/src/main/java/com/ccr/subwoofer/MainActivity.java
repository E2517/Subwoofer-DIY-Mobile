package com.ccr.subwoofer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // Declaracion de variables EditText del activity_main.xml (diseño)
    EditText editTextEmail;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Mensaje al usuario para que pulse el logo para calcular
        Toast.makeText(getApplicationContext(), "Pulsa en el logo para calcular", Toast.LENGTH_LONG).show();

        //Recupero las variables y parseo a EditText el id
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        /**
        Clase SharedPrefences en Modo privado,
         si ya se ha logeado lo manda a CalculoActivity

         */
        SharedPreferences settings = getSharedPreferences(getString(R.string.preferencias_subwoofer), Context.MODE_PRIVATE);
        boolean islogin =  settings.getBoolean(getString(R.string.preferencias_isLogin), false); //Recupera el valor de values --> strings --> preferencias_islogin, la primera vez como false porque aún no se ha logeado

        //Si se ha logeado islogin será true y lo manda a CalculoActivity
        if (islogin) {

            Intent intent = new Intent(this, CalculoActivity.class);
            startActivity(intent);

        }
    }

    /**
     * Calculo Subwoofer HUM PULSANDO EL LOGO, SE LLAMA AL EVENTO DESDE EL DISEÑO
     */
    public void calculo(View view) {
        Intent intent = new Intent(this, CalculoActivity.class);
        startActivity(intent);
    }

    /**
     + LOGIN y PASSWORD - PERSISTENCIA CON PREFERENCIAS
     */

    public void registro(View view) {

        String email = editTextEmail.getText().toString();
        String pass = editTextPassword.getText().toString();

        if (email.equals("carlos") && pass.equals("1234")) {

            //Preferencias compartidas
            //Almacenamiento de datos primitivos privados en pares clave-valor.
            SharedPreferences settings = getSharedPreferences(getString(R.string.preferencias_subwoofer), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            //Le introducimos los datos que queremos guardar
            editor.putString(getString(R.string.preferencias_email), email);
            editor.putBoolean(getString(R.string.preferencias_isLogin), true);
            //COMMIT
            editor.commit();


            //Si es correcto lo manda a CalculoActivity
            Intent intent = new Intent(this, CalculoActivity.class);
            startActivity(intent);


            // Limpio datos de la pantalla
            editTextEmail.setText("");
            editTextPassword.setText("");

        } else {

            Toast.makeText(getApplicationContext(), "Email y/o password incorrectos", Toast.LENGTH_SHORT).show();

        }

    }
}
