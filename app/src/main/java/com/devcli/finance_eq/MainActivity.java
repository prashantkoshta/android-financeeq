package com.devcli.finance_eq;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.devcli.finance_eq.calculator.SimpleInterest;
import com.devcli.finance_eq.service.ServiceHandler;
import com.devcli.finance_eq.vo.Calculator;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener {

    private static final String TAG = MainActivity.class.getName();
    private ListView listView;
    private List<Calculator> list;
    private ArrayAdapter<Calculator> adapter;
    private ResultReceiver receiver;
    private ProgressBar _progressBar;
    FragmentManager _fragmentManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _fragmentManager = getFragmentManager();
        _fragmentManager.addOnBackStackChangedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        _progressBar = (ProgressBar) findViewById(R.id.progressBar);


        listView = (ListView) findViewById(R.id.listViewEq);


        this.list = new ArrayList<Calculator>();
        adapter = new ArrayAdapter<Calculator>(MainActivity.this, R.layout.list_row_item, R.id.lsRowText, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Calculator calc = (Calculator) adapterView.getSelectedItem();
                Log.i("####", adapterView.toString());
                addFragemnt();
            }
        });

        if (savedInstanceState != null) {
            adapter.clear();
            list = (ArrayList<Calculator>) savedInstanceState.getSerializable("list");
            adapter.addAll(list);
            _progressBar.setVisibility(View.INVISIBLE);
        } else {
            ServiceHandler sHandler = new ServiceHandler();
            Intent intent = new Intent(this, ServiceHandler.class);
            intent.putExtra("url", "https://app-service-fn.herokuapp.com/fneqapi/listofequation");
            startService(intent);
            _progressBar.setVisibility(View.VISIBLE);
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void addFragemnt(){
        SimpleInterest simpleInterest = new SimpleInterest();
       // FragmentTransaction fragmentTransaction;
        _fragmentManager.beginTransaction().add(R.id.frag_container, simpleInterest, "SI").addToBackStack("AASds").commit();
        _fragmentManager.executePendingTransactions();
            Log.i(">>>>>>",">>>"+getFragmentManager().getBackStackEntryCount());

        //fragmentTransaction.setCustomAnimations(0, R.anim.slide_out_top);
        //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

    }

    @Override
    protected void onStart() {
        receiver = new ResultReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("GET_LIST");
        registerReceiver(receiver, intentFilter);
        //
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", (ArrayList<Calculator>) list);
    }

    @Override
    public void onBackPressed() {
        Log.i(">>>>>>",">>2222>"+_fragmentManager.getBackStackEntryCount());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(_fragmentManager.getBackStackEntryCount()>0){
            _fragmentManager.popBackStack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackStackChanged() {
        SimpleInterest simpleInterest = (SimpleInterest) _fragmentManager.findFragmentByTag("SI");
        FragmentTransaction fragmentTransaction = _fragmentManager.beginTransaction();

        if (simpleInterest != null) {
            fragmentTransaction.remove(simpleInterest);
            //fragmentTransaction.addToBackStack("REM_SI");
            fragmentTransaction.commit();
        } else {

        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


    private class ResultReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            adapter.clear();
            list = (ArrayList<Calculator>) intent.getSerializableExtra("result");
            adapter.addAll(list);
            _progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
