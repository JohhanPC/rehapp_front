package com.example.rehapp_20.views

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rehapp_20.R
import com.example.rehapp_20.models.UserDTO
import com.example.rehapp_20.utils.UserPreferences
import com.example.rehapp_20.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class perfil_usuario : AppCompatActivity() {

    @Inject
    lateinit var userPreferences: UserPreferences

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil_usuario)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recuperar los datos del usuario desde UserPreferences
        val user = userPreferences.getUser()

        user?.let {
            findViewById<TextView>(R.id.texto_correo).text = it.email ?: ""
            findViewById<TextView>(R.id.user_name).text = it.userName ?: ""
            findViewById<EditText>(R.id.edit_edad).setText(it.age?.toString() ?: "")
            findViewById<EditText>(R.id.edit_nombre).setText(it.userName ?: "")
            findViewById<EditText>(R.id.edit_apellido).setText(it.userName ?: "")
            findViewById<EditText>(R.id.edit_ubicacion).setText(it.city ?: "")
            findViewById<EditText>(R.id.edit_numero).setText(it.phoneNumber ?: "")
        }

        // Manejar clic en el botón "Editar"
        findViewById<TextView>(R.id.ButtonEditarUsuario).setOnClickListener {
            user?.let {
                val userName = findViewById<EditText>(R.id.edit_nombre).text?.toString() ?: ""
                val phoneNumber = findViewById<EditText>(R.id.edit_numero).text?.toString() ?: ""
                val city = findViewById<EditText>(R.id.edit_ubicacion).text?.toString() ?: ""
                val age = findViewById<EditText>(R.id.edit_edad).text.toString().toIntOrNull() ?: it.age ?: 0

                val updatedUser = UserDTO(
                    userId = it.userId,
                    userName = userName.takeIf { it.isNotBlank() },
                    password = it.password,
                    identificationNumber = it.identificationNumber?.takeIf { it.isNotBlank() } ?: "0",
                    age = age,
                    sex = it.sex,
                    email = it.email,
                    phoneNumber = phoneNumber.takeIf { it.isNotBlank() },
                    city = city.takeIf { it.isNotBlank() },
                    userType = it.userType
                )

                userViewModel.updateUser(updatedUser)
            }
        }
    }
}