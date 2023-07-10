<?php
$server = "localhost";
$username = "root";
$password = "";
$database = "db_kredit_motor";

$koneksi = mysqli_connect($server, $username, $password, $database);

@$operasi = $_GET['operasi'];

switch ($operasi) {
    case "view":
        /* Source code untuk Menampilkan motor */
        $query_tampil_motor = mysqli_query($koneksi, "SELECT * FROM motor") or die(mysqli_error());
        $data_array = array();
        while ($data = mysqli_fetch_assoc($query_tampil_motor)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array);
        break;

    case "select_by_idnama":
        /* Source code untuk Menampilkan kreditor */
        $query_by_idnama = mysqli_query($koneksi, "select kdmotor, nama, harga from motor order by kdmotor,nama") or die(mysqli_error());
        $data_array = array();
        while ($data = mysqli_fetch_assoc($query_by_idnama)) {
            $data_array[] = $data;
        }
        echo json_encode($data_array);
        break;

    case "insert":
        /* Source code untuk Insert data */
        @$kdmotor = $_GET['kdmotor'];
        @$nama = $_GET['nama'];
        @$harga = $_GET['harga'];

        $query_insert_data = mysqli_query($koneksi, "INSERT INTO motor (kdmotor, nama, harga) VALUES('$kdmotor', '$nama', '$harga')");
        if ($query_insert_data) {
            echo "Data Berhasil Disimpan";
        } else {
            echo "Error Insert motor " . mysqli_error();
        }
        break;

    case "get_motor_by_kdmotor":
        /* Source code untuk Edit data dan mengirim data berdasarkan kdmotor yang diminta */
        @$idmotor = $_GET['idmotor'];
        $query_tampil_motor = mysqli_query($koneksi, "SELECT * FROM motor WHERE idmotor='$idmotor'") or die(mysqli_error());
        $data_array = array();
        $data_array = mysqli_fetch_assoc($query_tampil_motor);
        echo "[" . json_encode($data_array) . "]";
        break;

    case "select_by_kdmotorkredit":
        /* Source code untuk menampilkan berdasarkan kdmotor yang diminta */
        @$kdmotor = $_GET['kdmotor'];
        @$idmotor = $_GET['idmotor'];

        $query_tampil_motor = mysqli_query($koneksi, "SELECT * FROM motor WHERE kdmotor='$kdmotor'") or die(mysqli_error());
        $data_array = array();
        $data_array = mysqli_fetch_assoc($query_tampil_motor);
        echo "[" . json_encode($data_array) . "]";
        break;

    case "update":
        /* Source code untuk Updatedata */
        @$idmotor = $_GET['idmotor'];
        @$kdmotor = $_GET['kdmotor'];
        @$nama = $_GET['nama'];
        @$harga = $_GET['harga'];

        $query_update_motor = mysqli_query($koneksi, "UPDATE motor SET nama='$nama', harga='$harga' WHERE idmotor='$idmotor'");
        if ($query_update_motor) {echo "Update Data Berhasil";
        } else {
            echo mysqli_error();
        }
        break;

    case "delete":
        /* Source code untuk Deletedata */@$idmotor = $_GET['idmotor'];
        $query_delete_motor = mysqli_query($koneksi, "DELETE FROM motor WHERE idmotor='$idmotor'");
        if ($query_delete_motor) {
            echo "Delete Data Berhasil";
        } else {
            echo mysqli_error();
        }

        break;

    default:
        break;
}
