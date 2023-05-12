package com.example.mediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
private Button Play;
private Button Pause;
private SeekBar seekBar;
private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        Play = findViewById(R.id.Play);
        Pause = findViewById(R.id.Pause);
        //media playing using local source
//        mediaPlayer = MediaPlayer.create(this,R.raw.music1);
        //media playing from remote source

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://paglasongs.com/files/download/id/2094");
        } catch (IOException e) {
            e.printStackTrace();
           // http://penguinradio.dominican.edu/Sound%20FX%20Collection/101301Motorcycle%202.wav
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "Ready to play", Toast.LENGTH_SHORT).show();
                mp.start();

              seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser) {
                            mediaPlayer.seekTo(progress);
                        }
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });
        mediaPlayer.prepareAsync();

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mediaPlayer.start();
            }
        });
        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
    }
}