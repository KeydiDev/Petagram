package com.keydi.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.keydi.petagram.adapter.MascotaAdapter;
import com.keydi.petagram.adapter.PageAdapter;
import com.keydi.petagram.fragment.PerfilFragment;
import com.keydi.petagram.fragment.RecyclerViewFragment;
import com.keydi.petagram.pojo.Mascota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar miActionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miActionBar = findViewById(R.id.miActionBar);
        if (miActionBar != null){
            setSupportActionBar(miActionBar);
        }

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        setUpViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.buttonFav:
                Intent intent = new Intent(MainActivity.this, Favoritas.class);
                startActivity(intent);
                break;
            case R.id.menu_contacto:
                Intent intentContacto = new Intent(MainActivity.this, ContactoActivity.class);
                startActivity(intentContacto);
                break;
            case R.id.menu_about:
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));
                startActivity(in);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragments(){
       ArrayList<Fragment> fragments = new ArrayList<>();

       fragments.add(new RecyclerViewFragment());
       fragments.add(new PerfilFragment());

       return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.blue_home_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.cat_icon);
    }

}
