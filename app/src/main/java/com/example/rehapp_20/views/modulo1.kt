package com.example.rehapp_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.rehapp_20.R
import com.example.rehapp_20.views.Calendario
import com.example.rehapp_20.views.MainActivity_Menu_Principal
import com.example.rehapp_20.views.final_Modulo1

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


        val txt: ImageView  = findViewById(R.id.image_finalizar_modulo)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, final_Modulo1 :: class.java)
            startActivity(intent)

        }
        val txt1: ImageView  = findViewById(R.id.image_ejercicio1_play)
        txt1.setOnClickListener {

            val intent: Intent = Intent(this, Reproductor11:: class.java)
            startActivity(intent)


    }
    val txt2: ImageView  = findViewById(R.id.imagemodulo1_play)
    txt2.setOnClickListener {

        val intent: Intent = Intent(this, Reproductor12:: class.java)
        startActivity(intent)

    }
        val txt3: ImageView  = findViewById(R.id.image_ejercicio3_play)
        txt3.setOnClickListener {

            val intent: Intent = Intent(this, Reproductor13:: class.java)
            startActivity(intent)

        }

        val txt4: ImageView  = findViewById(R.id.image_ejercicio4_play)
        txt4.setOnClickListener {

            val intent: Intent = Intent(this, Reproductor14:: class.java)
            startActivity(intent)
        }
        val txt5: ImageView  = findViewById(R.id.nav_home)
        txt5.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_Menu_Principal :: class.java)
            startActivity(intent)
        }

        val txt6: ImageView  = findViewById(R.id.nav_calendario)
        txt6.setOnClickListener {

            val intent: Intent = Intent(this, Calendario:: class.java)
            startActivity(intent)
        }


        val txt7: ImageView  = findViewById(R.id.nav_profile)
        txt7.setOnClickListener {

            val intent: Intent = Intent(this, perfil_paciente:: class.java)
            startActivity(intent)
        }





        // Inicializar el primer PlayerView y ExoPlayer
        playerView1 = findViewById(R.id.video_ejercicio1_modulo1)
        player1 = ExoPlayer.Builder(this).build()
        playerView1.player = player1
        val mediaItem1 = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        player1.setMediaItem(mediaItem1)
        player1.prepare()


        // Inicializar el segundo PlayerView y ExoPlayer
        playerView2 = findViewById(R.id.video_ejercicio2_modulo1)
        player2 = ExoPlayer.Builder(this).build()
        playerView2.player = player2
        val mediaItem2 = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        player2.setMediaItem(mediaItem2)
        player2.prepare()

        playerView2.setOnClickListener {
            if (!player2.isPlaying) {
                player2.play()
            }
        }

        // Inicializar el tercer PlayerView y ExoPlayer
        playerView3 = findViewById(R.id.video_ejercicio3_modulo1)
        player3 = ExoPlayer.Builder(this).build()
        playerView3.player = player3
        val mediaItem3 = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        player3.setMediaItem(mediaItem3)
        player3.prepare()

        playerView3.setOnClickListener {
            if (!player3.isPlaying) {
                player3.play()
            }
        }

        // Inicializar el cuarto PlayerView y ExoPlayer
        playerView4 = findViewById(R.id.video_ejercicio4_modulo1)
        player4 = ExoPlayer.Builder(this).build()
        playerView4.player = player4
        val mediaItem4 = MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        player4.setMediaItem(mediaItem4)
        player4.prepare()

        playerView4.setOnClickListener {
            if (!player4.isPlaying) {
                player4.play()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        player1.pause()
        player2.pause()
        player3.pause()
        player4.pause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        player1.release()
        player2.release()
        player3.release()
        player4.release()
    }
}







