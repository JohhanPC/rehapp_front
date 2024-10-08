package com.example.rehapp_20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.rehapp_20.R
import com.example.rehapp_20.utils.Constants

class Reproductor13: AppCompatActivity() {

    lateinit var playerView:PlayerView
    lateinit var player:ExoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        val urlVideos = Constants.BASE_URL
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor13)



        playerView=findViewById(R.id.videoModulo1)
        player=ExoPlayer.Builder(this).build()

        playerView.player=player

        val mediaItem=MediaItem.fromUri(urlVideos + "video_1.3.mp4")

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
