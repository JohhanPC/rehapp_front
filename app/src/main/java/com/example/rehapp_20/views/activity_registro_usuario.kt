package com.example.rehapp_20.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.rehapp_20.R
import com.example.rehapp_20.enums.UserType
import com.example.rehapp_20.models.PatientUserRegisterDTO
import com.example.rehapp_20.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class activity_registro_usuario : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private val TAG = "activity_registro_usuario"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        val buttonRegistrarme: Button = findViewById(R.id.ButtonRegistroExitoso)
        buttonRegistrarme.setOnClickListener {
            val email = findViewById<EditText>(R.id.textView_Correo_registro).text.toString()
            val password = findViewById<EditText>(R.id.textView_Contraseña_registro).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.textView_Confirmacion_registro).text.toString()

            Log.d(TAG, "onCreate: Click en 'Registrarme' con email: $email")

            if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                val user = PatientUserRegisterDTO(
                    email = email,
                    userName = findViewById<EditText>(R.id.textView_Nombre_usuario).text.toString(),
                    password = password,
                    userType = UserType.PATIENT
                )
                Log.d(TAG, "onCreate: Enviando DTO para registro: $user")
                userViewModel.registerUser(user)
            } else {
                Log.w(TAG, "onCreate: Fallo en la validación del formulario")
                Toast.makeText(this, "Por favor, verifica los campos y asegúrate de que las contraseñas coincidan.", Toast.LENGTH_SHORT).show()
            }
        }

        // Observa el resultado del registro
        userViewModel.registrationResult.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d(TAG, "onCreate: Registro exitoso con respuesta: ${response.body()}")
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, activity_encuesta1::class.java)
                startActivity(intent)
            } else {
                Log.e(TAG, "onCreate: Fallo en el registro con código: ${response.code()}, mensaje: ${response.message()}")
                Toast.makeText(this, "Fallo en el registro: ${response.message()}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
