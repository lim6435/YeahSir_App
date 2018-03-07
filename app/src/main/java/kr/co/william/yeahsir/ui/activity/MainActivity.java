package kr.co.william.yeahsir.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import kr.co.william.yeahsir.ui.EndDrawerToggle;
import kr.co.william.yeahsir.R;
import kr.co.william.yeahsir.data.IntentCode;
import kr.co.william.yeahsir.ui.fragment.MainFragment;
import kr.co.william.yeahsir.utils.CommonUtil;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ImageView.OnClickListener {

//    private ActivityMainBinding mainBinding;
    private DrawerLayout drawer;
    private EndDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new EndDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(Gravity.END)) {
                    drawer.closeDrawer(Gravity.END);
                } else {
                    drawer.openDrawer(Gravity.END);
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView iv_toggle = (ImageView) findViewById(R.id.iv_toggle);
        iv_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("[sheotest] iv_toggle 클릭");
//                drawerToggle.toggle();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("알림").setMessage("메뉴 준비중 입니다.").setPositiveButton("확인", null);
                AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
            }
        });

        CommonUtil.callFragment(MainActivity.this, new MainFragment(), null, true);

//        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String menuType = "";
        int id = item.getItemId();

        if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.menu_1) {
            menuType = IntentCode.INTENT_VALUE_MENU_1;
        } else if (id == R.id.menu_2) {
            menuType = IntentCode.INTENT_VALUE_MENU_2;
        } else if (id == R.id.menu_3) {
            menuType = IntentCode.INTENT_VALUE_MENU_3;
        } else if (id == R.id.menu_4) {
            menuType = IntentCode.INTENT_VALUE_MENU_4;
        } else if (id == R.id.menu_5) {
            menuType = IntentCode.INTENT_VALUE_MENU_5;
        } else if (id == R.id.menu_6) {
            menuType = IntentCode.INTENT_VALUE_MENU_6;
        } else if (id == R.id.menu_7) {
            menuType = IntentCode.INTENT_VALUE_MENU_7;
        } else if (id == R.id.menu_8) {
            menuType = IntentCode.INTENT_VALUE_MENU_8;
        }

        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        intent.putExtra(IntentCode.INTENT_KEY_MENU_TYPE, menuType);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}
