package com.iris.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText EtPassword, EtUser;
    Button BtnLogin;
    TextView TxtConteo;
    CountDownTimer countDownTimer;
    CheckBox GuardarCredChk;
    int intentos = 0;
    CheckBox VerPassChk;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EtUser = findViewById(R.id.EtUser);
        EtPassword = findViewById(R.id.EtPassword);
        BtnLogin = findViewById(R.id.BtnLogin);
        TxtConteo = findViewById(R.id.TxtConteo);
        GuardarCredChk= findViewById(R.id.GuardarCredChk);

        sharedPreferences = getSharedPreferences("com.iris.login", MODE_PRIVATE);


        String saveUser=sharedPreferences.getString("user",null);
        String savePass=sharedPreferences.getString("pass",null);
        if(saveUser!=null && savePass!=null){
            EtUser.setText(saveUser);
            EtPassword.setText(savePass);
            GuardarCredChk.setChecked(true);

        }

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarSeccion();
            }
        });
        GuardarCredChk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    String user = EtUser.getText().toString();
                    String pass = EtPassword.getText().toString();
                    if(!user.isEmpty() && !pass.isEmpty()){
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("user",user);
                        editor.putString("pass",pass);
                        editor.apply();
                    }else{
                        Toast.makeText(MainActivity.this, "Favor ingresar credenciales para guardar",Toast.LENGTH_SHORT).show();
                        GuardarCredChk.setChecked(false);
                    }
                }else{
                    limpiarCredenciales();
                }

            }
        });

    }
    private void limpiarCredenciales() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("user");
        editor.remove("pass");
        editor.apply();
    }
    private void IniciarSeccion() {
        String user = EtUser.getText().toString();
        String pass = EtPassword.getText().toString();


        if ("iris".equals(user) && "iris123".equals(pass)) {
            Toast.makeText(this, "Inicio de Sesión exitoso!", Toast.LENGTH_SHORT).show();
            intentos = 0;

            //home activity2//
            Intent intent= new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("usuario",user);

            startActivity(intent);
        } else {
            intentos++;
            if (intentos >= 3) {

                Toast.makeText(this, "Se supero el numero de intentos", Toast.LENGTH_SHORT).show();
                BtnLogin.setEnabled(false);
                IniciarConteo();
            } else {
                Toast.makeText(this, "Usuario o Contraseña es incorrectos.  Intentos" + intentos + " de 3", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void IniciarConteo() {

        TxtConteo.setVisibility(View.VISIBLE);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TxtConteo.setText(String.valueOf(millisUntilFinished / 1000) + "s restantes");

            }

            @Override
            public void onFinish() {
                TxtConteo.setVisibility(View.GONE);
                BtnLogin.setEnabled(true);
                intentos= 0;
            }
        }.start();
    }

}