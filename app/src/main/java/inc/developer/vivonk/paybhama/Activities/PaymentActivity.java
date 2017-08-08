package inc.developer.vivonk.paybhama.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.EditText;

import com.google.zxing.Result;

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
}
