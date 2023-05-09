package com.project_kreditmotor_ricky_ti21;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


@SuppressLint("NewApi")
public class DataMotorActivity extends AppCompatActivity implements View.OnClickListener{

    //Inisialisasi Objek + Variabel + Class
    Motor petugas = new Motor();

    TableLayout tbPetugas;

    Button btTambahPetugas, btRefreshDataPetugas;

    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arrayPetugas;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_motor);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Pemberian Nama Komponen
        tbPetugas = (TableLayout) findViewById(R.id.tbPetugas);
        btTambahPetugas = (Button) findViewById(R.id.btTambahPetugas);
        btRefreshDataPetugas = (Button) findViewById(R.id.btRefreshDataPetugas);

        tampildataPetugas();
    }

    //Metode KlikTambahPetugas
    public void KlikbtTambahPetugas(View v) {
        tambahPetugas();
    }

    ;


    //Refresh Data
    public void KlikRefreshDataPetugas(View v) {
        /* restart activity */
        finish();
        startActivity(getIntent());
    }

    //Tampil data Petugas
    public void tampildataPetugas() {
        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.BLACK);

        //Memberi ID Header Tabel
        //TextView viewHeaderId = new TextView(this);
        TextView viewHeaderKdPetugas = new TextView(this);
        viewHeaderKdPetugas.setGravity(Gravity.CENTER);
        TextView viewHeaderNama = new TextView(this);
        viewHeaderNama.setGravity(Gravity.CENTER);
        TextView viewHeaderJabatan = new TextView(this);
        viewHeaderJabatan.setGravity(Gravity.CENTER);
        TextView viewHeaderAction = new TextView(this);
        viewHeaderAction.setGravity(Gravity.CENTER);

        //Memberi Nama kolom HEADER
        //ViewHeaderId.setText("Id")
        viewHeaderKdPetugas.setText("KdPetugas");
        viewHeaderNama.setText("Nama");
        viewHeaderJabatan.setText("Jabatan");
        viewHeaderAction.setText("Action");

        //viewHeaderId.setPadding(5, 1, 5, 1,); (left, top, right, bottom)
        viewHeaderKdPetugas.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderJabatan.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        //barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderKdPetugas);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderJabatan);
        barisTabel.addView(viewHeaderAction);

        tbPetugas.addView(barisTabel, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        try {
            arrayPetugas = new JSONArray(petugas.tampilPetugas());

            //Menampilkan Header Kolom
            for (int i = 0; i < arrayPetugas.length(); i++) {
                JSONObject jsonChildNode = arrayPetugas.getJSONObject(i);
                //ambil data dari nama tabel database
                String idpetugas = jsonChildNode.optString("idpetugas");
                String kdpetugas = jsonChildNode.optString("kdpetugas");
                String nama = jsonChildNode.optString("nama");
                String jabatan = jsonChildNode.optString("jabatan");

                System.out.println("idpetugas :" + idpetugas);
                System.out.println("kdpetugas :" + kdpetugas);
                System.out.println("nama :" + nama);
                System.out.println("jabatan :" + jabatan);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                //TextView viewId = new TextView(this);
                //viewId.setText(id);
                //viewId.setPadding(5, 1, 5, 1);
                //barisTabel.addView(viewId);

                TextView viewKdPetugas = new TextView(this);
                viewKdPetugas.setGravity(Gravity.CENTER);
                viewKdPetugas.setText(kdpetugas);
                viewKdPetugas.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewKdPetugas);

                TextView viewNama = new TextView(this);
                viewNama.setText(nama);
                viewNama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNama);

                TextView viewJabatan = new TextView(this);
                viewJabatan.setGravity(Gravity.CENTER);
                viewJabatan.setText(jabatan);
                viewJabatan.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewJabatan);

                //Membuat Button Edit pada Baris
                buttonEdit.add(i, new Button(this));
                buttonEdit.get(i).setId(Integer.parseInt(idpetugas));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                barisTabel.addView(buttonEdit.get(i));

                //Membuat Button Delete pada Baris
                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(idpetugas));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTabel.addView(buttonDelete.get(i));

                tbPetugas.addView(barisTabel, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Hapus Mahasiswa berdasarkan ID
    public void deletePetugas(int idpetugas) {
        petugas.deletePetugas(idpetugas);

        /* restart activity */
        finish();
        startActivity(getIntent());
    }

    //Ambil data Mahasiswa berdasarkan Id
    public void getPetugasByKdpetugas(int idpetugas) {
        String idpetugasEdit = null;
        String kdpetugasEdit = null;
        String namaEdit = null;
        String jabatanEdit = null;

        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(petugas.getPetugasByKdpetugas(idpetugas));

            for (int i = 0; i < arrayPersonal.length(); i++) ;
            JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
            idpetugasEdit = jsonChildNode.optString("idpetugas");
            kdpetugasEdit = jsonChildNode.optString("kdpetugas");
            namaEdit = jsonChildNode.optString("nama");
            jabatanEdit = jsonChildNode.optString("jabatan");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        // buat id tesembunyi di alertbuilder
        final TextView viewidpetugas = new TextView(this);
        viewidpetugas.setText(String.valueOf(idpetugasEdit));
        viewidpetugas.setTextColor(Color.TRANSPARENT);
        layoutInput.addView(viewidpetugas);

        //membuat edit text di Alert builder
        EditText editIdPetugas = new EditText(this);
        editIdPetugas.setText(idpetugasEdit);
        layoutInput.addView(editIdPetugas);

        //membuat edit text di Alert builder
        EditText editKdPetugas = new EditText(this);
        editKdPetugas.setText(kdpetugasEdit);
        layoutInput.addView(editKdPetugas);

        //membuat edit text di Alert builder
        EditText editNama = new EditText(this);
        editNama.setText(namaEdit);
        layoutInput.addView(editNama);

        //membuat edit text di Alert builder
        EditText editJabatan = new EditText(this);
        editJabatan.setText(jabatanEdit);
        layoutInput.addView(editJabatan);

        AlertDialog.Builder builderEditPetugas = new AlertDialog.Builder(this);

        //builderEditBiodata.setIcon(R.drawable.batagrams);
        builderEditPetugas.setTitle("Update Petugas");
        builderEditPetugas.setView(layoutInput);
        builderEditPetugas.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String idpetugas = viewidpetugas.getText().toString();
                String kdpetugas = editKdPetugas.getText().toString();
                String nama = editNama.getText().toString();
                String jabatan = editJabatan.getText().toString();

                System.out.println("IdPetugas : " + idpetugas + "KdPetugas : " + kdpetugas + "Nama : " + nama + "Jabatan : " + jabatan);

                String laporan = petugas.updatePetugas(viewidpetugas.getText().toString(), editKdPetugas.getText().toString(), editNama.getText().toString(), editJabatan.getText().toString());

                Toast.makeText(DataMotorActivity.this, laporan, Toast.LENGTH_SHORT).show();

                /* restart activity */
                finish();
                startActivity(getIntent());
            }
        });

        //Membuat Button Cancel pada builder
        builderEditPetugas.setNegativeButton("cencel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderEditPetugas.show();
    }

    //Metode Tambah Petugas
    public void tambahPetugas() {
        /* layout akan ditampilkan pada AlertDialog */
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

        //builderInsetBiodata.setIcon(R.drawable.batagrams);
        builderInsertPetugas.setTitle("Insert Petugas");
        builderInsertPetugas.setView(layoutInput);
        builderInsertPetugas.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String kdpetugas = editKdPetugas.getText().toString();
                String nama = editNama.getText().toString();
                String jabatan = editJabatan.getText().toString();

                System.out.println("KdPetugas : " + kdpetugas + "Nama :" + nama + "Jabatan : " + jabatan);

                String laporan = petugas.insertPetugas(kdpetugas,nama,jabatan);

                Toast.makeText(DataMotorActivity.this, laporan, Toast.LENGTH_SHORT).show();

                /* restart activity */
                finish();
                startActivity(getIntent());
            }
        });

        builderInsertPetugas.setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });
        builderInsertPetugas.show();
    }

        /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main_activity, menu);
            return true;
        }
        */

    @Override
    public void onClick(View view) {
    //1000 Auto-generated method stub
        for (int i = 0; i < buttonEdit.size(); i++) {


            /* jika yang diklik adalah button edit */
            if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                int idpetugas = buttonEdit.get(i).getId();
                getPetugasByKdpetugas(idpetugas);
            }

            /* jika yang diklik button delete */
            else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                int idpetugas = buttonDelete.get(i).getId();
                deletePetugas(idpetugas);

            }
        }
    }
}
