package inc.developer.vivonk.paybhama.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import inc.developer.vivonk.paybhama.R;

/**
 * Created by vivonk on 08-08-2017.
 */

public class OTPActivity extends Activity {
    Button mProceedToChangePassword;
    TextInputLayout mMOtp;
    TextView tvOtp;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mProceedToChangePassword = (Button) findViewById(R.id.button_next_change_password);
        mMOtp = (TextInputLayout) findViewById(R.id.merchant_otp_input_layout);

        tvOtp = (TextView) findViewById(R.id.tv_otp);

        if(getSharedPreferences("user_info",MODE_PRIVATE).getString("language","English").equals("Hindi")){
            mProceedToChangePassword.setText(R.string.change_password_hindi);
            mMOtp.setHint(getResources().getString(R.string.otp_hindi));
            tvOtp.setText(R.string.otp_text_view_hindi);
        }
        //update is acauilavle
        mProceedToChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
            }
        });
    }
}
