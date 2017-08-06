package inc.developer.vivonk.paybhama.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import inc.developer.vivonk.paybhama.Activities.PaymentActivity;
import inc.developer.vivonk.paybhama.Activities.PaymentByQRCodeActivity;
import inc.developer.vivonk.paybhama.R;

import static android.content.ContentValues.TAG;

/**
 * Created by vivonk on 06-08-2017.
 */

public class PaymentFragment extends Fragment{
    Button mProceedForPaymentBarCode,mProceedForPaymentQRCode;
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: is called " );
        mView=inflater.inflate(R.layout.fragment_payment,container,false);
        return mView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: is called " );
        mProceedForPaymentBarCode=(Button)mView.findViewById(R.id.button);
        mProceedForPaymentQRCode=(Button)mView.findViewById(R.id.button1);
        mProceedForPaymentQRCode.setText("Pay using QR Code");
        mProceedForPaymentBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: called" );
                startActivity(new Intent(getContext(), PaymentActivity.class));
            }
        });

        mProceedForPaymentQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PaymentByQRCodeActivity.class));
            }
        });
    }
}
