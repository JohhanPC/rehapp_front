package com.example.rehapp_20

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rehapp_20.views.Calendario

class mis_pacientes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mis_pacientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txt1: ImageView = findViewById(R.id.flecha_avanzar_perfil_paciente1)
        txt1.setOnClickListener {
            val intent: Intent = Intent(this, vista_paciente::class.java)
            startActivity(intent)
    }
        val txt2: ImageView = findViewById(R.id.image_center)
        txt2.setOnClickListener {


        val intent: Intent = Intent(this, buscar_paciente ::class.java)
            startActivity(intent)
}
        val txt3: ImageView = findViewById(R.id.nav_home)
        txt3.setOnClickListener {


            val intent: Intent = Intent(this, menu_fisio::class.java)
            startActivity(intent)
}
        val txt4: ImageView = findViewById(R.id.nav_calendario)
        txt4.setOnClickListener {


            val intent: Intent = Intent(this, Calendario ::class.java)
            startActivity(intent)
}
        val txt5: ImageView = findViewById(R.id.nav_profile)
        txt5.setOnClickListener {


            val intent: Intent = Intent(this, perfil_fisio ::class.java)
            startActivity(intent)
}
}
}
