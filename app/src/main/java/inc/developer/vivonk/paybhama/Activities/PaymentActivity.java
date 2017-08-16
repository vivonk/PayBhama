package inc.developer.vivonk.paybhama.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import inc.developer.vivonk.paybhama.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


import static android.content.ContentValues.TAG;

/**
 * Created by vivonk on 06-08-2017.
 */

public class PaymentActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView barcodeScannerView;
    private EditText barCodeResult;
    Button mProceedToPay;
    String mURL = "https://apitest.sewadwaar.rajasthan.gov.in/app/live/Service/hofAndMember/ForApp/";
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    /*@Override
    public void onQRCodeRead(String text, PointF[] points) {
        qrCodeText.setText(text);
    }*/

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Log.e(TAG, "onCreate: Payment Actiity created" );
        if(checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }
        barcodeScannerView=(ZXingScannerView) findViewById(R.id.barcode_reader);
        barCodeResult=(EditText)findViewById(R.id.barcode_result);
      //  barcodeScannerView.startCamera();
        mProceedToPay = (Button) findViewById(R.id.proceed_to_pay);

        mProceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(barCodeResult.getText().toString().isEmpty()| barCodeResult.getText().toString().length()<7){
                    Toast.makeText(PaymentActivity.this, "Please enter correct family number", Toast.LENGTH_SHORT).show();
                }
                else{
                    new PaymentAddressGetClass().execute(barCodeResult.getText().toString());
                }
            }
        });
        barcodeScannerView.setResultHandler(this);

    }
    @Override
    protected void onResume() {
        super.onResume();


//        barcodeScannerView.setResultHandler(this);
        barcodeScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeScannerView.stopCamera();
    }


    @Override
    public void handleResult(Result result) {
        barCodeResult.setText(result.getText());
        Log.e(TAG, "handleResult:   handle result is -------\n "+ result.getBarcodeFormat().toString());
        barcodeScannerView.resumeCameraPreview(this);
    }
    private  boolean checkAndRequestPermissions() {
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
            return false;
        }
        return true;
    }
    private class PaymentAddressGetClass extends AsyncTask<String,String,String>{
        ProgressDialog pgd ;
        @Override
        protected void onPreExecute() {
            pgd = new ProgressDialog(PaymentActivity.this);
            if(getSharedPreferences("user_info",MODE_PRIVATE).getString("language","English").equals("Hindi")){
                pgd.setMessage(getResources().getString(R.string.please_wait_hindi));
            }else {
                pgd.setMessage(getResources().getString(R.string.please_wait));
            }
            pgd.setCancelable(false);
            pgd.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.e(TAG, "doInBackground: link is link link link link "+ mURL + strings[0]+"?client_id=ad7288a4-7764-436d-a727-783a977f1fe1");
            AndroidNetworking.get(mURL + strings[0]+"?client_id=ad7288a4-7764-436d-a727-783a977f1fe1")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e(TAG, "onResponse: response of this is ---------\n"+ response+ " length is "+response.length() );
                            try {
                                JSONObject object = (JSONObject) response.get("hof_Details");
                                String accountNumber;
                                String aadharNumber;
                                String IFSCCode;
                                String DOB;
                                String BHAMASHAH_ID;
                                String NAME_ENG;
                                String VILLAGE_NAME;
                                PaymentActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (pgd.isShowing()) {
                                            pgd.dismiss();
                                        }
                                    }
                                });
                                if (response.length() > 1) {
                                    if (object.get("ACC_NO") != "null") {
                                        accountNumber = (String) object.get("ACC_NO");
                                    } else {
                                        accountNumber = "Not provided";
                                    }
                                    if (object.get("AADHAR_ID") != "null") {
                                        aadharNumber = (String) object.get("AADHAR_ID");
                                    } else {
                                        aadharNumber = "Not Provided";
                                    }


                                    if (object.get("IFSC_CODE") != "null") {
                                        IFSCCode = (String) object.get("IFSC_CODE");
                                    } else {
                                        IFSCCode = "Not Provided";
                                    }
                                    if (object.get("DOB") != "null") {
                                        DOB = (String) object.get("DOB");
                                    } else {
                                        DOB = "Not Provided";
                                    }
                                    if (object.get("BHAMASHAH_ID") != "null") {
                                        BHAMASHAH_ID = (String) object.get("BHAMASHAH_ID");
                                    } else {
                                        BHAMASHAH_ID = "Not Provided";
                                    }
                                    if (object.get("NAME_ENG") != "null") {
                                        NAME_ENG = (String) object.get("NAME_ENG");
                                    } else {
                                        NAME_ENG = "Not Provided";
                                    }
                                    if (object.get("VILLAGE_NAME") != null) {
                                        VILLAGE_NAME = object.getString("VILLAGE_NAME");
                                    } else {
                                        VILLAGE_NAME = "Not Provided";
                                    }



                                    Intent intent = new Intent(getApplicationContext(), PaymentVarifierActivity.class);
                                    intent.putExtra("ACCOUNT_NUMBER", accountNumber)
                                            .putExtra("AADHAR_NUMBER", aadharNumber)
                                            .putExtra("IFSC_CODE", IFSCCode)
                                            .putExtra("DOB", DOB)
                                            .putExtra("BHAMASHAH_ID", BHAMASHAH_ID)
                                            .putExtra("VILLAGE_NAME", VILLAGE_NAME)
                                            .putExtra("NAME_ENG", NAME_ENG);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(PaymentActivity.this, "Please enter correct family number", Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch(JSONException e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(final ANError anError) {
                            PaymentActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(pgd.isShowing())
                                    {
                                        pgd.dismiss();
                                    }
                                    Toast.makeText(PaymentActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                                    Log.e(TAG, "run() returned:  error are 0000000   " + anError );
                                }
                            });
                        }
                    });
            return null;
        }
    }
}
