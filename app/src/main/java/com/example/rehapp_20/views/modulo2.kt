package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.rehapp_20.R
import com.example.rehapp_20.Reproductor21
import com.example.rehapp_20.Reproductor23
import com.example.rehapp_20.Reproductor24
import com.example.rehapp_20.views.Calendario
import com.example.rehapp_20.views.MainActivity_Menu_Principal
import com.example.rehapp_20.views.final_modulo2

class modulo2 : AppCompatActivity() {

    // Variables para los PlayerView y ExoPlayer
    private lateinit var playerView1: PlayerView
    private lateinit var playerView2: PlayerView
    private lateinit var playerView3: PlayerView
    private lateinit var playerView4: PlayerView
    private lateinit var player1: ExoPlayer
    private lateinit var player2: ExoPlayer
    private lateinit var player3: ExoPlayer
    private lateinit var player4: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modulo2) // Establece el diseño de la actividad con el layout correspondiente.

        // Configura el botón para finalizar el módulo y navegar a 'final_modulo2'
        val txt: ImageView = findViewById(R.id.image_finalizar_modulo)
        txt.setOnClickListener {
            val intent: Intent = Intent(this, final_modulo2::class.java)
            startActivity(intent)
        }

        // Configura el botón para reproducir el primer video y navegar a 'Reproductor21'
        val txt1: ImageView = findViewById(R.id.image_play1)
        txt1.setOnClickListener {
            val intent: Intent = Intent(this, Reproductor21::class.java)
            startActivity(intent)
        }

        // Configura el botón para reproducir el segundo video y navegar a 'Reproductor23'
        val txt2: ImageView = findViewById(R.id.image_play2)
        txt2.setOnClickListener {
            val intent: Intent = Intent(this, Reproductor23::class.java)
            startActivity(intent)
        }

        // Configura el botón para reproducir el tercer video y navegar a 'Reproductor23'
        val txt3: ImageView = findViewById(R.id.image_play3)
        txt3.setOnClickListener {
            val intent: Intent = Intent(this, Reproductor23::class.java)
            startActivity(intent)
        }

        // Configura el botón para reproducir el cuarto video y navegar a 'Reproductor24'
        val txt4: ImageView = findViewById(R.id.image_play4)
        txt4.setOnClickListener {
            val intent: Intent = Intent(this, Reproductor24::class.java)
            startActivity(intent)
        }

        // Configura el botón de navegación a la pantalla principal
        val txt5: ImageView = findViewById(R.id.nav_home)
        txt5.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity_Menu_Principal::class.java)
            startActivity(intent)
        }

        // Configura el botón de navegación al calendario
        val txt6: ImageView = findViewById(R.id.nav_calendario)
        txt6.setOnClickListener {
            val intent: Intent = Intent(this, Calendario::class.java)
            startActivity(intent)
        }

        // Configura el botón de navegación al perfil del paciente
        val txt7: ImageView = findViewById(R.id.nav_profile)
        txt7.setOnClickListener {
            val intent: Intent = Intent(this, perfil_paciente::class.java)
            startActivity(intent)
        }

        // Inicializa el primer PlayerView y ExoPlayer para el primer video
        playerView1 = findViewById(R.id.video_ejercicio1_modulo2)
        player1 = ExoPlayer.Builder(this).build()
        playerView1.player = player1
        val mediaItem1 = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        player1.setMediaItem(mediaItem1)
        player1.prepare()

        // Inicializa el segundo PlayerView y ExoPlayer para el segundo video
        playerView2 = findViewById(R.id.video_ejercicio2_modulo2)
        player2 = ExoPlayer.Builder(this).build()
        playerView2.player = player2
        val mediaItem2 = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        player2.setMediaItem(mediaItem2)
        player2.prepare()

        // Configura el PlayerView para iniciar la reproducción al hacer clic
        playerView2.setOnClickListener {
            if (!player2.isPlaying) {
                player2.play()
            }
        }

        // Inicializa el tercer PlayerView y ExoPlayer para el tercer video
        playerView3 = findViewById(R.id.video_ejercicio3_modulo2)
        player3 = ExoPlayer.Builder(this).build()
        playerView3.player = player3
        val mediaItem3 = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        player3.setMediaItem(mediaItem3)
        player3.prepare()

        // Configura el PlayerView para iniciar la reproducción al hacer clic
        playerView3.setOnClickListener {
            if (!player3.isPlaying) {
                player3.play()
            }
        }

        // Inicializa el cuarto PlayerView y ExoPlayer para el cuarto video
        playerView4 = findViewById(R.id.video_ejercicio4_modulo2)
        player4 = ExoPlayer.Builder(this).build()
        playerView4.player = player4
        val mediaItem4 = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        player4.setMediaItem(mediaItem4)
        player4.prepare()

        // Configura el PlayerView para iniciar la reproducción al hacer clic
        playerView4.setOnClickListener {
            if (!player4.isPlaying) {
                player4.play()
            }
        }
    }

    // Pausa todos los reproductores cuando la actividad está en pausa
    override fun onPause() {
        super.onPause()
        player1.pause()
        player2.pause()
        player3.pause()
        player4.pause()
    }

    // Método vacío para manejar la reanudación (puedes agregar funcionalidad si es necesario)
    override fun onResume() {
        super.onResume()
    }

    // Libera los recursos de los reproductores cuando la actividad se destruye
    override fun onDestroy() {
        super.onDestroy()
        player1.release()
        player2.release()
        player3.release()
        player4.release()
    }
}

