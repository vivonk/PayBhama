package inc.developer.vivonk.paybhama;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import inc.developer.vivonk.paybhama.dashboard.DashBoard;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG ="TAG" ;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    Button mProceedToLogin,mProceedForWebView;
    AlertDialog.Builder mLanguageChooseDialog ;
    SharedPreferences mSharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mSharedPreference = getSharedPreferences("user_info",MODE_PRIVATE);
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
        mProceedForWebView = (Button) findViewById(R.id.button_redirect_web);
        mLanguageChooseDialog = new AlertDialog.Builder(this);
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
        if(mSharedPreference.getBoolean("loggedIn",false)){
            startActivity(new Intent(getApplicationContext(), DashBoard.class));
        }

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
        mLanguageChooseDialog.setTitle("Choose language / भाषा चुने ")
                .setMessage("You can change it later on / आप भाषा बाद में कभी भी बदल सकते हो ")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mSharedPreference= getSharedPreferences("user_info",MODE_PRIVATE);
                        SharedPreferences.Editor editor = mSharedPreference.edit();
                        editor.putString("language","English").apply();
                    }
                }).setNegativeButton("Hindi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mSharedPreference= getSharedPreferences("user_info",MODE_PRIVATE);
                        SharedPreferences.Editor editor = mSharedPreference.edit();
                        editor.putString("language","Hindi").apply();
                        mProceedToLogin.setText(R.string.login_hindi);
                        mProceedForWebView.setText(R.string.know_more_hindi);
                    }
                }).setCancelable(false).create().show();
    }


}
