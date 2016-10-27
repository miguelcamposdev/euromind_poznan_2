package com.miguelcr.a01_duckhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    Long id;
    String nick;
    TextView textViewNick, textViewDuck, textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewNick = (TextView) findViewById(R.id.text_view_nick);
        textViewDuck = (TextView) findViewById(R.id.text_view_score);
        textViewTimer = (TextView) findViewById(R.id.text_view_countdown);

        // get the intent information
        Bundle extras = getIntent().getExtras();

        id = extras.getLong("userId");
        nick = extras.getString("nick");

        textViewNick.setText(nick);
    }
}
