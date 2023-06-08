package com.project_kreditmotor_ricky_ti21;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
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
public class DataMotorActivity extends AppCompatActivity implements OnClickListener{

    //Inisialisasi Objek + Variabel + Class
    Motor motor = new Motor();

    TableLayout tbMotor;

    Button btTambahMotor,btRefreshDataMotor;

    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();
    JSONArray arrayMotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_motor);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    //Pemberian Nama komponen
        tbMotor = (TableLayout) findViewById(R.id.tbMotor);
        btTambahMotor = (Button) findViewById(R.id.btTambahMotor);
        btRefreshDataMotor = (Button) findViewById(R.id.btRefreshDataMotor);

        tampildataMotor();
    }

    //Metode KlikbtTambahMotor
    public void KlikbtTambahMotor(View v){
        tambahMotor();
    }

    //Refresh Data
    public void KlikbtRefreshDataMotor(View v){
        /* restart acrtivity */
        finish();
        startActivity(getIntent());
    }

    //Tampil data data motor
    public void tampildataMotor(){
    TableRow barisTabel = new TableRow(this);
    barisTabel.setBackgroundColor(Color.BLACK);

    //Memberi ID Header Tabel
    //TextView viewHeaderId = new TextView(this);
    TextView viewHeaderKdMotor = new TextView(this);
    viewHeaderKdMotor.setGravity(Gravity.CENTER);
    TextView viewHeaderNama = new TextView(this);
    viewHeaderNama.setGravity(Gravity.CENTER);
    TextView viewHeaderHarga= new TextView(this);
    viewHeaderHarga.setGravity(Gravity.CENTER);
    TextView viewHeaderAction = new TextView(this);
    viewHeaderAction.setGravity(Gravity.CENTER);

    //Memberi Nama kolom HEADER
    //viewHeaderId.setText("Id");
    viewHeaderKdMotor.setText("KdMotor");
    viewHeaderNama.setText("Nama");
    viewHeaderHarga.setText("Harga");
    viewHeaderAction.setText("Action");

    viewHeaderKdMotor.setTextColor(Color.WHITE);
    viewHeaderNama.setTextColor(Color.WHITE);
    viewHeaderHarga.setTextColor(Color.WHITE);
    viewHeaderAction.setTextColor(Color.WHITE);

    //viewHeaderId.setPadding(5, 1, 5, 1);
    viewHeaderKdMotor.setPadding(5, 1, 5, 1);
    viewHeaderNama.setPadding(5, 1, 5, 1);
    viewHeaderHarga.setPadding(5, 1, 5, 1);
    viewHeaderAction.setPadding(5, 1, 5, 1);

    //barisTabel.addView(viewHeaderId);
    barisTabel.addView(viewHeaderKdMotor);
    barisTabel.addView(viewHeaderNama);
    barisTabel.addView(viewHeaderHarga);
    barisTabel.addView(viewHeaderAction);

    tbMotor.addView(barisTabel, new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));

    try {
        arrayMotor = new JSONArray(motor.tampilMotor());

    //Menampilkan Header Kolom
        for (int i = 0; i < arrayMotor.length(); i++) {
            JSONObject jsonChildNode = arrayMotor.getJSONObject(i);

    //ambil data dari nama tabel databse
            String idmotor = jsonChildNode.optString("idmotor");
            String kdmotor = jsonChildNode.optString("kdmotor");
            String nama = jsonChildNode.optString("nama");
            String harga = jsonChildNode.optString("harga");

            System.out.println("id :" + idmotor);
            System.out.println("kdmotor :" + kdmotor);
            System.out.println("nama :" + nama);
            System.out.println("harga :" + harga);

            barisTabel = new TableRow(this);

            if (i % 2 == 0) {
                barisTabel.setBackgroundColor(Color.LTGRAY);
            }

        //TextView viewId = new TextView(this);
        //viewId.setText(id);
        //viewId.setPadding(5, 1, 5, 1);
        //barisTabel.addView(viewId);

            TextView viewKdMotor = new TextView(this);
            viewKdMotor.setGravity(Gravity.CENTER);
            viewKdMotor.setText(kdmotor);
            viewKdMotor.setPadding(5, 1, 5, 1);
            barisTabel.addView(viewKdMotor);

            TextView viewNama = new TextView(this);
            viewNama.setText(nama);
            viewNama.setPadding(5, 1, 5, 1);
            barisTabel.addView(viewNama);

            TextView viewHarga = new TextView(this);
            viewHarga.setText(harga);
            viewHarga.setPadding(5, 1, 5, 1);
            barisTabel.addView(viewHarga);

        //Membuat Button Edit pada Baris
            buttonEdit.add(i, new Button(this));
            buttonEdit.get(i).setId(Integer.parseInt(idmotor));
            buttonEdit.get(i).setTag("Edit");
            buttonEdit.get(i).setText("Edit");
            buttonEdit.get(i).setOnClickListener(this);
            barisTabel.addView(buttonEdit.get(i));

        //Membuat Button Delete pada Baris
            buttonDelete.add(i, new Button(this));
            buttonDelete.get(i).setId(Integer.parseInt(idmotor));
            buttonDelete.get(i).setTag("Delete");
            buttonDelete.get(i).setText("Delete");
            buttonDelete.get(i).setOnClickListener(this);
            barisTabel.addView(buttonDelete.get(i));

            tbMotor.addView(barisTabel, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        }
    } catch (JSONException e) {
        e.printStackTrace();
    }
}

    //Hapus Motor bersadarkan ID
    public void deleteMotor(int id) {
        motor.deleteMotor(id);

        /* restart acrtivity */
        finish();
        startActivity(getIntent());
        }

    //Ambil data motor bersdasarkan Id
    public void getMotorByKdmotor(int idmotor) {

        String idmotorEdit = null;
        String kdmotorEdit = null;
        String namaEdit = null;
        String hargaEdit = null;

        JSONArray arrayPersonal;

        try {
            arrayPersonal = new JSONArray(motor.getMotorByKdmotor(idmotor));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                idmotorEdit = jsonChildNode.optString("idmotor");
                kdmotorEdit = jsonChildNode.optString("kdmotor");
                namaEdit = jsonChildNode.optString("nama");
                hargaEdit = jsonChildNode.optString("harga");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

    // buat id tersembunyi di alertbuilder
    final TextView viewKdmotor = new TextView(this);
    viewKdmotor.setBackgroundColor(Color.TRANSPARENT);
    viewKdmotor.setTextColor(Color.WHITE);
    viewKdmotor.setTextSize(20);
    layoutInput.addView(viewKdmotor);

    //membuat edit text di Allert builder
    final EditText editIdMotor = new EditText(this);
    editIdMotor.setText(idmotorEdit);
    //layoutInput.addView(editKdMotor);

    //membuat edit text di Allert builder
    final EditText editNama = new EditText(this);
    editNama.setText(namaEdit);
    layoutInput.addView(editNama);

    //membuat edit text di Allert builder
    final EditText editHarga = new EditText(this);
    editHarga.setText(hargaEdit);
    layoutInput.addView(editHarga);

    AlertDialog.Builder builderEditMotor = new AlertDialog.Builder(this);
    //builderEditBiodata.setIcon(R.drawable.batagrams);
        builderEditMotor.setTitle("Update Motor");
        builderEditMotor.setView(layoutInput);
        builderEditMotor.setPositiveButton("Update", new DialogInterface.OnClickListener() {

    @Override
    public void onClick(DialogInterface dialog, int which) {
        String idmotor = editIdMotor.getText().toString();
        String kdmotor = viewKdmotor.getText().toString();
        String nama = editNama.getText().toString();
        String harga = editHarga.getText().toString();

        System.out.println("IdMotor :" + idmotor + " KdMotor : " + kdmotor + " Nama : " + nama + " Harga : " + harga);

        String laporan = motor.updateMotor(editIdMotor.getText().toString(), viewKdmotor.getText().toString(),editNama.getText().toString(),editHarga.getText().toString());

        Toast.makeText(DataMotorActivity.this, laporan, Toast.LENGTH_SHORT).show();

        }

        });

    //membuat Button Cancel pada builder
        builderEditMotor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { dialog.cancel();
            }
        });
        builderEditMotor.show();
    }

    //Metode tambah Motor
    public void tambahMotor() {
        /* layout akan ditampilkan pada AlertDialog */
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final EditText editKdMotor = new EditText(this);
        editKdMotor.setHint("KdMotor");
        layoutInput.addView(editKdMotor);

        final EditText editNama = new EditText(this);
        editNama.setHint("Nama");
        layoutInput.addView(editNama);

        final EditText editHarga= new EditText(this);
        editHarga.setHint("harga");
        layoutInput.addView(editHarga);

        AlertDialog.Builder builderInsertMotor = new AlertDialog.Builder(this);

        //builderInsertMotor.setIcon(R.drawable.batagrams);
        builderInsertMotor.setTitle("Insert Motor");
        builderInsertMotor.setView(layoutInput);
        builderInsertMotor.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String kdmotor = editKdMotor.getText().toString();
                String nama = editNama.getText().toString();
                String harga = editHarga.getText().toString();

                System.out.println("KdMotor : " + kdmotor + " Nama : " + nama + " Harga : " + harga);

                String laporan = motor.insertMotor(kdmotor,nama,harga);

                Toast.makeText(DataMotorActivity.this, laporan, Toast.LENGTH_SHORT).show();

            /* restart acrtivity */
                finish();
                startActivity(getIntent());
            }
        });

        builderInsertMotor.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertMotor.show();
    }

    @Override
    public void onClick(View view){
        // TODO Auto-generated method stub
        for(int i=0;i<buttonEdit.size();i++){

            /* jika yang diklik adalah button edit */
            if(view.getId() == buttonEdit.get(i).getId()&&view.getTag().toString().trim().equals("Edit")) {

            // Toast.makeText(MainActivity.this, "Edit : " +
            // buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();

                int idmotor = buttonEdit.get(i).getId();
                getMotorByKdmotor(idmotor);

            } /* jika yang diklik adalah button delete */
            else if(view.getId()==buttonDelete.get(i).getId()&&view.getTag().toString().trim().equals("Delete")){

            // Toast.makeText(MainActivity.this, "Delete : " +
            // buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();

                int idmotor = buttonDelete.get(i).getId();
                deleteMotor(idmotor);
            }
        }
    }
}