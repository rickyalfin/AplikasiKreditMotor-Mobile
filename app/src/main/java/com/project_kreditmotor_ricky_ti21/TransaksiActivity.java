package com.project_kreditmotor_ricky_ti21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TransaksiActivity extends AppCompatActivity {

    LinearLayout TombolPengajuanKredit;
    LinearLayout TombolPembayaranAngsuran;
    LinearLayout TombolDataPengajuanKredit;
    LinearLayout TombolDataPembayaranAngsuran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        TombolPengajuanKredit = findViewById(R.id.lyPengajuanKredit);

        TombolPengajuanKredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(TransaksiActivity.this, PengajuanKreditActivity.class);
                startActivity(open);
            }
        });

        TombolPembayaranAngsuran = findViewById(R.id.lyPembayaranAngsuran);

        TombolPembayaranAngsuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(TransaksiActivity.this, PembayaranAngsuranActivity.class);
                startActivity(open);
            }
        });

        TombolDataPengajuanKredit = findViewById(R.id.lyDataPengajuanKredit);

        TombolDataPengajuanKredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(TransaksiActivity.this, DataPengajuanKreditActivity.class);
                startActivity(open);
            }
        });

        TombolDataPembayaranAngsuran = findViewById(R.id.lyDataPembayaranAngsuran);

        TombolDataPembayaranAngsuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(TransaksiActivity.this, DataPembayaranAngsuranActivity.class);
                startActivity(open);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_transaksi);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_cari:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_transaksi:
                    return true;
                case R.id.bottom_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }
}