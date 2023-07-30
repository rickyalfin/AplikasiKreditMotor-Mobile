package com.project_kreditmotor_ricky_ti21;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.os.Environment;
import android.view.View;

public class DataPengajuanKreditActivity extends AppCompatActivity implements OnClickListener {

    Kredit kredit = new Kredit();
    Server server = new Server();

    TableLayout tbQueryKredit;

    Button btRefreshKredit;

    ArrayList<Button> buttonPdf = new ArrayList<Button>();
    ArrayList<Button> buttonDelete = new ArrayList<Button>();

    JSONArray arrayQueryKredit;

    int invoice = 0; // Inisialisasi invoice dengan nilai default (misalnya 0)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajuan_kredit);

        // Penggunaan PdfUtils untuk membuat dan menyimpan PDF
        String filePath = getExternalFilesDir(null) + "/output.pdf";
        String content = "Ini adalah contoh teks dalam PDF.";
        PdfUtils.createPdf(filePath, content);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //Pemberian Nama komponen
        tbQueryKredit = (TableLayout) findViewById(R.id.tbQueryKredit);
        btRefreshKredit = (Button) findViewById(R.id.btRefreshKredit);

        tampilQueryKredit();
    }

    //Tampil data data motor
    public void tampilQueryKredit() {
        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.BLACK);

        //Memberi ID Header Tabel
        //TextView viewHeaderId = new TextView(this);
        TextView viewHeaderInvoice = new TextView(this);
        TextView viewHeaderTgl = new TextView(this);
        TextView viewHeaderIdKreditor = new TextView(this);
        TextView viewHeaderNama = new TextView(this);
        TextView viewHeaderAlamat = new TextView(this);
        TextView viewHeaderKdMotor = new TextView(this);
        TextView viewHeaderNmMotor = new TextView(this);
        TextView viewHeaderHrgTunai = new TextView(this);
        TextView viewHeaderDP = new TextView(this);
        TextView viewHeaderHrgKredit = new TextView(this);
        TextView viewHeaderBunga = new TextView(this);
        TextView viewHeaderLama = new TextView(this);
        TextView viewHeaderTotKredit = new TextView(this);
        TextView viewHeaderAngsuran = new TextView(this);

        //Memberi Nama kolom HEADER
        //viewHeaderId.setText("Id");
        viewHeaderInvoice.setText("Invoice");
        viewHeaderTgl.setText("Tgl");
        viewHeaderIdKreditor.setText("IdKreditor");
        viewHeaderNama.setText("Nama");
        viewHeaderAlamat.setText("Alamat");
        viewHeaderKdMotor.setText("KdMotor");
        viewHeaderNmMotor.setText("NmMotor");
        viewHeaderHrgTunai.setText("HrgTunai");
        viewHeaderDP.setText("DP");
        viewHeaderHrgKredit.setText("HrgKredit");
        viewHeaderBunga.setText("Bunga");
        viewHeaderLama.setText("Lama");
        viewHeaderTotKredit.setText("TotKredit");
        viewHeaderAngsuran.setText("Angsuran");

        //viewHeaderId
        viewHeaderInvoice.setTextColor(Color.WHITE);
        viewHeaderTgl.setTextColor(Color.WHITE);
        viewHeaderIdKreditor.setTextColor(Color.WHITE);
        viewHeaderNama.setTextColor(Color.WHITE);
        viewHeaderAlamat.setTextColor(Color.WHITE);
        viewHeaderKdMotor.setTextColor(Color.WHITE);
        viewHeaderNmMotor.setTextColor(Color.WHITE);
        viewHeaderHrgTunai.setTextColor(Color.WHITE);
        viewHeaderDP.setTextColor(Color.WHITE);
        viewHeaderHrgKredit.setTextColor(Color.WHITE);
        viewHeaderBunga.setTextColor(Color.WHITE);
        viewHeaderLama.setTextColor(Color.WHITE);
        viewHeaderTotKredit.setTextColor(Color.WHITE);
        viewHeaderAngsuran.setTextColor(Color.WHITE);


        //viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderInvoice.setPadding(5, 1, 5, 1);
        viewHeaderTgl.setPadding(5, 1, 5, 1);
        viewHeaderIdKreditor.setPadding(5, 1, 5, 1);
        viewHeaderNama.setPadding(5, 1, 5, 1);
        viewHeaderAlamat.setPadding(5, 1, 5, 1);
        viewHeaderKdMotor.setPadding(5, 1, 5, 1);
        viewHeaderNmMotor.setPadding(5, 1, 5, 1);
        viewHeaderHrgTunai.setPadding(5, 1, 5, 1);
        viewHeaderDP.setPadding(5, 1, 5, 1);
        viewHeaderHrgKredit.setPadding(5, 1, 5, 1);
        viewHeaderBunga.setPadding(5, 1, 5, 1);
        viewHeaderLama.setPadding(5, 1, 5, 1);
        viewHeaderTotKredit.setPadding(5, 1, 5, 1);
        viewHeaderAngsuran.setPadding(5, 1, 5, 1);


        //barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderInvoice);
        barisTabel.addView(viewHeaderTgl);
        barisTabel.addView(viewHeaderIdKreditor);
        barisTabel.addView(viewHeaderNama);
        barisTabel.addView(viewHeaderAlamat);
        barisTabel.addView(viewHeaderKdMotor);
        barisTabel.addView(viewHeaderNmMotor);
        barisTabel.addView(viewHeaderHrgTunai);
        barisTabel.addView(viewHeaderDP);
        barisTabel.addView(viewHeaderHrgKredit);
        barisTabel.addView(viewHeaderBunga);
        barisTabel.addView(viewHeaderLama);
        barisTabel.addView(viewHeaderTotKredit);
        barisTabel.addView(viewHeaderAngsuran);


        tbQueryKredit.addView(barisTabel, new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        try {
            arrayQueryKredit = new JSONArray(kredit.tampil_query_kredit());

            //Menampilkan Header Kolom
            for (int i = 0; i < arrayQueryKredit.length(); i++) {
                JSONObject jsonChildNode = arrayQueryKredit.getJSONObject(i);
                //ambil data dari nama tabel databse
                String invoiceValue = jsonChildNode.optString("invoice");
                String tanggal = jsonChildNode.optString("tanggal");
                String idkreditor = jsonChildNode.optString("idkreditor");
                String nama = jsonChildNode.optString("nama");
                String alamat = jsonChildNode.optString("alamat");
                String kdmotor = jsonChildNode.optString("kdmotor");
                String nmotor = jsonChildNode.optString("nmotor");
                String hrgtunai = jsonChildNode.optString("hrgtunai");
                String dp = jsonChildNode.optString("dp");
                String hrgkredit = jsonChildNode.optString("hrgkredit");
                String bunga = jsonChildNode.optString("bunga");
                String lama = jsonChildNode.optString("lama");
                String totalkredit = jsonChildNode.optString("totalkredit");
                String angsuran = jsonChildNode.optString("angsuran");

                // Convert String to Integer
                invoice = Integer.parseInt(invoiceValue);

                System.out.println("invoice :" + invoice);
                //System.out.println("kdmotor :" + kdmotor);

                barisTabel = new TableRow(this);

                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                //TextView viewId = new TextView(this);
                //viewId.setText(id);
                //viewId.setPadding(5, 1, 5, 1);
                //barisTabel.addView(viewId);

                TextView viewInvoice = new TextView(this);
                viewInvoice.setText(invoiceValue);
                viewInvoice.setPadding(5, 1, 5, 1);
                viewInvoice.setGravity(Gravity.CENTER);
                barisTabel.addView(viewInvoice);

                TextView viewTgl = new TextView(this);
                viewTgl.setText(tanggal);
                viewTgl.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewTgl);

                TextView viewIdKreditor = new TextView(this);
                viewIdKreditor.setText(idkreditor);
                viewIdKreditor.setPadding(5, 1, 5, 1);
                viewIdKreditor.setGravity(Gravity.CENTER);
                barisTabel.addView(viewIdKreditor);

                TextView viewviewNama = new TextView(this);
                viewviewNama.setText(nama);
                viewviewNama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewviewNama);

                TextView viewAlamat = new TextView(this);
                viewAlamat.setText(alamat);
                viewAlamat.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAlamat);

                TextView viewKdMotor = new TextView(this);
                viewKdMotor.setText(kdmotor);
                viewKdMotor.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewKdMotor);

                TextView viewNmMotor = new TextView(this);
                viewNmMotor.setText(nmotor);
                viewNmMotor.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewNmMotor);

                TextView viewHrgTunai = new TextView(this);
                viewHrgTunai.setText(hrgtunai);
                viewHrgTunai.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewHrgTunai);

                TextView viewDP = new TextView(this);
                viewDP.setText(dp);
                viewDP.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewDP);

                TextView viewHrgKredit = new TextView(this);
                viewHrgKredit.setText(hrgkredit);
                viewHrgKredit.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewHrgKredit);

                TextView viewBunga = new TextView(this);
                viewBunga.setText(bunga);
                viewBunga.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewBunga);

                TextView viewLama = new TextView(this);
                viewLama.setText(lama);
                viewLama.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewLama);

                TextView viewTotKredit = new TextView(this);
                viewTotKredit.setText(totalkredit);
                viewTotKredit.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewTotKredit);

                TextView viewAngsuran = new TextView(this);
                viewAngsuran.setText(angsuran);
                viewAngsuran.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewAngsuran);


                // Membuat Button Cetak PDF pada Baris
                buttonPdf.add(i, new Button(this));
                buttonPdf.get(i).setId(Integer.parseInt(invoiceValue));
                buttonPdf.get(i).setTag("PDF");
                buttonPdf.get(i).setText("Cetak PDF");
                buttonPdf.get(i).setOnClickListener(this);
                barisTabel.addView(buttonPdf.get(i));

                //Membuat Button Delete pada Baris
                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(invoiceValue));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTabel.addView(buttonDelete.get(i));

                tbQueryKredit.addView(barisTabel, new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteKredit(int invoice) {
        kredit.hapus_kredit(invoice);

        /* restart acrtivity */
        finish();
        startActivity(getIntent());
    }

    public void KlikbtRefreshKredit(View v){
        /* restart acrtivity */ finish(); startActivity(getIntent());
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-g1enerated method stub
        for (int i = 0; i < buttonPdf.size(); i++) {


            /* jika yang diklik adalah button edit */
            if (view.getId() == buttonPdf.get(i).getId() && view.getTag().toString().trim().equals("PDF")) {
                String fileName = "invoice_" + invoice + ".pdf";
                String content = "Isi dari PDF yang ingin Anda cetak";

                // Anda dapat mengisi dengan data yang sesuai dari aplikasi Anda.
                // Memeriksa apakah perangkat memiliki penyimpanan eksternal yang dapat ditulisi
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    // Memanggil metode utilitas untuk membuat PDF
                    PdfUtils.createPdf(Environment.getExternalStorageDirectory() + "/" + fileName, content);
                    Toast.makeText(DataPengajuanKreditActivity.this, "PDF berhasil dibuat dan disimpan di perangkat Anda.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DataPengajuanKreditActivity.this, "Gagal membuat PDF. Perangkat tidak memiliki penyimpanan eksternal yang dapat ditulisi.", Toast.LENGTH_LONG).show();
                }

            } /* jika yang diklik adalah button delete */
            else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
                int invoice = buttonDelete.get(i).getId();
                deleteKredit(invoice);

            }
        }
    }
}