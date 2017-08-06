package inc.developer.vivonk.paybhama.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import inc.developer.vivonk.paybhama.R;
import inc.developer.vivonk.paybhama.fragments.HomeFragment;

public class DashBoard extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    getSupportActionBar().setTitle(R.string.app_name);
                    return true;
                case R.id.navigation_history:
//                    mTextMessage.setText(R.string.history_hindi_english);
                    getSupportActionBar().setTitle(R.string.history_hindi_english);
                    return true;
                case R.id.navigation_notifications:
                    getSupportActionBar().setTitle(R.string.title_notifications);
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_setting:
                    getSupportActionBar().setTitle(R.string.setting_hindi);
//                    mTextMessage.setText(R.string.setting_hindi);
                    return true;
                case R.id.navigation_add_money:
                    getSupportActionBar().setTitle(R.string.add_money_hindi);
//                    mTextMessage.setText(R.string.add_money_hindi);
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
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(findViewById(R.id.content)!=null){
            if (savedInstanceState != null) {
                return;
            }
            HomeFragment homeFragment=new HomeFragment();
            FragmentManager fragmentManager= getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content,homeFragment).commit();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }*/
}
