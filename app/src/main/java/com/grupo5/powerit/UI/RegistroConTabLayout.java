package com.grupo5.powerit.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.grupo5.powerit.R;

import java.util.ArrayList;
import java.util.List;

public class RegistroConTabLayout extends AppCompatActivity {

    private TabLayout tlTipos;
    private ViewPager vpTipos;

    private FragmentCliente cliente;
    private FragmentSocio socio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_con_tab_layout);

        tlTipos = findViewById(R.id.tlTipos);
        vpTipos = findViewById(R.id.vpTipos);

        cliente = new FragmentCliente();
        socio = new FragmentSocio();

        tlTipos.setupWithViewPager(vpTipos);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        adapter.addFragment(cliente, "cliente");
        adapter.addFragment(socio, "socio");
        vpTipos.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> titles = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public CharSequence getPageTitle(int position){
            return titles.get(position);
        }
    }
}