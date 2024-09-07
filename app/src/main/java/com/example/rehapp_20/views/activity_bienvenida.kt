package com.example.rehapp_20.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.rehapp_20.R

class activity_bienvenida : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)



        val txt: Button=findViewById(R.id.button_comencemos)
        txt.setOnClickListener {

            val intent: Intent = Intent(this, activity_inicio_sesion  :: class.java)
            startActivity(intent)

    }

        playerView=findViewById(R.id.videoBienvenida)
        player=ExoPlayer.Builder(this).build()

        playerView.player=player

        val mediaItem= MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")

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

