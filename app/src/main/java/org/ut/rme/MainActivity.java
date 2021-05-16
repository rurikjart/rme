package org.ut.rme;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.ut.rme.adapter.TabsFragmentAdapter;
import org.ut.rme.dto.RemindDTO;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    TabsFragmentAdapter adapter;
   // private TabLayout tabLayout; удалили его так как он используется только в одном месте распостранять его на весь клас нет смысла

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Default);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        // точка входа для логики, создаем тул бар
        initToolbar();
        initNavigationView();
        initTabs();


    }


    private void initToolbar() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);

    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        //инициализируем адаптер
        viewPager.setAdapter(adapter);
            new RemindMeTask().execute();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.actionNotificationItem:
                        showNotificationTab();
                }
                return true;
            }
        });

    }


    private void showNotificationTab() {
        viewPager.setCurrentItem(Constants.TAB_TWO);
    }

    private class RemindMeTask extends AsyncTask<Void, Void, RemindDTO> {

        @Override
        protected RemindDTO doInBackground(Void... voids) {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());



            return template.getForObject(Constants.URL.GET_REMIND_ITEM, RemindDTO.class);
        }

        @Override
        protected void onPostExecute(RemindDTO remindDTO) {
            List<RemindDTO> list = new ArrayList<>();
            list.add(remindDTO);
            adapter.setData(list);
        }
    }


}