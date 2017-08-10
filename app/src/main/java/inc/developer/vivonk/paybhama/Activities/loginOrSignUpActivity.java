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

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.dashboard.DashBoard;

public class loginOrSignUpActivity extends AppCompatActivity {
    TextInputEditText mPassword;
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

        mPassword = (TextInputEditText) findViewById(R.id.merchant_password);
        mCheckPassword = (CheckBox) findViewById(R.id.checkbox_password);

        mButtonLogin = (Button) findViewById(R.id.button_login);
        mButtonForgotPassword = (Button) findViewById(R.id.button_forgot_password);
        mButtonSignUp = (Button) findViewById(R.id.button_sign_up);

        mMobileNumber=(EditText)findViewById(R.id.merchant_mobile_number);
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
