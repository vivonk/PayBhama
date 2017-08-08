package inc.developer.vivonk.paybhama.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.dashboard.DashBoard;

public class loginOrSignUpActivity extends AppCompatActivity {
    TextInputEditText mPassword;
    CheckBox mCheckPassword;
    Button mButtonLogin,mButtonForgotPassword,mButtonSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_signup);

        mPassword = (TextInputEditText) findViewById(R.id.merchant_password);
        mCheckPassword = (CheckBox) findViewById(R.id.checkbox_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonForgotPassword = (Button) findViewById(R.id.button_forgot_password);
        mButtonSignUp = (Button) findViewById(R.id.button_sign_up);

        mCheckPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if(!isChecked){
                    mPassword.setTransformationMethod(new PasswordTransformationMethod());
                    mPassword.setSelection(mPassword.getText().toString().length());
                }
                else {
                    mPassword.setTransformationMethod(null);
                    mPassword.setSelection(mPassword.getText().toString().length());

                }
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DashBoard.class));
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });

        mButtonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),OTPActivity.class));
            }
        });

    }
}
