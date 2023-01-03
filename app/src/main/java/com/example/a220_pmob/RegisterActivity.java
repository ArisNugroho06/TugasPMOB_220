package com.example.a220_pmob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a220_pmob.database.UserDatabase;
import com.example.a220_pmob.database.dao.UserDao;
import com.example.a220_pmob.database.entity.User;

public class RegisterActivity extends AppCompatActivity {

    EditText nimReg, nameReg, passReg;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nimReg = findViewById(R.id.nim_register);
        nameReg = findViewById(R.id.nama_register);
        passReg = findViewById(R.id.password_register);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create user entity
                User user = new User();

                user.setNim(nimReg.getText().toString());
                user.setName(nameReg.getText().toString());
                user.setPassword(passReg.getText().toString());

                if(validateInput(user)) {
                    // operasi insert
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // register
                            userDao.registerUser(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Register Successful!" ,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();

                }else{
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validateInput(User user) {
        if(user.getName().isEmpty() || user.getNim().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}