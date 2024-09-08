package com.example.rehapp_20.views

// Importaciones necesarias para el funcionamiento de la actividad
import android.content.Intent  // Para cambiar de actividades
import android.os.Bundle  // Para pasar y gestionar datos entre las actividades
import android.util.Log  // Para logear mensajes de depuración
import android.widget.Button  // Para trabajar con botones
import android.widget.EditText  // Para trabajar con campos de texto
import android.widget.TextView  // Para trabajar con vistas de texto
import android.widget.Toast  // Para mostrar mensajes rápidos al usuario
import androidx.activity.viewModels  // Para obtener instancias de ViewModels
import androidx.appcompat.app.AppCompatActivity  // Actividad base con compatibilidad hacia atrás
import androidx.lifecycle.Observer  // Para observar LiveData
import com.example.rehapp_20.R  // Referencia a los recursos de la app
import com.example.rehapp_20.viewmodels.UserViewModel  // Importa el ViewModel del usuario
import dagger.hilt.android.AndroidEntryPoint  // Anotación para habilitar inyección de dependencias con Hilt

// Marca la actividad para que sea compatible con la inyección de dependencias usando Hilt
@AndroidEntryPoint
class activity_inicio_sesion : AppCompatActivity() {

    // Inyección del UserViewModel usando el delegate 'by viewModels'
    private val userViewModel: UserViewModel by viewModels()

    // Método onCreate: se llama cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout de la actividad a activity_inicio_sesion.xml
        setContentView(R.layout.activity_inicio_sesion)

        // Encuentra el botón de iniciar sesión en el layout y configura un listener para el click
        val buttonLinkCuenta: Button = findViewById(R.id.ButtonLinkCuenta)
        buttonLinkCuenta.setOnClickListener {
            // Obtiene el texto del campo de email y contraseña
            val email = findViewById<EditText>(R.id.text_Nombre_inicio).text.toString()
            val password = findViewById<EditText>(R.id.tex_Contraseña_inicio).text.toString()

            // Si ambos campos no están vacíos, intenta iniciar sesión
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)  // Llama a la función para hacer login
            } else {
                // Muestra un mensaje al usuario si los campos están vacíos
                Toast.makeText(this, "Por favor, ingresa tu correo y contraseña.", Toast.LENGTH_SHORT).show()
            }
        }

        // Observa el resultado del intento de login a través del ViewModel
        userViewModel.loginResult.observe(this, Observer { response ->
            if (response.isSuccessful) {
                // Si el login fue exitoso, muestra un mensaje y navega a la pantalla principal
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity_Menu_Principal::class.java)
                startActivity(intent)
                finish()  // Finaliza la actividad actual para que no esté en el historial
            } else {
                // Si hubo un error en el login, muestra un mensaje de error y loguea el detalle
                Toast.makeText(this, "Error en el inicio de sesión: ${response.message()}", Toast.LENGTH_SHORT).show()
                Log.e("activity_inicio_sesion", "Error en el inicio de sesión: ${response.errorBody()?.string()}")
            }
        })

        // Encuentra el botón de registro de usuario en el layout y configura un listener para el click
        val buttonLinkRegistro: Button = findViewById(R.id.ButtonLinkRegistro)
        buttonLinkRegistro.setOnClickListener {
            // Crea un intent para navegar a la pantalla de registro de usuario
            val intent = Intent(this, activity_registro_usuario::class.java)
            startActivity(intent)
        }

        // Encuentra el botón de registro de fisioterapeuta y configura el listener
        val buttonRegistroFisio: Button = findViewById(R.id.ButtonRegistroFisio)
        buttonRegistroFisio.setOnClickListener {
            // Navega a la pantalla de registro de fisioterapeuta
            val intent = Intent(this, activity_registro_fisio::class.java)
            startActivity(intent)
        }

        // Encuentra el TextView para restablecer la contraseña y configura el listener
        val textViewOlvideContraseña: TextView = findViewById(R.id.textViewOlvideContraseña)
        textViewOlvideContraseña.setOnClickListener {
            // Navega a la pantalla de recuperación de contraseña
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }
    }

    // Función para iniciar sesión usando el email y contraseña
    private fun loginUser(email: String, password: String) {
        // Loguea un mensaje para depuración
        Log.d("activity_inicio_sesion", "Iniciando login para el usuario: $email")
        // Llama al ViewModel para hacer el login
        userViewModel.loginUser(email, password)
    }
}
