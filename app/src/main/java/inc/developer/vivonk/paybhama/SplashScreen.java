package inc.developer.vivonk.paybhama;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import inc.developer.vivonk.paybhama.Activities.loginOrSignUpActivity;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG ="TAG" ;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    Button mProceedToLogin,mProceedForWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
        mProceedForWebView = (Button) findViewById(R.id.button_redirect_web);
        mProceedToLogin = (Button) findViewById(R.id.button_login);
                scheduledExecutorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "run: I run after 3 sec" );
                        SplashScreen.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG, "run: This also worked for me, and you" );
                                mProceedForWebView.setVisibility(View.VISIBLE);
                                mProceedToLogin.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                }, 3, TimeUnit.SECONDS);
        mProceedToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),loginOrSignUpActivity.class));
            }
        });
        int permissionInternet = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);
        int permissionCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionInternet!= PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET);
        }
        if (permissionCamera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
    }
}
