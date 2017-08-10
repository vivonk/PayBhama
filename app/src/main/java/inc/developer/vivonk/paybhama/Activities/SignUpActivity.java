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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.dashboard.DashBoard;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout mMAccountNumber,mMAadharNumber,mMIFSCCode,mMMobileNumber,mMEmailAddress,mMOtp,mMPassword,mMFirstName,mMLastName,mMAddress;
    private CardView mCardView1,mCardView2,mCardView3;
    Button mNext1,mNext2,mNext3;
    EditText etOtp,etFirstName,etLastName,etAddress,etAadharNumber,etAccountNumber,etIFSCCode,etMobileNumber,etEmailAddress;
    CheckBox mCheckPassword;
    TextInputEditText mPassword;
    TextView mOtpTextView,mGenderTextView;
    Spinner spBankName;
    RadioButton mRbMale,mRbFemale;
    boolean mLanguageHindi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mMAadharNumber = (TextInputLayout) findViewById(R.id.merchant_aadhar_number_input_layout);
        mMAccountNumber = (TextInputLayout) findViewById(R.id.merchant_account_number_input_layout);
        mMIFSCCode = (TextInputLayout) findViewById(R.id.merchant_ifsc_code_input_layout);
        mMMobileNumber = (TextInputLayout) findViewById(R.id.merchant_mobile_number_input_layout);
        mMEmailAddress = (TextInputLayout) findViewById(R.id.merchant_email_address_input_layout);
        mMOtp = (TextInputLayout) findViewById(R.id.merchant_otp_input_layout);
        mMPassword = (TextInputLayout) findViewById(R.id.merchant_password_input_layout);
        mMFirstName = (TextInputLayout) findViewById(R.id.merchant_first_name_input_layout);
        mMLastName = (TextInputLayout) findViewById(R.id.merchant_last_name_input_layout);
        mMAddress = (TextInputLayout) findViewById(R.id.merchant_address_input_layout);

        etAadharNumber = (EditText) findViewById(R.id.merchant_aadhar_number);
        spBankName = (Spinner) findViewById(R.id.merchant_bank_name_spinner);
        etIFSCCode = (EditText) findViewById(R.id.merchant_ifsc_code);
        etMobileNumber = (EditText) findViewById(R.id.merchant_mobile_number);
        etEmailAddress = (EditText) findViewById(R.id.merchant_email_address);
        etOtp = (EditText) findViewById(R.id.merchant_otp);
        etFirstName = (EditText) findViewById(R.id.merchant_first_name);
        etLastName = (EditText) findViewById(R.id.merchant_last_name);
        etAddress = (EditText) findViewById(R.id.merchant_address);
        etAccountNumber = (EditText) findViewById(R.id.merchant_account_number);


        mRbMale = (RadioButton) findViewById(R.id.rb_male);
        mRbFemale = (RadioButton) findViewById(R.id.rb_female);

        mOtpTextView = (TextView) findViewById(R.id.otp_text_view);
        mGenderTextView = (TextView) findViewById(R.id.tv_gender);

        mCardView1 = (CardView) findViewById(R.id.card_view_1);
        mCardView2 = (CardView) findViewById(R.id.card_view_2);
        mCardView3 = (CardView) findViewById(R.id.card_view_3);

        mNext1 = (Button) findViewById(R.id.button_next_1);
        mNext2 = (Button) findViewById(R.id.button_next_2);
        mNext3 = (Button) findViewById(R.id.button_next_3);

        mPassword = (TextInputEditText) findViewById(R.id.merchant_password);

        mCheckPassword = (CheckBox) findViewById(R.id.checkbox_password);

        if(getSharedPreferences("user_info",MODE_PRIVATE).getString("language","English").equals("Hindi")){
            mMAccountNumber.setHint(getResources().getString(R.string.account_number_hindi));
            mMMobileNumber.setHint(getResources().getString(R.string.mobile_number_hindi));
            mMIFSCCode.setHint(getResources().getString(R.string.ifsc_code_hindi));
            mMEmailAddress.setHint(getResources().getString(R.string.email_hindi));
            mMAccountNumber.setHint(getResources().getString(R.string.account_number_hindi));
            mMOtp.setHint(getResources().getString(R.string.otp_hindi));
            mMAadharNumber.setHint(getResources().getString(R.string.aadhar_number_hindi));
            mMPassword.setHint(getResources().getString(R.string.password_hindi));
            mMFirstName.setHint(getResources().getString(R.string.first_name_hindi));
            mMLastName.setHint(getResources().getString(R.string.last_name_hindi));
            mMAddress.setHint(getResources().getString(R.string.address_hindi));

            mOtpTextView.setText(R.string.otp_text_view_hindi);
            mGenderTextView.setText(R.string.gender_hindi);

            mNext1.setText(R.string.next_hindi);
            mNext2.setText(R.string.next_hindi);
            mNext3.setText(R.string.submit_hindi);

            mCheckPassword.setText(R.string.show_password_hindi);

            mRbMale.setText(R.string.male_hindi);
            mRbFemale.setText(R.string.female_hindi);

            mLanguageHindi = true;
        }

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
    boolean ValidatorOne(){
        return false;
    }
    boolean ValidatorTwo(){
        return false;
    }
    boolean ValidatorThree(){
        return false;
    }
}
