<?php
$server = "localhost";
$username = "root";
$password = "";
$database = "db_kredit_motor";

$koneksi = mysqli_connect($server, $username, $password, $database);

@$operasi = $_GET['operasi'];

switch ($operasi) {
    case "view":
        /* Source code untuk Menampilkan petugas */

        $query_tampil_petugas = mysqli_query($koneksi, "SELECT * FROM petugas") or die(mysqli_error());
        $data_array = array();
        while ($data = mysqli_fetch_assoc($query_tampil_petugas)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array);
        break;

    case "query_kredit":

        /* Source code untuk Menampilkan petugas */
        $query_tampil_petugas = mysqli_query($koneksi, "SELECT kredit.invoice, kredit.tanggal, kredit.idkreditor,
        kreditor.nama, kreditor.alamat, kredit.kdmotor, motor.nama as nmotor, kredit.hrgtunai, kredit.dp,
        kredit.hrgkredit, kredit.bunga, kredit.lama, kredit.totalkredit, kredit.angsuran FROM motor INNER JOIN
        (kreditor INNER JOIN kredit ON kreditor.idkreditor = kredit.idkreditor) ON motor.kdmotor = kredit.kdmotor
        order by kredit.invoice desc") or die(mysqli_error());
        $data_array = array();
        while ($data = mysqli_fetch_assoc($query_tampil_petugas)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array);
        break;

    case "simpan_kredit":

        /* Source code untuk Insert data */@$idkreditor = $_GET['idkreditor'];
        @$kdmotor = $_GET['kdmotor'];
        @$hrgtunai = $_GET['hrgtunai'];
        @$dp = $_GET['dp'];
        @$hrgkredit = $_GET['hrgkredit'];
        @$bunga = $_GET['bunga'];
        @$lama = $_GET['lama'];
        @$totalkredit = $_GET['totalkredit'];
        @$angsuran = $_GET['angsuran'];

        $query_insert_data = mysqli_query($koneksi, "INSERT INTO kredit (idkreditor, kdmotor, hrgtunai, dp,
        hrgkredit, bunga, lama, totalkredit, angsuran) VALUES($idkreditor, '$kdmotor', '$hrgtunai', '$dp', '$hrgkredit',
        '$bunga', '$lama', '$totalkredit', '$angsuran')");
        if ($query_insert_data) {
            echo "Data Berhasil Disimpan";
        } else {
            echo "Error Insert petugas " . mysqli_error();
        }

        break;

    case "get_petugas_by_kdpetugas":

        /* Source code untuk Edit data dan mengirim data berdasarkan kdpetugas yang diminta */
        @$idpetugas = $_GET['idpetugas'];
        $query_tampil_petugas = mysqli_query($koneksi, "SELECT * FROM petugas WHERE idpetugas='$idpetugas'") or die(mysqli_error());
        $data_array = array();
        $data_array = mysqli_fetch_assoc($query_tampil_petugas);
        echo "[" . json_encode($data_array) . "]";

        break;

    case "hapus_kredit":

        /* Source code untuk Deletedata */
        @$invoice = $_GET['invoice'];

        $query_delete_petugas = mysqli_query($koneksi, "DELETE FROM kredit WHERE invoice='$invoice'");
        if ($query_delete_petugas) {
            echo "Berhasil hapus data Pengajuan Kredit.";
        } else {
            echo mysqli_error();
        }

        break;

    default:break;
}
