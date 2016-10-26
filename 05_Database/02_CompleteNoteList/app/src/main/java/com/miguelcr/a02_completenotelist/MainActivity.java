package com.miguelcr.a02_completenotelist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.miguelcr.a02_completenotelist.database.DaoSession;
import com.miguelcr.a02_completenotelist.database.User;
import com.miguelcr.a02_completenotelist.database.UserDao;

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail ,editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPass = (EditText) findViewById(R.id.password);
    }

    public void goToSignUp(View view) {
        // Launch the RegisterActivity
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void doLogin(View view) {
        String email = editTextEmail.getText().toString();
        String pass = editTextPass.getText().toString();

        if(email.isEmpty()) {
            editTextEmail.setError("Write an email");
        } else {
            if (pass.isEmpty()) {
                editTextPass.setError("Write a valid password");
            } else {
                DaoSession connection = DatabaseConnection.getConnection(this);
                UserDao userManager = connection.getUserDao();

                // Search with userManager if exist one User with this email and pass
                // SELECT * FROM User WHERE email='John' AND password='1234'
                User u = userManager.queryBuilder()
                        .where(UserDao.Properties.Email.eq(email),
                                UserDao.Properties.Password.eq(pass)
                        ).unique();

                if (u != null) {
                    Toast.makeText(this, "Login ok", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Login wrong", Toast.LENGTH_SHORT).show();
                }
            }
            }
        }
}
