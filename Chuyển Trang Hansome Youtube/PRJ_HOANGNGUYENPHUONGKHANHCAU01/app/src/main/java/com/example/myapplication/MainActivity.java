package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewYoutube, imageViewHalome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectViews();
        AddEvents();
    }

    void ConnectViews() {
        imageViewYoutube = (ImageView) findViewById(R.id.imageYoutubeId);
        imageViewHalome = (ImageView) findViewById(R.id.imageHalomeId);
    }

    void AddEvents() {
        imageViewYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPageYoutube();
            }

            void OpenPageYoutube() {
                Intent intentYoutube = new Intent();
                intentYoutube.setAction(Intent.ACTION_VIEW);
                intentYoutube.setData(Uri.parse("https://www.youtube.com/"));
                startActivity(intentYoutube);
            }
        });
        imageViewHalome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPageHalome();
            }

            private void OpenPageHalome() {
                Intent intentHalome = new Intent();
                intentHalome.setAction(Intent.ACTION_VIEW);
                intentHalome.setData(Uri.parse("https://halome.com/"));
                startActivity(intentHalome);
            }
        });
    }
}