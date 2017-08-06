package inc.developer.vivonk.paybhama.Activities;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import inc.developer.vivonk.paybhama.R;

/**
 * Created by vivonk on 06-08-2017.
 */

public class PaymentByQRCodeActivity extends Activity implements QRCodeReaderView.OnQRCodeReadListener{

    QRCodeReaderView mQRCodeScannerView;
    EditText mQRCodeScannerResult;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_by_qrcode);
        mQRCodeScannerResult=(EditText)findViewById(R.id.qr_code_result);
        mQRCodeScannerView=(QRCodeReaderView)findViewById(R.id.qr_code_reader);
        mQRCodeScannerView.setOnQRCodeReadListener(this);
        mQRCodeScannerView.setQRDecodingEnabled(true);
        mQRCodeScannerView.setBackCamera();
        mQRCodeScannerView.setTorchEnabled(true);
        mQRCodeScannerView.setAutofocusInterval(2000L);

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        mQRCodeScannerResult.setText(text);
    }

    @Override
    protected void onResume() {
        mQRCodeScannerView.startCamera();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mQRCodeScannerView.stopCamera();
    }
}
