package com.example.rehapp_20

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rehapp_20.views.MainActivity_Menu_Principal
import com.example.rehapp_20.views.inicio_Fisio

class perfil_paciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil_paciente)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            val concuesta4: TextView = findViewById(R.id.text_Salir)
            concuesta4.setOnClickListener {

                val intent: Intent = Intent(this, MainActivity_Menu_Principal :: class.java)
                startActivity(intent)
        }
        val concuesta5: TextView = findViewById(R.id.text_editar)
        concuesta5.setOnClickListener {

            val intent: Intent = Intent(this, EditarPerfilPacienteActivity :: class.java)
            startActivity(intent)



    }
}

}