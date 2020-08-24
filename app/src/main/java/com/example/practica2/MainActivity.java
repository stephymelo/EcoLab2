package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private EditText respuestaBox;
    private Button botonsito;
    private TextView pregunta;
    private TextView puntuaje;
    private TextView puntuajeView;
    private Boolean respuestaCorrect;
    private Pregunta respuestaCorrecta;
    private Integer sumaConter;
    ArrayList <Pregunta> preguntasArray = new ArrayList<Pregunta>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("auida");

        //Referenciar
        botonsito=findViewById(R.id.botonsito);
        pregunta=findViewById(R.id.pregunta);
        puntuaje=findViewById(R.id.puntuaje);
        puntuajeView=findViewById(R.id.puntuajeView);
        respuestaBox=findViewById(R.id.respuestaBox);

        preguntasArray.add(new Pregunta("10x2","20"));
        preguntasArray.add(new Pregunta("4x2","8"));

        botonsito.setOnClickListener(this);
        PreguntaRandom();
        sumaConter=15;






    }

    public void PreguntaRandom(){

        Random random = new Random();
        int respuesta = random.nextInt(preguntasArray.size());

        respuestaCorrecta=preguntasArray.get(respuesta);

        pregunta.setText(preguntasArray.get(respuesta).getPregunta());

    }

    public void onClick(View v){

        switch(v.getId()){

            case R.id.botonsito:

                if(respuestaBox.getText().toString().equals(respuestaCorrecta.getRespuesta())){
                    Log.e("funciono", "onClick: " );
                    Integer.parseInt(puntuajeView.getText().toString()+sumaConter);


                }else{

                }


                break;

        }
    }




}