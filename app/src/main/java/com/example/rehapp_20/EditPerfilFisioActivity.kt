package com.example.rehapp_20

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Definición de la actividad EditPerfilFisioActivity
class EditPerfilFisioActivity : AppCompatActivity() {

    // Método que se llama cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita el diseño de borde a borde para mejorar la experiencia visual
        enableEdgeToEdge()

        // Establece el layout que se usará para esta actividad
        setContentView(R.layout.activity_edit_perfil_fisio)

        // Ajusta los márgenes de la vista principal para que no se superpongan con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Obtiene los márgenes del sistema (barras de estado, navegación, etc.)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Establece el padding de la vista principal de acuerdo a las barras del sistema
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            // Devuelve los insets aplicados
            insets
        }

        // Asocia el ImageView con el ID "modulo1" al objeto "concuesta4"
        val concuesta4: ImageView = findViewById(R.id.modulo1)

        // Define una acción cuando se haga clic en la imagen
        concuesta4.setOnClickListener {
            // Crea un Intent para iniciar la misma actividad EditPerfilFisioActivity
            val intent: Intent = Intent(this, EditPerfilFisioActivity::class.java)

            // Inicia la actividad usando el Intent creado
            startActivity(intent)
        }
    }
}

