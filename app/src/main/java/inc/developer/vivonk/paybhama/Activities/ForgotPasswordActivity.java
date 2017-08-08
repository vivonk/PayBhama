package inc.developer.vivonk.paybhama.Activities;

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

public class ForgotPasswordActivity extends AppCompatActivity {
    CheckBox mShowPassword;
    Button mProceedToChangePassword;
    TextInputEditText mNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mProceedToChangePassword = (Button) findViewById(R.id.button_change_password);
        mShowPassword = (CheckBox) findViewById(R.id.checkbox_password);
        mNewPassword = (TextInputEditText) findViewById(R.id.merchant_new_password);
        mProceedToChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DashBoard.class));
            }
        });
        mShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if(!isChecked){
                    mNewPassword.setTransformationMethod(new PasswordTransformationMethod());
                    mNewPassword.setSelection(mNewPassword.getText().toString().length());
                }
                else {
                    mNewPassword.setTransformationMethod(null);
                    mNewPassword.setSelection(mNewPassword.getText().toString().length());

                }
            }
        });
    }
}
