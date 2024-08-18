package com.example.rehapp_20

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rehapp_20.views.activity_bienvenida

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, activity_bienvenida::class.java)
        startActivity(intent)
        finish()  // Finaliza MainActivity para que no vuelva al presionar "atrás"
    }

}