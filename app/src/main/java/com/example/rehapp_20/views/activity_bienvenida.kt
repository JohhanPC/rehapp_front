package com.example.rehapp_20.views

// Importaciones necesarias para el funcionamiento de la actividad
import android.content.Intent  // Permite cambiar de actividad
import androidx.appcompat.app.AppCompatActivity  // Actividad base compatible con versiones anteriores de Android
import android.os.Bundle  // Contiene el estado de la actividad
import android.widget.Button  // Para trabajar con botones
import androidx.media3.common.MediaItem  // Representa un archivo de medios que se reproducirá
import androidx.media3.exoplayer.ExoPlayer  // Reproductor de medios ExoPlayer
import androidx.media3.ui.PlayerView  // Vista en la que se reproducen los medios
import com.example.rehapp_20.R  // Acceso a los recursos como layouts y strings

// Definición de la clase de la actividad llamada activity_bienvenida, que hereda de AppCompatActivity
class activity_bienvenida : AppCompatActivity() {

    // Variables privadas para la vista del reproductor y el reproductor de video
    private lateinit var playerView: PlayerView  // Referencia al PlayerView del layout
    private lateinit var player: ExoPlayer  // Objeto ExoPlayer para manejar la reproducción de video

    // Método onCreate, que es llamado cuando la actividad se inicia
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout de la actividad a activity_bienvenida.xml
        setContentView(R.layout.activity_bienvenida)

        // Busca el botón en el layout usando su ID y lo asigna a la variable txt
        val txt: Button = findViewById(R.id.button_comencemos)
        // Define lo que sucede cuando se hace clic en el botón
        txt.setOnClickListener {
            // Crea un Intent para iniciar la actividad activity_inicio_sesion cuando el botón es presionado
            val intent: Intent = Intent(this, activity_inicio_sesion::class.java)
            // Inicia la nueva actividad
            startActivity(intent)
        }

        // Busca el PlayerView en el layout usando su ID y lo asigna a playerView
        playerView = findViewById(R.id.videoBienvenida)
        // Construye una nueva instancia de ExoPlayer
        player = ExoPlayer.Builder(this).build()

        // Asigna el reproductor ExoPlayer al PlayerView para que se muestre en pantalla
        playerView.player = player

        // Crea un MediaItem con la URL del video que se va a reproducir
        val mediaItem = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        // Asigna el MediaItem al reproductor
        player.setMediaItem(mediaItem)
        // Prepara el reproductor para la reproducción
        player.prepare()
        // Inicia la reproducción automáticamente
        player.play()
    }

    // Método que se ejecuta cuando la actividad entra en pausa (por ejemplo, si el usuario cambia de aplicación)
    override fun onPause() {
        super.onPause()
        // Pausa la reproducción del video
        player.pause()
    }

    // Método que se ejecuta cuando la actividad vuelve a estar activa (en primer plano)
    override fun onResume() {
        super.onResume()
        // Reanuda la reproducción del video
        player.play()
    }

    // Método que se ejecuta cuando la actividad es destruida (por ejemplo, cuando el usuario cierra la aplicación)
    override fun onDestroy() {
        super.onDestroy()
        // Libera los recursos del reproductor para evitar fugas de memoria
        player.release()
    }
}


