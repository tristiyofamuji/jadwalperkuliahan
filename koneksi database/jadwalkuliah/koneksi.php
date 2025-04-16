<?php
$host     = "localhost";
$username = "root";
$password = "";
$database = "db_jadwalkuliah";

// Membuat koneksi
$koneksi = mysqli_connect($host, $username, $password, $database);

// Cek koneksi
if (mysqli_connect_errno()) {
    echo "Koneksi database gagal: " . mysqli_connect_error();
    exit();
} else {
    // echo "Koneksi berhasil"; // Aktifkan ini jika ingin test koneksi langsung
}
?>
