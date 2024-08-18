package com.example.rehapp_20.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rehapp_20.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class inicio_Fisio : AppCompatActivity() {
    //se usan los servicios de firebase
    private lateinit var auth: FirebaseAuth
    //para usar bd de reestaurar la contraseña
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_fisio)

        // Initialize Firebase Auth y Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Setup button listener para el login
        val buttonLinkCuenta: Button = findViewById(R.id.ButtonLinkCuenta)
        buttonLinkCuenta.setOnClickListener {
            val email = findViewById<EditText>(R.id.text_Nombre_inicio).text.toString()
            val password = findViewById<EditText>(R.id.tex_Contraseña_inicio).text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Por favor, ingresa tu correo y contraseña.", Toast.LENGTH_SHORT).show()
            }
        }

        // Setup button listener para el registro de paciente
        val buttonLinkRegistro: Button = findViewById(R.id.ButtonLinkRegistro)
        buttonLinkRegistro.setOnClickListener {
            val intent = Intent(this, activity_registro_usuario::class.java)
            startActivity(intent)
        }

        // Setup listener para registro de fisioterapeuta
        val buttonRegistroFisio: Button = findViewById(R.id.ButtonRegistroFisio)
        buttonRegistroFisio.setOnClickListener {
            val intent = Intent(this, activity_registro_fisio::class.java)
            startActivity(intent)
        }

        // Setup text view listener para restablecer contraseña
        val textViewOlvideContraseña: TextView = findViewById(R.id.textViewOlvideContraseña)
        textViewOlvideContraseña.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Inicia la actividad de bienvenida
                    val intent = Intent(this, perfil_usuario::class.java)
                    startActivity(intent)
                    finish() // Finaliza la actividad actual si no deseas que el usuario vuelva a esta pantalla con el botón de retroceso
                } else {
                    // Si el inicio de sesión falla, muestra un mensaje al usuario.
                    Toast.makeText(this, "Error en el inicio de sesión: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
