package com.project_kreditmotor_ricky_ti21;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DataKreditorActivity extends AppCompatActivity implements OnClickListener{
    //Inisialisasi Objek + Variabel + Class
    Kreditor kreditor = new Kreditor();

    TableLayout tbKreditor;

    Button btTambahKreditor,btRefreshDataKreditor;

    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arrayKreditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kreditor);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Pemberian Nama Kmponen
        tbKreditor = (TableLayout) findViewById(R.id.tbKreditor);
        btTambahKreditor = (Button) findViewById(R.id.btTambahKreditor);
        btRefreshDataKreditor = (Button) findViewById(R.id.btRefreshDataKreditor);

        tampildataKreditor();
    }

    public void KlikbtTambahKreditor(View v) {

        tambahKreditor();
    }

    //Refresh Data
    public void KlikbtRefreshDataKreditor(View v) {
        /*Restart Activity*/
        finish();
        startActivity(getIntent());
    }

    public void tampildataKreditor() {
        TableRow barisTable = new TableRow(this);
        barisTable.setBackgroundColor(Color.BLACK);

        //Memberi ID Header Tabel
        //Textview viewHeader = new Textview(this);
        TextView viewHeaderNama = new TextView(this);
        viewHeaderNama.setGravity(Gravity.CENTER);
        TextView viewHeaderPekerjaan = new TextView(this);
        viewHeaderPekerjaan.setGravity(Gravity.CENTER);
        TextView viewHeaderTelp = new TextView(this);
        viewHeaderTelp.setGravity(Gravity.CENTER);
        TextView viewHeaderAlamat = new TextView(this);
        viewHeaderAlamat.setGravity(Gravity.CENTER);
        TextView viewHeaderAction = new TextView(this);
        viewHeaderAction.setGravity(Gravity.CENTER);

        //Memberi Nama Kolom HEADER
        //viewHeaderId.setText("id");
        viewHeaderNama.setText("Nama");
        viewHeaderPekerjaan.setText("Pekerjaan");
        viewHeaderTelp.setText("Telp");
        viewHeaderAlamat.setText("Alamat");
        viewHeaderAction.setText("Action");

        viewHeaderNama.setTextColor(Color.WHITE);
        viewHeaderPekerjaan.setTextColor(Color.WHITE);
        viewHeaderTelp.setTextColor(Color.WHITE);
        viewHeaderAlamat.setTextColor(Color.WHITE);
        viewHeaderAction.setTextColor(Color.WHITE);

        //viewHeaderId.setPadding(5, 1, 5, 1,); (left, top, right, bottom)
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderPekerjaan.setPadding(5, 1, 5, 1);
        viewHeaderTelp.setPadding(5, 1, 5, 1);
        viewHeaderAlamat.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        //barisTabel.addView(viewHeaderId);
        barisTable.addView(viewHeaderNama);
        barisTable.addView(viewHeaderPekerjaan);
        barisTable.addView(viewHeaderTelp);
        barisTable.addView(viewHeaderAlamat);
        barisTable.addView(viewHeaderAction);

        tbKreditor.addView(barisTable, new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

        try {
            arrayKreditor = new JSONArray(kreditor.tampilKreditor());

            //Menampilkan Header Kolom
            for (int i = 0; i < arrayKreditor.length(); i++) {
                JSONObject jsonChildNode = arrayKreditor.getJSONObject(i);

                //Ambil Data dari Nama Tabel Database
                String idkreditor = jsonChildNode.optString("idkreditor");
                String nama = jsonChildNode.optString("nama");
                String pekerjaan = jsonChildNode.optString("pekerjaan");
                String telp = jsonChildNode.optString("telp");
                String alamat = jsonChildNode.optString("alamat");

                System.out.println("idkreditor :" + idkreditor);
                System.out.println("nama :" + nama);
                System.out.println("pekerjaan :" + pekerjaan);
                System.out.println("telp :" + telp);
                System.out.println("alamat :" + alamat);

                barisTable = new TableRow(this);

                if (i % 2 == 0) {
                    barisTable.setBackgroundColor(Color.LTGRAY);
                }

                //TextView viewId = new TextView(this);
                // viewId.setText(id);
                // viewId.setPadding(5, 1, 5, 1);
                // barisTabel.addView(viewId);

                TextView viewNama = new TextView(this);
                viewNama.setText(nama);
                viewNama.setPadding(5, 1, 5, 1);
                barisTable.addView(viewNama);

                TextView viewPekerjaan = new TextView(this);
                viewPekerjaan.setText(pekerjaan);
                viewPekerjaan.setPadding(5, 1, 5, 1);
                barisTable.addView(viewPekerjaan);

                TextView viewTelp = new TextView(this);
                viewTelp.setText(telp);
                viewTelp.setPadding(5, 1, 5, 1);
                barisTable.addView(viewTelp);

                TextView viewAlamat = new TextView(this);
                viewAlamat.setText(alamat);
                viewAlamat.setPadding(5, 1, 5, 1);
                barisTable.addView(viewAlamat);

                //Membuat Button Edit pada Baris
                buttonEdit.add(i, new Button(this));
                buttonEdit.get(i).setId(Integer.parseInt(idkreditor));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                barisTable.addView(buttonEdit.get(i));

                //Membuat Button Delete Pada Baris
                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(idkreditor));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTable.addView(buttonDelete.get(i));

                tbKreditor.addView(barisTable, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Hapus Kreditor Berdasarkan ID
    public void deleteKreditor(int idkreditor) {
        kreditor.deleteKreditor(idkreditor);

        /* Restart Activity */
        finish();
        startActivity(getIntent());
    }

    //Ambil Data Kreditor Berdasarkan Id
    public void getKreditorByNama(int idkreditor) {
        String idkreditorEdit = null;
        String namaEdit = null;
        String pekerjaanEdit = null;
        String telpEdit = null;
        String alamatEdit = null;

        JSONArray arrayPersonal;

        try {
            arrayPersonal = new JSONArray(kreditor.getKreditorByNama(idkreditor));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                idkreditorEdit = jsonChildNode.optString("idkreditor");
                namaEdit = jsonChildNode.optString("nama");
                pekerjaanEdit = jsonChildNode.optString("pekerjaan");
                telpEdit = jsonChildNode.optString("telp");
                alamatEdit = jsonChildNode.optString("alamat");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);


        //Membuat Edit Text di Alert Builder
        final EditText editIdKreditor = new EditText(this);
        editIdKreditor.setText(idkreditorEdit);
        //layoutInput.addView(editKdKreditor);

        //Membuat Edit Text di Alert Builder
        final EditText editNama = new EditText(this);
        editNama.setText(namaEdit);
        layoutInput.addView(editNama);

        //Membuat Edit Text di Alert Builder
        final EditText editPekerjaan = new EditText(this);
        editPekerjaan.setText(pekerjaanEdit);
        layoutInput.addView(editPekerjaan);

        final EditText editTelp = new EditText(this);
        editTelp.setText(telpEdit);
        layoutInput.addView(editTelp);

        final EditText editAlamat = new EditText(this);
        editAlamat.setText(alamatEdit);
        layoutInput.addView(editAlamat);

        AlertDialog.Builder builderEditKreditor = new AlertDialog.Builder(this);
        builderEditKreditor.setTitle("Update Kreditor");
        builderEditKreditor.setView(layoutInput);
        builderEditKreditor.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String idkreditor = editIdKreditor.getText().toString();
                String nama = editNama.getText().toString();
                String pekerjaan = editPekerjaan.getText().toString();
                String telp = editTelp.getText().toString();
                String alamat = editAlamat.getText().toString();

                System.out.println("IdKreditor : " + idkreditor + "Nama : " + nama + "Pejerkaan : " + pekerjaan + "Telp : " + telp + "Alamat : " + alamat);

                String laporan = kreditor.updateKreditor(idkreditor, nama, pekerjaan, telp, alamat);

                Toast.makeText(DataKreditorActivity.this, laporan, Toast.LENGTH_SHORT).show();

                /* restart activity */
                finish();
                startActivity(getIntent());
            }
        });

        //Membuat Button Cancel pada Builder
        builderEditKreditor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        builderEditKreditor.show();
    }

    //Metode Tambah Kreditor
    public void tambahKreditor() {
        /* Layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editNama = new EditText(this);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editPekerjaan = new EditText(this);
        editPekerjaan.setHint("Pekerjaan");
        layoutInput.addView(editPekerjaan);

        final EditText editTelp = new EditText(this);
        editTelp.setHint("Telp");
        layoutInput.addView(editTelp);

        final EditText editAlamat = new EditText(this);
        editAlamat.setHint("Alamat");
        layoutInput.addView(editAlamat);

        AlertDialog.Builder builderInsertKreditor = new AlertDialog.Builder(this);

        //builderInsertKreditor.setIcon(R.drawable.batagrams);
        builderInsertKreditor.setTitle("Insert Kreditor");
        builderInsertKreditor.setView(layoutInput);
        builderInsertKreditor.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String nama = editNama.getText().toString();
                String pekerjaan = editPekerjaan.getText().toString();
                String telp = editTelp.getText().toString();
                String alamat = editAlamat.getText().toString();

                System.out.println("Nama :" + nama + "Pekerjaan : " + pekerjaan + "Telp : " + telp + "Alamat : " + alamat);

                String laporan = kreditor.insertKreditor(nama,pekerjaan,telp,alamat);

                Toast.makeText(DataKreditorActivity.this, laporan, Toast.LENGTH_SHORT).show();

                /* Restart Activity */
                finish();
                startActivity(getIntent());
            }
        });
        builderInsertKreditor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        builderInsertKreditor.show();
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        for (int i = 0; i < buttonEdit.size(); i++) {
            String Idkreditor;
            /* jika yang diklik adalah button edit */
            if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                int idkreditor = buttonEdit.get(i).getId();
                getKreditorByNama(idkreditor);
            }
            /* jika yang diklik adalah button delete */
            else if (view.getId() ==  buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                int idkreditor = buttonDelete.get(i).getId();
                deleteKreditor(idkreditor);
            }
        }
    }
}

