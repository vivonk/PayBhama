package inc.developer.vivonk.paybhama.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.fragments.HomeFragment;
import inc.developer.vivonk.paybhama.fragments.PaymentFragment;

public class DashBoard extends AppCompatActivity {

    private TextView mTextMessage;
    private Menu menu;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_history:
//                    mTextMessage.setText(R.string.history_hindi_english);
                    if(getSharedPreferences("user_info",MODE_PRIVATE).getString("language","English").equals("Hindi")){
                    getSupportActionBar().setTitle(R.string.history_hindi_english);
                    return true;
                }
                getSupportActionBar().setTitle("History");

                    return true;

                case R.id.navigation_setting:
                    getSupportActionBar().setTitle(R.string.setting_hindi);
//                    mTextMessage.setText(R.string.setting_hindi);
                    return true;
                case R.id.navigation_add_money:
                    getSupportActionBar().setTitle(R.string.add_money_hindi);
//                    mTextMessage.setText(R.string.add_money_hindi);
                    if(findViewById(R.id.content)!=null){
                        PaymentFragment homeFragment=new PaymentFragment();
                        FragmentManager fragmentManager= getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content,homeFragment).commit();
                    }
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        if(getSharedPreferences("user_info",MODE_PRIVATE).getString("language","English").equals("Hindi")){
            navigation.inflateMenu(R.menu.navigation_hindi);
        }
        else{
            navigation.inflateMenu(R.menu.navigation);
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(findViewById(R.id.content)!=null){
            if (savedInstanceState != null) {
                return;
            }
            PaymentFragment paymentFragment=new PaymentFragment();
            FragmentManager fragmentManager= getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content,paymentFragment).commit();
        }


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }*/
}
