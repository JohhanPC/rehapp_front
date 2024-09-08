package com.example.rehapp_20.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.rehapp_20.R
import com.example.rehapp_20.Reproductor11
import com.example.rehapp_20.Reproductor12
import com.example.rehapp_20.Reproductor13
import com.example.rehapp_20.Reproductor14
import com.example.rehapp_20.perfil_paciente
import com.example.rehapp_20.utils.Constants

class modulo1 : AppCompatActivity() {

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
        setContentView(R.layout.modulo1)

        // Inicializar PlayerViews y los ExoPlayer
        initializePlayers()

        // Manejo de clicks para pantalla completa (ir a una nueva actividad)
        setupFullScreenNavigation()

        // Navegación a otras actividades
        setupNavigation()
    }

    private fun initializePlayers() {

        val urlVideos = Constants.BASE_URL
        // Video 1
        playerView1 = findViewById(R.id.video_ejercicio1_modulo1)
        player1 = ExoPlayer.Builder(this).build()
        playerView1.player = player1
        val mediaItem1 = MediaItem.fromUri(urlVideos + "video_1.1.mp4")
        player1.setMediaItem(mediaItem1)
        player1.prepare()

        // Video 2
        playerView2 = findViewById(R.id.video_ejercicio2_modulo1)
        player2 = ExoPlayer.Builder(this).build()
        playerView2.player = player2
        val mediaItem2 = MediaItem.fromUri(urlVideos + "video_1.2.mp4")
        player2.setMediaItem(mediaItem2)
        player2.prepare()

        // Video 3
        playerView3 = findViewById(R.id.video_ejercicio3_modulo1)
        player3 = ExoPlayer.Builder(this).build()
        playerView3.player = player3
        val mediaItem3 = MediaItem.fromUri(urlVideos + "video_1.3.mp4")
        player3.setMediaItem(mediaItem3)
        player3.prepare()

        // Video 4
        playerView4 = findViewById(R.id.video_ejercicio4_modulo1)
        player4 = ExoPlayer.Builder(this).build()
        playerView4.player = player4
        val mediaItem4 = MediaItem.fromUri(urlVideos + "video_1.4.mp4")
        player4.setMediaItem(mediaItem4)
        player4.prepare()

        // Click listeners para reproducir/pausar cuando el usuario toque el video
        playerView1.setOnClickListener { togglePlayback(player1) }
        playerView2.setOnClickListener { togglePlayback(player2) }
        playerView3.setOnClickListener { togglePlayback(player3) }
        playerView4.setOnClickListener { togglePlayback(player4) }
    }

    private fun togglePlayback(player: ExoPlayer) {
        if (player.isPlaying) {
            player.pause()
        } else {
            pauseOtherPlayers(player)
            player.play()
        }
    }

    private fun pauseOtherPlayers(currentPlayer: ExoPlayer) {
        if (currentPlayer != player1 && player1.isPlaying) player1.pause()
        if (currentPlayer != player2 && player2.isPlaying) player2.pause()
        if (currentPlayer != player3 && player3.isPlaying) player3.pause()
        if (currentPlayer != player4 && player4.isPlaying) player4.pause()
    }

    private fun setupFullScreenNavigation() {
        // Ejercicio 1 (ir a pantalla completa)
        findViewById<ImageView>(R.id.image_ejercicio1_play).setOnClickListener {
            val intent = Intent(this, Reproductor11::class.java)
            startActivity(intent)
        }

        // Ejercicio 2
        findViewById<ImageView>(R.id.imagemodulo1_play).setOnClickListener {
            val intent = Intent(this, Reproductor12::class.java)
            startActivity(intent)
        }

        // Ejercicio 3
        findViewById<ImageView>(R.id.image_ejercicio3_play).setOnClickListener {
            val intent = Intent(this, Reproductor13::class.java)
            startActivity(intent)
        }

        // Ejercicio 4
        findViewById<ImageView>(R.id.image_ejercicio4_play).setOnClickListener {
            val intent = Intent(this, Reproductor14::class.java)
            startActivity(intent)
        }
    }

    private fun setupNavigation() {
        // Finalizar módulo
        findViewById<ImageView>(R.id.image_finalizar_modulo).setOnClickListener {
            val intent = Intent(this, final_Modulo1::class.java)
            startActivity(intent)
        }

        // Botón de navegación: home
        findViewById<ImageView>(R.id.nav_home).setOnClickListener {
            val intent = Intent(this, MainActivity_Menu_Principal::class.java)
            startActivity(intent)
        }

        // Botón de navegación: calendario
        findViewById<ImageView>(R.id.nav_calendario).setOnClickListener {
            val intent = Intent(this, Calendario::class.java)
            startActivity(intent)
        }

        // Botón de navegación: perfil
        findViewById<ImageView>(R.id.nav_profile).setOnClickListener {
            val intent = Intent(this, perfil_paciente::class.java)
            startActivity(intent)
        }
    }

    // Manejo del ciclo de vida para evitar fugas de memoria
    override fun onPause() {
        super.onPause()
        player1.pause()
        player2.pause()
        player3.pause()
        player4.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player1.release()
        player2.release()
        player3.release()
        player4.release()
    }
}







