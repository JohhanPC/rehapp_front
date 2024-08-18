package com.example.rehapp_20.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.rehapp_20.R

class MainActivity_Menu_Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_principal)

        val concuesta4: ImageView  = findViewById(R.id.imagen_modulo1)
        concuesta4.setOnClickListener {

            val intent: Intent = Intent(this, inicio_Fisio :: class.java)
            startActivity(intent)


        }


        val txt: ImageView  = findViewById(R.id.imagen_modulo2)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_modulo_menu:: class.java)
            startActivity(intent)

        }

        val Concuenta: ImageView  = findViewById(R.id.imagen_modulo4)
        Concuenta.setOnClickListener {

            val intent: Intent = Intent(this, activity_inicio_sesion:: class.java)
            startActivity(intent)

        }
        val Concuenta1: ImageView  = findViewById(R.id.imagen_modulo3)
        Concuenta1.setOnClickListener {

            val intent: Intent = Intent(this, Calendario :: class.java)
            startActivity(intent)

        }
        val Concuenta2: ImageView  = findViewById(R.id.imagen_modulo5)
        Concuenta2.setOnClickListener {

            val intent: Intent = Intent(this, Notificaciones :: class.java)
            startActivity(intent)

        }
        val Concuenta3: ImageView  = findViewById(R.id.imagen_modulo6)
        Concuenta3.setOnClickListener {

            val intent: Intent = Intent(this, perfil_usuario :: class.java)
            startActivity(intent)
        }
    }
}
