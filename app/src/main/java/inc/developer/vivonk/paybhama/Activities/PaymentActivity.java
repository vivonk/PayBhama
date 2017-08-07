package inc.developer.vivonk.paybhama.Activities;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;

import com.google.zxing.Result;

import inc.developer.vivonk.paybhama.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


import static android.content.ContentValues.TAG;

/**
 * Created by vivonk on 06-08-2017.
 */

public class PaymentActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView barcodeScannerView;
    private EditText barCodeResult;
    /*@Override
    public void onQRCodeRead(String text, PointF[] points) {
        qrCodeText.setText(text);
    }*/

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Log.e(TAG, "onCreate: Payment Actiity created" );
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
}
