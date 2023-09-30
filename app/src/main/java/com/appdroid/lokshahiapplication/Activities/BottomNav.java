package com.appdroid.lokshahiapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.appdroid.lokshahiapplication.Fragments.Gunhe;
import com.appdroid.lokshahiapplication.Fragments.JalgaonJilha;
import com.appdroid.lokshahiapplication.Fragments.JalgaonShahar;
import com.appdroid.lokshahiapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNav extends AppCompatActivity {

    Fragment bottomNav, firstFragment, secondFragment, thirdFragment;
    private BottomNavigationView bottomNavigationView;
    RelativeLayout mainFragment;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNav = new Fragment();
        firstFragment = new Gunhe();
        secondFragment = new JalgaonShahar();
        thirdFragment = new JalgaonJilha();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        mainFragment = findViewById(R.id.mainFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment= null;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()== R.id.nav_rajkaran) {
                   Fragment one = new Gunhe();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragment,firstFragment);
                    transaction.commit();

                }else if (item.getItemId()== R.id.nav_corona) {
                    Fragment two = new JalgaonShahar();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragment,secondFragment);
                    transaction.commit();

                }else if (item.getItemId()== R.id.nav_gunhe) {
                    Fragment three = new JalgaonJilha();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragment,thirdFragment);
                    transaction.commit();

                }
                return true;
            }
        });


    }
}
