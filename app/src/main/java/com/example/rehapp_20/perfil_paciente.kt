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

// Definición de la actividad perfil_paciente
class perfil_paciente : AppCompatActivity() {

    // Método que se ejecuta al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita el diseño que permite que la interfaz se extienda de borde a borde en la pantalla
        enableEdgeToEdge()

        // Establece el layout que se usará para esta actividad
        setContentView(R.layout.activity_perfil_paciente)

        // Ajusta el padding de la vista principal para que no se superponga con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Obtiene los márgenes del sistema (barras de estado, navegación, etc.)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Aplica el padding de acuerdo con las barras del sistema
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            // Devuelve los insets aplicados
            insets
        }

        // Asocia el TextView con el ID "text_Salir" al objeto "concuesta4"
        val concuesta4: TextView = findViewById(R.id.text_Salir)

        // Define una acción cuando se haga clic en el TextView "Salir"
        concuesta4.setOnClickListener {
            // Crea un Intent para iniciar la actividad principal del menú
            val intent: Intent = Intent(this, MainActivity_Menu_Principal::class.java)

            // Inicia la actividad usando el Intent creado
            startActivity(intent)
        }

        // Asocia el TextView con el ID "text_editar" al objeto "concuesta5"
        val concuesta5: TextView = findViewById(R.id.text_editar)

        // Define una acción cuando se haga clic en el TextView "Editar"
        concuesta5.setOnClickListener {
            // Crea un Intent para iniciar la actividad de edición del perfil del paciente
            val intent: Intent = Intent(this, EditarPerfilPacienteActivity::class.java)

            // Inicia la actividad usando el Intent creado
            startActivity(intent)
        }
    }
}