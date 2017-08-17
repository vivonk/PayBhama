package inc.developer.vivonk.paybhama.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.encryptor4j.Encryptor;
import org.encryptor4j.factory.KeyFactory;
import org.json.JSONArray;
import org.json.JSONException;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.dashboard.DashBoard;

public class loginOrSignUpActivity extends AppCompatActivity {
    TextInputEditText mTiePassword;
    TextInputLayout mTilPassword, mTilMobileNumber;
    EditText mMobileNumber;
    CheckBox mCheckPassword;
    boolean mLanguageHindi = false;
    public static String sSalt="indian";
    String mCustomerId, mPassword;
    Button mButtonLogin, mButtonForgotPassword, mButtonSignUp;

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

        mMobileNumber = (EditText) findViewById(R.id.merchant_mobile_number);
        mCheckPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if (!isChecked) {
                    mTiePassword.setTransformationMethod(new PasswordTransformationMethod());
                    mTiePassword.setSelection(mTiePassword.getText().toString().length());
                } else {
                    mTiePassword.setTransformationMethod(null);
                    mTiePassword.setSelection(mTiePassword.getText().toString().length());

                }
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMobileNumber.length() != 10) {
                    Toast.makeText(loginOrSignUpActivity.this, "Please enter correct phone number", Toast.LENGTH_SHORT).show();
                } else if (mTiePassword.getText().toString().trim().length() != mTiePassword.getText().toString().length()) {
                    Toast.makeText(loginOrSignUpActivity.this, "Password cannot include white space", Toast.LENGTH_SHORT).show();
                } else if (mTiePassword.length() < 8) {
                    Toast.makeText(loginOrSignUpActivity.this, "Please type at least 8 digit password", Toast.LENGTH_SHORT).show();
                } else {
                    mCustomerId = mMobileNumber.getText().toString();
                    mPassword = mTiePassword.getText().toString();
//                    String message = "This string has been encrypted & decrypted using AES in Galois Counter Mode";
                    /*SecretKey secretKey = (SecretKey) KeyFactory.AES.randomKey();
                    String key= secretKey.toString();
                    Log.e("Key ", "onClick: secret key is "+ key );
                    Encryptor encryptor = new Encryptor(secretKey, "AES/GCM/NoPadding", 16, 128);
                    byte[] encrypted = new byte[0];
                    try {
                         encrypted = encryptor.encrypt(mCustomerId.getBytes());
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                    byte[] decrypted = new byte[0];
                    try {
                        decrypted = encryptor.decrypt(encrypted);
                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }
                    Log.e("decrypted key + enc ", "onClick: ------------   "+new String(decrypted)+" enc is ***********0   "+new String(encrypted));
//*/
//                    String encCustomerId = new String(encrypted);
                    String encPassword = "12345678";
                    try {
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] encodedhash = digest.digest(
                                mPassword.getBytes(StandardCharsets.UTF_8));
                        encPassword= bytesToHex(encodedhash);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                   // Log.e("Credential", "onClick: customer id is "+ encCustomerId+ " decrypt is "+" ------------------------ password is    "+encPassword );
                    new Login().execute(mCustomerId,encPassword);
                }
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

        mButtonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OTPActivity.class));
            }
        });
        if (getSharedPreferences("user_info", MODE_PRIVATE).getString("language", "English").equals("Hindi")) {
            mLanguageHindi = true;
            mTilMobileNumber.setHint(getResources().getString(R.string.mobile_number_hindi));
            mTilPassword.setHint(getResources().getString(R.string.password_hindi));
            mButtonLogin.setText(R.string.login_hindi);
            mButtonSignUp.setText(R.string.sign_up_hindi);
            mButtonForgotPassword.setText(R.string.forgot_password_hindi);
            mCheckPassword.setText(R.string.show_password_hindi);
        }
    }
    /*static String encrypt(String msg,String key){
        String tmp=key;
        while (key.length() < msg.length())
        { key += tmp;}
        //cout<<"Now the key is "<<key<<endl;
        // And now for the encryption part
        char[] ch=msg.toCharArray();
        char[] b= key.toCharArray();
        for ( int i = 0; i < msg.length(); ++i)
        { ch[i] ^= b[i];}
        String newEncrypt = new String(ch);
        msg = newEncrypt;
        return msg;
    }
    static String decrypt(String msg, String key)
    {
        return encrypt(msg, key); // lol
    }*/
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private class Login extends AsyncTask<String, String, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog= new ProgressDialog(loginOrSignUpActivity.this);
            if (getSharedPreferences("user_info", MODE_PRIVATE).getString("language", "English").equals("Hindi")) {
                progressDialog.setMessage(getResources().getString(R.string.please_wait_hindi));
            }else{
                progressDialog.setMessage(getResources().getString(R.string.please_wait));
            }
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            AndroidNetworking.post("http://ec2-34-204-44-57.compute-1.amazonaws.com:8081/login")
                    .addBodyParameter("customer_id",strings[0])
                    .addBodyParameter("password",strings[1])
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //                                String result = response.get(0).toString();
                            Log.e("TAG", "onResponse: response of wrong credential is "+response );
                            String result="";
                            try {
                                 result = response.getString(0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(!result.equals("failure")){
                                JSONArray array = response;
                                array = response;
                                SharedPreferences sharedPreference = getSharedPreferences("user_info", MODE_PRIVATE);
                               /* putString("aadhar",strAadhar)
                                        .putString("bank",strBank)
                                        .putString("account",strAccount)
                                        .putString("ifsc",strIFSCCode)
                                        .putString("mobile",strMobile)
                                        .putString("email",strEmail)*/
                                SharedPreferences.Editor editor = sharedPreference.edit();
                                try {
                                    editor.putString("aadhar",array.getString(2))
                                            .putString("bank",array.getString(3))
                                            .putString("account",array.getString(4))
                                            .putString("mobile",array.getString(0))
                                            .putString("ifsc",array.getString(6))
                                            .putString("password",array.getString(1))
                                            .putBoolean("log",true).apply();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                startActivity(new Intent(getApplicationContext(), DashBoard.class));
                            }else {
                                Toast.makeText(loginOrSignUpActivity.this, "Please enter correct details", Toast.LENGTH_SHORT).show();
                                //TODO Hindi karo
                            }

                            loginOrSignUpActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                }
                            });
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.e("Error", "onError: Error is "+anError );
                            Toast.makeText(loginOrSignUpActivity.this, "Server Error, For testing purpose click twice for going forward. Contact us on 8006467951 or IITR_Cracks", Toast.LENGTH_LONG).show();
                            loginOrSignUpActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                }
                            });
                        }
                    });
            return null;
        }
    }
}