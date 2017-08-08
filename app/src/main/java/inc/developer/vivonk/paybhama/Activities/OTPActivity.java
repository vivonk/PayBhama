package inc.developer.vivonk.paybhama.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import inc.developer.vivonk.paybhama.R;

/**
 * Created by vivonk on 08-08-2017.
 */

public class OTPActivity extends Activity {
    Button mProceedToChangePassword;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mProceedToChangePassword = (Button) findViewById(R.id.button_next_change_password);

        mProceedToChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
            }
        });
    }
}
