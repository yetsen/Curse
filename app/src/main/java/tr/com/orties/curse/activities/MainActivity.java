package tr.com.orties.curse.activities;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import tr.com.orties.curse.R;
import tr.com.orties.curse.fragments.TabFragment2;
import tr.com.orties.curse.services.LocatifyRestServiceUsage;
import tr.com.orties.curse.services.MessageService;
import tr.com.orties.curse.ui.CustomViewPager;
import tr.com.orties.curse.ui.TabListener;
import tr.com.orties.curse.adapters.TabsPagerAdapter;
import tr.com.orties.curse.services.LocationService;

public class MainActivity extends FragmentActivity {

    public int totalTabCount = 0;
    public static final String PREFS_NAME = "CurseFile";

    ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String [] tmpList;
    ArrayAdapter<String> arrayAdapter;
    ActionBarDrawerToggle drawerToggle;
    private static long back_pressed;
    ActionBar.Tab tab;
    CustomViewPager viewPager;
    TabsPagerAdapter tabsPagerAdapter;
    LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeActionBar();
        initializeNavigationDrawer();
        initializeTabs();
    }

    private void initializeActionBar() {
        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

    }

    private void initializeTabs() {
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager = (CustomViewPager) findViewById(R.id.pager);
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        viewPager.setPagingEnabled(true);
                        if (totalTabCount >= position) {
                            viewPager.setPagingEnabled(true);
                            getActionBar().setSelectedNavigationItem(position);
                        } else {
                            viewPager.setPagingEnabled(false);
                        }
                    }
                });
        viewPager.setAdapter(tabsPagerAdapter);
        addTab("User Info");
    }

    public void addTab(String tabName) {
        if (totalTabCount < 2) {
            ActionBar.TabListener tabListener = new TabListener(viewPager);
            tab = actionBar.newTab().setText(tabName);
            tab.setTabListener(tabListener);
            actionBar.addTab(tab);
            totalTabCount++;
        }
        viewPager.setCurrentItem(totalTabCount - 1);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawers();
        }else {
            if (back_pressed + 2000 > System.currentTimeMillis())
                super.onBackPressed();
            else
                Toast.makeText(getBaseContext(), R.string.close_warning, Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

    private void initializeNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        tmpList = getResources().getStringArray(R.array.nav_drawer_items);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tmpList);
        drawerList.setAdapter(arrayAdapter);
        drawerList.setSelector(android.R.color.holo_blue_dark);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawers();
            }
        });
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
    }

    public void updateMessageList() {
        TabFragment2 messageFragment = (TabFragment2)tabsPagerAdapter.getRegisteredFragment(1);
        messageFragment.updateMessageList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId()) {
            case R.id.gps:
                locationService = new LocationService(this);
                if(locationService.isGPSOn()) {
                    locationService.startGettingLocation();
                }else {
                    Toast.makeText(getBaseContext(), "GPS yok lan!!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
