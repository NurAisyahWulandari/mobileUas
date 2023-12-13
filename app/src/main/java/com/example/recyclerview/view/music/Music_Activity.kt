package com.example.recyclerview.view.music

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.R

class music_Activity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private var isPaused = false
    private var pausedPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        val audioId = intent.getIntExtra("shadow", -1)
        mediaPlayer = MediaPlayer.create(this, audioId)

        val playButton: Button = findViewById(R.id.btn_play_audio)

        playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                if (isPaused) {
                    // Jika sedang di-pause, lanjutkan dari posisi terakhir
                    mediaPlayer.seekTo(pausedPosition)
                    mediaPlayer.start()
                    isPaused = false
                } else {
                    // Jika baru dimulai, mulai dari awal
                    mediaPlayer.start()
                }
                playButton.text = "Pause Audio"
            } else {
                // Jika sedang berjalan, pause dan simpan posisi
                mediaPlayer.pause()
                playButton.text = "Play Audio"
                isPaused = true
                pausedPosition = mediaPlayer.currentPosition
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
