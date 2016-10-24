package com.miguelcr.a02_completenotelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.miguelcr.a02_completenotelist.database.DaoSession;
import com.miguelcr.a02_completenotelist.database.User;
import com.miguelcr.a02_completenotelist.database.UserDao;

public class RegisterActivity extends AppCompatActivity {
    DaoSession connection;
    EditText editTextName, editTextEmail,
            editTextPass1, editTextPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = (EditText) findViewById(R.id.new_name);
        editTextEmail = (EditText) findViewById(R.id.new_email);
        editTextPass1 = (EditText) findViewById(R.id.new_password);
        editTextPass2 = (EditText) findViewById(R.id.new_password_repeat);

    }

    public void doSignUp(View view) {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String pass1 = editTextPass1.getText().toString();
        String pass2 = editTextPass2.getText().toString();

        // Check if all the information is ok

        if(name.isEmpty()) {
            editTextName.setError("Write your name");
        } else if (email.isEmpty()) {
            editTextEmail.setError("Write your email");
        } else if(pass1.isEmpty()) {
            editTextPass1.setError("Write a password");
        } else if(pass2.isEmpty()) {
            editTextPass2.setError("Repeat the password");
        } else if(!pass1.equals(pass2)) {
            editTextPass2.setError("Write the same password");
        } else {
            // Save the user info in the database because is ok

            // Create the database connection
            connection = DatabaseConnection.getConnection(this);
            UserDao userManager = connection.getUserDao();

            // Create a new User object
            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPassword(pass1);

            // Save the new User object in database
            userManager.insert(newUser);

            Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show();

            // Destroy this RegisterActivity to return to MainActivity
            // where we have the login form
            finish();
        }

    }
}
