package com.example.prj_hoangnguyenphuongkhanhcau01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewyoutube, imageViewhalome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectViews();
        AddEvents();
    }

    void AddEvents() {
        imageViewyoutube.setOnClickListener(new View.OnClickListener() {
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
        imageViewhalome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPageHalome();
            }

            void OpenPageHalome() {
                Intent intentHalome = new Intent();
                intentHalome.setAction(Intent.ACTION_VIEW);
                intentHalome.setData(Uri.parse("https://about.halome.com/"));
                startActivity(intentHalome);
            }
        });
    }

    void ConnectViews() {
        imageViewyoutube = (ImageView) findViewById(R.id.imageYoutubeId);
        imageViewhalome = (ImageView) findViewById(R.id.imageHalomeId);
    }

}