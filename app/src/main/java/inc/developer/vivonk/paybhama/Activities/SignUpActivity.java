package inc.developer.vivonk.paybhama.Activities;

import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import inc.developer.vivonk.paybhama.R;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout mMAccountNumber,mMAadharNumber,mMIFSCCode,mMMobileNumber,mMEmailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mMAadharNumber=(TextInputLayout)findViewById(R.id.merchant_aadhar_number_input_layout);
        mMAccountNumber=(TextInputLayout)findViewById(R.id.merchant_account_number_input_layout);
        mMIFSCCode=(TextInputLayout)findViewById(R.id.merchant_ifsc_code_input_layout);
        mMMobileNumber=(TextInputLayout)findViewById(R.id.merchant_mobile_number_input_layout);
        mMEmailAddress=(TextInputLayout)findViewById(R.id.merchant_email_address_input_layout);

        String simple = mMAadharNumber.getHint().toString();
        mMAadharNumber.setHint(setMandatory(simple));

        simple = mMIFSCCode.getHint().toString();
        mMIFSCCode.setHint(setMandatory(simple));

        simple = mMAccountNumber.getHint().toString();
        mMAccountNumber.setHint(setMandatory(simple));

        simple = mMMobileNumber.getHint().toString();
        mMMobileNumber.setHint(setMandatory(simple));

      /*  simple = mMEmailAddress.getHint().toString();
        mMEmailAddress.setHint(setMandatory(simple));
*/
    }
    String setMandatory(String simple){
        String colored = " *";
        SpannableStringBuilder builder = new SpannableStringBuilder();

        builder.append(simple);
        int start = builder.length();
        builder.append(colored);
        int end = builder.length();

        builder.setSpan(new ForegroundColorSpan(Color.BLACK), start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder.toString();
    }
}
