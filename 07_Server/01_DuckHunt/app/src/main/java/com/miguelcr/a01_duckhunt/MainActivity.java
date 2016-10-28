package com.miguelcr.a01_duckhunt;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcr.a01_duckhunt.database.DaoSession;
import com.miguelcr.a01_duckhunt.database.DatabaseConnection;
import com.miguelcr.a01_duckhunt.database.User;
import com.miguelcr.a01_duckhunt.database.UserDao;

public class MainActivity extends AppCompatActivity {
    EditText editTextNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNick = (EditText) findViewById(R.id.nick);
    }

    public void startGame(View view) {
        String nick = editTextNick.getText().toString();

        if (nick.isEmpty()) {
            editTextNick.setError("Write a nick name");
        } else {
            new LoginTask().execute(nick);
        }

    }

    public void showRanking(View view) {
        Intent i = new Intent(this, RankingUserActivity.class);
        startActivity(i);
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String nickname = params[0];
            String urlRequestLogin = "http://rest.miguelcr.com/killduck/register?nickname=" + nickname;
            String result = Util.getResultadoUrl(urlRequestLogin);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null) {
                Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
            }
        }

    }
}
