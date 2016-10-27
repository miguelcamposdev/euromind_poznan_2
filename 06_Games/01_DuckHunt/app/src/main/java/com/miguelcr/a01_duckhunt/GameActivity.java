package com.miguelcr.a01_duckhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    Long id;
    String nick;
    TextView textViewNick, textViewDuck, textViewTimer;
    ImageView duck;
    int counter = 0;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Create the Random object
        random = new Random();

        textViewNick = (TextView) findViewById(R.id.text_view_nick);
        textViewDuck = (TextView) findViewById(R.id.text_view_score);
        textViewTimer = (TextView) findViewById(R.id.text_view_countdown);
        duck = (ImageView) findViewById(R.id.imageViewDuck);

        // get the intent information
        Bundle extras = getIntent().getExtras();

        id = extras.getLong("userId");
        nick = extras.getString("nick");

        textViewNick.setText(nick);
    }

    public void duckClicked(View view) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int maxHeight = displaymetrics.heightPixels - duck.getHeight();
        int maxWidth = displaymetrics.widthPixels - duck.getWidth();

        int xPosition = random.nextInt(maxWidth - 0 + 1) + 0;
        int yPosition = random.nextInt(maxHeight - 0 + 1) + 0;

        duck.setX(xPosition);
        duck.setY(yPosition);

        counter++;
        textViewDuck.setText(counter+" ducks");
    }
}
