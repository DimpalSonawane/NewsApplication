package com.appdroid.lokshahiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.appdroid.lokshahiapplication.Activities.BottomNav;
import com.appdroid.lokshahiapplication.Activities.ENewsPaper;
import com.appdroid.lokshahiapplication.Activities.ShowingVillagesData;
import com.appdroid.lokshahiapplication.Adapter.TabsAdapter;
import com.appdroid.lokshahiapplication.Fragments.Gunhe;
import com.appdroid.lokshahiapplication.Fragments.JalgaonJilha;
import com.appdroid.lokshahiapplication.Fragments.JalgaonShahar;
import com.appdroid.lokshahiapplication.Fragments.NewsFragment;
import com.appdroid.lokshahiapplication.Fragments.Rajkaran;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SSSSSSSSSSAAAAA";
    TabLayout tabLayout;
    public static ViewPager viewPager;
    TabsAdapter adapter;

    Fragment newsFragment,gunhe,jalgaonShahar,rajKaran,jalgaonJilha;

    String action;
    ImageView next_btn;
    private NavigationView navigationView;
    //private BottomNavigationView bottomNavigationView;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.pager);

        action =  getIntent().getAction();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigationView);
        //bottomNavigationView = findViewById(R.id.bottomNavigationView);
        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getColor(R.color.black));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        newsFragment = new NewsFragment();
        newsFragment.getRetainInstance();

        adapter = new TabsAdapter(getSupportFragmentManager());


        jalgaonShahar = new JalgaonShahar();
        jalgaonJilha = new JalgaonJilha();
        rajKaran = new Rajkaran();
        gunhe = new Gunhe();

        adapter.addFragment(newsFragment, "मुख्य पान");
        adapter.addFragment(jalgaonShahar, "राष्ट्रीय");
        adapter.addFragment(jalgaonJilha, "महाराष्ट्र");
        adapter.addFragment(rajKaran,"जळगाव");
        adapter.addFragment(gunhe,"संपादकीय");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);

        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawer);

        //getData();

        next_btn = findViewById(R.id.next_btn);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BottomNav.class);
                startActivity(intent);

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                if (item.getItemId()== R.id.nav_prashasan) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Intent i = new Intent(getApplicationContext(), ENewsPaper.class);
                    startActivity(i);
                } else if (item.getItemId()== R.id.nav_rajkaran) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.brecking_cat));
                    startActivity(intent);
                }else if (item.getItemId()== R.id.nav_corona) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.vishesh));
                    startActivity(intent);
                }else if (item.getItemId()== R.id.nav_gunhe) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.jalgoanShahar));
                    startActivity(intent);
                }else if (item.getItemId()== R.id.nav_shaikshanik) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.nokri));
                    startActivity(intent);
                }else if (item.getItemId()== R.id.nav_samajik) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.havaman));
                    startActivity(intent);
                }else if (item.getItemId()== R.id.nav_sone_chandi) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.sports));
                    startActivity(intent);
                }

                else if (item.getItemId()== R.id.nav_jalgaon) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.sports));
                    startActivity(intent);
                }else if (item.getItemId()== R.id.nav_jalgaonJilha) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.lekh));
                    startActivity(intent);
                }else if (item.getItemId()== R.id.nav_bhusawal) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.bhusaval));
                    startActivity(intent);
                }else if (item.getItemId()== R.id.nav_chalisgaon) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.chalisgaon));
                    startActivity(intent);
                }else if (item.getItemId()== R.id.nav_raver) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    intent =new Intent(MainActivity.this, ShowingVillagesData.class);
                    intent.putExtra("link",getString(R.string.raver));
                    startActivity(intent);
                }

                return true;
            }
        });
    }

}