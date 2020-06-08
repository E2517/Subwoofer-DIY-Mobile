package com.ccr.subwoofer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculoActivity extends AppCompatActivity {

    EditText fs;
    EditText qes;
    EditText qts;
    EditText vas;
    TextView resultado;
    TextView volumen;
    TextView f3;
    TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Recupero los datos del CalculoActivity
        fs = (EditText) findViewById(R.id.editTextFs);
        qes = (EditText) findViewById(R.id.editTextQes);
        qts = (EditText) findViewById(R.id.editTextQts);
        vas = (EditText) findViewById(R.id.editTextVas);
        resultado = (TextView) findViewById(R.id.textViewResultado);
        volumen = (TextView) findViewById(R.id.textViewVolumen);
        f3 = (TextView) findViewById(R.id.textViewF3);
        titulo = (TextView) findViewById(R.id.textViewCalculo);

        //Botón flotante para limpiar los datos de la pantalla y que muestre un mensaje

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Se han borrado los datos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Borrar datos de la pantalla
                resultado.setText("");
                volumen.setText("");
                f3.setText("");
                fs.setText("");
                qes.setText("");
                qts.setText("");
                vas.setText("");

            }
        });

        //Necesitas los parámetros Thiell Small
        Toast.makeText(getApplicationContext(), "Necesitas los parámetros Thiele Small", Toast.LENGTH_LONG).show();
    }

        /** =================================================

         COMIENZAN LOS MÉTODOS PARA REALIZAR LOS CÁLCULOS

         ===================================================*/


        /**
        Método calcular Sellado o Bass Reflex
        */

    public void calcularSelladoBassReflex(View view) {

        String fs_String = fs.getText().toString();
        String qes_String = qes.getText().toString();

        double fs_double = Double.parseDouble(fs_String);
        double qes_double = Double.parseDouble(qes_String);

        double calculo = fs_double / qes_double;


        if (calculo <= 50) {

            resultado.setText("SUBWOOFER SELLADO");


        } else {

            resultado.setText("BASS REFLEX");
        }

    }

        /**
        Método calcular Volumen y F3
         */

    public void calcularVolumenSellado(View view) {

        String fs_String = fs.getText().toString();
        String qes_String = qes.getText().toString();

        String qts_String = qts.getText().toString();
        String vas_String = vas.getText().toString();

        double fs_double = Double.parseDouble(fs_String);
        double qes_double = Double.parseDouble(qes_String);
        double qts_double = Double.parseDouble(qts_String);
        double vas_double = Double.parseDouble(vas_String);
        double qtc = 0.707;

        double calculo = fs_double / qes_double;

        if (calculo <= 50) {

            double fc = (qtc * fs_double) / qts_double;
            double a = 1 / (qtc * qtc) - 2;
            double ftres = fc * (a + (a * a + 4) / 2) / 2;
            double vol = vas_double / (-1 + (fc / fs_double) * (fc / fs_double));

            volumen.setText("Volumen: " + String.format("%.2f", vol) + " litros");
            f3.setText("F3: " + String.format("%.2f", ftres) + " Hz");

        } else if (calculo > 50) {

            double vol = 20 * vas_double * (Math.pow(qts_double, 3.3));
            double raiz = (vas_double * (fs_double * fs_double)) / vol;
            double ftres = Math.sqrt(raiz);

            volumen.setText("Volumen: " + String.format("%.2f", vol) + " litros");
            f3.setText("F3: " + String.format("%.2f", ftres) + " Hz");
        }

    }

        /**
        Reset de resultados mostrados pulsando el titulo
         */

    public void reset(View view) {

        resultado.setText("");
        volumen.setText("");
        f3.setText("");

    }

}



