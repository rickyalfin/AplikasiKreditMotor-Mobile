<?php
$server = "localhost";
$username = "root";
$password = "";
$database = "db_kredit_motor";

$koneksi = mysqli_connect($server, $username, $password, $database);

@$operasi = $_GET['operasi'];

switch ($operasi) {
    case "view":
        /*Source code untuk Menampilkan kreditor*/
        $query_tampil_kreditor = mysqli_query($koneksi, "SELECT * FROM kreditor") or die(mysqli_error());
        $data_array = array();
        while ($data = mysqli_fetch_assoc($query_tampil_kreditor)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array);
        break;

    case "insert":
        /*Source code untuk insert data*/
        @$nama = $_GET['nama'];
        @$pekerjaan = $_GET['pekerjaan'];
        @$telp = $_GET['telp'];
        @$alamat = $_GET['alamat'];

        $query_insert_data = mysqli_query($koneksi, "INSERT INTO kreditor (nama, pekerjaan, telp, alamat) VALUES('$nama', '$pekerjaan','$telp', '$alamat')");

        if ($query_insert_data) {
            echo "Data Berhasil Disimpan";
        } else {
            echo "Error Insert kreditor " . mysqli_error();
        }
        break;

    case "get_kreditor_by_nama":
        /* Source code untuk Edit data dan mengirim data berdasarkan nama yang diminta */
        @$idkreditor = $_GET['idkreditor'];
        $query_tampil_kreditor = mysqli_query($koneksi, "SELECT * FROM kreditor WHERE idkreditor='$idkreditor'") or die(mysqli_error());
        $data_array = array();
        $data_array = mysqli_fetch_assoc($query_tampil_kreditor);

        echo "[" . json_encode($data_array) . "]";
        break;

    case "update":
        /* Source code untuk Updatedata */
        @$idkreditor = $_GET['idkreditor'];
        @$nama = $_GET['nama'];
        @$pekerjaan = $_GET['pekerjaan'];
        @$telp = $_GET['telp'];
        @$alamat = $_GET['alamat'];

        $query_update_kreditor = mysqli_query($koneksi, "UPDATE	kreditor	SET	nama='$nama', pekerjaan='$pekerjaan', telp='$telp', alamat='$alamat' WHERE idkreditor='$idkreditor'");

        if ($query_update_kreditor) {
            echo "Update Data Berhasil";
        } else {
            echo mysqli_error();
        }
        break;

    case "delete":
        /* Source code untuk Deletedata */
        @$idkreditor = $_GET['idkreditor'];
        $query_delete_kreditor = mysqli_query($koneksi, "DELETE FROM kreditor WHERE idkreditor='$idkreditor'");

        if ($query_delete_kreditor) {
            echo "Delete Data Berhasil";
        } else {
            echo mysqli_error();
        }
        break;

    default;
        break;
}
