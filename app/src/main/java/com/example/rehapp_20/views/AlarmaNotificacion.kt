package com.example.rehapp_20.views

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat



class AlarmaNotificacion:BroadcastReceiver() {
    lateinit var Titulo: String
    lateinit var TextoCorto: String
    lateinit var TextoLargo: String

    override fun onReceive(context: Context, intent: Intent) {
        Titulo = intent.getStringExtra("Titulo").toString()
        TextoCorto = intent.getStringExtra("TextoCorto").toString()
        TextoLargo = intent.getStringExtra("TextoLargo").toString()
        programarNotificacion(context)
    }

    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "EJEMPLO"
    }

    public fun programarNotificacion(context: Context) {

        //para abrir la app al dar clic en notificacion

        val intent=Intent(context, Calendario::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag:Int
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            flag=PendingIntent.FLAG_IMMUTABLE
        }
        else{
            flag=0
        }

        val pendingIntent:PendingIntent=PendingIntent.getActivity(context,0,intent,flag)


        var notificacion = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_my_calendar)
            .setContentTitle(Titulo)
            .setContentText(TextoCorto)
            .setStyle(NotificationCompat.BigTextStyle().bigText(TextoLargo))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //para android posterior a 7 se pone en el channel
            .setContentIntent(pendingIntent)
            .build()

        val manager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notificacion)
    }
}