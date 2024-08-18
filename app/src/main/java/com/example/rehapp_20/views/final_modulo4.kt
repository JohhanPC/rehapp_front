package com.example.rehapp_20.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.rehapp_20.R

class final_modulo4: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_modulo4)




        val txt: Button = findViewById(R.id.buttonNuevoModulo)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_modulo_menu:: class.java)
            startActivity(intent)




        }
    }
}