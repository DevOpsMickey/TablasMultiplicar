package com.example.tablasmultiplicar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_aprender_tablas.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_empezarPracticar.setOnClickListener {
            val intencion = Intent(this, AprenderTablas::class.java)
            startActivity(intencion)
        }

        img_btnfacil.setOnClickListener {
            val intencion = Intent(this, PracticarFacilActivity::class.java)
            startActivity(intencion)
        }
        img_btnDificil.setOnClickListener {
            val intencion = Intent(this, PracticarDificilActivity::class.java)
            startActivity(intencion)
        }
    }
}