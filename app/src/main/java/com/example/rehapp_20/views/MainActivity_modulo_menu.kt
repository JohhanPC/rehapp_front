package com.example.rehapp_20.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.rehapp_20.R

class MainActivity_modulo_menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_modulo_menu)

        val concuesta4: LinearLayout  = findViewById(R.id.Liner1)
        concuesta4.setOnClickListener {

            val intent: Intent = Intent(this, modulo1 :: class.java)
            startActivity(intent)
        }
        val txt: LinearLayout  = findViewById(R.id.Liner2)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, modulo2:: class.java)
            startActivity(intent)
        }
        val Concuenta: LinearLayout  = findViewById(R.id.Liner3)
        Concuenta.setOnClickListener {

            val intent: Intent = Intent(this, modulo3:: class.java)
            startActivity(intent)

        }
    val txt3: ImageView  = findViewById(R.id.nav_home)
    txt3.setOnClickListener {

        val intent: Intent = Intent(this, MainActivity_Menu_Principal:: class.java)
        startActivity(intent)
    }
        val txt4: ImageView  = findViewById(R.id.nav_calendario)
        txt4.setOnClickListener {

            val intent: Intent = Intent(this, Calendario:: class.java)
            startActivity(intent)
        }
        val txt5: ImageView  = findViewById(R.id.nav_profile)
        txt5.setOnClickListener {

            val intent: Intent = Intent(this, perfil_usuario:: class.java)
            startActivity(intent)
        }
        val txt6: ImageView  = findViewById(R.id.nav_Atras)
        txt6.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_Menu_Principal:: class.java)
            startActivity(intent)
        }
}
}
