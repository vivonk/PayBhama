package inc.developer.vivonk.paybhama.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import inc.developer.vivonk.paybhama.R;

public class MoneyVerifier extends AppCompatActivity {
    EditText etPassword,etAmount;
    Button button;
    ImageView image;
    TextView tv;
    LinearLayout ll1;
    int click=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_verifier);
        etPassword = (EditText) findViewById(R.id.merchant_password);
        etAmount = (EditText) findViewById(R.id.merchant_amount);
        button = (Button) findViewById(R.id.button_send_money);
        image = (ImageView) findViewById(R.id.image_success);
        tv = (TextView) findViewById(R.id.success);
        ll1 = (LinearLayout) findViewById(R.id.ll1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mPassword = etPassword.getText().toString();
                String encPassword="";
                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] encodedhash = digest.digest(mPassword
                            .getBytes(StandardCharsets.UTF_8));
                    encPassword= bytesToHex(encodedhash);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                Log.e("haha", "onClick: "+getSharedPreferences("user_info",MODE_PRIVATE).getString("password","")+" enc password is "+encPassword );
                if(getSharedPreferences("user_info",MODE_PRIVATE).getString("password","").equals(encPassword)){
                    final ProgressDialog pgd = new ProgressDialog(MoneyVerifier.this);
                    pgd.setMessage("Please wait.. Sending money");
                    pgd.show();
                    ScheduledExecutorService schedule = new ScheduledThreadPoolExecutor(5);
                    schedule.schedule(new Runnable() {
                        @Override
                        public void run() {
                            pgd.dismiss();
                        }
                    },2, TimeUnit.SECONDS);
                    AlertDialog.Builder alert = new AlertDialog.Builder(MoneyVerifier.this);
                    alert.setMessage("Your payment has been done...!!")
                            .create()
                            .show();

                    //Pay ho gaya

                }
                else{
                    Toast.makeText(MoneyVerifier.this, "Please enter correct password, you have only three chance", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
