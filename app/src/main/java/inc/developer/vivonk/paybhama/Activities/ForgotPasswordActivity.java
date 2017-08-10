package inc.developer.vivonk.paybhama.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
    TextInputLayout mMNewPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mProceedToChangePassword = (Button) findViewById(R.id.button_change_password);
        mShowPassword = (CheckBox) findViewById(R.id.checkbox_password);
        mNewPassword = (TextInputEditText) findViewById(R.id.merchant_new_password);
        mMNewPassword = (TextInputLayout) findViewById(R.id.merchant_new_password_input_layout);

        if(getSharedPreferences("user_info",MODE_PRIVATE).getString("language","English").equals("Hindi")){
            mShowPassword.setText(R.string.show_password_hindi);
            mMNewPassword.setHint(getResources().getString(R.string.new_password_hindi));
            mProceedToChangePassword.setText(R.string.change_password_hindi);
        }
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
