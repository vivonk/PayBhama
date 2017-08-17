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
import android.widget.EditText;

import inc.developer.vivonk.paybhama.Activities.PaymentActivity;
import inc.developer.vivonk.paybhama.R;

import static android.content.ContentValues.TAG;

/**
 * Created by vivonk on 06-08-2017.
 */

public class PaymentFragment extends Fragment{
    Button mProceedForPaymentBarCode,mSendMoneyTOOthers;
    View mView;
    EditText etAccount,etIFSC;
    String strAccount,strIFSC;
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
        mProceedForPaymentBarCode=(Button)mView.findViewById(R.id.button_user_scan);
        mSendMoneyTOOthers = (Button) mView.findViewById(R.id.button_send_money);
        etAccount = (EditText)mView.findViewById(R.id.sending_account_number);
        etIFSC =(EditText) mView.findViewById(R.id.sending_ifsc_code);
        mProceedForPaymentBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: called" );
                startActivity(new Intent(getContext(), PaymentActivity.class));
            }
        });
        mSendMoneyTOOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strAccount = etAccount.getText().toString();
                strIFSC = etAccount.getText().toString();
                if(10<=strAccount.length()&strAccount.length()<=16&!strIFSC.isEmpty()){

                }
            }
        });
    }
}
