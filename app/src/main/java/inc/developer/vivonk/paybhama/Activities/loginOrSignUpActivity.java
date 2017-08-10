package inc.developer.vivonk.paybhama.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.dashboard.DashBoard;

public class loginOrSignUpActivity extends AppCompatActivity {
    TextInputEditText mTiePassword;
    TextInputLayout mTilPassword, mTilMobileNumber;
    EditText mMobileNumber;
    CheckBox mCheckPassword;
    boolean mLanguageHindi=false;
    Button mButtonLogin,mButtonForgotPassword,mButtonSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_signup);

        mTilMobileNumber = (TextInputLayout) findViewById(R.id.merchant_mobile_number_input_layout);
        mTilPassword = (TextInputLayout) findViewById(R.id.merchant_password_input_layout);

        mTiePassword = (TextInputEditText) findViewById(R.id.merchant_password);
        mCheckPassword = (CheckBox) findViewById(R.id.checkbox_password);

        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonForgotPassword = (Button) findViewById(R.id.button_forgot_password);
        mButtonSignUp = (Button) findViewById(R.id.button_sign_up);

        mMobileNumber=(EditText)findViewById(R.id.merchant_mobile_number);
        mCheckPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if(!isChecked){
                    mTiePassword.setTransformationMethod(new PasswordTransformationMethod());
                    mTiePassword.setSelection(mTiePassword.getText().toString().length());
                }
                else {
                    mTiePassword.setTransformationMethod(null);
                    mTiePassword.setSelection(mTiePassword.getText().toString().length());

                }
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mMobileNumber.length()!=10){
                    Toast.makeText(loginOrSignUpActivity.this, "Please enter correct phone number", Toast.LENGTH_SHORT).show();
                }else if(mTiePassword.getText().toString().trim().length()!=mTiePassword.getText().toString().length()){
                    Toast.makeText(loginOrSignUpActivity.this, "Password cannot include white space", Toast.LENGTH_SHORT).show();
                }else  if(mTiePassword.length()<8){
                    Toast.makeText(loginOrSignUpActivity.this, "Please type at least 8 digit password", Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(getApplicationContext(), DashBoard.class));
                }
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
        if(getSharedPreferences("user_info",MODE_PRIVATE).getString("language","English").equals("Hindi")){
            mLanguageHindi = true;
            mTilMobileNumber.setHint(getResources().getString(R.string.mobile_number_hindi));
            mTilPassword.setHint(getResources().getString(R.string.password_hindi));
            mButtonLogin.setText(R.string.login_hindi);
            mButtonSignUp.setText(R.string.sign_up_hindi);
            mButtonForgotPassword.setText(R.string.forgot_password_hindi);
            mCheckPassword.setText(R.string.show_password_hindi);
        }
    }
}
