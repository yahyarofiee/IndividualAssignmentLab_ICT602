package com.example.dividendcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class CalculateActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        EditText investedAmount = findViewById(R.id.investedAmount);
        EditText dividendRate = findViewById(R.id.dividendRate);
        EditText monthsInvested = findViewById(R.id.monthsInvested);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultText = findViewById(R.id.resultText);

        // Set Toolbar sebagai ActionBar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Pasang toggle hamburger icon ke drawer
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                drawerLayout.closeDrawers();
                return true;
            } else if (id == R.id.nav_calculate) {
                drawerLayout.closeDrawers();
                return true;
            } else if (id == R.id.nav_about) {
                startActivity(new Intent(this, AboutActivity.class));
                drawerLayout.closeDrawers();
                return true;
            }
            return false;
        });

        calculateButton.setOnClickListener(v -> {
            String amountStr = investedAmount.getText().toString();
            String rateStr = dividendRate.getText().toString();
            String monthsStr = monthsInvested.getText().toString();

            if (amountStr.isEmpty() || rateStr.isEmpty() || monthsStr.isEmpty()) {
                resultText.setText("Please fill all fields");
                return;
            }

            try {
                double amount = Double.parseDouble(amountStr);
                double rate = Double.parseDouble(rateStr);
                int months = Integer.parseInt(monthsStr);

                if (amount <= 0) {
                    resultText.setText("Invalid invested amount");
                    return;
                }
                if (rate <= 0) {
                    resultText.setText("Invalid dividend rate");
                    return;
                }
                if (months <= 0 || months > 12) {
                    resultText.setText("Months must be between 1 and 12");
                    return;
                }

                double monthlyDividend = (rate / 100 / 12) * amount;
                double totalDividend = monthlyDividend * months;

                resultText.setText(String.format(
                        "Monthly Dividend: RM %.2f\nTotal Dividend: RM %.2f",
                        monthlyDividend, totalDividend
                ));
            } catch (NumberFormatException e) {
                resultText.setText("Invalid input format");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}