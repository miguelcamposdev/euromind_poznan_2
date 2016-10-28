package com.miguelcr.a01_duckhunt;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguelcr.a01_duckhunt.database.DaoSession;
import com.miguelcr.a01_duckhunt.database.DatabaseConnection;
import com.miguelcr.a01_duckhunt.database.User;
import com.miguelcr.a01_duckhunt.database.UserDao;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    Long id;
    String nick;
    TextView textViewNick, textViewDuck, textViewTimer;
    ImageView duck;
    int counter = 0;
    Random random;
    boolean gameOver = false;

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

        startCountDownTimer();

    }

    private void startCountDownTimer() {
        // Countdown
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(millisUntilFinished / 1000+"s");
            }

            public void onFinish() {
                textViewTimer.setText("game over!");
                gameOver = true;
                showGameDialog();
            }
        }.start();
    }

    private void showGameDialog() {
        // Save the new score
        DaoSession connection = DatabaseConnection.getConnection(this);
        UserDao userManager = connection.getUserDao();

        // Search the user in the database
        User u = userManager.load(id);

        if(u.getScore()<counter) {
            u.setScore(counter);
            userManager.update(u);
        }

        // Show the dialog to Restart game or Show Ranking
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("GAME OVER. What do you want to do?");

        // Add the buttons
        builder.setPositiveButton("Show Ranking", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                Intent i = new Intent(GameActivity.this, RankingUserActivity.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Play again", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                counter = 0;
                textViewDuck.setText("0 ducks");
                gameOver = false;
                startCountDownTimer();
            }
        });

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void duckClicked(View view) {

        if(!gameOver) {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int maxHeight = displaymetrics.heightPixels - duck.getHeight();
            int maxWidth = displaymetrics.widthPixels - duck.getWidth();

            int xPosition = random.nextInt(maxWidth - 0 + 1) + 0;
            int yPosition = random.nextInt(maxHeight - 0 + 1) + 0;

            duck.setX(xPosition);
            duck.setY(yPosition);

            counter++;
            textViewDuck.setText(String.valueOf(counter));
        }
    }
}
