package inc.developer.vivonk.paybhama.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.dashboard.DashBoard;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout mMAccountNumber,mMAadharNumber,mMIFSCCode,mMMobileNumber,mMEmailAddress;
    private CardView mCardView1,mCardView2,mCardView3;
    private Button mNext1,mNext2,mNext3;
    CheckBox mCheckPassword;
    TextInputEditText mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mMAadharNumber = (TextInputLayout) findViewById(R.id.merchant_aadhar_number_input_layout);
        mMAccountNumber = (TextInputLayout) findViewById(R.id.merchant_account_number_input_layout);
        mMIFSCCode = (TextInputLayout) findViewById(R.id.merchant_ifsc_code_input_layout);
        mMMobileNumber = (TextInputLayout) findViewById(R.id.merchant_mobile_number_input_layout);
        mMEmailAddress = (TextInputLayout) findViewById(R.id.merchant_email_address_input_layout);

        mCardView1 = (CardView) findViewById(R.id.card_view_1);
        mCardView2 = (CardView) findViewById(R.id.card_view_2);
        mCardView3 = (CardView) findViewById(R.id.card_view_3);

        mNext1 = (Button) findViewById(R.id.button_next_1);
        mNext2 = (Button) findViewById(R.id.button_next_2);
        mNext3 = (Button) findViewById(R.id.button_next_3);

        mPassword = (TextInputEditText) findViewById(R.id.merchant_password);
        mCheckPassword=(CheckBox)findViewById(R.id.checkbox_password);

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
      mNext1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              mCardView1.setVisibility(View.GONE);
              mCardView2.setVisibility(View.VISIBLE);
          }
      });

        mNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardView2.setVisibility(View.GONE);
                mCardView3.setVisibility(View.VISIBLE);
            }
        });
        mNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCardView3.setVisibility(View.VISIBLE);
                startActivity(new Intent(getApplicationContext(), DashBoard.class));
            }
        });

        mCheckPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if(!isChecked){
                    mPassword.setTransformationMethod(new PasswordTransformationMethod());
                    mPassword.setSelection(mPassword.getText().toString().length());
                }
                else {
                    mPassword.setTransformationMethod(null);
                    mPassword.setSelection(mPassword.getText().toString().length());

                }
            }
        });
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
