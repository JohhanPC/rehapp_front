package com.example.rehapp_20.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.rehapp_20.R

class modulo3: AppCompatActivity() {

    lateinit var playerView: PlayerView
    lateinit var player:ExoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modulo3)

        val txt: ImageView  = findViewById(R.id.imagemodulo_play)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, reproductor:: class.java)
            startActivity(intent)

        }

        val txt1: ImageView  = findViewById(R.id.nav_back)
        txt1.setOnClickListener {

            val intent: Intent = Intent(this, modulo31:: class.java)
            startActivity(intent)



        }

        val txt2: ImageView  = findViewById(R.id.nav_Atras)
        txt2.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_modulo_menu:: class.java)
            startActivity(intent)

        }
        val txt3: ImageView  = findViewById(R.id.nav_home)
        txt3.setOnClickListener {

            val intent: Intent = Intent(this, MainActivity_modulo_menu:: class.java)
            startActivity(intent)

        }

        val txt4: ImageView  = findViewById(R.id.nav_calendario)
        txt4.setOnClickListener {

            val intent: Intent = Intent(this, Calendario:: class.java)
            startActivity(intent)

        }

        val txt5: ImageView  = findViewById(R.id.nav_profile)
        txt5.setOnClickListener {

            val intent: Intent = Intent(this, perfil_usuario:: class.java)
            startActivity(intent)

        }

        playerView=findViewById(R.id.videoBienvenida)
        player=ExoPlayer.Builder(this).build()

        playerView.player=player

        val mediaItem=MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }
    override fun onPause() {
        super.onPause()
        player.pause()
    }
    override fun onResume() {
        super.onResume()
        player.play()
    }
    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}