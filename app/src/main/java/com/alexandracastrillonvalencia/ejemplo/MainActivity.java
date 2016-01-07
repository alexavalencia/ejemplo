package com.alexandracastrillonvalencia.ejemplo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.Parse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] menu;

    private DrawerLayout drawerLayout;

    private ListView listView;

    private ActionBarDrawerToggle drawerToggle;

    private android.support.v4.app.Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

        /*ParseObject gameScore = new ParseObject("GameScore");
        gameScore.put("score",1338);
        gameScore.put("playerName","Sean Plott");
        gameScore.put("cheatMode",false);
        gameScore.saveInBackground();*/
        menu = getResources().getStringArray(R.array.menu);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);

        ArrayList<list_menu> items = new ArrayList<list_menu>();
        items.add(new list_menu(menu[0], R.drawable.ic_account));
        items.add(new list_menu(menu[1], R.drawable.ic_film));
        items.add(new list_menu(menu[2], R.drawable.ic_theater));
        items.add(new list_menu(menu[3], R.drawable.ic_contact));

        listView.setAdapter(new List_Adapter(this, items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                fragment = null;
                Toast.makeText(getApplicationContext(), menu[position], Toast.LENGTH_SHORT).show();//Toast.makeText(context, text, duration).show();
                int op;
                op = position;
                switch (op) {

                    case 0:
                        fragment = new FilmsFragment();

                        break;
                    case 1:
                        fragment = new FilmsFragment();

                        break;
                    case 2:
                        fragment = new FilmsFragment();

                        break;
                    case 3:
                        fragment = new FilmsFragment();

                        break;


                }

                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
                listView.setItemChecked(op, true);
                getSupportActionBar().setTitle(menu[op]);

                drawerLayout.closeDrawer(listView);


            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_menu, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // getActionBar().setTitle(mTitle);
                // invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                //  invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
