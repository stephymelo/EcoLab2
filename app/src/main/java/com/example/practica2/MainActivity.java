package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {


    private EditText respuestaBox;
    private Button botonsito;
    private TextView pregunta;
    private TextView puntuaje;
    private TextView puntuajeView;
    private Boolean respuestaCorrect;
    private Pregunta respuestaCorrecta;
    private Integer sumaConter;
    private Integer contador;
    private Button botonIntento;
    private Integer respuesta;
    private TextView contadorText;
    private Integer clicks;
    private Boolean boo;
    ArrayList<Pregunta> preguntasArray = new ArrayList<Pregunta>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciar
        botonsito = findViewById(R.id.botonsito);
        botonIntento = findViewById(R.id.botonIntento);
        pregunta = findViewById(R.id.pregunta);
        puntuaje = findViewById(R.id.puntuaje);
        puntuajeView = findViewById(R.id.puntuajeView);
        respuestaBox = findViewById(R.id.respuestaBox);
        contadorText = findViewById(R.id.contador);
        preguntasArray.add(new Pregunta("10x2", "20"));
        preguntasArray.add(new Pregunta("4x2", "8"));
        preguntasArray.add(new Pregunta("5x2", "10"));


        botonsito.setOnClickListener(this);
        botonIntento.setOnClickListener(this);

        PreguntaRandom();


        sumaConter = 0;
        contador = 31;
        clicks=1;
        boo=true;
        AparecerBoton();


        respuestaBox.setOnTouchListener(
                (view, event) -> {

                    return false;
                }

        );






        respuestaBox.setOnTouchListener(

                (view,event) -> {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            clicks+=1;
                            if(clicks<=2){
                                new Thread(
                                        () -> {
                                            while (boo) {
                                                try {
                                                    Thread.sleep(300);



                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                                contador--;
                                                if(contador==0){
                                                    boo=false;


                                                }



                                                runOnUiThread(
                                                        () -> {
                                                            contadorText.setText("" + contador.toString());
                                                            if(contador==0){
                                                                botonIntento.setVisibility(View.VISIBLE);
                                                                boo=true;
                                                                clicks=1;

                                                            }
                                                        }
                                                );
                                            }




                                        }).start();
                            }


                        break;

                        case MotionEvent.ACTION_UP:



                            break;

                    }
                    return true;
                }
        );



}








    public void PreguntaRandom() {

        Random random = new Random();
        respuesta = random.nextInt(preguntasArray.size());
        System.out.println("random" + respuesta);

        respuestaCorrecta = preguntasArray.get(respuesta);

        pregunta.setText(preguntasArray.get(respuesta).getPregunta());

    }

    public void AparecerBoton(){
        if(contador==0){

            clicks=1;
            boo=false;
//            Toast.makeText(this,"holi",Toast.LENGTH_LONG).show();
            botonIntento.setVisibility(View.VISIBLE);
        }else{
            botonIntento.setVisibility(View.GONE);
        }

    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.botonsito:
                if (respuestaBox.getText().toString().equals(respuestaCorrecta.getRespuesta())) {
//                    Log.e("funciono", "onClick: " );


                    preguntasArray.remove(preguntasArray.get(respuesta));
                    System.out.println("tamano" + preguntasArray.size());
                    sumaConter += 15;
                    puntuajeView.setText("" + sumaConter.toString());
                    PreguntaRandom();


                } else {


                }


                break;
            case R.id.botonIntento:
                Log.e("funciono", "onClick: " );
                contador=30;
                contadorText.setText("" + contador.toString());
                botonIntento.setVisibility(View.GONE);


                break;

        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }




}




