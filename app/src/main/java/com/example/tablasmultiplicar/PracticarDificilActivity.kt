package com.example.tablasmultiplicar

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_practicar_dificil.*
import kotlinx.android.synthetic.main.activity_practicar_facil.*
import kotlin.random.Random



class PracticarDificilActivity : AppCompatActivity() {
    var resultado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practicar_dificil)
        generarOperacion()

        btnVerificaOperacionDificil.setOnClickListener {
            val restText = etNumDificil.text.toString()
            if (!restText.equals("")){
                val respuesta = restText.toInt()
                if (respuesta == resultado){
                    crearDialogoV()
                    generarOperacion()
                }else{
                    crearDialogoF()
                    generarOperacion()
                }
            }
            generarOperacion()
        }
    }


    fun generarOperacion(){
        val operador1 = Random.nextInt(10,15)
        val operador2 = Random.nextInt(10,15)

        resultado = operador1 * operador2
        tvOperacionDificil.text = "$operador1 x $operador2 = ?"
    }

    fun crearDialogoV(){
        val miDialogoView =  LayoutInflater.from(this).inflate(R.layout.dialogook, null)
        val alertBulder = AlertDialog.Builder(this).setView(miDialogoView)
            .setTitle("EXCELENTE")
        val miDialogoOk = alertBulder.create()
        miDialogoOk.show()

        val miPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.oksong)
        miPlayer?.start()
    }

    fun crearDialogoF(){
        val miDialogoView =  LayoutInflater.from(this).inflate(R.layout.dialogofail, null)
        val alertBulder = AlertDialog.Builder(this).setView(miDialogoView)
            .setTitle("MUY MAL")
        val miDialogoOk = alertBulder.create()
        miDialogoOk.show()

        val miPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.fail)
        miPlayer?.start()
    }


}