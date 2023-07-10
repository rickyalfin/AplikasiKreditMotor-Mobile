package com.project_kreditmotor_ricky_ti21;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class DataPengajuanKreditActivity extends AppCompatActivity {

    Kreditor kreditor = new Kreditor();
    Motor motor = new Motor ();
    Kredit kredit = new Kredit ();

    Spinner SpinnerNamaKreditor,SpinnerNamaMotor;

    EditText editUangMuka,editBunga,editLamaAngsuran;

    TextView textNamaMotor,textAlamatKrditor,textNamaKreditor,textHargaMotor,textHargaKredit,textTotalKredit,textAngsuranPerbulan;

    Button buttonProsesPengajuanKredit,buttonSimpanPengajuanKredit,buttonResetKredit;

    ArrayList<String> arrayListNamaKreditor = new ArrayList<String>();
    ArrayList<String> arrayListNamaMotor = new ArrayList<String>();

    JSONArray arrayKreditor;
    JSONArray arrayMotor;

    String idkreditorspiner;

    private double HargaKredit = 0.0;
    private double TotalKredit = 0.0;
    private double Angsuran = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajuan_kredit);

        if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build(); StrictMode.setThreadPolicy(policy);
        }
        textNamaKreditor = (TextView) findViewById(R.id.TextNamaKreditor);
        textAlamatKrditor = (TextView) findViewById(R.id.TextAlamatKreditor);
        textNamaMotor = (TextView) findViewById(R.id.textNamaMotor);
        textHargaMotor = (TextView) findViewById(R.id.textHargaMotor);
        textHargaKredit = (TextView) findViewById(R.id.textHargaKredit);
        textTotalKredit = (TextView) findViewById(R.id.textTotalKredit);
        textAngsuranPerbulan = (TextView) findViewById(R.id.textAngsuranPerbulan);

        editBunga = (EditText) findViewById(R.id.editBunga);
        editUangMuka = (EditText) findViewById(R.id.editUangMuka);
        editLamaAngsuran = (EditText) findViewById(R.id.editLamaAngsuran);

        buttonProsesPengajuanKredit = (Button) findViewById(R.id.buttonProsesPengajuanKredit);
        buttonSimpanPengajuanKredit = (Button) findViewById(R.id.buttonSimpanPengajuanKredit);
        buttonResetKredit = (Button) findViewById(R.id.buttonResetKredit);



        SpinnerNamaKreditor = (Spinner) findViewById(R.id.SpinnerNamaKreditor);
        //SpinnerNamaKreditor.setOnItemSelectedListener(this);
        SpinnerNamaKreditor.setOnItemSelectedListener(new OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                 // TODO Auto-generated method stub
                 getNamaKreditor();
             }
              @Override
              public void onNothingSelected(AdapterView<?> arg0) {
                 // TODO Auto-generated method stub

             }
        });

        SpinnerNamaMotor = (Spinner) findViewById(R.id.SpinnerNamaMotor);
        //SpinnerNamaMotor.setOnItemSelectedListener(this);
        SpinnerNamaMotor.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                // TODO Auto-generated method stub
                getKdmotorKredit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        Spinerkreditor();
        Spinermotor();
    }

    public void Spinerkreditor() {
        try {
            arrayKreditor = new JSONArray(kreditor.tampilKreditor());

            for (int i = 0; i < arrayKreditor.length(); i++) {
                JSONObject jsonChildNode = arrayKreditor.getJSONObject(i);
                //ambil data dari nama tabel databse
                String idkreditor = jsonChildNode.optString("idkreditor");
                String nama = jsonChildNode.optString("nama");

                System.out.println("id :" + idkreditor);
                System.out.println("nama :" + nama);

                //arrayListNamaKreditor.add(idkreditor +"-"+nama);
                arrayListNamaKreditor.add(idkreditor);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // membuat adapter untuk menghubungkan spinner dengan data arraylist
        ArrayAdapter<String> adapterNamaKreditor = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, arrayListNamaKreditor);

        adapterNamaKreditor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // masukkan adapter kedalam spinner
        SpinnerNamaKreditor.setAdapter(adapterNamaKreditor);
        SpinnerNamaKreditor.setSelection(0);
    }

    public void Spinermotor() {
        try {
            arrayMotor = new JSONArray(motor.tampilMotorbyIdNama());

            for (int i = 0; i < arrayMotor.length(); i++) {
                JSONObject jsonChildNode = arrayMotor.getJSONObject(i);

                //ambil data dari nama tabel databse
                String kdmotor = jsonChildNode.optString("kdmotor");
                String nama = jsonChildNode.optString("nama");
                String harga = jsonChildNode.optString("harga");

                System.out.println("kdmotor :" + kdmotor);
                System.out.println("nama :" + nama);
                System.out.println("harga :" + harga);

                //arrayListNamaMotor.add(kdmotor +" - "+nama);
                arrayListNamaMotor.add(kdmotor);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // membuat adapter untuk menghubungkan spinner dengan data arraylist
        ArrayAdapter<String> adapterNamaMotor = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, arrayListNamaMotor);

        adapterNamaMotor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // masukkan adapter kedalam spinner
        SpinnerNamaMotor.setAdapter(adapterNamaMotor);
        SpinnerNamaMotor.setSelection(0);
    }

    public void getNamaKreditor() {
        // TODO Auto-generated method stub
        String idkreditor =(SpinnerNamaKreditor.getSelectedItem().toString());

        String namaEdit = null;
        String alamatEdit = null;

        JSONArray arrayKreditor;

        try {

            arrayKreditor = new
                    JSONArray(kreditor.getKreditorByNama(Integer.parseInt(idkreditor)));

            for (int i = 0; i < arrayKreditor.length(); i++) {
                JSONObject jsonChildNode = arrayKreditor.getJSONObject(i);
                namaEdit = jsonChildNode.optString("nama");
                alamatEdit = jsonChildNode.optString("alamat");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        textNamaKreditor.setText(namaEdit);
        textAlamatKrditor.setText(alamatEdit);
    }

    public void getKdmotorKredit(){
        String kdmotor =(SpinnerNamaMotor.getSelectedItem().toString());

        String idmotorEdit = null;
        String kdmotorEdit = null;
        String namaEdit = null;
        String hargaEdit = null;

        JSONArray arrayKodemotor;

        try {
            arrayKodemotor = new JSONArray(motor.select_by_KdmotorKredit(kdmotor));

            for (int i = 0; i < arrayKodemotor.length(); i++) {
                JSONObject jsonChildNode = arrayKodemotor.getJSONObject(i);
                idmotorEdit = jsonChildNode.optString("idmotor");
                kdmotorEdit = jsonChildNode.optString("kdmotor");
                namaEdit = jsonChildNode.optString("nama");
                hargaEdit = jsonChildNode.optString("harga");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textNamaMotor.setText(namaEdit);
        textHargaMotor.setText(hargaEdit);

    }

    public void HitungKredit() {
        String sharga = textHargaMotor.getText().toString();
        String sdp = editUangMuka.getText().toString();
        String sbunga = editBunga.getText().toString();
        String slama = editLamaAngsuran.getText().toString();

        if ((sharga.equalsIgnoreCase("")) || (sharga.equalsIgnoreCase("0")) || (sdp.equalsIgnoreCase("")) || (sdp.equalsIgnoreCase("0")) || (sbunga.equalsIgnoreCase("")) || (sbunga.equalsIgnoreCase("0")) || (slama.equalsIgnoreCase("")) || (slama.equalsIgnoreCase("0"))){
            Toast.makeText(this, "Silahkan Lengkapi Data !", Toast.LENGTH_LONG).show();

        } else {
            double harga = Double.parseDouble(textHargaMotor.getText().toString());
            double dp = Double.parseDouble(editUangMuka.getText().toString());
            double bunga = Double.parseDouble(editBunga.getText().toString());
            double lama = Double.parseDouble(editLamaAngsuran.getText().toString());

            HargaKredit = harga - dp;
            TotalKredit = HargaKredit + (HargaKredit * (bunga/100) * 12);

            Angsuran = TotalKredit / lama;

            String sHargaKredit = String.format("%,.2f",HargaKredit);
            textHargaKredit.setText(sHargaKredit);

            String sTotalKredit = String.format("%,.2f",TotalKredit);
            textTotalKredit.setText(sTotalKredit);

            String sAngsuran = String.format("%,.2f",Angsuran);
            textAngsuranPerbulan.setText(sAngsuran);
        }
    }

    public void KlikbuttonProsesPengajuanKredit(View v){ HitungKredit();
    }

    public void KlikbuttonResetKredit(View v) {
        editUangMuka.setText("");
        editBunga.setText("");
        editLamaAngsuran.setText("");
        textTotalKredit.setText("");
        textAngsuranPerbulan.setText("");
    }

    public void KlikbuttonSimpanPengajuanKredit(View v) {
        simpanKredit();
    }

    //Metode tambah kredit
    public void simpanKredit() {
        /* layout akan ditampilkan pada AlertDialog */
        String idkreditor = SpinnerNamaKreditor.getSelectedItem().toString();
        String kdmotor = SpinnerNamaMotor.getSelectedItem().toString();
        String hrgtunai = textHargaMotor.getText().toString();
        String dp = editUangMuka.getText().toString();
        String hrgkredit = textHargaKredit.getText().toString();
        String bunga = editBunga.getText().toString();
        String lama = editLamaAngsuran.getText().toString();
        String totalkredit = textTotalKredit.getText().toString();
        String angsuran = textAngsuranPerbulan.getText().toString();

        //String idkreditor = "2";
        // String kdmotor = "SPRC125";

        System.out.println("idkreditor : " + idkreditor + " kdmotor : " + kdmotor);

        String laporan = kredit.simpan_kredit(idkreditor,kdmotor,hrgtunai,dp,hrgkredit,bunga,lama,totalkredit,angsuran);Toast.makeText(DataPengajuanKreditActivity.this, laporan, Toast.LENGTH_SHORT).show();

        /* restart acrtivity */
        finish();
        startActivity(getIntent());
    }
}
