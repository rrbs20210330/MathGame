package com.example.math_game

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btn_izquierdo: Button
    private lateinit var btn_derecho: Button
    private lateinit var txt_titulo: TextView
    private lateinit var txt_rounds: TextView
    private var rounds = 0
    private var right_guess = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Encuentra los botones en el layout
        btn_izquierdo = findViewById(R.id.btn_izquierdo)
        btn_derecho = findViewById(R.id.btn_derecho)
        txt_titulo = findViewById(R.id.txt_titulo)
        txt_rounds = findViewById(R.id.txt_rounds)


        start_new_round()

        btn_izquierdo.setOnClickListener {
            check_answer(btn_izquierdo.text.toString().toInt(), btn_derecho.text.toString().toInt())
        }

        btn_derecho.setOnClickListener{
            check_answer(btn_derecho.text.toString().toInt(),btn_izquierdo.text.toString().toInt())
        }

    }

    private fun start_new_round (){
        if (rounds < 10) {
            var number1 = (1..10).random()
            var number2 = (1..10).random()

            while (number1 == number2) {
                number2 = (1..10).random()
            }
            btn_izquierdo.text = number1.toString()
            btn_derecho.text = number2.toString()
            btn_izquierdo.setBackgroundColor(Color.BLUE)
            btn_derecho.setBackgroundColor(Color.BLUE)
            rounds++
            txt_rounds.text = rounds.toString()+"/10"
        } else {
            txt_titulo.text = "Juego Terminado\nAciertos:"+right_guess.toString()
            btn_izquierdo.setVisibility(View.GONE);
            btn_derecho.setVisibility(View.GONE);

        }
    }
    private fun check_answer(selectedNumber: Int, otherNumber: Int) {
        if (selectedNumber > otherNumber) {
            if (btn_izquierdo.text.toString().toInt() == selectedNumber) {
                btn_izquierdo.setBackgroundColor(Color.GREEN)
                right_guess++
            } else {
                btn_derecho.setBackgroundColor(Color.GREEN)
                right_guess++
            }
        } else {
            if (btn_izquierdo.text.toString().toInt() == selectedNumber) {
                btn_izquierdo.setBackgroundColor(Color.RED)
            } else {
                btn_derecho.setBackgroundColor(Color.RED)
            }
        }

        // Esperar un segundo antes de iniciar una nueva ronda
        btn_izquierdo.postDelayed({ start_new_round() }, 1000)
    }
}