package com.miguelcr.a01_relativefacebookmessenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avatar = (ImageView) findViewById(R.id.imageViewAvatar);

        Picasso.with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                .resize(100, 100)
                .into(avatar);
    }
}
