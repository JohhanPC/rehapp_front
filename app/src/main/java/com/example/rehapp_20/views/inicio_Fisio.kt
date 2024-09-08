package com.example.rehapp_20.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.rehapp_20.R
import com.example.rehapp_20.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

// Marca la actividad con @AndroidEntryPoint para que Hilt pueda inyectar dependencias
@AndroidEntryPoint
class inicio_Fisio : AppCompatActivity() {

    // Inyecta el ViewModel mediante Hilt
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_fisio) // Establece el layout para esta actividad

        // Encuentra el botón para el inicio de sesión por ID y establece un listener
        val buttonLinkCuenta: Button = findViewById(R.id.ButtonLinkCuenta)
        buttonLinkCuenta.setOnClickListener {
            // Obtiene el correo y la contraseña ingresados por el usuario
            val email = findViewById<EditText>(R.id.text_Nombre_inicio).text.toString()
            val password = findViewById<EditText>(R.id.tex_Contraseña_inicio).text.toString()

            // Verifica si los campos no están vacíos antes de intentar iniciar sesión
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password) // Llama a la función para iniciar sesión
            } else {
                // Muestra un mensaje de advertencia si los campos están vacíos
                Toast.makeText(this, "Por favor, ingresa tu correo y contraseña.", Toast.LENGTH_SHORT).show()
            }
        }

        // Observa el resultado del login a través del ViewModel
        userViewModel.loginResult.observe(this, Observer { response ->
            if (response.isSuccessful) {
                // Si el login es exitoso, muestra un mensaje y navega a la pantalla de perfil
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, perfil_usuario::class.java) // Crea un intent para cambiar de pantalla
                startActivity(intent) // Inicia la actividad de perfil
                finish() // Finaliza la actividad actual para que no vuelva al hacer "back"
            } else {
                // Si el login falla, muestra un mensaje de error con el detalle
                Toast.makeText(this, "Error en el inicio de sesión: ${response.message()}", Toast.LENGTH_SHORT).show()
                Log.e("inicio_Fisio", "Error en el inicio de sesión: ${response.errorBody()?.string()}")
            }
        })

        // Listener para redirigir a la actividad de registro de paciente
        val buttonLinkRegistro: Button = findViewById(R.id.ButtonLinkRegistro)
        buttonLinkRegistro.setOnClickListener {
            val intent = Intent(this, activity_registro_usuario::class.java) // Crea un intent para la actividad de registro
            startActivity(intent) // Inicia la actividad de registro de paciente
        }

        // Listener para redirigir a la actividad de registro de fisioterapeuta
        val buttonRegistroFisio: Button = findViewById(R.id.ButtonRegistroFisio)
        buttonRegistroFisio.setOnClickListener {
            val intent = Intent(this, activity_registro_fisio::class.java) // Crea un intent para la actividad de registro de fisioterapeuta
            startActivity(intent) // Inicia la actividad de registro de fisioterapeuta
        }

        // Listener para redirigir a la pantalla de restablecimiento de contraseña
        val textViewOlvideContraseña: TextView = findViewById(R.id.textViewOlvideContraseña)
        textViewOlvideContraseña.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java) // Crea un intent para la actividad de "olvidé mi contraseña"
            startActivity(intent) // Inicia la actividad de restablecimiento de contraseña
        }
    }

    // Función que maneja el proceso de inicio de sesión
    private fun loginUser(email: String, password: String) {
        Log.d("inicio_Fisio", "Iniciando login para el usuario: $email") // Muestra en el log que el login está comenzando
        userViewModel.loginUser(email, password) // Llama al ViewModel para que realice la lógica del login
    }
}
