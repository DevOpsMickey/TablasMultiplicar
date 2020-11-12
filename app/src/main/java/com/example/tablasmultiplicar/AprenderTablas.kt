package com.example.tablasmultiplicar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_aprender_tablas.*
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.view.get
import java.util.*

class AprenderTablas : AppCompatActivity(), TextToSpeech.OnInitListener, AdapterView.OnItemClickListener {
    var tts: TextToSpeech? = null
    var listaElementos: MutableList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprender_tablas)

        tvAprendeT.text = "TABLA DEL 1"
        listaElementos = mutableListOf<String>()
        tts = TextToSpeech(this, this)
        Log.i("Lenguajes", Locale.getAvailableLocales().toString())

        listaTablas.setOnItemClickListener(this)

        for(i in 0..10){
            val texto = "1 x $i = ${1*i}"
            listaElementos!!.add(texto)
        }
        val adaptador = ArrayAdapter(application, android.R.layout.simple_list_item_1,listaElementos!!)
        listaTablas.adapter = adaptador

        seekBarTabla.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (progress < 1){
                    seekBarTabla.setProgress(1)
                }else{
                    listaElementos!!.clear()

                    for(i in 0..10){
                        val texto = "$progress x $i = ${progress*i}"
                        listaElementos!!.add(texto)
                    }
                    val adaptador = ArrayAdapter(application, android.R.layout.simple_list_item_1,listaElementos!!)

                    listaTablas.adapter = adaptador

                    tvAprendeT.text = "TABLA DEL $progress"
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })


        btnAprenderRegresar.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale("es_MX"))
            if (result != TextToSpeech.LANG_NOT_SUPPORTED && result != TextToSpeech.LANG_MISSING_DATA){

            }else{
                Toast.makeText(this,"Lenguaje no soportado", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        if (tts!! != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var Texto = listaElementos!!.get(position)
        tts!!.speak(Texto, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}