package com.example.smilingface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    private EditText email, pass;
    private Button signin;
    private FirebaseAuth fAuth;
    private TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        fAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.sgInEmail);
        pass=findViewById(R.id.sigInPass);
        signin=findViewById(R.id.btnSign);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString().trim();
                String p = pass.getText().toString().trim();
                if (TextUtils.isEmpty(e)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                fAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignIn.this,Finish.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(),"Đăng nhập không thành công!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });
        signup=findViewById(R.id.chanceRegis);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityRegister = new Intent(SignIn.this,Register.class);
                startActivity(activityRegister);
            }
        });
    }
}