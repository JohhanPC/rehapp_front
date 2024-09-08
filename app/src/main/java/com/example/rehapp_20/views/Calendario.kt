package com.example.rehapp_20.views

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.rehapp_20.R
import java.util.Calendar

class Calendario : AppCompatActivity() {
    private val selectedCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita el modo Edge-to-Edge
        setContentView(R.layout.activity_calendario) // Establece el diseño de la actividad

        val BotonNotificacion = findViewById<Button>(R.id.btn_confirmar)
        crearCanal() // Crea el canal de notificaciones si es necesario

        BotonNotificacion.setOnClickListener {
            checkPermisos() // Verifica permisos para notificaciones
        }
    }

    // Verifica si los permisos de notificación están concedidos
    private fun checkPermisos() {
        var PermisoNotificaciones: String = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Verificación de permisos en Android 13 o superior
            PermisoNotificaciones = android.Manifest.permission.POST_NOTIFICATIONS
            if (ContextCompat.checkSelfPermission(this, PermisoNotificaciones) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Sí tiene permisos", Toast.LENGTH_SHORT).show()
                notificacionProgramada(
                    15000,
                    "Notificaciones RehApp",
                    "RehApp",
                    "Tu cita ha sido creada exitosamente, revisa tu app para más información"
                )
                Funciones().NotificacionInstantanea(this, "Instantánea", "Mi texto corto", "Mi texto largo de ejemplo")
            } else {
                Toast.makeText(this, "No tiene permisos", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(this, arrayOf(PermisoNotificaciones), 100)
            }
        } else {
            Toast.makeText(this, "No necesita permiso de notificaciones", Toast.LENGTH_SHORT).show()
            notificacionProgramada(
                15000,
                "Notificaciones RehApp",
                "RehApp",
                "Tu cita ha sido creada exitosamente, revisa tu app para más información"
            )
            Funciones().NotificacionInstantanea(this, "Instantánea", "Mi texto corto", "Mi texto largo de ejemplo")
        }
    }

    // Programa una notificación para que se muestre después de un retraso
    private fun notificacionProgramada(retraso: Long, titulo: String, textoCorto: String, textoLargo: String) {
        val intent = Intent(applicationContext, AlarmaNotificacion::class.java).apply {
            putExtra("Titulo", titulo)
            putExtra("TextoCorto", textoCorto)
            putExtra("TextoLargo", textoLargo)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            AlarmaNotificacion.NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + retraso, pendingIntent)
    }

    // Crea un canal de notificaciones para Android O y superior
    fun crearCanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                AlarmaNotificacion.CHANNEL_ID,
                "Ejemplo",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("StringFormatMatches")
    fun onClickScheduledDate(view: View) {
        val etScheduledDate = findViewById<EditText>(R.id.btn_go_to_calendario)

        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener { _, y, m, d ->
            selectedCalendar.set(y, m, d)
            etScheduledDate.setText(
                resources.getString(
                    R.string.date_format,
                    y,
                    (m + 1).twoDigits(),
                    d.twoDigits()
                )
            )
        }
        val datePickerDialog = DatePickerDialog(this, listener, year, month, dayOfMonth)
        val datePicker = datePickerDialog.datePicker
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        datePicker.minDate = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_MONTH, 29)
        datePicker.maxDate = calendar.timeInMillis

        datePickerDialog.show()

        val spinnerHours = findViewById<Spinner>(R.id.spinner_horas)
        val optionsHours = arrayOf(
            "8:00 a.m.", "9:00 a.m.", "10:00 a.m.", "11:00 a.m.", "12:00 p.m.", "1:00 p.m.",
            "2:00 p.m.", "3:00 p.m.", "4:00 p.m.", "5:00 p.m."
        )
        spinnerHours.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, optionsHours)

        val spinnerFisio = findViewById<Spinner>(R.id.spinner_fisio)
        val optionsFisio = arrayOf("Fisioterapeuta 1", "Fisioterapeuta 2", "Fisioterapeuta 3", "Fisioterapeuta 4")
        spinnerFisio.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, optionsFisio)

        val spinnerModulo = findViewById<Spinner>(R.id.spinner_modulo)
        val optionsModulo = arrayOf(
            "Rehabilitación fractura de muñeca", "Ejercicios síndrome túnel carpiano",
            "Ejercicios mejora dedo en gatillo", "Artrosis mano y dedos", "Extensión del dedo"
        )
        spinnerModulo.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, optionsModulo)

        val btnConfirm = findViewById<Button>(R.id.btn_confirmar)
        val btnNext = findViewById<Button>(R.id.btn_crear_cita)
        val cvConfirm = findViewById<CardView>(R.id.cv_crear_cita)
        val cvResume = findViewById<CardView>(R.id.cv_resumen)

        btnNext.setOnClickListener {
            showAppointmentDataToConfirm()
            cvConfirm.visibility = View.GONE
            cvResume.visibility = View.VISIBLE
        }

        btnConfirm.setOnClickListener {
            Toast.makeText(applicationContext, "Cita creada exitosamente!!", Toast.LENGTH_SHORT).show()
            finish() // Finaliza la actividad al confirmar
        }
    }

    // Muestra los datos de la cita en la vista de resumen
    private fun showAppointmentDataToConfirm() {
        val tvConfirmModule = findViewById<TextView>(R.id.tv_resumen_modulo)
        val tvConfirmFisio = findViewById<TextView>(R.id.tv_resumen_fisio)
        val tvConfirmFecha = findViewById<TextView>(R.id.tv_resumen_fecha)
        val tvConfirmHora = findViewById<TextView>(R.id.tv_resumen_hora)

        val etScheduledDate = findViewById<EditText>(R.id.btn_go_to_calendario)
        val spinnerHora = findViewById<Spinner>(R.id.spinner_horas)
        val spinnerModule = findViewById<Spinner>(R.id.spinner_modulo)
        val spinnerFisioterapeuta = findViewById<Spinner>(R.id.spinner_fisio)

        tvConfirmFecha.text = etScheduledDate.text.toString()
        tvConfirmFisio.text = spinnerFisioterapeuta.selectedItem.toString()
        tvConfirmModule.text = spinnerModule.selectedItem.toString()
        tvConfirmHora.text = spinnerHora.selectedItem.toString()
    }

    // Extiende la clase Int para convertir números en formato de dos dígitos
    private fun Int.twoDigits() =
        if (this >= 10) this.toString() else "0$this"

    // Muestra un diálogo de confirmación al intentar salir de la actividad
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        // Crea un AlertDialog para confirmar si el usuario desea salir
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Estás seguro que deseas salir?")
        builder.setMessage("Si abandonas el registro, los datos ingresados se perderán.")
        builder.setPositiveButton("Salir") { _, _ ->
            finish() // Finaliza la actividad si el usuario elige salir

        }
        builder.setNegativeButton("Continuar"){dialog, _ ->
            dialog.dismiss()

        }

        val dialog = builder.create()
        dialog.show()

    }

}





