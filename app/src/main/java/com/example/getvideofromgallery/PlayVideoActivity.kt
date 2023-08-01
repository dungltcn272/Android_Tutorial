package com.example.getvideofromgallery

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView


class PlayVideoActivity : AppCompatActivity() {
    private lateinit var playerView: PlayerView
    private lateinit var player: SimpleExoPlayer
    private lateinit var pathVideo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)
        pathVideo = intent.getStringExtra("path_video").toString()

        playerView = findViewById(R.id.playerView)
        initializePlayer()
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(this).build()
        playerView.player = player

        // Đường dẫn video của bạn
        val videoUrl = pathVideo

        val videoUri = Uri.parse(videoUrl)
        val mediaItem = MediaItem.fromUri(videoUri)

        player.setMediaItem(mediaItem)
        player.prepare()
        player.pause()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun releasePlayer() {
        player.release()
    }
}
