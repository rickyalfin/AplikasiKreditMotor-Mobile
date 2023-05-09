package com.project_kreditmotor_ricky_ti21;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

@SuppressLint("NewApi")
public class DataPetugasActivity extends AppCompatActivity implements OnClickListener{
    //Inisialisasi Objek + Variabel + Class
    Petugas petugas = new Petugas();

    TableLayout tbPetugas;

    Button btTambahPetugas,btRefreshDataPetugas;

    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arrayPetugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_petugas);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Pemberian Nama Kmponen
        tbPetugas = (TableLayout) findViewById(R.id.tbPetugas);
        btTambahPetugas = (Button) findViewById(R.id.btTambahPetugas);
        btRefreshDataPetugas = (Button) findViewById(R.id.btRefreshDataPetugas);

        tampildataPetugas();
    }

    public void KlikbtTambahPetugas(View v) {
        tambahPetugas();
    }

    //Refresh Data
    public void KlikRefreshDataPetugas(View v) {
        /*Restart Activity*/
        finish();
        startActivity(getIntent());
    }

    public void tampildataPetugas() {
        TableRow barisTable = new TableRow(this);
        barisTable.setBackgroundColor(Color.BLACK);

        //Memberi ID Header Tabel
        //Textview viewHeader = new Textview(this);
        TextView viewHeaderKdPetugas = new TextView(this);
        viewHeaderKdPetugas.setGravity(Gravity.CENTER);
        TextView viewHeaderNama = new TextView(this);
        viewHeaderNama.setGravity(Gravity.CENTER);
        TextView viewHeaderJabatan = new TextView(this);
        viewHeaderJabatan.setGravity(Gravity.CENTER);
        TextView viewHeaderAction = new TextView(this);
        viewHeaderAction.setGravity(Gravity.CENTER);

        //Memberi Nama Kolom HEADER
        //viewHeaderId.setText("id");
        viewHeaderKdPetugas.setText("KdPetugas");
        viewHeaderNama.setText("Nama");
        viewHeaderJabatan.setText("Jabatan");
        viewHeaderAction.setText("Action");

        viewHeaderKdPetugas.setTextColor(Color.WHITE);
        viewHeaderNama.setTextColor(Color.WHITE);
        viewHeaderJabatan.setTextColor(Color.WHITE);
        viewHeaderAction.setTextColor(Color.WHITE);

        //viewHeaderId.setPadding(5, 1, 5, 1,); (left, top, right, bottom)
        viewHeaderKdPetugas.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderJabatan.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        //barisTabel.addView(viewHeaderId);
        barisTable.addView(viewHeaderKdPetugas);
        barisTable.addView(viewHeaderNama);
        barisTable.addView(viewHeaderJabatan);
        barisTable.addView(viewHeaderAction);

        tbPetugas.addView(barisTable, new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

        try {
            arrayPetugas = new JSONArray(petugas.tampilPetugas());

            //Menampilkan Header Kolom
            for (int i = 0; i < arrayPetugas.length(); i++) {
                JSONObject jsonChildNode = arrayPetugas.getJSONObject(i);

                //Ambil Data dari Nama Tabel Database
                String idpetugas = jsonChildNode.optString("idpetugas");
                String kdpetugas = jsonChildNode.optString("kdpetugas");
                String nama = jsonChildNode.optString("nama");
                String jabatan = jsonChildNode.optString("jabatan");

                System.out.println("idpetugas :" + idpetugas);
                System.out.println("kdpetugas :" + kdpetugas);
                System.out.println("nama :" + nama);
                System.out.println("jabatan :" + jabatan);

                barisTable = new TableRow(this);

                if (i % 2 == 0) {
                    barisTable.setBackgroundColor(Color.LTGRAY);
                }

                //TextView viewId = new TextView(this);
                // viewId.setText(id);
                // viewId.setPadding(5, 1, 5, 1);
                // barisTabel.addView(viewId);

                TextView viewKdPetugas = new TextView(this);
                viewKdPetugas.setGravity(Gravity.CENTER);
                viewKdPetugas.setText(kdpetugas);
                viewKdPetugas.setPadding(5, 1, 5, 1);
                barisTable.addView(viewKdPetugas);

                TextView viewNama = new TextView(this);
                viewNama.setText(nama);
                viewNama.setPadding(5, 1, 5, 1);
                barisTable.addView(viewNama);

                TextView viewJabatan = new TextView(this);
                viewJabatan.setText(jabatan);
                viewJabatan.setPadding(5, 1, 5, 1);
                barisTable.addView(viewJabatan);

                //Membuat Button Edit pada Baris
                buttonEdit.add(i, new Button(this));
                buttonEdit.get(i).setId(Integer.parseInt(idpetugas));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                barisTable.addView(buttonEdit.get(i));

                //Membuat Button Delete Pada Baris
                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(idpetugas));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTable.addView(buttonDelete.get(i));

                tbPetugas.addView(barisTable, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Hapus Petugas Berdasarkan ID
    public void deletePetugas(int idpetugas) {
        petugas.deletePetugas(idpetugas);

        /* Restart Activity */
        finish();
        startActivity(getIntent());
    }

    //Ambil Data Petugas Berdasarkan Id
    public void getPetugasByKdpetugas(int idpetugas) {
        String idpetugasEdit = null;
        String kdPetugasEdit = null;
        String namaEdit = null;
        String jabatanEdit = null;

        JSONArray arrayPersonal;

        try {
            arrayPersonal = new JSONArray(petugas.getPetugasByKdpetugas(idpetugas));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                idpetugasEdit = jsonChildNode.optString("idpetugas");
                kdPetugasEdit = jsonChildNode.optString("kdpetugas");
                namaEdit = jsonChildNode.optString("nama");
                jabatanEdit = jsonChildNode.optString("jabatan");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        //Buat id Tersembunyi di alertbuilder
        final TextView viewKdPetugas = new TextView(this);
        viewKdPetugas.setText("Kode ="+String.valueOf(kdPetugasEdit));
        viewKdPetugas.setBackgroundColor(Color.TRANSPARENT);
        viewKdPetugas.setTextColor(Color.WHITE);
        viewKdPetugas.setTextSize(20);
        layoutInput.addView(viewKdPetugas);

        //Membuat Edit Text di Alert Builder
        final EditText editIdPetugas = new EditText(this);
        editIdPetugas.setText(idpetugasEdit);
        //layoutInput.addView(editKdPetugas);

        //Membuat Edit Text di Alert Builder
        final EditText editNama = new EditText(this);
        editNama.setText(namaEdit);
        layoutInput.addView(editNama);

        //Membuat Edit Text di Alert Builder
        final EditText editJabatan = new EditText(this);
        editJabatan.setText(jabatanEdit);
        layoutInput.addView(editJabatan);

        AlertDialog.Builder builderEditPetugas = new AlertDialog.Builder(this);
        builderEditPetugas.setTitle("Update Petugas");
        builderEditPetugas.setView(layoutInput);
        builderEditPetugas.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String idpetugas = editIdPetugas.getText().toString();
                String kdpetugas = viewKdPetugas.getText().toString();
                String nama = editNama.getText().toString();
                String jabatan = editJabatan.getText().toString();

                System.out.println("IdPetugas : " + idpetugas + "KdPetugas : " + kdpetugas + "Nama : " + nama + "Jabatan : " + jabatan);

                String laporan = petugas.updatePetugas(idpetugas, kdpetugas, nama, jabatan);

                Toast.makeText(DataPetugasActivity.this, laporan, Toast.LENGTH_SHORT).show();

                /* restart activity */
                finish();
                startActivity(getIntent());
            }
        });

        //Membuat Button Cancel pada Builder
        builderEditPetugas.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        builderEditPetugas.show();
    }

    //Metode Tambah Petugas
    public void tambahPetugas() {
        /* Layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editKdPetugas = new EditText(this);
        editKdPetugas.setHint("KdPetugas");
        layoutInput.addView(editKdPetugas);

        final EditText editNama = new EditText(this);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editJabatan = new EditText(this);
        editJabatan.setHint("Jabatan");
        layoutInput.addView(editJabatan);

        AlertDialog.Builder builderInsertPetugas = new AlertDialog.Builder(this);

        //builderInsertPetugas.setIcon(R.drawable.batagrams);
        builderInsertPetugas.setTitle("Insert Petugas");
        builderInsertPetugas.setView(layoutInput);
        builderInsertPetugas.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String kdpetugas = editKdPetugas.getText().toString();
                String nama = editNama.getText().toString();
                String jabatan = editJabatan.getText().toString();

                System.out.println("KdPetugas : " + kdpetugas + "Nama :" + nama + "Jabatan : " + jabatan);

                String laporan = petugas.insertPetugas(kdpetugas,nama,jabatan);

                Toast.makeText(DataPetugasActivity.this, laporan, Toast.LENGTH_SHORT).show();

                /* Restart Activity */
                finish();
                startActivity(getIntent());
            }
        });
        builderInsertPetugas.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        builderInsertPetugas.show();
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        for (int i = 0; i < buttonEdit.size(); i++) {
            String kdpetugas;
            /* jika yang diklik adalah button edit */
            if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                int idpetugas = buttonEdit.get(i).getId();
                getPetugasByKdpetugas(idpetugas);
            }
            /* jika yang diklik adalah button delete */
            else if (view.getId() ==  buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                int idpetugas = buttonDelete.get(i).getId();
                deletePetugas(idpetugas);
            }
        }
    }
}
