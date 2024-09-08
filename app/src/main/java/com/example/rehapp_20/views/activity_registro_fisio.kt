package com.example.rehapp_20.views

// Importaciones necesarias para el funcionamiento de la actividad
import android.content.Intent  // Para navegar entre actividades
import android.os.Bundle  // Para manejar el estado de la actividad
import android.util.Log  // Para mensajes de depuración
import android.widget.Button  // Para trabajar con botones
import android.widget.EditText  // Para trabajar con campos de texto
import android.widget.Toast  // Para mostrar mensajes breves al usuario
import androidx.activity.viewModels  // Para obtener instancias de ViewModels
import androidx.appcompat.app.AppCompatActivity  // Actividad base con compatibilidad hacia atrás
import androidx.lifecycle.Observer  // Para observar LiveData
import com.example.rehapp_20.R  // Para acceder a recursos como layouts
import com.example.rehapp_20.enums.UserType  // Enumeración para tipos de usuario
import com.example.rehapp_20.menu_fisio  // Actividad de menú del fisioterapeuta
import com.example.rehapp_20.models.PhysioUserRegisterDTO  // DTO para registro de fisioterapeuta
import com.example.rehapp_20.viewmodels.PhysioViewModel  // ViewModel para gestionar la lógica de registro
import dagger.hilt.android.AndroidEntryPoint  // Anotación para inyección de dependencias con Hilt

// Marca la actividad para que sea compatible con la inyección de dependencias usando Hilt
@AndroidEntryPoint
class activity_registro_fisio : AppCompatActivity() {

    // Inyección del PhysioViewModel usando el delegate 'by viewModels'
    private val physioViewModel: PhysioViewModel by viewModels()
    private val TAG = "activity_registro_fisio"  // Constante para usar en los logs

    // Método onCreate, llamado al iniciar la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout de la actividad a activity_registro_fisio.xml
        setContentView(R.layout.activity_registro_fisio)

        // Encuentra el botón de registro en el layout y configura un listener para el click
        val buttonRegistrarme: Button = findViewById(R.id.ButtonRegistrarFisioterapeuta)
        buttonRegistrarme.setOnClickListener {
            // Obtiene los valores de los campos de texto del formulario de registro
            val email = findViewById<EditText>(R.id.tex_Correo_fisio).text.toString()
            val password = findViewById<EditText>(R.id.text_contraseña_fisio).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.tex_comfirmar_contraseña_fisio).text.toString()
            val userName = findViewById<EditText>(R.id.text_Nombre_fisio).text.toString()
            val identificationNumber = findViewById<EditText>(R.id.text_identificacion_fisio).text.toString()
            val professionalCardNumber = findViewById<EditText>(R.id.tex_tajeta_fisio).text.toString()
            val phoneNumber = findViewById<EditText>(R.id.text_telefono_fisio).text.toString()

            // Log para depuración al hacer clic en el botón de registro
            Log.d(TAG, "onCreate: Click en 'Registrarme' con email: $email")

            // Verifica que los campos no estén vacíos y que las contraseñas coincidan
            if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword && identificationNumber.isNotEmpty() && professionalCardNumber.isNotEmpty() && phoneNumber.isNotEmpty()) {
                // Crea un objeto DTO con los datos del fisioterapeuta
                val user = PhysioUserRegisterDTO(
                    email = email,
                    userName = userName,
                    password = password,
                    userType = UserType.PHYSIOTHERAPIST,  // Especifica que es un fisioterapeuta
                    identificationNumber = identificationNumber,
                    professionalCardNumber = professionalCardNumber,
                    phoneNumber = phoneNumber
                )
                // Log para confirmar el envío del DTO
                Log.d(TAG, "onCreate: Enviando DTO para registro: $user")
                // Llama al ViewModel para registrar al fisioterapeuta
                physioViewModel.registerPhysio(user)
            } else {
                // Si falla la validación, muestra un mensaje y loguea un warning
                Log.w(TAG, "onCreate: Fallo en la validación del formulario")
                Toast.makeText(this, "Por favor, verifica los campos y asegúrate de que las contraseñas coincidan.", Toast.LENGTH_SHORT).show()
            }
        }

        // Observa el resultado del registro a través del ViewModel
        physioViewModel.registrationResult.observe(this, Observer { response ->
            if (response.isSuccessful) {
                // Si el registro fue exitoso, muestra un mensaje y navega al menú de fisioterapeuta
                Log.d(TAG, "onCreate: Registro exitoso con respuesta: ${response.body()}")
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, menu_fisio::class.java)
                startActivity(intent)
            } else {
                // Si hubo un error en el registro, muestra un mensaje y loguea el error
                Log.e(TAG, "onCreate: Fallo en el registro con código: ${response.code()}, mensaje: ${response.message()}")
                Toast.makeText(this, "Fallo en el registro: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
