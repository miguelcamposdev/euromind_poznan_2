package com.miguelcr.a02_tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewGameActivity extends AppCompatActivity {
    EditText player1Name, player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        player1Name = (EditText) findViewById(R.id.edit_text_player1);
        player2Name = (EditText) findViewById(R.id.edit_text_player2);

    }

    public void startNewGame(View view) {
        String name1 = player1Name.getText().toString();
        String name2 = player2Name.getText().toString();

        Intent intent = new Intent(NewGameActivity.this,MainActivity.class);
        intent.putExtra("player1",name1);
        intent.putExtra("player2",name2);
        startActivity(intent);
    }
}
