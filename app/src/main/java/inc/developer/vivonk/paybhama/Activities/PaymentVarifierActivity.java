package inc.developer.vivonk.paybhama.Activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.dashboard.DashBoard;

public class PaymentVarifierActivity extends AppCompatActivity {
    TextView details,hofDetails,mLast4Aadhar,mLast4Account,mFirst4Aadhar,mFirst4Account;
    EditText mEtFirst4Aadhar,mEtFirst4Account,mEtLast4Aadhar,mEtLast4Account;
    Button mProceedToPay;
    int clicks=0,minute=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_varifier);
        details = (TextView) findViewById(R.id.payment_complete_details);
        hofDetails = (TextView) findViewById(R.id.payment_complete_details_text);
        mLast4Account = (TextView) findViewById(R.id.payment_complete_account_number_last);
        mLast4Aadhar = (TextView) findViewById(R.id.payment_complete_aadhar_number_last);
        mFirst4Aadhar = (TextView) findViewById(R.id.payment_complete_aadhar_number_first);
        mFirst4Account = (TextView) findViewById(R.id.payment_complete_account_number_first);
        mEtFirst4Aadhar = (EditText) findViewById(R.id.payment_complete_aadhar_number_first_four_digit);
        mEtFirst4Account = (EditText) findViewById(R.id.payment_complete_account_number_first_four_digit);
        mEtLast4Account = (EditText) findViewById(R.id.payment_complete_account_number_last_four_digit);
        mEtLast4Aadhar = (EditText) findViewById(R.id.payment_complete_aadhar_number_last_four_digit);
        mProceedToPay = (Button) findViewById(R.id.proceed_to_pay);

        RendGenerator();

        Bundle stringBundle = getIntent().getExtras();
        final String accountNumber = stringBundle.getString("ACCOUNT_NUMBER","20307947499");
        final String aadharNumber = stringBundle.getString("AADHAR_NUMBER","931649541869");
        final int lAccountNumber = accountNumber.length();
        final int lAadharNumber = aadharNumber.length();
        //file is located in this way


        if (getSharedPreferences("user_info", MODE_PRIVATE).getString("language", "English").equals("Hindi")) {
            details.setText(R.string.details_hindi);
            mLast4Aadhar.setText(R.string.last_four_digit_of_your_aadhar_number);
            mLast4Account.setText(R.string.last_four_digit_of_your_account_number);
            mFirst4Account.setText(R.string.first_four_digit_of_your_account_number);
            mFirst4Aadhar.setText(R.string.first_four_digit_of_your_aadhar_number);
            mEtFirst4Account.setHint(getResources().getString(R.string.first_four_digit_hindi));
            mEtFirst4Aadhar.setHint(getResources().getString(R.string.first_four_digit_hindi));
            mEtLast4Aadhar.setHint(getResources().getString(R.string.last_four_digit_hindi));
            mEtLast4Account.setHint(getResources().getString(R.string.last_four_digit_hindi));
            mProceedToPay.setText(R.string.next_hindi);
            hofDetails.setText( getResources().getString(R.string.name)+" - "+ stringBundle.getString("NAME_ENG", "Rajasthan") + "\n" +getResources().getString(R.string.location) +" - " + stringBundle.getString("VILLAGE_NAME", "Rajasthan") + "\n" + getResources().getString(R.string.dob)+" - " + stringBundle.getString("DOB", "13/10/1999"));
        } else {
            hofDetails.setText("NAME -" + stringBundle.getString("NAME_ENG", "Rajasthan") + "\n Village Name - " + stringBundle.getString("VILLAGE_NAME", "Rajasthan") + "\n" + "DOB - " + stringBundle.getString("DOB", "13/10/1999"));
        }

        mProceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks++;
                if(clicks<4) {
                    int cal = minute;
                    Log.e("TAG", "onClick: "+ accountNumber.substring(0, 3)+" and account is "+accountNumber.substring(lAccountNumber - 4));
                    if (cal % 4 == 0) {
                    /*mEtLast4Account.setVisibility(View.GONE);
                    mEtLast4Aadhar.setVisibility(View.GONE);
                    mLast4Account.setVisibility(View.GONE);
                    mLast4Aadhar.setVisibility(View.GONE);*/
                        String str1 = mEtFirst4Aadhar.getText().toString();
                        String str2 = mEtFirst4Account.getText().toString();
                        if (str1.isEmpty() || str2.isEmpty()) {
                            toast("Please enter both details");
                        } else if (str1.length() < 4 | str2.length() < 4) {
                            toast("Please enter all details");
                        } else {
                            if (accountNumber.substring(0, 4).equals(str2) & aadharNumber.substring(0, 4).equals(str1)) {
                                toast("Proceed for transaction");
                            } else {
                                toast("Please enter correct details");
                            }
                        }
                    } else if (cal % 3 == 0) {
                    /*mEtFirst4Account.setVisibility(View.GONE);
                    mEtLast4Aadhar.setVisibility(View.GONE);
                    mFirst4Account.setVisibility(View.GONE);
                    mLast4Aadhar.setVisibility(View.GONE);*/
                        String str1 = mEtFirst4Aadhar.getText().toString();
                        String str2 = mEtLast4Account.getText().toString();
                        if (str1.isEmpty() || str2.isEmpty()) {
                            toast("Please enter both details");
                        } else if (str1.length() < 4 | str2.length() < 4) {
                            toast("Please enter all details");
                        } else {
                            if (accountNumber.substring(lAccountNumber - 4).equals(str2) & aadharNumber.substring(0, 4).equals(str1)) {
                                toast("Proceed for transaction");
                            } else {
                                toast("Please enter correct details");
                            }
                        }
                    } else if (cal % 2 == 0) {
                   /* mEtLast4Account.setVisibility(View.GONE);
                    mEtFirst4Aadhar.setVisibility(View.GONE);
                    mLast4Account.setVisibility(View.GONE);
                    mFirst4Aadhar.setVisibility(View.GONE);*/
                        String str1 = mEtLast4Aadhar.getText().toString();
                        String str2 = mEtFirst4Account.getText().toString();
                        if (str1.isEmpty() || str2.isEmpty()) {
                            toast("Please enter both details");
                        } else if (str1.length() < 4 | str2.length() < 4) {
                            toast("Please enter all details");
                        } else {
                            if (accountNumber.substring(0, 4).equals(str2) & aadharNumber.substring(lAadharNumber - 4).equals(str1)) {
                                toast("Proceed for transaction");
                            } else {
                                toast("Please enter correct details");
                            }
                        }
                    } else {
                    /*mEtFirst4Account.setVisibility(View.GONE);
                    mEtFirst4Aadhar.setVisibility(View.GONE);
                    mFirst4Account.setVisibility(View.GONE);
                    mFirst4Aadhar.setVisibility(View.GONE);*/
                        String str1 = mEtLast4Aadhar.getText().toString();
                        String str2 = mEtLast4Account.getText().toString();
                        if (str1.isEmpty() || str2.isEmpty()) {
                            toast("Please enter both details");
                        } else if (str1.length() < 4 | str2.length() < 4) {
                            toast("Please enter all details");
                        } else {
                            if (accountNumber.substring(lAccountNumber - 4).equals(str2) & aadharNumber.substring(lAadharNumber - 4).equals(str1)) {
                                toast("Proceed for transaction");
                            } else {
                                toast("Please enter correct details");
                            }
                        }
                    }
                }else{
                    toast("You entered wrong details for many time.");
                    startActivity(new Intent(getApplicationContext(),DashBoard.class));
                }
            }
        });
    }
    void RendGenerator(){
        int cal = Calendar.getInstance().getTime().getMinutes();
        minute=cal;
        if(cal%4==0){
            mEtLast4Account.setVisibility(View.GONE);
            mEtLast4Aadhar.setVisibility(View.GONE);
            mLast4Account.setVisibility(View.GONE);
            mLast4Aadhar.setVisibility(View.GONE);
        }else if(cal%3==0){
            mEtFirst4Account.setVisibility(View.GONE);
            mEtLast4Aadhar.setVisibility(View.GONE);
            mFirst4Account.setVisibility(View.GONE);
            mLast4Aadhar.setVisibility(View.GONE);
        }else if(cal%2==0){
            mEtLast4Account.setVisibility(View.GONE);
            mEtFirst4Aadhar.setVisibility(View.GONE);
            mLast4Account.setVisibility(View.GONE);
            mFirst4Aadhar.setVisibility(View.GONE);
        }else{
            mEtFirst4Account.setVisibility(View.GONE);
            mEtFirst4Aadhar.setVisibility(View.GONE);
            mFirst4Account.setVisibility(View.GONE);
            mFirst4Aadhar.setVisibility(View.GONE);
        }

    }
    void toast(String s){
         Toast.makeText(PaymentVarifierActivity.this,s,Toast.LENGTH_SHORT).show();
    }
}
