<?php
header("Content-Type: application/json");
include 'koneksi.php';

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $nim      = $_POST['nim'];
    $password = $_POST['password'];

    // Lindungi dari SQL Injection
    $nim      = mysqli_real_escape_string($koneksi, $nim);
    $password = mysqli_real_escape_string($koneksi, $password);

    // Cek user
    $query = "SELECT * FROM tb_user WHERE nim = '$nim' AND password = '$password'";
    $result = mysqli_query($koneksi, $query);

    if (mysqli_num_rows($result) > 0) {
        $user = mysqli_fetch_assoc($result);

        $response['status'] = true;
        $response['message'] = "Login berhasil";
        $response['data'] = array(
            "id" => $user['id'],
            "nim" => $user['nim'],
            "nama_lengkap" => $user['nama']
        );
    } else {
        $response['status'] = false;
        $response['message'] = "NIM atau password salah";
    }
} else {
    $response['status'] = false;
    $response['message'] = "Metode tidak diizinkan";
}

echo json_encode($response);
?>
