package com.example.rehapp_20

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge // Importa la función para usar el espacio completo de la pantalla
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditarPerfilPacienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita la función para usar el espacio completo de la pantalla, incluso debajo de las barras del sistema
        enableEdgeToEdge()

        // Establece el layout de la actividad
        setContentView(R.layout.activity_editar_perfil_paciente)

        // Configura los insets del sistema (barras de estado, navegación, etc.) para el View principal
        // Esto asegura que el contenido de la pantalla no se superponga a las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            // Obtiene los insets actuales del sistema (barras de estado y de navegación)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Aplica los insets como padding al View principal, asegurando que el contenido no se oculte debajo de las barras del sistema
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            // Retorna los insets tal como fueron recibidos, sin modificaciones
            insets
        }

        // Encuentra la ImageView con ID 'modulo1' en el layout
        val modulo1ImageView: ImageView = findViewById(R.id.modulo1)

        // Establece un listener para detectar cuando el usuario hace clic en la ImageView 'modulo1'
        modulo1ImageView.setOnClickListener {
            // Al hacer clic, se crea un Intent para navegar a la actividad 'perfil_paciente'
            val intent = Intent(this, perfil_paciente::class.java)

            // Inicia la actividad 'perfil_paciente'
            startActivity(intent)
        }
    }
}

