package com.example.dividendcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");

        // Sambungan ke layout komponen
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        Button githubLinkButton = findViewById(R.id.githubLinkButton);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);

        // Set Toolbar sebagai ActionBar
        setSupportActionBar(toolbar);

        // Toggle ikon hamburger
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Navigasi drawer item
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                drawerLayout.closeDrawers();
                return true;
            } else if (id == R.id.nav_calculate) {
                startActivity(new Intent(this, CalculateActivity.class));
                drawerLayout.closeDrawers();
                return true;
            } else if (id == R.id.nav_about) {
                drawerLayout.closeDrawers();
                return true; // sudah di sini
            }
            return false;
        });

        // Apabila button GitHub ditekan
        githubLinkButton.setOnClickListener(v -> {
            String url = "https://github.com/yahyarofiee/IndividualAssignmentLab_ICT602.git";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }

    // Aktifkan toggle hamburger menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
