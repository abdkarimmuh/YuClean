package andara.heatcliff.yuclean;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.HashMap;

import andara.heatcliff.yuclean.Menu.AboutFragment;
import andara.heatcliff.yuclean.Menu.UserProfilFragment;
import andara.heatcliff.yuclean.Menu.HargaSampahFragment;
import andara.heatcliff.yuclean.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    TextView txtFullname, txtSaldo;
    SQLiteHandler sqLiteHandler;
    Context context;
    ImageView imgProfileUser;

    String imgUrl = "http://yuclean.andara-tech.com/images/user/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        SQLiteHandler sqLiteHandler = new SQLiteHandler(this);
//        HashMap<String, String> user = sqLiteHandler.getUserDetails();
//
//        txtUsername.setText(user.get("username"));
//        txtSaldo.setText(user.get("saldo"));

        /**
         *Setup the DrawerLayout and NavigationView
         */

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        View view = mNavigationView.getHeaderView(0);
        txtFullname = (TextView) view.findViewById(R.id.txtFullname);
        txtSaldo = (TextView) view.findViewById(R.id.txtSaldo);
        imgProfileUser = (ImageView) view.findViewById(R.id.imgProfileUser);

        sqLiteHandler = new SQLiteHandler(MainActivity.this);

        final HashMap<String, String> user = sqLiteHandler.getUserDetails();

        txtFullname.setText(user.get("nama"));

        if (user.get("saldo").equals("{}")) {
            txtSaldo.setText("Rp. " + "0");
        } else {
            txtSaldo.setText("Rp. " + user.get("saldo"));
        }


        Glide.with(this).load(imgUrl + user.get("foto"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_profile_bgputih)
                .into(imgProfileUser);


        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.header_menu) {
                    Intent profile = new Intent (MainActivity.this, UserProfilFragment.class);
                    startActivity(profile);
                }

                if (menuItem.getItemId() == R.id.nav_beranda) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_form) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new HargaSampahFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_user) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new UserProfilFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_bagikan) {
                    Toast.makeText(getApplicationContext(),"Bagikan", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plan");
//                    i.setType("image/png");
                    i.putExtra(Intent.EXTRA_SUBJECT, "YuClean");
                    i.putExtra(Intent.EXTRA_TEXT, "Kunjungi Halaman Kami di \n http://yuclean.andara-tech.com/");
//                    i.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://entertaiment.emaka.planetku/drawable/ic_icon_planetku"));
//                    i.putExtra(Intent.EXTRA_STREAM, Uri.parse(getIntent().getStringExtra("ikon planet")));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    Intent share = Intent.createChooser(i, "Bagikan YuClean");
                    share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(share);
                }

                if (menuItem.getItemId() == R.id.nav_about) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new AboutFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_logout) {
                    sqLiteHandler.deteleTable();

                    Log.v("Profil : ", user.toString());

                    Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(logout);
                    finish();
                }

                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }

}
