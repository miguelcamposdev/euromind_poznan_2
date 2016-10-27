package com.miguelcr.a01_duckhunt;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        if(nick.isEmpty()) {
            editTextNick.setError("Write a nick name");
        } else {
            DaoSession connection = DatabaseConnection.getConnection(this);
            UserDao userManager = connection.getUserDao();

            // Search id exist one user where nick="..."
            // SELECT * FROM User WHERE nick='$nick'
            User u = userManager.queryBuilder()
                    .where(UserDao.Properties.Nick.eq(nick))
                    .unique();

            if(u==null) {
                u = new User();
                u.setNick(nick);
                userManager.insert(u);
            }

            Intent i = new Intent(this,GameActivity.class);
            i.putExtra("userId",u.getId());
            startActivity(i);
        }


    }
}
